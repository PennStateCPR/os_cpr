/* SVN FILE: $Id: EmailAddressServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
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
 * @package edu.psu.iam.cpr.services
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 * 
 */
package edu.psu.iam.cpr.service.returns;

import edu.psu.iam.cpr.core.service.returns.EmailAddressReturn;

/**
 * This class is returned as a result of calling the GetEmailAddress service.  It contains the the
 * standard service return information, the statusCode and statusMessage along with a array
 * of email address information and a count of a number of records.
 * 
 */
public class EmailAddressServiceReturn {

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
	 * this will only be populated during a GetEmailAddress service call.
	 */		
	private EmailAddressReturn[] emailAddressReturnRecord = null;

	/**
	 * Contains the number of elements in the emailAddressReturnRecord array.
	 */	
	private int numberElements = 0;


	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 * @param emailAddressReturnRecord the e-mail address return record only will be populated for a GetEmailAddress service call. 
	 * @param numberElements the number of elements in the emailAddressReturnRecord array.
	 */
	public EmailAddressServiceReturn(int statusCode, String statusMessage,
			EmailAddressReturn[] emailAddressReturnRecord,
			int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.emailAddressReturnRecord = emailAddressReturnRecord;
		this.numberElements = numberElements;
	}
	
	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public EmailAddressServiceReturn(int statusCode, String statusMessage) {
		this(statusCode, statusMessage, null, 0);
	}
	
	/**
	 * Empty constructor.
	 */
	public EmailAddressServiceReturn() {
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
	 * @return the emailAddressReturnRecord
	 */
	public EmailAddressReturn[] getEmailAddressReturnRecord() {
		return emailAddressReturnRecord;
	}

	/**
	 * @param emailAddressReturnRecord the emailAddressReturnRecord to set
	 */
	public void setEmailAddressReturnRecord(
			EmailAddressReturn[] emailAddressReturnRecord) {
		this.emailAddressReturnRecord = emailAddressReturnRecord;
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
