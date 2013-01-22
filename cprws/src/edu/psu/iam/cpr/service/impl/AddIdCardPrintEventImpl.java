/* SVN FILE: $Id: AddIdCardPrintEventImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import java.text.ParseException;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.api.AddIdCardPrintEventApi;
import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
* This class provides the implementation of the AddIdCardPrintEvent.
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

public class AddIdCardPrintEventImpl extends BaseServiceImpl {

	@Override
	public void runService(String serviceName, Database db,
			ServiceCoreReturn serviceCoreReturn, String updatedBy,
			Object[] otherParameters) throws CprException, JSONException,
			ParseException, JMSException {
		
		new AddIdCardPrintEventApi().implementApi(serviceName, db, updatedBy, serviceCoreReturn, 
				otherParameters, ApiHelper.DO_AUTHZ_CHECK);
	}
		
}
