/* SVN FILE: $Id: ValidateConfidentiality.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.error.CprException;

/**
 * The purpose of this class is to validate information specified with a confidentiality hold.
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
public final class ValidateConfidentiality {
	
	private static final String UPDATED_BY_STRING = "Updated by";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	private static final String DATABASE_TABLE_NAME = "CONFIDENTIALITY";
	
	/**
	 * Constructor.
	 */
	private ValidateConfidentiality() {
	}
	
	/**
	 * The purpose of this routine is to validate the information specified as part of an AddConfidentialityHold and/or
	 * UpdateConfidentialityHold service calls.  If there are any errors encountered, an exception will be thrown.  Otherwise
	 * a ConfidentialityTable object will be returned.
	 * @param db contains a reference to an open database connection.
	 * @param personId contains the user' person identifier.
	 * @param confidentialityType contains the type of confidentiality hold.
	 * @param updatedBy contains the person assigning the hold.
	 * @return will return a ConfidentialityTable if successful validation.
	 * @throws CprException will be thrown for any validation problems.
	 */
	public static ConfidentialityTable validateAddConfidentialityParameters(final Database db, final long personId, final String confidentialityType, 
			String updatedBy) throws CprException {

		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// Trim all of the input parameters that are string.
		String localConfidentialityType = null;
		if (confidentialityType != null) {
			localConfidentialityType = confidentialityType.trim().toUpperCase();
		}
		
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, UPDATED_BY_STRING, true);
		localConfidentialityType = ValidateHelper.checkField(null, localConfidentialityType, null, "Confidentiality type", false);
						
		// Build the return database table class.
		return new ConfidentialityTable(personId, localConfidentialityType, localUpdatedBy);
	}

	/**
	 * The purpose of this routine is to validate the information specified as part of an ArchiveConfidentialityHold service call.  
	 * If there are any errors encountered, an exception will be thrown.  Otherwise a ConfidentialityTable object will be returned.
	 * @param db contains a reference to an open database connection.
	 * @param personId contains the user' person identifier.
	 * @param confidentialityType contains the type of confidentiality hold.
	 * @param updatedBy contains the person assigning the hold.
	 * @return will return a ConfidentialityTable if successful validation.
	 * @throws CprException will be thrown for any validation problems.
	 */
	public static ConfidentialityTable validateArchiveConfidentialityParameters(final Database db, final long personId, final String confidentialityType, 
			String updatedBy) throws CprException {
		
		return validateAddConfidentialityParameters(db, personId, confidentialityType, updatedBy);
		
	}
	
	/**
	 * The purpose of this routine is to validate the information specified as part of an GetConfidentialityHold service call.  
	 * If there are any errors encountered, an exception will be thrown.  Otherwise a ConfidentialityTable object will be returned.
	 * @param db contains a reference to an open database connection.
	 * @param personId contains the user' person identifier.
	 * @param updatedBy contains the person assigning the hold.
	 * @param returnHistory Y/N flag to indicate whether to return history or not.
	 * @return ConfidentialityTable will contain a database implementation and bean.
	 * @throws CprException will be thrown for any validation problems.
	 * @throws InvalidParametersException 
	 */
	public static ConfidentialityTable validateGetConfidentialityParameters(final Database db, final long personId, 
			final String updatedBy, final String returnHistory) 
		throws CprException {

		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// Trim all of the input parameters that are string.
		@SuppressWarnings("unused")
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, UPDATED_BY_STRING, true);
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);

		final ConfidentialityTable confidentialityTable = new ConfidentialityTable();
		confidentialityTable.setReturnHistoryFlag(returnHistoryFlag);

		return confidentialityTable;
	}
}
