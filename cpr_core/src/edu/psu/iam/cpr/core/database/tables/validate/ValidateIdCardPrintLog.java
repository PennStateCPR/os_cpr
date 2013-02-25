/* SVN FILE: $Id: ValidateIdCardPrintLog.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables.validate;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 *  This class provides an implementation for validation routines for the ID_CARD_PRINT_LOG table.
 *  * 
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
 * 
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public final class ValidateIdCardPrintLog {

	private static final String DATABASE_TABLE_NAME = "ID_CARD_PRINT_LOG";
	
	/**
	 * Constructor.
	 */
	private ValidateIdCardPrintLog() {
	}
	
	/**
	 * Validate the AddIdCardPrintLog parameters
	 * @param db
	 * @param idType
	 * @param identifier
	 * @param eventUserId
	 * @param eventIpAddress
	 * @param eventWorkstation
	 * @return IdCardPrintLogTable object
	 * @throws CprException
	 */
	public static IdCardPrintLogTable validateAddIdCardPrintLogParameters (final Database db,String idType, String identifier,  
			 String eventUserId, String eventIpAddress, String eventWorkstation) throws CprException {
		String eventIdCard = null;
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);

		final String localEventUserId = ValidateHelper.checkField(db, eventUserId, "PRINTED_BY", "Printed By", true);
		final String localEventIpAddress = ValidateHelper.checkField(db, eventIpAddress, "WORK_STATION_IP_ADDRESS", 
				"Work Station IpAddress", true);
		final String localEventWorkstation = ValidateHelper.checkField(db, eventWorkstation, "WORK_STATION_NAME", 
				"Work Station Name", true);

		final IdentifierType identifierType = db.isValidIdentifierType(idType);
		if (identifierType.getTypeName().equals(Database.ID_CARD_IDENTIFIER)) {
			eventIdCard = identifier.trim();			
		}
		else {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Id Card Number");
		}
		
		return new IdCardPrintLogTable(eventIdCard, localEventUserId, localEventIpAddress, localEventWorkstation);
	}
	/**
	 * Validate GetIdCardPrintLog parameters
	 * @param db
	 * @param idType
	 * @param identifier
	 * @return IdCardPrintLogTable object
	 * @throws CprException
	 */
	public static IdCardPrintLogTable validateGetIdCardPrintLogParameters (final Database db,String idType, String identifier) 
		throws CprException {
		String eventIdCard = null;
		
		final IdentifierType identifierType = db.isValidIdentifierType(idType);
		if (identifierType.getTypeName().equals(Database.ID_CARD_IDENTIFIER)) {
			eventIdCard = identifier.trim();	
		}
		else {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Id Card Number");
		}
		return new IdCardPrintLogTable(eventIdCard);
	}
}
