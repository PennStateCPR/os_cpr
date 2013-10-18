/* SVN FILE: $Id: Semesters.java 7368 2013-05-23 20:11:49Z llg5 $ */
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
@Table(name="semesters")
public class Semesters implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Description of the Semester type (Fall 98) */
        @Column(name="semester_desc", nullable=false, length=20)
        private String semesterDesc;

        /** Start date of the semester */
        @Column(name="sem_start_date", nullable=false)
        private Date semStartDate;

        /** End date of the semester */
        @Column(name="sem_end_date", nullable=false)
        private Date semEndDate;

        /** Uniquely identifies a semester. */
        @Id
        @Column(name="semester_code", nullable=false, length=8)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_semesters")
        @SequenceGenerator(name="seq_semesters", sequenceName="seq_semesters", allocationSize = 1, initialValue= 1)
        private String semesterCode;

        /**
         * Constructor
         */
        public Semesters() {
            super();
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
         * @return the semesterDesc
         */
        public String getSemesterDesc() {
                return semesterDesc;
        }

        /**
         * @param semesterDesc the semesterDesc to set.
         */
        public void setSemesterDesc(String semesterDesc) {
                this.semesterDesc = semesterDesc;
        }

        /**
         * @return the semStartDate
         */
        public Date getSemStartDate() {
                return semStartDate;
        }

        /**
         * @param semStartDate the semStartDate to set.
         */
        public void setSemStartDate(Date semStartDate) {
                this.semStartDate = semStartDate;
        }

        /**
         * @return the semEndDate
         */
        public Date getSemEndDate() {
                return semEndDate;
        }

        /**
         * @param semEndDate the semEndDate to set.
         */
        public void setSemEndDate(Date semEndDate) {
                this.semEndDate = semEndDate;
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

}
