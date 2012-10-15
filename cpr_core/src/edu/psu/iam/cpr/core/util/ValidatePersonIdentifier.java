/* SVN FILE: $Id: ValidatePersonIdentifier.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.tables.PersonIdentifierTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * This class contains functions that are used to validate information that was specified during an Add/Archive/Get 
 * person identifier service calls.
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
public class ValidatePersonIdentifier {
	
	/**
	 * This private routine is used to validate common parameters that are passed to the various person identifier services.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier.
	 * @param identifierTypeName contains the name of the identifier type.
	 * @param requestedBy contains the person who requested the search.
	 * @return A PersonIdentifierTable object will be returned if this routine was successful.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 * @throws GeneralDatabaseException will be thrown if there are any general database problems.
	 */
	private static PersonIdentifierTable validateCommonParameters(Database db, 
			long personId, 
			String identifierTypeName,
			String requestedBy) throws CprException, GeneralDatabaseException
	{
		// Trim off all of the string parameters.
		requestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		identifierTypeName = (identifierTypeName != null) ? identifierTypeName.toUpperCase().trim() : null;

		// Verify that the requestedBy was specified and its length is valid.
		if (requestedBy == null || requestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}

		db.getAllTableColumns("PERSON_IDENTIFIER");
		if (requestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}

		final PersonIdentifierTable personIdentifierTable = new PersonIdentifierTable(personId, requestedBy);

		// Validate the identifier type string if one was specified.
		if (identifierTypeName != null) {
			final IdentifierType identifierType = Validate.isValidIdentifierType(db, identifierTypeName);
			if (identifierType == null) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Identifier type");
			}
			if (identifierType.getCanAssignFlag().equals("N")) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Identifier type");
			}
			personIdentifierTable.setIdentifierType(identifierType);
		}
		
		return personIdentifierTable;
	}

	/**
	 * This routine is used to validate information passed to a get person identifier service.
	 * @param db contains the database connection.
	 * @param personId person identifier.
	 * @param identifierTypeName contains the identifier type name to be searched for if specified.
	 * @param requestedBy contains the person who requested the search.
	 * @param returnHistory Y/N flag to indicate whether history is to be used or not.
	 * @return will return a PersonIdentifierTable object if successful.
	 * @throws GeneralDatabaseException will be thrown if there are any database problems.
	 * @throws CprException will be thrown if there are any CPR related issues.
	 */
	public static PersonIdentifierTable validateGetPersonIdentifierParameters(Database db, 
			long personId, 
			String identifierTypeName,
			String requestedBy, 
			String returnHistory) throws GeneralDatabaseException, CprException {

		PersonIdentifierTable personIdentifierTable = validateCommonParameters(db, personId, identifierTypeName, requestedBy);
		
		// Verify the return history flag, and set its value to the boolean.
		returnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		personIdentifierTable.setReturnHistoryFlag((returnHistory.equals("Y")) ? true : false);

		return personIdentifierTable;
	}
	
	/**
	 * This routine is used to validate the archive person identifier parameters.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier whose information will be archived.
	 * @param identifierTypeName contains the identifier type name.
	 * @param requestedBy contains the entity that has requested the archive.
	 * @return will return a PersonIdentifierTable object if successful.
	 * @throws GeneralDatabaseException will be thrown if there is a general database problem.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	public static PersonIdentifierTable validateArchivePersonIdentifierParameters(Database db, 
			long personId, 
			String identifierTypeName,
			String requestedBy) throws GeneralDatabaseException, CprException {
	
		PersonIdentifierTable personIdentifierTable = validateCommonParameters(db, personId, 
				identifierTypeName, requestedBy);
		
		// Verify that an identifier was specified.
		if (personIdentifierTable.getIdentifierType() == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Identifier type");
		}
		
		return personIdentifierTable;
	}
	
	/**
	 * This routine is used to validate parameters for the add person identifier service.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier whose information will be added.
	 * @param identifierTypeName contains the identifier type name.
	 * @param identifierValue contains the value of the identifier.
	 * @param requestedBy contains the entity that has requested the archive.
	 * @return will return a PersonIdentifierTable object if successful.
	 * @throws GeneralDatabaseException will be thrown if there is a general database problem.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	public static PersonIdentifierTable validateAddPersonIdentifierParameters(Database db, 
			long personId, 
			String identifierTypeName,
			String identifierValue,
			String requestedBy) throws GeneralDatabaseException, CprException {
	
		// Validate the identifier value to ensure its specified and contains a valid length.
		identifierValue = (identifierValue != null) ? identifierValue.trim() : null;
		if (identifierValue == null || identifierValue.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Identifier value");
		}
		db.getAllTableColumns("PERSON_IDENTIFIER");
		if (identifierValue.length() > db.getColumn("IDENTIFIER_VALUE").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Identifier value");
		}
		
		PersonIdentifierTable personIdentifierTable = validateCommonParameters(db, personId, 
				identifierTypeName, requestedBy);
		
		personIdentifierTable.getPersonIdentifierBean().setIdentifierValue(identifierValue);
		
		// Ensure they have specified a identifier type.
		if (personIdentifierTable.getIdentifierType() == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Identifier type");
		}
		
		return personIdentifierTable;
	}

}
