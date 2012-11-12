/* SVN FILE: $Id: IAPServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */

package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.IAPReturn;
/**
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
 *  @package pedu.psu.iam.cpr.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public class IAPServiceReturn {
	
	/** status code */
	private int statusCode;
	
	/** status message */
	private String statusMessage;	
	
	/** 
	 * Contains an array of iapReturn records returns.
	 */
	private IAPReturn iapReturnRecord[] = null;
	
	/** 
	 * Contains the number of elements in the array.
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
	 * @return the iapReturnRecord
	 */
	public IAPReturn[] getIapReturnRecord() {
		return iapReturnRecord;
	}

	/**
	 * @param iapReturnArray the iapReturnRecord to set
	 */
	public void setIapReturnRecord(IAPReturn[] iapReturnArray) {
		if (iapReturnArray != null) {
			this.iapReturnRecord = Arrays.copyOf(iapReturnArray, iapReturnArray.length);
		}
		else {
			this.iapReturnRecord = null;
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
	 * 
	 */
	public IAPServiceReturn() {
		super();
	}
	
	/**
	 * @param statusCode - contains the status code
	 * @param statusMessage - contains the status message
	 */
	public IAPServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	/**
	 * @param statusCode - contains the status code
	 * @param statusMessage - contains the status message
	 * @param iapReturnArray contains the IAP Data
	 * @param numberElements contains the number of elements in IAPReturn
	 */
	public IAPServiceReturn(int statusCode, String statusMessage, IAPReturn[] iapReturnArray, int numberElements) {
		this(statusCode, statusMessage);
		if (iapReturnArray != null) {
			this.iapReturnRecord = Arrays.copyOf(iapReturnArray, iapReturnArray.length);
		}
		else {
			this.iapReturnRecord = null;
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
