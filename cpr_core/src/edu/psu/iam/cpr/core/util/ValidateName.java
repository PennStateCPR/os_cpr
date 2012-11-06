/* SVN FILE: $Id: ValidateName.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.types.DocumentType;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

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
	 * @return will return a names table object if successful.
	 * @throws CprException will be thrown if there are any CPR exceptions.
	 */
	public static NamesTable validateGetNameParameters(Database db, long personId, String requestedBy, String nameType, String returnHistory) 
						throws CprException {
		
		// Trim all of the strings.
		String localRequestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		
		String localNameType = null;
		if (nameType != null) {
			if (nameType.trim().length() != 0) {
				localNameType = nameType.trim().toUpperCase();
			}
		}
		
		String localReturnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		
		// Ensure that a requested by was specified.
		if (localRequestedBy == null || localRequestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
		
		db.getAllTableColumns("NAMES");
		if (localRequestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}

		// If a name type was specified, verify that its valid.
		final NamesTable namesTable = new NamesTable();
		if (localNameType != null) {
			namesTable.setNameType(namesTable.findNameTypeEnum(localNameType));
		}

		// Verify the return history flag, and set its value to the boolean.
		if ((localReturnHistory = Validate.isValidYesNo(localReturnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		namesTable.setReturnHistoryFlag((localReturnHistory.equals("Y")) ? true : false);
		
		return namesTable;
	}
	
	/**
	 * This routine is used to validate the parameters to the ArchiveName service.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier.
	 * @param nameType contains the name type to be deleted.
	 * @param documentType contains the document type.
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the delete.
	 * @return NamesTable class.
	 * @throws CprException 
	 */
	public static NamesTable validateArchiveNameParameters(Database db, long personId, String nameType, String documentType, 
			String updatedBy) throws CprException {
	
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		String localNameType = null;
		if (nameType != null) {
			if (nameType.trim().length() != 0) {
				localNameType = nameType.trim().toUpperCase();
			}
		}
		
		String localDocumentType = null;
		if (documentType != null) {
			if (documentType.trim().length() != 0) {
				localDocumentType = documentType.trim().toUpperCase();
			}
		}
		
		if (localNameType == null || localNameType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Name type");
		}
		
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
	
		// Verify that the length is OK for updated By.
		db.getAllTableColumns("NAMES");
		if (localUpdatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
					
		// Validate that the name type and document type combination is valid.
		if (! isValidCombination(localNameType, localDocumentType)) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Name type and/or document type");
		}
		
		return new NamesTable(personId, localNameType, localDocumentType, localUpdatedBy);
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
	 * @param updatedBy contains the system identifier and/or userid that updated the name.
	 * @return NameTable class
	 * @throws CprException 
	 */
	public static NamesTable validateAddNameParameters(Database db, long personId, String nameType, String documentType, String firstName, 
				String middleNames, String lastName, String suffix, String updatedBy) throws CprException {
		
		
		// Trim all of the strings if they are not null.
		String localFirstName = (firstName != null) ? firstName.trim() : null;
		String localMiddleNames = (middleNames != null) ? middleNames.trim() : null;
		String localLastName = (lastName != null) ? lastName.trim() : null;
		String localSuffix = (suffix != null) ? suffix.trim() : null;
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		String localNameType = null;
		if (nameType != null) {
			if (nameType.trim().length() != 0) {
				localNameType = nameType.trim().toUpperCase();
			}
		}
		
		String localDocumentType = null;
		if (documentType != null) {
			if (documentType.trim().length() != 0) {
				localDocumentType = documentType.trim().toUpperCase();
			}
		}
		final String dbColumnNames[] = { "FIRST_NAME", "MIDDLE_NAMES", "LAST_NAME", "SUFFIX", "LAST_UPDATE_BY" };
		final String inputFields[]   = { localFirstName, localMiddleNames, localLastName, localSuffix, localUpdatedBy };
		final String prettyNames[] = { "First name", "Middle name(s)", "Last name", "Suffix", "Updated by" };
		
		// Ensure that a name type, updated by and last name was specified.
		if (localNameType == null || localNameType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Name type");
		}
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		if (localLastName == null || localLastName.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Last name");
		}

		// Verify that the lengths for the individual fields do not exceed the maximum for the database.
		db.getAllTableColumns("NAMES");
		for (int i = 0; i < dbColumnNames.length; ++i) {
			if (inputFields[i] != null && inputFields[i].length() > db.getColumn(dbColumnNames[i]).getColumnSize()) {
				throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, prettyNames[i]);
			}
		}
				
		// Validate that the name type and document type combination is valid.
		if (! isValidCombination(localNameType, localDocumentType)) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Name type and/or document type");
		}
		
		//+TODO verify that no one can specify a name type of PROFESSIONAL_NAME 
		
		// At this point we are ready to save off the information to a class.
		return new NamesTable(personId, localNameType, localDocumentType, localFirstName, localMiddleNames, 
					localLastName, localSuffix, localUpdatedBy);
	}
	
	/**
	 * This routine is used to determine if a valid combination was specified for name type and document type.
	 * @param nameType contains the name type that was passed in.
	 * @param documentType contains the document type that was passed in.
	 * @return will return true if the combination is valid, otherwise it will return false.
	 */
	public static boolean isValidCombination(String nameType, String documentType) {
		
		NameType nameEnum = null;
		DocumentType documentTypeEnum = null;
		
		if (nameType == null) {
			return false;
		}
		
		// Convert the nameType string to an enumerated type.
		try {
			nameEnum = NameType.valueOf(nameType);
		}
		catch (Exception e) {
		}

		
		// If a document type was specified, attempt to convert it to an enumerated type.
		if (documentType != null) {
			try {
				documentTypeEnum = DocumentType.valueOf(documentType);
			}
			catch (Exception e) {
			}
		}
		
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
