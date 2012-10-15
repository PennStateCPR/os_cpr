/* SVN FILE: $Id: ValidatePhone.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.util;

import java.util.regex.Pattern;


import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.TableColumn;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;


/**
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
public class ValidatePhone {

	/**
	 * This routine will check the input string for a valid phone number extension format
	 * 
	 * @param extension - contains the extension to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 */
    public static boolean isValidExtension(String extension) {
    	
    	// Extension is not required
    	
    	// Null is valid
    	if (extension == null) {
    		return true;
    	}
    	
    	extension = extension.trim();
    	
    	// Empty string is valid
    	if (extension.length() == 0) {
    		return true;
    	}
    	
    	// Extension can begin with x followed by digits
	    if (Pattern.matches(CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_REGEX_PHONE_NUMBER_EXTENSION.toString()), extension)) {
	    	return true;
	    }
	    
	    return false;
	}
    
	/**
	 * This routine will check the input string for a valid phone number format
	 * 
	 * @param isInternational - contains an international flag N/Y
	 * @param phoneNumber - contains the phone number to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 */
    public static boolean isValidPhoneNumber(String isInternational, String phoneNumber) {
 
       	if (isInternational == null) {
    		// The inInternational flag formatting is incorrect
    		return false;
    	}
       	
       	if (phoneNumber == null) {
       		return false;
       	}

    	isInternational = Validate.isValidYesNo(isInternational);      	
    	if (isInternational == null) {
    		return false;
    	}
     	
    	if (isInternational.equals("Y")) {
    		// Check the format for an international number format
    		if (Pattern.matches(CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_REGEX_INT_PHONE_NUMBER.toString()), phoneNumber)) {
    	    	return true;
    		}
    	    return false;
    	}
    	
    	// isInternational must be 'N', validate for US number format
    	if (Pattern.matches(CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_REGEX_US_PHONE_NUMBER.toString()), phoneNumber)) {
	    	return true;
    	}
    	
	    return false;
	}
    
   /** This routine is used to validate the AddPhone information.  If successful it returns PhonesTable class.
     * 
     * @param db  contains the Database object
     * @param personId contains the person id from the cpr.
     * @param phoneType contains the phone type.
     * @param phoneNumber contains the phone number.
     * @param extension contains the extension.
     * @param internationalNumber contains a flag to indicator whether this is an international phone number.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * 
     * @return will return an PhonesTable class if successful, otherwise it will throw an exception.
     * 
     * @throws GeneralDatabaseException
     * @throws CprException
     */
    public static PhonesTable validateAddPhonesParameters (Database db, long personId, String phoneType,
    		 String phoneNumber, String extension, String internationalNumber,
    		String updatedBy) throws GeneralDatabaseException, CprException {
    	String intlNumber = null;
    	PhonesTable phonesTable = null;
    	
    	String dbColumnNames[] = { "PHONE_NUMBER", "EXTENSION",  "LAST_UPDATE_BY" };
		String inputFields[]   = { phoneNumber, extension,  updatedBy};
		String prettyNames[] = { "Phone number", "Extension",  "Updated by"};
		
		// Trim all of the strings if they are not null.
		phoneNumber = (phoneNumber != null) ? phoneNumber.trim() : null;
		extension = (extension != null) ?extension.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		
		// insure phone type, phone number and updateBy specified
		if (phoneType == null || phoneType.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Phone type");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		if (phoneNumber == null || phoneNumber.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Phone number");
		}

		// Verify that the lengths for the individual fields do not exceed the maximum for the database.
		db.getAllTableColumns("PHONES");
		for (int i = 0; i < dbColumnNames.length; ++i) {
			if (inputFields[i] != null && inputFields[i].length() > db.getColumn(dbColumnNames[i]).getColumnSize()) {
				throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, prettyNames[i]);
			}
		}
		
		// Final validations
    	intlNumber = Validate.isValidYesNo(internationalNumber);
    	if (intlNumber == null) {
    		throw new CprException(ReturnType.YN_PARAMETERS_EXCEPTION, "International number");
    	}
    	
 
    
    	if ( !isValidPhoneNumber(intlNumber, phoneNumber)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone number");
    	}
    	
    	if (!isValidExtension (extension)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Extension");
    	}
    	
    	try {
			phonesTable = new PhonesTable(personId, phoneType,  phoneNumber, extension, intlNumber, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone type");
		}
    	return phonesTable;
    }
  /** This routine is used to validate the ArchivePhones information.  If successful it returns PhonesTable class.
    * 
    * @param db  contains the Database object
    * @param personId contains the person id from the cpr.
    * @param phoneType contains the phone type.
    * @param groupId contains the groupId.
    * @param updatedBy contains the system identifier or userid that updated the record.
    * 
    * @return will return an PhonesTable class if successful, otherwise it will throw an exception.
    * @throws GeneralDatabaseException
    * @throws CprException
    */
    public static PhonesTable validateArchiveAndSetPrimaryPhonesParameters (Database db, long personId, String phoneType,
    		Long groupId, String updatedBy) throws GeneralDatabaseException, CprException {

    	PhonesTable phonesTable = null;
    	if (groupId == null) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Group Id");
    	}
    	db.getAllTableColumns("PHONES");
    	if (updatedBy == null || updatedBy.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
    	TableColumn c = db.getColumn("LAST_UPDATE_BY");
		if (updatedBy.length() > c.getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
    	if (phoneType == null || phoneType.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Phone type");
		}
    	
    	try {
			phonesTable = new PhonesTable(personId, phoneType, groupId, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone type");
		}
    	return phonesTable;
    }
    
   /** This routine is used to validate the getPhone information.  If successful it returns PhonesTable class.
     * @param db contains the database object
     * @param personId contains the person id from the cpr.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * @param phoneType if specified will be used to limit the results from a query.
     * @param returnHistory Y/N flag that will be determine if the historical GET results will be returned.
     * @return PhonesTable will be returned if the validation was successful.
     * @throws CprException will be thrown for any CPR specific problems.
     * @throws GeneralDatabaseException will be be thrown for any database problems.
     */
    public static PhonesTable  validateGetPhonesParameters (Database db, long personId, 
    		String updatedBy, String phoneType, String returnHistory) throws GeneralDatabaseException, CprException {
   
    	// Trim all of the String values.
    	updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
    	phoneType = (phoneType != null) ? phoneType.trim() : null;
    	returnHistory = (returnHistory != null) ? returnHistory.trim() : null;
    	
    	// Verify that the updatedBy is within the length contained in the database.
    	db.getAllTableColumns("PHONES");
    	if (updatedBy == null || updatedBy.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
    	TableColumn c = db.getColumn("LAST_UPDATE_BY");
		if (updatedBy.length() > c.getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
		final PhonesTable phonesTable = new PhonesTable();
		
		// Phone type specified?
		if (phoneType != null) {
			try {
				phonesTable.setPhoneType(phoneType);
			}
			catch (Exception e) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone type");
			}
		}
		
		// Validate the return history flag.
		if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		phonesTable.setReturnHistoryFlag((returnHistory.equals("Y")) ? true : false);

		return phonesTable;
    }
    
   /** This routine is used to validate the UpdatePhone information.  If successful it returns PhonesTable class.
     * 
     * @param db contains Database object
     * @param personId contains the person id from the cpr.
     * @param phoneType contains the phone type.
     * @param groupId contains the groupId
     * @param phoneNumber contains the phone number.
     * @param extension contains the extension.
     * @param internationalNumber contains a flag to indicator whether this is an international phone number.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * 
     * @return will return an PhonesTable class if successful, otherwise it will throw an exception.
     * 
     * @throws CprException
     * @throws GeneralDatabaseException
     */
    public static PhonesTable validateUpdatePhonesParameters (Database db, long personId, String phoneType,
    		Long groupId, String phoneNumber, String extension, String internationalNumber,
    		String updatedBy) throws GeneralDatabaseException, CprException {
    	String intlNumber = null;
    	PhonesTable phonesTable = null;
    	
    	if (groupId == null) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Group Id");
    	}
    	String dbColumnNames[] = { "PHONE_NUMBER", "EXTENSION",  "LAST_UPDATE_BY" };
		String inputFields[]   = { phoneNumber, extension,  updatedBy };
		String prettyNames[] = { "Phone number", "Extension",  "Updated by" };
		
		// Trim all of the strings if they are not null.
		phoneNumber = (phoneNumber != null) ? phoneNumber.trim() : null;
		extension = (extension != null) ?extension.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		// insure phone type, phone number and updateBy specified
		if (phoneType == null || phoneType.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Phone type");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		if (phoneNumber == null || phoneNumber.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Phone number");
		}

		// Verify that the lengths for the individual fields do not exceed the maximum for the database.
		db.getAllTableColumns("PHONES");
		for (int i = 0; i < dbColumnNames.length; ++i) {
			if (inputFields[i] != null && inputFields[i].length() > db.getColumn(dbColumnNames[i]).getColumnSize()) {
				throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, prettyNames[i]);
			}
		}
		
		// Final validations
    	intlNumber = Validate.isValidYesNo(internationalNumber);
    	if (intlNumber == null) {
       		throw new CprException(ReturnType.YN_PARAMETERS_EXCEPTION, "International number");
    	}
    
       	
    
    	if ( !isValidPhoneNumber(intlNumber, phoneNumber)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone number");
    	}
    	
    	if (!isValidExtension (extension)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Extension");
    	}
    	try {
			phonesTable = new PhonesTable(personId, phoneType, groupId, phoneNumber, extension, intlNumber, updatedBy);
		}
		catch (Exception e) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone type");
		}
    	return phonesTable;
    }
    /**
	 * @return a string value.
	 */
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode());
	}
}
