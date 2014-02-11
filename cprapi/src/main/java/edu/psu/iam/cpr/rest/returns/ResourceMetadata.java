package edu.psu.iam.cpr.rest.returns;

/**
 * This class provides the RESTful return of meta information.
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
public class ResourceMetadata {
	
	/** Contains the last modified time of the resource. */
	private String lastModified;
	
	/** Contains the URI that describes the resource */
	private String baseUri;
	
	/** Contains the status code */
	private String statusCode;
	
	/**
	 * Constructor.
	 */
	public ResourceMetadata() {
		super();
	}
	
	/**
	 * Constructor
	 * @param lastModified contains the last modified time of the resource.
	 * @param baseUri contains the base uri reference. 
	 * @param statusCode contains the status code.
	 */
	public ResourceMetadata(String lastModified, String baseUri, String statusCode) {
		super();
		this.lastModified = lastModified;
		this.baseUri = baseUri;
		this.statusCode = statusCode;
	}

	/**
	 * @return the lastModified
	 */
	public String getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @return the baseUri
	 */
	public String getBaseUri() {
		return baseUri;
	}

	/**
	 * @param baseUri the baseUri to set
	 */
	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
