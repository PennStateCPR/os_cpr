package edu.psu.iam.cpr.core.api;

import static edu.psu.iam.cpr.core.api.BaseApi.*;
import java.text.ParseException;
import java.util.Map;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.AddressServiceReturn;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.AddressReturn;

/**
 * This class provides the implementation for the Get Address API.
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
public class GetAddressApi extends ExtendedBaseApi {

    /**
     * This method is used to execute the core logic for a service.
     * @param apiName contains the name of the api.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the person identifier value.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains a Map of Java objects that are additional parameters for the service.
     * @return will return an object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     * @throws JMSException will be thown if there are any JMS issues.
     */
	@Override
	public Object runApi(final String apiName, final Database db, final ServiceCoreReturn serviceCoreReturn,
			final String updatedBy, final Map<String, Object> otherParameters,
			final boolean checkAuthorization) throws CprException, JSONException,
			ParseException, JMSException {

		final long personId = serviceCoreReturn.getPersonId();
		final String addressType 	= (String) otherParameters.get(ADDRESS_TYPE_KEY);
		final String returnHistory 	= (String) otherParameters.get(RETURN_HISTORY_KEY);
		final String addressKey		= (String) otherParameters.get(ADDRESS_KEY);

		// Validate the data passed to the service
		final AddressesTable addressTable = ValidateAddress.validateGetAddressParameters(db, personId,  
				updatedBy, addressType, returnHistory, addressKey);

		// Do the query.
		final AddressReturn[] addressResults = addressTable.getAddress(db, personId);
		
		// Build the return class
		return new AddressServiceReturn(ReturnType.SUCCESS.index(), ApiHelper.SUCCESS_MESSAGE, 
				addressResults, addressResults.length);

	}

}
