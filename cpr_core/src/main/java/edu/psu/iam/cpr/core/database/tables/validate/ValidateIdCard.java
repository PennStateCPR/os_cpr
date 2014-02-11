/* SVN FILE: $Id: ValidateIdCard.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.IdCardTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Validate;

/**
 *
 *
 * This class provides an implementation for validation routines associated with id card.
 *  This class provides methods to validate add, update and get id card information
 *  in the CPR.
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
 * @package edu.psu.iam.cpr.core.util.
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public final class ValidateIdCard {
	
	private static final int ID_CARD_NUMBER_LENGTH = 16;
	private static final String DATABASE_TABLE_NAME = "PERSON_ID_CARD";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";

	/**
	 * Constructor
	 */
	private ValidateIdCard() {
	}
	
	/**
	 * This routine validates the parameters for the updateIdCard.  The validation checks:
	 * updatedBy is not null/blank and less than 31 characters
	 * idCardTypeString is not null/blank and valid id card type
	 * idCard is not null/blank and must be 16 digits
	 * idSerialNumber may be null/blank if entered must be less than 31 chararacters
	 * 
	 * @param db Database Object
	 * @param personId Id associated with the id card
	 * @param idCardTypeString id card type
	 * @param updatedBy userid of  issuing the update  id card
	 * @param idCardNumber - id card number associated with the id
	 * @param idSerialNumber - id serial number if using a smart card
	 * @param photo - contains the photograph.
	 * @param dateTaken - contains the date the photo was taken.
	 * @return IdCardTable object
	 * 
	 * @throws CprException
	 * @throws ParseException 
	 */
	public static IdCardTable validateAddUpdateIdCardParameters ( final Database db,
			final long personId, final String idCardTypeString, final String updatedBy,
			final String idCardNumber, final String idSerialNumber, final byte[] photo, final String dateTaken)  throws CprException, ParseException {
		IdCardTable idCardTable = null;
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		String localDateTaken = ValidateHelper.trimField(dateTaken);
		String localIdCardNumber = ValidateHelper.checkField(null, idCardNumber, null, "Id card number", false);
		String localIdSerialNumber = ValidateHelper.trimField(idSerialNumber);
		String localIdCardTypeString = ValidateHelper.checkField(null, idCardTypeString, null, "Id card type", false);
		@SuppressWarnings("unused")
		String localUpdateBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		if (localIdCardNumber.length() != ID_CARD_NUMBER_LENGTH) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Id Card Number");
		}
		
		if (!localIdCardNumber.matches("^[0-9]{16}") ){
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Id Card Number");
		}
		
		if  (ValidateHelper.isPhotoEmpty(photo))  {
			if (! ValidateHelper.isFieldEmpty(localDateTaken))  {		
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Image");
			}
		}
		else {
			if (ValidateHelper.isFieldEmpty(localDateTaken)) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Date taken");
			}

			// Validate the date taken.
			if (! Validate.isValidDate(localDateTaken)) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Date taken");
			}
		}
		
		if (localDateTaken == null) {
			idCardTable = new IdCardTable(personId, localIdCardTypeString, updatedBy, localIdCardNumber,  
					localIdSerialNumber);
		}
		else {
			idCardTable = new IdCardTable(personId, localIdCardTypeString, updatedBy, localIdCardNumber,  
					localIdSerialNumber, photo, new SimpleDateFormat(
							CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_FORMAT_DATE.toString())).parse(
									localDateTaken));
		}
		return idCardTable;
	}
	
	
	/**
	 * This routine validates the parameters for the archiveIdCard.  The data validated is
	 * @param db  database object
	 * @param personId  id
	 * @param updatedBy userid of the  issuing the getIdCardByType
	 * @param idCardTypeString  - the type of id card records to return
	 * @return IdCardTable
	 * @throws CprException
	 */
	public static IdCardTable  validateArchiveIdCardParameters ( final Database db,
			final long personId, final String updatedBy, final String idCardTypeString) throws CprException {
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);

		String localIdCardTypeString = null;
		if (idCardTypeString != null) {
			localIdCardTypeString = idCardTypeString.trim().toUpperCase();
		}
		
		return new IdCardTable(personId, localIdCardTypeString, localUpdatedBy);
	}
	
	/**
	 * This routine validates the parameters for the getIdCard.  The data validated is
	 * @param db  database object
	 * @param personId  id
	 * @param requestedBy userid of the  issuing the getIdCard
	 * @param idCardType contains the type of id card to be used in a search, if specified.
	 * @param returnHistory Y/N that indicates whether history is to be returned or not.
	 * @return IdCardTable
	 * @throws CprException
	 */
	public static IdCardTable validateGetIdCardParameters ( final Database db,
			final long personId, final String requestedBy, final String idCardType, final String returnHistory) throws CprException {

		// Trim all of the String parameters.
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		@SuppressWarnings("unused")
		String localRequestedBy = ValidateHelper.checkField(db, requestedBy, LAST_UPDATE_BY, "Requested by", true);
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		String localIdCardType = null;
		if (idCardType != null) {
			localIdCardType = idCardType.trim().toUpperCase();
		}
		
		// Validate the id card type.
		final IdCardTable idCardTable = new IdCardTable();
		if (localIdCardType != null) {
			idCardTable.setIdCardType(idCardTable.findIdCardTypeEnum(localIdCardType));
		}
		idCardTable.setReturnHistoryFlag(returnHistoryFlag);
		
		return idCardTable;
	}
}
