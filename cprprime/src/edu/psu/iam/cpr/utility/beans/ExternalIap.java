/* SVN FILE: $Id: ExternalIap.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
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
@Table(name="external_iap")
public class ExternalIap implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the externalIap. */
        @Column(name="external_iap", nullable=false, length=30)
        private String externalIap;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the federationKey. */
        @Column(name="federation_key", nullable=false)
        private Long federationKey;

        /** Contains the activeFlag. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the externalIapDesc. */
        @Column(name="external_iap_desc", nullable=false, length=150)
        private String externalIapDesc;

        /** Contains the externalIapUrl. */
        @Column(name="external_iap_url", nullable=true, length=200)
        private String externalIapUrl;

        /** Contains the externalIapKey. */
        @Id
        @Column(name="external_iap_key", nullable=false)
        private Long externalIapKey;

        /**
         * Constructor
         */
        public ExternalIap() {
            super();
        }

        /**
         * @return the externalIap
         */
        public String getExternalIap() {
                return externalIap;
        }

        /**
         * @param externalIap the externalIap to set.
         */
        public void setExternalIap(String externalIap) {
                this.externalIap = externalIap;
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
         * @return the federationKey
         */
        public Long getFederationKey() {
                return federationKey;
        }

        /**
         * @param federationKey the federationKey to set.
         */
        public void setFederationKey(Long federationKey) {
                this.federationKey = federationKey;
        }

        /**
         * @return the activeFlag
         */
        public String getActiveFlag() {
                return activeFlag;
        }

        /**
         * @param activeFlag the activeFlag to set.
         */
        public void setActiveFlag(String activeFlag) {
                this.activeFlag = activeFlag;
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
         * @return the externalIapDesc
         */
        public String getExternalIapDesc() {
                return externalIapDesc;
        }

        /**
         * @param externalIapDesc the externalIapDesc to set.
         */
        public void setExternalIapDesc(String externalIapDesc) {
                this.externalIapDesc = externalIapDesc;
        }

        /**
         * @return the externalIapUrl
         */
        public String getExternalIapUrl() {
                return externalIapUrl;
        }

        /**
         * @param externalIapUrl the externalIapUrl to set.
         */
        public void setExternalIapUrl(String externalIapUrl) {
                this.externalIapUrl = externalIapUrl;
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

}
