package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.PersonLinkageReturn;

/**
 * This class provides the implementation for the Get Person Linkage API.
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
public class PersonLinkageServiceReturn {

	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/**  Status message returned as the result of executing this service. */
	private String statusMessage;

	/** Contains the number of elements in the return array. */
	private int numberElements;
	
	/** Contains the array of elements returned for a "get". */
	private PersonLinkageReturn[] personLinkageReturn;
	
	/**
	 * Constructor. 
	 */
	public PersonLinkageServiceReturn() {
		super();
	}
	
	/**
	 * Constructor
	 * @param statusCode
	 * @param statusMessage
	 * @param numberElements
	 * @param personLinkageArray
	 */
	public PersonLinkageServiceReturn(int statusCode, String statusMessage,
			int numberElements, PersonLinkageReturn[] personLinkageArray) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.numberElements = numberElements;
		if (personLinkageArray != null) {
			this.personLinkageReturn = Arrays.copyOf(personLinkageArray, personLinkageArray.length);
		}
		else {
			this.personLinkageReturn = null;
		}
	}

	/**
	 * Constructor
	 * @param statusCode
	 * @param statusMessage
	 */
	public PersonLinkageServiceReturn(int statusCode, String statusMessage) {
		this(statusCode, statusMessage, 0, null);
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
	 * @return the personLinkageReturn
	 */
	public PersonLinkageReturn[] getPersonLinkageReturn() {
		return personLinkageReturn;
	}

	/**
	 * @param personLinkageArray the personLinkageReturn to set
	 */
	public void setPersonLinkageReturn(final PersonLinkageReturn[] personLinkageArray) {
		if (personLinkageArray != null) {
			this.personLinkageReturn = Arrays.copyOf(personLinkageArray, personLinkageArray.length);
		}
		else {
			this.personLinkageReturn = null;
		}
	}
	
	/**
	 * @return a string value.
	 */
	public String toString() {
		return "";
	}
	

}
