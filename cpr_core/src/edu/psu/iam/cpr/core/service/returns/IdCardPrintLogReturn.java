/* SVN FILE: $Id: IdCardPrintLogReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.service.returns;
/**
 *  This class provides the implementation of the return class for GetIdCardPrintEvent
 * table.  There are methods within here to get the IdCardPrintEvent data
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
 * 
 * @package edu.psu.iam.cpr.core.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class IdCardPrintLogReturn {

	/**
	 * contains the person id
	 */
	private Long personId;
	/**
	 * contains the Id card number
	 */
	private String idCardNumber;
	
	/**
	 * contains the workstation for the Id Card Print Event
	 * 
	 */
	private String workStationName;
	/**
	 * contains the ip address of workstation for the Id Card Print Event
	 * 
	 */
	private String ipAddress;
	
	/**
	 * contains the date of the Id Card Print Event
	 * 
	 */
	private String printDate;
	/**
	 * contains the userid of the person procesing the Id Card Print Event
	 * 
	 */
	private String printedBy;
	
	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId (Long personId) {
		this.personId = personId;
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
	 * @return the workStationName
	 */
	public String getWorkStationName() {
		return workStationName;
	}
	/**
	 * @param workStationName the workStationName to set
	 */
	public void setWorkStationName(String workStationName) {
		this.workStationName = workStationName;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the printDate
	 */
	public String getPrintDate() {
		return printDate;
	}
	/**
	 * @param printDate the printDate to set
	 */
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	/**
	 * @return the printedBy
	 */
	public String getPrintedBy() {
		return printedBy;
	}
	/**
	 * @param printedBy the printedBy to set
	 */
	public void setPrintedBy(String printedBy) {
		this.printedBy = printedBy;
	}
	/**
	 * 
	 */
	public IdCardPrintLogReturn() {
		super();
		
	}
	/**
	 * @param personId
	 * @param idCardNumber
	 * @param workStationName
	 * @param ipAddress
	 * @param printDate
	 * @param printedBy
	 */
	public IdCardPrintLogReturn(Long personId, String idCardNumber,
			String workStationName, String ipAddress, String printDate,
			String printedBy) {
		super();
		this.personId = personId;
		this.idCardNumber = idCardNumber;
		this.workStationName = workStationName;
		this.ipAddress = ipAddress;
		this.printDate = printDate;
		this.printedBy = printedBy;
	}
	
	
}
