/* SVN FILE: $Id: NameReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing a GetName service call.
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
public class NameReturn {
	
	/** Contains the type of name. */
	private String nameType;
	
	/** Contains the document type if available. */
	private String documentType;
	
	/** Contains the user's first name. */
	private String firstName;
	
	/** Contains the user's middle names. */
	private String middleNames;
	
	/** Contains the user's last name. */
	private String lastName;
	
	/** Contains the user's suffix */
	private String suffix;
	
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
	 * Constructor.
	 */
	public NameReturn() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param nameType type of name.
	 * @param documentType type of documented name if available.
	 * @param firstName first name.
	 * @param middleNames middle name(s).
	 * @param lastName last name.
	 * @param suffix suffix.
	 * @param startDate contains the start date.
	 * @param endDate contains the end date.
	 * @param lastUpdateBy contains the person who last updated this record.
	 * @param lastUpdateOn contains the date the record was last updated on.
	 * @param createdBy contains the person who created this record.
	 * @param createdOn contains the date the record was created.
	 */
	public NameReturn(String nameType, String documentType, String firstName, String middleNames,
			String lastName, String suffix, String startDate, String endDate, String lastUpdateBy,
			String lastUpdateOn, String createdBy, String createdOn) {
		super();
		this.nameType = nameType;
		this.documentType = documentType;
		this.firstName = firstName;
		this.middleNames = middleNames;
		this.lastName = lastName;
		this.suffix = suffix;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateOn = lastUpdateOn;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	/**
	 * @return the nameType
	 */
	public String getNameType() {
		return nameType;
	}

	/**
	 * @param nameType the nameType to set
	 */
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleNames
	 */
	public String getMiddleNames() {
		return middleNames;
	}

	/**
	 * @param middleNames the middleNames to set
	 */
	public void setMiddleNames(String middleNames) {
		this.middleNames = middleNames;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
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
}
