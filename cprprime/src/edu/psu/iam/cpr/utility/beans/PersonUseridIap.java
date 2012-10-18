/* SVN FILE: $Id: PersonUseridIap.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="person_userid_iap")
public class PersonUseridIap implements Serializable {

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

        /** Contains the personUseridIapKey. */
        @Id
        @Column(name="person_userid_iap_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_person_userid_iap")
        @SequenceGenerator(name="seq_person_userid_iap", sequenceName="seq_person_userid_iap", allocationSize = 1, initialValue= 1)
        private Long personUseridIapKey;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the iapKey. */
        @Column(name="iap_key", nullable=false)
        private Long iapKey;

        /** Contains the userid. */
        @Column(name="userid", nullable=false, length=30)
        private String userid;

        /**
         * Constructor
         */
        public PersonUseridIap() {
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
         * @return the personUseridIapKey
         */
        public Long getPersonUseridIapKey() {
                return personUseridIapKey;
        }

        /**
         * @param personUseridIapKey the personUseridIapKey to set.
         */
        public void setPersonUseridIapKey(Long personUseridIapKey) {
                this.personUseridIapKey = personUseridIapKey;
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

        /**
         * @return the userid
         */
        public String getUserid() {
                return userid;
        }

        /**
         * @param userid the userid to set.
         */
        public void setUserid(String userid) {
                this.userid = userid;
        }

}
