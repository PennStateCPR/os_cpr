/* SVN FILE: $Id: FindPersonServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */

package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.MatchReturn;

/**
 * Implementation of the service return for the SearchForPerson service.
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
 * @package edu.psu.iam.cpr.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 *
 */

public class FindPersonServiceReturn {
	
	private int statusCode;
	
	private String statusMessage = "";
	
	/** the PSU ID number of the person that matches the input data */
	private String psuID = "";

	/** the person id of the person that matches the input data */
	private long personID = -1;
	
	private String userId = "";
	
	/** Criteria used to determine whether a match exists */
	private String matchingMethod = "";

	/** An array of near matches (up to 10) sorted by ranking score */
	private MatchReturn[] nearMatchArray;
	
	/**
	 * Constructor
	 */
	public FindPersonServiceReturn() {
		super();
	}
	
	/**
	 * @param statusCode
	 * @param statusMessage
	 */
	public FindPersonServiceReturn(int statusCode, final String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Constructor
	 * 
	 * @param statusCode
	 * @param statusMessage
	 * @param psuID
	 * @param personID
	 * @param matchingMethod
	 */
	public FindPersonServiceReturn(int statusCode, final String statusMessage, final String psuID, int personID, final String matchingMethod) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.psuID = psuID;
		this.personID = personID;
		this.matchingMethod = matchingMethod;
	}
	
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatusCode(int status) {
		this.statusCode = status;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(final String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * @return the personID
	 */
	public long getPersonID() {
		return this.personID;
	}
	
	/**
	 * @return the psuID
	 */
	public String getPsuId() {
		return this.psuID;
	}
	
	/**
	 * @return the match method
	 */
	public String getMatchingMethod() {
		return this.matchingMethod;
	}

	/**
	 * @param matchingMethod the match method
	 */
	public void setMatchingMethod(final String matchingMethod) {
		this.matchingMethod = matchingMethod;
	}
	
	/**
	 * Get the near match array size
	 * 
	 * @return the near match array size
	 */
	public int getNearMatchArraySize() {
		return this.nearMatchArray.length;
	}
	
	/**
	 * Get the near match array
	 * 
	 * @return the near match array
	 */
	public MatchReturn[] getNearMatchArray() {
		return this.nearMatchArray;
	}
	
	/**
	 * Set the near match array
	 */
	public void setNearMatchArray(final MatchReturn[] nearMatches) {
		if (nearMatches != null) {
			this.nearMatchArray = Arrays.copyOf(nearMatches, nearMatches.length);
		}
		else {
			this.nearMatchArray = null;
		}
	}
	
	/**
	 * Get the PSU ID
	 * 
	 * @return the PSU ID
	 */
	public String getPsuID() {
		return psuID;
	}

	/** 
	 * Set the PSU ID
	 * 
	 * @param psuID the PSU ID
	 */
	public void setPsuID(final String psuID) {
		this.psuID = psuID;
	}

	/**
	 * Set the Person ID
	 * 
	 * @param personID
	 */
	public void setPersonID(long personID) {
		this.personID = personID;
	}

	/**
	 * Get the User ID
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Set the User ID
	 * 
	 * @param userId the user id
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}
	
	
}
