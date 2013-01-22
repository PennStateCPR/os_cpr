package edu.psu.iam.cpr.service.impl;

import java.text.ParseException;


import javax.jms.JMSException;
import org.apache.log4j.Logger;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.core.api.UpdatePersonApi;
import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.PersonServiceReturn;

/**
 * This class provides the implementation for the Update Person service.
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
public class UpdatePersonImpl extends ExtendedBaseServiceImpl {

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
	public Object runService(Database db, String serviceName,
			Logger log4jLogger, ServiceHelper serviceHelper,
			ServiceCoreReturn serviceCoreReturn, String updatedBy,
			Object[] otherParameters) throws CprException, JSONException,
			JMSException, ParseException {
		
		return (Object) new UpdatePersonApi().implementApi(serviceName, db, updatedBy, 
				serviceCoreReturn, 
				otherParameters, ApiHelper.DO_AUTHZ_CHECK);
	}

	/**
	 * This routine is used to handle exception processing.
	 * @param statusCode contains the status code.
	 * @param statusMessage contains the status message.
	 * @return will return an service return object.
	 */
	@Override
	public Object handleException(int statusCode, String statusMessage) {
		return (Object) new PersonServiceReturn(statusCode, statusMessage);
	}

}
