/* SVN FILE: $Id: Employee.java 7685 2013-06-27 17:36:39Z llg5 $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: llg5 $
 * @version $Rev: 7685 $
 * @lastrevision $Date: 2013-06-27 13:36:39 -0400 (Thu, 27 Jun 2013) $
 */

@Entity
@Table(name="employee")
public class Employee implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Alternate job title for employee per appointment. */
        @Column(name="alternate_job_title", nullable=true, length=128)
        private String alternateJobTitle;

        /** Contains a unique number that identifies an employee.  It is populated by the seq_employee sequence. */
        @Id
        @Column(name="employee_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_employee")
        @SequenceGenerator(name="seq_employee", sequenceName="seq_employee", allocationSize = 1, initialValue= 1)
        private Long employeeKey;

        /** Employee benefit rate. For example W, R, P... */
        @Column(name="benefits_rate", nullable=true, length=1)
        private String benefitsRate;

        /** Source system the import was from. */
        @Column(name="import_from", nullable=true, length=30)
        private String importFrom;

        /** Flag to indicate employee layoff status.  Values are 'Y' or 'N'.  */
        @Column(name="layoff_flag", nullable=true, length=1)
        private String layoffFlag;

        /** Date employee was last paid. */
        @Column(name="last_date_paid", nullable=true)
        private Date lastDatePaid;

        /** Employee's job title for specified employee appointment. */
        @Column(name="job_title", nullable=true, length=80)
        private String jobTitle;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains a flag to indicate if this is the primary appointment for an employee.  The valid values are Y or N.  The default value is N. An employee has 1 primary appointment with zero or more secondary appointments. */
        @Column(name="primary_appt_flag", nullable=false, length=1)
        private String primaryApptFlag;

        /** Date employee was hired. */
        @Column(name="hire_date", nullable=true)
        private Date hireDate;

        /** Employee pay frequency. Value stored is pay frequency code from code set table. */
        @Column(name="pay_freq_code", nullable=true, length=4)
        private String payFreqCode;

        /** Three character employee appointment code. */
        @Column(name="appt_code", nullable=true, length=3)
        private String apptCode;

        /** Campus employee is associated with for the specified appointment.  Foreign key to the campus_cs table holding the campus code and name. */
        @Column(name="campus_code_key", nullable=true)
        private Long campusCodeKey;

        /** Department employee is associated with for specified appointment. */
        @Column(name="department", nullable=true, length=80)
        private String department;

        /** Flag indicating whether this employees information should be in the directory.  Values are either 'Y' or 'N' default is 'N'. */
        @Column(name="show_in_directory_flag", nullable=false, length=1)
        private String showInDirectoryFlag;

        /** Employee status code. ACT, MIL, RET, TER.... */
        @Column(name="status_code", nullable=true, length=3)
        private String statusCode;

        /** Employee class code per appointment. ACAD, STFF, CLER... */
        @Column(name="class_code", nullable=true, length=4)
        private String classCode;

        /** Employee visa type.  For example J1, F1... */
        @Column(name="visa_type", nullable=true, length=6)
        private String visaType;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Date record was imported by batch processing. */
        @Column(name="import_date", nullable=true)
        private Date importDate;

        /** Special Appointment Status.  V = Visiting E = Emeritus... */
        @Column(name="special_status", nullable=true, length=1)
        private String specialStatus;

        /** Timestamp to mark the start date and time the record is current. Used to set the start_date in the history table when record is modified. The default value is "now". */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Person ID associated with this employee information. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** The employee's enrollment status. */
        @Column(name="student_status", nullable=true, length=1)
        private String studentStatus;

        /** Administration Area name per employee appointment. For Example 'DEAN'S OFFICE'. */
        @Column(name="admin_area", nullable=true, length=50)
        private String adminArea;

        /** Administration Area code per employee appointment. For example 080.  */
        @Column(name="admin_area_code", nullable=true, length=10)
        private String adminAreaCode;

        /** Date employment terminated. An entry in this field does not mean that the individual has lost all services (i.e. s/he may have services through other affiliations). */
        @Column(name="terminated_date", nullable=true)
        private Date terminatedDate;

        /**
         * Constructor
         */
        public Employee() {
            super();
        }

        /**
		 * Copy Constructor.
		 *
		 * @param employee An Employee object to duplicate.
		 */
		public Employee(final Employee employee) {
			super();
			
			if (employee == null) {
				return;
			}
			
			setAlternateJobTitle(employee.getAlternateJobTitle());
			setEmployeeKey(employee.getEmployeeKey());
			setBenefitsRate(employee.getBenefitsRate());
			setImportFrom(employee.getImportFrom());
			setLayoffFlag(employee.getLayoffFlag());
			setLastDatePaid(employee.getLastDatePaid());
			setJobTitle(employee.getJobTitle());
			setCreatedBy(employee.getCreatedBy());
			setLastUpdateOn(employee.getLastUpdateOn());
			setPrimaryApptFlag(employee.getPrimaryApptFlag());
			setHireDate(employee.getHireDate());
			setApptCode(employee.getApptCode());
			setCampusCodeKey(employee.getCampusCodeKey());
			setDepartment(employee.getDepartment());
			setShowInDirectoryFlag(employee.getShowInDirectoryFlag());
			setStatusCode(employee.getStatusCode());
			setClassCode(employee.getClassCode());
			setVisaType(employee.getVisaType());
			setLastUpdateBy(employee.getLastUpdateBy());
			setImportDate(employee.getImportDate());
			setSpecialStatus(employee.getSpecialStatus());
			setStartDate(employee.getStartDate());
			setCreatedOn(employee.getCreatedOn());
			setPersonId(employee.getPersonId());
			setStudentStatus(employee.getStudentStatus());
			setAdminArea(employee.getAdminArea());
			setAdminAreaCode(employee.getAdminAreaCode());
			setTerminatedDate(employee.getTerminatedDate());
			setPayFreqCode(employee.getPayFreqCode());
		}

		/**
		 * Copy Constructor
		 */
		public Employee(final Long personId, Long employeeKey, final String createdBy, final String importFrom, 
				final Date importDate, final Date createdOn, final Date lastUpdateOn, final String lastUpdateBy, 
				final String jobTitle, final String alternateJobTitle, final Date startDate, final Date hireDate, 
				final Date terminatedDate, final Date lastDatePaid, final String appointmentCode, final String adminArea, 
				final String adminAreaCode, final String department, final Long campusCodeKey, final String statusCode,
				final String classCode,	final String primaryApptFlag, final String layoffFlag, final String benefitsRate, 
				final String visaType, final String studentStatus, final String specialStatus, final String showInDirectoryFlag, final String payFreqCode) {

			super();
			setPersonId(personId);
			setEmployeeKey(employeeKey);
			setCreatedBy(createdBy);
			setImportFrom(importFrom);
			setImportDate(importDate);
			setCreatedOn(createdOn);
			setLastUpdateOn(lastUpdateOn);
			setLastUpdateBy(lastUpdateBy);
			setJobTitle(jobTitle);
			setAlternateJobTitle(alternateJobTitle);
			setStartDate(startDate);
			setHireDate(hireDate);
			setTerminatedDate(terminatedDate);
			setLastDatePaid(lastDatePaid);
			setApptCode(appointmentCode);
			setAdminArea(adminArea);
			setAdminAreaCode(adminAreaCode);
			setDepartment(department);
			setCampusCodeKey(campusCodeKey);
			setStatusCode(statusCode);
			setClassCode(classCode);
			setPrimaryApptFlag(primaryApptFlag);
			setLayoffFlag(layoffFlag);
			setBenefitsRate(benefitsRate);
			setVisaType(visaType);
			setStudentStatus(studentStatus);
			setSpecialStatus(specialStatus);
			setShowInDirectoryFlag(showInDirectoryFlag);
			setPayFreqCode(payFreqCode);
		}

        /**
         * @return the alternateJobTitle
         */
        public String getAlternateJobTitle() {
                return alternateJobTitle;
        }

        /**
         * @param alternateJobTitle the alternateJobTitle to set.
         */
        public final void setAlternateJobTitle(String alternateJobTitle) {
                this.alternateJobTitle = alternateJobTitle;
        }

        /**
         * @return the employeeKey
         */
        public Long getEmployeeKey() {
                return employeeKey;
        }

        /**
         * @param employeeKey the employeeKey to set.
         */
        public final void setEmployeeKey(Long employeeKey) {
                this.employeeKey = employeeKey;
        }

        /**
         * @return the benefitsRate
         */
        public String getBenefitsRate() {
                return benefitsRate;
        }

        /**
         * @param benefitsRate the benefitsRate to set.
         */
        public final void setBenefitsRate(String benefitsRate) {
                this.benefitsRate = benefitsRate;
        }

        /**
         * @return the importFrom
         */
        public String getImportFrom() {
                return importFrom;
        }

        /**
         * @param importFrom the importFrom to set.
         */
        public final void setImportFrom(String importFrom) {
                this.importFrom = importFrom;
        }

        /**
         * @return the layoffFlag
         */
        public String getLayoffFlag() {
                return layoffFlag;
        }

        /**
         * @param layoffFlag the layoffFlag to set.
         */
        public final void setLayoffFlag(String layoffFlag) {
                this.layoffFlag = layoffFlag;
        }

        /**
         * @return the lastDatePaid
         */
        public Date getLastDatePaid() {
                return lastDatePaid;
        }

        /**
         * @param lastDatePaid the lastDatePaid to set.
         */
        public final void setLastDatePaid(Date lastDatePaid) {
                this.lastDatePaid = lastDatePaid;
        }

        /**
         * @return the jobTitle
         */
        public String getJobTitle() {
                return jobTitle;
        }

        /**
         * @param jobTitle the jobTitle to set.
         */
        public final void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
        }

        /**
         * @return the createdBy
         */
        public String getCreatedBy() {
                return createdBy;
        }

        /**
         * @param createdBy the createdBy to set.
         */
        public final void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
        }

        /**
         * @return the lastUpdateOn
         */
        public Date getLastUpdateOn() {
                return lastUpdateOn;
        }

        /**
         * @param lastUpdateOn the lastUpdateOn to set.
         */
        public final void setLastUpdateOn(Date lastUpdateOn) {
                this.lastUpdateOn = lastUpdateOn;
        }

        /**
         * @return the primaryApptFlag
         */
        public String getPrimaryApptFlag() {
                return primaryApptFlag;
        }

        /**
         * @param primaryApptFlag the primaryApptFlag to set.
         */
        public final void setPrimaryApptFlag(String primaryApptFlag) {
                this.primaryApptFlag = primaryApptFlag;
        }

        /**
         * @return the hireDate
         */
        public Date getHireDate() {
                return hireDate;
        }

        /**
         * @param hireDate the hireDate to set.
         */
        public final void setHireDate(Date hireDate) {
                this.hireDate = hireDate;
        }

        /**
         * @return the payFreqCode
         */
        public String getPayFreqCode() {
                return payFreqCode;
        }

        /**
         * @param payFreqCode the payFreqCode to set.
         */
        public final void setPayFreqCode(String payFreqCode) {
                this.payFreqCode = payFreqCode;
        }

        /**
         * @return the apptCode
         */
        public String getApptCode() {
                return apptCode;
        }

        /**
         * @param apptCode the apptCode to set.
         */
        public final void setApptCode(String apptCode) {
                this.apptCode = apptCode;
        }

        /**
         * @return the campusCodeKey
         */
        public Long getCampusCodeKey() {
                return campusCodeKey;
        }

        /**
         * @param campusCodeKey the campusCodeKey to set.
         */
        public final void setCampusCodeKey(Long campusCodeKey) {
                this.campusCodeKey = campusCodeKey;
        }

        /**
         * @return the department
         */
        public String getDepartment() {
                return department;
        }

        /**
         * @param department the department to set.
         */
        public final void setDepartment(String department) {
                this.department = department;
        }

        /**
         * @return the showInDirectoryFlag
         */
        public String getShowInDirectoryFlag() {
                return showInDirectoryFlag;
        }

        /**
         * @param showInDirectoryFlag the showInDirectoryFlag to set.
         */
        public final void setShowInDirectoryFlag(String showInDirectoryFlag) {
                this.showInDirectoryFlag = showInDirectoryFlag;
        }

        /**
         * @return the statusCode
         */
        public String getStatusCode() {
                return statusCode;
        }

        /**
         * @param statusCode the statusCode to set.
         */
        public final void setStatusCode(String statusCode) {
                this.statusCode = statusCode;
        }

        /**
         * @return the classCode
         */
        public String getClassCode() {
                return classCode;
        }

        /**
         * @param classCode the classCode to set.
         */
        public final void setClassCode(String classCode) {
                this.classCode = classCode;
        }

        /**
         * @return the visaType
         */
        public String getVisaType() {
                return visaType;
        }

        /**
         * @param visaType the visaType to set.
         */
        public final void setVisaType(String visaType) {
                this.visaType = visaType;
        }

        /**
         * @return the lastUpdateBy
         */
        public String getLastUpdateBy() {
                return lastUpdateBy;
        }

        /**
         * @param lastUpdateBy the lastUpdateBy to set.
         */
        public final void setLastUpdateBy(String lastUpdateBy) {
                this.lastUpdateBy = lastUpdateBy;
        }

        /**
         * @return the importDate
         */
        public Date getImportDate() {
                return importDate;
        }

        /**
         * @param importDate the importDate to set.
         */
        public final void setImportDate(Date importDate) {
                this.importDate = importDate;
        }

        /**
         * @return the specialStatus
         */
        public String getSpecialStatus() {
                return specialStatus;
        }

        /**
         * @param specialStatus the specialStatus to set.
         */
        public final void setSpecialStatus(String specialStatus) {
                this.specialStatus = specialStatus;
        }

        /**
         * @return the startDate
         */
        public Date getStartDate() {
                return startDate;
        }

        /**
         * @param startDate the startDate to set.
         */
        public final void setStartDate(Date startDate) {
                this.startDate = startDate;
        }

        /**
         * @return the createdOn
         */
        public Date getCreatedOn() {
                return createdOn;
        }

        /**
         * @param createdOn the createdOn to set.
         */
        public final void setCreatedOn(Date createdOn) {
                this.createdOn = createdOn;
        }

        /**
         * @return the personId
         */
        public Long getPersonId() {
                return personId;
        }

        /**
         * @param personId the personId to set.
         */
        public final void setPersonId(Long personId) {
                this.personId = personId;
        }

        /**
         * @return the studentStatus
         */
        public String getStudentStatus() {
                return studentStatus;
        }

        /**
         * @param studentStatus the studentStatus to set.
         */
        public final void setStudentStatus(String studentStatus) {
                this.studentStatus = studentStatus;
        }

        /**
         * @return the adminArea
         */
        public String getAdminArea() {
                return adminArea;
        }

        /**
         * @param adminArea the adminArea to set.
         */
        public final void setAdminArea(String adminArea) {
                this.adminArea = adminArea;
        }

        /**
         * @return the adminAreaCode
         */
        public String getAdminAreaCode() {
                return adminAreaCode;
        }

        /**
         * @param adminAreaCode the adminAreaCode to set.
         */
        public final void setAdminAreaCode(String adminAreaCode) {
                this.adminAreaCode = adminAreaCode;
        }

        /**
         * @return the terminatedDate
         */
        public Date getTerminatedDate() {
                return terminatedDate;
        }

        /**
         * @param terminatedDate the terminatedDate to set.
         */
        public final void setTerminatedDate(Date terminatedDate) {
                this.terminatedDate = terminatedDate;
        }

}
