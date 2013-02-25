/* SVN FILE: $Id: IAPReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.service.returns;
/**
 * This class is used to represent the return from executing GetExternalIAP, GetPSUIAP service call.
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
public class IAPReturn {

	
	/**
	 * Contains the iap
	 */
	
	private String iap;
	
	/** 
	 * Contains start date
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
	 * @return the iap
	 */
	public String getIap() {
		return iap;
	}
	
	/**
	 * @param iap the iap to set
	 */
	public void setIap(String iap) {
		this.iap = iap;
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
	 * 
	 */
	public IAPReturn() {
		super();
		
	}

	/**
	 * IAPReturn constructor with fields
	 * 
	 * @param iap - contains the iap
	 */
	
	public IAPReturn(String iap,  String startDate, String endDate, String lastUpdateOn, String lastUpdateBy,
			String createdOn, String createdBy) {
		super();
		this.iap = iap;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateOn = lastUpdateOn;
		this.lastUpdateBy = lastUpdateBy;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

}

