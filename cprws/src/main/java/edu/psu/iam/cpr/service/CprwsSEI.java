package edu.psu.iam.cpr.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam.Mode;

import edu.psu.iam.cpr.core.api.returns.NamesServiceReturn;
import edu.psu.iam.cpr.core.api.returns.ServiceReturn;
import edu.psu.iam.cpr.core.api.returns.AddressServiceReturn;
import edu.psu.iam.cpr.core.api.returns.AffiliationServiceReturn;
import edu.psu.iam.cpr.core.api.returns.ConfidentialityServiceReturn;
import edu.psu.iam.cpr.core.api.returns.CredentialServiceReturn;
import edu.psu.iam.cpr.core.api.returns.EmailAddressServiceReturn;
import edu.psu.iam.cpr.core.api.returns.FindPersonServiceReturn;
import edu.psu.iam.cpr.core.api.returns.IAPServiceReturn;
import edu.psu.iam.cpr.core.api.returns.IdCardPrintEventServiceReturn;
import edu.psu.iam.cpr.core.api.returns.IdCardServiceReturn;
import edu.psu.iam.cpr.core.api.returns.MatchCodeServiceReturn;
import edu.psu.iam.cpr.core.api.returns.PersonIdCardNumberServiceReturn;
import edu.psu.iam.cpr.core.api.returns.PersonIdentifierServiceReturn;
import edu.psu.iam.cpr.core.api.returns.PersonLinkageServiceReturn;
import edu.psu.iam.cpr.core.api.returns.PersonServiceReturn;
import edu.psu.iam.cpr.core.api.returns.PhoneServiceReturn;
import edu.psu.iam.cpr.core.api.returns.PhotoServiceReturn;
import edu.psu.iam.cpr.core.api.returns.RulesServiceReturn;
import edu.psu.iam.cpr.core.api.returns.TransformServiceReturn;
import edu.psu.iam.cpr.core.api.returns.UserCommentServiceReturn;
import edu.psu.iam.cpr.core.api.returns.UseridServiceReturn;


/**
 * The functions contained in this file provide an implementation for the CPR SOAP-based services.
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
 * @package edu.psu.iam.cpr.service
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
@WebService(name = "CprwsSEI", targetNamespace = "http://service.cpr.iam.psu.edu/")
public interface CprwsSEI {

	/**
	 * This function provides the implementation for the AddAddress SOAP web service.
	 * AddAddress  will allow authorized registration authorities to add an address for a person in the
	 * Central Person Registry.  The RA must specify the type of address along with address.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to add a particular address type.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param addressType the type of address to add
	 * @param documentType the type of document
	 * @param address1 line 1 of address
	 * @param address2 line 2 of address
	 * @param address3 line 3 of address
	 * @param city the city of the address
	 * @param stateOrProvince the state of the address, if US address; Province for non-US addresses
	 * @param postalCode the postal code of the address. For US address, may include plus4 code.
	 * @param countryCode the three character country code.
	 * @param campusCode the two character campus code.
	 * @param verifyAddressFlag flag to control address validation
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddAddress")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "addressType", mode = Mode.IN) String addressType,
			@WebParam(name = "documentType", mode = Mode.IN) String documentType,
			@WebParam(name = "address1", mode = Mode.IN) String address1,
			@WebParam(name = "address2", mode = Mode.IN) String address2,
			@WebParam(name = "address3", mode = Mode.IN) String address3,
			@WebParam(name = "city", mode = Mode.IN) String city,
			@WebParam(name = "stateOrProvince", mode = Mode.IN) String stateOrProvince,
			@WebParam(name = "postalCode", mode = Mode.IN) String postalCode,
			@WebParam(name = "countryCode", mode = Mode.IN) String countryCode,
			@WebParam(name = "campusCode", mode = Mode.IN) String campusCode,
			@WebParam(name = "verifyAddressFlag", mode = Mode.IN) String verifyAddressFlag );

	/**
	 * This function provides the implementation for the ArchiveAddress SOAP web service.  ArchiveAddress will allow 
	 * authorized registration authorities to archive address information for a person in the Central Person Registry.  
	 * The RA must specify the type of the address to be archived. Authorization checks are made to determine if the RA agent is 
	 * allowed to call the service and to archive the particular address type. 
	 *  
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is requesting the addresses, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param addressType  the type of address to delete
	 * @param documentType the type of document
	 * @param groupId the groupId of the address record within the address type
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * 	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveAddress")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "addressType", mode = Mode.IN) String addressType,
			@WebParam(name = "documentType", mode = Mode.IN) String documentType,
			@WebParam(name = "groupId", mode = Mode.IN) Long groupId);

	/**
	 * This function provides the implementation for the GetAddress SOAP web service.  GetAddress will allow 
	 * authorized registration authorities to obtain address information for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service. 
	 *  
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param requestedBy the person (userid) who is requesting the addresses, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param addressType if specified will contain the type of address to be returned.
	 * @param returnHistory a Y/N flag that indicates whether to return history or not.
	 * @return AddressServiceReturn object that contains the result of executing the service.
	 *
	 * @see edu.psu.iam.cpr.core.api.returns.AddressServiceReturn
	 * 
	 */
	@WebMethod(operationName = "GetAddress")
	@WebResult(name = "AddressServiceReturn")
	AddressServiceReturn GetAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "addressType", mode = Mode.IN) String addressType,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the SetPrimaryAddressByType SOAP web service.  SetPrimaryAddressByType will allow 
	 * authorized registration authorities to specify a primary address within a type for a person in the Central Person Registry.  
	 * The RA must specify the type of the address, document type if necessary and groupId to be primary. Authorization checks are made to determine if the RA agent is 
	 * allowed to call the service and to set the primary for the particular address type.
	 *  
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is requesting the addresses, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier
	 * @param addressType  the type of address to delete
	 * @param documentType the type of document
	 * @param groupId the groupId of the address record within the address type
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * 	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "SetPrimaryAddressByType")
	@WebResult(name = "ServiceReturn")
	ServiceReturn SetPrimaryAddressByType(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "addressType", mode = Mode.IN) String addressType,
			@WebParam(name = "documentType", mode = Mode.IN) String documentType,
			@WebParam(name = "groupId", mode = Mode.IN) Long groupId);

	/**
	 * This function provides the implementation for the UpdateAddress SOAP web service.
	 * UpdateAddress  will allow authorized registration authorities to update an address for a person in the
	 * Central Person Registry.  The RA must specify the type of address along with address.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to update a particular address type.
	 *
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param addressType the type of address to update
	 * @param documentType the type of document
	 * @param groupId the groupId of the phone record within the phone type
	 * @param address1 line 1 of address
	 * @param address2 line 2 of address
	 * @param address3 line 3 of address
	 * @param city the city of the address
	 * @param stateOrProvince the state of the address, if US address; Province for non-US addresses
	 * @param postalCode the postal code of the address. For US address, may include plus4 code.
	 * @param countryCode the three character country code.
	 * @param campusCode the two character campus code.
	 * @param verifyAddressFlag flag to control address validation
	 * 
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdateAddress")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "addressType", mode = Mode.IN) String addressType,
			@WebParam(name = "documentType", mode = Mode.IN) String documentType,
			@WebParam(name = "groupId", mode = Mode.IN) Long groupId,
			@WebParam(name = "address1", mode = Mode.IN) String address1,
			@WebParam(name = "address2", mode = Mode.IN) String address2,
			@WebParam(name = "address3", mode = Mode.IN) String address3,
			@WebParam(name = "city", mode = Mode.IN) String city,
			@WebParam(name = "stateOrProvince", mode = Mode.IN) String stateOrProvince,
			@WebParam(name = "postalCode", mode = Mode.IN) String postalCode,
			@WebParam(name = "countryCode", mode = Mode.IN) String countryCode,
			@WebParam(name = "campusCode", mode = Mode.IN) String campusCode,
			@WebParam(name = "verifyAddressFlag", mode=Mode.IN) String verifyAddressFlag);

	/**
	 * This function provides the implementation for the AddAffiliation SOAP web service.
	 * AddAffiliation  will allow authorized registration authorities to add an affiliation for a person in the
	 * Central Person Registry.  The RA must specify the type of affiliation.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to add a particular affiliation type.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param affiliation the affiliation
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddAffiliation")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddAffiliation(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "affiliation", mode = Mode.IN) String affiliation);

	/**
	 * This function provides the implementation for the ArchiveAffiliation SOAP web service.
	 * ArchiveAffiliation  will allow authorized registration authorities to archive an affiliation for a person in the
	 * Central Person Registry.  The RA must specify the type of affiliation.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to archive the particular 
	 * affiliation type.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param affiliation the affiliation
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveAffiliation")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveAffiliation(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "affiliation", mode = Mode.IN) String affiliation);

	/**
	 * This function provides the implementation for the GetAffiliations SOAP web service.
	 * GetAffiliations  will allow authorized registration authorities to get all affiliation information for a person in the
	 * Central Person Registry.  Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param requestedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * 
	 * @return AffiliationServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.AffiliationServiceReturn
	
	 */
	@WebMethod(operationName = "GetAffiliations")
	@WebResult(name = "AffiliationServiceReturn")
	AffiliationServiceReturn GetAffiliations(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier);

	/**
	 * This function provides the implementation for the GetExternalAffiliations SOAP web service.
	 * GetExternalAffiliations  will allow authorized registration authorities to get all external affiliation information for a person in the
	 * Central Person Registry.  Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param requestedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * 
	 * @return AffiliationServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.AffiliationServiceReturn
	
	 */
	@WebMethod(operationName = "GetExternalAffiliations")
	@WebResult(name = "AffiliationServiceReturn")
	AffiliationServiceReturn GetExternalAffiliations(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier);

	/**
	 * This function provides the implementation for the GetInternalAffiliations SOAP web service.
	 * GetInternalAffiliations  will allow authorized registration authorities to get internal, Penn State,  affiliation information for a person in the
	 * Central Person Registry.  Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param requestedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param returnHistory Y/N flag that will indicate whether to return history or not.
	 * @return AffiliationServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.AffiliationServiceReturn
	
	 */
	@WebMethod(operationName = "GetInternalAffiliations")
	@WebResult(name = "AffiliationServiceReturn")
	AffiliationServiceReturn GetInternalAffiliations(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the UpdateAffiliation SOAP web service.
	 * UpdateAffiliation  will allow authorized registration authorities to update an affiliation for a person in the
	 * Central Person Registry.  The RA must specify the type of affiliation.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to update a particular affiliation type.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param affiliation the affiliation
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdateAffiliation")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateAffiliation(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "affiliation", mode = Mode.IN) String affiliation);

	/**
	 * This function provides the implementation for the SetPrimaryAffiliation SOAP web service.
	 * SetPrimryAffiliation  will allow authorized registration authorities to specify primary affiliation for a person in the
	 * Central Person Registry.  The RA must specify the type of affiliation.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to update a particular affiliation type.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param affiliation the affiliation
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 *
	 */
	@WebMethod(operationName = "SetPrimaryAffiliation")
	@WebResult(name = "ServiceReturn")
	ServiceReturn SetPrimaryAffiliation(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "affiliation", mode = Mode.IN) String affiliation);

	/**
	 * This function provides the implementation for the AddConfidentialityHold SOAP web service.  AddConfidentialityHold will allow 
	 * authorized registration authorities to add a confidentiality hold to a person in the Central Person Registry.  
	 * The RA must specify the confidentiality type and the expiration date.  Authorization checks are made to determine 
	 * if the RA agent is allowed to call the service and to add the hold.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy contains the system principal and/or userid that requested the confidentiality hold
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param confidentialityType contains the type of confidentiality hold.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddConfidentialityHold")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddConfidentialityHold(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "confidentialityType", mode = Mode.IN) String confidentialityType);

	/**
	 * This function provides the implementation for the UpdateConfidentialityHold SOAP web service.  UpdateConfidentialityHold will allow 
	 * authorized registration authorities to update a confidentiality hold for a person in the Central Person Registry.  
	 * The RA must specify the confidentiality type and the expiration date.  Authorization checks are made to determine 
	 * if the RA agent is allowed to call the service and to add the hold.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy contains the system principal and/or userid that requested the confidentiality hold
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param confidentialityType contains the type of confidentiality hold.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdateConfidentialityHold")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateConfidentialityHold(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "confidentialityType", mode = Mode.IN) String confidentialityType);

	/**
	 * This function provides the implementation for the ArchiveConfidentialityHold SOAP web service.  ArchiveConfidentialityHold will allow 
	 * authorized registration authorities to archive a confidentiality hold for a person in the Central Person Registry.  
	 * The RA must specify the confidentiality type to be archived.  Authorization checks are made to determine 
	 * if the RA agent is allowed to call the service and to perform the archive..
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy contains the system principal and/or userid that requested the confidentiality hold to be archived.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param confidentialityType contains the type of confidentiality hold.
	 * @return ConfidentialityServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveConfidentialityHold")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveConfidentialityHold(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "confidentialityType", mode = Mode.IN) String confidentialityType);

	/**
	 * This function provides the implementation for the GEtConfidentialityHold SOAP web service.  GetConfidentialityHold will allow 
	 * authorized registration authorities to obtain a list of confidentiality holds for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to perform the get.  This 
	 * service will only return the active holds that a user has.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy contains the system principal and/or userid that requested the confidentiality hold to be archived.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned or not.
	 * @return ConfidentialityServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ConfidentialityServiceReturn
	 */
	@WebMethod(operationName = "GetConfidentialityHold")
	@WebResult(name = "ConfidentialityServiceReturn")
	ConfidentialityServiceReturn GetConfidentialityHold(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the AddCredential SOAP web service.  AddCredential will allow 
	 * authorized registration authorities to add a new credential for a person in the Central Person Registry.  
	 * The RA must specify the type of the credential, along with its associated data.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service and to add a particular credential type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param credentialType the type of credential that is being added.
	 * @param credentialData the data associated with the credential.
	 * @return will return a ServiceReturn instance.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 * 
	 */
	@WebMethod(operationName = "AddCredential")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddCredential(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "credentialType", mode = Mode.IN) String credentialType,
			@WebParam(name = "credentialData", mode = Mode.IN) String credentialData);

	/**
	 * This function provides the implementation for the UpdateCredential SOAP web service.  UpdateCredential will allow 
	 * authorized registration authorities to update a credential for a person in the Central Person Registry.  
	 * The RA must specify the type of the credential, along with its associated data.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service and to update the particular credential type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param credentialType the type of credential that is being updated.
	 * @param credentialData the data associated with the credential.
	 * @return ServiceReturn
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 * 
	 */
	@WebMethod(operationName = "UpdateCredential")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateCredential(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "credentialType", mode = Mode.IN) String credentialType,
			@WebParam(name = "credentialData", mode = Mode.IN) String credentialData);

	/**
	 * This function provides the implementation for the ArchiveCredential SOAP web service.  ArchiveCredential will allow 
	 * authorized registration authorities to archive a credential for a person in the Central Person Registry.  
	 * The RA must specify the type of the credential, along with its associated data.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service and to archive the particular credential type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param credentialType the type of credential that is being archived.
	 * @return will return a ServiceReturn object upon success.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 * 
	 */
	@WebMethod(operationName = "ArchiveCredential")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveCredential(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "credentialType", mode = Mode.IN) String credentialType);

	/**
	 * This function provides the implementation for the GetCredential SOAP web service.  GetCredential will allow 
	 * authorized registration authorities to retrieve all of the active credentials for a person in the Central Person Registry.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param credentialType contains the type of credential to be returned, if specified.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned or not.
	 * @return ServiceReturn
	 * @see edu.psu.iam.cpr.core.api.returns.CredentialServiceReturn
	 * 
	 */
	@WebMethod(operationName = "GetCredential")
	@WebResult(name = "CredentialServiceReturn")
	CredentialServiceReturn GetCredential(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "credentialType", mode = Mode.IN) String credentialType,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the AddEmailAddress SOAP web service.
	 * AddEmailAddress will allow authorized registration authorities to be able to add a
	 * new e-mail address for a person in the Central Person Registry.
	 * The RA must specify the type of the e-mail address, along with the e-mail address.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service 
	 * and to add a particular e-mail address type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param emailAddressType contains the type of email address that is being added.
	 * @param emailAddress contains the email address
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddEmailAddress")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddEmailAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "emailAddressType", mode = Mode.IN) String emailAddressType,
			@WebParam(name = "emailAddress", mode = Mode.IN) String emailAddress);

	/**
	 * This function provides the implementation for the UpdateEmailAddress SOAP web service.
	 * UpdateEmailAddress will allow authorized registration authorities to be able to update
	 * an e-mail address for a person in the Central Person Registry.  The RA must specify the
	 * type of the e-mail address, along with the e-mail address.  Authorization checks are made
	 * to determine if the RA agent is allowed to call the service and to update a particular 
	 * e-mail address type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param emailAddressType contains the type of email address that is being updated.
	 * @param emailAddress contains the email address
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdateEmailAddress")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateEmailAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "emailAddressType", mode = Mode.IN) String emailAddressType,
			@WebParam(name = "emailAddress", mode = Mode.IN) String emailAddress);

	/**
	 * This function provides the implementation for the GetEmailAddress SOAP web service.  
	 * GetEmailAddress will allow authorized registration authorities to be able to obtain 
	 * an e-mail address for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param requestedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param returnHistory Y/N that indicates whether to return history records or not.
	 * @return EmailAddressServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.EmailAddressServiceReturn
	 * 
	 */
	@WebMethod(operationName = "GetEmailAddress")
	@WebResult(name = "EmailAddressServiceReturn")
	EmailAddressServiceReturn GetEmailAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the ArchiveEmailAddress SOAP web service.
	 * ArchiveEmailAddress will allow authorized registration authorities to be able to archive
	 * an e-mail address for a person in the Central Person Registry.  The RA must specify the
	 * type of the e-mail address.  Authorization checks are made to determine if the RA agent
	 * is allowed to call the service and to archive a particular e-mail address type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param emailAddressType contains the type of email address that is being archived.
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveEmailAddress")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveEmailAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "emailAddressType", mode = Mode.IN) String emailAddressType);

	/** 
	 * 
	 * Implementation for service SearchForPerson
	 * 
	 * @param principalId The principal requesting the service. Mandatory.
	 * @param password The password for principalId. Mandatory.
	 * @param requestedBy The user requesting the service. Mandatory.
	 * @param psuId The Penn State id number of the user to be found. Optional.
	 * @param userId The userId of the user to be found. Optional.
	 * @param ssn The SSN of the user to be found. Optional.
	 * @param firstName The first name of the user to be found. Mandatory.
	 * @param lastName The last name of the user to be found. Mandatory.
	 * @param middleName The middle name of the user to be found. Optional.
	 * @param address1 First line of the street address of the user to be found. Optional.
	 * @param address2 Second line of the street address of the user to be found. Optional.
	 * @param address3 Third line of the street address of the user to be found. Optional.
	 * @param city City of the user to be found. Optional.
	 * @param state State or province of the user to be found. Optional.
	 * @param postalCode Zip/Postcode of the user to be found. Optional.
	 * @param plus4 Extra zipcode information. Optional.
	 * @param country Country for the street address. Optional. If blank, "USA" is assumed.
	 * @param dateOfBirth full birth date in format mm/dd/yyyy or partial birth date in format mm/dd
	 * @param gender Gender for the user to be found. Optional.
	 * @param rankCutOff The minimum ranking to be considered a positive match. If no value provided, then cut off score will be determined by default cutoff rankings from the standard and international criteria matrices
	 *  
	 * @return FindPersonServiceReturn
	 * 
	 */

	@WebMethod(operationName = "SearchForPerson")
	@WebResult(name = "FindPersonServiceReturn")
	FindPersonServiceReturn SearchForPerson(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "psuId", mode = Mode.IN) String psuId,
			@WebParam(name = "userId", mode = Mode.IN) String userId,
			@WebParam(name = "ssn", mode = Mode.IN) String ssn,
			@WebParam(name = "firstName", mode = Mode.IN) String firstName,
			@WebParam(name = "lastName", mode = Mode.IN) String lastName,
			@WebParam(name = "middleName", mode = Mode.IN) String middleName,
			@WebParam(name = "address1", mode = Mode.IN) String address1,
			@WebParam(name = "address2", mode = Mode.IN) String address2,
			@WebParam(name = "address3", mode = Mode.IN) String address3,
			@WebParam(name = "city", mode = Mode.IN) String city,
			@WebParam(name = "state", mode = Mode.IN) String state,
			@WebParam(name = "postalCode", mode = Mode.IN) String postalCode,
			@WebParam(name = "plus4", mode = Mode.IN) String plus4,
			@WebParam(name = "country", mode = Mode.IN) String country,
			@WebParam(name = "dateOfBirth", mode = Mode.IN) String dateOfBirth,
			@WebParam(name = "gender", mode = Mode.IN) String gender,
			@WebParam(name = "rankCutOff", mode = Mode.IN) String rankCutOff);

	/**
	 * This function implements the GetPSUIAP SOAP Web service.
	 * GetPSUIAP  will allow authorized registration authorities to get internal, Penn State,
	 * IAP information for a person in the Central Person Registry.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId the service principal's identifier. 
	 * @param password the service principal's password.
	 * @param requestedBy the person (userid) who requested the service, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR.
	 * @param identifier the identifier used to identify the user within the CPR.
	 * @param userId the userid
	 * @param returnHistory Y/N flag that indicates whether to return history or not.
	 * @return IAPServiceReturn  object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.IAPServiceReturn
	 */
	@WebMethod(operationName = "GetPSUIAP")
	@WebResult(name = "IAPServiceReturn")
	IAPServiceReturn GetPSUIAP(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userId", mode = Mode.IN) String userId,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function implements the GetExternalIAP SOAP Web service.
	 * GetExternalIAP  will allow authorized registration authorities to get external IAP information
	 * for a person in the Central Person Registry.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service.
	 * @param principalId the service principal's identifier. 
	 * @param password the service principal's password.
	 * @param requestedBy the person (userid) who requested the service, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR.
	 * @param identifier the identifier used to identify the user within the CPR.
	 * @param userId the userid
	 * @param federationName the Federation Name
	 * 
	 * @return IAPServiceReturn  object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.IAPServiceReturn
	 */
	@WebMethod(operationName = "GetExternalIAP")
	@WebResult(name = "IAPServiceReturn")
	IAPServiceReturn GetExternalIAP(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userId", mode = Mode.IN) String userId,
			@WebParam(name = "federationName", mode = Mode.IN) String federationName);

	/**
	 * This function provides the implementation for the AddIdCard SOAP web service.  AddIdCard will allow 
	 * authorized registration authorities to add Id Card information for a person in the Central Person Registry  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param idCardType the type of id card to return
	 * @param idCardNumber the id card number associated 
	 * @param idSerialNumber the serial number, if any, associated with the card
	 * @param photo the photo, if any, on the id card
	 * @param photoDateTaken date photo was taken
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddIdCard")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddIdCard(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "IdCardType", mode = Mode.IN) String idCardType,
			@WebParam(name = "idCardNumber", mode = Mode.IN) String idCardNumber,
			@WebParam(name = "idSerialNumber", mode = Mode.IN) String idSerialNumber,
			@WebParam(name = "photo", mode = Mode.IN) byte[] photo,
			@WebParam(name = "photoDateTaken", mode = Mode.IN) String photoDateTaken);

	/**
	 * This function provides the implementation for the ArchiveIdCard SOAP web service.  ArchiveIdCard will allow 
	 * authorized registration authorities to archive an active Id Card information for a person in the Central Person Registry  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param idCardType the type of id card to return
	 * @return will return a ServiceReturn object upon successful completion of the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveIdCard")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveIdCard(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "idCardType", mode = Mode.IN) String idCardType);

	/**
	 * This function provides the implementation for the GetIdCard SOAP web service.  GetIdCard will allow 
	 * authorized registration authorities to obtain id card information information for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param idCardType contains the id card type to be used for a query, if specified.
	 * @param returnHistory Y/N flag that indicates whether history is returned or not.
	 * @return IdCardServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.IdCardServiceReturn
	 */
	@WebMethod(operationName = "GetIdCard")
	@WebResult(name = "IdCardServiceReturn")
	IdCardServiceReturn GetIdCard(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "idCardType", mode = Mode.IN) String idCardType,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the GetIdCardNumber SOAP web service.  GetIdCardNumber will allow 
	 * authorized registration authorities to obtain Id Card number information for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 *
	 * @return IdCardNumberServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.IdCardServiceReturn
	 */
	@WebMethod(operationName = "GetIdCardNumber")
	@WebResult(name = "IdCardNumberServiceReturn")
	PersonIdCardNumberServiceReturn GetIdCardNumber(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier);

	/**
	 * This function provides the implementation for the UpdateIdCard SOAP web service.  UpdateIdCard will allow 
	 * authorized registration authorities to update Id Card information for a person in the Central Person Registry  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param idCardType the type of id card to return
	 * @param idCardNumber the id card number associated 
	 * @param idSerialNumber the serial number, if any, associated with the card
	 * @param photo the photo, if any, on the id card
	 * @param photoDateTaken date photo was taken
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdateIdCard")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateIdCard(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "idCardType", mode = Mode.IN) String idCardType,
			@WebParam(name = "idCardNumber", mode = Mode.IN) String idCardNumber,
			@WebParam(name = "idSerialNumber", mode = Mode.IN) String idSerialNumber,
			@WebParam(name = "photo", mode = Mode.IN) byte[] photo,
			@WebParam(name = "photoDateTaken", mode = Mode.IN) String photoDateTaken);

	/**
	 * This function provides the implementation for the AddIdCardPrintEvent SOAP web service.  AddIdCardPrintEvent will allow 
	 * authorized registration authorities to add a Id Card Print Log entry information for an id card in the Central Person Registry  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR (must be ID_CARD). 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param eventUserId the user that printed the id card
	 * @param eventIpAddress the ip address of the workstation where the id card was printed
	 * @param eventWorkstation the name of the workstation where the id card was printed.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddIdCardPrintEvent")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddIdCardPrintEvent(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "eventUserId", mode = Mode.IN) String eventUserId,
			@WebParam(name = "eventIpAddress", mode = Mode.IN) String eventIpAddress,
			@WebParam(name = "eventWorkstation", mode = Mode.IN) String eventWorkstation);

	/**
	 * This function provides the implementation for the GetIdCardPrintEvent SOAP web service.  GetIdCardPrintEvent will allow 
	 * authorized registration authorities to get Id Card Print Log entries information for an id card in the Central Person Registry  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR (must be ID_CARD). 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @return GetIdCardPrintEventServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.IdCardPrintEventServiceReturn
	 */
	@WebMethod(operationName = "GetIdCardPrintEvent")
	@WebResult(name = "IdCardPrintEventServiceReturn")
	IdCardPrintEventServiceReturn GetIdCardPrintEvent(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier);

	/**
	 * This function provides the implementation for the AddName SOAP web service.  AddName will allow 
	 * authorized registration authorities to add a new name for a person in the Central Person Registry.  
	 * The RA must specify the type of the name, along with the full name.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service and to add a particular name type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param nameType the type of name that is being added.
	 * @param documentType the type of documented name that is being added (optional).
	 * @param firstName the first name that is being added.
	 * @param middleNames the middle name(s) that are being added.
	 * @param lastName the last name that is being added.
	 * @param suffix optionally the suffix that is being added.
	 * @param nickname optionally the nickname that is being added.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 * 
	 */
	@WebMethod(operationName = "AddName")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddName(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "nameType", mode = Mode.IN) String nameType,
			@WebParam(name = "documentType", mode = Mode.IN) String documentType,
			@WebParam(name = "firstName", mode = Mode.IN) String firstName,
			@WebParam(name = "middleNames", mode = Mode.IN) String middleNames,
			@WebParam(name = "lastName", mode = Mode.IN) String lastName,
			@WebParam(name = "suffix", mode = Mode.IN) String suffix,
			@WebParam(name = "nickname", mode= Mode.IN) String nickname);

	/**
	 * This function provides the implementation for the UpdateName SOAP web service.  UpdateName will allow 
	 * authorized registration authorities to update a name for a person in the Central Person Registry.  
	 * The RA must specify the type of the name, along with the full name.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service and to update the particular name type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param nameType the type of name that is being updated.
	 * @param documentType the type of documented name that is being updated (optional).
	 * @param firstName the first name that is being added.
	 * @param middleNames the middle name(s) that are being added.
	 * @param lastName the last name that is being added.
	 * @param suffix optionally the suffix that is being added.
	 * @param nickname optionally the nickname that is being added.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 * 
	 */
	@WebMethod(operationName = "UpdateName")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateName(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "nameType", mode = Mode.IN) String nameType,
			@WebParam(name = "documentType", mode = Mode.IN) String documentType,
			@WebParam(name = "firstName", mode = Mode.IN) String firstName,
			@WebParam(name = "middleNames", mode = Mode.IN) String middleNames,
			@WebParam(name = "lastName", mode = Mode.IN) String lastName,
			@WebParam(name = "suffix", mode = Mode.IN) String suffix,
			@WebParam(name = "nickname", mode = Mode.IN) String nickname);

	/**
	 * This function provides the implementation for the ArchiveName SOAP web service.  ArchiveName will allow 
	 * authorized registration authorities to archive a name for a person in the Central Person Registry.  
	 * The RA must specify the type of the name to be archived.  Authorization checks are made 
	 * to determine if the RA agent is allowed to call the service and to archive the particular name type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param nameType the type of name that is being archived.
	 * @param documentType the type of documented name that is being archived (optional).
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveName")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveName(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "nameType", mode = Mode.IN) String nameType,
			@WebParam(name = "documentType", mode = Mode.IN) String documentType);

	/**
	 * This function provides the implementation for the GetName SOAP web service.  GetName will allow 
	 * authorized registration authorities to obtain name information for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param nameType contains the type of name to be retrieved, if specified.
	 * @param returnHistory flag that indicates whether history must be returned or not.
	 * @return NamesServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.NamesServiceReturn
	 */
	@WebMethod(operationName = "GetName")
	@WebResult(name = "NamesServiceReturn")
	NamesServiceReturn GetName(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "nameType", mode = Mode.IN) String nameType,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the AddPersonLinkage SOAP web service.  
	 * This service will enable an authorized registration authority to be able to add a linkage between two 
	 * persons in the person registry.  The calling parameters to the service will specify the linked users 
	 * along with the linkage type.  If the user already has a linkage of the type specified, it will be expired prior 
	 * to the new linkage being added.  The service will either return an exception (with the reason the 
	 * add did not happen) or success. 
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param updatedBy contains the system principal and/or userid that requested the add.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param linkageType contains the type of linkage that is being added.
	 * @param linkedIdentifierType contains the type of identifier used to find the person being linked.
	 * @param linkedIdentifier contains the value of the identifier for the person being linked.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddPersonLinkage")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddPersonLinkage(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "linkageType", mode = Mode.IN) String linkageType,
			@WebParam(name = "linkedIdentifierType", mode = Mode.IN) String linkedIdentifierType,
			@WebParam(name = "linkedIdentifier", mode = Mode.IN) String linkedIdentifier);

	/**
	 * This function provides the implementation for the UpdatePersonLinkage SOAP web service.  
	 * This service will enable an authorized registration authority to be able to update a linkage between two 
	 * persons in the person registry.  The calling parameters to the service will specify the linked users 
	 * along with the linkage type.  If the user already has a linkage of the type specified, it will be expired prior 
	 * to the new linkage being added.  The service will either return an exception (with the reason the 
	 * add did not happen) or success. 
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param updatedBy contains the system principal and/or userid that requested the add.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param linkageType contains the type of linkage that is being added.
	 * @param linkedIdentifierType contains the type of identifier used to find the person being linked.
	 * @param linkedIdentifier contains the value of the identifier for the person being linked.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdatePersonLinkage")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdatePersonLinkage(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "linkageType", mode = Mode.IN) String linkageType,
			@WebParam(name = "linkedIdentifierType", mode = Mode.IN) String linkedIdentifierType,
			@WebParam(name = "linkedIdentifier", mode = Mode.IN) String linkedIdentifier);

	/**
	 * This function provides the implementation for the ArchivePersonLinkage SOAP web service.  
	 * This service will enable an authorized registration authority to be able to archive a linkage between two 
	 * persons in the person registry.
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param updatedBy contains the system principal and/or userid that requested the archive.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param linkageType contains the type of linkage that is being archived.
	 * @param linkedIdentifierType contains the type of identifier used to find the person being linked.
	 * @param linkedIdentifier contains the value of the identifier for the person being linked.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchivePersonLinkage")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchivePersonLinkage(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "linkageType", mode = Mode.IN) String linkageType,
			@WebParam(name = "linkedIdentifierType", mode = Mode.IN) String linkedIdentifierType,
			@WebParam(name = "linkedIdentifier", mode = Mode.IN) String linkedIdentifier);

	/**
	 * This function provides the implementation for the GetPersonLinkage SOAP web service.  
	 * This service will enable an authorized registration authority to be able to obtain linkage
	 * information about a person in the registry.  This service will only return the active
	 * person linkages.
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param requestedBy contains the system principal and/or userid that requested the get.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned or not.
	 * @return PersonLinkageServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.PersonLinkageServiceReturn
	 */
	@WebMethod(operationName = "GetPersonLinkage")
	@WebResult(name = "PersonLinkageServiceReturn")
	PersonLinkageServiceReturn GetPersonLinkage(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the AddPerson SOAP web service.  AddPerson will allow 
	 * authorized registration authorities to add a new person to the Central Person Registry.  
	 * The RA must specify some required information about the new person and whether they want to
	 * generate a PSU ID and/or userid.  Authorization checks are made to determine if the RA agent is 
	 * allowed to call the service and to add the person.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param assignPsuIdFlag a y/n flag to indiciate whether a new PSU ID is to be created for the person.
	 * @param assignUseridFlag a y/n flag to indicate whether a new userid is to be created for the person.
	 * @param gender a flag to indicate the person's gender.
	 * @param dob the person's date of birth, it can either be a full DOB or a partial one (mm/dd).
	 * @param nameType the type of name that is being added.
	 * @param nameDocumentType the document type associated with the name.
	 * @param firstName the first name that is being added.
	 * @param middleNames the middle name(s) that are being added.
	 * @param lastName the last name that is being added.
	 * @param suffix optionally the suffix that is being added.
	 * @param nickname optionally the nickname that is being added.
	 * @param addressType the type of address that is being added.
	 * @param addressDocumentType the document type of the address that is being added.
	 * @param address1 line number one of the address.
	 * @param address2 line number two of the address (if it exists).
	 * @param address3 line number three of the address (if it exists).
	 * @param city the city of the address.
	 * @param stateOrProvince the state (if country is USA), or province (otherwise).
	 * @param postalCode the postal code.
	 * @param countryCode the ISO country code abbreviation.
	 * @param campusCode the Penn State campus code for University addresses only.
	 * @param phoneType the type of phone number being added.
	 * @param phoneNumber the phone number.
	 * @param extension the extension for the phone number if applicable.
	 * @param internationalNumber a y/n flag to indicate whether the phone number is an international number.
	 * @param emailType the type of email address being added.
	 * @param emailAddress the email address.
	 * @param affiliation the affiliation to be added.
	 * @param ssn contains the user's SSN.
	 * @return PersonServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.PersonServiceReturn
	 * 
	 */
	@WebMethod(operationName = "AddPerson")
	@WebResult(name = "PersonServiceReturn")
	PersonServiceReturn AddPerson(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "assignPsuIdFlag") String assignPsuIdFlag,
			@WebParam(name = "assignUseridFlag") String assignUseridFlag,
			@WebParam(name = "gender", mode = Mode.IN) String gender,
			@WebParam(name = "dob", mode = Mode.IN) String dob,
			@WebParam(name = "nameType", mode = Mode.IN) String nameType,
			@WebParam(name = "nameDocumentType", mode = Mode.IN) String nameDocumentType,
			@WebParam(name = "firstName", mode = Mode.IN) String firstName,
			@WebParam(name = "middleNames", mode = Mode.IN) String middleNames,
			@WebParam(name = "lastName", mode = Mode.IN) String lastName,
			@WebParam(name = "suffix", mode = Mode.IN) String suffix,
			@WebParam(name = "nickname", mode = Mode.IN) String nickname, 
			@WebParam(name = "addressType", mode = Mode.IN) String addressType,
			@WebParam(name = "addressDocumentType", mode = Mode.IN) String addressDocumentType,
			@WebParam(name = "address1", mode = Mode.IN) String address1,
			@WebParam(name = "address2", mode = Mode.IN) String address2,
			@WebParam(name = "address3", mode = Mode.IN) String address3,
			@WebParam(name = "city", mode = Mode.IN) String city,
			@WebParam(name = "stateOrProvince", mode = Mode.IN) String stateOrProvince,
			@WebParam(name = "postalCode", mode = Mode.IN) String postalCode,
			@WebParam(name = "countryCode", mode = Mode.IN) String countryCode,
			@WebParam(name = "campusCode", mode = Mode.IN) String campusCode,
			@WebParam(name = "verifyAddressFlag", mode = Mode.IN) String verifyAddressFlag,
			@WebParam(name = "phoneType", mode = Mode.IN) String phoneType,
			@WebParam(name = "phoneNumber", mode = Mode.IN) String phoneNumber,
			@WebParam(name = "extension", mode = Mode.IN) String extension,
			@WebParam(name = "internationalNumber", mode = Mode.IN) String internationalNumber,
			@WebParam(name = "emailType", mode = Mode.IN) String emailType,
			@WebParam(name = "emailAddress", mode = Mode.IN) String emailAddress,
			@WebParam(name = "affilation", mode = Mode.IN) String affiliation,
			@WebParam(name = "ssn", mode = Mode.IN) String ssn);

	/**
	 * This function provides the implementation for the UnarchivePerson SOAP web service.  UnarchivePerson will allow 
	 * authorized registration authorities to un-archive a person's information in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to un-archive the person.
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param updatedBy contains the system principal and/or userid that requested the un-archive.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UnarchivePerson")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UnarchivePerson(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier);

	/**
	 * This function provides the implementation for the ArchivePerson SOAP web service.  ArchivePerson will allow 
	 * authorized registration authorities to archive a person's information in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to archive the person.
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param updatedBy contains the system principal and/or userid that requested the archive.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchivePerson")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchivePerson(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier);

	/**
	 * This function provides the implementation for the GetPerson SOAP web service.  GetPerson will allow 
	 * authorized registration authorities to obtain a person's information from the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param requestedBy contains the system principal and/or userid that requested the information.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param returnHistory Y/N flag that indicates whether to return a complete history or not.
	 * @return PersonServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.PersonServiceReturn
	 */
	@WebMethod(operationName = "GetPerson")
	@WebResult(name = "PersonServiceReturn")
	PersonServiceReturn GetPerson(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the UpdatePerson SOAP web service.  UpdatePerson will allow 
	 * authorized registration authorities to update a person's information in the Central Person Registry.  
	 * The RA must specify some required information about the person and whether they want to
	 * generate a PSU ID and/or userid.  Authorization checks are made to determine if the RA agent is 
	 * allowed to call the service and to add the person.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param assignPsuIdFlag a y/n flag to indiciate whether a new PSU ID is to be created for the person.
	 * @param assignUseridFlag a y/n flag to indicate whether a new userid is to be created for the person.
	 * @param gender a flag to indicate the person's gender.
	 * @param dob the person's date of birth, it can either be a full DOB or a partial one (mm/dd).
	 * @param nameType the type of name that is being added.
	 * @param nameDocumentType the document type associated with the name.
	 * @param firstName the first name that is being added.
	 * @param middleNames the middle name(s) that are being added.
	 * @param lastName the last name that is being added.
	 * @param suffix optionally the suffix that is being added.
	 * @param nickname optionally the nickname that is being added.
	 * @param addressType the type of address that is being added.
	 * @param addressDocumentType the document type of the address that is being added.
	 * @param addressGroupId the group id associated addressType and addressDocumentType being updated.
	 * @param address1 line number one of the address.
	 * @param address2 line number two of the address (if it exists).
	 * @param address3 line number three of the address (if it exists).
	 * @param city the city of the address.
	 * @param stateOrProvince the state (if country is USA), or province (otherwise).
	 * @param postalCode the postal code.
	 * @param countryCode the ISO country code abbreviation.
	 * @param campusCode the Penn State campus code for University addresses only.
	 * @param phoneType the type of phone number being added.
	 * @param phoneGroupId the group id associated with the record being updated.
	 * @param phoneNumber the phone number.
	 * @param extension the extension for the phone number if applicable.
	 * @param internationalNumber a y/n flag to indicate whether the phone number is an international number.
	 * @param emailType the type of email address being added.
	 * @param emailAddress the email address.
	 * @param affiliation the affiliation to be added.
	 * @param ssn the user's social security number.
	 * @return PersonServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.PersonServiceReturn
	 * 
	 */
	@WebMethod(operationName = "UpdatePerson")
	@WebResult(name = "PersonServiceReturn")
	PersonServiceReturn UpdatePerson(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "assignPsuIdFlag") String assignPsuIdFlag,
			@WebParam(name = "assignUseridFlag") String assignUseridFlag,
			@WebParam(name = "gender", mode = Mode.IN) String gender,
			@WebParam(name = "dob", mode = Mode.IN) String dob,
			@WebParam(name = "nameType", mode = Mode.IN) String nameType,
			@WebParam(name = "nameDocumentType", mode = Mode.IN) String nameDocumentType,
			@WebParam(name = "firstName", mode = Mode.IN) String firstName,
			@WebParam(name = "middleNames", mode = Mode.IN) String middleNames,
			@WebParam(name = "lastName", mode = Mode.IN) String lastName,
			@WebParam(name = "suffix", mode = Mode.IN) String suffix,
			@WebParam(name = "nickname", mode = Mode.IN) String nickname,  
			@WebParam(name = "addressType", mode = Mode.IN) String addressType,
			@WebParam(name = "addressDocumentType", mode = Mode.IN) String addressDocumentType,
			@WebParam(name = "addressGroupId", mode = Mode.IN) Long addressGroupId,
			@WebParam(name = "address1", mode = Mode.IN) String address1,
			@WebParam(name = "address2", mode = Mode.IN) String address2,
			@WebParam(name = "address3", mode = Mode.IN) String address3,
			@WebParam(name = "city", mode = Mode.IN) String city,
			@WebParam(name = "stateOrProvince", mode = Mode.IN) String stateOrProvince,
			@WebParam(name = "postalCode", mode = Mode.IN) String postalCode,
			@WebParam(name = "countryCode", mode = Mode.IN) String countryCode,
			@WebParam(name = "campusCode", mode = Mode.IN) String campusCode,
			@WebParam(name = "verifyAddressFlag", mode = Mode.IN) String verifyAddressFlag,
			@WebParam(name = "phoneType", mode = Mode.IN) String phoneType,
			@WebParam(name = "phoneGroupId", mode = Mode.IN) Long phoneGroupId,
			@WebParam(name = "phoneNumber", mode = Mode.IN) String phoneNumber,
			@WebParam(name = "extension", mode = Mode.IN) String extension,
			@WebParam(name = "internationalNumber", mode = Mode.IN) String internationalNumber,
			@WebParam(name = "emailType", mode = Mode.IN) String emailType,
			@WebParam(name = "emailAddress", mode = Mode.IN) String emailAddress,
			@WebParam(name = "affiliation", mode = Mode.IN) String affiliation,
			@WebParam(name = "ssn", mode = Mode.IN) String ssn);

	/**
	 * This function provides the implementation for the AddPhone SOAP web service.
	 * AddPhone  will allow authorized registration authorities to add phone information for a person in the
	 * Central Person Registry.  The RA must specify the type of phone along with the phone number.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to add a particular phone type.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is adding the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param phoneType the type of phone number that is being added
	 * @param phoneNumber the phone number to added.
	 * @param extension the extension, if available, that is being added.
	 * @param internationalNumber a flag indicating if the phone number is an international number
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddPhone")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddPhone(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "phoneType", mode = Mode.IN) String phoneType,
			@WebParam(name = "phoneNumber", mode = Mode.IN) String phoneNumber,
			@WebParam(name = "extension", mode = Mode.IN) String extension,
			@WebParam(name = "internationalNumber", mode = Mode.IN) String internationalNumber);

	/**
	 * This function provides the implementation for the ArchivePhone SOAP web service.
	 * ArchivePhone  will allow authorized registration authorities to archive phone information for a person in the
	 * Central Person Registry.  The RA must specify the type of phone and groupId. Authorization checks are made to determine if the RA agent 
	 * is allowed to call the service and to archive a particular phone type.
	 *
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is archiving the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param phoneType the type of phone number that is being archived.
	 * @param groupId the groupId of the phone record within the phone type
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchivePhone")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchivePhone(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "phoneType", mode = Mode.IN) String phoneType,
			@WebParam(name = "groupId", mode = Mode.IN) Long groupId);

	/**
	 * This function provides the implementation for the GetPhone SOAP web service.
	 * GetPhone  will allow authorized registration authorities to get phone information for a person in the
	 * Central Person Registry. Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param requestedBy the person (userid)  who is requesting the phone information, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param phoneType if specified will perform a query for a specific phone type.
	 * @param returnHistory Y/N that indicates whether history is to be returned.
	 * @return PhoneServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.PhoneServiceReturn
	 * 
	 */
	@WebMethod(operationName = "GetPhone")
	@WebResult(name = "PhoneServiceReturn")
	PhoneServiceReturn GetPhone(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "phoneType", mode = Mode.IN) String phoneType,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the SetPrimaryPhoneByType SOAP web service.
	 * SetPrimaryPhoneByType  will allow authorized registration authorities to set a primary phone number with a phone type for a person in the
	 * Central Person Registry.  The RA must specify the type of phone and groupId. Authorization checks are made to determine if the RA agent 
	 * is allowed to call the service and to archive a particular phone type.
	 *
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is setting the primary address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param phoneType the type of phone number that is being set to primary.
	 * @param groupId the groupId of the phone record within the phone type
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "SetPrimaryPhoneByType")
	@WebResult(name = "ServiceReturn")
	ServiceReturn SetPrimaryPhoneByType(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "phoneType", mode = Mode.IN) String phoneType,
			@WebParam(name = "groupId", mode = Mode.IN) Long groupId);

	/**
	 * This function provides the implementation for the UpdatePhone SOAP web service.
	 * UpdatePhone  will allow authorized registration authorities to update phone information for a person in the
	 * Central Person Registry.  The RA must specify the type of phone and groupId along with the phone number.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service and to update a particular phone type.
	 * 
	 * @param principalId service principal identifier.
	 * @param password  password for the service principal identifier.
	 * @param updatedBy the person (userid) who is updating the address, this person will be an RA agent.
	 * @param identifierType the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value of the identifier.
	 * @param phoneType the type of phone number that is being updated.
	 * @param groupId the groupId of the phone record within the phone type
	 * @param phoneNumber the phone number of the update.
	 * @param extension the extension, if available, of the update.
	 * @param internationalNumber a flag indicating if the phone number is an international number
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdatePhone")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdatePhone(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "phoneType", mode = Mode.IN) String phoneType,
			@WebParam(name = "groupId", mode = Mode.IN) Long groupId,
			@WebParam(name = "phoneNumber", mode = Mode.IN) String phoneNumber,
			@WebParam(name = "extension", mode = Mode.IN) String extension,
			@WebParam(name = "internationalNumber", mode = Mode.IN) String internationalNumber);

	/**
	 * This function provides the implementation for the AddPhoto SOAP web service.  
	 * This service will enable an authorized registration authority to be able to add a photograph  
	 * of a user to the person registry.  The calling parameters to the service will specify the photograph
	 * along with the date the photo was taken.  If the user already has a photo, it will be expired prior 
	 * to the new photo being added.  The service will either return an exception (with the reason the 
	 * add did not happen) or success. 
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param updatedBy contains the system principal and/or userid that requested the add.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param photo contains the bytes of the jpeg photo.
	 * @param photoDateTaken contains the date the photo was taken.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddPhoto")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddPhoto(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "photo", mode = Mode.IN) byte[] photo,
			@WebParam(name = "photoDateTaken", mode = Mode.IN) String photoDateTaken);

	/**
	 * This function provides the implementation for the GetPhoto SOAP web service.  
	 * This service will enable an authorized registration authority to be able to obtain a photograph  
	 * of a user to the person registry.  The service will either return an exception (with the reason the 
	 * add did not happen) or success. 
	 * 
	 * @param principalId contains the service principal used to authenticate the service.
	 * @param password contains the password for the service principal.
	 * @param requestedBy contains the system principal and/or userid that requested the add.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @return PhotoServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.PhotoServiceReturn
	 */
	@WebMethod(operationName = "GetPhoto")
	@WebResult(name = "PhotoServiceReturn")
	PhotoServiceReturn GetPhoto(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier);

	/**
	 * This function provides the implementation for the ProcessRules SOAP web service.  ProcessRules will allow 
	 * the Central Person Registry to interface with a Drools Rules Engine.  The service will pass in the
	 * ruleset to be processed, along with the known facts and a new fact.  The service will process the
	 * rules and return the result to the caller.
	 * 
	 * @param rulesetName the name of the ruleset to be processed.
	 * @param knownFacts an array containing the known facts.
	 * @param newFact the new fact to be asserted.
	 * @return RulesServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.RulesServiceReturn
	 * 
	 */
	@WebMethod(operationName = "ProcessRules")
	@WebResult(name = "RulesServiceReturn")
	RulesServiceReturn ProcessRules(
			@WebParam(name = "rulesetName", mode = Mode.IN) String rulesetName,
			@WebParam(name = "knownFacts", mode = Mode.IN) String[] knownFacts,
			@WebParam(name = "newFact", mode = Mode.IN) String newFact);

	/**
	 * This function provides the implementation for the BlockUser SOAP web service.  BlockUser will allow authorized
	 * security and helpdesk personnel to block a user's access from wireless as the result of a security action.  Authorization
	 * checks are made to determine if the the caller is allowed to the call the service and add the block.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy contains the system principal and/or userid that requested the block.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param userid contains the credential that is being blocked.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "BlockUser")
	@WebResult(name = "ServiceReturn")
	ServiceReturn BlockUser(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userid", mode = Mode.IN) String userid);

	/**
	 * This function provides the implementation for the UnblockUser SOAP web service.  UnblockUser will allow authorized
	 * security and helpdesk personnel to unblock a user's access from wireless as the result of a security action.  Authorization
	 * checks are made to determine if the the caller is allowed to the call the service and remove the block.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy contains the system principal and/or userid that requested the unblock.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param userid contains the credential that is being unblocked.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UnblockUser")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UnblockUser(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userid", mode = Mode.IN) String userid);

	/**
	 * This function provides the implementation for the DisableUser SOAP web service.  DisableUser will allow authorized
	 * security and helpdesk personnel to lock a user's access account as the result of a security action.  Authorization
	 * checks are made to determine if the the caller is allowed to the call the service and lock the account.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy contains the system principal and/or userid that requested the lock.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param userid contains the credential that is being disabled.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "DisableUser")
	@WebResult(name = "ServiceReturn")
	ServiceReturn DisableUser(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userid", mode = Mode.IN) String userid);

	/**
	 * This function provides the implementation for the EnableUser SOAP web service.  EnableUser will allow authorized
	 * security and helpdesk personnel to unlock a user's access account as the result of a security action.  Authorization
	 * checks are made to determine if the the caller is allowed to the call the service and unlock the account.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy contains the system principal and/or userid that requested the enable.
	 * @param identifierType contains the type of identifier used to find the person.
	 * @param identifier contains the value of the identifier.
	 * @param userid contains the credential that is being unlocked.
	 * @return ServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "EnableUser")
	@WebResult(name = "ServiceReturn")
	ServiceReturn EnableUser(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userid", mode = Mode.IN) String userid);
	
	/**
	 * This function implements the TransformAddress SOAP web service.  TransformAddress 
	 * places the incoming address into a standardized format according to postal rules and makes an 
	 * assessment of the address deliverability. TransformAddress is currently only valid for
	 * addresses from the United State and Canada
	 * 
	 * @param principalId service principal identifier (will be a Kerberos principal).
	 * @param password password for the service principal specified as the first argument.
	 * @param requestedBy the person (userid) who is making the request
	 * @param address1 first line of the street address
	 * @param address2 second line of the street address
	 * @param address3 third line of the street address
	 * @param city the city of the address
	 * @param stateOrProvince for US addresses, a State; for Canadian addresses, a Province, 
	 * @param postalCode the postal code of the address. For US address, may include plus4 code.
	 * @param countryCode three character country code as defined in ISO 3166
	 * @return TransformServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.TransformServiceReturn
	 * 
	 */
	@WebMethod(operationName = "TransformAddress")
	@WebResult(name = "TransformServiceReturn")
	TransformServiceReturn TransformAddress(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "address1", mode = Mode.IN) String address1,
			@WebParam(name = "address2", mode = Mode.IN) String address2,
			@WebParam(name = "address3", mode = Mode.IN) String address3,
			@WebParam(name = "city", mode = Mode.IN) String city,
			@WebParam(name = "stateOrProvince", mode = Mode.IN) String stateOrProvince,
			@WebParam(name = "postalCode", mode = Mode.IN) String postalCode,
			@WebParam(name = "countryCode", mode = Mode.IN) String countryCode);

	/**
	 * This function implements the GetMatchCode SOAP web service.  Given a matching data type and data,
	 * service GetMatchCode will connect to the data quality server and generate a match code.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service. 
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (user id) who is making the request
	 * @param matchDataType first line of the street address
	 * @param dataValue second line of the street address
	 * 
	 * @return MatchCodeServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.MatchCodeServiceReturn
	 */

	@WebMethod(operationName = "GetMatchCode")
	@WebResult(name = "MatchCodeServiceReturn")
	MatchCodeServiceReturn GetMatchCode(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "matchDataType", mode = Mode.IN) String matchDataType,
			@WebParam(name = "dataValue", mode = Mode.IN) String dataValue);


	/**
	 * This function provides the implementation for the AddUserComment SOAP web service.
	 * AddUserComment will allow authorized Security Agent to be able to add a
	 * comment for a userid in the Central Person Registry.
	 * The Security Agent must specify the type of the comment, along with the comment and a userId that is
	 * associated with the comment.
	 * Authorization checks are made to determine if the security agent is allowed to call the service 
	 * and to add a particular comment type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param userId contains the userId associated with the comment.
	 * @param userCommentType contains the type of comment that is being added.
	 * @param comment contains the comment
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddUserComment")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddUserComment(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userId", mode = Mode.IN) String userId,
			@WebParam(name = "userCommentType", mode = Mode.IN) String userCommentType,
			@WebParam(name = "comment", mode = Mode.IN) String comment);

	/**
	 * This function provides the implementation for the UpdateUserComment SOAP web service.
	 * UpdateUserComment will allow authorized Security Agent to be able to update a
	 * comment for a userid in the Central Person Registry.
	 * The Security Agent must specify the type of the comment, along with the comment and a userId that is
	 * associated with the comment.
	 * Authorization checks are made to determine if the Security Agent is allowed to call the service 
	 * and to update a particular comment type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param userId contains the userId associated with the comment.
	 * @param userCommentType contains the type of comment that is being added.
	 * @param comment contains the comment
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdateUserComment")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdateUserComment(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userId", mode = Mode.IN) String userId,
			@WebParam(name = "userCommentType", mode = Mode.IN) String userCommentType,
			@WebParam(name = "comment", mode = Mode.IN) String comment);

	/**
	 * This function provides the implementation for the GetUserComments SOAP web service.  
	 * GetUserComments will allow authorized registration authorities to be able to obtain 
	 * the comments for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param requestedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param userId contains the userId associated with the comment.
	 * @param userCommentType if specified will be the comment type that will be searched for.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned or not.
	 * @return UserCommentServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.UserCommentServiceReturn
	 * 
	 */
	@WebMethod(operationName = "GetUserComments")
	@WebResult(name = "UserCommentServiceReturn")
	UserCommentServiceReturn GetUserComments(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userId", mode = Mode.IN) String userId,
			@WebParam(name = "userCommentType", mode = Mode.IN) String userCommentType,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the ArchiveUserComment SOAP web service.
	 * ArchiveUserComment will allow authorized Security Agent to be able to archive a
	 * comment for a userid in the Central Person Registry.
	 * The Security Agent must specify the type of the comment, along with the comment and a userId that is
	 * associated with the comment.
	 * Authorization checks are made to determine if the security agent is allowed to call the service 
	 * and to add a particular comment type.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password contains the password for the service principal identifier.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType contains the type of identifier to be used to find the user in the CPR.
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param userId contains the userId associated with the comment.
	 * @param userCommentType contains the type of comment that is being added.
	 * 
	 * @return ServiceReturn object that contains the result of executing the service.
	 * 
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveUserComment")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveUserComment(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userId", mode = Mode.IN) String userId,
			@WebParam(name = "userCommentType", mode = Mode.IN) String userCommentType);

	/**
	 * This function provides the implementation for the AddUserid SOAP web service.  AddUserid will allow 
	 * authorized registration authorities to add a new userid for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  NOTE:
	 * if the user already has an existing userid, calling this service will provision a new secondary
	 * userid.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param personId the CPR private person identifier who a userid is to be created for.
	 * @return UseridServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.UseridServiceReturn
	 */
	@WebMethod(operationName = "AddUserid")
	@WebResult(name = "UseridServiceReturn")
	UseridServiceReturn AddUserid(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "personId", mode = Mode.IN) int personId);

	/**
	 * This function provides the implementation for the AddSpecialUserid SOAP web service.  AddSpecialUserid will allow 
	 * authorized registration authorities to add a new userid for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  NOTE:
	 * if the user already has an existing userid, calling this service will provision a new secondary
	 * userid.  The user will specify what the special userid is.  NOTE: this service will only be 
	 * executed in certain situations.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param personId the CPR private person identifier who a userid is to be created for.
	 * @param userid the userid that will be added for the user.
	 * @return ServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddSpecialUserid")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddSpecialUserid(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "personId", mode = Mode.IN) int personId,
			@WebParam(name = "userid", mode = Mode.IN) String userid);

	/**
	 * This function provides the implementation for the GetUserid SOAP web service.  GetUserid will allow 
	 * authorized registration authorities to obtain userid information for a person in the Central Person Registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  This service will
	 * return only the active records.
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (userid) who is requesting the information, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param returnHistory Y/N flag that indicates whether history is to be returned.
	 * @return UseridServiceReturn object that contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.UseridServiceReturn
	 */
	@WebMethod(operationName = "GetUserid")
	@WebResult(name = "UseridServiceReturn")
	UseridServiceReturn GetUserid(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy", mode = Mode.IN) String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

	/**
	 * This function provides the implementation for the SetPrimaryUserid SOAP web service.  SetPrimaryUserid will allow 
	 * authorized registration authorities to add a indicate a userid as primary in the central person registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param userid the userid that will be set as primary for the user.
	 * @return ServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "SetPrimaryUserid")
	@WebResult(name = "ServiceReturn")
	ServiceReturn SetPrimaryUserid(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userid", mode = Mode.IN) String userid);

	/**
	 * This function provides the implementation for the ArchiveUserid SOAP web service.  ArchiveUserid will allow 
	 * authorized registration authorities to add a indicate a userid as archived in the central person registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param userid the userid that will be archived.
	 * @return ServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchiveUserid")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchiveUserid(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userid", mode = Mode.IN) String userid);

	/**
	 * This function provides the implementation for the UnarchiveUserid SOAP web service.  UnarchiveUserid will allow 
	 * authorized registration authorities to add a indicate a userid as unarchived in the central person registry.  
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param userid the userid that will be unarchived.
	 * @return ServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UnarchiveUserid")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UnarchiveUserid(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "userid", mode = Mode.IN) String userid);

	/**
	 * This function provides the implementation for the AddPersonIdentifier SOAP web service.  AddPersonIdentifier will allow 
	 * authorized registration authorities to add a SoR registry identifier to a person.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param registryIdentifierType contains the type of SoR identifier that is being added to the user.
	 * @param registryIdentifierValue contains the value of the identfier that is being added.
	 * @return ServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "AddPersonIdentifier")
	@WebResult(name = "ServiceReturn")
	ServiceReturn AddPersonIdentifier(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "registryIdentifierType", mode = Mode.IN) String registryIdentifierType,
			@WebParam(name = "registryIdentifierValue", mode = Mode.IN) String registryIdentifierValue);

	/**
	 * This function provides the implementation for the UpdatePersonIdentifier SOAP web service.  UpdatePersonIdentifier will allow 
	 * authorized registration authorities to update a SoR registry identifier to a person.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param registryIdentifierType contains the type of SoR identifier that is being added to the user.
	 * @param registryIdentifierValue contains the value of the identfier that is being added.
	 * @return ServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "UpdatePersonIdentifier")
	@WebResult(name = "ServiceReturn")
	ServiceReturn UpdatePersonIdentifier(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "registryIdentifierType", mode = Mode.IN) String registryIdentifierType,
			@WebParam(name = "registryIdentifierValue", mode = Mode.IN) String registryIdentifierValue);

	/**
	 * This function provides the implementation for the ArchivePersonIdentifier SOAP web service.  ArchivePersonIdentifier will allow 
	 * authorized registration authorities to archive a SoR registry identifier to a person.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param updatedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param registryIdentifierType contains the type of SoR identifier that is being added to the user.
	 * @return ServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "ArchivePersonIdentifier")
	@WebResult(name = "ServiceReturn")
	ServiceReturn ArchivePersonIdentifier(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "updatedBy", mode = Mode.IN) String updatedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "registryIdentifierType", mode = Mode.IN) String registryIdentifierType);

	/**
	 * This function provides the implementation for the GetPersonIdentifier SOAP web service.  GetPersonIdentifier will allow 
	 * authorized registration authorities to query person identifiers for a person in the CPR.
	 * Authorization checks are made to determine if the RA agent is allowed to call the service.  
	 * 
	 * @param principalId server principal identifier (will be a Kerberos principal).
	 * @param password password for the server principal specified as the first argument.
	 * @param requestedBy the person (userid) who is making the change, this person will be an RA agent.
	 * @param identifierType the type of identifier used to find the person in the CPR. 
	 * @param identifier the value for the identifier specified in the identifierType argument.
	 * @param registryIdentifierType contains the type of SoR identifier that is being added to the user.
	 * @return PersonIdentifierServiceReturn object contains the result of executing the service.
	 * @see edu.psu.iam.cpr.core.api.returns.ServiceReturn
	 */
	@WebMethod(operationName = "GetPersonIdentifier")
	@WebResult(name = "PersonIdentifierServiceReturn")
	PersonIdentifierServiceReturn GetPersonIdentifier(
			@WebParam(name = "principalId", mode = Mode.IN) String principalId,
			@WebParam(name = "password", mode = Mode.IN) String password,
			@WebParam(name = "requestedBy") String requestedBy,
			@WebParam(name = "identifierType", mode = Mode.IN) String identifierType,
			@WebParam(name = "identifier", mode = Mode.IN) String identifier,
			@WebParam(name = "registryIdentifierType", mode = Mode.IN) String registryIdentifierType,
			@WebParam(name = "returnHistory", mode = Mode.IN) String returnHistory);

}
