/* SVN FILE: $Id: ValidatePersonPhoto.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonPhotoTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * This class contains functions that are used to validate information that was specified during an Add/Get 
 * photo service calls.
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
public final class ValidatePersonPhoto {

	private static final String DATABASE_TABLE_NAME = "PERSON_PHOTO";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";
	
	/**
	 * Constructor
	 */
	private ValidatePersonPhoto() {
	}
	
	/**
	 * This routine is used to validate the parameters that were passed to the AddPhoto service.  If successful,
	 * this function will return an instance of the PersonPhotoTable class.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier of the user in the CPR.
	 * @param image contains the byte data of the person's photograph,
	 * @param dateTaken contains the date the photo was taken.
	 * @param updatedBy contains the userid that is performing the add.
	 * @return will return an instance of the PersonPhotoTable class if successful.
	 * @throws CprException 
	 * @throws ParseException 
	 */
	public static PersonPhotoTable validateAddPhotoParameters(final Database db, final Long personId, final byte[] image, 
			final String dateTaken, final String updatedBy) 
			throws CprException, ParseException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		String localDateTaken = ValidateHelper.checkField(null, dateTaken, null, "Date taken", false);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		if (image == null || image.length == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Image");			
		}
		
		// Validate the date taken.
		if (! Validate.isValidDate(localDateTaken)) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Date taken");
		}
		
		// Instantiate a person photo table object and return it to the caller.
		return new PersonPhotoTable(personId, image, 
				new SimpleDateFormat(CprProperties.INSTANCE.getProperties().getProperty(
						CprPropertyName.CPR_FORMAT_DATE.toString())).parse(localDateTaken), localUpdatedBy);
	}
	
	/**
	 * This routine is called to validate data that is passed to the GetPhoto SOAP service.
	 * @param db contains an instance of the database class.
	 * @param personId contains the person identifier in the CPR whose photo information was requested.
	 * @param requestedBy contains the entity that requested the photo information.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public static void validateGetPhotoParameters(final Database db, final Long personId, final String requestedBy) throws CprException {

		db.getAllTableColumns(DATABASE_TABLE_NAME);
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
	}
}
