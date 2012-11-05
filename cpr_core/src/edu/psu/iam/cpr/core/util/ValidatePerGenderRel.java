/* SVN FILE: $Id: ValidatePerGenderRel.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * This class provides an implementation of functions that are used to validate gender information.
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
public final class ValidatePerGenderRel {
	
	private static final String GENDER = "Gender";
	private static final String UPDATED_BY = "Updated By";
	
	/**
	 * Constructor.
	 */
	private ValidatePerGenderRel() {
		
	}
	
	
	/**
	 * This routine is used to validate parameters for an add gender call.
	 * @param personId contains the person identifier from the CPR.
	 * @param genderString contains the string gender value.
	 * @param updatedBy contains the user that is updating the record.
	 * @return will return a PerGenderRelTable if successful.
	 * @throws CprException 
	 */
	public static PersonGenderTable validateAddGenderParameters(long personId, String genderString, String updatedBy) throws CprException {
		
		String localGenderString = null;
		if (genderString != null) {
			if (genderString.trim().length() != 0) {
				localGenderString = genderString.trim().toUpperCase();
			}
		}
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : updatedBy;
		
		if (localGenderString == null || localGenderString.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, GENDER);
		}
		
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, UPDATED_BY);
		}
		
		return new PersonGenderTable(personId, localGenderString, localUpdatedBy);
	}
}
