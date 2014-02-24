package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.Utility;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * ValidateHelper is a helper class that handles validation simple functions. 
 * @author jvuccolo
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
 * @version %I%, %G%
 * 
 * @since 1.0
 */

public final class ValidateHelper {
	
	/**
	 * Constructor
	 */
	private ValidateHelper() {
	}
	
	/**
	 * This method is used to trim a string.
	 * @param input contains the string to be trimmed.
	 * @return will return either a null string or the trimmed string.
	 */
	public static String trimField(final String input) {
		if (input != null) {
			return input.trim();
		}
		else {
			return input;
		}
	}

	/**
	 * This method is use to check whether a field is empty or not.
	 * @param input contains the field to be checked.
	 * @return will return true if the field is empty or false if its not.
	 */
	public static boolean isFieldEmpty(final String input) {
		if (input == null || input.length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method is use to check whether a field is empty or not.
	 * @param input contains the field to be checked.
	 * @return will return true if the field is empty or false if its not.
	 */
	public static boolean isPhotoEmpty(final byte[] input) {
		if (input == null || input.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This routine is used to validate whether an input field's length is valid.
	 * @param db contains a database connection.
	 * @param input contains the field whose length is to be validated.
	 * @param fieldName contains the database field name.
	 * @return will return true if the field is valid, otherwise it will return false.
	 * @throws CprException will be thrown if there are any database issues.
	 */
	public static boolean isFieldLengthValid(final Database db, final String input, final String fieldName) throws CprException {
		if (input.length() <= db.getColumn(fieldName).getColumnSize()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This routine is used to check and valid a return history flag.
	 * @param input contains the input to be checked. 
	 * @return will return a boolean true if successful.
	 * @throws CprException will throw a CprException if there is a problem.
	 */
	public static boolean checkReturnHistory(final String input) throws CprException {
		
		String localInput = trimField(input);
		
		localInput = Validate.isValidYesNo(localInput);
		if (localInput == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		
		if (Utility.isOptionYes(localInput)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This routine is used to check the validity of a field.
	 * @param db contains a database connection.
	 * @param input contains the input string to be validated.
	 * @param fieldName contains the database field to be checked.
	 * @param prettyName contains the pretty name.
	 * @param checkFieldLength boolean field that indicates whether to validation the field length or not.
	 * @return will return the validated field.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public static String checkField(final Database db, final String input, final String fieldName, final String prettyName, final boolean checkFieldLength) 
		throws CprException {
		
		String localInput = trimField(input);
		
		if (isFieldEmpty(localInput)) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, prettyName);
		}
		
		if (checkFieldLength && (! isFieldLengthValid(db, localInput, fieldName))) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, prettyName);
		}
		return localInput;
	}
}
