/* SVN FILE: $Id: PersonIapVp.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="person_iap_vp")
public class PersonIapVp implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the groupMemberKey. */
        @Column(name="group_member_key", nullable=false)
        private Long groupMemberKey;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the registrationAuthorityKey. */
        @Column(name="registration_authority_key", nullable=false)
        private Long registrationAuthorityKey;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the personIapVpKey. */
        @Id
        @Column(name="person_iap_vp_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_person_iap_vp")
        @SequenceGenerator(name="seq_person_iap_vp", sequenceName="seq_person_iap_vp", allocationSize = 1, initialValue= 1)
        private Long personIapVpKey;

        /** Contains the vpDate. */
        @Column(name="vp_date", nullable=false)
        private Date vpDate;

        /** Contains the iapKey. */
        @Column(name="iap_key", nullable=false)
        private Long iapKey;

        /** Contains the userid. */
        @Column(name="userid", nullable=false, length=30)
        private String userid;

        /**
         * Constructor
         */
        public PersonIapVp() {
            super();
        }

        /**
         * @return the groupMemberKey
         */
        public Long getGroupMemberKey() {
                return groupMemberKey;
        }

        /**
         * @param groupMemberKey the groupMemberKey to set.
         */
        public void setGroupMemberKey(Long groupMemberKey) {
                this.groupMemberKey = groupMemberKey;
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
         * @return the personIapVpKey
         */
        public Long getPersonIapVpKey() {
                return personIapVpKey;
        }

        /**
         * @param personIapVpKey the personIapVpKey to set.
         */
        public void setPersonIapVpKey(Long personIapVpKey) {
                this.personIapVpKey = personIapVpKey;
        }

        /**
         * @return the vpDate
         */
        public Date getVpDate() {
                return vpDate;
        }

        /**
         * @param vpDate the vpDate to set.
         */
        public void setVpDate(Date vpDate) {
                this.vpDate = vpDate;
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
