/* SVN FILE: $Id: ValidatePerson.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * The class provides an implementation of functions that are used to validate information specified during an
 * Add/Get person service call.
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
public final class ValidatePerson {
	
	/**
	 * Constructor
	 */
	private ValidatePerson() {
		
	}
	
	/**
	 * This routine is used to validate information from the Person service.
	 * @param db contains an open database connection.
	 * @param personId contains the user's person identifier.
	 * @param updatedBy contains the userid of the person performing the update.
	 * @return will return a PersonTable instance upon successful validation.
	 * @throws CprException
	 */
	public static PersonTable validatePersonParameters(Database db, long personId, String updatedBy) throws  CprException {
		
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : updatedBy;		
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		
		db.getAllTableColumns("PERSON");
		
		if (localUpdatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
		
		return new PersonTable(personId, localUpdatedBy);

	}
}
