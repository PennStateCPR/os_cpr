/* SVN FILE: $Id: MatchResultsTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

/**
 * This class provides an implementation for interfacing with the match_results database
 * table.
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
 * @package edu.psu.iam.cpr.core.database.tables
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class MatchResultsTable implements Comparable<MatchResultsTable> {
	
	/** the identifier for a collection of records that constitute a near match set */
	private Long matchSetKey;
	
	/** the person identifier who a match result exists for */
	private Long personId;
	
	/** the matching score */
	private Long score;
	
	/**
	 * Constructor.
	 */
	public MatchResultsTable() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param matchSetKey contains the match set identifier.
	 * @param personId contains the person identifier from the set.
	 * @param score contains the ranking score.
	 */
	public MatchResultsTable(Long matchSetKey, Long personId, Long score) {
		super();
		this.matchSetKey = matchSetKey;
		this.personId = personId;
		this.score = score;
	}

	/**
	 * @return the matchSetKey
	 */
	public Long getMatchSetKey() {
		return matchSetKey;
	}

	/**
	 * @param matchSetKey the matchSetKey to set
	 */
	public void setMatchSetKey(Long matchSetKey) {
		this.matchSetKey = matchSetKey;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the score
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Long score) {
		this.score = score;
	}
	
	/**
	 * Compare two MatchResultsTables on the basis of match score.
	 * @param mrt contains the match results table with the match scores in it.
	 * @return the results of the compare.
	 */
	@Override
	public int compareTo(MatchResultsTable mrt) {
		
		final Long o1Score = this.getScore();
		final Long o2Score = mrt.getScore();
	
		if (o1Score.equals(o2Score)) {
			return 0;
		} 
		else if (o1Score < o2Score) {
			return -1;
		} 
		else {
			// (o1Score > o2Score) 
			return 1;
		}
	}
	
	@Override
	/**
	 * @param an object to do compares against.
	 * @return a boolean value whether the objects are equal or not.
	 */
	public boolean equals(Object obj) {
	
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof MatchResultsTable)) {
			return false;
		}
		
		final MatchResultsTable mrt = (MatchResultsTable) obj;
		
		if (this.matchSetKey.equals(mrt.matchSetKey) && 
		    this.personId.equals(mrt.personId) && 
		    this.score.equals(mrt.score)) {
			return true;
		}
		
		return false;
	
	}
	
	@Override 
	public int hashCode() {
		return super.hashCode();
	}
}
