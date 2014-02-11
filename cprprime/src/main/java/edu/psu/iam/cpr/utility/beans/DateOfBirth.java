/* SVN FILE: $Id: DateOfBirth.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

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
 * @author $Author: jvuccolo $
 * @version $Rev: 5084 $
 * @lastrevision $Date: 2012-09-13 10:49:56 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="date_of_birth")
public class DateOfBirth implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the dobChar. */
        @Column(name="dob_char", nullable=true, length=8)
        private String dobChar;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the dob. */
        @Column(name="dob", nullable=true)
        private Date dob;

        /** Contains the dateOfBirthKey. */
        @Id
        @Column(name="date_of_birth_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_date_of_birth")
        @SequenceGenerator(name="seq_date_of_birth", sequenceName="seq_date_of_birth", allocationSize = 1, initialValue= 1)
        private Long dateOfBirthKey;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** null */
        @Column(name="import_from", nullable=true, length=30)
        private String importFrom;

        /** null */
        @Column(name="import_date", nullable=true)
        private Date importDate;

        /**
         * Constructor
         */
        public DateOfBirth() {
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
         * @return the dobChar
         */
        public String getDobChar() {
                return dobChar;
        }

        /**
         * @param dobChar the dobChar to set.
         */
        public void setDobChar(String dobChar) {
                this.dobChar = dobChar;
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
         * @return the dob
         */
        public Date getDob() {
                return dob;
        }

        /**
         * @param dob the dob to set.
         */
        public void setDob(Date dob) {
                this.dob = dob;
        }

        /**
         * @return the dateOfBirthKey
         */
        public Long getDateOfBirthKey() {
                return dateOfBirthKey;
        }

        /**
         * @param dateOfBirthKey the dateOfBirthKey to set.
         */
        public void setDateOfBirthKey(Long dateOfBirthKey) {
                this.dateOfBirthKey = dateOfBirthKey;
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
		 * @return the importFrom
		 */
		public String getImportFrom() {
			return importFrom;
		}

		/**
		 * @param importFrom the importFrom to set
		 */
		public void setImportFrom(String importFrom) {
			this.importFrom = importFrom;
		}

		/**
		 * @return the importDate
		 */
		public Date getImportDate() {
			return importDate;
		}

		/**
		 * @param importDate the importDate to set
		 */
		public void setImportDate(Date importDate) {
			this.importDate = importDate;
		}

}
