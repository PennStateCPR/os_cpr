/* SVN FILE: $Id: StudentAcademicDeptHist.java 7368 2013-05-23 20:11:49Z llg5 $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: llg5 $
 * @version $Rev: 7368 $
 * @lastrevision $Date: 2013-05-23 16:11:49 -0400 (Thu, 23 May 2013) $
 */

@Entity
@Table(name="student_academic_dept_hist")
public class StudentAcademicDeptHist implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Timestamp to mark the start date and time the record is current. Used to set the start_date in the history table when record is modified. The default value is "now". */
        @Id
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Numbered 1-4, the rank denotes the level of affiliation a student has with one or more academic departments. 1= the primary affiliation. */
        @Column(name="rank", nullable=false)
        private Long rank;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Source system the import was from. */
        @Column(name="import_from", nullable=true, length=30)
        private String importFrom;

        /** ID of person associated with the academic department for the specified semester. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Date/Time marking when the record was no longer current in the system. */
        @Column(name="end_date", nullable=false)
        private Date endDate;

        /** Department  code for department student is associated with. */
        @Column(name="department_code", nullable=false, length=5)
        private String departmentCode;

        /** Date record was imported by batch processing. */
        @Column(name="import_date", nullable=true)
        private Date importDate;

        /** The semester code for the semester associated with this person's academic department. */
        @Column(name="semester_code", nullable=false, length=8)
        private String semesterCode;

        /** Contains a unique number that identifies a student academic department.  It is populated by the seq_student_academic_dept sequence. */
        @Id
        @Column(name="student_academic_dept_key", nullable=false)
        private Long studentAcademicDeptKey;

        /**
         * Constructor
         */
        public StudentAcademicDeptHist() {
            super();
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
        public void setStartDate(Date startDate) {
                this.startDate = startDate;
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
        public void setCreatedBy(String createdBy) {
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
        public void setLastUpdateOn(Date lastUpdateOn) {
                this.lastUpdateOn = lastUpdateOn;
        }

        /**
         * @return the rank
         */
        public Long getRank() {
                return rank;
        }

        /**
         * @param rank the rank to set.
         */
        public void setRank(Long rank) {
                this.rank = rank;
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
        public void setCreatedOn(Date createdOn) {
                this.createdOn = createdOn;
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
        public void setLastUpdateBy(String lastUpdateBy) {
                this.lastUpdateBy = lastUpdateBy;
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
        public void setImportFrom(String importFrom) {
                this.importFrom = importFrom;
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
        public void setPersonId(Long personId) {
                this.personId = personId;
        }

        /**
         * @return the endDate
         */
        public Date getEndDate() {
                return endDate;
        }

        /**
         * @param endDate the endDate to set.
         */
        public void setEndDate(Date endDate) {
                this.endDate = endDate;
        }

        /**
         * @return the departmentCode
         */
        public String getDepartmentCode() {
                return departmentCode;
        }

        /**
         * @param departmentCode the departmentCode to set.
         */
        public void setDepartmentCode(String departmentCode) {
                this.departmentCode = departmentCode;
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
        public void setImportDate(Date importDate) {
                this.importDate = importDate;
        }

        /**
         * @return the semesterCode
         */
        public String getSemesterCode() {
                return semesterCode;
        }

        /**
         * @param semesterCode the semesterCode to set.
         */
        public void setSemesterCode(String semesterCode) {
                this.semesterCode = semesterCode;
        }

        /**
         * @return the studentAcademicDeptKey
         */
        public Long getStudentAcademicDeptKey() {
                return studentAcademicDeptKey;
        }

        /**
         * @param studentAcademicDeptKey the studentAcademicDeptKey to set.
         */
        public void setStudentAcademicDeptKey(Long studentAcademicDeptKey) {
                this.studentAcademicDeptKey = studentAcademicDeptKey;
        }

}
