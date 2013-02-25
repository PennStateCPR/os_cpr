/* SVN FILE: $Id: EventTriggers.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="event_triggers")
public class EventTriggers implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the activeFlag. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the eventTrigger. */
        @Column(name="event_trigger", nullable=false, length=30)
        private String eventTrigger;

        /** Contains the enumString. */
        @Column(name="enum_string", nullable=false, length=30)
        private String enumString;

        /** Contains the eventTriggerKey. */
        @Id
        @Column(name="event_trigger_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_event_triggers")
        @SequenceGenerator(name="seq_event_triggers", sequenceName="seq_event_triggers", allocationSize = 1, initialValue= 1)
        private Long eventTriggerKey;

        /**
         * Constructor
         */
        public EventTriggers() {
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
         * @return the activeFlag
         */
        public String getActiveFlag() {
                return activeFlag;
        }

        /**
         * @param activeFlag the activeFlag to set.
         */
        public void setActiveFlag(String activeFlag) {
                this.activeFlag = activeFlag;
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
         * @return the eventTrigger
         */
        public String getEventTrigger() {
                return eventTrigger;
        }

        /**
         * @param eventTrigger the eventTrigger to set.
         */
        public void setEventTrigger(String eventTrigger) {
                this.eventTrigger = eventTrigger;
        }

        /**
         * @return the enumString
         */
        public String getEnumString() {
                return enumString;
        }

        /**
         * @param enumString the enumString to set.
         */
        public void setEnumString(String enumString) {
                this.enumString = enumString;
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

}
