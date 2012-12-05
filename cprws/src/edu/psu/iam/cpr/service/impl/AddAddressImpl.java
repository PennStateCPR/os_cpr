/* SVN FILE: $Id: AddAddressImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import java.text.ParseException;

/**
 * This class provides an implementation for the Add Address service.
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
public class AddAddressImpl extends BaseServiceImpl {

	/** Contains the index for the address type */
	private static final int ADDRESS_TYPE = 0;
	
	/** Contains the index for the document type parameter */
	private static final int DOCUMENT_TYPE = 1;
	
	/** Contains the index for the address line 1 parameter */
	private static final int ADDRESS1 = 2;
	
	/** Contains the index for the address line 2 parameter */
	private static final int ADDRESS2 = 3;
	
	/** Contains the index for the address line 3 parameter */
	private static final int ADDRESS3 = 4;
	
	/** Contains the index for the city parameter */
	private static final int CITY = 5;
	
	/** Contains the index for the state parameter */
	private static final int STATE = 6;
	
	/** Contains the index for postal code parameter */
	private static final int POSTAL_CODE = 7;
	
	/** Contains the index for the country code parameter */
	private static final int COUNTRY_CODE = 8;
	
	/** Contains the index for the campus code parameter */
	private static final int CAMPUS_CODE = 9;

   /**
     * This method is used to execute the core logic for a service.
     * @param serviceName contains the name of the service.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the service core information.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an JsonMessage object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     */	
	@Override
	public JsonMessage runService(String serviceName, Database db,
			ServiceCoreReturn serviceCoreReturn, String updatedBy,
			Object[] otherParameters) throws CprException, JSONException, ParseException {

		final String addressType 	= (String) otherParameters[ADDRESS_TYPE];
		final String documentType = (String) otherParameters[DOCUMENT_TYPE];
		final String address1 	= (String) otherParameters[ADDRESS1];
		final String address2 	= (String) otherParameters[ADDRESS2];
		final String address3 	= (String) otherParameters[ADDRESS3];
		final String city 		= (String) otherParameters[CITY];
		final String stateOrProvince = (String) otherParameters[STATE];
		final String postalCode 	= (String) otherParameters[POSTAL_CODE];
		final String countryCode 	= (String) otherParameters[COUNTRY_CODE];
		final String campusCode 	= (String) otherParameters[CAMPUS_CODE];
		
		final AddressesTable addressTableRecord = ValidateAddress.validateAddAddressParameters(db, serviceCoreReturn.getPersonId(), 
				addressType, documentType,  updatedBy, 
				address1, address2, address3, city, stateOrProvince, postalCode,  countryCode, campusCode);
		
		// Determine if the caller is authorized to make this call.
		db.isDataActionAuthorized(addressTableRecord.getAddressType().toString(), 
				AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy);
		
		// add the address
		addressTableRecord.addAddress(db);

		// Create a new json message.
		final JsonMessage jsonMessage = new JsonMessage(db, serviceCoreReturn.getPersonId(), serviceName, updatedBy);
		jsonMessage.setAddress(addressTableRecord);
		
		return jsonMessage;
	}
}
