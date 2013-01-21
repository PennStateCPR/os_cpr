package edu.psu.iam.cpr.core.api;

import java.text.ParseException;

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

	private static final int PSU_ID 		= 0;
	private static final int USERID 		= 1;
	private static final int SSN 			= 2;
	private static final int FIRST_NAME 	= 3;
	private static final int LAST_NAME 		= 4;
	private static final int MIDDLE_NAME 	= 5;
	private static final int ADDRESS1 		= 6;
	private static final int ADDRESS2 		= 7;
	private static final int ADDRESS3 		= 8;
	private static final int CITY 			= 9;
	private static final int STATE 			= 10;
	private static final int POSTAL_CODE 	= 11;
	private static final int PLUS4 			= 12;
	private static final int COUNTRY 		= 13;
	private static final int DATE_OF_BIRTH 	= 14;
	private static final int GENDER 		= 15;
	private static final int RANK_CUT_OFF 	= 16;

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
     * @throws JMSException will be thown if there are any JMS issues.
     */	
	@Override
	public Object runApi(String apiName, Database db, ServiceCoreReturn serviceCoreReturn,
			String updatedBy, Object[] otherParameters,
			boolean checkAuthorization) throws CprException, JSONException,
			ParseException, JMSException {
		final String psuId 			= (String) otherParameters[PSU_ID];
		final String userId 		= (String) otherParameters[USERID];
		final String ssn 			= (String) otherParameters[SSN];
		final String firstName 		= (String) otherParameters[FIRST_NAME];
		final String lastName 		= (String) otherParameters[LAST_NAME];
		final String middleName 	= (String) otherParameters[MIDDLE_NAME];
		final String address1 		= (String) otherParameters[ADDRESS1];
		final String address2 		= (String) otherParameters[ADDRESS2];
		final String address3 		= (String) otherParameters[ADDRESS3];
		final String city 			= (String) otherParameters[CITY];
		final String state 			= (String) otherParameters[STATE];
		final String postalCode 	= (String) otherParameters[POSTAL_CODE];
		final String plus4 			= (String) otherParameters[PLUS4];
		final String country 		= (String) otherParameters[COUNTRY];
		final String dateOfBirth 	= (String) otherParameters[DATE_OF_BIRTH];
		final String gender 		= (String) otherParameters[GENDER];
		final String rankCutOff 	= (String) otherParameters[RANK_CUT_OFF];
		
		final FindPersonHelper helper = new FindPersonHelper(db);

		return (Object) helper.doSearchForPersonOS(serviceCoreReturn, updatedBy, psuId, userId, ssn, 
				firstName, lastName, middleName, address1, address2, address3, city, state, postalCode, plus4, country, dateOfBirth, 
				gender, rankCutOff);				

	}

}
