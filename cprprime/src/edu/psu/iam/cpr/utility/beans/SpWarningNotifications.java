/* SVN FILE: $Id: SpWarningNotifications.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
 * @version $Rev: 5084 $
 * @lastrevision $Date: 2012-09-13 10:49:56 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="sp_warning_notifications")
public class SpWarningNotifications implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the serviceProvisionerKey. */
        @Column(name="service_provisioner_key", nullable=false)
        private Long serviceProvisionerKey;

        /** Contains the entryTimestamp. */
        @Column(name="entry_timestamp", nullable=false)
        private Date entryTimestamp;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the eventTriggerKey. */
        @Column(name="event_trigger_key", nullable=false)
        private Long eventTriggerKey;

        /** Contains the spWarningNotificationKey. */
        @Id
        @Column(name="sp_warning_notification_key", nullable=false)
        private Long spWarningNotificationKey;

        /**
         * Constructor
         */
        public SpWarningNotifications() {
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
         * @return the spWarningNotificationKey
         */
        public Long getSpWarningNotificationKey() {
                return spWarningNotificationKey;
        }

        /**
         * @param spWarningNotificationKey the spWarningNotificationKey to set.
         */
        public void setSpWarningNotificationKey(Long spWarningNotificationKey) {
                this.spWarningNotificationKey = spWarningNotificationKey;
        }

}
