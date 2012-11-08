/* SVN FILE: $Id: UserCommentServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
/**
 * 
 */
package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.UserCommentReturn;

/**
 * This class is returned as a result of calling the GetUserComment service.  It contains the the
 * standard service return information, the statusCode and statusMessage along with a array
 * of user comment information and a count of a number of records.
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
	private UserCommentReturn userCommentReturnRecord[] = null;

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
		this.userCommentReturnRecord = Arrays.copyOf(userCommentReturnArray, userCommentReturnArray.length);
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
	public void setUserCommentReturnRecord(
			UserCommentReturn[] userCommentReturnArray) {
		this.userCommentReturnRecord = Arrays.copyOf(userCommentReturnArray, userCommentReturnArray.length);
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
