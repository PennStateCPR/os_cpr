/* SVN FILE: $Id: ValidateName.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.types.DocumentType;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation of functions that perform name information validation.
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
public final class ValidateName {
	
	private static final String DATABASE_TABLE_NAME = "NAMES";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	private static final String LAST_NAME = "LAST_NAME";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String MIDDLE_NAMES = "MIDDLE_NAMES";
	private static final String SUFFIX = "SUFFIX";
	private static final String NICKNAME = "NICKNAME";

	/** 
	 * Constructor.
	 */
	private ValidateName() {
	}
	
	/**
	 * This routine is used to validate parameters that were passed to the GetName service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param requestedBy contains the service principal and/or userid that requested the information.
	 * @param nameType contains the type of name to be retrieved, if specified.
	 * @param returnHistory flag that indicates whether history must be returned or not.
	 * @param nameKey contains the nameKey that would be passed in for a RESTful API.
	 * @return will return a names table object if successful.
	 * @throws CprException will be thrown if there are any CPR exceptions.
	 */
	public static NamesTable validateGetNameParameters(final Database db, final long personId, final String requestedBy, final String nameType, 
			final String returnHistory, final String nameKey) 
						throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// Trim all of the strings.
		@SuppressWarnings("unused")
		final String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
		
		String localNameType = null;
		boolean returnHistoryFlag = false;
		Long nameKeyValue = -1L;
		
		if (nameKey == null) {
			if (nameType != null) {
				localNameType = nameType.trim().toUpperCase();
			}
			returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		}
		else {
			nameKeyValue = Long.valueOf(nameKey);
		}
		
		// If a name type was specified, verify that its valid.
		final NamesTable namesTable = new NamesTable();
		if (localNameType != null) {
			namesTable.setNameType(namesTable.findNameTypeEnum(localNameType));
		}
		namesTable.setReturnHistoryFlag(returnHistoryFlag);
		namesTable.setNameKey(nameKeyValue);
		
		return namesTable;
	}
	
	/**
	 * This routine is used to validate the parameters to the ArchiveName service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param nameType contains the name type to be deleted.
	 * @param documentType contains the document type.
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the delete.
	 * @param nameKey contains the nameKey value, only used for RESTful services.
	 * @return NamesTable class.
	 * @throws CprException 
	 */
	public static NamesTable validateArchiveNameParameters(final Database db, final long personId, final String nameType,final  String documentType, 
			final String updatedBy, final String nameKey) throws CprException {
	
		db.getAllTableColumns(DATABASE_TABLE_NAME);

		final String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		Long nameKeyValue = -1L;
		String localNameType = null;
		String localDocumentType = null;

		if (nameKey == null) {
			if (nameType != null) {
				localNameType = nameType.trim().toUpperCase();
			}
			localNameType = ValidateHelper.checkField(null, localNameType, null, "Name type", false);

			if (documentType != null && documentType.trim().length() != 0) {
				localDocumentType = documentType.trim().toUpperCase();
			}

			if (! isValidCombination(localNameType, localDocumentType)) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Name type and/or document type");
			}
		}
		else {
			nameKeyValue = Long.valueOf(nameKey);
		}
		
		final NamesTable namesTable = new NamesTable(personId, localNameType, localDocumentType, localUpdatedBy);
		namesTable.setNameKey(nameKeyValue);
		
		return namesTable;
	}
	
	/**
	 * This routine is used to validate the input for the AddName and UpdateName service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param nameType contains the type of name being added or updated.
	 * @param documentType contains the document type.
	 * @param firstName contains the user's first name.
	 * @param middleNames contains the user's middle name.
	 * @param lastName contains the user's last name.
	 * @param suffix contains the user's suffix. 
	 * @param nickname contains the user's nickname.
	 * @param updatedBy contains the system identifier and/or userid that updated the name.
	 * @return NameTable class
	 * @throws CprException 
	 */
	public static NamesTable validateAddNameParameters(final Database db, final long personId, final String nameType, final String documentType, 
			final String firstName, final String middleNames, final String lastName, final String suffix, final String nickname, 
			final String updatedBy) throws CprException {
		
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// Trim all of the strings if they are not null.
		String localFirstName = ValidateHelper.trimField(firstName);
		String localMiddleNames = ValidateHelper.trimField(middleNames);		
		String localSuffix = ValidateHelper.trimField(suffix);
		String localNickname = ValidateHelper.trimField(nickname);
		
		String localLastName = ValidateHelper.checkField(db, lastName, LAST_NAME, "Last name", true);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		String localNameType = null;
		if (nameType != null) {
			localNameType = nameType.trim().toUpperCase();
		}
		localNameType = ValidateHelper.checkField(null, localNameType, null, "Name type", false);
		
		String localDocumentType = null;
		if (documentType != null && documentType.trim().length() != 0) {
			localDocumentType = documentType.trim().toUpperCase();
		}
		
		final String dbColumnNames[] = { FIRST_NAME, MIDDLE_NAMES, SUFFIX, NICKNAME };
		final String inputFields[]   = { localFirstName, localMiddleNames, localSuffix, localNickname };
		final String prettyNames[] = { "First name", "Middle name(s)", "Suffix", "Nickname" };
		
		// Verify that the lengths for the individual fields do not exceed the maximum for the database.
		for (int i = 0; i < dbColumnNames.length; ++i) {
			if (inputFields[i] != null && (! ValidateHelper.isFieldLengthValid(db, inputFields[i], dbColumnNames[i]))) {
				throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, prettyNames[i]);
			}
		}
				
		// Validate that the name type and document type combination is valid.
		if (! isValidCombination(localNameType, localDocumentType)) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Name type and/or document type");
		}
				
		// At this point we are ready to save off the information to a class.
		return new NamesTable(personId, localNameType, localDocumentType, localFirstName, localMiddleNames, 
					localLastName, localSuffix, localNickname, localUpdatedBy);
	}
	
	/**
	 * This routine is used to determine if a valid combination was specified for name type and document type.
	 * @param nameType contains the name type that was passed in.
	 * @param documentType contains the document type that was passed in.
	 * @return will return true if the combination is valid, otherwise it will return false.
	 */
	public static boolean isValidCombination(final String nameType, final String documentType) {
		
		NameType nameEnum = Utility.getEnumFromString(NameType.class, nameType);
		DocumentType documentTypeEnum = Utility.getEnumFromString(DocumentType.class, documentType);
			
		// If either the document type and/or name type could not have been converted return false.
		if ((nameType != null && nameEnum == null) ||
				(documentType != null && documentTypeEnum == null)) {
			return false;
		}
		
		// If the document type was specified, verify that a name type was specified and that the name type was
		// DOCUMENTED_NAME.
		if (documentTypeEnum != null) {
			if (nameEnum == null || nameEnum != NameType.DOCUMENTED_NAME) {
				return false;
			}
		}
		else {
			if (nameEnum != null && nameEnum == NameType.DOCUMENTED_NAME) {
				return false;
			}
		}
		
		// Success!
		return true;
	}
}
