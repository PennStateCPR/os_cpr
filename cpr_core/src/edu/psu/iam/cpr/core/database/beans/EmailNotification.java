/* SVN FILE: $Id: EmailNotification.java 5659 2012-11-19 17:24:59Z slk24 $ */
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
 * @author $Author: slk24 $
 * @version $Rev: 5659 $
 * @lastrevision $Date: 2012-11-19 12:24:59 -0500 (Mon, 19 Nov 2012) $
 */

@Entity
@Table(name="email_notification")
public class EmailNotification implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the name of the notification process that sends the email. */
        @Column(name="notification_process", nullable=false, length=100)
        private String notificationProcess;

        /** Contains the text version of the body of the email. */
        @Column(name="text_body", nullable=false, length=1500)
        private String textBody;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the subject of the email. */
        @Column(name="email_subject", nullable=false, length=100)
        private String emailSubject;

        /** Contains the html version of the email body. */
        @Column(name="html_body", nullable=false, length=1500)
        private String htmlBody;

        /** Contains a unique number that identifies an email notification.  It is populated by the seq_email_notification sequence. */
        @Id
        @Column(name="mail_notification_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_email_notification")
        @SequenceGenerator(name="seq_email_notification", sequenceName="seq_email_notification", allocationSize = 1, initialValue= 1)
        private Long mailNotificationKey;

        /**
         * Constructor
         */
        public EmailNotification() {
            super();
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

}
