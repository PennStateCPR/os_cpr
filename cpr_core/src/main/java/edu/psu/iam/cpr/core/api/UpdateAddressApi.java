package edu.psu.iam.cpr.core.api;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * This class provides the implementation for the Add Address API.
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
public class UpdateAddressApi extends BaseApi {

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
        final String addressType = (String) otherParameters.get(ADDRESS_TYPE_KEY);
        final String documentType = (String) otherParameters.get(DOCUMENT_TYPE_KEY);
        final Long groupId = (Long) otherParameters.get(GROUP_ID_KEY);
        final String address1 = (String) otherParameters.get(ADDRESS1_KEY);
        final String address2 = (String) otherParameters.get(ADDRESS2_KEY);
        final String address3 = (String) otherParameters.get(ADDRESS3_KEY);
        final String city = (String) otherParameters.get(CITY_KEY);
        final String stateOrProvince = (String) otherParameters.get(STATE_KEY);
        final String postalCode = (String) otherParameters.get(POSTALCODE_KEY);
        final String countryCode = (String) otherParameters.get(COUNTRY_KEY);
        final String campusCode = (String) otherParameters.get(CAMPUS_KEY);
        final String verifyAddressFlag  = (String) otherParameters.get(VERIFY_ADDRESS_FLAG_KEY);		
        final long personId = serviceCoreReturn.getPersonId();
		
		// Validate the data passed to the service
		final AddressesTable addressTableRecord = ValidateAddress.validateUpdateAddressParameters(db, personId, 
				addressType, documentType, groupId,  updatedBy, address1, address2, address3, city, stateOrProvince, postalCode,  
				countryCode, campusCode);
		final String doAddressTransform = Validate.isValidYesNo(verifyAddressFlag);
		if (doAddressTransform == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "verify address flag");
		}
		
		// Determine if the caller is authorized to make this call.
		if (checkAuthorization) { 
			serviceCoreReturn.getAuthorizationService().dataActionAuthorized(db,
					addressTableRecord.getAddressType().toString(), 
					AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy );
		}

		// update the address
		addressTableRecord.updateAddress(db);
		setRecordKey(addressTableRecord.getAddressesBean().getAddressKey());
		
		// Create a new json message.
		final JsonMessage jsonMessage = new JsonMessage(db, personId, apiName, updatedBy);
		jsonMessage.setAddress(addressTableRecord);
	
		return jsonMessage;
	}

}
