/* SVN FILE: $Id: MessageLog.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

@Entity
@Table(name="message_log")
public class MessageLog implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the successFlag. */
        @Column(name="success_flag", nullable=true, length=1)
        private String successFlag;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the serviceProvisionerKey. */
        @Column(name="service_provisioner_key", nullable=false)
        private Long serviceProvisionerKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the requestUserid. */
        @Column(name="request_userid", nullable=false, length=30)
        private String requestUserid;

        /** Contains the webServiceKey. */
        @Column(name="web_service_key", nullable=false)
        private Long webServiceKey;

        /** Contains the numberOfTries. */
        @Column(name="number_of_tries", nullable=false)
        private Long numberOfTries;

        /** Contains the messageSent. */
        @Column(name="message_sent", nullable=true, length=1000)
        private String messageSent;

        /** Contains the messageLogKey. */
        @Id
        @Column(name="message_log_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_message_log")
        @SequenceGenerator(name="seq_message_log", sequenceName="seq_message_log", allocationSize = 1, initialValue= 1)
        private Long messageLogKey;

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
         * @return the serviceProvisionerKey
         */
        public Long getServiceProvisionerKey() {
                return serviceProvisionerKey;
        }

        /**
         * @param serviceProvisionerKey the serviceProvisionerKey to set.
         */
        public void setServiceProvisionerKey(Long serviceProvisionerKey) {
                this.serviceProvisionerKey = serviceProvisionerKey;
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

}