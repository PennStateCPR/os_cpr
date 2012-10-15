/* SVN FILE: $Id: ValidateCredential.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.CredentialTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

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

public class ValidateCredential {

	
	/**
	 * This routine is used to validate parameters that were passed to the GetCredential service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param requestedBy contains the service principal and/or userid that requested the information.
	 * @param credentialType contains the credential type.
	 * @param returnHistory Y/N flag that indicates whether to return history records or not.
	 * @return CredentialTable will be reutrn if this function is successful.
	 * @throws InvalidParametersException
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static CredentialTable validateGetCredentialParameters(Database db, long personId, String requestedBy, String credentialType, String returnHistory) 
				throws GeneralDatabaseException, CprException {
		
		// If the strings are not null, trim them.
		requestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		returnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		
		if (requestedBy == null || requestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
		
		// Verify that updatedBy is within the length of the database field.
		db.getAllTableColumns("CREDENTIAL");
		if (requestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
		// Validate the credential type if one was specified.
		final CredentialTable credentialTable = new CredentialTable();
		if (credentialType != null) {
			try {
				credentialTable.setCredentialType(credentialType);
			}
			catch (Exception e) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Credential type");
			}
		}

		// Verify the return history flag, and set its value to the boolean.
		if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		credentialTable.setReturnHistoryFlag((returnHistory.equals("Y")) ? true : false);

		return credentialTable;
	}
	
	/**
	 * This routine is used to validate the parameters to the ArchiveName service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param credentialType contains the credential type to be archived.
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the archive.
	 * @return CredentialType class.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static CredentialTable validateArchiveCredentialParameters(Database db, long personId, String credentialType, String updatedBy) throws GeneralDatabaseException, CprException {
	
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		if (credentialType == null || credentialType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Credential type");
		}
		
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
	
		// Verify that the length is OK for updated By.
		db.getAllTableColumns("CREDENTIAL");
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
			
		// If the credential type contains an empty string, convert them to null objects.
		credentialType = (credentialType != null && credentialType.trim().length() == 0) ? null : credentialType;
		
		try {
			final CredentialTable credentialTable = new CredentialTable(personId, credentialType, updatedBy);
			return credentialTable;
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Credential type");
		}
	}
	
	/**
	 * This routine is used to validate the input for the AddCredential and UpdateCredential service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param credentialType contains the type of credential being added or updated.
	 * @param credentialData contains the additional data associated with the credential.
	 * @param updatedBy contains the system identifier and/or userid that updated the name.
	 * @return CredentialTable class
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static CredentialTable validateAddCredentialParameters(Database db, long personId, String credentialType, String credentialData, String updatedBy) throws GeneralDatabaseException, CprException {
		
		final String dbColumnNames[] = { "CREDENTIAL_DATA", "LAST_UPDATE_BY" };
		final String inputFields[]   = { credentialData, updatedBy };
		final String prettyNames[] = { "Credential data", "Updated by" };
		
		// Trim all of the strings if they are not null.
		credentialData = (credentialData != null) ? credentialData.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		// Ensure that a credential type, updated by and credential data was specified.
		if (credentialType == null || credentialType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Credential type");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		if (credentialData == null || credentialData.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Credential data");
		}

		// Verify that the lengths for the individual fields do not exceed the maximum for the database.
		db.getAllTableColumns("CREDENTIAL");
		for (int i = 0; i < dbColumnNames.length; ++i) {
			if (inputFields[i] != null && inputFields[i].length() > db.getColumn(dbColumnNames[i]).getColumnSize()) {
				throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, prettyNames[i]);
			}
		}
		
		// If the credential type contains an empty string, convert it to a null object.
		credentialType = (credentialType != null && credentialType.trim().length() == 0) ? null : credentialType;
		
		// At this point we are ready to save off the information to a class.
		try {
			final CredentialTable credentialTable = new CredentialTable(personId, credentialType, credentialData, updatedBy);
			return credentialTable;
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Credential type");
		}
	}
	
}
