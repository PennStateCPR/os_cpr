/* SVN FILE: $Id: ValidatePersonIdentifier.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.tables.PersonIdentifierTable;
import edu.psu.iam.cpr.core.error.CprException;
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
public final class ValidatePersonIdentifier {
	
	private static final String IDENTIFIER_TYPE_STRING = "Identifier type";
	private static final String DATABASE_TABLE_NAME = "PERSON_IDENTIFIER";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	private static final String IDENTIFIER_VALUE = "IDENTIFIER_VALUE";

	/**
	 * Constructor
	 */
	private ValidatePersonIdentifier() {
		
	}
	
	/**
	 * This private routine is used to validate common parameters that are passed to the various person identifier services.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier.
	 * @param identifierTypeName contains the name of the identifier type.
	 * @param requestedBy contains the person who requested the search.
	 * @return A PersonIdentifierTable object will be returned if this routine was successful.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	private static PersonIdentifierTable validateCommonParameters(final Database db, 
			final long personId, 
			final String identifierTypeName,
			final String requestedBy) throws CprException
	{
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// Trim off all of the string parameters.
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
		
		String localIdentifierTypeName = null;
		if (identifierTypeName != null) {
			localIdentifierTypeName = identifierTypeName.trim().toUpperCase();
			localIdentifierTypeName = ValidateHelper.checkField(null, localIdentifierTypeName, null, "Identifier type", false);
		}

		final PersonIdentifierTable personIdentifierTable = new PersonIdentifierTable(personId, localRequestedBy);

		// Validate the identifier type string if one was specified.
		if (localIdentifierTypeName != null) {
			final IdentifierType identifierType = db.isValidIdentifierType(localIdentifierTypeName);
			if (identifierType == null) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, IDENTIFIER_TYPE_STRING);
			}
			if (identifierType.getCanAssignFlag().equals("N")) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, IDENTIFIER_TYPE_STRING);
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
	 * @throws CprException will be thrown if there are any CPR related issues.
	 */
	public static PersonIdentifierTable validateGetPersonIdentifierParameters(final Database db, 
			final long personId, 
			final String identifierTypeName,
			final String requestedBy, 
			final String returnHistory) throws CprException {

		PersonIdentifierTable personIdentifierTable = validateCommonParameters(db, personId, identifierTypeName, requestedBy);
		
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);		
		personIdentifierTable.setReturnHistoryFlag(returnHistoryFlag);

		return personIdentifierTable;
	}
	
	/**
	 * This routine is used to validate the archive person identifier parameters.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier whose information will be archived.
	 * @param identifierTypeName contains the identifier type name.
	 * @param requestedBy contains the entity that has requested the archive.
	 * @return will return a PersonIdentifierTable object if successful.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	public static PersonIdentifierTable validateArchivePersonIdentifierParameters(final Database db, 
			final long personId, 
			final String identifierTypeName,
			final String requestedBy) throws CprException {
	
		PersonIdentifierTable personIdentifierTable = validateCommonParameters(db, personId, 
				identifierTypeName, requestedBy);
		
		// Verify that an identifier was specified.
		if (personIdentifierTable.getIdentifierType() == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, IDENTIFIER_TYPE_STRING);
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
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	public static PersonIdentifierTable validateAddPersonIdentifierParameters(final Database db, 
			final long personId, 
			final String identifierTypeName,
			final String identifierValue,
			final String requestedBy) throws CprException {
	
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// Validate the identifier value to ensure its specified and contains a valid length.
		String localIdentifierValue = ValidateHelper.checkField(db, identifierValue, IDENTIFIER_VALUE, "Identifier value", true);
		
		PersonIdentifierTable personIdentifierTable = validateCommonParameters(db, personId, 
				identifierTypeName, requestedBy);
		
		personIdentifierTable.getPersonIdentifierBean().setIdentifierValue(localIdentifierValue);
		
		// Ensure they have specified a identifier type.
		if (personIdentifierTable.getIdentifierType() == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, IDENTIFIER_TYPE_STRING);
		}
		
		return personIdentifierTable;
	}

}
