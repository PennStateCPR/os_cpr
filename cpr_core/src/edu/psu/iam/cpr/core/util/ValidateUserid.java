/* SVN FILE: $Id: ValidateUserid.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

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
	 * @throws GeneralDatabaseException 
	 * @throws CprException
	 */
	public static UseridTable validateUseridParameters(Database db, long personId, String updatedBy) throws CprException, GeneralDatabaseException {
		
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : null;

		// Verify that they have specified an updateby.
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Userid/server principal");
		}
		
		// Check the length of the updated by against the database to ensure that its less/equal the maximum column length.
		db.getAllTableColumns("USERID");
		if (localUpdatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Userid/server principal");
		}
			
		// Success, build the object.
		return new UseridTable(personId, localUpdatedBy);
	}
	
	/**
	 * This routine is used to validate the parameters for the GetUserid service.
	 * @param db contains an instance of the database connection.
	 * @param personId contains the person identifier.
	 * @param requestedBy contains the user that requested the search.
	 * @param returnHistory contains a Y/N that indicates whether history is to be returned or not.
	 * @return UseridTable upon success an instance of a UseridTable will be returned.
	 * @throws GeneralDatabaseException will be thrown if there are any database problems.
	 * @throws CprException will be thrown for any CPR specific problems.
	 */
	public static UseridTable validateGetUseridParameters(Database db, long personId, String requestedBy, String returnHistory) throws CprException, GeneralDatabaseException {
		
		final UseridTable useridTable = validateUseridParameters(db, personId, requestedBy);
		String localReturnHistory = returnHistory;

		// Verify the return history flag, and set its value to the boolean.
		if ((localReturnHistory = Validate.isValidYesNo(localReturnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		useridTable.setReturnHistoryFlag((localReturnHistory.equals("Y")) ? true : false);
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
	 * @throws GeneralDatabaseException will be thrown for any database exceptions.
	 */
	public static UseridTable validateUseridParameters(Database db, long personId, String userid, String updatedBy) throws CprException, GeneralDatabaseException {
		
		UseridTable useridTable = validateUseridParameters(db, personId, updatedBy);
		
		String localUserid = (userid != null) ? userid.trim() : null;

		// Verify that they have specified an userid.
		if (localUserid == null || localUserid.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Userid");
		}
		
		// Check the length of the updated by against the database to ensure that its less/equal the maximum column length.
		db.getAllTableColumns("USERID");
		if (localUserid.length() > db.getColumn("USERID").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Userid");
		}
			
		// Success, build the object.
		useridTable.getUseridBean().setUserid(localUserid);
		return useridTable;		
	}
}
