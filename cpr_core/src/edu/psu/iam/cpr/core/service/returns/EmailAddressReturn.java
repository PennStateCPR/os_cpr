/* SVN FILE: $Id: EmailAddressReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.returns;

/**
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
public class EmailAddressReturn {
	
	/** Contains the type of email address. */
	private String emailAddressType;
	
	/** Contains the email address. */
	private String emailAddress;
	
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
	
	/**
	 * Constructor with parameters.
	 * @param emailAddressType type of email address.
	 * @param emailAddress email address value.
	 * @param startDate contains the start date.
	 * @param endDate contains the end date.
	 * @param lastUpdateBy contains the person who last updated this record.
	 * @param lastUpdateOn contains the date the record was last updated on.
	 * @param createdBy contains the person who created this record.
	 * @param createdOn contains the date the record was created.
	 */
	public EmailAddressReturn(String emailAddressType, String emailAddress,
			String startDate, String endDate, String lastUpdateBy,
			String lastUpdateOn, String createdBy, String createdOn) {
		super();
		this.emailAddressType = emailAddressType;
		this.emailAddress = emailAddress;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateOn = lastUpdateOn;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
	
	/**
	 * Default constructor.
	 */
	public EmailAddressReturn() {
		super();
	}


	/**
	 * @return the emailAddressType
	 */
	public String getEmailAddressType() {
		return emailAddressType;
	}

	/**
	 * @param emailAddressType the emailAddressType to set
	 */
	public void setEmailAddressType(String emailAddressType) {
		this.emailAddressType = emailAddressType;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param lastUpdateBy the lastUpdateBy to set
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * @return the lastUpdateBy
	 */
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	/**
	 * @param lastUpdateOn the lastUpdateOn to set
	 */
	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	/**
	 * @return the lastUpdateOn
	 */
	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}
	
	/**
	 * @return a string value.
	 */
	public String toString() {
		return "EmailAddressReturn";
	}
}
