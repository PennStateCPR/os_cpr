/* SVN FILE: $Id: AddressStaging.java 7360 2013-05-22 18:37:04Z llg5 $ */
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
 * @version $Rev: 7360 $
 * @lastrevision $Date: 2013-05-22 14:37:04 -0400 (Wed, 22 May 2013) $
 */

@Entity
@Table(name="address_staging")
public class AddressStaging implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** null */
        @Column(name="city_match_code", nullable=true, length=60)
        private String cityMatchCode;

        /** null */
        @Column(name="state", nullable=true, length=2)
        private String state;

        /** null */
        @Id
        @Column(name="address_staging_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_address_staging")
        @SequenceGenerator(name="seq_address_staging", sequenceName="seq_address_staging", allocationSize = 1, initialValue= 1)
        private Long addressStagingKey;

        /** null */
        @Column(name="data_type_key", nullable=true)
        private Long dataTypeKey;

        /** null */
        @Column(name="import_from", nullable=true, length=30)
        private String importFrom;

        /** null */
        @Column(name="import_date", nullable=true)
        private Date importDate;

        /** null */
        @Column(name="address2", nullable=true, length=100)
        private String address2;

        /** null */
        @Column(name="country_key", nullable=true)
        private Long countryKey;

        /** null */
        @Column(name="address3", nullable=true, length=100)
        private String address3;

        /** null */
        @Column(name="address1", nullable=true, length=100)
        private String address1;

        /** null */
        @Column(name="country_code_three", nullable=true, length=3)
        private String countryCodeThree;

        /** null */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** null */
        @Column(name="province", nullable=true, length=40)
        private String province;

        /** null */
        @Column(name="city", nullable=true, length=40)
        private String city;

        /** null */
        @Column(name="postal_code", nullable=true, length=20)
        private String postalCode;

        /** null */
        @Column(name="campus_code_key", nullable=true)
        private Long campusCodeKey;

        /** null */
        @Column(name="country_code_two", nullable=true, length=2)
        private String countryCodeTwo;

        /** null */
        @Column(name="address_match_code", nullable=true, length=60)
        private String addressMatchCode;

        /** null */
        @Column(name="campus_code", nullable=true, length=2)
        private String campusCode;

        /**
         * Constructor
         */
        public AddressStaging() {
            super();
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
         * @return the addressStagingKey
         */
        public Long getAddressStagingKey() {
                return addressStagingKey;
        }

        /**
         * @param addressStagingKey the addressStagingKey to set.
         */
        public void setAddressStagingKey(Long addressStagingKey) {
                this.addressStagingKey = addressStagingKey;
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
         * @return the countryCodeThree
         */
        public String getCountryCodeThree() {
                return countryCodeThree;
        }

        /**
         * @param countryCodeThree the countryCodeThree to set.
         */
        public void setCountryCodeThree(String countryCodeThree) {
                this.countryCodeThree = countryCodeThree;
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
         * @return the countryCodeTwo
         */
        public String getCountryCodeTwo() {
                return countryCodeTwo;
        }

        /**
         * @param countryCodeTwo the countryCodeTwo to set.
         */
        public void setCountryCodeTwo(String countryCodeTwo) {
                this.countryCodeTwo = countryCodeTwo;
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
         * @return the campusCode
         */
        public String getCampusCode() {
                return campusCode;
        }

        /**
         * @param campusCode the campusCode to set.
         */
        public void setCampusCode(String campusCode) {
                this.campusCode = campusCode;
        }

}
