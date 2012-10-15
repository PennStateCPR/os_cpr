/* SVN FILE: $Id: ValidateIdCard.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import java.text.SimpleDateFormat;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.IdCardTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

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
public class ValidateIdCard {
	
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
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	public static IdCardTable validateAddUpdateIdCardParameters ( final Database db,
			long personId, String idCardTypeString, String updatedBy,
			String idCardNumber, String idSerialNumber, byte[] photo, String dateTaken)  throws GeneralDatabaseException,
			CprException {
		IdCardTable IdCardTable = null;
		dateTaken = (dateTaken != null) ? dateTaken.trim() : null;
		idCardNumber = (idCardNumber  != null) ? idCardNumber.trim() : null;
		idSerialNumber = (idSerialNumber  != null) ? idSerialNumber.trim() : null;
		if (idCardTypeString == null || idCardTypeString.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Id Card type");			
		}
		if (idCardNumber == null || idCardNumber.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Id Card Number");
		}
		if (idCardNumber.length() != 16) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Id Card Number");
		}
		if (!idCardNumber.matches("^[0-9]{16}") ){
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Id Card Number");
		}
		
		db.getAllTableColumns("PERSON_ID_CARD");
		
		if (idSerialNumber != null ) {
			if (idSerialNumber.length() > db.getColumn("ID_SERIAL_NUMBER").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Id Serial Number");
			}
		}
		if  (photo == null || photo.length == 0)  {
			if (dateTaken != null &&  dateTaken.length() != 0)  {		
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Image");
			}
			
		
		}
		else
		{
			if (dateTaken == null || dateTaken.length() == 0) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Date taken");
			}
			else {
		
			// Validate the date taken.
				if (! Validate.isValidDate(dateTaken)) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Date taken");
				}
			}
		}
		try {
			if (dateTaken == null) {
				IdCardTable = new IdCardTable(personId, idCardTypeString, updatedBy, idCardNumber,  
						idSerialNumber);
			}
			else
			{
			IdCardTable = new IdCardTable(personId, idCardTypeString, updatedBy, idCardNumber,  
					idSerialNumber, photo, new SimpleDateFormat(CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_FORMAT_DATE.toString())).parse(dateTaken));
			}
		}
		catch (Exception e)
		{
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Id CardType");
		}
		return IdCardTable;
	}
	
	
	/**
	 * This routine validates the parameters for the archiveIdCard.  The data validated is
	 * @param db  database object
	 * @param personId  id
	 * @param updatedBy userid of the  issuing the getIdCardByType
	 * @param idCardTypeString  - the type of id card records to return
	 * @return IdCardTable
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	public static IdCardTable  validateArchiveIdCardParameters ( final Database db,
			long personId, String updatedBy, String idCardTypeString) throws GeneralDatabaseException,CprException {
		
		IdCardTable IdCardTable = null;
		updatedBy = (updatedBy  != null) ? updatedBy.trim() : null;
		if (updatedBy == null || updatedBy.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Update by");
		}
		
		db.getAllTableColumns("PERSON_ID_CARD");
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Update by");
		}
		idCardTypeString  = (idCardTypeString != null && idCardTypeString.trim().length() ==0)  ? null : idCardTypeString;
		try {
			IdCardTable = new IdCardTable(personId, idCardTypeString, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Id Card Type");
		
		}
		return IdCardTable;
	}
	
	/**
	 * This routine validates the parameters for the getIdCard.  The data validated is
	 * @param db  database object
	 * @param personId  id
	 * @param requestedBy userid of the  issuing the getIdCard
	 * @param idCardType contains the type of id card to be used in a search, if specified.
	 * @param returnHistory Y/N that indicates whether history is to be returned or not.
	 * @return IdCardTable
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	public static IdCardTable validateGetIdCardParameters ( final Database db,
			long personId, String requestedBy, String idCardType, String returnHistory) throws GeneralDatabaseException,CprException {

		// Trim all of the String parameters.
		requestedBy = (requestedBy  != null) ? requestedBy.trim() : null;
		idCardType = (idCardType != null) ? idCardType.trim() : null;
		returnHistory = (returnHistory != null) ? returnHistory.trim() : null;

		// Validate the requested by.
		if (requestedBy == null || requestedBy.trim().length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
		db.getAllTableColumns("PERSON_ID_CARD");
		if (requestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}
		
		// Validate the id card type.
		final IdCardTable idCardTable = new IdCardTable();
		if (idCardType != null) {
			try {
				idCardTable.setIdCardType(idCardType);
			}
			catch (Exception e) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Id Card Type");
			}
		}
		
		// Validate the return history flag.
		if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		idCardTable.setReturnHistoryFlag((returnHistory.equals("Y")) ? true : false);
		
		return idCardTable;
	}
}
