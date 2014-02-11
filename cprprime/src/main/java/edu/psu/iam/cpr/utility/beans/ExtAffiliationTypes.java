/* SVN FILE: $Id: ExtAffiliationTypes.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
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
@Table(name="ext_affiliation_types")
public class ExtAffiliationTypes implements Serializable {

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
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the extAffiliationTypeKey. */
        @Id
        @Column(name="ext_affiliation_type_key", nullable=false)
        private Long extAffiliationTypeKey;

        /** Contains the extAffiliationType. */
        @Column(name="ext_affiliation_type", nullable=false, length=30)
        private String extAffiliationType;

        /** Contains the extAffiliationTypeDesc. */
        @Column(name="ext_affiliation_type_desc", nullable=false, length=300)
        private String extAffiliationTypeDesc;

        /**
         * Constructor
         */
        public ExtAffiliationTypes() {
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
         * @return the extAffiliationType
         */
        public String getExtAffiliationType() {
                return extAffiliationType;
        }

        /**
         * @param extAffiliationType the extAffiliationType to set.
         */
        public void setExtAffiliationType(String extAffiliationType) {
                this.extAffiliationType = extAffiliationType;
        }

        /**
         * @return the extAffiliationTypeDesc
         */
        public String getExtAffiliationTypeDesc() {
                return extAffiliationTypeDesc;
        }

        /**
         * @param extAffiliationTypeDesc the extAffiliationTypeDesc to set.
         */
        public void setExtAffiliationTypeDesc(String extAffiliationTypeDesc) {
                this.extAffiliationTypeDesc = extAffiliationTypeDesc;
        }

}
