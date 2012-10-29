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
package edu.psu.iam.cpr.core.util;

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
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

public final class ValidatePersonUseridIap {
	
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
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static PersonUseridIapTable validateGetPsuIapParameters(Database db, long personId, String userid, String requestedBy, String returnHistory) 
					throws GeneralDatabaseException, CprException {
	
		
		String localUserid = (userid != null) ? userid.trim() : null;
		String localReturnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		String localRequestedBy = (requestedBy != null) ? requestedBy.trim() : null;

		// Verify that they have specified an userid.
		if (localUserid == null || localUserid.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Userid");
		}
		
		// Verify that they have specified a requested by.
		if (localRequestedBy == null || localRequestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "requested by");
		}
	
		// Verify that the length is OK for updated By and localUserid
		db.getAllTableColumns("PERSON_USERID_IAP");
		if (localUserid.length() > db.getColumn("USERID").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Userid"); 
		}
		if (localRequestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requestedby");
		}
		
		// Validate the return history flag.
		final PersonUseridIapTable personUseridIapTable = new PersonUseridIapTable();
		if ((localReturnHistory = Validate.isValidYesNo(localReturnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		personUseridIapTable.setReturnHistoryFlag((localReturnHistory.equals("Y")) ? true : false);
		
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
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static void validateGetExternalIapParameters(Database db, long personId, String userid, String requestedBy, String federation) throws GeneralDatabaseException, CprException {
	

		String localUserid = (userid != null) ? userid.trim() : null;
		String localRequestedBy = (requestedBy != null) ? requestedBy.trim() : null;

		// Verify that they have specified an localUserid.
		if (localUserid == null || localUserid.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Userid");
		}
		
		// Verify that they have specified an federation
		if (federation == null || federation.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Federation");
		}
		db.getAllTableColumns("FEDERATION");
		
		if (federation.length() > db.getColumn("FEDERATION").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Federation"); 
		}
		
		
		if (localRequestedBy == null || localRequestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
	
		// Verify that the length is OK for updated By, localUserid and federation.
		db.getAllTableColumns("PERSON_USERID_IAP");
		if (localUserid.length() > db.getColumn("USERID").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Userid"); 
		}
		
		if (localRequestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
	}

}
