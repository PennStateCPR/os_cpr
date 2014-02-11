package edu.psu.iam.cpr.rest.returns;

/**
 * This class provides the RESTful return of response meta information.
 * Copyright 2013 The Pennsylvania State University
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
 * @package edu.psu.iam.cpr.rest.returns
 * @author $Author: jvuccolo $
 */
public class ResponseMeta {
	
	/** Contains the response timestamp. */
	private String responseTimestamp;
	
	/** Contains the execution time in responseTimeeconds */
	private long responseTime;
	
	/** Contains  freeform text about the request that the server processed (for debug) */
	private String requestCommentary;
	
	/** Contains the http status code */
	private int httpStatusCode;
	
	/** Contains the major.minor server version number */
	private String serverVersion;
	
	/**
	 * Constructor
	 */
	public ResponseMeta() {
		super();
	}

	/**
	 * Constructor
	 * @param responseTimestamp contains the response timestamp.
	 * @param responseTime contains the execution time in responseTimeeconds.
	 * @param requestCommentary contains the request processed.
	 * @param httpStatusCode contains the http status code.
	 */
	public ResponseMeta(String responseTimestamp, long responseTime,
			String requestCommentary, int httpStatusCode,
			String serverVersion) {
		super();
		this.responseTimestamp = responseTimestamp;
		this.responseTime = responseTime;
		this.requestCommentary = requestCommentary;
		this.httpStatusCode = httpStatusCode;
		this.serverVersion = serverVersion;
	}

	/**
	 * @return the responseTimestamp
	 */
	public String getResponseTimestamp() {
		return responseTimestamp;
	}

	/**
	 * @param responseTimestamp the responseTimestamp to set
	 */
	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

	/**
	 * @return the responseTime
	 */
	public long getresponseTime() {
		return responseTime;
	}

	/**
	 * @param responseTime the responseTime to set
	 */
	public void setresponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * @return the requestCommentary
	 */
	public String getrequestCommentary() {
		return requestCommentary;
	}

	/**
	 * @param requestCommentary the requestCommentary to set
	 */
	public void setrequestCommentary(String requestCommentary) {
		this.requestCommentary = requestCommentary;
	}

	/**
	 * @return the httpStatusCode
	 */
	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 * @param httpStatusCode the httpStatusCode to set
	 */
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * @return the serverVersion
	 */
	public String getServerVersion() {
		return serverVersion;
	}

	/**
	 * @param serverVersion the serverVersion to set
	 */
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

}
