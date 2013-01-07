/* SVN FILE: $Id: BuildBean.java 5970 2013-01-04 15:50:31Z jvuccolo $ */
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
 * @version $Rev: 5970 $
 * @lastrevision $Date: 2013-01-04 10:50:31 -0500 (Fri, 04 Jan 2013) $
 */

@Entity
@Table(name="email_notification")
public class EmailNotification implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the htmlBody. */
        @Column(name="html_body", nullable=false, length=1500)
        private String htmlBody;

        /** Contains the notificationProcess. */
        @Column(name="notification_process", nullable=false, length=100)
        private String notificationProcess;

        /** Contains the mailNotificationKey. */
        @Id
        @Column(name="mail_notification_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_email_notification")
        @SequenceGenerator(name="seq_email_notification", sequenceName="seq_email_notification", allocationSize = 1, initialValue= 1)
        private Long mailNotificationKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the textBody. */
        @Column(name="text_body", nullable=false, length=1500)
        private String textBody;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the emailSubject. */
        @Column(name="email_subject", nullable=false, length=100)
        private String emailSubject;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /**
         * Constructor
         */
        public EmailNotification() {
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
         * @return the htmlBody
         */
        public String getHtmlBody() {
                return htmlBody;
        }

        /**
         * @param htmlBody the htmlBody to set.
         */
        public void setHtmlBody(String htmlBody) {
                this.htmlBody = htmlBody;
        }

        /**
         * @return the notificationProcess
         */
        public String getNotificationProcess() {
                return notificationProcess;
        }

        /**
         * @param notificationProcess the notificationProcess to set.
         */
        public void setNotificationProcess(String notificationProcess) {
                this.notificationProcess = notificationProcess;
        }

        /**
         * @return the mailNotificationKey
         */
        public Long getMailNotificationKey() {
                return mailNotificationKey;
        }

        /**
         * @param mailNotificationKey the mailNotificationKey to set.
         */
        public void setMailNotificationKey(Long mailNotificationKey) {
                this.mailNotificationKey = mailNotificationKey;
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
         * @return the textBody
         */
        public String getTextBody() {
                return textBody;
        }

        /**
         * @param textBody the textBody to set.
         */
        public void setTextBody(String textBody) {
                this.textBody = textBody;
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
         * @return the emailSubject
         */
        public String getEmailSubject() {
                return emailSubject;
        }

        /**
         * @param emailSubject the emailSubject to set.
         */
        public void setEmailSubject(String emailSubject) {
                this.emailSubject = emailSubject;
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
