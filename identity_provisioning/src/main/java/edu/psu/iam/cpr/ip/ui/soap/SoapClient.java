/* SVN FILE: $Id: SoapClient.java 5992 2013-01-09 18:37:24Z slk24 $ */
package edu.psu.iam.cpr.ip.ui.soap;

import static edu.psu.iam.cpr.core.api.BaseApi.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.api.AddAddressApi;
import edu.psu.iam.cpr.core.api.AddPersonApi;
import edu.psu.iam.cpr.core.api.AddPhoneApi;
import edu.psu.iam.cpr.core.api.GetPersonApi;
import edu.psu.iam.cpr.core.api.GetPhoneApi;
import edu.psu.iam.cpr.core.api.SearchForPersonApi;
import edu.psu.iam.cpr.core.api.UpdatePersonApi;
import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.PhoneServiceReturn;
import edu.psu.iam.cpr.core.api.returns.FindPersonServiceReturn;
import edu.psu.iam.cpr.core.api.returns.PersonServiceReturn;
import edu.psu.iam.cpr.core.api.returns.ServiceReturn;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.PhoneReturn;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;

/**
*
* This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
* view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
* Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
*
* @package edu.psu.iam.cpr.ip.ui.soap
* @author $Author: slk24 $
* @version $Rev: 5992 $
* @lastrevision $Date: 2013-01-09 13:37:24 -0500 (Wed, 09 Jan 2013) $
*/

/**
*
* This class contains soap client service calls
*/
public final class SoapClient {
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(SoapClient.class);
	
	/** Database connection */
	private static final Database db = new Database();
	
	/** Instance of the service core return object */
	private static final ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
	
	/** Contains the user who updated the record */
	private static final String updatedBy = "cpruser";
	
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private SoapClient()  { }
	
	/**
	 * This method calls the get phone soap service
	 * @return
	 */
	public static PhoneReturn[] callGetPhoneService() {
		
		Session apiSession = null;
		PhoneServiceReturn phoneServiceReturn = null;
		try {
			final String phoneType = null;
			final String returnHistory = "N";

			apiSession = SessionFactoryUtil.getSessionFactory().openSession();
			apiSession.getTransaction().begin();

			db.setSession(apiSession);
			serviceCoreReturn.setPersonId(100000L);
			
	        final Map<String, Object> otherParameters = new HashMap<String, Object>(2);
	        otherParameters.put(PHONE_TYPE_KEY, phoneType);
	        otherParameters.put(RETURN_HISTORY_KEY, returnHistory);

			phoneServiceReturn = (PhoneServiceReturn) new GetPhoneApi().implementApi(
					CprServiceName.GetPhone.toString(), 
					db, 
					updatedBy, 
					serviceCoreReturn, 
					otherParameters, 
					ApiHelper.SKIP_AUTHZ_CHECK);

			apiSession.getTransaction().commit();

			return phoneServiceReturn.getPhoneReturnRecord();
		}
		catch (Exception e) {
			try {
				apiSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			apiSession.close();
		}

		return null;
	}
	
	/**
	 * This method calls the get person soap service
	 * @return
	 * @throws Exception
	 */
	public static PersonServiceReturn callGetPersonService() {
		
		Session apiSession = null;
		PersonServiceReturn personServiceReturn = null;
		try {
			final String returnHistory = "N";

			apiSession = SessionFactoryUtil.getSessionFactory().openSession();
			apiSession.getTransaction().begin();

			db.setSession(apiSession);
			serviceCoreReturn.setPersonId(100000L);

	        final Map<String, Object> otherParameters = new HashMap<String, Object>(1);
	        otherParameters.put(RETURN_HISTORY_KEY, returnHistory);

	        personServiceReturn = (PersonServiceReturn) new GetPersonApi().implementApi(
					CprServiceName.GetPerson.toString(), 
					db, 
					updatedBy, 
					serviceCoreReturn, 
					otherParameters, 
					ApiHelper.SKIP_AUTHZ_CHECK);

			apiSession.getTransaction().commit();

			return personServiceReturn;
		}
		catch (Exception e) {
			try {
				apiSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			apiSession.close();
		}

		return null;
	}
	
	/**
	 * This method calls the find person soap service
	 * @param personData
	 * @return
	 * @throws Exception
	 */
	public static FindPersonServiceReturn callFindPersonService(Map<String, String> findPersonData, String uniqueId) throws Exception {

		LOG.info(uniqueId +" " +"Sending to cleanse data " + findPersonData.get(UIConstants.PRINCIPAL_ID) + findPersonData.get(UIConstants.REQUESTED_BY) + 
				findPersonData.get("pennStateId") + findPersonData.get("userId") + findPersonData.get(UIConstants.SSN) + findPersonData.get(UIConstants.FIRST_NAME) + findPersonData.get(UIConstants.LAST_NAME) +  
				findPersonData.get(UIConstants.MIDDLE_NAMES) + findPersonData.get(UIConstants.ADDRESS1) +  findPersonData.get(UIConstants.ADDRESS2) +  findPersonData.get(UIConstants.ADDRESS3) +  findPersonData.get(UIConstants.CITY) + 
				findPersonData.get(UIConstants.STATE_OR_PROV) +  findPersonData.get(UIConstants.POSTAL_CODE) + findPersonData.get("plus4") +  findPersonData.get(UIConstants.COUNTRY) +  findPersonData.get(UIConstants.DOB) + 
				findPersonData.get(UIConstants.GENDER) + findPersonData.get("rankCutOff"));
		LOG.info(uniqueId +" " +"Hash size is " + findPersonData.size());
		
		findPersonData = cleanseHash(findPersonData);
		
		LOG.info(uniqueId +" " +"Sending to find person service " + findPersonData.get(UIConstants.PRINCIPAL_ID) + findPersonData.get(UIConstants.REQUESTED_BY) + 
				findPersonData.get("pennStateId") + findPersonData.get("userId") + findPersonData.get(UIConstants.SSN) + findPersonData.get(UIConstants.FIRST_NAME) + findPersonData.get(UIConstants.LAST_NAME) +  
				findPersonData.get(UIConstants.MIDDLE_NAMES) + findPersonData.get(UIConstants.ADDRESS1) +  findPersonData.get(UIConstants.ADDRESS2) +  findPersonData.get(UIConstants.ADDRESS3) +  findPersonData.get(UIConstants.CITY) + 
				findPersonData.get(UIConstants.STATE_OR_PROV) +  findPersonData.get(UIConstants.POSTAL_CODE) + findPersonData.get("plus4") +  findPersonData.get(UIConstants.COUNTRY) +  findPersonData.get(UIConstants.DOB) + 
				findPersonData.get(UIConstants.GENDER) + findPersonData.get("rankCutOff"));
		LOG.info(uniqueId +" " +"Hash size is " + findPersonData.size());
		
		Session apiSession = null;
		FindPersonServiceReturn findPersonServiceReturn = null;
		try {

			apiSession = SessionFactoryUtil.getSessionFactory().openSession();
			apiSession.getTransaction().begin();

			db.setSession(apiSession);

	        final Map<String, Object> otherParameters = new HashMap<String,Object>();
	        otherParameters.put(PSUID_KEY, findPersonData.get("pennStateId"));
	        otherParameters.put(USERID_KEY, findPersonData.get("userId"));
	        otherParameters.put(SSN_KEY, findPersonData.get(UIConstants.SSN));
	        otherParameters.put(FIRST_NAME_KEY, findPersonData.get(UIConstants.FIRST_NAME));
	        otherParameters.put(LAST_NAME_KEY, findPersonData.get(UIConstants.LAST_NAME));
	        otherParameters.put(MIDDLE_NAMES_KEY, findPersonData.get(UIConstants.MIDDLE_NAMES));
	        otherParameters.put(ADDRESS1_KEY, findPersonData.get(UIConstants.ADDRESS1));
	        otherParameters.put(ADDRESS2_KEY, findPersonData.get(UIConstants.ADDRESS2));
	        otherParameters.put(ADDRESS3_KEY, findPersonData.get(UIConstants.ADDRESS3));
	        otherParameters.put(CITY_KEY, findPersonData.get(UIConstants.CITY));
	        otherParameters.put(STATE_KEY, findPersonData.get(UIConstants.STATE_OR_PROV));
	        otherParameters.put(POSTALCODE_KEY, findPersonData.get(UIConstants.POSTAL_CODE));
	        otherParameters.put(PLUS4_KEY, findPersonData.get("plus4"));
	        otherParameters.put(COUNTRY_KEY, findPersonData.get(UIConstants.COUNTRY));
	        otherParameters.put(DOB_KEY, findPersonData.get(UIConstants.DOB));
	        otherParameters.put(GENDER_KEY, findPersonData.get(UIConstants.GENDER));
	        otherParameters.put(RANK_CUTOFF_KEY, findPersonData.get("rankCutOff"));
	        
			findPersonServiceReturn = (FindPersonServiceReturn) new SearchForPersonApi().implementApi(
					CprServiceName.FindPerson.toString(), 
					db, 
					findPersonData.get(UIConstants.REQUESTED_BY), 
					serviceCoreReturn, 
					otherParameters, 
					ApiHelper.SKIP_AUTHZ_CHECK);

			apiSession.getTransaction().commit();

			return findPersonServiceReturn;
		}
		catch (Exception e) {
			try {
				apiSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			apiSession.close();
		}

		throw new Exception("Unable to find person" + UIConstants.CODE + ReturnType.GENERAL_EXCEPTION.index());
		
	}

	/**
	 * This method calls the add person soap service
	 * @param personData
	 * @return
	 * @throws Exception
	 */
	public static PersonServiceReturn callAddPersonService(Map<String, String> addPerson, String uniqueId) throws Exception {
		
		addPerson = cleanseHash(addPerson);
		
		LOG.info(uniqueId +" " +"Sending to add person " + addPerson.get(UIConstants.PRINCIPAL_ID) + addPerson.get(UIConstants.REQUESTED_BY) + 
				addPerson.get(UIConstants.ASSIGN_PSU_ID) + addPerson.get(UIConstants.ASSIGN_USER_ID) + addPerson.get(UIConstants.GENDER) +  addPerson.get(UIConstants.DOB) + 
				addPerson.get(UIConstants.NAME_TYPE) + addPerson.get(UIConstants.NAME_DOCUMENT_TYPE) + addPerson.get(UIConstants.FIRST_NAME) + addPerson.get(UIConstants.MIDDLE_NAMES) + 
				addPerson.get(UIConstants.LAST_NAME) + addPerson.get(UIConstants.SUFFIX) + addPerson.get(UIConstants.ADDRESS_TYPE) + addPerson.get(UIConstants.ADDRESS_DOCUMENT_TYPE) + 
				addPerson.get(UIConstants.ADDRESS1) +  addPerson.get(UIConstants.ADDRESS2) +  addPerson.get(UIConstants.ADDRESS3) + 
				addPerson.get(UIConstants.CITY) + addPerson.get(UIConstants.STATE_OR_PROV) +  addPerson.get(UIConstants.POSTAL_CODE) + addPerson.get(UIConstants.COUNTRY) +
				addPerson.get(UIConstants.CAMPUS) + addPerson.get(UIConstants.PHONE_TYPE) + addPerson.get(UIConstants.PHONE_NUMBER) + 
				addPerson.get(UIConstants.EXTENSION) + addPerson.get(UIConstants.INTERNATIONAL_NUMBER) + addPerson.get(UIConstants.EMAIL_TYPE) + addPerson.get(UIConstants.EMAIL) + 
				addPerson.get(UIConstants.AFFILIATION) + addPerson.get(UIConstants.SSN));

		Session apiSession = null;
		PersonServiceReturn personServiceReturn = null;
		try {

			apiSession = SessionFactoryUtil.getSessionFactory().openSession();
			apiSession.getTransaction().begin();

			db.setSession(apiSession);
			serviceCoreReturn.setPersonId(-1L);

	        final Map<String, Object> otherParameters = new HashMap<String, Object>(30);
	        otherParameters.put(DO_FIND_PERSON_KEY, UIConstants.LETTER_N);
	        otherParameters.put(ASSIGN_PSU_ID_FLAG_KEY, addPerson.get(UIConstants.ASSIGN_PSU_ID));
	        otherParameters.put(ASSIGN_USERID_FLAG_KEY, addPerson.get(UIConstants.ASSIGN_USER_ID));
	        otherParameters.put(GENDER_KEY, addPerson.get(UIConstants.GENDER));
	        otherParameters.put(DOB_KEY, addPerson.get(UIConstants.DOB));
	        otherParameters.put(NAME_TYPE_KEY, addPerson.get(UIConstants.NAME_TYPE));
	        otherParameters.put(NAME_DOCUMENT_TYPE_KEY, addPerson.get(UIConstants.NAME_DOCUMENT_TYPE));
	        otherParameters.put(FIRST_NAME_KEY, addPerson.get(UIConstants.FIRST_NAME));
	        otherParameters.put(MIDDLE_NAMES_KEY, addPerson.get(UIConstants.MIDDLE_NAMES));
	        otherParameters.put(LAST_NAME_KEY, addPerson.get(UIConstants.LAST_NAME));
	        otherParameters.put(SUFFIX_KEY, addPerson.get(UIConstants.SUFFIX));
	        otherParameters.put(ADDRESS_TYPE_KEY, addPerson.get(UIConstants.ADDRESS_TYPE));
	        otherParameters.put(ADDRESS_DOCUMENT_TYPE_KEY, addPerson.get(UIConstants.ADDRESS_DOCUMENT_TYPE));
	        otherParameters.put(ADDRESS1_KEY, addPerson.get(UIConstants.ADDRESS1));
	        otherParameters.put(ADDRESS2_KEY, addPerson.get(UIConstants.ADDRESS2));
	        otherParameters.put(ADDRESS3_KEY, addPerson.get(UIConstants.ADDRESS3));
	        otherParameters.put(CITY_KEY, addPerson.get(UIConstants.CITY));
	        otherParameters.put(STATE_KEY, addPerson.get(UIConstants.STATE_OR_PROV));
	        otherParameters.put(POSTALCODE_KEY, addPerson.get(UIConstants.POSTAL_CODE));
	        otherParameters.put(COUNTRY_KEY, addPerson.get(UIConstants.COUNTRY));
	        otherParameters.put(CAMPUS_KEY, addPerson.get(UIConstants.CAMPUS));
	        otherParameters.put(VERIFY_ADDRESS_FLAG_KEY, UIConstants.LETTER_N);
	        otherParameters.put(PHONE_TYPE_KEY, addPerson.get(UIConstants.PHONE_TYPE));
	        otherParameters.put(PHONE_NUMBER_KEY, addPerson.get(UIConstants.PHONE_NUMBER));
	        otherParameters.put(PHONE_EXTENSION_KEY, addPerson.get(UIConstants.EXTENSION));
	        otherParameters.put(PHONE_INTERNATIONAL_NUMBER_KEY, addPerson.get(UIConstants.INTERNATIONAL_NUMBER));
	        otherParameters.put(EMAIL_ADDRESS_TYPE_KEY, addPerson.get(UIConstants.EMAIL_TYPE));
	        otherParameters.put(EMAIL_ADDRESS_KEY, addPerson.get(UIConstants.EMAIL));
	        otherParameters.put(AFFILIATION_KEY, addPerson.get(UIConstants.AFFILIATION));
	        otherParameters.put(SSN_KEY, addPerson.get(UIConstants.SSN));

	        personServiceReturn = (PersonServiceReturn) new AddPersonApi().implementApi(
					CprServiceName.AddPerson.toString(), 
					db, 
					addPerson.get(UIConstants.REQUESTED_BY), 
					serviceCoreReturn, 
					otherParameters, 
					ApiHelper.SKIP_AUTHZ_CHECK);

			apiSession.getTransaction().commit();

			return personServiceReturn;
		}
		catch (Exception e) {
			try {
				apiSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			apiSession.close();
		}

		throw new Exception("Unable to add person" + UIConstants.CODE + ReturnType.ADD_FAILED_EXCEPTION.index());
	}
	
	/**
	 * This method calls the update person soap service
	 * @param personData
	 * @return
	 * @throws Exception
	 */
	public static PersonServiceReturn callUpdatePersonService(Map<String, String> updatePersonData) throws Exception {
		updatePersonData = cleanseHash(updatePersonData);

		LOG.info("Sending to update person " + updatePersonData.get(UIConstants.PRINCIPAL_ID) + updatePersonData.get(UIConstants.REQUESTED_BY) + 
				updatePersonData.get(UIConstants.IDENTIFIER_TYPE) + updatePersonData.get(UIConstants.IDENTIFIER) +
				updatePersonData.get(UIConstants.ASSIGN_PSU_ID) + updatePersonData.get(UIConstants.ASSIGN_USER_ID) + updatePersonData.get(UIConstants.GENDER) +  updatePersonData.get(UIConstants.DOB) + 
				updatePersonData.get(UIConstants.NAME_TYPE) + updatePersonData.get(UIConstants.NAME_DOCUMENT_TYPE) + updatePersonData.get(UIConstants.FIRST_NAME) + updatePersonData.get(UIConstants.MIDDLE_NAMES) + 
				updatePersonData.get(UIConstants.LAST_NAME) + updatePersonData.get(UIConstants.SUFFIX) + updatePersonData.get(UIConstants.ADDRESS_TYPE) + updatePersonData.get(UIConstants.ADDRESS_DOCUMENT_TYPE) + 
				updatePersonData.get(UIConstants.ADDRESS_GROUP_ID) + updatePersonData.get(UIConstants.ADDRESS1) + updatePersonData.get(UIConstants.ADDRESS2) +  updatePersonData.get(UIConstants.ADDRESS3) + 
				updatePersonData.get(UIConstants.CITY) + updatePersonData.get(UIConstants.STATE_OR_PROV) +  updatePersonData.get(UIConstants.POSTAL_CODE) + updatePersonData.get(UIConstants.COUNTRY) +
				updatePersonData.get(UIConstants.CAMPUS) + updatePersonData.get(UIConstants.PHONE_TYPE) + updatePersonData.get(UIConstants.PHONE_GROUP_ID) + updatePersonData.get(UIConstants.PHONE_NUMBER) + 
				updatePersonData.get(UIConstants.EXTENSION) + updatePersonData.get(UIConstants.INTERNATIONAL_NUMBER) + updatePersonData.get(UIConstants.EMAIL_TYPE) + updatePersonData.get(UIConstants.EMAIL) + 
				updatePersonData.get(UIConstants.AFFILIATION) + updatePersonData.get(UIConstants.SSN));

		// Convert address group Id to Long
		String groupId = updatePersonData.get(UIConstants.ADDRESS_GROUP_ID);
		Long addressGroupId = null;
		if (groupId != null) {
			addressGroupId = new Long(groupId);
		}
		//Convert phone group Id to Long
		groupId = updatePersonData.get(UIConstants.PHONE_GROUP_ID);
		Long phoneGroupId = null;
		if (groupId != null) {
			phoneGroupId = new Long(groupId);
		}
		
		Session apiSession = null;
		PersonServiceReturn personServiceReturn = null;
		try {

			apiSession = SessionFactoryUtil.getSessionFactory().openSession();
			apiSession.getTransaction().begin();

			db.setSession(apiSession);
			serviceCoreReturn.setPersonId(Long.valueOf(updatePersonData.get(UIConstants.IDENTIFIER)));

	        final Map<String, Object> otherParameters = new HashMap<String, Object>(29);
	        otherParameters.put(ASSIGN_PSU_ID_FLAG_KEY, updatePersonData.get(UIConstants.ASSIGN_PSU_ID));
	        otherParameters.put(ASSIGN_USERID_FLAG_KEY, updatePersonData.get(UIConstants.ASSIGN_USER_ID));
	        otherParameters.put(GENDER_KEY, updatePersonData.get(UIConstants.GENDER));
	        otherParameters.put(DOB_KEY, updatePersonData.get(UIConstants.DOB));
	        otherParameters.put(NAME_TYPE_KEY, updatePersonData.get(UIConstants.NAME_TYPE));
	        otherParameters.put(NAME_DOCUMENT_TYPE_KEY, updatePersonData.get(UIConstants.NAME_DOCUMENT_TYPE));
	        otherParameters.put(FIRST_NAME_KEY, updatePersonData.get(UIConstants.FIRST_NAME));
	        otherParameters.put(MIDDLE_NAMES_KEY, updatePersonData.get(UIConstants.MIDDLE_NAMES));
	        otherParameters.put(LAST_NAME_KEY, updatePersonData.get(UIConstants.LAST_NAME));
	        otherParameters.put(SUFFIX_KEY, updatePersonData.get(UIConstants.SUFFIX));
	        otherParameters.put(ADDRESS_TYPE_KEY, updatePersonData.get(UIConstants.ADDRESS_TYPE));
	        otherParameters.put(ADDRESS_DOCUMENT_TYPE_KEY, updatePersonData.get(UIConstants.ADDRESS_DOCUMENT_TYPE));
	        otherParameters.put(ADDRESS_GROUP_ID_KEY, addressGroupId);
	        otherParameters.put(ADDRESS1_KEY, updatePersonData.get(UIConstants.ADDRESS1));
	        otherParameters.put(ADDRESS2_KEY, updatePersonData.get(UIConstants.ADDRESS2));
	        otherParameters.put(ADDRESS3_KEY, updatePersonData.get(UIConstants.ADDRESS3));
	        otherParameters.put(CITY_KEY, updatePersonData.get(UIConstants.CITY));
	        otherParameters.put(STATE_KEY, updatePersonData.get(UIConstants.STATE_OR_PROV));
	        otherParameters.put(POSTALCODE_KEY, updatePersonData.get(UIConstants.POSTAL_CODE));
	        otherParameters.put(COUNTRY_KEY, updatePersonData.get(UIConstants.COUNTRY));
	        otherParameters.put(CAMPUS_KEY, updatePersonData.get(UIConstants.CAMPUS));
	        otherParameters.put(VERIFY_ADDRESS_FLAG_KEY, UIConstants.LETTER_N);
	        otherParameters.put(PHONE_TYPE_KEY, updatePersonData.get(UIConstants.PHONE_TYPE));
	        otherParameters.put(PHONE_GROUP_ID_KEY, phoneGroupId);
	        otherParameters.put(PHONE_NUMBER_KEY, updatePersonData.get(UIConstants.PHONE_NUMBER));
	        otherParameters.put(PHONE_EXTENSION_KEY, updatePersonData.get(UIConstants.EXTENSION));
	        otherParameters.put(PHONE_INTERNATIONAL_NUMBER_KEY, updatePersonData.get(UIConstants.INTERNATIONAL_NUMBER));
	        otherParameters.put(EMAIL_ADDRESS_TYPE_KEY, updatePersonData.get(UIConstants.EMAIL_TYPE));
	        otherParameters.put(EMAIL_ADDRESS_KEY, updatePersonData.get(UIConstants.EMAIL));
	        otherParameters.put(AFFILIATION_KEY, updatePersonData.get(UIConstants.AFFILIATION));
	        otherParameters.put(SSN_KEY, updatePersonData.get(UIConstants.SSN));
	        
			personServiceReturn = (PersonServiceReturn) new UpdatePersonApi().implementApi(
					CprServiceName.UpdatePerson.toString(), 
					db, 
					updatePersonData.get(UIConstants.REQUESTED_BY), 
					serviceCoreReturn, 
					otherParameters, 
					ApiHelper.SKIP_AUTHZ_CHECK);

			apiSession.getTransaction().commit();

			return personServiceReturn;
		}
		catch (Exception e) {
			try {
				apiSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			apiSession.close();
		}
		throw new Exception("Unable to update person" + UIConstants.CODE + ReturnType.UPDATE_FAILED_EXCEPTION.index());
	}
	
	/**
	 * This method calls the add address soap service
	 * @param personData
	 * @return
	 * @throws Exception
	 */
	public static ServiceReturn callAddAddressService(Map<String, String> addAddressData) throws Exception {
		
		addAddressData = cleanseHash(addAddressData);
		
		// call service
		LOG.info("Sending to add address " + addAddressData.get(UIConstants.PRINCIPAL_ID) + addAddressData.get(UIConstants.REQUESTED_BY) + 
				addAddressData.get(UIConstants.IDENTIFIER_TYPE) + addAddressData.get(UIConstants.IDENTIFIER) +
				addAddressData.get(UIConstants.ADDRESS_TYPE) + addAddressData.get(UIConstants.ADDRESS_DOCUMENT_TYPE) + 
				addAddressData.get(UIConstants.ADDRESS1) + addAddressData.get(UIConstants.ADDRESS2) +  addAddressData.get(UIConstants.ADDRESS3) + 
				addAddressData.get(UIConstants.CITY) + addAddressData.get(UIConstants.STATE_OR_PROV) +  addAddressData.get(UIConstants.POSTAL_CODE) + addAddressData.get(UIConstants.COUNTRY) +
				addAddressData.get(UIConstants.CAMPUS));

		Session apiSession = null;
		try {

			apiSession = SessionFactoryUtil.getSessionFactory().openSession();
			apiSession.getTransaction().begin();

			db.setSession(apiSession);
			serviceCoreReturn.setPersonId(Long.valueOf(addAddressData.get(UIConstants.IDENTIFIER)));

	        final Map<String, Object> otherParameters = new HashMap<String,Object>(11);
	        otherParameters.put(ADDRESS_TYPE_KEY, addAddressData.get(UIConstants.ADDRESS_TYPE));
	        otherParameters.put(DOCUMENT_TYPE_KEY, addAddressData.get(UIConstants.ADDRESS_DOCUMENT_TYPE));
	        otherParameters.put(ADDRESS1_KEY, addAddressData.get(UIConstants.ADDRESS1));
	        otherParameters.put(ADDRESS2_KEY, addAddressData.get(UIConstants.ADDRESS2));
	        otherParameters.put(ADDRESS3_KEY, addAddressData.get(UIConstants.ADDRESS3));
	        otherParameters.put(CITY_KEY, addAddressData.get(UIConstants.CITY));
	        otherParameters.put(STATE_KEY, addAddressData.get(UIConstants.STATE_OR_PROV));
	        otherParameters.put(POSTALCODE_KEY, addAddressData.get(UIConstants.POSTAL_CODE));
	        otherParameters.put(COUNTRY_KEY, addAddressData.get(UIConstants.COUNTRY));
	        otherParameters.put(CAMPUS_KEY, addAddressData.get(UIConstants.CAMPUS));
	        otherParameters.put(VERIFY_ADDRESS_FLAG_KEY, UIConstants.LETTER_N);
	        
			new AddAddressApi().implementApi(
					CprServiceName.AddAddress.toString(), 
					db, 
					addAddressData.get(UIConstants.REQUESTED_BY), 
					serviceCoreReturn, 
					otherParameters, 
					ApiHelper.SKIP_AUTHZ_CHECK);

			apiSession.getTransaction().commit();

			return new ServiceReturn(ReturnType.SUCCESS.index(), ApiHelper.SUCCESS_MESSAGE);
		}
		catch (Exception e) {
			try {
				apiSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			apiSession.close();
		}


		throw new Exception("Unable to add address" + UIConstants.CODE + ReturnType.ADD_FAILED_EXCEPTION.index());
	}
	
	/**
	 * This method calls the add phone soap service
	 * @param addPhoneData
	 * @return
	 * @throws Exception
	 */
	public static ServiceReturn callAddPhoneService(Map<String, String> addPhoneData) throws Exception {
		addPhoneData = cleanseHash(addPhoneData);
		
		// call service
		LOG.info("Sending to add phone " + addPhoneData.get(UIConstants.PRINCIPAL_ID) + addPhoneData.get(UIConstants.REQUESTED_BY) + 
				addPhoneData.get(UIConstants.IDENTIFIER_TYPE) + addPhoneData.get(UIConstants.IDENTIFIER) + addPhoneData.get(UIConstants.PHONE_TYPE) + addPhoneData.get(UIConstants.PHONE_NUMBER) + 
				addPhoneData.get(UIConstants.EXTENSION) + addPhoneData.get(UIConstants.INTERNATIONAL_NUMBER));
		
		Session apiSession = null;
		try {

			apiSession = SessionFactoryUtil.getSessionFactory().openSession();
			apiSession.getTransaction().begin();

			db.setSession(apiSession);
			serviceCoreReturn.setPersonId(Long.valueOf(addPhoneData.get(UIConstants.IDENTIFIER)));

	        final Map<String, Object> otherParameters = new HashMap<String, Object>(4);
	        otherParameters.put(PHONE_TYPE_KEY, addPhoneData.get(UIConstants.PHONE_TYPE));
	        otherParameters.put(PHONE_NUMBER_KEY, addPhoneData.get(UIConstants.PHONE_NUMBER));
	        otherParameters.put(PHONE_EXTENSION_KEY, addPhoneData.get(UIConstants.EXTENSION));
	        otherParameters.put(PHONE_INTERNATIONAL_NUMBER_KEY, addPhoneData.get(UIConstants.INTERNATIONAL_NUMBER));

	        
			new AddPhoneApi().implementApi(
					CprServiceName.AddPhone.toString(), 
					db, 
					addPhoneData.get(UIConstants.REQUESTED_BY), 
					serviceCoreReturn, 
					otherParameters, 
					ApiHelper.SKIP_AUTHZ_CHECK);

			apiSession.getTransaction().commit();

			return new ServiceReturn(ReturnType.SUCCESS.index(), ApiHelper.SUCCESS_MESSAGE);
		}
		catch (Exception e) {
			try {
				apiSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			apiSession.close();
		}

		throw new Exception("Unable to add telephone number" + UIConstants.CODE + ReturnType.ADD_FAILED_EXCEPTION.index());
	}

	/**
	 * This method removes entries from a map that contain values of empty strings 
	 * @param addPerson 
	 * @return
	 */
	private static Map<String, String> cleanseHash(Map<String, String> addPerson) {
		Map<String, String> cleansedHash = new HashMap<String, String>(addPerson);
		//Remove all entries with a value of empty string
		Iterator<Entry<String,String>> hashIterator = addPerson.entrySet().iterator();
		while (hashIterator.hasNext()) {
			Entry<String, String> mapEntry = (Entry<String, String>)hashIterator.next();
			if (mapEntry.getValue() == null || mapEntry.getValue().trim().equals("")) {
				cleansedHash.remove(mapEntry.getKey());
			}
		}
		return cleansedHash;
	}
}

