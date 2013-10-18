/* SVN FILE: $Id: Phones.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="phones")
public class Phones implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the groupId. */
        @Column(name="group_id", nullable=false)
        private Long groupId;

        /** Contains the primaryFlag. */
        @Column(name="primary_flag", nullable=false, length=1)
        private String primaryFlag;

        /** Contains the dataTypeKey. */
        @Column(name="data_type_key", nullable=false)
        private Long dataTypeKey;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the phoneKey. */
        @Id
        @Column(name="phone_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_phones")
        @SequenceGenerator(name="seq_phones", sequenceName="seq_phones", allocationSize = 1, initialValue= 1)
        private Long phoneKey;

        /** Contains the phoneNumber. */
        @Column(name="phone_number", nullable=false, length=40)
        private String phoneNumber;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the internationalNumberFlag. */
        @Column(name="international_number_flag", nullable=true, length=1)
        private String internationalNumberFlag;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the extension. */
        @Column(name="extension", nullable=true, length=40)
        private String extension;

        /** null */
        @Column(name="import_from", nullable=true, length=30)
        private String importFrom;

        /** null */
        @Column(name="import_date", nullable=true)
        private Date importDate;

        /**
         * Constructor
         */
        public Phones() {
            super();
        }

        /**
         * @return the groupId
         */
        public Long getGroupId() {
                return groupId;
        }

        /**
         * @param groupId the groupId to set.
         */
        public void setGroupId(Long groupId) {
                this.groupId = groupId;
        }

        /**
         * @return the primaryFlag
         */
        public String getPrimaryFlag() {
                return primaryFlag;
        }

        /**
         * @param primaryFlag the primaryFlag to set.
         */
        public void setPrimaryFlag(String primaryFlag) {
                this.primaryFlag = primaryFlag;
        }

        /**
         * @return the dataTypeKey
         */
        public Long getDataTypeKey() {
                return dataTypeKey;
        }

        /**
         * @param dataTypeKey the dataTypeKey to set.
         */
        public void setDataTypeKey(Long dataTypeKey) {
                this.dataTypeKey = dataTypeKey;
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
         * @return the phoneKey
         */
        public Long getPhoneKey() {
                return phoneKey;
        }

        /**
         * @param phoneKey the phoneKey to set.
         */
        public void setPhoneKey(Long phoneKey) {
                this.phoneKey = phoneKey;
        }

        /**
         * @return the phoneNumber
         */
        public String getPhoneNumber() {
                return phoneNumber;
        }

        /**
         * @param phoneNumber the phoneNumber to set.
         */
        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
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
         * @return the internationalNumberFlag
         */
        public String getInternationalNumberFlag() {
                return internationalNumberFlag;
        }

        /**
         * @param internationalNumberFlag the internationalNumberFlag to set.
         */
        public void setInternationalNumberFlag(String internationalNumberFlag) {
                this.internationalNumberFlag = internationalNumberFlag;
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
         * @return the extension
         */
        public String getExtension() {
                return extension;
        }

        /**
         * @param extension the extension to set.
         */
        public void setExtension(String extension) {
                this.extension = extension;
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
