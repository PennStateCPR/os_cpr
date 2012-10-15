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
package edu.psu.iam.cpr.core.util;

import java.util.regex.Pattern;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

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
public class ValidateEmail {
	
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
    	if (Pattern.matches(CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_REGEX_EMAIL_ADDRESS.toString()), emailAddress)) {
	    //if (Pattern.matches("^((^|@)[0-9A-Za-z!#$%&'*+/=?^_`{|}~-]+(\\.[0-9A-Za-z!#$%&'*+/=?^_`{|}~-]+)*){2}$",
	    	//	emailAddress)) {
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
     * @throws GeneralDatabaseException 
     * @throws CprException 
     */
	public static EmailAddressTable validateEmailAddressParameters(Database db, long personId,
			String emailType, String emailAddress, String updatedBy) throws GeneralDatabaseException, CprException {
		
		EmailAddressTable emailAddressTable = null;
		
		// For input strings that are non-null, trim them.
		emailType = (emailType != null) ? emailType.trim() : null;
		emailAddress = (emailAddress != null) ? emailAddress.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
	
		// Verify that an email address type, email address and updated by were specified.
		if (emailType == null || emailType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Email type");
		}
		if (emailAddress == null || emailAddress.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Email address");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}

		// Verify that the length for the email address and updated by do not exceed the maximums for the database.
		db.getAllTableColumns("EMAIL_ADDRESS");
		if (emailAddress.length() > db.getColumn("EMAIL_ADDRESS").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Email address");
		}
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}
		
		// Create a new object.
		try {
			emailAddressTable = new EmailAddressTable(personId, emailType, emailAddress, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Email address type");
		}

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
     * @throws GeneralDatabaseException 
     * @throws CprException 
     */
	public static EmailAddressTable validatePrimaryEmailParameters(Database db, long personId,
			String emailType, String updatedBy) throws GeneralDatabaseException, CprException {
		
		EmailAddressTable emailAddressTable = null;
		String emailAddress = null;

		// For non-null input fields, trim the strings.
		emailType = (emailType != null) ? emailType.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		// Verify that an email type and updated by were specified.
		if (emailType == null || emailType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Email address type");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		
		// Verify that the fields do not exceed the database lengths.
		db.getAllTableColumns("EMAIL_ADDRESS");
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}

		// Create the new object.
		try {
			emailAddressTable = new EmailAddressTable(personId, emailType, emailAddress, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Email address type");
		}
		
		return emailAddressTable;
	}	
	
	/**
	 * This routine is used to validate the data for the GetEmailAddress service.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier from the CPR.
	 * @param requestedBy contains the system identifier or userid that is requesting information.
	 * @param returnHistory Y/N that indicates whether to return history or not.
	 * @return EmailAddressTable upon success, it will return an instance of an EmailAddressTable.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static EmailAddressTable validateGetEmailAddressParameters(Database db, long personId, String requestedBy, String returnHistory) throws GeneralDatabaseException, CprException {
				
		// For non-null input fields, trim them off.
		requestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		returnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		
		// Verify that a requestor was specified.
		if (requestedBy == null || requestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
				
		// Verify that the fields do not exceed the database lengths.
		db.getAllTableColumns("EMAIL_ADDRESS");
		if (requestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
		// Validate the return history parameter.
		final EmailAddressTable emailAddressTable = new EmailAddressTable();
		if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		emailAddressTable.setReturnHistoryFlag((returnHistory.equals("Y")) ? true : false);
		
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
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public static EmailAddressTable validateArchiveEmailAddressParameters(Database db, long personId, String emailAddressType, String updatedBy) throws GeneralDatabaseException, CprException {
		
		EmailAddressTable emailAddressTable = null;
		
		// For input parameters that are non-null, trim them.
		emailAddressType = (emailAddressType != null) ? emailAddressType.trim() : null;
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		// Verify that an email address type was specified.
		if (emailAddressType == null || emailAddressType.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Email address type");
		}
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
		
		// Verify that the fields do not exceed the database lengths.
		db.getAllTableColumns("EMAIL_ADDRESS");
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}

		// Attempt to instantiate a new email address table class.
		try {
			emailAddressTable = new EmailAddressTable(personId, emailAddressType, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Email address type");
		}
		
		// Success return the new object.
		return emailAddressTable;
	}
}
