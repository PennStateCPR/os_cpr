/* SVN FILE: $Id$ */
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
 * @package put the correct package name here.
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 *
*/

package edu.psu.iam.cpr.core.gi;

/**
 * Class to implement the return data for a GI service that requests the PSU Id corresponding to the Ssn in the CIDR file
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
 * @author E. Hayes   02/01/2012
 *
 */
public class GetPsuIdServiceReturn {

	
	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;
	
	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;
	
	/** Contains the PSU Id returned by the service. */
	private String psuId;
	
	/*
	 * Empty constructor.
	 */
	public GetPsuIdServiceReturn() {
		super();
	}
	
	/**
	 * Constructor
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public GetPsuIdServiceReturn(int statusCode, String statusMessage) {
		this.setStatusCode(statusCode);
		this.setStatusMessage(statusMessage);
		this.setPsuId(new String());
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
	public final void setStatusCode(int statusCode) {
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
	public final void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * @return the psuId
	 */
	public String getPsuId() {
		return psuId;
	}

	/**
	 * @param psuId the psuId to set
	 */
	public final void setPsuId(String psuId) {
		this.psuId = psuId;
	}


}
