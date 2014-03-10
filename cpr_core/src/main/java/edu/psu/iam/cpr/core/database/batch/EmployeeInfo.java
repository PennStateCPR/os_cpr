/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.batch;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

import edu.psu.iam.cpr.core.database.beans.CampusCs;
import edu.psu.iam.cpr.core.database.beans.Employee;
import edu.psu.iam.cpr.core.database.beans.EmployeeHist;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateHelper;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.util.DataQualityService;
import edu.psu.iam.cpr.core.util.Utility;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * This class provides the concrete implementation for the IBIS (Employee) batch information processor.
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
public class EmployeeInfo extends PersonBio {

	/** Instance of logger */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(EmployeeInfo.class);

	/** Contains a reference to the old employee bean */
	private Employee oldEmployee = null;

	/** Contains a reference to the new employee bean */
	private Employee newEmployee = null;

	/** the record number in the transtable. used for logging. */
	private long transEmplKey;

	/**
	 * Constructor.
	 * @param databaseSession contains the hibernate database session.
	 * @param batchDataSource contains the source of the data feed.
	 * @param dataQualityService contains a reference to a data quality service.
	 */
	public EmployeeInfo(final StatelessSession databaseSession,
			final BatchDataSource batchDataSource,	final DataQualityService dataQualityService) {
		super(databaseSession, batchDataSource, dataQualityService);
	}

	/**
	 * Add an Employee to the CPR.
	 *
	 * @param jobTitleShort The short job title
	 * @param alternateJobTitle The alternate job title
	 * @param hireDate The date the employee was hired
	 * @param terminatedDate The date the employee was terminated
	 * @param lastDatePaid The date the was last paid
	 * @param appointmentCode The appointment code
	 * @param appointmentCodeType TODO
	 * @param adminArea The employee's administrative area
	 * @param adminAreaCode The code for the admin area
	 * @param department The employee's department
	 * @param campusCode The employee's campus
	 * @param statusCode The employee's employment status code
	 * @param classCode The employee's employment class code
	 * @param layoffFlag Indicates if the employee has been laid off. Should be "Y" or "N".
	 * @param benefitsRate The employee's benefits rate
	 * @param visaType Visa code
	 * @param studentStatus The employee's student status
	 * @param specialStatus Special status code
	 * @param directPhoneCode Is the phone a direct phone
	 * @param payFreqCode Contains the employee's pay frequency code.
	 * @throws CprException On any error
	 */
	public void addEmployee(final String jobTitleShort, final String alternateJobTitle, final Date hireDate, final Date terminatedDate,
			final Date lastDatePaid, final String appointmentCode, final String appointmentCodeType, final String adminArea,
			final String adminAreaCode, final String department, final String campusCode, final String statusCode,
			final String classCode, final String layoffFlag, final String benefitsRate, final String visaType,
			final String studentStatus, final String specialStatus, final String directPhoneCode, final String payFreqCode) throws CprException {

		// check for mandatory fields
		if (getBatchDataSource() == BatchDataSource.IBIS && ValidateHelper.isFieldEmpty(appointmentCode)) {
			return;
		}

		/** placeholder for yes/no strings */
		String tempYN = null;

		// add employee-specific information

		final String updatedBy = getBatchDataSource().toString();
		final Date d = getUpdateDate();

		final Employee employee = new Employee();

		// mandatory fields
		employee.setPersonId(getPersonId());
		employee.setStartDate(d);
		employee.setCreatedBy(updatedBy);
		employee.setCreatedOn(d);
		employee.setLastUpdateBy(updatedBy);
		employee.setLastUpdateOn(d);
		employee.setImportFrom(updatedBy);
		employee.setImportDate(d);

		employee.setApptCode(appointmentCode);

		// switch between transEmpl's format (P = Primary; S = Secondary)
		// to Employee's format (a yes/no field for primary appt)
		if ("P".equals(appointmentCodeType)) {
			employee.setPrimaryApptFlag("Y");
		} else {
			employee.setPrimaryApptFlag("N");
		}

		if (showEmployeeInDirectory(directPhoneCode)) {
			employee.setShowInDirectoryFlag("Y");
		} else {
			employee.setShowInDirectoryFlag("N");
		}

		// optional fields
		if (!ValidateHelper.isFieldEmpty(adminAreaCode)) {
			employee.setAdminAreaCode(adminAreaCode);
		}

		if (!ValidateHelper.isFieldEmpty(adminArea)) {
			employee.setAdminArea(adminArea);
		}

		if (!ValidateHelper.isFieldEmpty(department)) {
			employee.setDepartment(department);
		}

		if (!ValidateHelper.isFieldEmpty(jobTitleShort)) {
			employee.setJobTitle(jobTitleShort);
		}

		if (!ValidateHelper.isFieldEmpty(alternateJobTitle)) {
			employee.setAlternateJobTitle(alternateJobTitle);
		}

		tempYN = Validate.isValidYesNo(studentStatus);
		if (tempYN != null) {
			employee.setStudentStatus(tempYN);
		}

		tempYN = Validate.isValidYesNo(specialStatus);
		if (tempYN != null) {
			employee.setSpecialStatus(tempYN);
		}

		tempYN = Validate.isValidYesNo(layoffFlag);
		if (tempYN != null) {
			employee.setLayoffFlag(tempYN);
		}


		if (lastDatePaid != null) {
			employee.setLastDatePaid(lastDatePaid);
		}

		if (hireDate != null) {
			employee.setHireDate(hireDate);
		}

		if (terminatedDate != null) {
			employee.setTerminatedDate(terminatedDate);
		}

		if (!ValidateHelper.isFieldEmpty(visaType)) {
			employee.setVisaType(visaType);
		}

		if (!ValidateHelper.isFieldEmpty(campusCode)) {
			try {
				final CampusCs campusCsBean = getCampusBean(campusCode);
				employee.setCampusCodeKey(campusCsBean.getCampusCodeKey());
			} catch (final CprException ex) {
				LOG4J_LOGGER.warn("Unknown campus code: " + campusCode + " for record " + transEmplKey, ex);
			}
		}

		if (!ValidateHelper.isFieldEmpty(statusCode)) {
			employee.setStatusCode(statusCode);
		}

		if (!ValidateHelper.isFieldEmpty(classCode)) {
			employee.setClassCode(classCode);
		}

		if (!ValidateHelper.isFieldEmpty(benefitsRate)) {
			employee.setBenefitsRate(benefitsRate);
		}
		
		if (!ValidateHelper.isFieldEmpty(payFreqCode)) {
			employee.setPayFreqCode(payFreqCode);
		}

		// store the new record
		getDatabaseSession().insert(employee);

		// New employee added, reset the old bean and save off the new bean.
		setOldEmployee(null);
		setNewEmployee(employee);
	}

	/**
	 * Utility method to convert a 'Y' or 'N' into a boolean.
	 *
	 * @param boolString A string that should contain a 'Y' or an 'N'
	 * @return true if the string contains a 'Y' otherwise false.
	 */
	protected boolean booleanFromString(final String boolString) {
		if (ValidateHelper.isFieldEmpty(boolString)) {
			return false;
		}

		if ("Y".equals(boolString.trim().toUpperCase())) {
			return true;
		}

		return false;
	}

	/**
	 * @return the newEmployee
	 */
	public Employee getNewEmployee() {
		return newEmployee;
	}

	/**
	 * @return the oldEmployee
	 */
	public Employee getOldEmployee() {
		return oldEmployee;
	}

	/**
	 * This method is used to reset the history beans.  It is called by the batch processor for each new person record.
	 */
	@Override
	public void resetHistoryBeans() {
		super.resetHistoryBeans();
		oldEmployee = null;
		newEmployee = null;
		transEmplKey = -1;
	}

	/**
	 * Save an employee history record.
	 *
	 * @param oldEmployee The old Employee record to save.
	 * @throws CprException on any error.
	 */
	public void saveEmployeeHistory(final Employee oldEmployee) throws CprException {
		final EmployeeHist employeeHist = new EmployeeHist();

		// set employee info fields
		employeeHist.setAdminArea(oldEmployee.getAdminArea());
		employeeHist.setAdminAreaCode(oldEmployee.getAdminAreaCode());
		employeeHist.setAlternateJobTitle(oldEmployee.getAlternateJobTitle());
		employeeHist.setApptCode(oldEmployee.getApptCode());
		employeeHist.setBenefitsRate(oldEmployee.getBenefitsRate());
		employeeHist.setCampusCodeKey(oldEmployee.getCampusCodeKey());
		employeeHist.setClassCode(oldEmployee.getClassCode());
		employeeHist.setDepartment(oldEmployee.getDepartment());
		employeeHist.setEmployeeKey(oldEmployee.getEmployeeKey());
		employeeHist.setHireDate(oldEmployee.getHireDate());
		employeeHist.setJobTitle(oldEmployee.getJobTitle());
		employeeHist.setLastDatePaid(oldEmployee.getLastDatePaid());
		employeeHist.setLayoffFlag(oldEmployee.getLayoffFlag());
		employeeHist.setPayFreqCode(oldEmployee.getPayFreqCode());
		employeeHist.setPrimaryApptFlag(oldEmployee.getPrimaryApptFlag());
		employeeHist.setPersonId(oldEmployee.getPersonId());
		employeeHist.setShowInDirectoryFlag(oldEmployee.getShowInDirectoryFlag());
		employeeHist.setSpecialStatus(oldEmployee.getSpecialStatus());
		employeeHist.setStatusCode(oldEmployee.getStatusCode());
		employeeHist.setStudentStatus(oldEmployee.getStudentStatus());
		employeeHist.setTerminatedDate(oldEmployee.getTerminatedDate());
		employeeHist.setVisaType(oldEmployee.getVisaType());

		// copy metadata fields and set the end date
		employeeHist.setCreatedBy(oldEmployee.getCreatedBy());
		employeeHist.setCreatedOn(oldEmployee.getCreatedOn());
		employeeHist.setImportDate(oldEmployee.getImportDate());
		employeeHist.setImportFrom(oldEmployee.getImportFrom());
		employeeHist.setLastUpdateBy(oldEmployee.getLastUpdateBy());
		employeeHist.setLastUpdateOn(oldEmployee.getLastUpdateOn());
		employeeHist.setStartDate(oldEmployee.getStartDate());
		employeeHist.setEndDate(getUpdateDate());

		// insert into the database.
		getDatabaseSession().insert(employeeHist);
	}

	/**
	 * @param newEmployee the newEmployee to set
	 */
	public void setNewEmployee(final Employee newEmployee) {
		this.newEmployee = newEmployee;
	}

	/**
	 * @param oldEmployee the oldEmployee to set
	 */
	public void setOldEmployee(final Employee oldEmployee) {
		this.oldEmployee = oldEmployee;
	}

	/**
	 * @param transEmplKey The trans_empl_key value for the current record
	 */
	public void setRecordNumber(final long transEmplKey) {
		this.transEmplKey = transEmplKey;
	}


	/**
	 * Utility method to calculate if we show an employee in the directory.
	 *
	 * @param directPhoneCode The employee's direct phone number.
	 * @return true if the employee should be shown in directory, otherwise false.
	 */
	public boolean showEmployeeInDirectory(final String directPhoneCode) {

		if (ValidateHelper.isFieldEmpty(directPhoneCode) || "B".equals(directPhoneCode) ||  "W".equals(directPhoneCode)) {
			return true;
		} else if ("N".equals(directPhoneCode)) {
			return false;
		}

		return true;
	}

	/**
	 * Update an employee in the CPR.
	 *
	 * @param jobTitleShort The short job title
	 * @param alternateJobTitle The alternate job title
	 * @param hireDate The date the employee was hired
	 * @param terminatedDate The date the employee was terminated
	 * @param lastDatePaid The date the was last paid
	 * @param appointmentCode The appointment code
	 * @param appointmentCodeType The appointment code type
	 * @param adminArea The employee's administrative area
	 * @param adminAreaCode The code for the admin area
	 * @param department The employee's department
	 * @param campusCode The employee's campus
	 * @param statusCode The employee's employment status code
	 * @param classCode The employee's employment class code
	 * @param layoffFlag Indicates if the employee has been laid off. Should be "Y" or "N".
	 * @param benefitsRate The employee's benefits rate
	 * @param visaType Visa code
	 * @param studentStatus The employee's student status
	 * @param specialStatus Special status code
	 * @param directPhoneCode Is the phone a direct phone
	 * @param eduPersonPrimaryAffiliation the EduPersonPrimaryAffiliation (will be null except for Hershey)
	 * @param payFreqCode Contains the employee's pay frequency code.
	 * @throws CprException On any error.
	 */
	public void updateEmployee(final String jobTitleShort, final String alternateJobTitle, final Date hireDate, final Date terminatedDate,
			final Date lastDatePaid, final String appointmentCode, final String appointmentCodeType, final String adminArea,
			final String adminAreaCode, final String department, final String campusCode, final String statusCode,
			final String classCode, final String layoffFlag, final String benefitsRate, final String visaType,
			final String studentStatus, final String specialStatus, final String directPhoneCode, final String eduPersonPrimaryAffiliation, 
			final String payFreqCode) throws CprException  {

		// check for mandatory fields
		if (getBatchDataSource() == BatchDataSource.IBIS && ValidateHelper.isFieldEmpty(appointmentCode)) {
			return;
		}

		boolean matchFound = false;
		boolean changeFound = false;
		boolean lastPaidChangeFound = false;

		boolean processingSecondary = false;
		boolean changedTermDate = false;

		final Date updateDate = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();

		// look for any employees
		final StatelessSession session = getDatabaseSession();

		// switch between transEmpl/transHershey's format (P = Primary; S = Secondary) to Employee's format (a yes/no field for primary appt)
		String primaryAppt;
		if ("P".equals(appointmentCodeType)) {
			primaryAppt = "Y";
		} else {
			primaryAppt ="N";
		}

		Query query = null;

		// we use different logic for IBIS vs HMC updates
		// because they pass different values
		String localDepartment = null;
		if (department != null) {
			localDepartment = department.toUpperCase();
		}
		if (getBatchDataSource() == BatchDataSource.IBIS || getBatchDataSource() == BatchDataSource.UNIT_TEST) {
			if (Utility.isOptionYes(primaryAppt)) {
				query = session.createQuery("from Employee where personId = :person_id and primaryApptFlag = :primary_appt_flag");
				query.setParameter("person_id", getPersonId());
				query.setParameter("primary_appt_flag", primaryAppt);
			} else {
				processingSecondary = true;

				// Make sure we are doing a case insensitive compare when we are doing a compare using department.
				query = session.createQuery("from Employee where personId = :person_id and primaryApptFlag = :primary_appt_flag and apptCode = :appt_code and statusCode = :status_code and upper(department) = :department and alternateJobTitle = :alt_job_title");
				query.setParameter("person_id", getPersonId());
				query.setParameter("primary_appt_flag", primaryAppt);
				query.setParameter("appt_code", appointmentCode);
				query.setParameter("status_code", statusCode);
				query.setParameter("department", localDepartment);
				query.setParameter("alt_job_title", alternateJobTitle);
			}
		} else if (getBatchDataSource() == BatchDataSource.HMC) {
			
			// Make sure we are doing a case insensitive compare for the department.
			query = session.createQuery("from Employee where personId = :person_id and upper(department) = :department and jobTitle = :job_title");
			query.setParameter("person_id", getPersonId());
			query.setParameter("department", localDepartment);
			query.setParameter("job_title", jobTitleShort);
		}

		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			matchFound = true;

			final Employee employee = (Employee) it.next();
			setOldEmployee(new Employee(employee));
			setNewEmployee(new Employee(employee));

			// since we don't match on primary/secondary appt for HMC,
			// update the record here
			if (getBatchDataSource() == BatchDataSource.HMC && appointmentCodeType != null
					&& !Utility.areStringFieldsEqual(employee.getPrimaryApptFlag(), primaryAppt)) {
				changeFound = true;
				newEmployee.setPrimaryApptFlag(primaryAppt);
			}

			// compare string fields

			if (!Utility.areStringFieldsEqual(employee.getJobTitle(), jobTitleShort)) {
				changeFound = true;
				newEmployee.setJobTitle(jobTitleShort);
			}

			if (!Utility.areStringFieldsEqual(employee.getAlternateJobTitle(), alternateJobTitle)) {
				changeFound = true;
				newEmployee.setAlternateJobTitle(alternateJobTitle);
			}

			if (!Utility.areStringFieldsEqual(employee.getBenefitsRate(), benefitsRate)) {
				changeFound = true;
				newEmployee.setAlternateJobTitle(benefitsRate);
			}

			final String tempLayoffFlag = Validate.isValidYesNo(layoffFlag);
			final String tempEmpLayoffFlag = Validate.isValidYesNo(employee.getLayoffFlag());
			if (!Utility.areStringFieldsEqual(tempEmpLayoffFlag, tempLayoffFlag)) {
				changeFound = true;
				newEmployee.setLayoffFlag(layoffFlag);
			}

			if (!Utility.areStringFieldsEqual(employee.getAdminArea(), adminArea)) {
				changeFound = true;
				newEmployee.setAdminArea(adminArea);
			}

			if (!Utility.areStringFieldsEqual(employee.getAdminAreaCode(), adminAreaCode)) {
				changeFound = true;
				newEmployee.setAdminAreaCode(adminAreaCode);
			}

			// Ignore case for department compares.
			if (!Utility.areStringFieldsEqualIgnoreCase(employee.getDepartment(), department)) {
				changeFound = true;
				newEmployee.setDepartment(department);
			}

			if (!Utility.areStringFieldsEqual(employee.getStudentStatus(), studentStatus)) {
				changeFound = true;
				newEmployee.setStudentStatus(studentStatus);
			}

			if (!Utility.areStringFieldsEqual(employee.getStatusCode(), statusCode)) {
				changeFound = true;
				newEmployee.setStatusCode(statusCode);

				// HMC doesn't pass us a termination date. If this is the first
				// time we've seen an HMC term for this record, insert a term date.
				if (getBatchDataSource() == BatchDataSource.HMC && employee.getTerminatedDate() == null) {
					newEmployee.setTerminatedDate(getUpdateDate());
				}
			}

			if (!Utility.areStringFieldsEqual(employee.getClassCode(), classCode)) {
				changeFound = true;
				newEmployee.setClassCode(classCode);
			}

			if (!Utility.areStringFieldsEqual(employee.getVisaType(), visaType)) {
				changeFound = true;
				newEmployee.setVisaType(visaType);
			}
			
			if (!Utility.areStringFieldsEqual(employee.getPayFreqCode(), payFreqCode)) {
				changeFound = true;
				newEmployee.setPayFreqCode(payFreqCode);
			}


			if (!ValidateHelper.isFieldEmpty(campusCode)) {
				try {
					final CampusCs campusCsBean = getCampusBean(campusCode);
					final Long newCampusCode = campusCsBean.getCampusCodeKey();
					final Long oldCampusCode = employee.getCampusCodeKey();
					if (!Utility.areLongFieldsEqual(oldCampusCode, newCampusCode)) {
						changeFound = true;
						newEmployee.setCampusCodeKey(newCampusCode);
					}
				} catch (final CprException ex) {
					LOG4J_LOGGER.warn("Unknown campus code: " + campusCode + " for record " + transEmplKey, ex);
				}
			}


			final boolean oldEmployeeShowDir = booleanFromString(employee.getShowInDirectoryFlag());
			final boolean newEmployeeShowDir = showEmployeeInDirectory(directPhoneCode);
			if (oldEmployeeShowDir != newEmployeeShowDir) {
				changeFound = true;
				if (newEmployeeShowDir) {
					newEmployee.setShowInDirectoryFlag("Y");
				} else {
					newEmployee.setShowInDirectoryFlag("N");
				}
			}


			// compare date fields
			if (lastDatePaid != null && !lastDatePaid.equals(oldEmployee.getLastDatePaid())) {
				lastPaidChangeFound = true;
				oldEmployee.setLastDatePaid(lastDatePaid);
				newEmployee.setLastDatePaid(lastDatePaid);
			}

			if (hireDate != null && !hireDate.equals(oldEmployee.getHireDate())) {
				changeFound = true;
				newEmployee.setHireDate(hireDate);
			}

			if (terminatedDate != null && !terminatedDate.equals(oldEmployee.getTerminatedDate())) {
				//changeFound = true;
				changedTermDate = true;
				newEmployee.setTerminatedDate(terminatedDate);
			}

			// special case logic for people
			// with multiple secondary appts and a term date change
			// on one of the secondary appts.
			if (!changeFound && changedTermDate && processingSecondary) {
				LOG4J_LOGGER.error("Unable to determine which record to update. Trans_empl_key: " + transEmplKey);
			}

			// save changes to the database
			if (changeFound || lastPaidChangeFound) {

				// If all that changed was the last date paid, update in place.
				// Since last date paid changes frequently,
				// we don't save a history record if it's the only thing that changed.
				if (!changeFound && lastPaidChangeFound) {
					getDatabaseSession().update(oldEmployee);
				}

				// archive the old bean
				if (changeFound) {
					saveEmployeeHistory(oldEmployee);
				}

				newEmployee.setStartDate(updateDate);

				newEmployee.setLastUpdateBy(updatedBy);
				newEmployee.setLastUpdateOn(updateDate);

				newEmployee.setImportFrom(updatedBy);
				newEmployee.setImportDate(updateDate);

				getDatabaseSession().update(newEmployee);
			} else {
				// Employee was found, but there was no changes, so we need to reset the employee beans.
				setOldEmployee(null);
				setNewEmployee(null);
			}
		}


		if (!matchFound) {
			addEmployee(jobTitleShort, alternateJobTitle, hireDate, terminatedDate, lastDatePaid,
					appointmentCode, appointmentCodeType, adminArea, adminAreaCode, department, campusCode,
					statusCode, classCode, layoffFlag, benefitsRate, visaType, studentStatus, specialStatus, directPhoneCode, payFreqCode);
		}
	}
}
