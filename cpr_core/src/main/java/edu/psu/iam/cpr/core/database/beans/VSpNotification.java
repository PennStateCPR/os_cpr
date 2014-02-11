/* SVN FILE: $Id: BuildBean.java 6451 2013-03-08 16:49:27Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
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
 * @version $Rev: 6451 $
 * @lastrevision $Date: 2013-03-08 11:49:27 -0500 (Fri, 08 Mar 2013) $
 */

@Entity
@Table(name="v_sp_notification")
public class VSpNotification implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the webService. */
        @Column(name="web_service", nullable=false, length=30)
        private String webService;

        /** Contains the consumerDestination. */
        @Column(name="consumer_destination", nullable=true, length=128)
        private String consumerDestination;

        /** Contains the serviceKey. */
        @Column(name="service_key", nullable=false)
        private Long serviceKey;

        /** Contains the messageConsumerKey. */
        @Id
        @Column(name="message_consumer_key", nullable=false)
        private Long messageConsumerKey;

        /** Contains the webServiceKey. */
        @Column(name="web_service_key", nullable=false)
        private Long webServiceKey;

        /** Contains the serviceName. */
        @Column(name="service_name", nullable=false, length=100)
        private String serviceName;

        /** Contains the destinationType. */
        @Column(name="destination_type", nullable=true, length=30)
        private String destinationType;

        /** Contains the consumer. */
        @Column(name="consumer", nullable=false, length=50)
        private String consumer;

        /**
         * Constructor
         */
        public VSpNotification() {
            super();
        }

        /**
         * @return the webService
         */
        public String getWebService() {
                return webService;
        }

        /**
         * @param webService the webService to set.
         */
        public void setWebService(String webService) {
                this.webService = webService;
        }

        /**
         * @return the consumerDestination
         */
        public String getConsumerDestination() {
                return consumerDestination;
        }

        /**
         * @param consumerDestination the consumerDestination to set.
         */
        public void setConsumerDestination(String consumerDestination) {
                this.consumerDestination = consumerDestination;
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
         * @return the serviceName
         */
        public String getServiceName() {
                return serviceName;
        }

        /**
         * @param serviceName the serviceName to set.
         */
        public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
        }

        /**
         * @return the destinationType
         */
        public String getDestinationType() {
                return destinationType;
        }

        /**
         * @param destinationType the destinationType to set.
         */
        public void setDestinationType(String destinationType) {
                this.destinationType = destinationType;
        }

        /**
         * @return the consumer
         */
        public String getConsumer() {
                return consumer;
        }

        /**
         * @param consumer the consumer to set.
         */
        public void setConsumer(String consumer) {
                this.consumer = consumer;
        }

}
