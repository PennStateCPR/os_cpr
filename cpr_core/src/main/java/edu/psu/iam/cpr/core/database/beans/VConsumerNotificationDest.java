/* SVN FILE: $Id: VConsumerNotificationDest.java 6848 2013-04-12 18:41:30Z slk24 $ */
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
 * @author $Author: slk24 $
 * @version $Rev: 6848 $
 * @lastrevision $Date: 2013-04-12 14:41:30 -0400 (Fri, 12 Apr 2013) $
 */

@Entity
@Table(name="v_consumer_notification_dest")
public class VConsumerNotificationDest implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the change notification types key */
        @Id
        @Column(name="change_notification_types_key", nullable=false)
        private Long changeNotificationTypesKey;

        /** Contains the name of the consumer destination queue */
        @Column(name="consumer_destination", nullable=true, length=128)
        private String consumerDestination;

        /** Contains the change notification key */
        @Column(name="change_notifications_key", nullable=false)
        private Long changeNotificationsKey;

        /** Contains the notification type */
        @Column(name="notification_type", nullable=false, length=60)
        private String notificationType;

        /** Contains the message consumer key */
        @Column(name="message_consumer_key", nullable=false)
        private Long messageConsumerKey;

        /**
         * Constructor
         */
        public VConsumerNotificationDest() {
            super();
        }

        /**
         * @return the changeNotificationTypesKey
         */
        public Long getChangeNotificationTypesKey() {
                return changeNotificationTypesKey;
        }

        /**
         * @param changeNotificationTypesKey the changeNotificationTypesKey to set.
         */
        public void setChangeNotificationTypesKey(Long changeNotificationTypesKey) {
                this.changeNotificationTypesKey = changeNotificationTypesKey;
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
         * @return the changeNotificationsKey
         */
        public Long getChangeNotificationsKey() {
                return changeNotificationsKey;
        }

        /**
         * @param changeNotificationsKey the changeNotificationsKey to set.
         */
        public void setChangeNotificationsKey(Long changeNotificationsKey) {
                this.changeNotificationsKey = changeNotificationsKey;
        }

        /**
         * @return the notificationType
         */
        public String getNotificationType() {
                return notificationType;
        }

        /**
         * @param notificationType the notificationType to set.
         */
        public void setNotificationType(String notificationType) {
                this.notificationType = notificationType;
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

}