package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.NameReturn;

/**
 * This class provides the implementation of the Get Names API Return.
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

public class NamesServiceReturn {

	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/** Status message returned as the result of executing this service. */
	private String statusMessage;

	/** Contains the names return information, this will only be populated during a GetNames service call. */
	private NameReturn[] namesReturnRecord = null;
	
	/** Contains the number of elements in the namesReturnRecord array. */
	private int numberElements = 0;

	/**
	 * Empty constructor.
	 */
	public NamesServiceReturn() {
		super();
	}
	
	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public NamesServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 * @param namesReturnArray the names return record only will be populated for a GetNames service call.
	 * @param numberElements the number of elements in the namesReturnRecord array.
	 */
	public NamesServiceReturn(int statusCode, String statusMessage,
			NameReturn[] namesReturnArray, int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (namesReturnArray != null) {
			this.namesReturnRecord = Arrays.copyOf(namesReturnArray, namesReturnArray.length);
		}
		else {
			this.namesReturnRecord = null;
		}
		this.numberElements = numberElements;
	}
	
	/**
	 * @return the namesReturnRecord
	 */
	public NameReturn[] getNamesReturnRecord() {
		return namesReturnRecord;
	}

	/**
	 * @param namesReturnArray the namesReturnRecord to set
	 */
	public void setNamesReturnRecord(final NameReturn[] namesReturnArray) {
		if (namesReturnArray != null) {
			this.namesReturnRecord = Arrays.copyOf(namesReturnArray, namesReturnArray.length);
		}
		else {
			this.namesReturnRecord = null;
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
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @return a string value.
	 */
	public String toString() {
		return "";
	}

}
