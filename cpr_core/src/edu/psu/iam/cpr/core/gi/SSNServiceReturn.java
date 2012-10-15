/* SVN FILE: $Id: SSNServiceReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.gi;

import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * SSN return.
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
 * @package edu.psu.iam.cpr.core.gi
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class SSNServiceReturn {

	/** Status code that is result of executing this service. */
	private ReturnType statusCode;
	
	/** Status message returned as the result of executing this service. */
	private String statusMessage;
	
	/** Contains the PSU ID number */
	private String psuId = null;
	
	/**
	 * Sole constructor.
	 */
	public SSNServiceReturn() {
		super();
	}
	
	/**
	 * @return the statusCode
	 */
	public ReturnType getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(ReturnType statusCode) {
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
	 * @return the psuID
	 */
	public String getPsuId() {
		return psuId;
	}

	/**
	 * @param psuId the PSU ID
	 */
	public void setPsuId(String psuId) {
		this.psuId = psuId;
	}


}
