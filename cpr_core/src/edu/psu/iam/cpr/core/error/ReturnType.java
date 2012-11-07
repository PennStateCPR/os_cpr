/* SVN FILE: $Id: ReturnType.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.error;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
/**
 *  Return Types
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
 * @package edu.psu.iam.cpr.core.error
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

   /**
    * Contains the enumerated types that are returned by a function.
    * @author jvuccolo
    *
    */
public enum ReturnType {
        SUCCESS(0,"\"%s\"."),
        ADD_FAILED_EXCEPTION(1,"Unable to store \"%s\" record in the database."),
        ALREADY_DELETED_EXCEPTION(2,"The \"%s\" record has already been archived."),
        RECORD_NOT_FOUND_EXCEPTION(3,"A \"%s\" record was not found in the database."),
        ARCHIVE_FAILED_EXCEPTION(4,"Unable to archive \"%s\"."),
        NOT_SPECIFIED_EXCEPTION(5,"\"%s\" parameter must be specified."),
        TYPE_NOT_FOUND_EXCEPTION(6,"Invalid \"%s\" type was specified."),
        INVALID_PARAMETERS_EXCEPTION(7,"\"%s\" parameter is invalid."),
        YN_PARAMETERS_EXCEPTION(8,"\"%s\" parameter must be Y or N."),
        GENERAL_EXCEPTION(9,"\"%s\"."),
        PARAMETER_LENGTH_EXCEPTION(10,"\"%s\" parameter exceeds the maximum length allowed."),
        MESSAGE_CREATION_EXCEPTION(11,"Unable to create message to be sent to service providers."),
        MESSAGE_INITIALIZATION_EXCEPTION(12,"Unable to set \"%s\" information for message."),
        MESSAGE_SEND_EXCEPTION(13,"Unable to send message to queue \"%s\"."),
        NOT_AUTHORIZED_EXCEPTION(14,"You are not authorized to execute the \"%s\" service."),
        PERSON_NOT_ACTIVE_EXCEPTION(15,"The person is not currently active in the database."),
        PERSON_NOT_FOUND_EXCEPTION(16,"A person was not found in the database."),
        PSUID_NOT_FOUND_EXCEPTION(17,"PSU ID was not found in the database."),
        SERVICE_AUTHENTICATION_EXCEPTION(18,"Invalid service authentication."),
        SET_PRIMARY_FAILED_EXCEPTION(19,"Unable to set primary \"%s\"."),
        UPDATE_FAILED_EXCEPTION(20,"Unable to update \"%s\"."),
        WEB_SERVICE_NOT_FOUND_EXCEPTION(21,"Web service \"%s\" was not found in the database."),
        GI_FAILURE(23,"A failure was encountered interfacing with the Generalized Interface."),
        DB_CONNECTION_FAILURE(24,"Unable to obtain a database connection."),
        GENERAL_DATABASE_EXCEPTION(25,"\"%s\"."),
        UNARCHIVE_FAILED_EXCEPTION(26,"Unable to un-archive \"%s\"."),
        DATA_CHANGE_EXCEPTION(51,"Unauthorized to perform action on \"%s\" data."),
        SECURITY_OPERATION_EXCEPTION(76,"Unable to perform security action on \"%s\"."),
        AFFILIATION_USE_EXCEPTION(77,"You are not authorized to use the \"%s\" affiliation."),
        IAP_USE_EXCEPTION(101,"You are not authorized to use the \"%s\" IAP"),
        RECORD_ALREADY_EXISTS(201,"\"%s\" record already exists in the database."),
        EXACT_MATCH_EXCEPTION(228,"The person cannot be added, because an exact match using \"%s\" was found."),
        NEAR_MATCH_EXCEPTION(229,"The person cannot be added, because a near match(s) was found"),
        MESSAGE_RECEIVE_EXCEPTION(251,"Unable to receive message from queue \"%s\"."),
        DIRECTORY_EXCEPTION(255, "\"%s\"."),
        JSON_EXCEPTION(256, "\"%s\".");

   /**
    * Contains the index
    */


	private int index;
   /** 
    * Contains the message
    */
	private String message;
	
   /** 
    *  Constructor
    * @param  index
    * @param  message
    */

	private ReturnType(int index, String message) {
		this.index = index;
		this.message = message;
	}
   /**
    * @return int
    */
	
	public int index() {
		return index;
	}
	
   /**
    * @return message
    */

	public String message() {
		return message;
	}
   /**
    *
    */

	private static final Map<Integer, ReturnType> LOOKUP = new HashMap<Integer,ReturnType>();
	static {
		for (ReturnType p : EnumSet.allOf(ReturnType.class)) {
			LOOKUP.put(p.index(), p);
		}
	}
	
   /**
    * @param index
    * @return enum
    */

	public static ReturnType get(int index) {
		return LOOKUP.get(index);
	}
}

