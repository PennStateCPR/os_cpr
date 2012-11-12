/* SVN FILE: $Id: AffiliationServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */

package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.AffiliationReturn;

/**
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
public class AffiliationServiceReturn {

	/** 
	 * Status code that is result of executing this service.
	 */
	private int statusCode;
	
	/**
	 *  Status message returned as the result of executing this service.
	 */
	private String statusMessage;
	
	/**
	 *  Contains an array of AffiliationTable returns.
	 */
	private AffiliationReturn affiliationReturnRecord[] = null;
	
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
	 * @return the affiliationReturnRecord
	 */
	public AffiliationReturn[] getAffiliationReturnRecord() {
		return affiliationReturnRecord;
	}

	/**
	 * @param affiliationReturnArray the affiliationReturnRecord to set
	 */
	public void setAffiliationReturnRecord( AffiliationReturn[] affiliationReturnArray) {
		if (affiliationReturnArray != null) {
			this.affiliationReturnRecord = Arrays.copyOf(affiliationReturnArray, affiliationReturnArray.length);
		}
		else {
			this.affiliationReturnRecord = null;
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
	 * Empty Constructor.
	 * 
	 */
	public AffiliationServiceReturn() {
		super();
	}



	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public AffiliationServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 * @param affiliationReturnArray the affiliation return record only will be populated for the get affiliations service call.
	 * @param numberElements the number of elements in the affiliationReturnRecord array.
	 */
	public AffiliationServiceReturn(int statusCode, String statusMessage,
			AffiliationReturn[] affiliationReturnArray, int numberElements) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		if (affiliationReturnArray != null) {
			this.affiliationReturnRecord = Arrays.copyOf(affiliationReturnArray, affiliationReturnArray.length);
		}
		else {
			this.affiliationReturnRecord = null;
		}
		this.numberElements = numberElements;
	}
	/**
	 * @return a string value.
	 */
	public String toString() {
		return "";
	}	
	
}
