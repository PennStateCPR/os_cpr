/* SVN FILE: $Id: ValidatePhone.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.database.tables.validate;

import java.util.regex.Pattern;


import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Utility;
import edu.psu.iam.cpr.core.util.Validate;


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

	private static final String DATABASE_TABLE_NAME = "PHONES";
	private static final String PHONE_NUMBER = "PHONE_NUMBER";
	private static final String EXTENSION = "EXTENSION";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	
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
    public static boolean isValidExtension(final String extension) {
    	
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
    public static boolean isValidPhoneNumber(final String isInternational, final String phoneNumber) {
 
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
     	
    	if (Utility.isOptionYes(localIsInternational)) {
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
    public static PhonesTable validateAddPhonesParameters (final Database db, final long personId, final String phoneType,
    		final String phoneNumber, final String extension, final String internationalNumber,
    		final String updatedBy) throws CprException {
    	String intlNumber = null;
    	
		db.getAllTableColumns(DATABASE_TABLE_NAME);

		String localPhoneNumber = ValidateHelper.checkField(db, phoneNumber, PHONE_NUMBER, "Phone number", true);

		String localExtension = null;
		if (extension != null) {
			localExtension = ValidateHelper.checkField(db, extension, EXTENSION, "Extension", true);
		}

		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		String localPhoneType = null;
		if (phoneType != null) {
			localPhoneType = phoneType.trim().toUpperCase();
		}
		localPhoneType = ValidateHelper.checkField(null, localPhoneType, null, "Phone type", false);
		
    	intlNumber = Validate.isValidYesNo(internationalNumber);
    	if (intlNumber == null) {
    		throw new CprException(ReturnType.YN_PARAMETERS_EXCEPTION, "International number");
    	}
    
    	if (! isValidPhoneNumber(intlNumber, localPhoneNumber)) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone number");
    	}
    	
    	if (! isValidExtension (localExtension)) {
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
    * @param phoneKey contains the phone key, only matters for RESTful services.
    * @return will return an PhonesTable class if successful, otherwise it will throw an exception.
    * @throws CprException
    */
    public static PhonesTable validateArchiveAndSetPrimaryPhonesParameters (final Database db, final long personId, final String phoneType,
    		final Long groupId, final String updatedBy, final String phoneKey) throws CprException {

    	db.getAllTableColumns(DATABASE_TABLE_NAME);
    	
		String localPhoneType = null;
		long phoneKeyValue = -1;
		if (phoneKey == null) {
			if (phoneType != null) {
				localPhoneType = phoneType.trim().toUpperCase();
			}
			localPhoneType = ValidateHelper.checkField(null, localPhoneType, null, "Phone type", false);
		}
		else {
			phoneKeyValue = Long.valueOf(phoneKey);
		}
		
    	if (groupId == null) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Group Id");
    	}
    	
    	final String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
     	
    	PhonesTable phonesTable = new PhonesTable(personId, localPhoneType, groupId, localUpdatedBy);
    	phonesTable.setPhoneKey(phoneKeyValue);
		return phonesTable;
    }
    
   /** This routine is used to validate the getPhone information.  If successful it returns PhonesTable class.
     * @param db contains the database object
 * @param personId contains the person id from the cpr.
 * @param updatedBy contains the system identifier or userid that updated the record.
 * @param phoneType if specified will be used to limit the results from a query.
 * @param returnHistory Y/N flag that will be determine if the historical GET results will be returned.
 * @param phoneKey contains the phone key passed in for a RESTful service.
     * @return PhonesTable will be returned if the validation was successful.
     * @throws CprException will be thrown for any CPR specific problems.
     */
    public static PhonesTable  validateGetPhonesParameters (final Database db, final long personId, 
    		final String updatedBy, final String phoneType, final String returnHistory, final String phoneKey) throws CprException {
   
    	db.getAllTableColumns(DATABASE_TABLE_NAME);

    	@SuppressWarnings("unused")
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Requested by", true);

    	Long phoneKeyValue = -1L;
    	boolean returnHistoryFlag = false;
    	String localPhoneType = null;
    	
    	if (phoneKey == null) {
    		if (phoneType != null) {
    			localPhoneType = phoneType.trim().toUpperCase();
    		}
    		returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
    	}
    	else {
    		phoneKeyValue = Long.valueOf(phoneKey);
    	}
    	
		final PhonesTable phonesTable = new PhonesTable();
		
		// Phone type specified?
		if (localPhoneType != null) {
			phonesTable.setPhoneType(phonesTable.findPhoneTypeEnum(localPhoneType));
		}
		
		phonesTable.setReturnHistoryFlag(returnHistoryFlag);
		phonesTable.setPhoneKey(phoneKeyValue);

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
    public static PhonesTable validateUpdatePhonesParameters (final Database db, final long personId, final String phoneType,
    		final Long groupId, final String phoneNumber,final  String extension,final  String internationalNumber,
    		final String updatedBy) throws CprException {
    	
    	PhonesTable phonesTable = validateAddPhonesParameters(db, personId, phoneType, phoneNumber, extension, internationalNumber, updatedBy);
    	if (groupId == null) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Group Id");
    	}
    	phonesTable.getPhonesBean().setGroupId(groupId);

    	return phonesTable;
    }
    /**
	 * @return a string value.
	 */
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode());
	}
}
