package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.PhoneReturn;

/**
 * This class provides the implementation for the Add Address API.
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
public class PhoneServiceReturn {
	
	/** 
	 * Status code that is result of executing this service.
	 */
	private int statusCode;
	
	/** 
	 *  Status message returned as the result of executing this service.
	 */
	
	private String statusMessage;
	
	/** 
	 * Contains an array of phoneReturn recordsreturns.
	 */
	private PhoneReturn[] phoneReturnRecord = null;
	
	/** 
	 *  Contains the number of elements in the array.
	 */
	private int numberElements = 0;
	
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
	 * @return the phoneReturnRecord
	 */
	public PhoneReturn[] getPhoneReturnRecord() {
		return phoneReturnRecord;
	}

	/**
	 * @param phoneArray the phoneReturnRecord to set
	 */
	public void setPhoneReturnRecord(PhoneReturn[] phoneArray) {
		if (phoneArray != null) {
			this.phoneReturnRecord = Arrays.copyOf(phoneArray, phoneArray.length);
		}
		else {
			this.phoneReturnRecord = null;
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
	 * Empty Constructor
	 */
	public PhoneServiceReturn() {
		super();
	
	}

	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public PhoneServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.numberElements = 0;
	}

	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 * @param phoneArray the phone return record only will be populated for the GetPhone service call.
	 * @param numberElements the number of elements in the phoneReturnRecord array.
	 */
	public PhoneServiceReturn(int statusCode, String statusMessage,
			PhoneReturn[] phoneArray, int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (phoneArray != null) {
			this.phoneReturnRecord = Arrays.copyOf(phoneArray, phoneArray.length);
		}
		else {
			this.phoneReturnRecord = null;
		}
		this.numberElements = numberElements;
	}
	
}
