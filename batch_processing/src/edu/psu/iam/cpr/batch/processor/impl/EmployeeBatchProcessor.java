/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.batch.processor.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.EduPersonAffiliationCalculator;
import edu.psu.iam.cpr.core.database.batch.EmployeeInfo;
import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.beans.Phones;
import edu.psu.iam.cpr.core.database.beans.TransEmpl;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateHelper;
import edu.psu.iam.cpr.core.database.types.AddressType;
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
 * This class provides the concrete implementation for the IBIS (Employee) data batch processor.
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
public class EmployeeBatchProcessor extends GenericBatchProcessor {

	private static final String USA = "USA";
	private static final String IBIS_DATE_FORMAT = "yyyyMMdd";
	private static final SimpleDateFormat IBIS_DATE_PARSER = new SimpleDateFormat(IBIS_DATE_FORMAT);

	public static final String WAGE_APPT_CODE = "WAG";
	public static final String GRAD_APPT_CODE = "GRD";
	public static final String FT1_APPT_CODE = "FT1";
	public static final String FT2_APPT_CODE = "FT2";


	public static final String TERMINATED_STATUS_CODE = "TER";
	public static final String DECEASED_STATUS_CODE = "DEC";
	public static final String RETIRED_STATUS_CODE = "RET";
	public static final String ACTIVE_STATUS_CODE = "ACT";

	public static final String EMERITUS_SPECIAL_APPOINTMENT_CODE = "E";

	/** 126 days ago. This is the threshold date for last date paid and hired date. */
	private static final Date THRESHOLD_DATE;

	private static final int THRESHOLD_MONTHS = 4;

	/** Instance of logger */
	private static final Logger LOG = Logger.getLogger(EmployeeBatchProcessor.class);

	/** Contains the number of records to fetch for initial processing */
	private static final int RECORD_FETCH_SIZE = 50;

	/** Contains the name of the batch data source associated with this processor */
	private static final BatchDataSource BATCH_DATA_SOURCE = BatchDataSource.IBIS;

	/** Contains a reference to the EduPersonAffiliationCalculator */
	private EduPersonAffiliationCalculator affiliationCalculator;

	// bogus dates in the IBIS feed.
	public static final String BOGUS_DATE_1 = "20991231";
	public static final String BOGUS_DATE_2 = "00000000";
	public static final String BOGUS_DATE_3 = "99999999";

	// bogus class code in the IBIS feed
	private static final String BOGUS_CLASS_1 = "ZZZZ";

	static {
		final DateTime dt = new DateTime();
		THRESHOLD_DATE = dt.minusMonths(THRESHOLD_MONTHS).toDate();
	}

	/**
	 * Add a new employee to the CPR.
	 *
	 * @param transEmpl A temporary employee record
	 * @param employeeInfo A reference to the employee info class
	 * @param changeNotification contains an instance of the change notification class.
	 * @throws CprException on any error
	 * @throws JMSException
	 * @throws JSONException
	 */
	protected void addEmployee(final TransEmpl transEmpl, final EmployeeInfo employeeInfo,
			final ChangeNotification changeNotification) throws CprException, JSONException, JMSException {

		// make friendly variable names, since the trans table has unfriendly names
		final Long employeeKey = transEmpl.getTransEmplKey();
		final String psuId = transEmpl.getPsuId();

		final String campusAddress1 = transEmpl.getAddrCampusAddrLine1();
		final String campusCity = transEmpl.getAddrEmplOfficeCity();
		final String campusState = transEmpl.getAddrEmplOffcState();
		final String campusZip = transEmpl.getAddrEmplOffcZip();

		final String employeeSex = transEmpl.getCodeEmplSex();
		final String employeeDOBString = transEmpl.getDateEmplBirth();

		final String legalFirstName = transEmpl.getNameEmplFirstLegal();
		final String legalMiddleNames = transEmpl.getNameEmplMidLegal();
		final String legalLastName = transEmpl.getNameEmplLastLegal();
		final String legalNameSuffix = transEmpl.getNameEmplSfxLegal();

		final String employeeOfficePhone = transEmpl.getNumbEmplPhoneOffc();

		final String homeStreetAddress1 = transEmpl.getAddrEmplStreetHome();
		final String homeStreetAddress2 = transEmpl.getAddrEmplSt2Home();
		final String homeAddressCity = transEmpl.getAddrEmplCityHome();
		final String homeAddressState = transEmpl.getAddrEmplStateHome();
		final String homeAddressZipPrime = transEmpl.getAddrEmplZipHomePrime();
		final String homeAddressCountry = transEmpl.getCodeEmplCountryHome();
		final String homePhone = transEmpl.getNumbEmplPhoneHome();

		String statusCode = transEmpl.getCodeEmplStatus();
		final String campusCode = transEmpl.getCodeCampus();
		final String appointmentCode = transEmpl.getCodeAppt();
		final String adminAreaCode = transEmpl.getCodeAdminArea();
		final String employeeClass = transEmpl.getCodeEmplClass();
		final String studentStatus = transEmpl.getStudentStatus();
		final String directPhoneCode = transEmpl.getCodeEmplDirectPhone();
		final String benefitsRateCode = transEmpl.getCodeEmplBnftsRate();
		final String layoffCode = transEmpl.getCodeEmplLayoff();
		final String visaType = transEmpl.getCodeEmplFnatVisaType();
		final String alternateJobTitle = transEmpl.getAltJobTitle();
		final String shortJobDescription = transEmpl.getNameJobdTitleShort();
		final String specialAppointmentCode = transEmpl.getCodeEmplApptSpecial();
		final String adminArea = transEmpl.getNameEmplAdminArea();
		final String departmentName = transEmpl.getNameEmplDepartment();
		final String payFreqCode = transEmpl.getCodeEmplPayCalc();
		final String appointmentCodeType = transEmpl.getCodeApptType();

		employeeInfo.addPerson();
		employeeInfo.addPsuId(psuId);

		// notification is done in the names post-processor.
		employeeInfo.addName(legalFirstName, legalMiddleNames, legalLastName, legalNameSuffix);

		// add gender if not null.
		if (!ValidateHelper.isFieldEmpty(employeeSex)) {
			try {
				final GenderType genderType = Utility.genderStringToType(employeeSex);
				employeeInfo.addGender(genderType);
			} catch (final IllegalArgumentException ex) { // $codepro.audit.disable logExceptions
				LOG.error("Invalid gender for record " + employeeKey, ex);
			}
		}

		// add date of birth if not null
		if (!ValidateHelper.isFieldEmpty(employeeDOBString)) {
			try {
				employeeInfo.addDateOfBirth(parseDateString(employeeDOBString));
			} catch (final ParseException ex) {
				throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid date of birth", ex);
			}
		}

		// use the updatePhone method here b/c it handles group id automatically
		employeeInfo.updatePhone(PhoneType.WORK_PHONE, employeeOfficePhone, null);
		final Phones newPhone = employeeInfo.getNewPhone();

		employeeInfo.updatePhone(PhoneType.PERMANENT_PHONE, homePhone, null);

		Addresses newAddress = null;

		// use the updateAddress method here b/c it handles group id automatically
		employeeInfo.updateAddress(AddressType.PERMANENT_ADDRESS, homeStreetAddress1, homeStreetAddress2, null,
				homeAddressCity, homeAddressState, homeAddressZipPrime,	homeAddressCountry, null);

		/*		// don't process W4 addresses for now.
		 * 		employeeInfo.updateAddress(AddressType.W4_PERMANENT_ADDRESS, transEmpl.getAddrEmplStreetHome(), transEmpl.getAddrEmplSt2Home(), null,
		 *				transEmpl.getAddrEmplCityHome(), transEmpl.getAddrEmplStateHome(), transEmpl.getAddrEmplZipHomePrime(),
		 *				transEmpl.getCodeEmplCountryHome(), null);
		 */

		// pass address line 2 which has the campus code as the city -- we need to do this for match codes
		employeeInfo.updateAddress(AddressType.WORK_ADDRESS, campusAddress1, null, null, campusCity, campusState, campusZip, USA, null);
		newAddress = employeeInfo.getNewAddress();

		final Date hiredDate;
		try {
			hiredDate = parseDateString(transEmpl.getDateEmplHired());
		} catch (final ParseException ex) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid hire date", ex);
		}

		final Date terminatedDate;
		try {
			terminatedDate = parseDateString(transEmpl.getDateEmplTermn());
		} catch (final ParseException ex) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid termination date", ex);
		}

		final Date lastPaidDate;
		try {
			lastPaidDate = parseDateString(transEmpl.getDateEmplPaid());
		} catch (final ParseException ex) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid last paid date", ex);
		}

		// add the employee-specific data
		employeeInfo.addEmployee(shortJobDescription, alternateJobTitle, hiredDate, terminatedDate,	lastPaidDate,
				appointmentCode, null, adminArea, adminAreaCode, departmentName, campusCode, statusCode,
				employeeClass, layoffCode, benefitsRateCode, visaType, studentStatus, specialAppointmentCode, directPhoneCode,
				payFreqCode);

		// calculate affiliations
		if ("P".equals(appointmentCodeType)) {
			if (! isEmployeeActive(lastPaidDate, hiredDate, employeeInfo.showEmployeeInDirectory(directPhoneCode), appointmentCode, statusCode,
					specialAppointmentCode, employeeClass)) {
				statusCode = TERMINATED_STATUS_CODE;
			}
			affiliationCalculator.setPersonId(employeeInfo.getPersonId());
			affiliationCalculator.setEmployeeAffiliation(employeeClass, statusCode, specialAppointmentCode);
		}

		changeNotification.setRequiredInfo(employeeInfo.getPersonId(), employeeInfo.getPsuIdNumber(), null, BATCH_DATA_SOURCE.toString());
		changeNotification.newEmployee(employeeInfo.getNewName(),
				newAddress,
				newPhone,
				null, 	// email address.
				affiliationCalculator.getNewPersonAffiliation(), 	// affiliation
				employeeInfo.getNewPersonGender(),
				null, 	// confidentiality
				employeeInfo.getNewEmployee());

	}

	/**
	 * Remove bogus data from the employee temp data.
	 * The transEmpl object is modified in-place.
	 *
	 * @param transEmpl The transEmpl object to examine.
	 */
	protected void fixBogusData(final TransEmpl transEmpl) {

		if (BOGUS_DATE_1.equals(transEmpl.getDateEmplHired()) || BOGUS_DATE_2.equals(transEmpl.getDateEmplHired()) || BOGUS_DATE_3.equals(transEmpl.getDateEmplHired())) {
			transEmpl.setDateEmplHired(null);
		}

		if (BOGUS_DATE_1.equals(transEmpl.getDateEmplPaid()) || BOGUS_DATE_2.equals(transEmpl.getDateEmplPaid()) || BOGUS_DATE_3.equals(transEmpl.getDateEmplPaid())) {
			transEmpl.setDateEmplPaid(null);
		}

		if (BOGUS_DATE_1.equals(transEmpl.getDateEmplBirth()) || BOGUS_DATE_2.equals(transEmpl.getDateEmplBirth()) || BOGUS_DATE_3.equals(transEmpl.getDateEmplBirth())) {
			transEmpl.setDateEmplBirth(null);
		}

		if (BOGUS_DATE_1.equals(transEmpl.getDateEmplTermn()) || BOGUS_DATE_2.equals(transEmpl.getDateEmplTermn()) || BOGUS_DATE_3.equals(transEmpl.getDateEmplTermn())) {
			transEmpl.setDateEmplTermn(null);
		}

		if (BOGUS_CLASS_1.equals(transEmpl.getCodeEmplClass())) {
			transEmpl.setCodeEmplClass("");
		}

		// if we have zipcode+4, concat them into a single field
		if (transEmpl.getAddrEmplZipHomeSecond() != null && !transEmpl.getAddrEmplZipHomeSecond().equals("")) {
			transEmpl.setAddrEmplZipHomePrime(transEmpl.getAddrEmplZipHomePrime() + "-" + transEmpl.getAddrEmplZipHomeSecond());
		}

		// for University Park employees, the OHR dump doesn't set addr_campus_addr_line2 to "University Park."
		// So we have to set it. In all caps, of course.
		if (ValidateHelper.isFieldEmpty(transEmpl.getAddrCampusAddrLine2())) {
			transEmpl.setAddrCampusAddrLine2("UNIVERSITY PARK");
		}
	}

	@Override
	public void implementBatchProcessor(final StatelessSession databaseSession,
			final MessagingCore messagingCore, final DataQualityService dataQualityService) throws CprException, JSONException, JMSException {

		final long startTime, stopTime;
		final long totalRecords;
		long recordsProcessed = 0;

		startTime = System.currentTimeMillis();

		// Perform a query for all of the trans empl records.
		final Query query = databaseSession.createQuery("FROM TransEmpl ORDER BY codeApptType ASC");
		query.setFetchSize(RECORD_FETCH_SIZE);

		StatelessSession recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		final EmployeeInfo employeeInfo = new EmployeeInfo(recordSession, BATCH_DATA_SOURCE, dataQualityService);
		final ChangeNotification changeNotification = new ChangeNotification(recordSession, messagingCore);
		affiliationCalculator = new EduPersonAffiliationCalculator(recordSession, BATCH_DATA_SOURCE, dataQualityService);

		Transaction tx = null;

		final List<?> queryList = query.list();
		totalRecords = queryList.size();

		// Loop for all of the records that were found.
		for (final Iterator<?> it = queryList.iterator(); it.hasNext(); ) {
			final TransEmpl transEmpl = (TransEmpl) it.next();
			recordsProcessed++;

			fixBogusData(transEmpl);

			try {
				// Begin transaction.
				tx = recordSession.beginTransaction();

				employeeInfo.resetHistoryBeans();
				affiliationCalculator.resetHistoryBeans();

				employeeInfo.setRecordNumber(transEmpl.getTransEmplKey().longValue());
				employeeInfo.findPerson(transEmpl.getPsuId());

				final Long personId = employeeInfo.getPersonId();
				if (personId == null) {
					addEmployee(transEmpl, employeeInfo, changeNotification);
				} else {
					updateEmployee(transEmpl, employeeInfo, changeNotification);
				}

				// Commit!
				tx.commit();
			} catch (final HibernateException ex) {

				// Log the error.
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transEmpl.getTransEmplKey(), ex);

				// Rollback the transaction, close the session.
				tx.rollback();
				recordSession.close();

				// We need to create a new session and update the person bio with the new session.
				recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
				employeeInfo.setDatabaseSession(recordSession);
				changeNotification.setStatelessSession(recordSession);
				affiliationCalculator.setDatabaseSession(recordSession);
			} catch (final CprException ex) {

				// Log the error.
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transEmpl.getTransEmplKey(), ex);
				throw ex;
			}
		}

		try {
			recordSession.close();
		} catch (final HibernateException e) {

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
	 * Determine if a record is for an active employee.
	 * This logic was adapted from the CACTUS stored procedure.
	 * Algorithm here: https://wikispaces.psu.edu/display/IAM/IAM+Non+Student+Digital+Life+Cycle (section 2.1)
	 *
	 * @param lastPaidDate The last pay day
	 * @param hiredDate The hire data
	 * @param showInDirectory Is the employee displayed in the directory
	 * @param appointmentCode appointment code
	 * @param statusCode status code
	 * @param specialAppointmentCode special appointment code
	 * @param employeeClass employee class
	 */
	public boolean isEmployeeActive(final Date lastPaidDate, final Date hiredDate, boolean showInDirectory,
			final String appointmentCode, final String statusCode, final String specialAppointmentCode, final String employeeClass) {

		/** has the employee been paid in the past 126 days */
		final boolean paid126;

		/** has the employee been hired in the past 126 days */
		final boolean hired126;

		if ((! WAGE_APPT_CODE.equals(appointmentCode)) && 
			(! GRAD_APPT_CODE.equals(appointmentCode))) {
			showInDirectory = true;
		}

		if (GRAD_APPT_CODE.equals(appointmentCode) && showInDirectory) {
			showInDirectory = false;
		}

		if (lastPaidDate != null && lastPaidDate.compareTo(THRESHOLD_DATE) > 0) {
			paid126 = true;
		} else {
			paid126 = false;
		}

		if (hiredDate != null && hiredDate.compareTo(THRESHOLD_DATE) > 0) {
			hired126 = true;
		} else {
			hired126 = false;
		}

		final boolean isActiveEmployee;
		if (showInDirectory && 
				(! TERMINATED_STATUS_CODE.equals(statusCode) && ! DECEASED_STATUS_CODE.equals(statusCode))
				|| EMERITUS_SPECIAL_APPOINTMENT_CODE.equals(specialAppointmentCode)) {
			if (ACTIVE_STATUS_CODE.equals(statusCode) && (! paid126) && (! hired126)
					&& (FT2_APPT_CODE.equals(appointmentCode) || WAGE_APPT_CODE.equals(appointmentCode))) {
				isActiveEmployee = false;
			} else {
				isActiveEmployee = true;
			}
		} else {
			isActiveEmployee = false;
		}
		
		return isActiveEmployee;
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

		return IBIS_DATE_PARSER.parse(dateString);
	}

	/**
	 * Update an employee in the CPR.
	 *
	 * @param transEmpl A temporary employee record
	 * @param employeeInfo A reference to the employee info class
	 * @param changeNotification contains an instance of the change notification class.
	 * @throws CprException on any error
	 * @throws JMSException messaging error.
	 * @throws JSONException  json error.
	 */
	protected void updateEmployee(final TransEmpl transEmpl, final EmployeeInfo employeeInfo,
			final ChangeNotification changeNotification) throws CprException, JSONException, JMSException {

		transEmpl.getTransEmplKey();
		transEmpl.getPsuId();

		final String campusAddress1 = transEmpl.getAddrCampusAddrLine1();
		final String campusCity = transEmpl.getAddrEmplOfficeCity();
		final String campusState = transEmpl.getAddrEmplOffcState();
		final String campusZip = transEmpl.getAddrEmplOffcZip();

		final String employeeSex = transEmpl.getCodeEmplSex();
		final String employeeDOBString = transEmpl.getDateEmplBirth();

		final String legalFirstName = transEmpl.getNameEmplFirstLegal();
		final String legalMiddleNames = transEmpl.getNameEmplMidLegal();
		final String legalLastName = transEmpl.getNameEmplLastLegal();
		final String legalNameSuffix = transEmpl.getNameEmplSfxLegal();

		final String officePhone = transEmpl.getNumbEmplPhoneOffc();

		final String homeStreetAddress1 = transEmpl.getAddrEmplStreetHome();
		final String homeStreetAddress2 = transEmpl.getAddrEmplSt2Home();
		final String homeAddressCity = transEmpl.getAddrEmplCityHome();
		final String homeAddressState = transEmpl.getAddrEmplStateHome();
		final String homeAddressZipPrime = transEmpl.getAddrEmplZipHomePrime();
		final String homeAddressCountry = transEmpl.getCodeEmplCountryHome();
		final String homePhone = transEmpl.getNumbEmplPhoneHome();

		String statusCode = transEmpl.getCodeEmplStatus();	// not final b/c we might need to change it for affiliation calculator.
		final String campusCode = transEmpl.getCodeCampus();
		final String appointmentCode = transEmpl.getCodeAppt();
		final String appointmentCodeType = transEmpl.getCodeApptType();
		final String adminAreaCode = transEmpl.getCodeAdminArea();
		final String employeeClass = transEmpl.getCodeEmplClass();
		final String studentStatus = transEmpl.getStudentStatus();
		final String directPhoneCode = transEmpl.getCodeEmplDirectPhone();
		final String benefitsRateCode = transEmpl.getCodeEmplBnftsRate();
		final String layoffCode = transEmpl.getCodeEmplLayoff();
		final String visaType = transEmpl.getCodeEmplFnatVisaType();
		final String alternateJobTitle = transEmpl.getAltJobTitle();
		final String shortJobDescription = transEmpl.getNameJobdTitleShort();
		final String specialAppointmentCode = transEmpl.getCodeEmplApptSpecial();
		final String adminArea = transEmpl.getNameEmplAdminArea();
		final String departmentName = transEmpl.getNameEmplDepartment();
		final String payFreqCode = transEmpl.getCodeEmplPayCalc();

		changeNotification.setRequiredInfo(employeeInfo.getPersonId(),
				employeeInfo.getPsuIdNumber(),
				employeeInfo.getPrimaryUserid(),
				BATCH_DATA_SOURCE.toString());

		// update name - change notification will be done in the names post processor.
		employeeInfo.updateName(legalFirstName, legalMiddleNames, legalLastName, legalNameSuffix);

		// update gender if not null.
		if (!ValidateHelper.isFieldEmpty(employeeSex)) {
			try {
				final GenderType genderType = Utility.genderStringToType(employeeSex);
				employeeInfo.updateGender(genderType);
				changeNotification.genderChange(employeeInfo.getOldPersonGender(), employeeInfo.getNewPersonGender());
			} catch (final IllegalArgumentException ex) { // $codepro.audit.disable logExceptions
				LOG.warn("Invalid gender for record " + transEmpl.getTransEmplKey(), ex);
			}
		}

		// update date of birth if not null
		if (!ValidateHelper.isFieldEmpty(employeeDOBString)) {
			try {
				employeeInfo.updateDateOfBirth(parseDateString(employeeDOBString));
				changeNotification.dateOfBirthChange(employeeInfo.getOldDateOfBirth(), employeeInfo.getNewDateOfBirth());
			} catch (final ParseException ex) {
				throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalidate birth date", ex);
			}
		}

		// use the updatePhone method here b/c it handles group id automatically
		employeeInfo.updatePhone(PhoneType.WORK_PHONE, officePhone, null);
		changeNotification.phoneChange(employeeInfo.getOldPhone(), employeeInfo.getNewPhone());

		employeeInfo.updatePhone(PhoneType.PERMANENT_PHONE, homePhone, null);

		// update addresses
		employeeInfo.updateAddress(AddressType.PERMANENT_ADDRESS, homeStreetAddress1, homeStreetAddress2, null,
				homeAddressCity, homeAddressState, homeAddressZipPrime, homeAddressCountry, null);


		/*		// don't process W4 addresses for now
		 * 		employeeInfo.updateAddress(AddressType.W4_PERMANENT_ADDRESS, transEmpl.getAddrEmplStreetHome(), transEmpl.getAddrEmplSt2Home(), null,
		 *				transEmpl.getAddrEmplCityHome(), transEmpl.getAddrEmplStateHome(), transEmpl.getAddrEmplZipHomePrime(),
		 *				transEmpl.getCodeEmplCountryHome(), null);
		 */
		employeeInfo.updateAddress(AddressType.WORK_ADDRESS, campusAddress1, null, null, campusCity, campusState, campusZip, USA, null);
		changeNotification.addressChange(employeeInfo.getOldAddress(), employeeInfo.getNewAddress());

		final Date hiredDate;
		try {
			hiredDate = parseDateString(transEmpl.getDateEmplHired());
		} catch (final ParseException ex) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid hire date", ex);
		}

		final Date terminatedDate;
		try {
			terminatedDate = parseDateString(transEmpl.getDateEmplTermn());
		} catch (final ParseException ex) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid termination date", ex);
		}

		final Date lastPaidDate;
		try {
			lastPaidDate = parseDateString(transEmpl.getDateEmplPaid());
		} catch (final ParseException ex) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid last paid date", ex);
		}

		// calculate affiliations
		if ("P".equals(appointmentCodeType)) {
			if (!isEmployeeActive(lastPaidDate, hiredDate, employeeInfo.showEmployeeInDirectory(directPhoneCode), appointmentCode, statusCode,
					specialAppointmentCode, employeeClass)) {
				statusCode = TERMINATED_STATUS_CODE;
			}
			affiliationCalculator.setPersonId(employeeInfo.getPersonId());
			affiliationCalculator.setEmployeeAffiliation(employeeClass, statusCode, specialAppointmentCode);
		}

		employeeInfo.updateEmployee(shortJobDescription, alternateJobTitle, hiredDate, terminatedDate,
				lastPaidDate, appointmentCode, appointmentCodeType, adminArea, adminAreaCode,
				departmentName, campusCode, statusCode, employeeClass,
				layoffCode, benefitsRateCode, visaType, studentStatus,
				specialAppointmentCode, directPhoneCode, null, payFreqCode);
		changeNotification.employeeChange(employeeInfo.getOldEmployee(), employeeInfo.getNewEmployee());
	}
}
