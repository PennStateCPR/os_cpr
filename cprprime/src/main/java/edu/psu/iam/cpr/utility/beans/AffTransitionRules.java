/* SVN FILE: $Id: AffTransitionRules.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="aff_transition_rules")
public class AffTransitionRules implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the expireFlag. */
        @Column(name="expire_flag", nullable=false, length=1)
        private String expireFlag;

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

        /** Contains the currentAffiliationKey. */
        @Column(name="current_affiliation_key", nullable=true)
        private Long currentAffiliationKey;

        /** Contains the resultAffiliationKey. */
        @Column(name="result_affiliation_key", nullable=false)
        private Long resultAffiliationKey;

        /** Contains the affTransitionRuleKey. */
        @Id
        @Column(name="aff_transition_rule_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_aff_transition_rules")
        @SequenceGenerator(name="seq_aff_transition_rules", sequenceName="seq_aff_transition_rules", allocationSize = 1, initialValue= 1)
        private Long affTransitionRuleKey;

        /** Contains the requestAffiliationKey. */
        @Column(name="request_affiliation_key", nullable=false)
        private Long requestAffiliationKey;

        /**
         * Constructor
         */
        public AffTransitionRules() {
            super();
        }

        /**
         * @return the expireFlag
         */
        public String getExpireFlag() {
                return expireFlag;
        }

        /**
         * @param expireFlag the expireFlag to set.
         */
        public void setExpireFlag(String expireFlag) {
                this.expireFlag = expireFlag;
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
         * @return the currentAffiliationKey
         */
        public Long getCurrentAffiliationKey() {
                return currentAffiliationKey;
        }

        /**
         * @param currentAffiliationKey the currentAffiliationKey to set.
         */
        public void setCurrentAffiliationKey(Long currentAffiliationKey) {
                this.currentAffiliationKey = currentAffiliationKey;
        }

        /**
         * @return the resultAffiliationKey
         */
        public Long getResultAffiliationKey() {
                return resultAffiliationKey;
        }

        /**
         * @param resultAffiliationKey the resultAffiliationKey to set.
         */
        public void setResultAffiliationKey(Long resultAffiliationKey) {
                this.resultAffiliationKey = resultAffiliationKey;
        }

        /**
         * @return the affTransitionRuleKey
         */
        public Long getAffTransitionRuleKey() {
                return affTransitionRuleKey;
        }

        /**
         * @param affTransitionRuleKey the affTransitionRuleKey to set.
         */
        public void setAffTransitionRuleKey(Long affTransitionRuleKey) {
                this.affTransitionRuleKey = affTransitionRuleKey;
        }

        /**
         * @return the requestAffiliationKey
         */
        public Long getRequestAffiliationKey() {
                return requestAffiliationKey;
        }

        /**
         * @param requestAffiliationKey the requestAffiliationKey to set.
         */
        public void setRequestAffiliationKey(Long requestAffiliationKey) {
                this.requestAffiliationKey = requestAffiliationKey;
        }

}
