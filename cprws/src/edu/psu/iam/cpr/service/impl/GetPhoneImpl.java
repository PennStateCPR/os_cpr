/* SVN FILE: $Id: GetPhoneImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PhoneReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidatePhone;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.PhoneServiceReturn;

/**
 * This class provides an implementation for the get phone service.
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
public class GetPhoneImpl implements ServiceInterface {

	final private static Logger log4jLogger = Logger.getLogger(GetPhoneImpl.class);
	
	/**
	 * This method provides the implementation for a service.
	 * @param serviceName contains the name of the service.
	 * @param ipAddress contains the ip address of the caller. 
	 * @param principalId contains the principal identifier that is used to authenticate the service.
	 * @param password contains the password associated with the principal.
	 * @param updatedBy contains the user that either updated or requested information.
	 * @param identifierType contains the identifier type used to find the user.
	 * @param identifier contains the value of the identifier.
	 * @param otherParameters contains an array of Objects that are additional parameters to the service implementation.
	 * @return Object will return an object will needed to be casted to obtain the real return.
	 */

	public Object implementService(String serviceName, String ipAddress,
			String principalId, String password, String updatedBy,
			String identifierType, String identifier, Object[] otherParameters) {
		
		PhoneServiceReturn serviceReturn = null;
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db=new Database();
		ServiceHelper serviceHelper = new ServiceHelper();
		
		log4jLogger.info(serviceName + ": Start of service.");
		try {
			
			final String phoneType = (String) otherParameters[0];
			final String returnHistory = (String) otherParameters[1];
			
			final StringBuilder parameters = new StringBuilder();
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("requestedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("phoneType=[").append(phoneType).append("] ");
			parameters.append("returnHistory=[").append(returnHistory).append("] ");
			log4jLogger.info(serviceName + ": Input Parameters = " + parameters.toString());

			// Init the service.
			serviceCoreReturn = serviceHelper.initializeService(serviceName, 
					ipAddress,
					principalId,
					password,
					updatedBy,
					identifierType, 
					identifier,
					serviceCore, 
					db, 
					parameters);
			log4jLogger.info(serviceName + ": Found Person Id = " + serviceCoreReturn.getPersonId());
			
			// Validate the input parameters and do the query.
			final PhonesTable phonesTable = ValidatePhone.validateGetPhonesParameters(db, serviceCoreReturn.getPersonId(), 
													updatedBy, phoneType, returnHistory);
		    PhoneReturn [] queryResults =  phonesTable.getPhones(db, serviceCoreReturn.getPersonId());
			
			// Build the return value class.
			serviceReturn = new PhoneServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE, queryResults, queryResults.length);
			
			// Log Success
			log4jLogger.info(serviceName + ": Status = SUCCESS, query returned " + queryResults.length + " elements.");
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			db.closeSession();
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new PhoneServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (GeneralDatabaseException e) {
			serviceHelper.handleGeneralDatabaseException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new PhoneServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), e.getMessage());
		}
		catch (Exception e) {
			serviceHelper.handleOtherException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new PhoneServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), e.getMessage());
		}
		
		// Return the results to the caller.
		log4jLogger.info(serviceName + ": End of service.");
		return (Object) serviceReturn;
	}

}
