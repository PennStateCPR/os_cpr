/* SVN FILE: $Id$ */
 
package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing a GetIdCard service call.
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
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date: 2012-01-30 09:18:58 -0500 (Mon, 30 Jan 2012) $
 */
public class PersonIdCardReturn {
	/** Contains the the Id card type. */
	private String idCardType;
	
	/** Contains the Id card number  */
	private String idCardNumber;
	
	/** Contains the Id card number  */
	private String idSerialNumber;
	
	/** Contains the start date associated with the record. */
	private String startDate;
	
	/** Contains the end date associated with the record */
	private String endDate;
	
	/** Contains the last update person */
	private String lastUpdateBy;
	
	/** Contains the date the record was last updated on */
	private String lastUpdateOn;
	
	/** Contains the person who created the record */
	private String createdBy;
	
	/** Contains the date the record was created on */
	private String createdOn;
	
	/**
	 * Constructor.
	 */
	public PersonIdCardReturn() {
		super();
	}

	/**
	 * PersonIdCardReturn constructor with fields
	 * @param idCardType  type of id card
	 * @param idCardNumber - id card number
	 * @param idSerialNumber - id serial number
	 * @param startDate  - start date of card
	 * @param endDate - end date of card
	 * @param lastUpdateBy - last update by
	 * @param lastUpdateOn - last update date
	 * @param createdBy - created by
	 * @param createdOn - created date
	 */
	public PersonIdCardReturn(String idCardType, String idCardNumber,
			 String idSerialNumber, String startDate,
			String endDate, String lastUpdateBy, String lastUpdateOn,
			String createdBy, String createdOn) {
		super();
		this.idCardType = idCardType;
		this.idCardNumber = idCardNumber;
		this.idSerialNumber = idSerialNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateOn = lastUpdateOn;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	/**
	 * @return the idCardType
	 */
	public String getIdCardType() {
		return idCardType;
	}

	/**
	 * @param idCardType the idCardType to set
	 */
	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	/**
	 * @return the idCardNumber
	 */
	public String getIdCardNumber() {
		return idCardNumber;
	}

	/**
	 * @param idCardNumber the idCardNumber to set
	 */
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	

	/**
	 * @return the idSerialNumber
	 */
	public String getIdSerialNumber() {
		return idSerialNumber;
	}

	/**
	 * @param idSerialNumber the idSerialNumber to set
	 */
	public void setIdSerialNumber(String idSerialNumber) {
		this.idSerialNumber = idSerialNumber;
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
	

}
