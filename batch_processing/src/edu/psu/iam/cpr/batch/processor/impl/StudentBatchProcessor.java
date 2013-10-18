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
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.EduPersonAffiliationCalculator;
import edu.psu.iam.cpr.core.database.batch.StudentInfo;
import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.beans.Phones;
import edu.psu.iam.cpr.core.database.beans.Semesters;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicCollege;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicDepartment;
import edu.psu.iam.cpr.core.database.beans.StudentMajor;
import edu.psu.iam.cpr.core.database.beans.TransStubio;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateHelper;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.ChangeNotification;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.DataQualityService;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides the concrete implementation for the ISIS (Student data) batch processor.
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
public class StudentBatchProcessor extends GenericBatchProcessor {

	private static final String ISIS_DATE_FORMAT = "yyyyMMdd";
	private static final SimpleDateFormat ISIS_DATE_PARSER = new SimpleDateFormat(ISIS_DATE_FORMAT);

	/** Instance of logger */
	private static final Logger LOG = Logger.getLogger(StudentBatchProcessor.class);

	/** Contains the number of records to fetch for initial processing */
	private static final int RECORD_FETCH_SIZE = 50;

	/** Contains the name of the database table for the source of records */
	private static final String TRANS_DATABASE_TABLE = "TransStubio";

	/** Contains the name of the batch data source associated with this processor */
	private static final BatchDataSource BATCH_DATA_SOURCE = BatchDataSource.ISIS;

	/** Contains a long one */
	private static final Long LONG_ONE = 1L;

	/** Contains a long two */
	private static final Long LONG_TWO = 2L;

	/** Contains a long three */
	private static final Long LONG_THREE = 3L;

	/** Contains a long four */
	private static final Long LONG_FOUR = 4L;

	/** now constant */
	private static final String NOW = "now";

	/**
	 * This method provides the implementation of the student processor.
	 * @param databaseSession contains an instance of the database session.
	 * @param messagingCore contains the messaging core instance.
	 * @param dataQualityService contains a reference to the data quality service.
	 * @throws CprException will be thrown for any Cpr Related problems.
	 * @throws JSONException will be thrown for any JSON problems.
	 * @throws JMSException will be thrown for any messaging problems.
	 */
	@Override
	public void implementBatchProcessor(final StatelessSession databaseSession,
			final MessagingCore messagingCore, final DataQualityService dataQualityService) throws CprException, ParseException, JSONException, JMSException {

		final long startTime, stopTime;
		final long totalRecords;
		long recordsProcessed = 0;

		startTime = System.currentTimeMillis();

		String currentSemester = null;
		// Get the current semester
		final Date d = new Date();
		Query query = databaseSession.createQuery("from Semesters where :now >= semStartDate AND :now < semEndDate");
		query.setParameter(NOW, d);

		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final Semesters bean = (Semesters) it.next();
			currentSemester = bean.getSemesterCode();
		}

		// Perform a query for all of the trans stubio records.
		query = databaseSession.createQuery("from " + TRANS_DATABASE_TABLE + " order by sem asc, codeStudStat desc");
		query.setFetchSize(RECORD_FETCH_SIZE);

		StatelessSession recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		final StudentInfo studentInfo = new StudentInfo(recordSession, BATCH_DATA_SOURCE, dataQualityService);
		final EduPersonAffiliationCalculator eduPersonAffiliationCalculator = new EduPersonAffiliationCalculator(recordSession, BATCH_DATA_SOURCE, dataQualityService);
		final ChangeNotification changeNotification = new ChangeNotification(recordSession, messagingCore);
		Transaction tx = null;

		final List<?> queryList = query.list();
		totalRecords = queryList.size();

		// Loop for all of the records that were found.
		for (final Iterator<?> it = queryList.iterator(); it.hasNext(); ) {
			final TransStubio transStubio = (TransStubio) it.next();
			recordsProcessed++;
			try {
				final String semesterCode = transStubio.getSem();
				// Begin transaction.
				tx = recordSession.beginTransaction();

				studentInfo.resetHistoryBeans();

				Long personId = studentInfo.findPerson(transStubio.getPsuId());
				if (personId == null && transStubio.getPsuIdPrev() != null) {
					personId = studentInfo.findPerson(transStubio.getPsuIdPrev());

					// Update PSU ID
					if (personId != null) {
						studentInfo.updatePsuId(transStubio.getPsuId());
					}
				}

				// User was not found, so we need to add them.
				if (personId == null) {
					
					// Create a new person
					studentInfo.addPerson();
					
					// Add their PSU ID
					studentInfo.addPsuId(transStubio.getPsuId());

					// Add Date of birth
					studentInfo.addDateOfBirth(parseDateString(transStubio.getDatePersBirth()));

					// Add Gender if not null
					if (!ValidateHelper.isFieldEmpty(transStubio.getCodePersSex())) {
						GenderType genderType;
						try {
							genderType = Utility.genderStringToType(transStubio.getCodePersSex());
							studentInfo.addGender(genderType);
						} catch (final IllegalArgumentException e) {
							LOG.error("Invalid gender for record " + transStubio.getTransStubioKey());
						}

					}

					// Update phones
					studentInfo.updatePhone(PhoneType.LOCAL_PHONE, transStubio.getPhoneLocal(), null);
					final Phones newPhone = studentInfo.getNewPhone();
					studentInfo.updatePhone(PhoneType.PERMANENT_PHONE, transStubio.getPhoneHome(), null);

					// Add name
					studentInfo.addName(transStubio.getNamePersFirst(), transStubio.getNamePersMid(),
							transStubio.getNamePersLast(), transStubio.getNamePersSfx());

					// Update addresses
					studentInfo.updateAddress(AddressType.LOCAL_ADDRESS, transStubio.getAddrLoclSt1(),
							transStubio.getAddrLoclSt2(), transStubio.getAddrLoclSt3(),
							transStubio.getAddrLoclCity(), transStubio.getAddrLoclState(),
							transStubio.getAddrLoclZip(), transStubio.getAddrLoclCtry(),
							transStubio.getCodeCamp());
					final Addresses newAddress = studentInfo.getNewAddress();

					studentInfo.updateAddress(AddressType.PERMANENT_ADDRESS, transStubio.getAddrHomeSt1(),
							transStubio.getAddrHomeSt2(), transStubio.getAddrHomeSt3(),
							transStubio.getAddrHomeCity(), transStubio.getAddrHomeState(),
							transStubio.getAddrHomeZip(), transStubio.getAddrHomeCtry(),
							null);

					// Add the student record
					if (transStubio.getCodeStudStat() != null && transStubio.getCodeStudLvl() != null) {
						studentInfo.addStudent(semesterCode, 
								transStubio.getCodeCamp(), 
								transStubio.getCodeStudStat(), 
								transStubio.getCodeStudLvl(),
								transStubio.getCodeStudClsfctnYrtm(), 
								transStubio.getIndcStudGraduation(),
								transStubio.getLoaStart(), 
								transStubio.getLoaReturnSt1(),
								transStubio.getCodeHotl(), 
								transStubio.getClassLoad(), 
								transStubio.getStudentAid());
						
						// Add the student's academic colleges
						studentInfo.addStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl1(), LONG_ONE);
						final StudentAcademicCollege newStudentAcademicCollege = studentInfo.getNewStudentAcademicCollege();
						studentInfo.addStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl2(), LONG_TWO);
						studentInfo.addStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl3(), LONG_THREE);
						studentInfo.addStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl4(), LONG_FOUR);

						// Update the student's academic departments
						studentInfo.addStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt1(), LONG_ONE);
						final StudentAcademicDepartment newStudentAcademicDepartment = studentInfo.getNewStudentAcademicDepartment();
						studentInfo.addStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt2(), LONG_TWO);
						studentInfo.addStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt3(), LONG_THREE);
						studentInfo.addStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt4(), LONG_FOUR);

						// Update the student's majors
						studentInfo.addStudentMajor(semesterCode, transStubio.getCodeStudMajr1(), LONG_ONE);
						final StudentMajor newStudentMajor = studentInfo.getNewStudentMajor();
						studentInfo.addStudentMajor(semesterCode, transStubio.getCodeStudMajr2(), LONG_TWO);
						studentInfo.addStudentMajor(semesterCode, transStubio.getCodeStudMajr3(), LONG_THREE);
						studentInfo.addStudentMajor(semesterCode, transStubio.getCodeStudMajr4(), LONG_FOUR);

						changeNotification.setRequiredInfo(studentInfo.getPersonId(), studentInfo.getPsuIdNumber(), null, BATCH_DATA_SOURCE.toString());
						if (Utility.areStringFieldsEqual(currentSemester, semesterCode)) {
							eduPersonAffiliationCalculator.setPersonId(studentInfo.getPersonId());
							eduPersonAffiliationCalculator.setStudentAffiliation(transStubio.getCodeStudStat());
							changeNotification.primaryAffiliationChange(eduPersonAffiliationCalculator.getOldPersonAffiliation(),
									eduPersonAffiliationCalculator.getNewPersonAffiliation());
						}
						changeNotification.newStudent(studentInfo.getNewName(),
								newAddress,
								newPhone,
								null,	// email address.
								eduPersonAffiliationCalculator.getNewPersonAffiliation(), 	// affiliation.
								studentInfo.getNewPersonGender(),
								null, 	// confidentiality
								studentInfo.getNewStudent(),
								newStudentAcademicCollege,
								newStudentAcademicDepartment,
								newStudentMajor);
					}
					else {
						LOG.error("Skipping student record: " + transStubio.getTransStubioKey() + " due to a lack of data.");
					}

				}

				// User was found.
				else {
					
					changeNotification.setRequiredInfo(studentInfo.getPersonId(), studentInfo.getPsuIdNumber(),
							studentInfo.getPrimaryUserid(),
							BATCH_DATA_SOURCE.toString());

					// Update name - change name for name will be done in the names post processor.
					studentInfo.updateName(transStubio.getNamePersFirst(), transStubio.getNamePersMid(),
							transStubio.getNamePersLast(), transStubio.getNamePersSfx());

					// Update addresses
					studentInfo.updateAddress(AddressType.LOCAL_ADDRESS, transStubio.getAddrLoclSt1(),
							transStubio.getAddrLoclSt2(), transStubio.getAddrLoclSt3(),
							transStubio.getAddrLoclCity(), transStubio.getAddrLoclState(),
							transStubio.getAddrLoclZip(), transStubio.getAddrLoclCtry(),
							transStubio.getCodeCamp());
					changeNotification.addressChange(studentInfo.getOldAddress(), studentInfo.getNewAddress());

					studentInfo.updateAddress(AddressType.PERMANENT_ADDRESS, transStubio.getAddrHomeSt1(),
							transStubio.getAddrHomeSt2(), transStubio.getAddrHomeSt3(),
							transStubio.getAddrHomeCity(), transStubio.getAddrHomeState(),
							transStubio.getAddrHomeZip(), transStubio.getAddrHomeCtry(),
							null);


					// Update Date of birth
					studentInfo.updateDateOfBirth(parseDateString(transStubio.getDatePersBirth()));
					changeNotification.dateOfBirthChange(studentInfo.getOldDateOfBirth(), studentInfo.getNewDateOfBirth());
					//TODO Add change notifications for date of birth.

					// Update Gender if not null
					if (!ValidateHelper.isFieldEmpty(transStubio.getCodePersSex())) {
						try {
							final GenderType genderType = Utility.genderStringToType(transStubio.getCodePersSex());
							studentInfo.updateGender(genderType);
							changeNotification.genderChange(studentInfo.getOldPersonGender(), studentInfo.getNewPersonGender());
						} catch (final IllegalArgumentException e) {
							LOG.error("Invalid gender for record " + transStubio.getTransStubioKey());
						}
					}

					// Update phones
					studentInfo.updatePhone(PhoneType.LOCAL_PHONE, transStubio.getPhoneLocal(), null);
					changeNotification.phoneChange(studentInfo.getOldPhone(), studentInfo.getNewPhone());

					studentInfo.updatePhone(PhoneType.PERMANENT_PHONE, transStubio.getPhoneHome(), null);

					boolean processRecord = true;
					
					// If the student status and academic level are null, we need to do an additional check.
					if (transStubio.getCodeStudStat() == null && transStubio.getCodeStudLvl() == null) {
						
						// Pull the number of records for the current user and their semester code.
						final String sqlQuery = "from TransStubio where psuId = :psu_id AND sem = :sem";
						final Query countQuery = recordSession.createQuery(sqlQuery);
						countQuery.setParameter("psu_id", transStubio.getPsuId());
						countQuery.setParameter("sem", transStubio.getSem());

						// If there are more than one record, we need to skip this one because it contains null information.  Based
						// on analysis there will be another record in the file that will contain real data.  This null record must
						// be skipping.
						if (countQuery.list().size() > 1) {
							processRecord = false;
						}
					}					
					
					// Check to see if we are going to process the record...
					if (processRecord) {
						studentInfo.updateStudent(semesterCode, 
								transStubio.getCodeCamp(), 
								transStubio.getCodeStudStat(), 
								transStubio.getCodeStudLvl(),
								transStubio.getCodeStudClsfctnYrtm(), 
								transStubio.getIndcStudGraduation(),
								transStubio.getLoaStart(), 
								transStubio.getLoaReturnSt1(),
								transStubio.getCodeHotl(), 
								transStubio.getClassLoad(), 
								transStubio.getStudentAid());

						// Update the student's academic colleges
						studentInfo.updateStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl1(), LONG_ONE);
						final StudentAcademicCollege oldStudentAcademicCollege = studentInfo.getOldStudentAcademicCollege();
						final StudentAcademicCollege newStudentAcademicCollege = studentInfo.getNewStudentAcademicCollege();
						studentInfo.updateStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl2(), LONG_TWO);
						studentInfo.updateStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl3(), LONG_THREE);
						studentInfo.updateStudentAcademicCollege(semesterCode, transStubio.getCodeStudColl4(), LONG_FOUR);

						// Update the student's academic departments
						studentInfo.updateStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt1(), LONG_ONE);
						final StudentAcademicDepartment oldStudentAcademicDepartment = studentInfo.getOldStudentAcademicDepartment();
						final StudentAcademicDepartment newStudentAcademicDepartment = studentInfo.getNewStudentAcademicDepartment();
						studentInfo.updateStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt2(), LONG_TWO);
						studentInfo.updateStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt3(), LONG_THREE);
						studentInfo.updateStudentAcademicDepartment(semesterCode, transStubio.getCodeStudAcdt4(), LONG_FOUR);

						// Update the student's majors
						studentInfo.updateStudentMajor(semesterCode, transStubio.getCodeStudMajr1(), LONG_ONE);
						final StudentMajor oldStudentMajor = studentInfo.getOldStudentMajor();
						final StudentMajor newStudentMajor = studentInfo.getNewStudentMajor();
						studentInfo.updateStudentMajor(semesterCode, transStubio.getCodeStudMajr2(), LONG_TWO);
						studentInfo.updateStudentMajor(semesterCode, transStubio.getCodeStudMajr3(), LONG_THREE);
						studentInfo.updateStudentMajor(semesterCode, transStubio.getCodeStudMajr4(), LONG_FOUR);

						// Change notification for student data.
						changeNotification.studentChange(studentInfo.getOldStudent(),
								oldStudentAcademicCollege,
								oldStudentAcademicDepartment,
								oldStudentMajor,
								studentInfo.getNewStudent(),
								newStudentAcademicCollege,
								newStudentAcademicDepartment,
								newStudentMajor);

						// If we are looking at the current semester
						// process the Affiliation Change.
						if (Utility.areStringFieldsEqual(currentSemester, semesterCode)) {
							eduPersonAffiliationCalculator.setPersonId(studentInfo.getPersonId());
							eduPersonAffiliationCalculator.setStudentAffiliation(transStubio.getCodeStudStat());
							changeNotification.primaryAffiliationChange(eduPersonAffiliationCalculator.getOldPersonAffiliation(),
									eduPersonAffiliationCalculator.getNewPersonAffiliation());
						}

					}
					else {
						LOG.error("Skipping student record: " + transStubio.getTransStubioKey() + " due to a lack of data.");
					}
				}

				// Commit!
				tx.commit();
			}
			catch (final HibernateException e) {

				// Log the error.
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transStubio.getTransStubioKey(), e);

				// Rollback the transaction, close the session.
				tx.rollback();
				recordSession.close();

				// We need to create a new session and update the person bio with the new session.
				recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
				studentInfo.setDatabaseSession(recordSession);
				changeNotification.setStatelessSession(recordSession);
				eduPersonAffiliationCalculator.setDatabaseSession(recordSession);
			} catch (final CprException ex) {
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transStubio.getTransStubioKey(), ex);
				throw ex;
			} catch (final RuntimeException ex) {
				// Log the error.
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transStubio.getTransStubioKey(), ex);

				// Rollback the transaction, close the session.
				tx.rollback();
				recordSession.close();

				// We need to create a new session and update the person bio with the new session.
				recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
				studentInfo.setDatabaseSession(recordSession);
				changeNotification.setStatelessSession(recordSession);
			}
		}

		try {
			recordSession.close();
		}
		catch (final HibernateException e) { // $codepro.audit.disable logExceptions, emptyCatchClause
		}

		stopTime = System.currentTimeMillis();
		final double elapsedTime = ((double) stopTime - startTime) / 1000;

		LOG.info(BATCH_DATA_SOURCE.toString() + " Batch: processed " + recordsProcessed + " records out of " + totalRecords + " in " + elapsedTime + " seconds");
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
		else if (EmployeeBatchProcessor.BOGUS_DATE_1.equals(dateString) ||
				EmployeeBatchProcessor.BOGUS_DATE_2.equals(dateString) ||
				EmployeeBatchProcessor.BOGUS_DATE_3.equals(dateString)) {
			return null;
		}

		return ISIS_DATE_PARSER.parse(dateString);
	}
}
