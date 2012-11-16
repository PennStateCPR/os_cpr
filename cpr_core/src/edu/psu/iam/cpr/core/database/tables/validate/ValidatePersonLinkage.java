/* SVN FILE: $Id: ValidatePersonLinkage.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonLinkageTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * This class contains functions that are used to validate information that was specified during an Add/Archive/Get 
 * person linkage service calls.
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
public final class ValidatePersonLinkage {

	/**
	 * Constructor
	 */
	private ValidatePersonLinkage() {
		
	}
	
	/**
	 * This routine is used to validate the data passed in for GetPersonLinkage service call.  The routine will return
	 * if successful, otherwise it will throw an exception.
	 * @param db contains an open database connection.
	 * @param personId contains the person identifier that the get is being performed on.
	 * @param requestedBy contains the userid of the person making the request.
	 * @param returnHistory Y/N flag indicating whether history is to be returned for the GET.
	 * @return PersonLinkageTable
	 * @throws CprException will be thrown for CPR specific errors.
	 */
	public static PersonLinkageTable validateGetPersonLinkageParameters(Database db, long personId, String requestedBy, String returnHistory) 
					throws CprException {
		
		// Trim off all of the string parameters.
		String localRequestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		String localReturnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		
		// Verify that the localRequestedBy was specified and its length is valid.
		if (localRequestedBy == null || localRequestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
		
		db.getAllTableColumns("PERSON_LINKAGE");
		if (localRequestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
		final PersonLinkageTable personLinkageTable = new PersonLinkageTable();

		// Verify the return history flag, and set its value to the boolean.
		localReturnHistory = Validate.isValidYesNo(localReturnHistory);
		if (localReturnHistory == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		personLinkageTable.setReturnHistoryFlag((localReturnHistory.equals("Y")) ? true : false);
		
		return personLinkageTable;
	}
	
	/**
	 * This routine is used to validate parameters for an Add, Update and Archive person linkage services.  If the validation
	 * was successful, a person linkage table will be returned, otherwise an exception will be thrown.
	 * @param db contains an open database connection.
	 * @param personId contains the linker person id (already validated).
	 * @param linkedIdentifierType
	 * @param linkedIdentifier
	 * @param linkageType contains the linkage type.
	 * @param updatedBy contains the user who requested this service.
	 * @return will return a person linkage table if successful.
	 * @throws CprException will be thrown for CPR specific errors.
	 * @throws InvalidParametersException 
	 * @throws NotSpecifiedException 
	 */
	public static PersonLinkageTable validatePersonLinkageParameters(Database db, long personId, String linkedIdentifierType, 
			String linkedIdentifier, String linkageType, String updatedBy) throws CprException {
		
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		String localLinkageType = null;
		if (linkageType != null) {
			if (linkageType.trim().length() != 0) {
				localLinkageType = linkageType.trim().toUpperCase();
			}
		}
		
		// Verify that the requestedBy was specified and its length is valid.
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		
		db.getAllTableColumns("PERSON_LINKAGE");
		if (localUpdatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
		
		final long linkedPersonId = db.getPersonId(linkedIdentifierType, linkedIdentifier);
		if (personId == linkedPersonId) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Linked Person Identifier.");
		}
		
		return new PersonLinkageTable(personId, linkedPersonId, localLinkageType, localUpdatedBy);
	}
}
