package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.EmailAddressReturn;

/**
 * This class provides the implementation for the Get Email Address API.
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
	 * @param emailAddressReturnRecordArray the e-mail address return record only will be populated for a GetEmailAddress service call. 
	 * @param numberElements the number of elements in the emailAddressReturnRecord array.
	 */
	public EmailAddressServiceReturn(int statusCode, String statusMessage,
			EmailAddressReturn[] emailAddressReturnRecordArray,
			int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (emailAddressReturnRecordArray != null) {
			this.emailAddressReturnRecord = Arrays.copyOf(emailAddressReturnRecordArray, emailAddressReturnRecordArray.length);
		}
		else {
			this.emailAddressReturnRecord = null;
		}
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
	 * @param emailAddressReturnRecordArray the emailAddressReturnRecord to set
	 */
	public void setEmailAddressReturnRecord(
			EmailAddressReturn[] emailAddressReturnRecordArray) {
		if (emailAddressReturnRecordArray != null) {
			this.emailAddressReturnRecord = Arrays.copyOf(emailAddressReturnRecordArray, emailAddressReturnRecordArray.length);
		}
		else {
			this.emailAddressReturnRecord = null;
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
