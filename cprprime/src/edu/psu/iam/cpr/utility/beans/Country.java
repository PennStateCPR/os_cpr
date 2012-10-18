/* SVN FILE: $Id: Country.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5096 $
 * @lastrevision $Date: 2012-09-13 14:30:09 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="country")
public class Country implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the countryFullName. */
        @Column(name="country_full_name", nullable=false, length=100)
        private String countryFullName;

        /** Contains the remarks. */
        @Lob
        @Column(name="remarks", nullable=true)
        private String remarks;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the country. */
        @Column(name="country", nullable=false, length=60)
        private String country;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the usTerritoryFlag. */
        @Column(name="us_territory_flag", nullable=false, length=1)
        private String usTerritoryFlag;

        /** Contains the countryNumericCode. */
        @Column(name="country_numeric_code", nullable=true, length=3)
        private String countryNumericCode;

        /** Contains the countryKey. */
        @Id
        @Column(name="country_key", nullable=false)
        private Long countryKey;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=true)
        private Date startDate;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the countryCodeThree. */
        @Column(name="country_code_three", nullable=false, length=3)
        private String countryCodeThree;

        /** Contains the countryCodeFour. */
        @Column(name="country_code_four", nullable=true, length=4)
        private String countryCodeFour;

        /** Contains the countryCodeTwo. */
        @Column(name="country_code_two", nullable=false, length=2)
        private String countryCodeTwo;

        /**
         * Constructor
         */
        public Country() {
            super();
        }

        /**
         * @return the countryFullName
         */
        public String getCountryFullName() {
                return countryFullName;
        }

        /**
         * @param countryFullName the countryFullName to set.
         */
        public void setCountryFullName(String countryFullName) {
                this.countryFullName = countryFullName;
        }

        /**
         * @return the remarks
         */
        public String getRemarks() {
                return remarks;
        }

        /**
         * @param remarks the remarks to set.
         */
        public void setRemarks(String remarks) {
                this.remarks = remarks;
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
         * @return the country
         */
        public String getCountry() {
                return country;
        }

        /**
         * @param country the country to set.
         */
        public void setCountry(String country) {
                this.country = country;
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
         * @return the usTerritoryFlag
         */
        public String getUsTerritoryFlag() {
                return usTerritoryFlag;
        }

        /**
         * @param usTerritoryFlag the usTerritoryFlag to set.
         */
        public void setUsTerritoryFlag(String usTerritoryFlag) {
                this.usTerritoryFlag = usTerritoryFlag;
        }

        /**
         * @return the countryNumericCode
         */
        public String getCountryNumericCode() {
                return countryNumericCode;
        }

        /**
         * @param countryNumericCode the countryNumericCode to set.
         */
        public void setCountryNumericCode(String countryNumericCode) {
                this.countryNumericCode = countryNumericCode;
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
         * @return the countryCodeFour
         */
        public String getCountryCodeFour() {
                return countryCodeFour;
        }

        /**
         * @param countryCodeFour the countryCodeFour to set.
         */
        public void setCountryCodeFour(String countryCodeFour) {
                this.countryCodeFour = countryCodeFour;
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

}
