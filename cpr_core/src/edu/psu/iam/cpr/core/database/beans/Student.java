/* SVN FILE: $Id: Student.java 7368 2013-05-23 20:11:49Z llg5 $ */
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
 * @version $Rev: 7368 $
 * @lastrevision $Date: 2013-05-23 16:11:49 -0400 (Thu, 23 May 2013) $
 */

@Entity
@Table(name="student")
public class Student implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /**  Student registration status, used to calculate affiliation. Examples include REGADV, REGIND, SCHED, APPL. */
        @Column(name="registration_status", nullable=true, length=6)
        private String registrationStatus;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Source system the import was from. */
        @Column(name="import_from", nullable=true, length=30)
        private String importFrom;

        /** Indicates whether student intends to graduate with a degree.  Valid values are 'Y', 'N'. */
        @Column(name="graduating_flag", nullable=true, length=1)
        private String graduatingFlag;

        /**  Student's academic leave of absence start semester.  This is the semester code for the start of the 
         * students leave of absence. */
        @Column(name="sem_loa_start", nullable=true, length=8)
        private String semLoaStart;

        /** Student's academic leave of absence end semester. This is the semester code for the 
         * end of the students leave of absence. */
        @Column(name="sem_loa_end", nullable=true, length=8)
        private String semLoaEnd;

        /** Date record was imported by batch processing. */
        @Column(name="import_date", nullable=true)
        private Date importDate;

        /**  Timestamp to mark the start date and time the record is current. Used to set the start_date in the 
         * history table when record is modified. The default value is "now". */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** ** Student's associated person ID from the person table.  PERSON_ID and SEMESTER_CODE uniquely identify 
         * the student record in this table. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /**  Flag indicating whether student has student aid. Valid values are 'Y' or 'N'. */
        @Column(name="student_aid_flag", nullable=true, length=1)
        private String studentAidFlag;

        /** Student academic level for the current semester. UG=Undergrad, GR=Grad, MD=Medical LW=Law */
        @Column(name="academic_level", nullable=true, length=2)
        private String academicLevel;

        /** Contains a unique number that identifies a student.  It is populated by the seq_student sequence.  */
        @Id
        @Column(name="student_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_student")
        @SequenceGenerator(name="seq_student", sequenceName="seq_student", allocationSize = 1, initialValue= 1)
        private Long studentKey;

        /** Class load.  Full-time or Part-time for current semester.  Values are "F", "H", or "L". */
        @Column(name="class_load", nullable=true, length=1)
        private String classLoad;

        /** Campus student is associated with during current semester. */
        @Column(name="campus_code_key", nullable=true)
        private Long campusCodeKey;

        /**  Semester code of the semester for which this student information is associated with.  */
        @Column(name="semester_code", nullable=false, length=8)
        private String semesterCode;

        /** Semester standing for current semester. 01-11,GR=Grad, NC=non-credit.  */
        @Column(name="year_term", nullable=true, length=2)
        private String yearTerm;

        /**  Location of the student's dorm during this semester.  Four character string representing dorm.  */
        @Column(name="dorm_location", nullable=true, length=4)
        private String dormLocation;

        /**
         * Field constructor.
         * @param registrationStatus registration status.
         * @param lastUpdateBy last updated by.
         * @param importFrom import from.
         * @param graduatingFlag graduating flag.
         * @param semLoaStart loa start date.
         * @param semLoaEnd loa end date.
         * @param importDate import date.
         * @param startDate start date.
         * @param createdBy created by.
         * @param lastUpdateOn last update on.
         * @param createdOn created on.
         * @param personId person identifier.
         * @param studentAidFlag student aid flag.
         * @param academicLevel academic level.
         * @param studentKey student key.
         * @param classLoad class load.
         * @param campusCodeKey campus code key.
         * @param semesterCode semester code.
         * @param yearTerm year terminated.
         * @param dormLocation dorm location.
         */
        public Student(String registrationStatus, String lastUpdateBy,
				String importFrom, String graduatingFlag, String semLoaStart,
				String semLoaEnd, Date importDate, Date startDate,
				String createdBy, Date lastUpdateOn, Date createdOn,
				Long personId, String studentAidFlag, String academicLevel,
				Long studentKey, String classLoad, Long campusCodeKey,
				String semesterCode, String yearTerm, String dormLocation) {
			super();
			this.registrationStatus = registrationStatus;
			this.lastUpdateBy = lastUpdateBy;
			this.importFrom = importFrom;
			this.graduatingFlag = graduatingFlag;
			this.semLoaStart = semLoaStart;
			this.semLoaEnd = semLoaEnd;
			this.importDate = importDate;
			this.startDate = startDate;
			this.createdBy = createdBy;
			this.lastUpdateOn = lastUpdateOn;
			this.createdOn = createdOn;
			this.personId = personId;
			this.studentAidFlag = studentAidFlag;
			this.academicLevel = academicLevel;
			this.studentKey = studentKey;
			this.classLoad = classLoad;
			this.campusCodeKey = campusCodeKey;
			this.semesterCode = semesterCode;
			this.yearTerm = yearTerm;
			this.dormLocation = dormLocation;
		}
        
        /**
         * Copy constructor.
         * @param copy contains entity that is being copied.
         */
        public Student(Student copy) {
        	this(
        			copy.getRegistrationStatus(),
        			copy.getLastUpdateBy(),
        			copy.getImportFrom(),
        			copy.getGraduatingFlag(),
        			copy.getSemLoaStart(),
        			copy.getSemLoaEnd(),
        			copy.getImportDate(),
        			copy.getStartDate(),
        			copy.getCreatedBy(),
        			copy.getLastUpdateOn(),
        			copy.getCreatedOn(),
        			copy.getPersonId(),
        			copy.getStudentAidFlag(),
        			copy.getAcademicLevel(),
        			copy.getStudentKey(),
        			copy.getClassLoad(),
        			copy.getCampusCodeKey(),
        			copy.getSemesterCode(),
        			copy.getYearTerm(),
        			copy.getDormLocation()
        	);
        }
    	
		/**
         * Constructor
         */
        public Student() {
            super();
        }

        /**
         * @return the registrationStatus
         */
        public String getRegistrationStatus() {
                return registrationStatus;
        }

        /**
         * @param registrationStatus the registrationStatus to set.
         */
        public void setRegistrationStatus(String registrationStatus) {
                this.registrationStatus = registrationStatus;
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
         * @return the graduatingFlag
         */
        public String getGraduatingFlag() {
                return graduatingFlag;
        }

        /**
         * @param graduatingFlag the graduatingFlag to set.
         */
        public void setGraduatingFlag(String graduatingFlag) {
                this.graduatingFlag = graduatingFlag;
        }

        /**
         * @return the semLoaStart
         */
        public String getSemLoaStart() {
                return semLoaStart;
        }

        /**
         * @param semLoaStart the semLoaStart to set.
         */
        public void setSemLoaStart(String semLoaStart) {
                this.semLoaStart = semLoaStart;
        }

        /**
         * @return the semLoaEnd
         */
        public String getSemLoaEnd() {
                return semLoaEnd;
        }

        /**
         * @param semLoaEnd the semLoaEnd to set.
         */
        public void setSemLoaEnd(String semLoaEnd) {
                this.semLoaEnd = semLoaEnd;
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
         * @return the studentAidFlag
         */
        public String getStudentAidFlag() {
                return studentAidFlag;
        }

        /**
         * @param studentAidFlag the studentAidFlag to set.
         */
        public void setStudentAidFlag(String studentAidFlag) {
                this.studentAidFlag = studentAidFlag;
        }

        /**
         * @return the academicLevel
         */
        public String getAcademicLevel() {
                return academicLevel;
        }

        /**
         * @param academicLevel the academicLevel to set.
         */
        public void setAcademicLevel(String academicLevel) {
                this.academicLevel = academicLevel;
        }

        /**
         * @return the studentKey
         */
        public Long getStudentKey() {
                return studentKey;
        }

        /**
         * @param studentKey the studentKey to set.
         */
        public void setStudentKey(Long studentKey) {
                this.studentKey = studentKey;
        }

        /**
         * @return the classLoad
         */
        public String getClassLoad() {
                return classLoad;
        }

        /**
         * @param classLoad the classLoad to set.
         */
        public void setClassLoad(String classLoad) {
                this.classLoad = classLoad;
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
        public void setCampusCodeKey(Long campusCodeKey) {
                this.campusCodeKey = campusCodeKey;
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
         * @return the yearTerm
         */
        public String getYearTerm() {
                return yearTerm;
        }

        /**
         * @param yearTerm the yearTerm to set.
         */
        public void setYearTerm(String yearTerm) {
                this.yearTerm = yearTerm;
        }

        /**
         * @return the dormLocation
         */
        public String getDormLocation() {
                return dormLocation;
        }

        /**
         * @param dormLocation the dormLocation to set.
         */
        public void setDormLocation(String dormLocation) {
                this.dormLocation = dormLocation;
        }

}
