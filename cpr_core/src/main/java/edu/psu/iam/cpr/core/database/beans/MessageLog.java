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
 * @version $Rev: 6451 $
 * @lastrevision $Date: 2013-03-08 11:49:27 -0500 (Fri, 08 Mar 2013) $
 */

@Entity
@Table(name="message_log")
public class MessageLog implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the successFlag. */
        @Column(name="success_flag", nullable=true, length=1)
        private String successFlag;

        /** Contains the serviceKey. */
        @Column(name="service_key", nullable=false)
        private Long serviceKey;

        /** Contains the messageConsumerKey. */
        @Column(name="message_consumer_key", nullable=false)
        private Long messageConsumerKey;

        /** Contains the messageLogKey. */
        @Id
        @Column(name="message_log_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_message_log")
        @SequenceGenerator(name="seq_message_log", sequenceName="seq_message_log", allocationSize = 1, initialValue= 1)
        private Long messageLogKey;

        /** Contains the webServiceKey. */
        @Column(name="web_service_key", nullable=false)
        private Long webServiceKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the requestUserid. */
        @Column(name="request_userid", nullable=false, length=30)
        private String requestUserid;

        /** Contains the numberOfTries. */
        @Column(name="number_of_tries", nullable=false)
        private Long numberOfTries;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the messageSent. */
        @Column(name="message_sent", nullable=true, length=1000)
        private String messageSent;

        /**
         * Constructor
         */
        public MessageLog() {
            super();
        }

        /**
         * @return the successFlag
         */
        public String getSuccessFlag() {
                return successFlag;
        }

        /**
         * @param successFlag the successFlag to set.
         */
        public void setSuccessFlag(String successFlag) {
                this.successFlag = successFlag;
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
         * @return the messageLogKey
         */
        public Long getMessageLogKey() {
                return messageLogKey;
        }

        /**
         * @param messageLogKey the messageLogKey to set.
         */
        public void setMessageLogKey(Long messageLogKey) {
                this.messageLogKey = messageLogKey;
        }

        /**
         * @return the webServiceKey
         */
        public Long getWebServiceKey() {
                return webServiceKey;
        }

        /**
         * @param webServiceKey the webServiceKey to set.
         */
        public void setWebServiceKey(Long webServiceKey) {
                this.webServiceKey = webServiceKey;
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
         * @return the requestUserid
         */
        public String getRequestUserid() {
                return requestUserid;
        }

        /**
         * @param requestUserid the requestUserid to set.
         */
        public void setRequestUserid(String requestUserid) {
                this.requestUserid = requestUserid;
        }

        /**
         * @return the numberOfTries
         */
        public Long getNumberOfTries() {
                return numberOfTries;
        }

        /**
         * @param numberOfTries the numberOfTries to set.
         */
        public void setNumberOfTries(Long numberOfTries) {
                this.numberOfTries = numberOfTries;
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
         * @return the messageSent
         */
        public String getMessageSent() {
                return messageSent;
        }

        /**
         * @param messageSent the messageSent to set.
         */
        public void setMessageSent(String messageSent) {
                this.messageSent = messageSent;
        }

}