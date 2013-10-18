/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.batch.processor.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.EduPersonAffiliationCalculator;
import edu.psu.iam.cpr.core.database.batch.EmployeeInfo;
import edu.psu.iam.cpr.core.database.beans.TransHershey;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateHelper;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.ChangeNotification;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.DataQualityService;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides the concrete implementation for the Hershey Medical Center data batch processor.
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.batch
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */

public class HMCBatchProcessor extends GenericBatchProcessor {

	private static final String HMC_DATE_FORMAT = "MMddyyyy";

	private static final SimpleDateFormat HMC_DATE_PARSER = new SimpleDateFormat(HMC_DATE_FORMAT);

	/** Constant United States of America */
	private static final String USA = "USA";

	/** Instance of logger */
	private static final Logger LOG = Logger.getLogger(HMCBatchProcessor.class);

	/** Contains the number of records to fetch for initial processing */
	private static final int RECORD_FETCH_SIZE = 50;
	
	/** Contains the name of the database table for the source of records */
	private static final String TRANS_DATABASE_TABLE = "TransHershey";

	/** Contains the name of the batch data source associated with this processor */
	private static final BatchDataSource BATCH_DATA_SOURCE = BatchDataSource.HMC;

	/** Contains a reference to the EduPersonAffiliationCalculator */
	private EduPersonAffiliationCalculator affiliationCalculator;
	
	/** Contains today's date as of midnight */
	private DateTime now = DateTime.now().withTime(0, 0, 0, 0);
	
	/** Hershey start date key */
	private static final String HY_START_DATE_KEY = "HyStartDateKey";
	
	/** Hershey end date key */
	private static final String HY_END_DATE_KEY = "HyEndDateKey";
	
	/**
	 * 
	 * NOTE: We do not send name or address change notifications in any batch processor code, because those messages must be sent only by the name 
	 * and address post-processors
	 * 
	 */
	

	/**
	 * Add a new employee to the CPR.
	 *
	 * @param transHershey A temporary employee record
	 * @param employeeInfo A reference to the employee info class
	 * @param changeNotification contains an instance of the change notification class.
	 * @throws CprException on any error
	 * @throws JMSException
	 * @throws JSONException
	 */
	protected void addEmployee(final TransHershey transHershey, final EmployeeInfo employeeInfo,
			final ChangeNotification changeNotification) throws CprException, JSONException, JMSException {

		employeeInfo.addPerson();
		employeeInfo.addPsuId(transHershey.getPsuId());

		employeeInfo.addName(transHershey.getFirstName(), transHershey.getMiddleNames(), transHershey.getLastName(), transHershey.getSuffix());

		// add gender if not null.
		if (!ValidateHelper.isFieldEmpty(transHershey.getGender())) {
			try {
				final GenderType genderType = Utility.genderStringToType(transHershey.getGender());
				employeeInfo.addGender(genderType);
			} catch (final IllegalArgumentException ex) { // $codepro.audit.disable logExceptions
				LOG.error("Invalid gender for record " + transHershey.getTransHersheyKey());
			}
		}

		// add date of birth if not null
		try {
			final Date birthDate = parseDateString(transHershey.getDateOfBirth());
			employeeInfo.updateDateOfBirth(birthDate);
			changeNotification.dateOfBirthChange(employeeInfo.getNewDateOfBirth(), employeeInfo.getOldDateOfBirth());
		} catch (final ParseException ex) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid birthdate", ex);
		}

		// use the updatePhone method here b/c it handles group id automatically
		employeeInfo.updatePhone(PhoneType.WORK_PHONE, transHershey.getWorkPhone(), transHershey.getWorkPhoneExt());
		changeNotification.phoneChange(employeeInfo.getOldPhone(), employeeInfo.getOldPhone());

		// use the updateAddress method here b/c it handles group id automatically
		employeeInfo.updateAddress(AddressType.PERMANENT_ADDRESS, transHershey.getHomeAddress1(), transHershey.getHomeAddress2(), null,
				transHershey.getHomeCity(), transHershey.getHomeState(), transHershey.getHomeZipcode(),
				transHershey.getHomeCountry(), null);

		/*		// don't process W4 addresses for now
		 *		employeeInfo.updateAddress(AddressType.W4_PERMANENT_ADDRESS, transHershey.getHomeAddress1(), transHershey.getHomeAddress2(), null,
		 *				transHershey.getHomeCity(), transHershey.getHomeState(), transHershey.getHomeZipCode(),
		 *				transHershey.getHomeCountry(), null);
		 */

		// calculate the "mail stop + dept" and use for street1. use trans street1 as street2, and trans street2 as street 3.
		employeeInfo.updateAddress(AddressType.WORK_ADDRESS, calculateWorkAddressStreet1(transHershey), transHershey.getWorkAddress1(),
				transHershey.getWorkAddress2(), transHershey.getWorkCity(), transHershey.getWorkState(), transHershey.getWorkZipcode(), USA, null);

		Map<String, DateTime> hyStartEndDates = getHersheyStartEndDates(transHershey);
		
		Date hyStartDate = null;
		Date hyEndDate = null;
		
		if(hyStartEndDates.get(HY_START_DATE_KEY) != null) {
			hyStartDate = hyStartEndDates.get(HY_START_DATE_KEY).toDate();
		}
		
		if(hyStartEndDates.get(HY_END_DATE_KEY) != null) {
			hyEndDate = hyStartEndDates.get(HY_END_DATE_KEY).toDate();
		}
		
		// add the employee-specific data
		employeeInfo.addEmployee(transHershey.getTitle(), null, hyStartDate, hyEndDate,
				null, transHershey.getApptTypeCode(), null, null,
				null, transHershey.getDeptName(), null, mapHersheyStatus(hyStartEndDates.get(HY_START_DATE_KEY), hyStartEndDates.get(HY_END_DATE_KEY)),
				null, null, null, null, null, null, null, null);

		// calculate affiliation
		final AffiliationsType hmcAffiliation = mapHersheyAffiliation(hyStartEndDates.get(HY_START_DATE_KEY), hyStartEndDates.get(HY_END_DATE_KEY));
		affiliationCalculator.setPersonId(employeeInfo.getPersonId());
		affiliationCalculator.setHMCAffiliation(hmcAffiliation.index());

		changeNotification.setRequiredInfo(employeeInfo.getPersonId(), employeeInfo.getPsuIdNumber(), null, BATCH_DATA_SOURCE.toString());
		changeNotification.newEmployee(employeeInfo.getNewName(),
				employeeInfo.getNewAddress(),
				employeeInfo.getNewPhone(),
				null, 	// email address.
				affiliationCalculator.getNewPersonAffiliation(), 	// affiliation
				employeeInfo.getNewPersonGender(),
				null, 	// confidentiality
				employeeInfo.getNewEmployee());
	}

	/**
	 * Calculate work address street 1 for Hershey.
	 * 
	 * @param transHershey A TransHershey record
	 * @return A string with street address line 1 for the work address. On error, returns null.
	 */
	protected String calculateWorkAddressStreet1(final TransHershey transHershey) {
		if (transHershey == null ||
				ValidateHelper.isFieldEmpty(transHershey.getDeptName()) || ValidateHelper.isFieldEmpty(transHershey.getMailCode())) {
			return null;
		}

		return transHershey.getDeptName() + " - " + transHershey.getMailCode();
	}

	/**
	 * This method provides the implementation of the Hershey medical center processor.
	 * @param databaseSession contains an instance of the database session.
	 * @param messagingCore contains the messaging core instance.
	 * @param dataQualityService contains an instance to the data flux server.
	 * @throws CprException will be thrown if there are any Cpr relate problems.
	 */
	@Override
	public void implementBatchProcessor(final StatelessSession databaseSession,
			final MessagingCore messagingCore, final DataQualityService dataQualityService) throws CprException, JSONException, JMSException {

		final long startTime, stopTime;
		final long totalRecords;
		long recordsProcessed = 0;

		startTime = System.currentTimeMillis();

		// Perform a query for all of the trans hershey records.
		final Query query = databaseSession.createQuery("FROM " + TRANS_DATABASE_TABLE + " ORDER BY transHersheyKey ASC");
		query.setFetchSize(RECORD_FETCH_SIZE);
		// Below line should be uncommented during manual testing to only allow a certain number of records through at a time.
//		query.setMaxResults(TEST_RECORD_MAX_SIZE);

		
		
		StatelessSession recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		final EmployeeInfo employeeInfo = new EmployeeInfo(recordSession, BATCH_DATA_SOURCE, dataQualityService);
		final ChangeNotification changeNotification = new ChangeNotification(recordSession, messagingCore);
		affiliationCalculator = new EduPersonAffiliationCalculator(recordSession, BATCH_DATA_SOURCE, dataQualityService);

		Transaction tx = null;

		final List<?> queryList = query.list();
		totalRecords = queryList.size();

		// Loop for all of the records that were found.
		for (final Iterator<?> it = queryList.iterator(); it.hasNext(); ) {
			final TransHershey transHershey = (TransHershey) it.next();
			recordsProcessed++;

			try {
				// Begin transaction.
				tx = recordSession.beginTransaction();

				employeeInfo.resetHistoryBeans();
				affiliationCalculator.resetHistoryBeans();

				employeeInfo.setRecordNumber(transHershey.getTransHersheyKey().longValue());
				employeeInfo.findPerson(transHershey.getPsuId());
				final Long personId = employeeInfo.getPersonId();
				if (personId == null) {

					// The psu id might have been merged or reassigned.
					// Call CIDR to check. If so, do an update.

					addEmployee(transHershey, employeeInfo, changeNotification);
				} else {
					updateEmployee(transHershey, employeeInfo, changeNotification);
				}

				// Commit!
				tx.commit();
			} catch (final HibernateException e) { // $codepro.audit.disable logExceptions

				// Log the error.
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transHershey.getTransHersheyKey(), e);

				// Rollback the transaction, close the session.
				tx.rollback();
				recordSession.close();

				// We need to create a new session and update the helper classes with the new session.
				recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
				employeeInfo.setDatabaseSession(recordSession);
				changeNotification.setStatelessSession(recordSession);
				affiliationCalculator.setDatabaseSession(recordSession);
			}
		}

		try {
			recordSession.close();
		} catch (final HibernateException e) { // $codepro.audit.disable logExceptions

			// Rollback the transaction, close the session.
			tx.rollback();
			recordSession.close();

			// We need to create a new session and update the helper classes with the new session.
			recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			employeeInfo.setDatabaseSession(recordSession);
			changeNotification.setStatelessSession(recordSession);
			affiliationCalculator.setDatabaseSession(recordSession);
		}

		stopTime = System.currentTimeMillis();
		final double elapsedTime = ((double) stopTime - startTime) / 1000;

		LOG.info(BATCH_DATA_SOURCE.toString() + " Batch: processed " + recordsProcessed + " records out of " + totalRecords + " in " + elapsedTime + " seconds");
	}

	/**
	 * Determine if a person has a current Hershey staff or member affiliation
	 * 
	 * @param hyStartDate the person's nullable Hershey start date
	 * @param hyEndDate the person's nullable Hershey end date
	 * @return A CPR affiliations type of MEMBER or STAFF depending on if the person is a current employee or not
	 */
	protected AffiliationsType mapHersheyAffiliation(DateTime hyStartDate, DateTime hyEndDate) {

		final AffiliationsType affiliation;
		
		String status = mapHersheyStatus(hyStartDate, hyEndDate);
		
		// for terminated employees, return member
		if (EmployeeBatchProcessor.TERMINATED_STATUS_CODE.equals(status)) {
			affiliation = AffiliationsType.MEMBER;
		} else {
			affiliation = AffiliationsType.STAFF;
		}

		return affiliation;
	}

	/**
	 * Map Hershey's active/terminated status onto the IBIS codes for employeeInfo, using the supplied Hershey HR start and end dates.
	 * 
	 * @param hyEmpStartDate a nullable Hershey employee start date
	 * @param hyEmpEndDate a nullable Hershey employee end date
	 * @return an IBIS batch processor status code enum for the employee status
	 */
	protected String mapHersheyStatus(DateTime hyEmpStartDate, DateTime hyEmpEndDate) {
		String retVal = EmployeeBatchProcessor.TERMINATED_STATUS_CODE;
		
		if(hyEmpStartDate != null && hyEmpStartDate.isBefore(now) && (hyEmpEndDate == null || hyEmpEndDate.isAfter(now))) {
			retVal = EmployeeBatchProcessor.ACTIVE_STATUS_CODE;
		}

		return retVal;
	}
	
	
	/**
	 * Get Hershey Start and End dates for this person
	 * 
	 * @param transHershey an instance of the TransHershey table bean
	 * @return a HashMap containing key-value pairs of the start date and end date of employment
	 */
	protected Map<String, DateTime> getHersheyStartEndDates(final TransHershey transHershey) {
		Map<String, DateTime> retVal = new HashMap<String, DateTime>();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(HMC_DATE_FORMAT);
		
		DateTime hyEmpStartDate = null;
		DateTime hyEmpEndDate = null;
		
		hyEmpStartDate = null;
		hyEmpEndDate = null;

		if(!ValidateHelper.isFieldEmpty(transHershey.getHrHireDate())){
			hyEmpStartDate = formatter.parseDateTime(transHershey.getHrHireDate());
		}
		
		if(!ValidateHelper.isFieldEmpty(transHershey.getHrTermDate())) {
			hyEmpEndDate = formatter.parseDateTime(transHershey.getHrTermDate());
		}
		
		retVal.clear();
		retVal.put(HY_START_DATE_KEY, hyEmpStartDate);
		retVal.put(HY_END_DATE_KEY, hyEmpEndDate);
		
		return retVal;
	}

	/**
	 * Helper method to convert primary/secondary appointment code type strings.
	 * 
	 * The HMC input file stores Primary as "P" and secondary as "S".
	 * Much of the rest of the code expects primary as "Y" and secondary as "N".
	 * 
	 * @param appointmentCodeType Should be a "P" or "S"
	 * @return "Y" if input is "P" otherwise "N".
	 */
	protected String mapPStoYN(final String appointmentCodeType) {
		// switch between transEmpl/transHershey's format (P = Primary; S = Secondary) to Employee's format (a yes/no field for primary appt)

		if (ValidateHelper.isFieldEmpty(appointmentCodeType)) {
			return "N";
		}

		final String primaryAppt;
		if ("P".equals(appointmentCodeType)) {
			primaryAppt = "Y";
		} else {
			primaryAppt ="N";
		}

		return primaryAppt;
	}

	/**
	 * Parse a String into a Date.
	 *
	 * @param dateString A textual representation of a date.
	 * @return A Date object or null if dateString is null.
	 * @throws ParseExcepion If dateString is misformatted.
	 */
	protected Date parseDateString(final String dateString) throws ParseException {

		if (ValidateHelper.isFieldEmpty(dateString)) {
			return null;
		}

		return HMC_DATE_PARSER.parse(dateString);
	}

	/**
	 * Update an employee in the CPR.
	 *
	 * @param transHershey A temporary employee record
	 * @param employeeInfo A reference to the employee info class
	 * @param changeNotification contains an instance of the change notification class.
	 * @throws CprException on any error
	 * @throws JMSException messaging error.
	 * @throws JSONException  json error.
	 */
	protected void updateEmployee(final TransHershey transHershey, final EmployeeInfo employeeInfo,
			final ChangeNotification changeNotification) throws CprException, JSONException, JMSException {

		changeNotification.setRequiredInfo(employeeInfo.getPersonId(),
				employeeInfo.getPsuIdNumber(),
				employeeInfo.getPrimaryUserid(),
				BATCH_DATA_SOURCE.toString());

		// update name - change notification will be done in the names post processor.
		employeeInfo.updateName(transHershey.getFirstName(), transHershey.getMiddleNames(),
				transHershey.getLastName(), transHershey.getSuffix());

		// update gender if not null.
		if (!ValidateHelper.isFieldEmpty(transHershey.getGender())) {
			try {
				final GenderType genderType = Utility.genderStringToType(transHershey.getGender());
				employeeInfo.updateGender(genderType);
				changeNotification.genderChange(employeeInfo.getOldPersonGender(), employeeInfo.getNewPersonGender());
			} catch (final IllegalArgumentException ex) { // $codepro.audit.disable logExceptions
				LOG.error("Invalid gender for record " + transHershey.getTransHersheyKey());
			}
		}

		// update date of birth if not null
		if (!ValidateHelper.isFieldEmpty(transHershey.getDateOfBirth())) {
			try {
				employeeInfo.updateDateOfBirth(transHershey.getDateOfBirth());
				changeNotification.dateOfBirthChange(employeeInfo.getOldDateOfBirth(), employeeInfo.getOldDateOfBirth());
			} catch (final ParseException ex) {
				throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid date of birth", ex);
			}
		}

		// use the updatePhone method here b/c it handles group id automatically
		employeeInfo.updatePhone(PhoneType.WORK_PHONE, transHershey.getWorkPhone(), transHershey.getWorkPhoneExt());
		changeNotification.phoneChange(employeeInfo.getOldPhone(), employeeInfo.getNewPhone());

		// update addresses
		employeeInfo.updateAddress(AddressType.PERMANENT_ADDRESS, transHershey.getHomeAddress1(), transHershey.getHomeAddress2(), null,
				transHershey.getHomeCity(), transHershey.getHomeState(), transHershey.getHomeZipcode(),
				transHershey.getHomeCountry(), null);

		/*		// don't process W4 addresses for now.
		 *		employeeInfo.updateAddress(AddressType.W4_PERMANENT_ADDRESS, transHershey.getHomeAddress1(), transHershey.getHomeAddress2(), null,
		 *				transHershey.getHomeCity(), transHershey.getHomeState(), transHershey.getHomeZipCode(),
		 *				transHershey.getHomeCountry(), null);
		 */

		// calculate the "mail stop + dept" and use for street1. use trans street1 as street2, and trans street2 as street 3.
		employeeInfo.updateAddress(AddressType.WORK_ADDRESS, calculateWorkAddressStreet1(transHershey), transHershey.getWorkAddress1(),
				transHershey.getWorkAddress2(), transHershey.getWorkCity(), transHershey.getWorkState(), transHershey.getWorkZipcode(), USA, null);

		Map<String, DateTime> hyStartEndDates = getHersheyStartEndDates(transHershey);

		// calculate affiliation
		final AffiliationsType hmcAffiliation = mapHersheyAffiliation(hyStartEndDates.get(HY_START_DATE_KEY), hyStartEndDates.get(HY_END_DATE_KEY));
		affiliationCalculator.setPersonId(employeeInfo.getPersonId());
		affiliationCalculator.setHMCAffiliation(hmcAffiliation.index());

		Date hyStartDate = null;
		Date hyEndDate = null;
		
		if(hyStartEndDates.get(HY_START_DATE_KEY) != null) {
			hyStartDate = hyStartEndDates.get(HY_START_DATE_KEY).toDate();
		}
		
		if(hyStartEndDates.get(HY_END_DATE_KEY) != null) {
			hyEndDate = hyStartEndDates.get(HY_END_DATE_KEY).toDate();
		}
		
		employeeInfo.updateEmployee(transHershey.getTitle(), null, hyStartDate, hyEndDate,
				null, transHershey.getApptTypeCode(), null, null,
				null, transHershey.getDeptName(), null, mapHersheyStatus(hyStartEndDates.get(HY_START_DATE_KEY), hyStartEndDates.get(HY_END_DATE_KEY)),
				null, null, null, null, null, null, null, null, null);
		changeNotification.employeeChange(employeeInfo.getOldEmployee(), employeeInfo.getNewEmployee());
		changeNotification.primaryAffiliationChange(affiliationCalculator.getOldPersonAffiliation(), affiliationCalculator.getNewPersonAffiliation());
	}
}