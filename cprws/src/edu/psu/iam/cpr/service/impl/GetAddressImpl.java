/* SVN FILE: $Id: GetAddressImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import org.apache.log4j.Logger;
//import org.perf4j.log4j.Log4JStopWatch;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.AddressReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidateAddress;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.AddressServiceReturn;

/**
 * This service provides the implementation for the get address service.
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
public class GetAddressImpl implements ServiceInterface {

	final private static Logger log4jLogger = Logger.getLogger(GetAddressImpl.class);
	
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
		
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		AddressServiceReturn serviceReturn = null;
		final ServiceCore serviceCore = new ServiceCore();
		final Database db=new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		
		log4jLogger.info(serviceName + ": Start of service.");
		try {
			final String addressType 	= (String) otherParameters[0];
			final String returnHistory 	= (String) otherParameters[1];
			
			final StringBuilder parameters = new StringBuilder(1024);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("requestedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("addressType=[").append(addressType).append("] ");
			parameters.append("returnHistory=[").append(returnHistory).append("] ");
			log4jLogger.info(serviceName + ": Input Parameters = " + parameters.toString());

			// Init the service.
			//Log4JStopWatch totalWatch = new Log4JStopWatch("Initialize Service");
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
			//totalWatch.stop();
			log4jLogger.info(serviceName + ": Found Person Id = " + serviceCoreReturn.getPersonId());

			// Validate the data passed to the service
			//totalWatch = new  Log4JStopWatch("Data Validation");
			final AddressesTable addressTable = ValidateAddress.validateGetAddressParameters(db, serviceCoreReturn.getPersonId(),  updatedBy, addressType, returnHistory);
			//totalWatch.stop();
			
			//totalWatch = new  Log4JStopWatch("Get Data");
			final AddressReturn[] addressResults = addressTable.getAddress(db, serviceCoreReturn.getPersonId());
			//totalWatch.stop();

			// Build the return class
			serviceReturn = new AddressServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE, addressResults, addressResults.length);
			
			// Log a success!
			log4jLogger.info(serviceName + ": Status = SUCCESS, query returned " + addressResults.length + " elements.");
			//totalWatch = new Log4JStopWatch("End of logging");
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			//totalWatch.stop();
			//totalWatch = new Log4JStopWatch("Commit");
			db.closeSession();
			//totalWatch.stop();
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new AddressServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (GeneralDatabaseException e) {
			serviceHelper.handleGeneralDatabaseException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new AddressServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), e.getMessage());
		}
		catch (Exception e) {
			serviceHelper.handleOtherException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new AddressServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), e.getMessage());
		}
		log4jLogger.info("GetAddress: End of service.");
		return (Object) serviceReturn;
	}

}
