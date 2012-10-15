/* SVN FILE: $Id: SecurityQuestionAnswers.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

@Entity
@Table(name="security_question_answers")
public class SecurityQuestionAnswers implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the secQuestAnswerKey. */
        @Id
        @Column(name="sec_quest_answer_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_security_question_answers")
        @SequenceGenerator(name="seq_security_question_answers", sequenceName="seq_security_question_answers", allocationSize = 1, initialValue= 1)
        private Long secQuestAnswerKey;

        /** Contains the secQuestGroupKey. */
        @Column(name="sec_quest_group_key", nullable=false)
        private Long secQuestGroupKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the secQuestKey. */
        @Column(name="sec_quest_key", nullable=false)
        private Long secQuestKey;

        /** Contains the answer. */
        @Column(name="answer", nullable=false, length=100)
        private String answer;

        /** Contains the userid. */
        @Column(name="userid", nullable=false, length=30)
        private String userid;

        /**
         * Constructor
         */
        public SecurityQuestionAnswers() {
            super();
        }

        /**
         * @return the secQuestAnswerKey
         */
        public Long getSecQuestAnswerKey() {
                return secQuestAnswerKey;
        }

        /**
         * @param secQuestAnswerKey the secQuestAnswerKey to set.
         */
        public void setSecQuestAnswerKey(Long secQuestAnswerKey) {
                this.secQuestAnswerKey = secQuestAnswerKey;
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
         * @return the personId
         */
        public Long getPersonId() {
                return personId;
        }

        /**
         * @param personId the personId to set.
         */
        public void setPersonId(Long personId) {
                this.personId = personId;
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

        /**
         * @return the answer
         */
        public String getAnswer() {
                return answer;
        }

        /**
         * @param answer the answer to set.
         */
        public void setAnswer(String answer) {
                this.answer = answer;
        }

        /**
         * @return the userid
         */
        public String getUserid() {
                return userid;
        }

        /**
         * @param userid the userid to set.
         */
        public void setUserid(String userid) {
                this.userid = userid;
        }

}
