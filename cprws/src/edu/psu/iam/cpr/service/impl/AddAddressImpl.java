/* SVN FILE: $Id: AddAddressImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.ValidateAddress;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.helper.TransformAddressHelper;
import edu.psu.iam.cpr.service.returns.ServiceReturn;

/**
 * This class provides an implementation for the Add Address service.
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
public class AddAddressImpl implements ServiceInterface {

	final private static Logger log4jLogger = Logger.getLogger(AddAddressImpl.class);

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

	public Object implementService(String serviceName,
								String ipAddress, 
								String principalId, 
								String password, 
								String updatedBy,
								String identifierType, 
								String identifier, 
								Object[] otherParameters) {
		
		MessagingCore mCore=null;
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();

		log4jLogger.info(serviceName + ": Start of service.");

		try {

			final String addressType 	= (String) otherParameters[0];
			final String documentType = (String) otherParameters[1];
			final String address1 	= (String) otherParameters[2];
			final String address2 	= (String) otherParameters[3];
			final String address3 	= (String) otherParameters[4];
			final String city 		= (String) otherParameters[5];
			final String stateOrProvince = (String) otherParameters[6];
			final String postalCode 	= (String) otherParameters[7];
			final String countryCode 	= (String) otherParameters[8];
			final String campusCode 	= (String) otherParameters[9];

			final StringBuilder parameters = new StringBuilder(128);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("updatedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("addressType=[").append(addressType).append("] ");
			parameters.append("documentType=[").append(documentType).append("] ");
			parameters.append("address1=[").append(address1).append("] ");
			parameters.append("address2=[").append(address2).append("] ");
			parameters.append("address3=[").append(address3).append("] ");
			parameters.append("city=[").append(city).append("] ");
			parameters.append("stateOrProvince=[").append(stateOrProvince).append("] ");
			parameters.append("postalCode=[").append(postalCode).append("] ");
			parameters.append("countryCode=[").append(countryCode).append("] ");
			parameters.append("campusCode=[").append(campusCode).append("] ");
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

			final AddressesTable addressTableRecord = ValidateAddress.validateAddAddressParameters(db, serviceCoreReturn.getPersonId(), 
					addressType, documentType,  updatedBy, 
					address1, address2, address3, city, stateOrProvince, postalCode,  countryCode, campusCode);
			
			if (CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_ADDRESS_VALIDATION.toString()).equals("YES")) {
				final TransformAddressHelper transformAddressHelper = new TransformAddressHelper();
				transformAddressHelper.transformRegistryAddress(db, serviceCoreReturn, addressTableRecord);
			}
			
			// Determine if the caller is authorized to make this call.
			//db.isDataActionAuthorized(serviceCoreReturn.getIamGroupKey(), addressTableRecord.getAddressType().index(), AccessType.ACCESS_OPERATION_WRITE.index(), updatedBy);
			db.isDataActionAuthorized(serviceCoreReturn,addressTableRecord.getAddressType().toString(), AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy);
			// add the address
			addressTableRecord.addAddress(db);


			// Create a new json message.
			final JsonMessage jsonMessage = new JsonMessage(db, serviceCoreReturn.getPersonId(), serviceName, updatedBy);
			jsonMessage.setAddress(addressTableRecord);

			// set up message connection
			mCore = serviceHelper.sendMessagesToServiceProviders(serviceName, mCore, db, jsonMessage); 

			// Log a success!
			log4jLogger.info(serviceName + ": Status = SUCCESS, record added.");

			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
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
			return (Object) new ServiceReturn(ReturnType.ADD_FAILED_EXCEPTION.index(), e.getMessage());
		}
		finally {
			try {
				mCore.closeMessaging();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		log4jLogger.info(serviceHelper + ": End of service.");

		return (Object) new ServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE);

	}

}
