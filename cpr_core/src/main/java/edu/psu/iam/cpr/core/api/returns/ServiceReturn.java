package edu.psu.iam.cpr.core.api.returns;
/* SVN FILE: $Id: ServiceReturn.java 5577 2012-11-12 17:04:47Z llg5 $ */

/**
 * This class provides the implementation of the Service Return.
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
 * @package edu.psu.iam.cpr.api.returns
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class ServiceReturn {

	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/** Status message returned as the result of executing this service. */
	private String statusMessage;

	/** Contains the URI to the object */
	private String uri;
	
	/** Contains the record key of the updated object */
	private Long recordKey;
	
	/**
	 * Constructor.
	 */
	public ServiceReturn() {
		super();
	}

	/**
	 * Constructor.
	 * @param statusCode contains the result of executing this service.
	 * @param statusMessage contains the status message associated with executing this service.
	 */
	public ServiceReturn(int statusCode, String statusMessage) {
		this(statusCode, statusMessage, null);
	}
	
	/**
	 * Constructor.
	 * @param statusCode
	 * @param statusMessage
	 * @param recordKey
	 */
	public ServiceReturn(int statusCode, String statusMessage, Long recordKey) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.recordKey = recordKey;
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
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the recordKey
	 */
	public Long getRecordKey() {
		return recordKey;
	}

	/**
	 * @param recordKey the recordKey to set
	 */
	public void setRecordKey(Long recordKey) {
		this.recordKey = recordKey;
	}
}
