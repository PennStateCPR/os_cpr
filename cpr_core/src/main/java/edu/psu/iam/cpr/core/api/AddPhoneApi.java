package edu.psu.iam.cpr.core.api;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePhone;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
 * This class provides the implementation for the Add Phone API.
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
public class AddPhoneApi extends BaseApi {

    /**
     * This method is used to execute the core logic for a service.
     * @param apiName contains the name of the api.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the person identifier value.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an map of Java objects that are additional parameters for the service.
     * @return will return an JsonMessage object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     */	
	@Override
	public JsonMessage runApi(final String apiName, final Database db, final ServiceCoreReturn serviceCoreReturn,
			final String updatedBy, final Map<String, Object> otherParameters,
			final boolean checkAuthorization) throws CprException, JSONException,
			ParseException {
        final String phoneType = (String) otherParameters.get(PHONE_TYPE_KEY);
        final String phoneNumber = (String) otherParameters.get(PHONE_NUMBER_KEY);
        final String extension = (String) otherParameters.get(PHONE_EXTENSION_KEY);
        final String internationalNumber = (String) otherParameters.get(PHONE_INTERNATIONAL_NUMBER_KEY);
        final long personId = serviceCoreReturn.getPersonId();

		// Validate the data.
		final PhonesTable phonesTableRecord = ValidatePhone.validateAddPhonesParameters(db, personId, 
				phoneType, phoneNumber, extension, internationalNumber, updatedBy);
		
		// Determine if the caller is authorized to make this call.
		if (checkAuthorization) {
			serviceCoreReturn.getAuthorizationService().dataActionAuthorized(db, 
					phonesTableRecord.getPhoneType().toString(), 
					AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy);
		}
		
		// Do the add.
		phonesTableRecord.addPhone(db);

		setRecordKey(phonesTableRecord.getPhonesBean().getPhoneKey());
		
		// Create a new json message.
		final JsonMessage jsonMessage = new JsonMessage(db, personId, apiName, updatedBy);
		jsonMessage.setPhone(phonesTableRecord);
		
		return jsonMessage;
	}

}
