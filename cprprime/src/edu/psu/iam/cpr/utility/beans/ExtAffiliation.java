/* SVN FILE: $Id: ExtAffiliation.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
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
@Table(name="ext_affiliation")
public class ExtAffiliation implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the activeFlag. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=true, length=30)
        private String lastUpdateBy;

        /** Contains the extAffiliationTypeKey. */
        @Column(name="ext_affiliation_type_key", nullable=false)
        private Long extAffiliationTypeKey;

        /** Contains the extAffiliation. */
        @Column(name="ext_affiliation", nullable=false, length=30)
        private String extAffiliation;

        /** Contains the extAffiliationDesc. */
        @Column(name="ext_affiliation_desc", nullable=false, length=150)
        private String extAffiliationDesc;

        /** Contains the extAffiliationKey. */
        @Id
        @Column(name="ext_affiliation_key", nullable=false)
        private Long extAffiliationKey;

        /**
         * Constructor
         */
        public ExtAffiliation() {
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
         * @return the extAffiliationTypeKey
         */
        public Long getExtAffiliationTypeKey() {
                return extAffiliationTypeKey;
        }

        /**
         * @param extAffiliationTypeKey the extAffiliationTypeKey to set.
         */
        public void setExtAffiliationTypeKey(Long extAffiliationTypeKey) {
                this.extAffiliationTypeKey = extAffiliationTypeKey;
        }

        /**
         * @return the extAffiliation
         */
        public String getExtAffiliation() {
                return extAffiliation;
        }

        /**
         * @param extAffiliation the extAffiliation to set.
         */
        public void setExtAffiliation(String extAffiliation) {
                this.extAffiliation = extAffiliation;
        }

        /**
         * @return the extAffiliationDesc
         */
        public String getExtAffiliationDesc() {
                return extAffiliationDesc;
        }

        /**
         * @param extAffiliationDesc the extAffiliationDesc to set.
         */
        public void setExtAffiliationDesc(String extAffiliationDesc) {
                this.extAffiliationDesc = extAffiliationDesc;
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
