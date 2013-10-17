/* SVN FILE: $Id: ChangeNotificationTypes.java 7325 2013-05-20 12:54:54Z llg5 $ */
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
 * @version $Rev: 7325 $
 * @lastrevision $Date: 2013-05-20 08:54:54 -0400 (Mon, 20 May 2013) $
 */

@Entity
@Table(name="change_notification_types")
public class ChangeNotificationTypes implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the notificationType. */
        @Column(name="notification_type", nullable=false, length=60)
        private String notificationType;

        /** Contains the changeNotificationTypesKey. */
        @Id
        @Column(name="change_notification_types_key", nullable=false)
        private Long changeNotificationTypesKey;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the notificationDesc. */
        @Column(name="notification_desc", nullable=false, length=100)
        private String notificationDesc;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the activeFlag. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /**
         * Constructor
         */
        public ChangeNotificationTypes() {
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
         * @return the notificationDesc
         */
        public String getNotificationDesc() {
                return notificationDesc;
        }

        /**
         * @param notificationDesc the notificationDesc to set.
         */
        public void setNotificationDesc(String notificationDesc) {
                this.notificationDesc = notificationDesc;
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

}
