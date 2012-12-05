/* SVN FILE: $Id: GetPSUIAPImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import java.text.ParseException;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.IAPReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonUseridIap;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.returns.IAPServiceReturn;

/**
 * This class provides an implementation for the get psu IAP service.
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
public class GetPSUIAPImpl extends ExtendedBaseServiceImpl {

	/** Contains the index for the userid parameter */
	private static final int USERID = 0;
	
	/** Contains the index for the return history parameter */
	private static final int RETURN_HISTORY = 1;
	
    /**
     * This method is used to execute the core logic for a service.
     * @param db contains a open database session.
     * @param serviceName contains the name of the service.
     * @param log4jLogger database logger.
     * @param serviceHelper contains the service helper object.
     * @param serviceCoreReturn contains the service core information.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any JSON issues.
     * @throws JMSException will be thrown if there are any JMS issues.
     * @throws ParseException will be thrown if there are any parsing issues.
     */
	@Override
	public Object runService(Database db, String serviceName,
			Logger log4jLogger, ServiceHelper serviceHelper, ServiceCoreReturn serviceCoreReturn, String updatedBy, 
			Object[] otherParameters) throws CprException, JMSException, JSONException, ParseException {
		
		String userId 				= (String) otherParameters[USERID];
		final String returnHistory 	= (String) otherParameters[RETURN_HISTORY];
		
		// Validate the data passed to the service
		PersonUseridIapTable personUseridIapTable = ValidatePersonUseridIap.validateGetPsuIapParameters(db, 
								serviceCoreReturn.getPersonId(), userId, updatedBy, returnHistory);
		userId = (userId != null) ? userId.trim() : null;
		final IAPReturn[] iapResults = personUseridIapTable.getPSUIAP(db, serviceCoreReturn.getPersonId(), userId);

		// Build the return class
		return (Object) new IAPServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE, iapResults, iapResults.length);
		
	}
	
    /**
     * This routine is used to handle exceptions.
     * @param statusCode contains the status code associated with the exception.
     * @param statusMessage contains the error message text.
     * @return will return an service return containing the exception information.
     */
	@Override
	public Object handleException(int statusCode, String statusMessage) {
		return (Object) new IAPServiceReturn(statusCode, statusMessage);
	}
}
