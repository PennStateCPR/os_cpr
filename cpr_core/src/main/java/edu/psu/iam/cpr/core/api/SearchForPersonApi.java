package edu.psu.iam.cpr.core.api;

import static edu.psu.iam.cpr.core.api.BaseApi.*;
import java.text.ParseException;
import java.util.Map;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.FindPersonHelper;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
 * This class provides the implementation for the Find Person API.
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
public class SearchForPersonApi extends ExtendedBaseApi {

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
        final String psuId              = (String) otherParameters.get(PSUID_KEY);
        final String userId             = (String) otherParameters.get(USERID_KEY);
        final String ssn				= (String) otherParameters.get(SSN_KEY);
        final String firstName          = (String) otherParameters.get(FIRST_NAME_KEY);
        final String lastName           = (String) otherParameters.get(LAST_NAME_KEY);
        final String middleName         = (String) otherParameters.get(MIDDLE_NAMES_KEY);
        final String address1           = (String) otherParameters.get(ADDRESS1_KEY);
        final String address2           = (String) otherParameters.get(ADDRESS2_KEY);
        final String address3           = (String) otherParameters.get(ADDRESS3_KEY);
        final String city				= (String) otherParameters.get(CITY_KEY);
        final String state				= (String) otherParameters.get(STATE_KEY);
        final String postalCode         = (String) otherParameters.get(POSTALCODE_KEY);
        final String plus4				= (String) otherParameters.get(PLUS4_KEY);
        final String country            = (String) otherParameters.get(COUNTRY_KEY);
        final String dateOfBirth        = (String) otherParameters.get(DOB_KEY);
        final String gender             = (String) otherParameters.get(GENDER_KEY);
        final String rankCutOff         = (String) otherParameters.get(RANK_CUTOFF_KEY);		
		final FindPersonHelper helper = new FindPersonHelper(db);

		return helper.doSearchForPersonOS(serviceCoreReturn, updatedBy, psuId, userId, ssn, 
				firstName, lastName, middleName, address1, address2, address3, city, state, postalCode, plus4, country, dateOfBirth, 
				gender, rankCutOff);				

	}

}
