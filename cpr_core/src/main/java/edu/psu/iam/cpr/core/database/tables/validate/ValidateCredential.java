/* SVN FILE: $Id: ValidateCredential.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.CredentialTable;
import edu.psu.iam.cpr.core.error.CprException;

/**
 * This class provides an implementation of functions that perform credential information validation.
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

public final class ValidateCredential {

	private static final String DATABASE_TABLE_NAME = "CREDENTIAL";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	private static final String CREDENTIAL_DATA = "CREDENTIAL_DATA";

	/**
	 * Constructor
	 */
	private ValidateCredential() {
	}
	
	/**
	 * This routine is used to validate parameters that were passed to the GetCredential service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param requestedBy contains the service principal and/or userid that requested the information.
	 * @param credentialType contains the credential type.
	 * @param returnHistory Y/N flag that indicates whether to return history records or not.
	 * @param credentialKey contains the credential key that is used for a RESTful query.
	 * @return CredentialTable will be return if this function is successful.
	 * @throws InvalidParametersException
	 * @throws CprException 
	 */
	public static CredentialTable validateGetCredentialParameters(final Database db, final long personId, 
			final String requestedBy, final String credentialType, final String returnHistory, 
			final String credentialKey) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
				
		final CredentialTable credentialTable = new CredentialTable();
		
		if (credentialKey == null) {
			boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
			if (credentialType != null) {
				credentialTable.setCredentialType(credentialTable.findCredentialEnum(credentialType));
			}
			credentialTable.setReturnHistoryFlag(returnHistoryFlag);
		}
		else {
			credentialTable.setCredentialKey(Long.valueOf(credentialKey));
		}
		
		return credentialTable;
	}
	
	/**
	 * This routine is used to validate the parameters to the ArchiveName service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param credentialType contains the credential type to be archived.
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the archive.
	 * @return CredentialType class.
	 * @throws CprException 
	 */
	public static CredentialTable validateArchiveCredentialParameters(final Database db, final long personId, 
			final String credentialType, final String updatedBy) 
		throws CprException {
	
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		String localCredentialType = ValidateHelper.checkField(db, credentialType, null, "Credential type", false);
		
		return new CredentialTable(personId, localCredentialType, localUpdatedBy);
	}
	
	/**
	 * This routine is used to validate the input for the AddCredential and UpdateCredential service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param credentialType contains the type of credential being added or updated.
	 * @param credentialData contains the additional data associated with the credential.
	 * @param updatedBy contains the system identifier and/or userid that updated the name.
	 * @return CredentialTable class
	 * @throws CprException 
	 */
	public static CredentialTable validateAddCredentialParameters(final Database db, final long personId, 
			final String credentialType, final String credentialData, 
			final String updatedBy) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		String localCredentialData = ValidateHelper.checkField(db, credentialData, CREDENTIAL_DATA, "Credential data", true);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		String localCredentialType = ValidateHelper.checkField(db, credentialType, null, "Credential type", false);	
		
		return new CredentialTable(personId, localCredentialType, localCredentialData, localUpdatedBy);
	}
	
}
