/* SVN FILE: $Id: MatchResults.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="match_results")
public class MatchResults implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the score. */
        @Column(name="score", nullable=false)
        private Long score;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the matchSetKey. */
        @Id
        @Column(name="match_set_key", nullable=false)
        private Long matchSetKey;

        /**
         * Constructor
         */
        public MatchResults() {
            super();
        }

        /**
         * @return the score
         */
        public Long getScore() {
                return score;
        }

        /**
         * @param score the score to set.
         */
        public void setScore(Long score) {
                this.score = score;
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
         * @return the matchSetKey
         */
        public Long getMatchSetKey() {
                return matchSetKey;
        }

        /**
         * @param matchSetKey the matchSetKey to set.
         */
        public void setMatchSetKey(Long matchSetKey) {
                this.matchSetKey = matchSetKey;
        }

}
