/* SVN FILE: $Id: Affiliations.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
 * @version $Rev: 5084 $
 * @lastrevision $Date: 2012-09-13 10:49:56 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="affiliations")
public class Affiliations implements Serializable {

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

        /** Contains the affiliation. */
        @Column(name="affiliation", nullable=false, length=40)
        private String affiliation;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the affiliationDesc. */
        @Column(name="affiliation_desc", nullable=false, length=200)
        private String affiliationDesc;

        /** Contains the affiliationKey. */
        @Id
        @Column(name="affiliation_key", nullable=false)
        private Long affiliationKey;

        /** Contains the parentAffiliationKey. */
        @Column(name="parent_affiliation_key", nullable=true)
        private Long parentAffiliationKey;

        /** Contains the canAssignFlag. */
        @Column(name="can_assign_flag", nullable=false, length=1)
        private String canAssignFlag;

        /** Contains the enumString. */
        @Column(name="enum_string", nullable=true, length=200)
        private String enumString;

        /**
         * Constructor
         */
        public Affiliations() {
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
         * @return the affiliation
         */
        public String getAffiliation() {
                return affiliation;
        }

        /**
         * @param affiliation the affiliation to set.
         */
        public void setAffiliation(String affiliation) {
                this.affiliation = affiliation;
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
         * @return the affiliationDesc
         */
        public String getAffiliationDesc() {
                return affiliationDesc;
        }

        /**
         * @param affiliationDesc the affiliationDesc to set.
         */
        public void setAffiliationDesc(String affiliationDesc) {
                this.affiliationDesc = affiliationDesc;
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
         * @return the parentAffiliationKey
         */
        public Long getParentAffiliationKey() {
                return parentAffiliationKey;
        }

        /**
         * @param parentAffiliationKey the parentAffiliationKey to set.
         */
        public void setParentAffiliationKey(Long parentAffiliationKey) {
                this.parentAffiliationKey = parentAffiliationKey;
        }

        /**
         * @return the canAssignFlag
         */
        public String getCanAssignFlag() {
                return canAssignFlag;
        }

        /**
         * @param canAssignFlag the canAssignFlag to set.
         */
        public void setCanAssignFlag(String canAssignFlag) {
                this.canAssignFlag = canAssignFlag;
        }

        /**
         * @return the enumString
         */
        public String getEnumString() {
                return enumString;
        }

        /**
         * @param enumString the enumString to set.
         */
        public void setEnumString(String enumString) {
                this.enumString = enumString;
        }

}
