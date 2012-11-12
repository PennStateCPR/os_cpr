/* SVN FILE: $Id: UseridServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.UseridReturn;

/**
 * This class is returned as a result of calling the GetUserid service.  It contains the the
 * standard service return information, the statusCode and statusMessage along with a array
 * of userid information and a count of a number of records.
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
public class UseridServiceReturn {

	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/** Status message returned as the result of executing this service. */
	private String statusMessage;
	
	/** Contains an array of userid returns.  Only has a value as the result of executing a get. */
	private UseridReturn useridReturnRecord[] = null;
	
	/** Contains the number of elements in the array. */
	private int numberElements = 0;
	
	/**
	 * Constructor
	 */
	public UseridServiceReturn() {
		super();
	}
	
	/**
	 * @param statusCode contains the status code as the result of executing this service.
	 * @param statusMessage contains the status message associated with executing this service.
	 * @param useridReturnArray contains an array of userid records.
	 * @param numberElements contains the number of elements in the array.
	 */
	public UseridServiceReturn(int statusCode, String statusMessage,  
			UseridReturn[] useridReturnArray, int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (useridReturnArray != null) {
			this.useridReturnRecord = Arrays.copyOf(useridReturnArray, useridReturnArray.length);
		}
		else {
			this.useridReturnRecord = null;
		}
		this.numberElements = numberElements;
	}

	/**
	 * Constructor
	 * @param statusCode contains the status code as the result of executing this service.
	 * @param statusMessage contains the status message that is the result of executing this service.
	 */
	public UseridServiceReturn(int statusCode, String statusMessage) {
		this(statusCode, statusMessage,  null, 0);
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
	 * @return the useridReturnRecord
	 */
	public UseridReturn[] getUseridReturnRecord() {
		return useridReturnRecord;
	}
	
	/**
	 * @param useridReturnArray the useridReturnRecord to set
	 */
	public void setUseridReturnRecord(UseridReturn[] useridReturnArray) {
		if (useridReturnArray != null) {
			this.useridReturnRecord = Arrays.copyOf(useridReturnArray, useridReturnArray.length);
		}
		else {
			this.useridReturnRecord = null;
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
	 * @return a string value
	 */
	public String toString() {
		return "";
	}
}
