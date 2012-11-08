/* SVN FILE: $Id: GetPersonIdentifierImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonIdentifierTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PersonIdentifierReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidatePersonIdentifier;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.PersonIdentifierServiceReturn;

/**
 * This class provides an implementation for the get person identifier service.
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
public class GetPersonIdentifierImpl implements ServiceInterface {

	final private static Logger LOG4J_LOGGER = Logger.getLogger(GetPersonIdentifierImpl.class);
	private static final int BUFFER_SIZE = 2048;
	
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
		final ServiceCore serviceCore = new ServiceCore();
		PersonIdentifierServiceReturn serviceReturn = null;
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		
		LOG4J_LOGGER.info(serviceName + ": Start of service.");
		
		try {
			final String registryIdentifierType = (String) otherParameters[0];
			final String returnHistory 			= (String) otherParameters[1];
			
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("requestedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("registryIdentifierType=[").append(registryIdentifierType).append("] ");
			parameters.append("returnHistory=[").append(returnHistory).append("] ");
			LOG4J_LOGGER.info(serviceName + ": input parameters = " + parameters.toString());

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
			LOG4J_LOGGER.info(serviceName + ": person identifier found =" + serviceCoreReturn.getPersonId());
			
			// Do the Get.
			final PersonIdentifierTable personIdentifierTable = ValidatePersonIdentifier.validateGetPersonIdentifierParameters(db, 
						serviceCoreReturn.getPersonId(), 
						registryIdentifierType, 
						updatedBy, 
						returnHistory);
			final PersonIdentifierReturn queryResults[] = personIdentifierTable.getPersonIdentifiersForPersonId(db, serviceCoreReturn.getPersonId());
			
			// Build the return class.
			serviceReturn = new PersonIdentifierServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE, 
					queryResults, queryResults.length);
			LOG4J_LOGGER.info(serviceName + ": get person identifier, records returned = " + queryResults.length);
			
			// Log a success!
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			
			LOG4J_LOGGER.info(serviceName + ": success!");

			// Commit.
			db.closeSession();
			
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PersonIdentifierServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PersonIdentifierServiceReturn(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PersonIdentifierServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
			
		}
		
		LOG4J_LOGGER.info(serviceName + ": End of service.");
		
		// Return a successful status to the caller.
		return (Object) serviceReturn;
	}

}
