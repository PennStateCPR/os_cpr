/* SVN FILE: $Id: ValidateUserComment.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
package edu.psu.iam.cpr.core.util;


import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;


/**
 * ValidateUserComment is a utility class that will validate user comment data inputs 
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
 * @author Bob Walters
 *
 * @version %I%, %G%
 * 
 * @since 1.0
 */
public class ValidateUserComment {
	
    
    /**
     * This routine is used to valid user comment information.  If successful it will return an UserCommentTable class.
     * @param db contains the Database object
     * @param personId contains the person id from the cpr.
     * @param userId contains the userid associated with the comment.
     * @param userCommentType contains the comment type.
     * @param comment contains the comment.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * @return will return an UserCommentTable class if successful, otherwise it will throw an exception.
     * @throws GeneralDatabaseException 
     * @throws CprException 
     */
	public static UserCommentTable validateUserCommentParameters(Database db, long personId, String userId,
			String userCommentType, String comment, String updatedBy) throws GeneralDatabaseException, CprException {
		
		UserCommentTable userCommentTable = null;
		
		// For input strings that are non-null, trim them.
		userCommentType = (userCommentType != null) ? userCommentType.trim() : null;
		userId = (userId != null) ? userId.trim() : null;
		comment = (comment != null) ? comment.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
	
		// Verify that an email address type, email address and updated by were specified.

		if (userId == null || userId.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "UserId");
		}
		if (userCommentType == null || userCommentType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "User comment type");
		}
		if (comment == null || comment.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Comments");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}

		// Verify that the length for the comment and updated by do not exceed the maximums for the database.
		db.getAllTableColumns("USER_COMMENTS");
		if (comment.length() > db.getColumn("COMMENTS").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Comments");
		}
		if (userId.length() > db.getColumn("USERID").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Userid");
		}
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
		
		// Create a new object.
		try {
			userCommentTable = new UserCommentTable(personId, userId, userCommentType, comment, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "User comment type");
		}

	
		return userCommentTable;
	}

	/**
	 * This routine is used to validate the data for the GetUserComments service.
	 * @param db contains the Database object
	 * @param personId contains the person identifier from the CPR.
	 * @param requestedBy contains the system identifier or userid that is requesting information.
	 * @param userCommentType contains the type of user comment to be retrieved, if specified.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned or not.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static UserCommentTable validateGetUserCommentsParameters(Database db, long personId, 
						String requestedBy, String userCommentType, String returnHistory) throws GeneralDatabaseException, CprException {
				
		// For non-null input fields, trim them off.
		requestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		userCommentType = (userCommentType != null) ? userCommentType.trim() : null;
		returnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		
		// Verify that a requestor was specified.
		if (requestedBy == null || requestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
				
		// Verify that the fields do not exceed the database lengths.
		db.getAllTableColumns("USER_COMMENTS");
		if (requestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
		// If a user comment type was specified, verify that its valid. 
		final UserCommentTable userCommentTable = new UserCommentTable();
		if (userCommentType != null) {
			try {
				userCommentTable.setUserCommentType(userCommentType);
			}
			catch (Exception e) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "User comment type");
			}
		}
		
		// Validate the return history flag.
		if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		userCommentTable.setReturnHistoryFlag((returnHistory.equals("Y")) ? true : false);
		
		return userCommentTable;
	}
	
	/**
	 * This routine is used to validate the data for the GetUserComments service.
	 * @param db contains the Database object
	 * @param personId contains the person identifier from the CPR.
     * @param userId contains the userid associated with the comment.
     * @param userCommentType contains the comment type. 
	 * @param requestedBy contains the system identifier or userid that is requesting information.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static void validateGetUserCommentByTypeParameters(Database db, long personId, String userId, String userCommentType, String requestedBy) throws GeneralDatabaseException, CprException {
				
		// For non-null input fields, trim them off.
		requestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		userCommentType = (userCommentType != null) ? userCommentType.trim() : null;
		userId = (userId != null) ? userId.trim() : null;
		
		// Verify that a requestor was specified.
		if (userId == null || userId.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "UserId");
		}
		if (userCommentType == null || userCommentType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "User comment type");
		}
		if (requestedBy == null || requestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
				
		// Verify that the fields do not exceed the database lengths.
		db.getAllTableColumns("USER_COMMENTS");
		if (userId.length() > db.getColumn("USERID").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Userid");
		}
		if (requestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
	}
	
	/**
	 * This routine is used to validate parameters that were sent to the ArchiveUserComment service.  For errors, an exception 
	 * will be thrown.  Otherwise, it will return an UserCommentTable object.
	 * @param db contains the Database object
	 * @param personId contains the user's person identifier from the CPR.
     * @param userId contains the userid associated with the comment.
     * @param userCommentType contains the comment type.
	 * @param updatedBy contains the userid or system identifier that is updating the record.
	 * @return UserCommentTable class.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static UserCommentTable validateArchiveUserCommentParameters(Database db, long personId, String userId, String userCommentType, String updatedBy) throws GeneralDatabaseException, CprException {
		
		UserCommentTable userCommentTable = null;
		
		// For input parameters that are non-null, trim them.
		userCommentType = (userCommentType != null) ? userCommentType.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		userId = (userId != null) ? userId.trim() : null;
		
		// Verify that the user comment fields were specified.
		if (userId == null || userId.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "UserId");
		}
		if (userCommentType == null || userCommentType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "User comment type");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		
		// Verify that the fields do not exceed the database lengths.
		db.getAllTableColumns("USER_COMMENTS");
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
		if (userId.length() > db.getColumn("USERID").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Userid");
		}

		// Attempt to instantiate a new user comment table class.
		try {
			userCommentTable = new UserCommentTable(personId, userId, userCommentType, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "User comment type");
		}
		
		// Success return the new object.
		return userCommentTable;
	}
}
