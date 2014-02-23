package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.UserCommentReturn;

/**
 * This class provides the implementation for the Get User Comment API.
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
 * @package edu.psu.iam.cpr.core.api
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class UserCommentServiceReturn {

	/**
	 * Contains the status code that is the result of executing of a service.
	 */
	private int statusCode;

	/**
	 * Contains the text message associated with the execution of a service.
	 */	
	private String statusMessage;

	/**
	 * Contains the e-mail address return information, 
	 * this will only be populated during a GetUserComments and 
	 * GetUserCommentByType service calls.
	 */		
	private UserCommentReturn[] userCommentReturnRecord = null;

	/**
	 * Contains the number of elements in the emailAddressReturnRecord array.
	 */	
	private int numberElements = 0;

	
	/**
	 * Constructor
	 * @param statusCode
	 * @param statusMessage
	 * @param userCommentReturnArray
	 * @param numberElements
	 */
	public UserCommentServiceReturn(int statusCode, String statusMessage,
			UserCommentReturn[] userCommentReturnArray, int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (userCommentReturnArray != null) {
			this.userCommentReturnRecord = Arrays.copyOf(userCommentReturnArray, userCommentReturnArray.length);
		}
		else {
			this.userCommentReturnRecord = null;
		}
		this.numberElements = numberElements;
	}
	
	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public UserCommentServiceReturn(int statusCode, String statusMessage) {
		this(statusCode, statusMessage, null, 0);
	}
	
	/**
	 * Empty constructor.
	 */
	public UserCommentServiceReturn() {
		super();
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the userCommentReturnRecord
	 */
	public UserCommentReturn[] getUserCommentReturnRecord() {
		return userCommentReturnRecord;
	}

	/**
	 * @param userCommentReturnArray the userCommentReturnRecord to set
	 */
	public void setUserCommentReturnRecord(final UserCommentReturn[] userCommentReturnArray) {
		if (userCommentReturnArray != null) {
			this.userCommentReturnRecord = Arrays.copyOf(userCommentReturnArray, userCommentReturnArray.length);
		}
		else {
			this.userCommentReturnRecord = null;
		}
	}

	/**
	 * @return the numberElements
	 */
	public int getNumberElements() {
		return numberElements;
	}

	/**
	 * @param numberElements the numberElements to set
	 */
	public void setNumberElements(int numberElements) {
		this.numberElements = numberElements;
	}
	
	/**
	 * @return a string value.
	 */
	public String toString() {
		return "";
	}	
	
}
