/* SVN FILE: $Id: ValidateEmail.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
package edu.psu.iam.cpr.core.database.tables.validate;

import java.util.regex.Pattern;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;

/**
 * ValidateEmail is a utility class that will validate email address data inputs 
 * @author slk24
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
public final class ValidateEmail {
	
	private static final String DATABASE_TABLE_NAME = "EMAIL_ADDRESS";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	private static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";

	/** 
	 * Constructor.
	 */
	private ValidateEmail() {
	}
	
	/**
	 * This routine will use a regular expression to check the input string for a valid 
	 * email address format
	 * 
	 * @param emailAddress - contains the email address to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 */
    public static boolean isValidEmail(String emailAddress) {
    	if (emailAddress == null) {
    		return false;
    	}
    	if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_EMAIL_ADDRESS.toString()), 
    			emailAddress)) {
	    	return true;
	    }
	    return false;
	}
    
    /**
     * This routine is used to valid email address information.  If successful it will return an EmailAddressTable class.
     * @param db contains the database connection.
     * @param personId contains the person id from the cpr.
     * @param emailType contains the email address type.
     * @param emailAddress contains the email address.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * @return will return an EmailAddressTable class if successful, otherwise it will throw an exception.
     * @throws CprException 
     */
	public static EmailAddressTable validateEmailAddressParameters(Database db, long personId,
			String emailType, String emailAddress, String updatedBy) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		String localEmailAddress = ValidateHelper.checkField(db, emailAddress, EMAIL_ADDRESS, "Email address", true);
		String localEmailType = ValidateHelper.checkField(db, emailType, null, "Email type", false);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
	
		// Create a new object.
		EmailAddressTable emailAddressTable = new EmailAddressTable(personId, localEmailType, localEmailAddress, localUpdatedBy);

		// Verify that the email address is good.
		if (! isValidEmail(emailAddressTable.getEmailAddressBean().getEmailAddress())) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Email address");
		}
		
		return emailAddressTable;
	}

    /**
     * This routine is used to valid information to set a EmailAddress as primary.  If successful it will return an EmailAddressTable class.
     * @param db contains the database connection.
     * @param personId contains the person id from the cpr.
     * @param emailType contains the email address type.
     * @param updatedBy contains the system identifier or userid that updated the record.
     * @return will return an EmailAddressTable class if successful, otherwise it will throw an exception.
     * @throws CprException 
     */
	public static EmailAddressTable validatePrimaryEmailParameters(Database db, long personId,
			String emailType, String updatedBy) throws CprException {
		
		return validateArchiveEmailAddressParameters(db, personId, emailType, updatedBy);
	}	
	
	/**
	 * This routine is used to validate the data for the GetEmailAddress service.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier from the CPR.
	 * @param requestedBy contains the system identifier or userid that is requesting information.
	 * @param returnHistory Y/N that indicates whether to return history or not.
	 * @return EmailAddressTable upon success, it will return an instance of an EmailAddressTable.
	 * @throws CprException 
	 */
	public static EmailAddressTable validateGetEmailAddressParameters(Database db, long personId, String requestedBy, 
			String returnHistory) throws CprException {
				
		db.getAllTableColumns(DATABASE_TABLE_NAME);

		// For non-null input fields, trim them off.
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		
		// Validate the return history parameter.
		final EmailAddressTable emailAddressTable = new EmailAddressTable();
		emailAddressTable.setReturnHistoryFlag(returnHistoryFlag);
		
		return emailAddressTable;
	}
	
	/**
	 * This routine is used to validate parameters that were sent to the ArchiveEmailAddress service.  For errors, an exception 
	 * will be thrown.  Otherwise, it will return an EmailAddressTable object.
	 * @param db contains the database connection.
	 * @param personId contains the user's person identifier from the CPR.
	 * @param emailAddressType contains the email address type to be deleted.
	 * @param updatedBy contains the userid or system identifier that is updating the record.
	 * @return EmailAddressTable class.
	 * @throws CprException 
	 */
	public static EmailAddressTable validateArchiveEmailAddressParameters(Database db, long personId, String emailAddressType, 
			String updatedBy) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);

		String localEmailAddressType = ValidateHelper.checkField(db, emailAddressType, null, "Email address type", false);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		// Attempt to instantiate a new email address table class.
		return new EmailAddressTable(personId, localEmailAddressType, localUpdatedBy);
	}
}
