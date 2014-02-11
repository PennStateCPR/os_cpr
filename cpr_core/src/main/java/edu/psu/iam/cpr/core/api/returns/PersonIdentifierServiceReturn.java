package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.PersonIdentifierReturn;

/**
 * This class provides the implementation for the Get Person Identifier API.
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
public class PersonIdentifierServiceReturn {

	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;
	
	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;

	/** Contains the array of person identifier return values. */
	private PersonIdentifierReturn[] personIdentifierReturn;
	
	/** Contains the number of elements in the array above. */
	private int numberOfElements;

	/**
	 * Default Constructor.
	 */
	public PersonIdentifierServiceReturn() {
		super();
	}

	/**
	 * Constructor.
	 * @param statusCode contains the status code that is the result of executing the service.
	 * @param statusMessage contains the message associated with the status code.
	 * @param personIdentifierArray contains the array of results.
	 * @param numberOfElements contains the number of elements in the array.
	 */
	public PersonIdentifierServiceReturn(int statusCode, String statusMessage,
			PersonIdentifierReturn[] personIdentifierArray,
			int numberOfElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (personIdentifierArray != null) {
			this.personIdentifierReturn = Arrays.copyOf(personIdentifierArray, personIdentifierArray.length);
		}
		else {
			this.personIdentifierReturn = null;
		}
		this.numberOfElements = numberOfElements;
	}

	/**
	 * Constructor
	 * @param statusCode contains the result of executing this service.
	 * @param statusMessage conatins the status message.
	 */
	public PersonIdentifierServiceReturn(int statusCode, String statusMessage) {
		this(statusCode, statusMessage, null, 0);
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
	 * @return the personIdentifierReturn
	 */
	public PersonIdentifierReturn[] getPersonIdentifierReturn() {
		return personIdentifierReturn;
	}

	/**
	 * @param personIdentifierArray the personIdentifierReturn to set
	 */
	public void setPersonIdentifierReturn(
			PersonIdentifierReturn[] personIdentifierArray) {
		if (personIdentifierArray != null) {
			this.personIdentifierReturn = Arrays.copyOf(personIdentifierArray, personIdentifierArray.length);
		}
		else {
			this.personIdentifierReturn = null;
		}
	}

	/**
	 * @return the numberOfElements
	 */
	public int getNumberOfElements() {
		return numberOfElements;
	}

	/**
	 * @param numberOfElements the numberOfElements to set
	 */
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	
}
