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
@Table(name="mc_warning_notifications")
public class McWarningNotifications implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the messageConsumerKey. */
        @Column(name="message_consumer_key", nullable=false)
        private Long messageConsumerKey;

        /** Contains the entryTimestamp. */
        @Column(name="entry_timestamp", nullable=false)
        private Date entryTimestamp;

        /** Contains the eventTriggerKey. */
        @Column(name="event_trigger_key", nullable=false)
        private Long eventTriggerKey;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the mcWarningNotificationKey. */
        @Id
        @Column(name="mc_warning_notification_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_mc_warning_notifications")
        @SequenceGenerator(name="seq_mc_warning_notifications", sequenceName="seq_mc_warning_notifications", allocationSize = 1, initialValue= 1)
        private Long mcWarningNotificationKey;

        /**
         * Constructor
         */
        public McWarningNotifications() {
            super();
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
         * @return the entryTimestamp
         */
        public Date getEntryTimestamp() {
                return entryTimestamp;
        }

        /**
         * @param entryTimestamp the entryTimestamp to set.
         */
        public void setEntryTimestamp(Date entryTimestamp) {
                this.entryTimestamp = entryTimestamp;
        }

        /**
         * @return the eventTriggerKey
         */
        public Long getEventTriggerKey() {
                return eventTriggerKey;
        }

        /**
         * @param eventTriggerKey the eventTriggerKey to set.
         */
        public void setEventTriggerKey(Long eventTriggerKey) {
                this.eventTriggerKey = eventTriggerKey;
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
         * @return the mcWarningNotificationKey
         */
        public Long getMcWarningNotificationKey() {
                return mcWarningNotificationKey;
        }

        /**
         * @param mcWarningNotificationKey the mcWarningNotificationKey to set.
         */
        public void setMcWarningNotificationKey(Long mcWarningNotificationKey) {
                this.mcWarningNotificationKey = mcWarningNotificationKey;
        }

}
