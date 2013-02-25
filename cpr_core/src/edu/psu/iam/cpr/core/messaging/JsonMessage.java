/* SVN FILE: $Id: JsonMessage.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.messaging;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.beans.DateOfBirth;
import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.beans.PersonIdCard;
import edu.psu.iam.cpr.core.database.beans.Phones;
import edu.psu.iam.cpr.core.database.beans.PsuDirectory;
import edu.psu.iam.cpr.core.database.beans.UserComments;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.IdCardTable;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.tables.PsuDirectoryTable;
import edu.psu.iam.cpr.core.database.tables.PsuIdTable;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.database.types.MessageKeyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.util.CprProperties;

/**
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
 * 
 * @package edu.psu.iam.cpr.core.messaging
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class JsonMessage {
	
	/** Contains the constructor jsonObject. */
	private JSONObject jsonObject = null;

	
	/** Contains the service name. */
	private String serviceName;
	
	/** Contains the requesting userid . */
	private String requestedBy;
	
	/**
	 * Constructor
	 */
	public JsonMessage() {
		super();
	}

	/**
	 * Constructor
	 * @param db database connection.
	 * @param personId person identifier from the CPR.
	 * @param serviceName contains the service name that is requesting a new message.
	 * @param requestedBy contains the userid of the person who is requesting the message.
	 * @throws JSONException 
	 * @throws CprException 
	 */
	public JsonMessage(Database db, long personId, String serviceName, String requestedBy) throws JSONException, CprException  {
		
		jsonObject = new JSONObject();
		setJsonObject(jsonObject);
		setServiceName(serviceName);
		setRequestedBy(requestedBy);
			
		final PsuDirectoryTable psuDirectoryTable = new PsuDirectoryTable();
		psuDirectoryTable.getPsuDirectoryTable(db, personId);

		setValue(MessageKeyName.SERVICE_NAME, getServiceName());
		setValue(MessageKeyName.REQUESTED_BY, requestedBy);
		setValue(MessageKeyName.PERSON_ID, personId);

		final PsuIdTable psuIdTable = new PsuIdTable();
		psuIdTable.setReturnHistoryFlag(false);
		final PsuIdReturn[] psuIdReturn = psuIdTable.getPsuIdForPersonId(db, personId);		
		if (psuIdReturn.length == 1) {
			setValue(MessageKeyName.PSU_ID, psuIdReturn[0].getPsuId());
		}
		else {
			setValue(MessageKeyName.PSU_ID, (String) null);
		}

		final PsuDirectory bean = psuDirectoryTable.getPsuDirectoryBean();
		setValue(MessageKeyName.DIRECTORY_ID, bean.getPsuDirectoryKey());
		setValue(MessageKeyName.USERID, bean.getUserid());
	}

	/**
	 * This routine is used to set a key/value for a string value portion of a JSON message.
	 * @param key contains the key.
	 * @param value contains the value.
	 * @throws JSONException exception will be thrown if there are any problems.
	 */
	public final void setValue(MessageKeyName key, String value) throws JSONException {
		
		// If the value is NULL, we need to set a special JSON value to indicate NULL.
		if (value == null) {
			getJsonObject().put(key.toString(), JSONObject.NULL);
		}
		
		// Otherwise set the value.
		else {
			getJsonObject().put(key.toString(), value);
		}
	}
	
	/**
	 * This routine is used to set a key/value for a integer portion of a JSON message.
	 * @param key contains the key.
	 * @param value contains the value.
	 * @throws JSONException exception will be thrown if there are any problems.
	 */
	public final void setValue(MessageKeyName key, Long value) throws JSONException {
		getJsonObject().put(key.toString(), Long.toString(value));
	}
	
	/**
	 * @return the jsonObject
	 */
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	
	/**
	 * @param jsonObject
	 */
	public final void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public final void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the serviceName
	 */
	public final String getServiceName() {
		return serviceName;
	}
	
	/**
	 * @param requestedBy the requestedBy to set
	 */
	public final void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}

	/**
	 * This routine is used to set the names portion of a message.
	 * @param namesTable contains a NamesTable object containing the name information.
	 * @throws JSONException 
	 */
	public void setName(NamesTable namesTable) throws JSONException {
		setValue(MessageKeyName.NAME_TYPE, namesTable.getNameType().toString());
		if (! getServiceName().equals(CprServiceName.ArchiveName.toString())) {
			final Names bean = namesTable.getNamesBean();
			setValue(MessageKeyName.FIRST_NAME, bean.getFirstName());
			setValue(MessageKeyName.MIDDLE_NAMES, bean.getMiddleNames());
			setValue(MessageKeyName.LAST_NAME, bean.getLastName());
			setValue(MessageKeyName.SUFFIX, bean.getSuffix());
		}
	}
	
	/**
	 * This routine is used to set the phones portion of a message.
	 * @param phonesTable contains a PhonesTable object containing the phone information.
	 * @throws JSONException 
	 */
	public void setPhone(PhonesTable phonesTable) throws JSONException  {
		setValue(MessageKeyName.PHONE_TYPE, phonesTable.getPhoneType().toString());
		if (! getServiceName().equals(CprServiceName.ArchivePhone.toString())) {
			final Phones bean = phonesTable.getPhonesBean();
			setValue(MessageKeyName.PHONE_NUMBER, bean.getPhoneNumber());
			setValue(MessageKeyName.EXTENSION, bean.getExtension());
		}
	}
	
	/**
	 * This routine is used to set the addresses portion of a message.
	 * @param addressesTable contains an AddressesTable object containing the address information.
	 * @throws JSONException 
	 */
	public void setAddress(AddressesTable addressesTable) throws JSONException  {
			setValue(MessageKeyName.ADDRESS_TYPE, addressesTable.getAddressType().toString());
			if (! getServiceName().equals(CprServiceName.ArchiveAddress.toString())) {
				final Addresses bean = addressesTable.getAddressesBean();
				setValue(MessageKeyName.ADDRESS1, bean.getAddress1());
				setValue(MessageKeyName.ADDRESS2, bean.getAddress2());
				setValue(MessageKeyName.ADDRESS3, bean.getAddress3());
				setValue(MessageKeyName.STATE, bean.getState());
				setValue(MessageKeyName.CITY, bean.getCity());
				setValue(MessageKeyName.POSTAL_CODE, bean.getPostalCode());
				setValue(MessageKeyName.PROVINCE, bean.getProvince());
				setValue(MessageKeyName.CAMPUS_NAME, addressesTable.getCampusName());
				setValue(MessageKeyName.COUNTRY_NAME, addressesTable.getCountryName());
			}
	}
	
	/**
	 * This routine is used to set the userid portion of a message.
	 * @param useridTable contains the UseridTable object containing the userid information.
	 * @throws JSONException 
	 */
	public void setUserid(UseridTable useridTable) throws JSONException   {
		setValue(MessageKeyName.USERID, useridTable.getUseridBean().getUserid());
	}
	
	/**
	 * This routine is used to set the date of birth portion of a message.
	 * @param dateOfBirthTable contains an DateOfBirthTable object containing the DOB information.
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public void setDateOfBirth(DateOfBirthTable dateOfBirthTable) throws JSONException, ParseException  {
		final DateOfBirth bean = dateOfBirthTable.getDateOfBirthBean();
		final Properties props = CprProperties.INSTANCE.getProperties();
		DateFormat df = new SimpleDateFormat(props.getProperty(CprPropertyName.CPR_FORMAT_RAW_DATE.toString()));
		final Date d = df.parse(bean.getDobChar());
		if (bean.getDobChar().endsWith("0000")) {
			df = new SimpleDateFormat(props.getProperty(CprPropertyName.CPR_FORMAT_PARTIAL_DATE.toString()));
		}
		else {
			df = new SimpleDateFormat(props.getProperty(CprPropertyName.CPR_FORMAT_DATE.toString()));
		}
		setValue(MessageKeyName.DATE_OF_BIRTH, df.format(d));
	}
	
	/**
	 * This routine is used to set the email address portion of a message.
	 * @param emailAddressTable contains an EmailAddressTable object containing the email address information.
	 * @throws JSONException 
	 */
	public void setEmailAddress(EmailAddressTable emailAddressTable) throws JSONException  {
		setValue(MessageKeyName.EMAIL_ADDRESS_TYPE, emailAddressTable.getEmailAddressType().toString());
		if (! getServiceName().equals(CprServiceName.ArchiveEmailAddress.toString())) {
			setValue(MessageKeyName.EMAIL_ADDRESS, emailAddressTable.getEmailAddressBean().getEmailAddress());
		}
	}
	
	/**
	 * This routine is used to set the gender information portion of a message.
	 * @param perGenderRelTable contains an PerGenderRelTable object containing the gender information.
	 * @throws JSONException 
	 */
	public void setGender(PersonGenderTable perGenderRelTable) throws JSONException {
		setValue(MessageKeyName.GENDER, perGenderRelTable.getGenderType().toString());
	}
	
	/**
	 * This routine is used to set the confidentiality information portion of a message.
	 * @param confidentialityTable contains a confidentiality table reference.
	 * @throws JSONException 
	 */
	public void setConfidentiality(ConfidentialityTable confidentialityTable) throws JSONException {
		setValue(MessageKeyName.CONFIDENTIALITY_TYPE, confidentialityTable.getConfidentialityType().toString());
	}
	
	/**
	 * This routine is used to set the affiliation information portion of a message.
	 * @param affiliationsTable contains an AffiliationsTable object containing the affiliation information.
	 * @throws JSONException 
	 */
	public void setAffiliation(PersonAffiliationTable affiliationsTable) throws JSONException {
		setValue(MessageKeyName.PSU_AFFILIATION, affiliationsTable.getAffiliationsType().toString());
		setValue(MessageKeyName.PRIMARY, affiliationsTable.getPersonAffiliationBean().getPrimaryFlag().toString());
	}	
	
	/**
	 * This routine is used to set the user comment portion of a message.
	 * @param userCommentTable contains a UserCommentTable object containing the name information.
	 * @throws JSONException 
	 */
	public void setUserComment( UserCommentTable userCommentTable) throws JSONException {
		setValue(MessageKeyName.USER_COMMENT_TYPE, userCommentTable.getUserCommentType().toString());
		if (! getServiceName().equals(CprServiceName.ArchiveUserComment.toString())) { 
			final UserComments bean = userCommentTable.getUserCommentsBean();
			setValue(MessageKeyName.COMMENTS, bean.getComments());
			setValue(MessageKeyName.USERID, bean.getUserid());
		}
	}
	
	/**
	 * This routine is used to set the user comment portion of a message.
	 * @param idCardTable contains a UserCommentTable object containing the name information.
	 * @throws JSONException 
	 */
	public void setPersonIdCard(IdCardTable idCardTable) throws JSONException {
		setValue(MessageKeyName.ID_CARD_TYPE, idCardTable.getIdCardType().toString());
		final PersonIdCard bean = idCardTable.getPersonIdCardBean();
		setValue(MessageKeyName.ID_CARD_NUMBER, bean.getIdCardNumber());
		setValue(MessageKeyName.ID_SERIAL_NUMBER, bean.getIdSerialNumber());
	}
}
