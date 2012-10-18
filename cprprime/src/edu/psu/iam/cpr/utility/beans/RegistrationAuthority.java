/* SVN FILE: $Id: RegistrationAuthority.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
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
@Table(name="registration_authority")
public class RegistrationAuthority implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the registrationAuthority. */
        @Column(name="registration_authority", nullable=false, length=60)
        private String registrationAuthority;

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

        /** Contains the registrationAuthorityKey. */
        @Id
        @Column(name="registration_authority_key", nullable=false)
        private Long registrationAuthorityKey;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the raContactKey. */
        @Column(name="ra_contact_key", nullable=false)
        private Long raContactKey;

        /** Contains the registrationAuthorityDesc. */
        @Column(name="registration_authority_desc", nullable=false, length=100)
        private String registrationAuthorityDesc;

        /** Contains the suspendFlag. */
        @Column(name="suspend_flag", nullable=false, length=1)
        private String suspendFlag;

        /**
         * Constructor
         */
        public RegistrationAuthority() {
            super();
        }

        /**
         * @return the registrationAuthority
         */
        public String getRegistrationAuthority() {
                return registrationAuthority;
        }

        /**
         * @param registrationAuthority the registrationAuthority to set.
         */
        public void setRegistrationAuthority(String registrationAuthority) {
                this.registrationAuthority = registrationAuthority;
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
         * @return the registrationAuthorityKey
         */
        public Long getRegistrationAuthorityKey() {
                return registrationAuthorityKey;
        }

        /**
         * @param registrationAuthorityKey the registrationAuthorityKey to set.
         */
        public void setRegistrationAuthorityKey(Long registrationAuthorityKey) {
                this.registrationAuthorityKey = registrationAuthorityKey;
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
         * @return the raContactKey
         */
        public Long getRaContactKey() {
                return raContactKey;
        }

        /**
         * @param raContactKey the raContactKey to set.
         */
        public void setRaContactKey(Long raContactKey) {
                this.raContactKey = raContactKey;
        }

        /**
         * @return the registrationAuthorityDesc
         */
        public String getRegistrationAuthorityDesc() {
                return registrationAuthorityDesc;
        }

        /**
         * @param registrationAuthorityDesc the registrationAuthorityDesc to set.
         */
        public void setRegistrationAuthorityDesc(String registrationAuthorityDesc) {
                this.registrationAuthorityDesc = registrationAuthorityDesc;
        }

        /**
         * @return the suspendFlag
         */
        public String getSuspendFlag() {
                return suspendFlag;
        }

        /**
         * @param suspendFlag the suspendFlag to set.
         */
        public void setSuspendFlag(String suspendFlag) {
                this.suspendFlag = suspendFlag;
        }

}
