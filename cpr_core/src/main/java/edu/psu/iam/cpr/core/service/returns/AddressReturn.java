/* SVN FILE: $Id: AddressReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */


package edu.psu.iam.cpr.core.service.returns;

/**
 * This class implements the AddressReturn that is returned as the result of calling GetAddress.
 */

/**
 * This class is used to represent the return from executing a GetAddress service call.
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
public class AddressReturn {

	/**
	 * Contains the address key.
	 */
	private String addressKey;
	
	/**
	 * Contains the address type
	 */
	private String addressType;
	
	/**
	 * Contains the document Type
	 */
	private String documentType;
	
	/**
	 * Contains the group identifier within a particular type.
	 */
	private  Long groupId;
	
	/**
	 * Contains a Y/N indicator as to whether the particular phone number is primary or not.
	 */
	private String primaryFlag;
	
	/**
	 * Contains the address line 1
	 */
	private String address1;
	
	/**
	 * Contains the address line 2
	 */
	private String address2;
	
	/**
	 * Contains the address line 3
	 */
	private String address3;
	
	/**
	 * Contains the city
	 */
	private String city;
	
	/** 
	 * Contains the state or province
	 */
	private String stateOrProvince;
	
	/**
	 *  Contains the postalCode
	 */
	private String postalCode;

	/**
	 * Contains the Campus Code
	 */
	private String campusCode;
	
	/**
	 *  Contains the Campus Name
	 */
	private String campusName;
	
	/**
	 * Contains the countryCode
	 */
	private String countryCode;
	
	/**
	 * Contains the country Name
	 */
	private String countryName;
	/**
	 * @return the addressType
	 */
	
	/**
	 * Contains the verified flag
	 *
	 */
	private String verifiedFlag ;

	/** 
	 * Contains start date
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
	 * Contains the URI that refers to the resource (only used for the RESTful services).
	 */
	private String uri;
	
	
	/**
	 * 
	 * @return addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the primaryFlag
	 */
	public String getPrimaryFlag() {
		return primaryFlag;
	}

	/**
	 * @param primaryFlag the primaryFlag to set
	 */
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getStateOrProvince() {
		return stateOrProvince;
	}

	/**
	 * @param stateOrProvince the state to set
	 */
	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the campusCode
	 */
	public String getCampusCode() {
		return campusCode;
	}

	/**
	 * @param campusCode the campusCode to set
	 */
	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	/**
	 * @return the campusName
	 */
	public String getCampusName() {
		return campusName;
	}

	/**
	 * @param campusName the campusName to set
	 */
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the verifiedFlag
	 */
	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	/**
	 * @param verifiedFlag the verifiedFlag to set
	 */
	public void setVerifiedFlag(String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
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
	 * @return the addressKey
	 */
	public String getAddressKey() {
		return addressKey;
	}

	/**
	 * @param addressKey the addressKey to set
	 */
	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
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
	 * 
	 */
	public AddressReturn() {
		super();

	}

	/**
	 * AddressReturn constructor with fields
	 * @param addressKey contains the address key.
	 * @param addressType - contains the address type
	 * @param documentType - contains the document Type
	 * @param address1 - contains address line 1
	 * @param address2 - contains address line 2
	 * @param address3 - contains address line 3
	 * @param city - contains the city
	 * @param stateOrProvince - contains the state 
	 * @param postalCode - contains the postal code
	 * @param campusCode - contains the two character Campus Code
	 * @param campusName - contains the two character Campus Name
	 * @param countryCode - contains the three character Country Code
	 * @param countryName - contains the three character Country Name
	 * @param verifiedFlag - contains the status from address verification
	 * @param startDate - contains the start date
	 * @param endDate  - contains the end date
	 * @param lastUpdateOn  - contains last Update date
	 * @param lastUpdateBy  - contains the last updater
	 * @param createdOn - contains creation date
	 * @param createdBy  contains the creator
	 */
	
	public AddressReturn(String addressKey, String addressType, String documentType, String address1,
			String address2, String address3, String city, String stateOrProvince,
		    String postalCode,  String campusCode,
			String campusName, String countryCode, String countryName,
			String verifiedFlag, String startDate, String endDate, String lastUpdateOn,
			String lastUpdateBy, String createdOn, String createdBy ) {
		super();
		this.addressKey = addressKey;
		this.addressType = addressType;
		this.documentType = documentType;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
		this.postalCode = postalCode;
		this.campusCode = campusCode;
		this.campusName = campusName;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.verifiedFlag = verifiedFlag;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateOn = lastUpdateOn;
		this.lastUpdateBy = lastUpdateBy;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}
}
