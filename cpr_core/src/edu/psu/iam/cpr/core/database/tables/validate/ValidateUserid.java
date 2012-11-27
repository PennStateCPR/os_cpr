/* SVN FILE: $Id: ValidateUserid.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.error.CprException;

/**
 * This class contains methods that are used to validate information specified during an AddUserid, GetUserid
 * service calls.
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
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public final class ValidateUserid {
	
	private static final String DATABASE_TABLE_NAME = "USERID";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	private static final String USERID = "USERID";
	
	/**
	 * Constructor
	 */
	private ValidateUserid() {	
	}
	
	/**
	 * This routine is used to validate the parameters to the userid services.
	 * @param db
	 * @param personId
	 * @param updatedBy
	 * @return UseridTable
	 * @throws CprException
	 */
	public static UseridTable validateUseridParameters(Database db, long personId, String updatedBy) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		return new UseridTable(personId, localUpdatedBy);

	}
	
	/**
	 * This routine is used to validate the parameters for the GetUserid service.
	 * @param db contains an instance of the database connection.
	 * @param personId contains the person identifier.
	 * @param requestedBy contains the user that requested the search.
	 * @param returnHistory contains a Y/N that indicates whether history is to be returned or not.
	 * @return UseridTable upon success an instance of a UseridTable will be returned.
	 * @throws CprException will be thrown for any CPR specific problems.
	 */
	public static UseridTable validateGetUseridParameters(Database db, long personId, String requestedBy, 
			String returnHistory) throws CprException {
		
		final UseridTable useridTable = validateUseridParameters(db, personId, requestedBy);
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		useridTable.setReturnHistoryFlag(returnHistoryFlag);
		return useridTable;		
	}
	
	/**
	 * This routine is used to validate information for when a userid is specified as part of a service call.  Cases
	 * in which this happen are during the call of SetPrimaryUserid, ArchiveUserid, UnarchiveUserid, and AddSpecialUserid.
	 * @param db contains a reference to an open database connection.
	 * @param personId contains the person's identifier in the CPR.
	 * @param userid contains the userid that is being used by the service.
	 * @param updatedBy contains the userid and/or service principal that initiated this service call.
	 * @return will return a UseridTable class upon a successful execution of this routine.
	 * @throws CprException will be thrown for any CPR related exceptions.
	 */
	public static UseridTable validateUseridParameters(Database db, long personId, String userid, String updatedBy) 
		throws CprException {
		
		db.getAllTableColumns("USERID");
		UseridTable useridTable = validateUseridParameters(db, personId, updatedBy);
		String localUserid = ValidateHelper.checkField(db, userid, USERID, "Userid", true);
		useridTable.getUseridBean().setUserid(localUserid);
		return useridTable;		
	}
}
