/* SVN FILE: $Id: PhoneReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing GetPhone service call.
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
public class PhoneReturn {

	/**
	 * Contains the phone Key.
	 */
	private String phoneKey;
	
	/** 
	 * Contains the string representation of the phonetype
	 */
	private String phoneType;
	
	/**
	 * Contains the group identifier within a particular type.
	 */
	
	private Long groupId;
	/**
	 * Contains a Y/N indicator as to whether the particular phone number is primary or not.
	 */
	private String primaryFlag;
	

	/** 
	 * Contains the phone number string;
	 */
	private String phoneNumber;
	
	/**
	 * Contains the phone number extension string;
	 */
	private String extension;
	
	/** 
	 * Contains a Y/N as to whether the phone number is international.
	 */
	private String internationalNumber;

	/** 
	 * Contains the start date
	 * 
	 */
	private String startDate;
	
	/** 
	 * Contains end date 
	 */
	private String endDate;
	
	/** 
	 * contains last update by
	 */
	private String lastUpdateBy;
	

	/** 
	 * Contains last update on
	 */
	private String lastUpdateOn;
	
	/** 
	 * contains created by
	 */
	private String createdBy;

	/** 
	 * Contains created on
	 */
	private String createdOn;
	
	/**
	 * Contains the URI for RESTful services.
	 */
	private String uri;
	
	/**
	 * Constructor
	 */
	public PhoneReturn() {
		super();

	}
	/**
	 * @param phoneKey contains the phone key.
	 * @param phoneType type of telephone number.
	 * @param phoneNumber telephone number
	 * @param extension extension (optional).
	 * @param internationalNumber y/n indicator as to whether the telephone number is international or not.
	 */
	public PhoneReturn(String phoneKey, String phoneType, String phoneNumber,
			String extension ,
			String internationalNumber, String startDate, String endDate, String lastUpdateOn,
			String lastUpdateBy, String createdOn, String createdBy ) {
		super();
		this.phoneKey = phoneKey;
		this.phoneNumber = phoneNumber;
		this.extension = extension;
		this.internationalNumber = internationalNumber;
		this.phoneType = phoneType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateOn = lastUpdateOn;
		this.lastUpdateBy = lastUpdateBy;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}



	/**
	 * @return the phoneTypeString
	 */
	public String getPhoneType() {
		return phoneType;
	}


	/**
	 * @param phoneTypeString the phoneTypeString to set
	 */
	public void setPhoneType(String phoneTypeString) {
		this.phoneType = phoneTypeString;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * @return the internationalNumber
	 */
	public String getInternationalNumber() {
		return internationalNumber;
	}
	/**
	 * @param internationalNumber the internationalNumber to set
	 */
	public void setInternationalNumber(String internationalNumber) {
		this.internationalNumber = internationalNumber;
	}
	/**
	 * @return the phoneType
	 */
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}
	/**
	 * @param primaryFlag the primaryFlag to set
	 */
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	/**
	 * @return the primaryFlag
	 */
	public String getPrimaryFlag() {
		return primaryFlag;
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
	 * @return the phoneKey
	 */
	public String getPhoneKey() {
		return phoneKey;
	}
	/**
	 * @param phoneKey the phoneKey to set
	 */
	public void setPhoneKey(String phoneKey) {
		this.phoneKey = phoneKey;
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



}
