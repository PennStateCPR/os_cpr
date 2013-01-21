package edu.psu.iam.cpr.core.api;

import java.text.ParseException;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.IAPServiceReturn;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.FederationTable;
import edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonUseridIap;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.IAPReturn;

/**
 * This class provides the implementation for the Get External IAP.
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
public class GetExternalIAPApi extends ExtendedBaseApi {

	
	/** Contains the index for the userid parameter */
	private static final int USERID = 0;
	
	/** Contains the index for the federation name parameter */
	private static final int FEDERATION_NAME = 1;
	
    /**
     * This method is used to execute the core logic for a service.
     * @param apiName contains the name of the api.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the person identifier value.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     */	
	@Override
	public Object runApi(String apiName, Database db, ServiceCoreReturn serviceCoreReturn,
			String updatedBy, Object[] otherParameters,
			boolean checkAuthorization) throws CprException, JSONException,
			ParseException, JMSException {
		String userId 				= (String) otherParameters[USERID];
		final String federationName = (String) otherParameters[FEDERATION_NAME];
		final long personId = serviceCoreReturn.getPersonId();
		
		// Validate the data passed to the service
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, personId, userId,federationName, updatedBy);
		
		FederationTable fedTable = new FederationTable();
		@SuppressWarnings("unused")
		boolean fedValid = fedTable.isFederationValid(db, federationName);
		
		// get a new iap table
		final PersonUseridIapTable personUseridIapTable  = new PersonUseridIapTable();
		userId = (userId != null) ? userId.trim() : null;
	
		final IAPReturn[] iapResults = personUseridIapTable.getExternalIAP(db, personId, userId, federationName);
	
		// Build the return class
		IAPServiceReturn serviceReturn = new IAPServiceReturn(ReturnType.SUCCESS.index(), 
				ApiHelper.SUCCESS_MESSAGE, iapResults, iapResults.length);
		
		return (Object) serviceReturn;
	}

}
