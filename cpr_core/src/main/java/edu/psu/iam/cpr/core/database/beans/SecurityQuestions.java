/* SVN FILE: $Id$ */
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
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */

@Entity
@Table(name="security_questions")
public class SecurityQuestions implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the effective date for the record. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains a unique number that identifies a group of security questions.  These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Column(name="sec_quest_group_key", nullable=false)
        private Long secQuestGroupKey;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains a security question.  These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Column(name="question", nullable=false, length=100)
        private String question;

        /** Contains the expiration date for the record.  If the record is active, then this date is set to NULL. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains a unique number that identifies a security question.  It is populated by the seq_sec_quest sequence.  These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Id
        @Column(name="sec_quest_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_security_questions")
        @SequenceGenerator(name="seq_security_questions", sequenceName="seq_security_questions", allocationSize = 1, initialValue= 1)
        private Long secQuestKey;

        /**
         * Constructor
         */
        public SecurityQuestions() {
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
         * @return the secQuestGroupKey
         */
        public Long getSecQuestGroupKey() {
                return secQuestGroupKey;
        }

        /**
         * @param secQuestGroupKey the secQuestGroupKey to set.
         */
        public void setSecQuestGroupKey(Long secQuestGroupKey) {
                this.secQuestGroupKey = secQuestGroupKey;
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
         * @return the question
         */
        public String getQuestion() {
                return question;
        }

        /**
         * @param question the question to set.
         */
        public void setQuestion(String question) {
                this.question = question;
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
         * @return the secQuestKey
         */
        public Long getSecQuestKey() {
                return secQuestKey;
        }

        /**
         * @param secQuestKey the secQuestKey to set.
         */
        public void setSecQuestKey(Long secQuestKey) {
                this.secQuestKey = secQuestKey;
        }

}
