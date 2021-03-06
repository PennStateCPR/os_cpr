/* SVN FILE: $Id: IapExtMapping.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="iap_ext_mapping")
public class IapExtMapping implements Serializable {

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

        /** Contains the iapExtMappingKey. */
        @Id
        @Column(name="iap_ext_mapping_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_iap_ext_mapping")
        @SequenceGenerator(name="seq_iap_ext_mapping", sequenceName="seq_iap_ext_mapping", allocationSize = 1, initialValue= 1)
        private Long iapExtMappingKey;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the externalIapKey. */
        @Column(name="external_iap_key", nullable=false)
        private Long externalIapKey;

        /** Contains the mappingUrl. */
        @Column(name="mapping_url", nullable=true, length=200)
        private String mappingUrl;

        /** Contains the iapKey. */
        @Column(name="iap_key", nullable=false)
        private Long iapKey;

        /**
         * Constructor
         */
        public IapExtMapping() {
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
         * @return the iapExtMappingKey
         */
        public Long getIapExtMappingKey() {
                return iapExtMappingKey;
        }

        /**
         * @param iapExtMappingKey the iapExtMappingKey to set.
         */
        public void setIapExtMappingKey(Long iapExtMappingKey) {
                this.iapExtMappingKey = iapExtMappingKey;
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
         * @return the externalIapKey
         */
        public Long getExternalIapKey() {
                return externalIapKey;
        }

        /**
         * @param externalIapKey the externalIapKey to set.
         */
        public void setExternalIapKey(Long externalIapKey) {
                this.externalIapKey = externalIapKey;
        }

        /**
         * @return the mappingUrl
         */
        public String getMappingUrl() {
                return mappingUrl;
        }

        /**
         * @param mappingUrl the mappingUrl to set.
         */
        public void setMappingUrl(String mappingUrl) {
                this.mappingUrl = mappingUrl;
        }

        /**
         * @return the iapKey
         */
        public Long getIapKey() {
                return iapKey;
        }

        /**
         * @param iapKey the iapKey to set.
         */
        public void setIapKey(Long iapKey) {
                this.iapKey = iapKey;
        }

}
