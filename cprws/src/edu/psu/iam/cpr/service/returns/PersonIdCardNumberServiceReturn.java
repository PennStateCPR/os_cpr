/* SVN FILE: $Id: PersonIdCardNumberServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.returns;


import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn;

/**
* This class provides the implementation of the IdCardNumber Service Return.
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

public class PersonIdCardNumberServiceReturn {

	

	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;
	
	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;
	
	/** Contains the id card return information, this will only be populated during a GetIdCard service call. */
	private PersonIdCardNumberReturn idCardNumberReturnRecord[] = null;
	
	/** Contains the number of elements in the idCardsReturnRecord array. */
	private int numberIdCardNumberElements = 0;

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
	 * @return the idCardNumberReturnRecord
	 */
	public PersonIdCardNumberReturn[] getIdCardNumberReturnRecord() {
		return idCardNumberReturnRecord;
	}

	/**
	 * @param idCardNumberReturnArray the idCardNumberReturnRecord to set
	 */
	public void setIdCardNumberReturnRecord(
			PersonIdCardNumberReturn[] idCardNumberReturnArray) {
		this.idCardNumberReturnRecord = Arrays.copyOf(idCardNumberReturnArray, idCardNumberReturnArray.length);
	}

	/**
	 * @return the numberIdCardNumberElements
	 */
	public int getNumberIdCardNumberElements() {
		return numberIdCardNumberElements;
	}

	/**
	 * @param numberIdCardNumberElements the numberIdCardNumberElements to set
	 */
	public void setNumberIdCardNumberElements(int numberIdCardNumberElements) {
		this.numberIdCardNumberElements = numberIdCardNumberElements;
	}

	/**
	 * 
	 */
	public PersonIdCardNumberServiceReturn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param statusCode
	 * @param statusMessage
	 */
	public PersonIdCardNumberServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * @param statusCode
	 * @param statusMessage
	 * @param idCardNumberReturnArray
	 * @param numberIdCardNumberElements
	 */
	public PersonIdCardNumberServiceReturn(int statusCode, String statusMessage,
			PersonIdCardNumberReturn[] idCardNumberReturnArray,
			int numberIdCardNumberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.idCardNumberReturnRecord = Arrays.copyOf(idCardNumberReturnArray, idCardNumberReturnArray.length);
		this.numberIdCardNumberElements = numberIdCardNumberElements;
	}
 
}
