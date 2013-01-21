package edu.psu.iam.cpr.core.api;

import java.text.ParseException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonLinkageTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonLinkage;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

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
public class ArchivePersonLinkageApi extends BaseApi {

	/** Contains the index for the linkage type parameter */
	private static final int LINKAGE_TYPE = 0;
	
	/** Contains the index for the identifier type parameter */
	private static final int IDENTIFIER_TYPE = 1;
	
	/** Contains the index for the identifier parameter */
	private static final int IDENTIFIER = 2;
	
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
		
		final String linkageType 			= (String) otherParameters[LINKAGE_TYPE];
		final String linkedIdentifierType 	= (String) otherParameters[IDENTIFIER_TYPE];
		final String linkedIdentifier 		= (String) otherParameters[IDENTIFIER];

		// Validate the parameters.
		final PersonLinkageTable personLinkageTable = ValidatePersonLinkage.validatePersonLinkageParameters(db, 
				serviceCoreReturn.getPersonId(), linkedIdentifierType, linkedIdentifier, linkageType, updatedBy);
		
		// Do the archive of the linkage.
		personLinkageTable.archivePersonLinkage(db);
		
		return null;
	}

}
