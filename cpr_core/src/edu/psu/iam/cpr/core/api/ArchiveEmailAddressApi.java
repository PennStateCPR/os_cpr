package edu.psu.iam.cpr.core.api;

import java.text.ParseException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateEmail;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
 * This class provides the implementation for the Archive Email Address API.
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
 * @package edu.psu.iam.cpr.core.api
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class ArchiveEmailAddressApi extends BaseApi {

	/** Contains the index for the email address type parameter */
	private static final int EMAIL_ADDRESS_TYPE = 0;
	
    /**
     * This method is used to execute the core logic for a service.
     * @param apiName contains the name of the api.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the person identifier value.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an JsonMessage object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     */	
	@Override
	public JsonMessage runApi(String apiName, Database db, ServiceCoreReturn serviceCoreReturn,
			String updatedBy, Object[] otherParameters,
			boolean checkAuthorization) throws CprException, JSONException,
			ParseException {
		final String emailAddressType = (String) otherParameters[EMAIL_ADDRESS_TYPE];
		final long personId = serviceCoreReturn.getPersonId();
		
		// Validate the input parameters.
		EmailAddressTable emailAddressTable = ValidateEmail.validateArchiveEmailAddressParameters(db, 
				personId, emailAddressType, updatedBy);
		
		// Determine if the caller is authorized to make this call.
		if (checkAuthorization) {
			serviceCoreReturn.getAuthorizationService().dataActionAuthorized(db, 
					emailAddressTable.getEmailAddressType().toString(), 
					AccessType.ACCESS_OPERATION_ARCHIVE.toString(), updatedBy);
		}

		// Perform the delete.
		emailAddressTable.archiveEmailAddress(db);
		
		// Create a new json message.
		JsonMessage jsonMessage = new JsonMessage(db, personId, apiName, updatedBy);
		jsonMessage.setEmailAddress(emailAddressTable);
		
		return jsonMessage;
	}

}
