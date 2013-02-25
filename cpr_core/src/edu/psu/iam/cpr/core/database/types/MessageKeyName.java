/* SVN FILE: $Id: MessageKeyName.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

/**
 * 
 */
package edu.psu.iam.cpr.core.database.types;
/**
 * Message key names.
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
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 * @author jvuccolo
 *
 */
public enum MessageKeyName {
	
	// Common information to all messages.
	SERVICE_NAME,
	PERSON_ID,
	DIRECTORY_ID,
	USERID,
	REQUESTED_BY,
	MESSAGE_LOG_ID,
	PSU_ID,
	
	//   Information that most messages may need
	PRIMARY,
	
	// Name Table
	NAME_TYPE,
	FIRST_NAME,
	MIDDLE_NAMES,
	LAST_NAME,
	SUFFIX,
	
	// Phones Table
	PHONE_TYPE,
	PHONE_NUMBER,
	EXTENSION,
	
	// Addresses Table, need to figure out what to do with the campus code later.
	ADDRESS_TYPE,
	ADDRESS1,
	ADDRESS2,
	ADDRESS3,
	CITY,
	STATE,
	POSTAL_CODE,
	PROVINCE,
	CAMPUS_NAME,
	COUNTRY_NAME,
	
	// Date of birth table
	DATE_OF_BIRTH,
	
	// Email Address table
	EMAIL_ADDRESS_TYPE,
	EMAIL_ADDRESS,
	
	// Gender.
	GENDER,
	
	// PSU Affiliations
	PSU_AFFILIATION,

	
	// Confidentiality
	CONFIDENTIALITY_TYPE,
	
	// IAP
	IAP_TYPE,
	
	// User Comment table
	USER_COMMENT_TYPE,
	COMMENTS,
	
	// Person Id Card table
	ID_CARD_TYPE,
	ID_CARD_NUMBER,
	ID_SERIAL_NUMBER;
	
}
