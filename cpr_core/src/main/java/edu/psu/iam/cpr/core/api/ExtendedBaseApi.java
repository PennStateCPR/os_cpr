package edu.psu.iam.cpr.core.api;

import java.text.ParseException;
import java.util.Map;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
 * This class provides the abstract extended base API.
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
 * @package edu.psu.iam.cpr.api
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public abstract class ExtendedBaseApi {
	
	/** Contains the log4j Logger instance */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(ExtendedBaseApi.class);
	
	/**
	 * Provides the abstract template for an API.
	 * @param apiName contains the name of the API.
	 * @param db contains the database connection.
	 * @param updatedBy contains the entity that is updating the record.
	 * @param serviceCoreReturn contains the person identifier.
 	 * @param otherParameters contain the map of parameters to the API.
	 * @param checkAuthorization contains a flag that determines if AuthZ is to be checked or not.
	 * @return will return a service return object.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException will be thrown if there are any JSON related problems.
	 * @throws ParseException will be thrown if there are any parsing problems.
	 * @throws JMSException will be thrown if there are any JMS problems.
	 */
	public Object implementApi(final String apiName, final Database db, final String updatedBy, 
			final ServiceCoreReturn serviceCoreReturn, final Map<String, Object> otherParameters, 
			final boolean checkAuthorization) throws CprException, JSONException, ParseException, JMSException {
		
		LOG4J_LOGGER.info(apiName + ": Start of api.");

		// dump the parameters.
		LOG4J_LOGGER.info(apiName + ": Input Parameters = " + ApiHelper.dumpParameters(otherParameters));	
		
		// Run the api.
		Object apiReturn = runApi(apiName, db, serviceCoreReturn, updatedBy, otherParameters, checkAuthorization);
		
		LOG4J_LOGGER.info(apiName + ": End of api.");
		
		return apiReturn;
		
	}
	
	public abstract Object runApi(final String apiName, final Database db, final ServiceCoreReturn serviceCoreReturn, 
			final String updatedBy, final Map<String, Object> otherParameters, final boolean checkAuthorization) 
				throws CprException, JSONException, ParseException, JMSException;


}
