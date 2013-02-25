/* SVN FILE: $Id: MatchReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
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
 * 
 */
package edu.psu.iam.cpr.core.service.returns;
/**
 * This class is returned from the Person and FindUser services to the caller containing the results of finding a match.
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
 * @package edu.psu.iam.cpr.core.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class MatchReturn {

	/** the person identifier associated with a match. */
	private Long personId;
	
	/** the psu ID associated with a match. */
	private String psuId;

	/** the user ID associated with a match. */
	private String userId;
	
	/** the match score */
	private Long score;
	
	/**
	 * Constructor.
	 */
	public MatchReturn() {
		super();
	}

	/**
	 * Constructor.
	 * @param personId contains the person identifier from the CPR.
	 * @param score contains the match score.
	 */
	public MatchReturn(Long personId, Long score) {
		super();
		this.personId = personId;
		this.score = score;
	}
	
	/**
	 * Constructor.
	 * @param personId contains the person identifier from the CPR.
	 * @param score contains the match score.
	 * @param psuId the PSU ID from the CPR
	 */
	public MatchReturn(Long personId, Long score, String psuId) {
		super();
		this.personId = personId;
		this.score = score;
		this.psuId = psuId;
	}

	/**
	 * Constructor.
	 * @param personId contains the person identifier from the CPR.
	 * @param score contains the match score.
	 * @param psuId the PSU ID from the CPR
	 * @param userId The primary User ID from the CPR.
	 */
	public MatchReturn(Long personId, Long score, String psuId, String userId) {
		super();
		this.personId = personId;
		this.score = score;
		this.psuId = psuId;
		this.userId = userId;
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
	 * @return the PSU ID
	 */
	public String getPsuId() {
		return psuId;
	}

	/**
	 * @param psuId the PSU ID to set
	 */
	public void setPsuId(String psuId) {
		this.psuId = psuId;
	}

	/**
	 * @return the primary user ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the primary user ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
