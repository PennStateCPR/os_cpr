/* SVN FILE: $Id: MatchResults.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

import java.io.Serializable;
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
