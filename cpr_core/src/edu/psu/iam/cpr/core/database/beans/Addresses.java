/* SVN FILE: $Id: Addresses.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

@Entity
@Table(name="addresses")
public class Addresses implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the addressKey. */
        @Id
        @Column(name="address_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_addresses")
        @SequenceGenerator(name="seq_addresses", sequenceName="seq_addresses", allocationSize = 1, initialValue= 1)
        private Long addressKey;

        /** Contains the state. */
        @Column(name="state", nullable=true, length=2)
        private String state;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the verifiedFlag. */
        @Column(name="verified_flag", nullable=false, length=1)
        private String verifiedFlag;

        /** Contains the validateAttemptedOn. */
        @Column(name="validate_attempted_on", nullable=true)
        private Date validateAttemptedOn;

        /** Contains the address2. */
        @Column(name="address2", nullable=true, length=60)
        private String address2;

        /** Contains the address3. */
        @Column(name="address3", nullable=true, length=60)
        private String address3;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the address1. */
        @Column(name="address1", nullable=true, length=60)
        private String address1;

        /** Contains a flag to indicate if the address should be verified.  The valid values are Y or N.  The default value is N. */
        @Column(name="do_not_verify_flag", nullable=false, length=1)
        private String doNotVerifyFlag;

        /** Contains the campusCodeKey. */
        @Column(name="campus_code_key", nullable=true)
        private Long campusCodeKey;

        /** Contains the addressMatchCode. */
        @Column(name="address_match_code", nullable=true, length=60)
        private String addressMatchCode;

        /** Contains the groupId. */
        @Column(name="group_id", nullable=false)
        private Long groupId;

        /** Contains the cityMatchCode. */
        @Column(name="city_match_code", nullable=true, length=60)
        private String cityMatchCode;

        /** Contains the primaryFlag. */
        @Column(name="primary_flag", nullable=false, length=1)
        private String primaryFlag;

        /** Contains the dataTypeKey. */
        @Column(name="data_type_key", nullable=false)
        private Long dataTypeKey;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the documentTypeKey. */
        @Column(name="document_type_key", nullable=true)
        private Long documentTypeKey;

        /** Contains the countryKey. */
        @Column(name="country_key", nullable=true)
        private Long countryKey;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the province. */
        @Column(name="province", nullable=true, length=40)
        private String province;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the city. */
        @Column(name="city", nullable=true, length=40)
        private String city;

        /** Contains the postalCode. */
        @Column(name="postal_code", nullable=true, length=20)
        private String postalCode;
        
        /** null */
        @Column(name="import_from", nullable=true, length=30)
        private String importFrom;

        /** null */
        @Column(name="import_date", nullable=true)
        private Date importDate;

        /**
         * Constructor
         */
        public Addresses() {
            super();
        }

        /**
         * @return the addressKey
         */
        public Long getAddressKey() {
                return addressKey;
        }

        /**
         * @param addressKey the addressKey to set.
         */
        public void setAddressKey(Long addressKey) {
                this.addressKey = addressKey;
        }

        /**
         * @return the state
         */
        public String getState() {
                return state;
        }

        /**
         * @param state the state to set.
         */
        public void setState(String state) {
                this.state = state;
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
         * @return the verifiedFlag
         */
        public String getVerifiedFlag() {
                return verifiedFlag;
        }

        /**
         * @param verifiedFlag the verifiedFlag to set.
         */
        public void setVerifiedFlag(String verifiedFlag) {
                this.verifiedFlag = verifiedFlag;
        }

        /**
         * @return the validateAttemptedOn
         */
        public Date getValidateAttemptedOn() {
                return validateAttemptedOn;
        }

        /**
         * @param validateAttemptedOn the validateAttemptedOn to set.
         */
        public void setValidateAttemptedOn(Date validateAttemptedOn) {
                this.validateAttemptedOn = validateAttemptedOn;
        }

        /**
         * @return the address2
         */
        public String getAddress2() {
                return address2;
        }

        /**
         * @param address2 the address2 to set.
         */
        public void setAddress2(String address2) {
                this.address2 = address2;
        }

        /**
         * @return the address3
         */
        public String getAddress3() {
                return address3;
        }

        /**
         * @param address3 the address3 to set.
         */
        public void setAddress3(String address3) {
                this.address3 = address3;
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
         * @return the address1
         */
        public String getAddress1() {
                return address1;
        }

        /**
         * @param address1 the address1 to set.
         */
        public void setAddress1(String address1) {
                this.address1 = address1;
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
         * @return the addressMatchCode
         */
        public String getAddressMatchCode() {
                return addressMatchCode;
        }

        /**
         * @param addressMatchCode the addressMatchCode to set.
         */
        public void setAddressMatchCode(String addressMatchCode) {
                this.addressMatchCode = addressMatchCode;
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
         * @return the cityMatchCode
         */
        public String getCityMatchCode() {
                return cityMatchCode;
        }

        /**
         * @param cityMatchCode the cityMatchCode to set.
         */
        public void setCityMatchCode(String cityMatchCode) {
                this.cityMatchCode = cityMatchCode;
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
         * @return the documentTypeKey
         */
        public Long getDocumentTypeKey() {
                return documentTypeKey;
        }

        /**
         * @param documentTypeKey the documentTypeKey to set.
         */
        public void setDocumentTypeKey(Long documentTypeKey) {
                this.documentTypeKey = documentTypeKey;
        }

        /**
         * @return the countryKey
         */
        public Long getCountryKey() {
                return countryKey;
        }

        /**
         * @param countryKey the countryKey to set.
         */
        public void setCountryKey(Long countryKey) {
                this.countryKey = countryKey;
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
         * @return the province
         */
        public String getProvince() {
                return province;
        }

        /**
         * @param province the province to set.
         */
        public void setProvince(String province) {
                this.province = province;
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
         * @return the city
         */
        public String getCity() {
                return city;
        }

        /**
         * @param city the city to set.
         */
        public void setCity(String city) {
                this.city = city;
        }

        /**
         * @return the postalCode
         */
        public String getPostalCode() {
                return postalCode;
        }

        /**
         * @param postalCode the postalCode to set.
         */
        public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
        }

		/**
		 * @param doNotVerifyFlag the doNotVerifyFlag to set
		 */
		public void setDoNotVerifyFlag(String doNotVerifyFlag) {
			this.doNotVerifyFlag = doNotVerifyFlag;
		}

		/**
		 * @return the doNotVerifyFlag
		 */
		public String getDoNotVerifyFlag() {
			return doNotVerifyFlag;
		}

		public String getImportFrom() {
			return importFrom;
		}

		public void setImportFrom(String importFrom) {
			this.importFrom = importFrom;
		}

		public Date getImportDate() {
			return importDate;
		}

		public void setImportDate(Date importDate) {
			this.importDate = importDate;
		}

}
