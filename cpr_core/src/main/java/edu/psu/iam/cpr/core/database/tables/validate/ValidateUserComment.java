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
package edu.psu.iam.cpr.core.database.tables.validate;


import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.error.CprException;


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
public final class ValidateUserComment {
	
	private static final String DATABASE_TABLE_NAME = "USER_COMMENTS";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	private static final String COMMENTS = "COMMENTS";
	private static final String USERID = "USERID";
	
	/** 
	 * Constructor
	 */
    private ValidateUserComment() {
    	
    }
    
    /**
     * This routine is used to valid user comment information.  If successful it will return an UserCommentTable class.
     * @param db contains the Database object
     * @param personId contains the person id from the cpr.
     * @param userId contains the userid associated with the comment.
     * @param userCommentType contains the comment type.
     * @param comment contains the comment.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * @return will return an UserCommentTable class if successful, otherwise it will throw an exception.
     * @throws CprException 
     */
	public static UserCommentTable validateUserCommentParameters(final Database db, final long personId, final String userId,
			final String userCommentType, final String comment,final  String updatedBy) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		String localUserCommentType = ValidateHelper.checkField(null, userCommentType, null, "User comment type", false);
		String localUserId = ValidateHelper.checkField(db, userId, USERID, "Userid", true);
		String localComment = ValidateHelper.checkField(db, comment, COMMENTS, "Comments", true);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
	
		return new UserCommentTable(personId, localUserId, localUserCommentType, localComment, localUpdatedBy);
	}

	/**
	 * This routine is used to validate the data for the GetUserComments service.
	 * @param db contains the Database object
	 * @param personId contains the person identifier from the CPR.
	 * @param requestedBy contains the system identifier or userid that is requesting information.
	 * @param userCommentType contains the type of user comment to be retrieved, if specified.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned or not.
	 * @param commentKey comments a comment key, only valid for RESTful services.
	 * @throws CprException 
	 */
	public static UserCommentTable validateGetUserCommentsParameters(final Database db, final long personId, 
			final String requestedBy, final String userCommentType, final String returnHistory, final String commentKey) throws CprException {
				
		db.getAllTableColumns(DATABASE_TABLE_NAME);

		// For non-null input fields, trim them off.
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
		
		String localUserCommentType = null;
		boolean returnHistoryFlag = false;
		Long commentKeyValue = -1L;
		
		if (commentKey == null) {
			localUserCommentType = (userCommentType != null) ? userCommentType.trim() : null;
			returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		}
		else {
			commentKeyValue = Long.valueOf(commentKey);
		}
		
		// If a user comment type was specified, verify that its valid. 
		final UserCommentTable userCommentTable = new UserCommentTable();
		if (localUserCommentType != null) {
			userCommentTable.setUserCommentType(userCommentTable.findUserCommentTypeEnum(localUserCommentType));
		}
				
		userCommentTable.setReturnHistoryFlag(returnHistoryFlag);
		userCommentTable.setCommentKey(commentKeyValue);
		
		return userCommentTable;
	}
	
	/**
	 * This routine is used to validate the data for the GetUserComments service.
	 * @param db contains the Database object
	 * @param personId contains the person identifier from the CPR.
     * @param userId contains the userid associated with the comment.
     * @param userCommentType contains the comment type. 
	 * @param requestedBy contains the system identifier or userid that is requesting information.
	 * @throws CprException 
	 */
	public static void validateGetUserCommentByTypeParameters(final Database db, final long personId, final String userId, 
			final String userCommentType, final String requestedBy) throws CprException {
				
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// For non-null input fields, trim them off.
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
		@SuppressWarnings("unused")
		String localUserCommentType = ValidateHelper.checkField(null, userCommentType, null, "User comment type", false);
		@SuppressWarnings("unused")
		String localUserId = ValidateHelper.checkField(db, userId, USERID, "Userid", true);
		
	}
	
	/**
	 * This routine is used to validate parameters that were sent to the ArchiveUserComment service.  For errors, an exception 
	 * will be thrown.  Otherwise, it will return an UserCommentTable object.
	 * @param db contains the Database object
	 * @param personId contains the user's person identifier from the CPR.
	 * @param userId contains the userid associated with the comment.
	 * @param userCommentType contains the comment type.
	 * @param updatedBy contains the userid or system identifier that is updating the record.
	 * @param commentKey contains the comment key, only used for RESTful services.
	 * @return UserCommentTable class.
	 * @throws CprException 
	 */
	public static UserCommentTable validateArchiveUserCommentParameters(final Database db, final long personId, final String userId, 
			final String userCommentType,final  String updatedBy, final String commentKey) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// For non-null input fields, trim them off.
		final String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		String localUserCommentType = null;
		Long commentKeyValue = -1L;
		if (commentKey == null) {
			localUserCommentType = ValidateHelper.checkField(null, userCommentType, null, "User comment type", false);
		}
		else {
			commentKeyValue = Long.valueOf(commentKey);
		}
		final String localUserId = ValidateHelper.checkField(db, userId, USERID, "Userid", true);
		
		// Attempt to instantiate a new user comment table class.
		UserCommentTable userCommentTable = new UserCommentTable(personId, localUserId, localUserCommentType, localUpdatedBy);
		userCommentTable.setCommentKey(commentKeyValue);
		return userCommentTable;
	}
}
