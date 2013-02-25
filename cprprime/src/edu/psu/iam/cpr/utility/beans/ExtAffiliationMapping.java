/* SVN FILE: $Id: ExtAffiliationMapping.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

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
 * @author $Author: jvuccolo $
 * @version $Rev: 5096 $
 * @lastrevision $Date: 2012-09-13 14:30:09 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="ext_affiliation_mapping")
public class ExtAffiliationMapping implements Serializable {

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

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the affiliationKey. */
        @Column(name="affiliation_key", nullable=false)
        private Long affiliationKey;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the extAffiliationMappingKey. */
        @Id
        @Column(name="ext_affiliation_mapping_key", nullable=false)
        private Long extAffiliationMappingKey;

        /** Contains the extAffiliationKey. */
        @Column(name="ext_affiliation_key", nullable=false)
        private Long extAffiliationKey;

        /**
         * Constructor
         */
        public ExtAffiliationMapping() {
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
         * @return the affiliationKey
         */
        public Long getAffiliationKey() {
                return affiliationKey;
        }

        /**
         * @param affiliationKey the affiliationKey to set.
         */
        public void setAffiliationKey(Long affiliationKey) {
                this.affiliationKey = affiliationKey;
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
         * @return the extAffiliationMappingKey
         */
        public Long getExtAffiliationMappingKey() {
                return extAffiliationMappingKey;
        }

        /**
         * @param extAffiliationMappingKey the extAffiliationMappingKey to set.
         */
        public void setExtAffiliationMappingKey(Long extAffiliationMappingKey) {
                this.extAffiliationMappingKey = extAffiliationMappingKey;
        }

        /**
         * @return the extAffiliationKey
         */
        public Long getExtAffiliationKey() {
                return extAffiliationKey;
        }

        /**
         * @param extAffiliationKey the extAffiliationKey to set.
         */
        public void setExtAffiliationKey(Long extAffiliationKey) {
                this.extAffiliationKey = extAffiliationKey;
        }

}
