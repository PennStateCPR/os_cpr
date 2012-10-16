/* SVN FILE: $Id: CredentialServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.returns;

import edu.psu.iam.cpr.core.service.returns.CredentialReturn;


/**
 * This class provides the implementation of the Credential Service Return.
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
 */
public class CredentialServiceReturn {

	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;
	
	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;
	
	/** Contains the names return information, this will only be populated during a GetNames service call. */
	private CredentialReturn credentialReturnRecord[] = null;
	
	/** Contains the number of elements in the namesReturnRecord array. */
	private int numberElements = 0;
	
	/**
	 * Constructor
	 */
	public CredentialServiceReturn() {
		super();
	}
	
	/**
	 * Constructor
	 * @param statusCode contains the status code that is the result of executing this service.
	 * @param statusMessage contains the status message that is the result of executing this service.
	 * @param credentialReturnRecord contains an array of credential return objects.
	 * @param numberElements contains the number of elements in the array.
	 */
	public CredentialServiceReturn(int statusCode, String statusMessage,
			CredentialReturn[] credentialReturnRecord, int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.credentialReturnRecord = credentialReturnRecord;
		this.numberElements = numberElements;
	}

	/**
	 * Constructor
	 * @param statusCode contains the status code that is the result of executing this service.
	 * @param statusMessage contains the status message that is result of executing this service.
	 */
	public CredentialServiceReturn(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
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
	 * @return the credentialReturnRecord
	 */
	public CredentialReturn[] getCredentialReturnRecord() {
		return credentialReturnRecord;
	}

	/**
	 * @param credentialReturnRecord the credentialReturnRecord to set
	 */
	public void setCredentialReturnRecord(CredentialReturn[] credentialReturnRecord) {
		this.credentialReturnRecord = credentialReturnRecord;
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

}
