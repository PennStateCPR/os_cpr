/* SVN FILE: $Id: ValidatePersonAffiliation.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
  This class provides an implementation of functions that perform affiliation information validation. 
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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.error.CprException;

/**
 * This class is responsible for the validation of the Person Affiliation data.
 * @author llg5
 *
 */
public final class ValidatePersonAffiliation {

	private static final String UPDATED_BY_STRING = "Updated by";
	private static final String DATABASE_TABLE_NAME ="PERSON_AFFILIATION";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";

	/**
	 * Constructor.
	 */
	private ValidatePersonAffiliation() {
		
	}

	/**
	 * This routine is used to validate the parameters to the AddAffiliation, SetPrimaryAffiliation and UpdateAffiliation  service
	 * @param db contains the reference to the database connection
	 * @param personId contains the user's person identifier.
	 * @param affiliation contains the name type to be deleted.
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the delete.
	 * @return PersonAffiliationTable class.
	 * @throws CprException 
	 * 
	 */
	public static PersonAffiliationTable validateAddAffiliationParameters(Database db, long personId, String affiliation,  String updatedBy) 
		throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		final String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, UPDATED_BY_STRING, true);
		final String localAffiliation = ValidateHelper.checkField(null, affiliation, null, "Affiliation", false);
		return new PersonAffiliationTable(personId, localAffiliation, localUpdatedBy);
	}

	/**
	 * This routine is used to validate the parameters to the ArchiveAffiliation  service
	 * @param db contains the reference to the database connection
	 * @param personId contains the user's person identifier.
	 * @param affiliation contains the name type to be deleted
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the delete.
	 * @return PersonAffilationTable class.
	 * @throws CprException 
	 * 
	 */
	public static PersonAffiliationTable validateArchiveAffiliationParameters(Database db, long personId, String affiliation,  
			String updatedBy) throws CprException {
		return validateAddAffiliationParameters(db, personId, affiliation, updatedBy);
	}
	
	/**
	 * This routine is used to validate the parameters to the GetInternalAffiliation  service
	 * @param db contains the reference to the database connection
	 * @param personId contains the user's person identifier.
	 * @param requestedBy contains the userid or server identifier of the user who is requesting the delete.'
	 * @param returnHistory Y/N flag that indicates whether to return history or not.
	 * @return PersonAffiliationTable
	 * @throws CprException 
	 * 
	 */
	public static PersonAffiliationTable validateGetAffiliationsForPersonIdParameters(Database db, long personId,  String requestedBy, 
			String returnHistory) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", false);
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		
		final PersonAffiliationTable personAffiliationTable = new PersonAffiliationTable();
		personAffiliationTable.setReturnHistoryFlag(returnHistoryFlag);
		
		return personAffiliationTable;
		
	}

	/**
	 * @return a string value.
	 */
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode());
	}
}
