/* SVN FILE: $Id: ValidatePersonUseridIap.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
 * This class provides an implementation of functions that perform iap information validation. 
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

/**
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
 */
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable;
import edu.psu.iam.cpr.core.error.CprException;

public final class ValidatePersonUseridIap {
	
	private static final String DATABASE_TABLE_NAME = "PERSON_USERID_IAP";
	private static final String USERID = "USERID";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	
	private static final String USERID_STRING = "Userid";

	/**
	 * Constructor
	 */
	private ValidatePersonUseridIap() {
		
	}
	
	/**
	 * This routine is used to validate the parameters to the GetPSUIAP service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param userid contains the userid associated with personId
	 * @param requestedBy contains the userid or server identifier of the user who is requesting the add.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned or not.
	 * @return PersonUseridIapTable
	 * @throws CprException 
	 */
	public static PersonUseridIapTable validateGetPsuIapParameters(final Database db, final long personId, final String userid, final String requestedBy, 
			final String returnHistory) throws CprException {
	
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		@SuppressWarnings("unused")
		String localUserid = ValidateHelper.checkField(db, userid, USERID, USERID_STRING, true);
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);

		// Validate the return history flag.
		final PersonUseridIapTable personUseridIapTable = new PersonUseridIapTable();
		personUseridIapTable.setReturnHistoryFlag(returnHistoryFlag);
		
		return personUseridIapTable;		
	}
	
	/**
	 * This routine is used to validate the parameters to the GetExternalIAP service.
	 * @param db contains the database connection
	 * @param personId contains the user's person identifier.
	 * @param userid contains the userid associated with personId
	 * @param requestedBy contains the userid or server identifier of the user who is requesting the add.
	 * @param federation - the federation
	 * 
	 * @throws CprException 
	 */
	public static void validateGetExternalIapParameters(final Database db, final long personId, final String userid, 
			final String requestedBy, final String federation) throws CprException {
	

		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		@SuppressWarnings("unused")
		String localUserid = ValidateHelper.checkField(db, userid, USERID, USERID_STRING, true);
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
		
		db.getAllTableColumns("FEDERATION");
		@SuppressWarnings("unused")
		String localFederation = ValidateHelper.checkField(db, federation, "FEDERATION", "Federation", true);

	}

}
