/* SVN FILE: $Id: PersonIdCardNumberReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing GetIdCardNumber
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
public class PersonIdCardNumberReturn {

	
	/** Contains the the Id card type. */
	private String idCardType;
	
	/** Contains the Id card number  */
	private String idCardNumber;

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
	 * 
	 */
	public PersonIdCardNumberReturn() {
		super();
		
	}

	/**
	 * @param idCardType
	 * @param idCardNumber
	 */
	public PersonIdCardNumberReturn(String idCardType, String idCardNumber) {
		super();
		this.idCardType = idCardType;
		this.idCardNumber = idCardNumber;
	}

}
