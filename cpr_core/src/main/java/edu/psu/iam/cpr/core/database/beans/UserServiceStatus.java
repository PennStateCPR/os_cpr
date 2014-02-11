/* SVN FILE: $Id: BuildBean.java 5970 2013-01-04 15:50:31Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

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
 * @version $Rev: 5970 $
 * @lastrevision $Date: 2013-01-04 10:50:31 -0500 (Fri, 04 Jan 2013) $
 */

@Entity
@Table(name="user_service_status")
public class UserServiceStatus implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the provisionDate. */
        @Column(name="provision_date", nullable=false)
        private Date provisionDate;

        /** Contains the messageConsumerKey. */
        @Column(name="message_consumer_key", nullable=false)
        private Long messageConsumerKey;

        /** Contains the serviceKey. */
        @Column(name="service_key", nullable=false)
        private Long serviceKey;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the deprovisionDate. */
        @Column(name="deprovision_date", nullable=true)
        private Date deprovisionDate;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the expirationDate. */
        @Column(name="expiration_date", nullable=true)
        private Date expirationDate;

        /** Contains the userid. */
        @Column(name="userid", nullable=false, length=30)
        private String userid;

        /** Contains the userServiceStatusKey. */
        @Id
        @Column(name="user_service_status_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_user_service_status")
        @SequenceGenerator(name="seq_user_service_status", sequenceName="seq_user_service_status", allocationSize = 1, initialValue= 1)
        private Long userServiceStatusKey;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /**
         * Constructor
         */
        public UserServiceStatus() {
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
         * @return the provisionDate
         */
        public Date getProvisionDate() {
                return provisionDate;
        }

        /**
         * @param provisionDate the provisionDate to set.
         */
        public void setProvisionDate(Date provisionDate) {
                this.provisionDate = provisionDate;
        }

        /**
         * @return the messageConsumerKey
         */
        public Long getMessageConsumerKey() {
                return messageConsumerKey;
        }

        /**
         * @param messageConsumerKey the messageConsumerKey to set.
         */
        public void setMessageConsumerKey(Long messageConsumerKey) {
                this.messageConsumerKey = messageConsumerKey;
        }

        /**
         * @return the serviceKey
         */
        public Long getServiceKey() {
                return serviceKey;
        }

        /**
         * @param serviceKey the serviceKey to set.
         */
        public void setServiceKey(Long serviceKey) {
                this.serviceKey = serviceKey;
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
         * @return the deprovisionDate
         */
        public Date getDeprovisionDate() {
                return deprovisionDate;
        }

        /**
         * @param deprovisionDate the deprovisionDate to set.
         */
        public void setDeprovisionDate(Date deprovisionDate) {
                this.deprovisionDate = deprovisionDate;
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
         * @return the expirationDate
         */
        public Date getExpirationDate() {
                return expirationDate;
        }

        /**
         * @param expirationDate the expirationDate to set.
         */
        public void setExpirationDate(Date expirationDate) {
                this.expirationDate = expirationDate;
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

        /**
         * @return the userServiceStatusKey
         */
        public Long getUserServiceStatusKey() {
                return userServiceStatusKey;
        }

        /**
         * @param userServiceStatusKey the userServiceStatusKey to set.
         */
        public void setUserServiceStatusKey(Long userServiceStatusKey) {
                this.userServiceStatusKey = userServiceStatusKey;
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

}
