/* SVN FILE: $Id: CredentialReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing a GetCredential* service call.
 * This data is returned as an array to the caller.
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
 * @package edu.psu.iam.cpr.core.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class CredentialReturn {

	/** Contains the credential key */
	private String credentialKey;
	
	/** Contains the credential type */
	private String credentialType;
	
	/** Contains the credential data. */
	private String credentialData;
	
	/** Contains the start date */
	private String startDate;
	
	/** Contains the end date for the record */
	private String endDate;
	
	/** Contains the last update person */
	private String lastUpdateBy;
	
	/** Contains the date the record was last updated on */
	private String lastUpdateOn;
	
	/** Contains the person who created the record */
	private String createdBy;
	
	/** Contains the date the record was created on */
	private String createdOn;
	
	/** Contains the URI that is only used for RESTful services */
	private String uri;

	/**
	 * Constructor 
	 * @param credentialType contains the type of credential.
	 * @param credentialData contains additional data associated with the credential.
	 * @param startDate contains the start date of the record.
	 * @param endDate contains the end date of the record.
	 * @param lastUpdateBy contains the entity that last updated the record.
	 * @param lastUpdateOn contains the date the record was last updated.
	 * @param createdBy contains the entity that created the record.
	 * @param createdOn contains the date the record was created.
	 */
	public CredentialReturn(String credentialType, String credentialData,
			String startDate, String endDate, String lastUpdateBy,
			String lastUpdateOn, String createdBy, String createdOn) {
		super();
		this.credentialType = credentialType;
		this.credentialData = credentialData;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateOn = lastUpdateOn;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
	
	/**
	 * Empty constructor.
	 */
	public CredentialReturn() {
		super();
	}
	
	/**
	 * @return the credentialType
	 */
	public String getCredentialType() {
		return credentialType;
	}

	/**
	 * @param credentialType the credentialType to set
	 */
	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	/**
	 * @return the credentialData
	 */
	public String getCredentialData() {
		return credentialData;
	}

	/**
	 * @param credentialData the credentialData to set
	 */
	public void setCredentialData(String credentialData) {
		this.credentialData = credentialData;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the lastUpdateBy
	 */
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	/**
	 * @param lastUpdateBy the lastUpdateBy to set
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * @return the lastUpdateOn
	 */
	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	/**
	 * @param lastUpdateOn the lastUpdateOn to set
	 */
	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
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
	 * @return the credentialKey
	 */
	public String getCredentialKey() {
		return credentialKey;
	}

	/**
	 * @param credentialKey the credentialKey to set
	 */
	public void setCredentialKey(String credentialKey) {
		this.credentialKey = credentialKey;
	}

		
}
