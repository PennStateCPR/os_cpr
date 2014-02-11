package edu.psu.iam.cpr.core.api.returns;

/**
 * This class provides the implementation for the Match Code API.
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
public class MatchCodeServiceReturn {
	
	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;

	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;

	/** Contains the generated match code */
	private String matchCode;
	
	/**
	 * Empty constructor.
	 */
	public MatchCodeServiceReturn() {
		super();
	}
	
	/**
 	 * Constructor
 	 * 
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public MatchCodeServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	/**
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 * @param matchCode the match code generated for the data value entered
	 */
	
	public MatchCodeServiceReturn(int statusCode, String statusMessage,
			String matchCode) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.matchCode = matchCode;
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
	 * @return the matchCode
	 */
	public String getMatchCode() {
		return matchCode;
	}

	/**
	 * @param matchCode the matchCode value to set
	 */
	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}

}
