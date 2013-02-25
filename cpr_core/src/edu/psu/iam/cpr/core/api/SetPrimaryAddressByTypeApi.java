package edu.psu.iam.cpr.core.api;

import java.text.ParseException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
 * This class provides the implementation for the Set Primary Address By Type API.
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
public class SetPrimaryAddressByTypeApi extends BaseApi {

	/** Contains the index for the address type parameter */
	private static final int ADDRESS_TYPE = 0;
	
	/** Contains the index for the document type parameter */
	private static final int DOCUMENT_TYPE = 1;
	
	/** Contains the index for the group id parameter */
	private static final int GROUP_ID = 2;

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
		final String addressType = (String) otherParameters[ADDRESS_TYPE];
		final String documentType = (String) otherParameters[DOCUMENT_TYPE];
		final Long groupId = (Long) otherParameters[GROUP_ID];
		final long personId = serviceCoreReturn.getPersonId();
		
		// Validate the data passed to the service
		AddressesTable addressTableRecord = ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, personId,
				addressType, documentType, groupId, updatedBy);
		
		if (checkAuthorization) {
			serviceCoreReturn.getAuthorizationService().dataActionAuthorized(db, 
					addressTableRecord.getAddressType().toString(), 
					AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy );
		}
		
		// set primary
		addressTableRecord.setPrimaryByType(db);
		
		// Create a new json message.
		final JsonMessage jsonMessage = new JsonMessage(db, personId, apiName, updatedBy);
		jsonMessage.setAddress(addressTableRecord);
	
		return jsonMessage;
	}

}
