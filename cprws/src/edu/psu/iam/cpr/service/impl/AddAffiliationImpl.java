/* SVN FILE: $Id: AddAffiliationImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import java.text.ParseException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonAffiliation;

/**
 * This class provides an implementation for the Add Affiliation service.
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
public class AddAffiliationImpl extends BaseServiceImpl {

	/** Contains the index for the affiliation parameter */
	private static final int AFFILIATION = 0;
	
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
		
		final String affiliation = (String) otherParameters[AFFILIATION];
		
		// Validate the data passed to the service
		final PersonAffiliationTable aTableRecord = ValidatePersonAffiliation
				.validateAddAffiliationParameters(db,serviceCoreReturn.getPersonId(),
						affiliation, updatedBy);
		
		// add the AFfiliation
		db.isAffiliationAccessAuthorized(affiliation, updatedBy);
		aTableRecord.addAffiliation(db);
		
		// Create a new json message.
		final JsonMessage jsonMessage = new JsonMessage(db,serviceCoreReturn.getPersonId(), serviceName,
				updatedBy);
		jsonMessage.setAffiliation(aTableRecord);
		return jsonMessage;
	}
}
