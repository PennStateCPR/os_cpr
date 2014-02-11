package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.AddressReturn;

/**
 * This class provides the implementation of the Get Address API Return.
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
 * @package edu.psu.iam.cpr.core.api.returns
 * @author $Author: jvuccolo $
 * @version $Rev$
 * @lastrevision $Date$
 */
public class AddressServiceReturn {
	
	/**
	 * Status code that is result of executing this service.
	 */
	private int statusCode;
	
	/**
	 * Status message returned as the result of executing this service.
	 */
	private String statusMessage;
	
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
	 * @return the addressReturnRecord
	 */
	public AddressReturn[] getAddressReturnRecord() {
		return addressReturnRecord;
	}

	/**
	 * @param addressReturnArray the addressReturnArray to set
	 */
	public void setAddressReturnRecord(AddressReturn[] addressReturnArray) {
		if (addressReturnArray != null) {
			this.addressReturnRecord = Arrays.copyOf(addressReturnArray, addressReturnArray.length);
		}
		else {
			this.addressReturnRecord = null;
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
	 *  Contains an array of addressReturn records returns.
	 */
	
	private AddressReturn addressReturnRecord[] = null;
	
	/** 
	 *  Contains the number of elements in the array.
	 * 
	 */
	private int numberElements = 0;



	/**
	 *  Empty constructor.
	 */
	public AddressServiceReturn() {
		super();
	}

	
	/**
 	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public AddressServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 * @param addressReturnArray the address return record only will be populated for a GetAddress service call.
	 * @param numberElements the number of elements in the addressReturnRecord array.
	 */
	public AddressServiceReturn(int statusCode, String statusMessage,
			AddressReturn[] addressReturnArray, int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (addressReturnArray != null) {
			this.addressReturnRecord = Arrays.copyOf(addressReturnArray, addressReturnArray.length);
		}
		else {
			this.addressReturnRecord = null;
		}
		this.numberElements = numberElements;
	}
	/**
	 * @return a string value.
	 */
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode());
	}
}
