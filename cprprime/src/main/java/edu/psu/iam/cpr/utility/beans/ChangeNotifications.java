/* SVN FILE: $Id: ChangeNotifications.java 7568 2013-06-11 17:24:04Z llg5 $ */
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
 * @author $Author: llg5 $
 * @version $Rev: 7568 $
 * @lastrevision $Date: 2013-06-11 13:24:04 -0400 (Tue, 11 Jun 2013) $
 */

@Entity
@Table(name="change_notifications")
public class ChangeNotifications implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the messageConsumerKey. */
        @Column(name="message_consumer_key", nullable=false)
        private Long messageConsumerKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the changeNotificationTypesKey. */
        @Column(name="change_notification_types_key", nullable=false)
        private Long changeNotificationTypesKey;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the changeNotificationsKey. */
        @Id
        @Column(name="change_notifications_key", nullable=false)
        private Long changeNotificationsKey;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /**
         * Constructor
         */
        public ChangeNotifications() {
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
