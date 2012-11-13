/* SVN FILE: $Id: AddIdCardPrintEventImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import java.text.ParseException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidateIdCardPrintLog;

/**
* This class provides the implementation of the AddIdCardPrintEvent.
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
* @package edu.psu.iam.cpr.service.impl
* @author $Author: jvuccolo $
* @version $Rev: 5343 $
* @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
*/

public class AddIdCardPrintEventImpl extends GenericServiceImpl {

	/** Contains the index for the identifier type parameter */
	private static final int IDENTIFIER_TYPE = 0;
	
	/** Contains the index for the identifier parameter */
	private static final int IDENTIFIER = 1;
	
	/** Contains the index for the event userid parameter */
	private static final int EVENT_USER_ID = 2;
	
	/** Contains the index for the ip address parameter */
	private static final int EVENT_IP_ADDRESS = 3;
	
	/** Contains the index for the workstation parameter */
	private static final int EVENT_WORKSTATION = 4;

	@Override
	public JsonMessage runService(String serviceName, Database db,
			ServiceCoreReturn serviceCoreReturn, String updatedBy,
			Object[] otherParameters) throws CprException, JSONException,
			ParseException {
		
		final String identifierType = (String) otherParameters[IDENTIFIER_TYPE];
		final String identifier = (String) otherParameters[IDENTIFIER];
		final String eventUserId = (String) otherParameters[EVENT_USER_ID];
		final String eventIpAddress = (String) otherParameters[EVENT_IP_ADDRESS];
		final String eventWorkStation = (String) otherParameters[EVENT_WORKSTATION];

		final IdCardPrintLogTable idCardPrintLogTableRecord = ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(db, 
				identifierType,identifier, 
				eventUserId, eventIpAddress, eventWorkStation);
		
		idCardPrintLogTableRecord.addIdCardPrintLog(db);
		
		return null;

	}
		
}
