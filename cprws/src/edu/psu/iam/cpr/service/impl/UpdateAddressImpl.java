/* SVN FILE: $Id: UpdateAddressImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
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
 * This class provides an implementation for the update address service.
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
public class UpdateAddressImpl implements ServiceInterface {

	final private static Logger LOG4J_LOGGER = Logger.getLogger(UpdateAddressImpl.class);
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
		
		final ServiceHelper serviceHelper = new ServiceHelper();
		MessagingCore mCore = null;
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db=new Database();
		
		LOG4J_LOGGER.info("UpdateAddress: Start of service.");
		try {
			
			final String addressType = (String) otherParameters[0];
			final String documentType = (String) otherParameters[1];
			final Long groupId = (Long) otherParameters[2];
			final String address1 = (String) otherParameters[3];
			final String address2 = (String) otherParameters[4];
			final String address3 = (String) otherParameters[5];
			final String city = (String) otherParameters[6];
			final String stateOrProvince = (String) otherParameters[7];
			final String postalCode = (String) otherParameters[8];
			final String countryCode = (String) otherParameters[9];
			final String campusCode = (String) otherParameters[10];
			
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("updatedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("addressType=[").append(addressType).append("] ");
			parameters.append("documentType=[").append(documentType).append("] ");
			parameters.append("groupId=[").append(groupId).append("] ");
			parameters.append("address1=[").append(address1).append("] ");
			parameters.append("address2=[").append(address2).append("] ");
			parameters.append("address3=[").append(address3).append("] ");
			parameters.append("city=[").append(city).append("] ");
			parameters.append("stateOrProvince=[").append(stateOrProvince).append("] ");
			parameters.append("postalCode=[").append(postalCode).append("] ");
			parameters.append("countryCode=[").append(countryCode).append("] ");
			parameters.append("campusCode=[").append(campusCode).append("] ");
			LOG4J_LOGGER.info("UpdateAddress: Input Parameters = " + parameters.toString());
			

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
			LOG4J_LOGGER.info("UpdateAddress: Found Person Id = " + serviceCoreReturn.getPersonId());

			
			// Validate the data passed to the service
			
			final AddressesTable addressTableRecord = ValidateAddress.validateUpdateAddressParameters(db, serviceCoreReturn.getPersonId(), 
					addressType, documentType, groupId,  updatedBy, address1, address2, address3, city, stateOrProvince, postalCode,  
					countryCode, campusCode);
			
			if (CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_ADDRESS_VALIDATION.toString()).equals("YES")) {
				final TransformAddressHelper transformAddressHelper = new TransformAddressHelper();
				transformAddressHelper.transformRegistryAddress(db, serviceCoreReturn, addressTableRecord);
			}
			
			// Determine if the caller is authorized to make this call.
			db.isDataActionAuthorized(serviceCoreReturn,addressTableRecord.getAddressType().toString(), 
					AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy );

			// update the address
			addressTableRecord.updateAddress(db);
			
			// Create a new json message.
			final JsonMessage jsonMessage = new JsonMessage(db, serviceCoreReturn.getPersonId(), serviceName, updatedBy);
			jsonMessage.setAddress(addressTableRecord);
			
			// set up message connection
			mCore = serviceHelper.sendMessagesToServiceProviders(serviceName, mCore, db, jsonMessage); 

			// Log a success!
			LOG4J_LOGGER.info("UpdateAddress: Status = SUCCESS, record updated");
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			db.closeSession();
		
		}	
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
		} 
		catch (JSONException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.JSON_EXCEPTION.index(), e.getMessage());
		} 
		catch (JMSException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.JMS_EXCEPTION.index(), e.getMessage());
		}
		finally {
			mCore.closeMessaging();
		}
		return (Object) new ServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE);
	}

}
