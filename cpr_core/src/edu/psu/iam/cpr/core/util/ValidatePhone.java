/* SVN FILE: $Id: ValidatePhone.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.util;

import java.util.regex.Pattern;


import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.TableColumn;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
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
public final class ValidatePhone {

	/**
	 * Constructor.
	 */
	private ValidatePhone() {
		
	}
	
	/**
	 * This routine will check the input string for a valid phone number extension format
	 * 
	 * @param extension - contains the extension to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 */
    public static boolean isValidExtension(String extension) {
    	
    	String localExtension = extension;
    	
    	// Extension is not required
    	
    	// Null is valid
    	if (localExtension == null) {
    		return true;
    	}
    	
    	localExtension = localExtension.trim();
    	
    	// Empty string is valid
    	if (localExtension.length() == 0) {
    		return true;
    	}
    	
    	// Extension can begin with x followed by digits
	    if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_PHONE_NUMBER_EXTENSION.toString()), 
	    		localExtension)) {
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
 
    	String localIsInternational = isInternational;
    	String localPhoneNumber = phoneNumber;
    	
       	if (localIsInternational == null) {
    		// The inInternational flag formatting is incorrect
    		return false;
    	}
       	
       	if (localPhoneNumber == null) {
       		return false;
       	}

    	localIsInternational = Validate.isValidYesNo(localIsInternational);      	
    	if (localIsInternational == null) {
    		return false;
    	}
     	
    	if (localIsInternational.equals("Y")) {
    		// Check the format for an international number format
    		if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_INT_PHONE_NUMBER.toString()), 
    				localPhoneNumber)) {
    	    	return true;
    		}
    	    return false;
    	}
    	
    	// isInternational must be 'N', validate for US number format
    	if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_US_PHONE_NUMBER.toString()), 
    			localPhoneNumber)) {
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
     * @throws CprException
     */
    public static PhonesTable validateAddPhonesParameters (Database db, long personId, String phoneType,
    		 String phoneNumber, String extension, String internationalNumber,
    		String updatedBy) throws CprException {
    	String intlNumber = null;
    	
 		
		// Trim all of the strings if they are not null.
		String localPhoneNumber = (phoneNumber != null) ? phoneNumber.trim() : null;
		String localExtension = (extension != null) ?extension.trim() : null;
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		String localPhoneType = null;
		if (phoneType != null) {
			if (phoneType.trim().length() != 0) {
				localPhoneType = phoneType.trim().toUpperCase();
			}
		}
	   	String dbColumnNames[] = { "PHONE_NUMBER", "EXTENSION",  "LAST_UPDATE_BY" };
		String inputFields[]   = { localPhoneNumber, localExtension,  localUpdatedBy};
		String prettyNames[] = { "Phone number", "Extension",  "Updated by"};
		
		
		// insure phone type, phone number and updateBy specified
		if (localPhoneType == null || localPhoneType.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Phone type");
		}
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		if (localPhoneNumber == null || localPhoneNumber.length() == 0) {
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
    	
 
    
    	if ( !isValidPhoneNumber(intlNumber, localPhoneNumber)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone number");
    	}
    	
    	if (!isValidExtension (localExtension)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Extension");
    	}
    	
 		return new PhonesTable(personId, localPhoneType,  localPhoneNumber, localExtension, intlNumber, localUpdatedBy);
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
    * @throws CprException
    */
    public static PhonesTable validateArchiveAndSetPrimaryPhonesParameters (Database db, long personId, String phoneType,
    		Long groupId, String updatedBy) throws CprException {

		String localPhoneType = null;
		if (phoneType != null) {
			if (phoneType.trim().length() != 0) {
				localPhoneType = phoneType.trim().toUpperCase();
			}
		}
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
    	
		return new PhonesTable(personId, localPhoneType, groupId, updatedBy);
    }
    
   /** This routine is used to validate the getPhone information.  If successful it returns PhonesTable class.
     * @param db contains the database object
     * @param personId contains the person id from the cpr.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * @param phoneType if specified will be used to limit the results from a query.
     * @param returnHistory Y/N flag that will be determine if the historical GET results will be returned.
     * @return PhonesTable will be returned if the validation was successful.
     * @throws CprException will be thrown for any CPR specific problems.
     */
    public static PhonesTable  validateGetPhonesParameters (Database db, long personId, 
    		String updatedBy, String phoneType, String returnHistory) throws CprException {
   
    	// Trim all of the String values.
    	String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		String localPhoneType = null;
		if (phoneType != null) {
			if (phoneType.trim().length() != 0) {
				localPhoneType = phoneType.trim().toUpperCase();
			}
		}
    	String localReturnHistory = (returnHistory != null) ? returnHistory.trim() : null;
    	
    	// Verify that the updatedBy is within the length contained in the database.
    	db.getAllTableColumns("PHONES");
    	if (localUpdatedBy == null || localUpdatedBy.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
    	TableColumn c = db.getColumn("LAST_UPDATE_BY");
		if (localUpdatedBy.length() > c.getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
		final PhonesTable phonesTable = new PhonesTable();
		
		// Phone type specified?
		if (localPhoneType != null) {
			phonesTable.setPhoneType(localPhoneType);
		}
		
		// Validate the return history flag.
		if ((localReturnHistory = Validate.isValidYesNo(localReturnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		phonesTable.setReturnHistoryFlag((localReturnHistory.equals("Y")) ? true : false);

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
     */
    public static PhonesTable validateUpdatePhonesParameters (Database db, long personId, String phoneType,
    		Long groupId, String phoneNumber, String extension, String internationalNumber,
    		String updatedBy) throws CprException {
    	String intlNumber = null;
    	
    	if (groupId == null) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Group Id");
    	}
 		
		// Trim all of the strings if they are not null.
		String localPhoneNumber = (phoneNumber != null) ? phoneNumber.trim() : null;
		String localExtension = (extension != null) ?extension.trim() : null;
		String localUpdatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		String localPhoneType = null;
		if (phoneType != null) {
			if (phoneType.trim().length() != 0) {
				localPhoneType = phoneType.trim().toUpperCase();
			}
		}
	   	String dbColumnNames[] = { "PHONE_NUMBER", "EXTENSION",  "LAST_UPDATE_BY" };
		String inputFields[]   = { localPhoneNumber, localExtension,  localUpdatedBy };
		String prettyNames[] = { "Phone number", "Extension",  "Updated by" };
		
		// insure phone type, phone number and updateBy specified
		if (localPhoneType == null || localPhoneType.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Phone type");
		}
		if (localUpdatedBy == null || localUpdatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		if (localPhoneNumber == null || localPhoneNumber.length() == 0) {
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
    
       	
    
    	if ( !isValidPhoneNumber(intlNumber, localPhoneNumber)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone number");
    	}
    	
    	if (!isValidExtension (localExtension)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Extension");
    	}
		return new PhonesTable(personId, localPhoneType, groupId, localPhoneNumber, localExtension, intlNumber, localUpdatedBy);
    }
    /**
	 * @return a string value.
	 */
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode());
	}
}
