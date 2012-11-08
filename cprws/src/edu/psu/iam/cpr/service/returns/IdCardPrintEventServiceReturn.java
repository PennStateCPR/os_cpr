/* SVN FILE: $Id: IdCardPrintEventServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn;

/**
* This class provides the implementation of the IdCard Service Return.
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

public class IdCardPrintEventServiceReturn {

	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;
	
	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;
	
	/** Contains the id card return information, this will only be populated during a GetIdCard service call. */
	private IdCardPrintLogReturn idCardPrintEventReturnRecord[] = null;
	
	/** Contains the number of elements in the idCardsReturnRecord array. */
	private int numberIdCardPrintEventElements = 0;

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
	 * @return the idCardPrintEventReturnRecord
	 */
	public IdCardPrintLogReturn[] getIdCardPrintEventReturnRecord() {
		return idCardPrintEventReturnRecord;
	}

	/**
	 * @param idCardPrintEventReturnArray the idCardPrintEventReturnRecord to set
	 */
	public void setIdCardPrintEventReturnRecord(
			IdCardPrintLogReturn[] idCardPrintEventReturnArray) {
		this.idCardPrintEventReturnRecord = Arrays.copyOf(idCardPrintEventReturnArray, idCardPrintEventReturnArray.length);
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}



	/**
	 * @return the numberIdCardPrintEventElements
	 */
	public int getNumberIdCardPrintEventElements() {
		return numberIdCardPrintEventElements;
	}

	/**
	 * @param numberIdCardPrintEventElements the numberIdCardPrintEventElements to set
	 */
	public void setNumberIdCardPrintEventElements(int numberIdCardPrintEventElements) {
		this.numberIdCardPrintEventElements = numberIdCardPrintEventElements;
	}

	
	/**
	 * Constructor 
	 */
	public IdCardPrintEventServiceReturn() {
		super();
	}

	/**
	 * Constructor
	 * @param statusCode contains the status code that is the result of executing this service.
	 * @param statusMessage contains the status message.
	 * @param idCardPrintEventReturnArray contains the query results.
	 * @param numberIdCardPrintEventElements contains the number of elements in the array.
	 */
	public IdCardPrintEventServiceReturn(int statusCode, String statusMessage,
			IdCardPrintLogReturn[] idCardPrintEventReturnArray,
			int numberIdCardPrintEventElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.idCardPrintEventReturnRecord = Arrays.copyOf(idCardPrintEventReturnArray, idCardPrintEventReturnArray.length);
		this.numberIdCardPrintEventElements = numberIdCardPrintEventElements;
	}
	
	/**
	 * Constructor
	 * @param statusCode contains the status code that is the result of executing this service.
	 * @param statusMessage contains the status message.
	 */
	public IdCardPrintEventServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
}
