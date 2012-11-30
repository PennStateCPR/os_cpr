package edu.psu.iam.cpr.service.impl;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
/**
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
public abstract class GenericGetServiceImpl {
	
	/** Contains the log4j Logger instance */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(GenericGetServiceImpl.class);
	
	/** Buffer size */
	private static final int BUFFER_SIZE = 4096;
	
	/**
	 * This code provides an abstract template for the implementation of the "GET" service.
	 * @param serviceName contains the name of the service.
	 * @param ipAddress contains the IP address of the caller.
	 * @param principalId contains the principal id of the caller. 
	 * @param password contains the password for the principal.
	 * @param updatedBy contains the updated by user.
	 * @param identifierType contains the identifier type.
	 * @param identifier contains the identifier.
	 * @param otherParameters contains other parameters related to the service.
	 * @return will return an object containing the results of this service.
	 */
	public Object implementService(String serviceName, String ipAddress,
			String principalId, String password, String updatedBy,
			String identifierType, String identifier, Object[] otherParameters) {

		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		Object serviceReturn = null;

		try {
			
			// Dump the parameters to a formatted string.
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("requestedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			
			if (otherParameters != null) {
				for (int i = 0; i < otherParameters.length; ++i) {
					parameters.append("parameter");
					parameters.append((i+1));
					parameters.append("=[");
					try {
						parameters.append((String) otherParameters[i]);
					}
					catch (ClassCastException e) {
						parameters.append("Non-String Argument");
					}
					parameters.append("] ");
				}
			}
			LOG4J_LOGGER.info(serviceName + ": Input Parameters = " + parameters.toString());

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
			LOG4J_LOGGER.info(serviceName + ": Found Person Id = " + serviceCoreReturn.getPersonId());
					
			// Do the actual work of the service.
			serviceReturn = runService(db, serviceCoreReturn, updatedBy, otherParameters);
			
			// Log a success!
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			
			// Commit
			db.closeSession();
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return handleException(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return handleException(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return handleException(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
		}
		catch (RuntimeException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return handleException(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());
		}

		return serviceReturn;
	}
	
	public abstract Object runService(Database db, ServiceCoreReturn serviceCoreReturn, String updatedBy, Object[] otherParameters) 
									throws CprException;
	public abstract Object handleException(int statusCode, String statusMessage);

}
