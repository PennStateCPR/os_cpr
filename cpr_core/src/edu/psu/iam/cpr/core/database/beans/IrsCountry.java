/* SVN FILE: $Id$ */
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
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */

@Entity
@Table(name="irs_country")
public class IrsCountry implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the effective date for the record.  */
        @Column(name="start_date", nullable=true)
        private Date startDate;

        /** Mapping to ISO country code. */
        @Column(name="country_key", nullable=false)
        private Long countryKey;

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

        /** Two character IRS country code. */
        @Column(name="irs_country_code", nullable=false, length=2)
        private String irsCountryCode;

        /** Contains the expiration date for the record.  If the record is active, then this date is set to NULL. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains a unique number that identifies an IRS country code.  It is populated by the seq_irs_country sequence. */
        @Id
        @Column(name="irs_country_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_irs_country")
        @SequenceGenerator(name="seq_irs_country", sequenceName="seq_irs_country", allocationSize = 1, initialValue= 1)
        private Long irsCountryKey;

        /**
         * Constructor
         */
        public IrsCountry() {
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
         * @return the irsCountryCode
         */
        public String getIrsCountryCode() {
                return irsCountryCode;
        }

        /**
         * @param irsCountryCode the irsCountryCode to set.
         */
        public void setIrsCountryCode(String irsCountryCode) {
                this.irsCountryCode = irsCountryCode;
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
         * @return the irsCountryKey
         */
        public Long getIrsCountryKey() {
                return irsCountryKey;
        }

        /**
         * @param irsCountryKey the irsCountryKey to set.
         */
        public void setIrsCountryKey(Long irsCountryKey) {
                this.irsCountryKey = irsCountryKey;
        }

}
