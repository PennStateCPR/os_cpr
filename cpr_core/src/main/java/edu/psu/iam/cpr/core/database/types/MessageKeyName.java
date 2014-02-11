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
	PSU_ID,
	MESSAGE_LOG_ID,
	MESSAGE_CONSUMER_KEY,
	SERVICE_KEY,
	SERVICE,
	MESSAGE_TYPE,
	//   Information for notification message
	CHANGE_KEY,
	
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
	ID_SERIAL_NUMBER,
	
	// Notification changes old types
	OLD_ACCOUNT_STATUS,
	OLD_ADDRESS,
	OLD_AFFILIATION,
	OLD_AFFILIATION_STATUS,
	OLD_CONFIDENTIALITY_STATUS,
	OLD_DATE_OF_BIRTH,
	OLD_EMAIL_ADDRESS,
	OLD_EMPLOYEE_DATA,
	OLD_GENDER,
	OLD_NAME,
	OLD_PHONE_NUMBER,
	OLD_PRIMARY_AFFILIATION,
	OLD_PS_DEPARTMENT,
	OLD_PS_CAMPUS,
	OLD_PSUID,
	OLD_STUDENT_DATA,
	OLD_USERID,

	// Notification changes new types
	NEW_ACCOUNT_STATUS,
	NEW_ADDRESS,
	NEW_AFFILIATION,
	NEW_AFFILIATION_STATUS,
	NEW_CONFIDENTIALITY_STATUS,
	NEW_DATE_OF_BIRTH,
	NEW_EMAIL_ADDRESS,
	NEW_EMPLOYEE_DATA,
	NEW_GENDER,
	NEW_NAME,
	NEW_PHONE_NUMBER,
	NEW_PRIMARY_AFFILIATION,
	NEW_TITLE,
	NEW_PS_DEPARTMENT,
	NEW_PS_CAMPUS,
	NEW_PSUID,
	NEW_STUDENT_DATA,
	NEW_USERID,

	// EMPLOYEE DATA
	TITLE,
	APPOINTMENT_CODE,
	SPECIAL_STATUS,
	EMPLOYEE_CLASS,
	ESTATUS_CLASS,
	EMPLOYEE_CAMPUS_CODE,
	LAST_DATE_PAID,
	DEPARTMENT,
	HERSHEY_DATA_STATUS,
	PAY_FREQUENCY,


	// Student Data type
	SEMESTER_CODE,
	STUDENT_CAMPUS_CODE,
	STUDENT_ACADEMIC_LEVEL,
	STUDENT_SEMESTER_STANDING,
	STUDENT_STATUS,
	ACADEMIC_COLLEGE,
	ACADEMIC_DEPARTMENT,
	MAJOR,
	CLASS_LOAD;
	
}
