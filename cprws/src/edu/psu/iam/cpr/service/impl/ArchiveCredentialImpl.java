/* SVN FILE: $Id: ArchiveCredentialImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.CredentialTable;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidateCredential;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.ServiceReturn;

/**
 * This class provides the implementation for the Archive Credential service.
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
public class ArchiveCredentialImpl implements ServiceInterface {

	final private static Logger log4jLogger = Logger.getLogger(ArchiveCredentialImpl.class);

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
		
		final ServiceHelper serviceHelper = new ServiceHelper();
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db = new Database();

		log4jLogger.info(serviceName + ": Start of service.");
		try {
			
			final String credentialType = (String) otherParameters[0];
			
			final StringBuilder parameters = new StringBuilder(10000);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("updatedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("credentialType=[").append(credentialType).append("] ");
			log4jLogger.info(serviceName + ": Input parameters = " + parameters.toString());

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
			log4jLogger.info(serviceName + ": Found person identifier = " + serviceCoreReturn.getPersonId());
									
			// Validate the parameters for the delete.
			final CredentialTable credentialTable = ValidateCredential.validateArchiveCredentialParameters(db, 
					serviceCoreReturn.getPersonId(), credentialType, updatedBy);
				
			// Determine if the caller is authorized to make this call.
			log4jLogger.info(serviceName + ": Determing if the caller is authorized");
			db.isDataActionAuthorized(serviceCoreReturn, credentialTable.getCredentialType().toString(), 
					AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy);
			
			// Attempt to do the archive.
			credentialTable.archiveCredential(db);
			
			// Log a success!
			serviceCoreReturn.getServiceLogTable().endLog(db, "SUCCESS!");
			log4jLogger.info(serviceName + ": SUCCESS!");
		
			// Commit
			db.closeSession();
		} 
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (GeneralDatabaseException e) {
			serviceHelper.handleGeneralDatabaseException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), e.getMessage());
		} 
		catch (Exception e) {
			serviceHelper.handleOtherException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.ARCHIVE_FAILED_EXCEPTION.index(), e.getMessage());
		} 
		finally {
		}
		log4jLogger.info(serviceName + ": End of service.");
		
		// Success so return it.
		return (Object) new ServiceReturn(ReturnType.SUCCESS.index(), "Success!");
	}

}
