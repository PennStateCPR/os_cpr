package edu.psu.iam.cpr.core.api;

import java.text.ParseException;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.ConfidentialityServiceReturn;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateConfidentiality;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.ConfidentialityReturn;

/**
 * This class provides the implementation for the Get Confidentiality API.
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
public class GetConfidentialityApi extends ExtendedBaseApi {

	/** Contains the index of the return history parameter */
	private static final int RETURN_HISTORY = 0;
	
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
		String returnHistory = (String) otherParameters[RETURN_HISTORY];
		
		long personId = serviceCoreReturn.getPersonId();
		
		// Validate the input parameters.
		final ConfidentialityTable confidentialityTable = ValidateConfidentiality.validateGetConfidentialityParameters(db, 
										personId, updatedBy, returnHistory);
		
		// Get the data about the hold.
		final ConfidentialityReturn queryResults[] = confidentialityTable.getConfidentiality(db, personId);

		// Build the return class.
		return (Object) new ConfidentialityServiceReturn(ReturnType.SUCCESS.index(), ApiHelper.SUCCESS_MESSAGE, 
										queryResults.length, queryResults);			
	}

}
