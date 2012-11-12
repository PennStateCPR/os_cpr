/* SVN FILE: $Id: GetPhotoImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonPhotoTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PhotoReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidatePersonPhoto;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.PhotoServiceReturn;

/**
 * This class provides an implementation for the get photo service.
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
public class GetPhotoImpl implements ServiceInterface {

	private static final Logger LOG4J_LOGGER = Logger.getLogger(GetPhotoImpl.class);
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
		
		PhotoServiceReturn serviceReturn = null;
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		
		LOG4J_LOGGER.info(serviceName + ": Start of service.");
		try {
			
			// Build the parameters string.
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("requestedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
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
									
			// Do the query from the database.
			ValidatePersonPhoto.validateGetPhotoParameters(db, serviceCoreReturn.getPersonId(), updatedBy);
			final PersonPhotoTable personPhotoTable = new PersonPhotoTable();
			LOG4J_LOGGER.info(serviceName + ": Performing query for Person Id = " + serviceCoreReturn.getPersonId());
			final PhotoReturn queryResults[] = personPhotoTable.getPhoto(db, serviceCoreReturn.getPersonId());
			
			// Build the return class.
			serviceReturn = new PhotoServiceReturn();
			serviceReturn.setStatusCode(ReturnType.SUCCESS.index());
			serviceReturn.setStatusMessage(ServiceHelper.SUCCESS_MESSAGE);
			serviceReturn.setNumberElements(queryResults.length);
			serviceReturn.setPhotoReturn(queryResults);
			
			LOG4J_LOGGER.info(serviceName + ": Status = SUCCESS, query returned " + queryResults.length + " elements.");
			
			// Log a success!
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			
			// Commit
			db.closeSession();
		} 
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PhotoServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PhotoServiceReturn(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PhotoServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);			
		}
		catch (RuntimeException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PhotoServiceReturn(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());			
		}
				
		LOG4J_LOGGER.info(serviceName + ": End of service.");
		
		// Return the results to the caller.
		return (Object) serviceReturn;
	}

}
