/* SVN FILE: $Id: JsonMessage.java 7981 2013-09-06 13:28:11Z smc1 $ */
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
import edu.psu.iam.cpr.core.database.beans.Confidentiality;
import edu.psu.iam.cpr.core.database.beans.DateOfBirth;
import edu.psu.iam.cpr.core.database.beans.EmailAddress;
import edu.psu.iam.cpr.core.database.beans.Employee;
import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.beans.PersonGender;
import edu.psu.iam.cpr.core.database.beans.PersonIdCard;
import edu.psu.iam.cpr.core.database.beans.Phones;
import edu.psu.iam.cpr.core.database.beans.PsuDirectory;
import edu.psu.iam.cpr.core.database.beans.PsuId;
import edu.psu.iam.cpr.core.database.beans.Student;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicCollege;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicDepartment;
import edu.psu.iam.cpr.core.database.beans.StudentMajor;
import edu.psu.iam.cpr.core.database.beans.UserComments;
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.tables.IdCardTable;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.tables.PsuDirectoryTable;
import edu.psu.iam.cpr.core.database.tables.PsuIdTable;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.database.types.AccessAccountStatusType;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.MessageKeyName;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.database.types.PhoneType;

import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Utility;

/**
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
 * @author $Author: smc1 $
 * @version $Rev: 7981 $
 * @lastrevision $Date: 2013-09-06 09:28:11 -0400 (Fri, 06 Sep 2013) $
 */
public class JsonMessage {


	private static final String VALUE_NO = "NO";
	private static final String VALUE_YES = "YES";
	private static final int CAMPUS_CODE = 0;
	private static final int ACADEMIC_LEVEL =1;
	private static final int SEMESTER_STANDING=2;
	private static final int STUDENT_STATUS =3;
	private static final int CLASS_LOAD=4;
	private static final int ACADEMIC_COLLEGE=5;
	private static final int ACADEMIC_DEPARTMENT=6;
	private static final int MAJOR=7;
	private static final int JOB_TITLE=1;
	private static final int SPECIAL_STATUS=2;
	private static final int CLASS_CODE =3;
	private static final int STATUS_CODE=4;
	private static final int LAST_PAID = 5;
	private static final int APPT_CODE =6;
	private static final int DEPARTMENT=7;
	private static final int PAY_FREQUENCY = 8;


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
	public JsonMessage(Database db, long personId, String serviceName, String requestedBy) throws JSONException, CprException {

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
	 * This routine os the constructor for change notifications
	 * @param personId contains the personId
	 * @param psuId contains the psuId
	 * @param userid contains the userid
	 * @param changeKey contains the type of change
	 * @param requestedBy contains the requestor (batch processing data source)
	 * @throws JSONException
	 */
	public JsonMessage( Long personId, String psuId, String userid, String changeKey, String requestedBy) throws JSONException {

		jsonObject = new JSONObject();
		setJsonObject(jsonObject);
		setRequestedBy(requestedBy);

		// Check the person id is active

		if (personId != null) {
			setValue(MessageKeyName.PERSON_ID, personId);
		}
		if (psuId != null) {
			setValue(MessageKeyName.PSU_ID,psuId);
		}
		else
		{
			setValue(MessageKeyName.PSU_ID, (String) null);
		}

		if (userid != null) {
			setValue(MessageKeyName.USERID, userid);
		}
		else
		{
			setValue(MessageKeyName.USERID, (String) null);
		}
		setValue(MessageKeyName.CHANGE_KEY, changeKey);
		setValue(MessageKeyName.REQUESTED_BY, requestedBy);
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
	 *
	 * his routine is used to set a key/value for a JSON Object portion of a JSON message.
	 * @param key contains the key.
	 * @param value  contains the value
	 * @throws JSONException exception will be thrown if there are any problems.
	 */
	public final void setValue(MessageKeyName key, JSONObject value) throws JSONException {

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
	 * This method is used to return a true/false value that indicates whether a value is set for a particular JSON component.
	 * @param key contains the key to be checked.
	 * @return will return true if the key is set, otherwise it will return false.
	 */
	public final boolean isValueSet(MessageKeyName key)  {
		try {
			getJsonObject().get(key.toString());
			return true;
		}
		catch (JSONException e) {
			return false;
		}
	}

	/**
	 * This routine is used to set a key/value for a string value portion of a JSON message.
	 * @param tempJsonObject contains jsonObject
	 * @param key contains the key.
	 * @param value contains the value.
	 * @throws JSONException exception will be thrown if there are any problems.
	 */
	public final void setValue(JSONObject tempJsonObject,MessageKeyName key, String value) throws JSONException {

		// If the value is NULL, we need to set a special JSON value to indicate NULL.
		if (value == null) {
			tempJsonObject.put(key.toString(), JSONObject.NULL);
		}

		// Otherwise set the value.
		else {
			tempJsonObject.put(key.toString(), value);
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
	 * This routine is used to set a key/value for a integer portion of a JSON message.
	 * @param tempJsonObject  the jsonObject
	 * @param key contains the key.
	 * @param value contains the value.
	 * @throws JSONException exception will be thrown if there are any problems.
	 */
	public final void setValue(JSONObject tempJsonObject, MessageKeyName key, Long value) throws JSONException {
		tempJsonObject.put(key.toString(), Long.toString(value));
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
	public void setName(NamesTable namesTable) throws JSONException   {
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
	 * This routine creates the OLD_NAME and NEW_NAME JSON data for an name change
	 * @param oldName - Names bean containing the old name ( may be null)
	 * @param newName - Names bean containing the new name ( may be null)
	 * @throws JSONException
	 */
	public void setNameChange(Names oldName, Names newName) throws JSONException  {
		if (oldName == null && newName == null) {
			return;
		}
		JSONObject oldNameObject = null;
		JSONObject newNameObject= null;
		if(newName != null) {
			if (oldName != null) {
				setValue(MessageKeyName.NAME_TYPE, NameType.get(oldName.getDataTypeKey()).toString());
				oldNameObject = setName(oldName);
				setValue(MessageKeyName.OLD_NAME, oldNameObject);
			}
			else
			{
				setValue(MessageKeyName.NAME_TYPE, NameType.get(newName.getDataTypeKey()).toString());
				setValue(MessageKeyName.OLD_NAME, (String)null);
			}
			newNameObject = setName(newName);
			setValue(MessageKeyName.NEW_NAME, newNameObject);

		}
		else
		{
			setValue(MessageKeyName.NAME_TYPE, NameType.get(oldName.getDataTypeKey()).toString());
			oldNameObject = setName(oldName);
			setValue(MessageKeyName.OLD_NAME, oldNameObject);
			setValue(MessageKeyName.NEW_NAME, (String) null);
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
	 * This routine creates the OLD_PHONE and NEW_PHONE JSON data for an phone change
	 * @param oldPhone - Phones bean containing the old phone ( may be null)
	 * @param newPhone - Phones bean containing the new phone ( may be null)
	 * @throws JSONException
	 */
	public void setPhoneChange(Phones oldPhone, Phones newPhone) throws JSONException  {
		if (oldPhone == null && newPhone == null) {
			return;
		}
		JSONObject oldPhoneObject = null;
		JSONObject newPhoneObject= null;
		if(newPhone != null) {
			if (oldPhone != null) {
				setValue(MessageKeyName.PHONE_TYPE, PhoneType.get(oldPhone.getDataTypeKey()).toString());
				oldPhoneObject = setPhone(oldPhone);
				setValue(MessageKeyName.OLD_PHONE_NUMBER, oldPhoneObject);
			}
			else
			{
				setValue(MessageKeyName.PHONE_TYPE, PhoneType.get(newPhone.getDataTypeKey()).toString());
				setValue(MessageKeyName.OLD_PHONE_NUMBER, (String)null);
			}
			newPhoneObject = setPhone(newPhone);
			setValue(MessageKeyName.NEW_PHONE_NUMBER, newPhoneObject);

		}
		else
		{
			setValue(MessageKeyName.PHONE_TYPE, PhoneType.get(oldPhone.getDataTypeKey()).toString());
			oldPhoneObject = setPhone(oldPhone);
			setValue(MessageKeyName.OLD_PHONE_NUMBER, oldPhoneObject);
			setValue(MessageKeyName.NEW_PHONE_NUMBER, (String) null);
		}
	}
	/**
	 * This routines sets the PS Campus change data
	 * @param oldPSCampus contains the old PS Campus
	 * @param newPSCampus contains the new PS Campus
	 * @throws JSONException
	 */
	public void setPSCampusChange(String oldPSCampus, String newPSCampus) throws JSONException  {
		if (oldPSCampus == null && newPSCampus == null)
		{
			return;
		}
		if (newPSCampus != null) {
			if (oldPSCampus != null) {
				setValue(MessageKeyName.OLD_PS_CAMPUS, oldPSCampus);
			}
			else
			{
				setValue(MessageKeyName.OLD_PS_CAMPUS, (String)null);
			}
			setValue(MessageKeyName.NEW_PS_CAMPUS,newPSCampus);
		}
		else
		{
			setValue(MessageKeyName.OLD_PS_CAMPUS, oldPSCampus);
			setValue(MessageKeyName.NEW_PS_CAMPUS, (String)null);
		}

	}
	/**
	 * This routine sets the PS department change date
	 * @param oldPSDepartment contains the old PS Department
	 * @param newPSDepartment contains the new PS Department
	 * @throws JSONException
	 */
	public void setPSDepartmentChange(String oldPSDepartment, String newPSDepartment) throws  JSONException  {
		if (oldPSDepartment == null && newPSDepartment == null)
		{
			return;
		}
		if (newPSDepartment != null) {
			if (oldPSDepartment != null) {
				setValue(MessageKeyName.OLD_PS_DEPARTMENT, oldPSDepartment);
			}
			else
			{
				setValue(MessageKeyName.OLD_PS_DEPARTMENT, (String)null);
			}
			setValue(MessageKeyName.NEW_PS_DEPARTMENT,newPSDepartment);
		}
		else
		{
			setValue(MessageKeyName.OLD_PS_DEPARTMENT, oldPSDepartment);
			setValue(MessageKeyName.NEW_PS_DEPARTMENT, (String)null);
		}

	}
	/**
	 * This routine set the PSUID change data
	 * @param oldPsuId contains the old PsuId
	 * @param newPsuId contains the new Psuid
	 * @throws JSONException
	 */
	public void setPsuIdChange(PsuId oldPsuId, PsuId newPsuId) throws  JSONException  {
		if (oldPsuId == null && newPsuId == null)
		{
			return;
		}
		if (newPsuId != null) {
			if (oldPsuId != null) {
				setValue(MessageKeyName.OLD_PSUID, oldPsuId.getPsuId());
			}
			else
			{
				setValue(MessageKeyName.OLD_PSUID, (String)null);
			}
			setValue(MessageKeyName.NEW_PSUID,newPsuId.getPsuId());
		}
		else
		{
			setValue(MessageKeyName.OLD_PSUID, oldPsuId.getPsuId());
			setValue(MessageKeyName.NEW_PSUID, (String)null);
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
	 * This routine creates the OLD_ADDRESS and NEW_ADDRESS JSON data for an address change
	 * @param oldAddress - Address bean containing the old address ( may be null)
	 * @param oldCampusCode - old campus code ( may be null)
	 * @param oldCountryCode - old country code ( may be null)
	 * @param newAddress - Address bean containing the new address ( may be null)
	 * @param newCampusCode - new campus code ( may be null)
	 * @param newCountryCode - new country code ( may be null)
	 * @throws JSONException
	 */
	public void setAddressChange(Addresses oldAddress, String oldCampusCode, String oldCountryCode, Addresses newAddress,
			String newCampusCode, String newCountryCode) throws JSONException  {

		if (oldAddress == null && newAddress == null) {
			return;
		}
		JSONObject oldAddressObject = null;
		JSONObject newAddressObject= null;
		if(newAddress != null) {
			if (oldAddress != null) {
				setValue(MessageKeyName.ADDRESS_TYPE,AddressType.get(oldAddress.getDataTypeKey()).toString());
				oldAddressObject = setAddress(oldAddress, oldCampusCode, oldCountryCode);
				setValue(MessageKeyName.OLD_ADDRESS, oldAddressObject);
			}
			else
			{
				setValue(MessageKeyName.ADDRESS_TYPE, AddressType.get(newAddress.getDataTypeKey()).toString());
				setValue(MessageKeyName.OLD_ADDRESS, (String)null);
			}
			newAddressObject = setAddress(newAddress, newCampusCode, newCountryCode);
			setValue(MessageKeyName.NEW_ADDRESS, newAddressObject);

		}
		else
		{
			setValue(MessageKeyName.ADDRESS_TYPE,  AddressType.get(oldAddress.getDataTypeKey()).toString());
			oldAddressObject = setAddress(oldAddress, oldCampusCode, oldCountryCode);
			setValue(MessageKeyName.OLD_ADDRESS, oldAddressObject);
			setValue(MessageKeyName.NEW_ADDRESS, (String) null);
		}

	}

	/**
	 * This routine is used to set the userid portion of a message.
	 * @param useridTable contains the UseridTable object containing the userid information.
	 * @throws JSONException

	 */
	public void setUserid(UseridTable useridTable) throws JSONException {

		setValue(MessageKeyName.USERID, useridTable.getUseridBean().getUserid());


	}

	/**
	 * This routine is used to set the date of birth portion of a message.
	 * @param dateOfBirthTable contains an DateOfBirthTable object containing the DOB information.
	 * @throws JSONException
	 * @throws ParseException

	 */
	public void setDateOfBirth(DateOfBirthTable dateOfBirthTable) throws JSONException, ParseException {

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
	 * This routine used to set the date of birth change portion of a message.
	 * @param oldDateOfBirth contains the old DateOfBirth bean
	 * @param newDateOfBirth contains the new DateOfBirth bean
	 * @throws JSONException
	 */
	public void setDateOfBirthChange(DateOfBirth oldDateOfBirth, DateOfBirth newDateOfBirth) throws  JSONException  {
		if (oldDateOfBirth == null && newDateOfBirth == null)
		{
			return;
		}
		if (newDateOfBirth != null) {
			if (oldDateOfBirth != null) {
				setValue(MessageKeyName.OLD_DATE_OF_BIRTH, oldDateOfBirth.getDobChar());

			}
			else
			{
				setValue(MessageKeyName.OLD_DATE_OF_BIRTH, (String)null);
			}
			setValue(MessageKeyName.NEW_DATE_OF_BIRTH,newDateOfBirth.getDobChar());
		}
		else
		{

			setValue(MessageKeyName.OLD_DATE_OF_BIRTH, oldDateOfBirth.getDobChar());
			setValue(MessageKeyName.NEW_DATE_OF_BIRTH, (String)null);
		}
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
	 * This routine used to set the email address change portion of a message.
	 * @param oldEmailAddress contains the old EmailAddress bean
	 * @param newEmailAddress contains the new EmailAddress bean
	 * @throws JSONException
	 */
	public void setEmailAddressChange(EmailAddress oldEmailAddress, EmailAddress newEmailAddress) throws  JSONException  {
		if (oldEmailAddress == null && newEmailAddress == null)
		{
			return;
		}
		if (newEmailAddress != null) {
			if (oldEmailAddress != null) {
				setValue(MessageKeyName.EMAIL_ADDRESS_TYPE, EmailAddressType.get(oldEmailAddress.getDataTypeKey()).toString());
				setValue(MessageKeyName.OLD_EMAIL_ADDRESS, oldEmailAddress.getEmailAddress());

			}
			else
			{
				setValue(MessageKeyName.EMAIL_ADDRESS_TYPE, EmailAddressType.get(newEmailAddress.getDataTypeKey()).toString());
				setValue(MessageKeyName.OLD_EMAIL_ADDRESS, (String)null);
			}
			setValue(MessageKeyName.NEW_EMAIL_ADDRESS,newEmailAddress.getEmailAddress());
		}
		else
		{

			setValue(MessageKeyName.EMAIL_ADDRESS_TYPE, EmailAddressType.get(oldEmailAddress.getDataTypeKey()).toString());
			setValue(MessageKeyName.OLD_EMAIL_ADDRESS, oldEmailAddress.getEmailAddress());
			setValue(MessageKeyName.NEW_EMAIL_ADDRESS, (String)null);
		}
	}
	/**
	 * Send an AddEmployee Notification
	 * @param newName contains the name
	 * @param newAddress contains the address
	 * @param newAddressCampusCode contains the address campus code
	 * @param newAddressCountryCode contains the address country code
	 * @param newPhone contains the phone
	 * @param newEmailAddress contains the emailAddress
	 * @param newAffiliation contains the affiliation
	 * @param newGender contains the gender
	 * @param newConfidentialty contain confidentiality
	 * @param newEmployee contains employee data
	 * @param newEmployeeCampusCode contains campus code
	 * @throws JSONException
	 */
	public void setEmployeeAdd(Names newName, Addresses newAddress, String newAddressCampusCode, String newAddressCountryCode,
			Phones newPhone,EmailAddress newEmailAddress,PersonAffiliation newAffiliation,
			PersonGender newGender, Confidentiality newConfidentialty,
			Employee newEmployee, String newEmployeeCampusCode) throws JSONException {

		JSONObject newEmployeeObject= null;
		setBioData (newName, newAddress, newAddressCampusCode, newAddressCountryCode, newPhone, newEmailAddress, newAffiliation,
			 newGender,  newConfidentialty);

		 if (newEmployee != null)  {
		boolean[] employeeChanges = {true, true, true, true, true, true, true, true, true};
		newEmployeeObject = setEmployeeData(employeeChanges,newEmployee, newEmployeeCampusCode);
		setValue(MessageKeyName.NEW_EMPLOYEE_DATA, newEmployeeObject);
		 }
	}

	/**
	 * This routine set the Employee data change message
	 * @param oldEmployee contains the old employee data
	 * @param oldCampusCode contains the old campus data
	 * @param newEmployee contains the new employee data
	 * @param newCampusCode contains the new campus location
	 * @throws JSONException
	 * @throws CprException
	 */
	public void setEmployeeChange(Employee oldEmployee, String oldCampusCode,  Employee newEmployee, String newCampusCode) throws JSONException, CprException  {

		if (oldEmployee == null && newEmployee == null) {
			return;
		}
		JSONObject oldEmployeeObject = null;
		JSONObject newEmployeeObject= null;
		boolean[] employeeChanges = {true, true, true, true, true, true, true, true, true};
		boolean changesFound = false;
		if (oldCampusCode != null) {
			if (newCampusCode != null) {

				if (Utility.areStringFieldsEqual(oldCampusCode, newCampusCode)) {
					employeeChanges[CAMPUS_CODE]=false;
				}
				else
				{
					changesFound = true;
				}
			}
			else
			{
				changesFound = true;
			}
		}
		else
		{
			if (newCampusCode != null) {
				changesFound = true;
			}
			else
			{
				employeeChanges[CAMPUS_CODE]=false;
			}
		}
		if(oldEmployee != null) {
			if (newEmployee != null) {

				if (Utility.areStringFieldsEqual(oldEmployee.getJobTitle(), newEmployee.getJobTitle())) {
					employeeChanges[JOB_TITLE]=false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldEmployee.getApptCode(), newEmployee.getApptCode())) {
					employeeChanges[APPT_CODE]=false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldEmployee.getClassCode(), newEmployee.getClassCode())) {
					employeeChanges[CLASS_CODE]=false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldEmployee.getStatusCode(), newEmployee.getStatusCode())) {
					employeeChanges[STATUS_CODE]=false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areDateFieldsEqual(oldEmployee.getLastDatePaid(), newEmployee.getLastDatePaid())) {
					employeeChanges[LAST_PAID]=false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldEmployee.getSpecialStatus(), newEmployee.getSpecialStatus())){
					employeeChanges[SPECIAL_STATUS]=false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldEmployee.getDepartment(), newEmployee.getDepartment())){
					employeeChanges[DEPARTMENT]=false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldEmployee.getPayFreqCode(), newEmployee.getPayFreqCode())){
					employeeChanges[PAY_FREQUENCY]=false;
				}
				else
				{
					changesFound = true;
				}
			}
			else
			{
				changesFound = true;
			}

		}
		else
		{
			if (newEmployee != null) {
				changesFound = true;
			}
			else {
				employeeChanges[JOB_TITLE]=false;
				employeeChanges[APPT_CODE]=false;
				employeeChanges[CLASS_CODE]=false;
				employeeChanges[STATUS_CODE]=false;
				employeeChanges[LAST_PAID]=false;
				employeeChanges[SPECIAL_STATUS]=false;
				employeeChanges[DEPARTMENT]=false;
				employeeChanges[PAY_FREQUENCY] = false;
			}
		}

		if ( changesFound) {

			if (oldEmployee == null && oldCampusCode == null) {
				setValue(MessageKeyName.OLD_EMPLOYEE_DATA, (String)null);
			}
			else
			{
				oldEmployeeObject = setEmployeeData(employeeChanges, oldEmployee, oldCampusCode);
				setValue(MessageKeyName.OLD_EMPLOYEE_DATA, oldEmployeeObject);
			}
			if (newEmployee == null && newCampusCode == null) {
				setValue(MessageKeyName.NEW_EMPLOYEE_DATA, (String)null);
			}
			else
			{
				newEmployeeObject = setEmployeeData(employeeChanges,newEmployee, newCampusCode);
				setValue(MessageKeyName.NEW_EMPLOYEE_DATA, newEmployeeObject);
			}
		}
	}
	/**
	 * This routine is used to set the gender information portion of a message.
	 * @param perGenderRelTable contains an PerGenderRelTable object containing the gender information.
	 * @throws JSONException
	 */
	public void setGender(PersonGenderTable perGenderRelTable) throws JSONException  {
		setValue(MessageKeyName.GENDER, perGenderRelTable.getGenderType().toString());

	}
	/**
	 * This routine is used to set the gender change information portion of a message.
	 * @param oldPerGender contains old Gender Bean
	 * @param newPerGender contains new Gender Bean
	 * @throws JSONException
	 */
	public void setGenderChange(PersonGender oldPerGender, PersonGender newPerGender) throws JSONException  {
		if (oldPerGender == null && newPerGender == null)
		{
			return;
		}
		if (newPerGender != null) {
			if (oldPerGender != null) {
				setValue(MessageKeyName.OLD_GENDER, GenderType.get(oldPerGender.getDataTypeKey()).toString());
			}
			else
			{
				setValue(MessageKeyName.OLD_GENDER, (String)null);
			}
			setValue(MessageKeyName.NEW_GENDER,GenderType.get(newPerGender.getDataTypeKey()).toString());
		}
		else
		{
			setValue(MessageKeyName.OLD_GENDER, GenderType.get(oldPerGender.getDataTypeKey()).toString());
			setValue(MessageKeyName.NEW_GENDER, (String)null);
		}


	}
	/**
	 * This routine is used to set the confidentiality information portion of a message.
	 * @param oldConfidentiality contains a confidentiality table reference old
	 * @param newConfidentiality contains a confidentiality table reference new
	 * @throws JSONException
	 */
	public void setConfidentialityChange(Confidentiality oldConfidentiality, Confidentiality newConfidentiality ) throws JSONException {
		if (oldConfidentiality == null) {
			setValue(MessageKeyName.OLD_CONFIDENTIALITY_STATUS, VALUE_NO);
		}
		else {
			setValue(MessageKeyName.OLD_CONFIDENTIALITY_STATUS, VALUE_YES);
		}
		if (newConfidentiality == null) {
			setValue(MessageKeyName.NEW_CONFIDENTIALITY_STATUS, VALUE_NO);
		}
		else {
			setValue(MessageKeyName.NEW_CONFIDENTIALITY_STATUS, VALUE_YES);
		}

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
	 * This routine set the json message data for primary affiliation
	 * @param oldAffiliation contains the old affiliation
	 * @param newAffiliation contains the new affiliation
	 * @throws JSONException
	 */
	public void setPrimaryAffiliationChange (PersonAffiliation oldAffiliation, PersonAffiliation newAffiliation) throws JSONException {
		if (oldAffiliation == null && newAffiliation == null) {
			return;
		}
		if (newAffiliation != null) {
			if (oldAffiliation != null) {
				setValue(MessageKeyName.OLD_PRIMARY_AFFILIATION, AffiliationsType.get(oldAffiliation.getAffiliationKey()).toString());
			}
			else
			{
				setValue(MessageKeyName.OLD_PRIMARY_AFFILIATION,(String) null);
			}
			setValue(MessageKeyName.NEW_PRIMARY_AFFILIATION,AffiliationsType.get(newAffiliation.getAffiliationKey()).toString());
		}
		else
		{

			setValue(MessageKeyName.OLD_PRIMARY_AFFILIATION,AffiliationsType.get(oldAffiliation.getAffiliationKey()).toString());
			setValue(MessageKeyName.NEW_PRIMARY_AFFILIATION, (String)null);
		}
	}
	/**
	 *
	 * This routine set the json message data for affiliation changes
	 * @param oldAffiliation contains the old affiliation
	 * @param newAffiliation contains the new affiliation
	 * @throws JSONException
	 */
	public void setAffiliationChange (PersonAffiliation oldAffiliation, PersonAffiliation newAffiliation) throws JSONException {
		if (oldAffiliation == null && newAffiliation == null) {
			return;
		}
		if (newAffiliation != null) {
			if (oldAffiliation != null) {
				setValue(MessageKeyName.OLD_AFFILIATION, AffiliationsType.get(oldAffiliation.getAffiliationKey()).toString());
				setValue(MessageKeyName.OLD_AFFILIATION_STATUS, oldAffiliation.getPrimaryFlag());
			}
			else
			{
				setValue(MessageKeyName.OLD_AFFILIATION,(String) null);
				setValue(MessageKeyName.OLD_AFFILIATION, (String) null);
			}
			setValue(MessageKeyName.NEW_AFFILIATION,AffiliationsType.get(newAffiliation.getAffiliationKey()).toString());
			setValue(MessageKeyName.NEW_AFFILIATION_STATUS, newAffiliation.getPrimaryFlag());
		}
		else
		{

			setValue(MessageKeyName.OLD_AFFILIATION,AffiliationsType.get(oldAffiliation.getAffiliationKey()).toString());
			setValue(MessageKeyName.OLD_AFFILIATION_STATUS, oldAffiliation.getPrimaryFlag());
			setValue(MessageKeyName.NEW_AFFILIATION, (String)null);
			setValue(MessageKeyName.NEW_AFFILIATION_STATUS, (String)null);
		}
	}
	/**
	 * This routine creates the json message data for a new student
	 * @param newName contains the name
	 * @param newAddress contains the address
	 * @param newAddressCampusCode contains the address campus code
	 * @param newAddressCountryCode contains the address country code
	 * @param newPhone contains the phone
	 * @param newEmailAddress contains the email address
	 * @param newAffiliation contains the affiliation
	 * @param newGender contains the gender
	 * @param newConfidentialty contains confidentiality
	 * @param newStudent contains student data
	 * @param newStudentCampusCode contains student campus code
	 * @param newAcadCollege contains academic college
	 * @param newAcadDept contains academic department
	 * @param newMajor contains major
	 * @throws JSONException
	 */
	public void setStudentAdd(Names newName, Addresses newAddress, String newAddressCampusCode, String newAddressCountryCode,
			Phones newPhone,EmailAddress newEmailAddress,PersonAffiliation newAffiliation,
			PersonGender newGender, Confidentiality newConfidentialty,
			Student newStudent, String newStudentCampusCode,StudentAcademicCollege newAcadCollege,StudentAcademicDepartment newAcadDept, StudentMajor newMajor  ) throws JSONException	 {

		JSONObject newStudentObject= null;
		setBioData (newName,newAddress,newAddressCampusCode, newAddressCountryCode, newPhone, newEmailAddress, newAffiliation,
			newGender,  newConfidentialty);


		boolean[] studentChanges = {true, true, true, true, true,true, true, true};
		setValue(MessageKeyName.SEMESTER_CODE, newStudent.getSemesterCode());
		newStudentObject = setStudentData(studentChanges,newStudent, newStudentCampusCode, newAcadCollege,
				newAcadDept, newMajor);
		setValue(MessageKeyName.NEW_STUDENT_DATA, newStudentObject);

	}
	/**
	 * This routine sets the student change message
	 * @param oldStudent contains old student data
	 * @param oldCampusCode contains old campus code for student
	 * @param oldAcadCollege contains the old academic college
	 * @param oldAcadDept contains the old academic department
	 * @param oldMajor contains the old major
	 * @param newStudent contains new student data
	 * @param newCampusCode contains new student campus code
	 * @param newAcadCollege contains the new academic college
	 * @param newAcadDept contains the new academic department
	 * @param newMajor contains the new major
	 * @throws JSONException
	 */
	public void setStudentChange(Student oldStudent, String oldCampusCode, StudentAcademicCollege oldAcadCollege,StudentAcademicDepartment oldAcadDept, StudentMajor oldMajor,  Student newStudent, String newCampusCode,
			StudentAcademicCollege newAcadCollege,StudentAcademicDepartment newAcadDept, StudentMajor newMajor) throws JSONException  {

		if (oldStudent == null && newStudent == null) {
			return;
		}
		JSONObject oldStudentObject = null;
		JSONObject newStudentObject= null;
		boolean[] studentChanges = {true, true, true, true, true, true, true, true};
		boolean changesFound = false;
		if ( oldCampusCode != null) {
			if ( newCampusCode != null) {
				if (Utility.areStringFieldsEqual(oldCampusCode, newCampusCode)) {
					studentChanges[CAMPUS_CODE] = false;
				}
				else
				{
					changesFound = true;
				}
			}
			else
			{
				changesFound = true;
			}
		}
		else
		{
			if (newCampusCode != null) {
				changesFound = true;
			}
			else
			{
			studentChanges[CAMPUS_CODE] = false;
			}
		}
		if (oldAcadCollege != null )  {
			if (newAcadCollege != null) {
				if (Utility.areStringFieldsEqual(oldAcadCollege.getCollegeCode(), newAcadCollege.getCollegeCode())){
					studentChanges[ACADEMIC_COLLEGE] = false;
				}
				else
				{
					changesFound = true;
				}
			}
			else
			{
				changesFound = true;
			}
		}
		else
		{
			if (newAcadCollege != null) {
				changesFound = true;
			}
			else
			{
			studentChanges[ACADEMIC_COLLEGE] = false;
			}
		}
		if (oldAcadDept != null ) {
			if (newAcadDept != null ) {
				if (Utility.areStringFieldsEqual(oldAcadDept.getDepartmentCode(), newAcadDept.getDepartmentCode())){
					studentChanges[ACADEMIC_DEPARTMENT] = false;
				}
				else
				{
				changesFound = true;
				}
			}
			else
			{
				changesFound = true;
			}
		}
		else
		{
			if (newAcadDept != null ) {
					changesFound = true;
				}
			else
			{
				studentChanges[ACADEMIC_DEPARTMENT] = false;
			}
		}
		if (oldMajor != null ) {
			if (newMajor != null) {
				if (Utility.areStringFieldsEqual(oldMajor.getMajorCode(), newMajor.getMajorCode())){
					studentChanges[MAJOR] = false;
				}
				else

				{
					changesFound = true;
				}
			}
			else

				{
					changesFound = true;
				}
		}
		else
		{
			if (newMajor != null) {
				changesFound = true;
			}
			else
			{
				studentChanges[MAJOR] = false;
			}
		}
		if(oldStudent != null) {
			if (newStudent != null) {

				if (Utility.areStringFieldsEqual(oldStudent.getAcademicLevel(), newStudent.getAcademicLevel())) {
					studentChanges[ACADEMIC_LEVEL] = false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldStudent.getYearTerm(), newStudent.getYearTerm())) {
					studentChanges[SEMESTER_STANDING] = false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldStudent.getRegistrationStatus(), newStudent.getRegistrationStatus())) {
					studentChanges[STUDENT_STATUS] = false;
				}
				else
				{
					changesFound = true;
				}
				if (Utility.areStringFieldsEqual(oldStudent.getClassLoad(), newStudent.getClassLoad())){
					studentChanges[CLASS_LOAD] = false;
				}
				else
				{
					changesFound = true;
				}
			}
			else {
				changesFound = true;
			}
		}
		else
		{
			if (newStudent != null) {
				changesFound = true;
			}
			else
			{
				studentChanges[ACADEMIC_LEVEL] = false;
				studentChanges[SEMESTER_STANDING] = false;
				studentChanges[STUDENT_STATUS] = false;
				studentChanges[CLASS_LOAD] = false;

			}
		}
		if ( changesFound) {
			if ( oldStudent != null)
				{
					setValue(MessageKeyName.SEMESTER_CODE, oldStudent.getSemesterCode());
				}
			else
				{
					setValue(MessageKeyName.SEMESTER_CODE, newStudent.getSemesterCode());
				}
			if (oldStudent == null && oldCampusCode ==null && oldAcadCollege == null && oldAcadDept== null &&  oldMajor == null )	{
				setValue(MessageKeyName.OLD_STUDENT_DATA, (String)null);
			}
			else
			{
				oldStudentObject = setStudentData(studentChanges,oldStudent, oldCampusCode, oldAcadCollege, oldAcadDept, oldMajor);
				setValue(MessageKeyName.OLD_STUDENT_DATA, oldStudentObject);
			}
			if (newStudent == null && newCampusCode ==null && newAcadCollege == null && newAcadDept== null &&  newMajor == null )	{
				setValue(MessageKeyName.NEW_STUDENT_DATA, (String)null);
			}
			else
			{
				newStudentObject = setStudentData(studentChanges,newStudent, newCampusCode,  newAcadCollege, newAcadDept, newMajor);
				setValue(MessageKeyName.NEW_STUDENT_DATA, newStudentObject);
			}

		}


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
	 * This routine set the Userid change data
	 * @param oldUserid contains the old userid
	 * @param newUserid contains the new userid
	 * @throws JSONException
	 */
	public void setUseridChange(Userid oldUserid, Userid newUserid) throws JSONException  {
		if (oldUserid == null && newUserid == null)
		{
			return;
		}
		if (newUserid != null) {
			if (oldUserid != null) {
				setValue(MessageKeyName.OLD_USERID, oldUserid.getUserid());
			}
			else
			{
				setValue(MessageKeyName.OLD_USERID, (String)null);
			}
			setValue(MessageKeyName.NEW_USERID,newUserid.getUserid());
		}
		else
		{
			setValue(MessageKeyName.OLD_USERID, oldUserid.getUserid());
			setValue(MessageKeyName.NEW_USERID, (String)null);
		}

	}

	/**
	 * This method is used to build the JSON message for an access account status change.
	 * @param oldAccountStatus contains the old access account status.
	 * @param newAccountStatus contains the new access account status.
	 * @throws JSONException will be thrown if the JSON message cannot be constructed.
	 */
	public void setAccountStatusChange(AccessAccountStatusType oldAccountStatus, AccessAccountStatusType newAccountStatus) throws JSONException {

		if (oldAccountStatus == null && newAccountStatus == null) {
			return;
		}
		if (newAccountStatus != null) {
			if (oldAccountStatus != null) {
				setValue(MessageKeyName.OLD_ACCOUNT_STATUS, oldAccountStatus.toString());
			}
			else {
				setValue(MessageKeyName.OLD_ACCOUNT_STATUS, (String) null);
			}
			setValue(MessageKeyName.NEW_ACCOUNT_STATUS, newAccountStatus.toString());
		}
		else {
			setValue(MessageKeyName.OLD_ACCOUNT_STATUS, oldAccountStatus.toString());
			setValue(MessageKeyName.NEW_ACCOUNT_STATUS, (String) null);
		}
	}

	/**
	 * This routine is used to set the addresses jsonObject portion of a message.
	 * @param bean contains an Addresses object containing the address information.
	 * @param campusCode contains the campus code for the address
	 * @param countryCode contains the country code for the address
	 * @return JSONObject containing the address
	 * @throws JSONException
	 */
	private JSONObject setAddress( Addresses bean, String campusCode, String countryCode) throws JSONException  {
		JSONObject anObject = new JSONObject();


		setValue(anObject, MessageKeyName.ADDRESS1, bean.getAddress1());
		setValue(anObject,MessageKeyName.ADDRESS2, bean.getAddress2());
		setValue(anObject,MessageKeyName.ADDRESS3, bean.getAddress3());
		setValue(anObject,MessageKeyName.STATE, bean.getState());
		setValue(anObject,MessageKeyName.CITY, bean.getCity());
		setValue(anObject,MessageKeyName.POSTAL_CODE, bean.getPostalCode());
		setValue(anObject,MessageKeyName.PROVINCE, bean.getProvince());
		setValue(anObject,MessageKeyName.CAMPUS_NAME, campusCode);
		setValue(anObject,MessageKeyName.COUNTRY_NAME, countryCode);

		return anObject;
	}
	/**
	 *
	 * @param aName contains name bean
	 * @param anAddress contains address bean
	 * @param anAddressCampusCode contains campus code associated with address
	 * @param anAddressCountryCode contains contrycode associated with address
	 * @param aPhone contains a phone bean
	 * @param anEmailAddress contains an email address bean
	 * @param anAffiliation contains a personAffiliation bean
	 * @param aGender contains a gender bean
	 * @param aConfidentiality contains a confidentiality bean
	 * @throws JSONException
	 */
	private void setBioData (Names aName, Addresses anAddress,String anAddressCampusCode, String anAddressCountryCode,
			Phones aPhone,EmailAddress anEmailAddress, PersonAffiliation anAffiliation,
			PersonGender aGender, Confidentiality aConfidentiality) throws JSONException {

		JSONObject aNameObject= null;
		JSONObject aAddressObject= null;
		JSONObject aPhoneObject = null;
		if (aName != null) {
			setValue(MessageKeyName.NAME_TYPE, NameType.get(aName.getDataTypeKey()).toString());
			aNameObject = setName(aName);
			setValue(MessageKeyName.NEW_NAME, aNameObject);
			}
		if (anAddress != null) {
			setValue(MessageKeyName.ADDRESS_TYPE,AddressType.get(anAddress.getDataTypeKey()).toString());
			aAddressObject = setAddress(anAddress, anAddressCampusCode, anAddressCountryCode);
			setValue(MessageKeyName.NEW_ADDRESS, aAddressObject);
		}
		if (aPhone != null) {
			setValue(MessageKeyName.PHONE_TYPE, PhoneType.get(aPhone.getDataTypeKey()).toString());
			aPhoneObject = setPhone(aPhone);
			setValue(MessageKeyName.NEW_PHONE_NUMBER, aPhoneObject);
		}
		if (anEmailAddress != null) {

			setValue(MessageKeyName.EMAIL_ADDRESS_TYPE, EmailAddressType.get(anEmailAddress.getDataTypeKey()).toString());
			setValue(MessageKeyName.NEW_EMAIL_ADDRESS, anEmailAddress.getEmailAddress());
			}
		if (anAffiliation != null){
			setValue(MessageKeyName.NEW_PRIMARY_AFFILIATION, AffiliationsType.get(anAffiliation.getAffiliationKey()).toString());

			}
		if (aGender != null) {
			setValue(MessageKeyName.NEW_GENDER, GenderType.get(aGender.getDataTypeKey()).toString());
			}
		if (aConfidentiality != null) {

			setValue(MessageKeyName.NEW_CONFIDENTIALITY_STATUS, VALUE_YES);
		}
	}
	/**
	 * This routine creates an employee_data jsonObject.  If the employee data object contains data and
	 *  the employee record is imported from HMC, the HERSHEY_DATA_STATUS is set to Y.
	 * @param dataStatus
	 * @param bean
	 * @param campusCode
	 * @return JSONObject contain employee data
	 * @throws JSONException
	 */
	private JSONObject setEmployeeData(final boolean[] dataStatus, final Employee bean, final String campusCode) throws JSONException  {
		JSONObject anObject = new JSONObject();

		if (dataStatus[JOB_TITLE] ){
			setValue(anObject, MessageKeyName.TITLE, bean.getJobTitle());
		}
		if (dataStatus[APPT_CODE]){
			setValue(anObject, MessageKeyName.APPOINTMENT_CODE, bean.getApptCode());
		}
		if (dataStatus[SPECIAL_STATUS]){
			setValue(anObject, MessageKeyName.SPECIAL_STATUS, bean.getSpecialStatus());
		}
		if (dataStatus[CLASS_CODE]){
			setValue(anObject, MessageKeyName.EMPLOYEE_CLASS, bean.getClassCode());
		}
		if (dataStatus[STATUS_CODE]){
			setValue(anObject, MessageKeyName.ESTATUS_CLASS, bean.getStatusCode());
		}
		if (dataStatus[LAST_PAID]){
			setValue(anObject, MessageKeyName.LAST_DATE_PAID, Utility.convertTimestampToString(bean.getLastDatePaid()));
		}
		if (dataStatus[CAMPUS_CODE] ){
			setValue(anObject, MessageKeyName.EMPLOYEE_CAMPUS_CODE, campusCode);
		}
		if (dataStatus[DEPARTMENT] ) {
			setValue(anObject,MessageKeyName.DEPARTMENT, bean.getDepartment());
		}
		if (dataStatus[PAY_FREQUENCY] ) {
			setValue(anObject,MessageKeyName.PAY_FREQUENCY, bean.getPayFreqCode());
		}


		if (anObject.length() == 0){
			return null;
		}
		else {
			if (Utility.areStringFieldsEqual(bean.getImportFrom(), BatchDataSource.HMC.toString())) {
				setValue(anObject,MessageKeyName.HERSHEY_DATA_STATUS, "Y");
			}
			else
			{
				setValue(anObject,MessageKeyName.HERSHEY_DATA_STATUS, "N");
			}
			return anObject;
		}
	}

	/**
	 * This routine is used to create a JSONObject containing a Name
	 * @param bean - Names bean
	 * @return - JSONObject containing the name data from the bean
	 * @throws JSONException
	 */
	private JSONObject setName(Names bean) throws JSONException   {
		JSONObject anObject = new JSONObject();
		setValue(anObject,MessageKeyName.FIRST_NAME, bean.getFirstName());
		setValue(anObject,MessageKeyName.MIDDLE_NAMES, bean.getMiddleNames());
		setValue(anObject, MessageKeyName.LAST_NAME, bean.getLastName());
		setValue(anObject, MessageKeyName.SUFFIX, bean.getSuffix());
		return anObject;

	}
	/**
	 *
	 * This routine returns a JSONObject with phone information to the caller
	 * @param bean - phone bean
	 * @return - JSON object contains phone data
	 * @throws JSONException
	 */
	private JSONObject setPhone(Phones bean) throws JSONException  {
		JSONObject anObject = new JSONObject();
		setValue(anObject, MessageKeyName.PHONE_NUMBER, bean.getPhoneNumber());
		setValue(anObject, MessageKeyName.EXTENSION, bean.getExtension());
		return anObject;
	}
	/**
	 * Create a JSONObject containing all the student data
	 * @param dataStatus  boolean array denoting items that should be added to the message
	 * @param bean contains the student data
	 * @param campusCode contains the student campus code
	 * @param anAcadCollege contains the academic college
	 * @param anAcadDept contains the academic department
	 * @param aMajor contains the major
	 * @return a JSONObject containing all the student data
	 * @throws JSONException
	 */
	private JSONObject setStudentData(final boolean[] dataStatus, final Student bean, final String campusCode, final StudentAcademicCollege anAcadCollege,
			final StudentAcademicDepartment anAcadDept, final StudentMajor aMajor) throws JSONException  {
		JSONObject anObject = new JSONObject();
		if (dataStatus[CAMPUS_CODE] ){
			if (campusCode == null) {
				setValue(anObject,  MessageKeyName.STUDENT_CAMPUS_CODE, (String)null);
			}
			else {
				setValue(anObject,  MessageKeyName.STUDENT_CAMPUS_CODE, campusCode);
			}
		}

		if (dataStatus[ACADEMIC_LEVEL] ){
			if (bean == null) {
				setValue(anObject, MessageKeyName.STUDENT_ACADEMIC_LEVEL, (String)null);

			}
			else
			{
				setValue(anObject, MessageKeyName.STUDENT_ACADEMIC_LEVEL, bean.getAcademicLevel());
			}
		}
		if (dataStatus[SEMESTER_STANDING]){
			if (bean == null) {
				setValue(anObject, MessageKeyName.STUDENT_SEMESTER_STANDING, (String)null);

			}
			else
			{
				setValue(anObject, MessageKeyName.STUDENT_SEMESTER_STANDING, bean.getYearTerm());
			}
		}
		if (dataStatus[STUDENT_STATUS] ){
			if (bean == null) {
				setValue(anObject, MessageKeyName.STUDENT_STATUS,(String)null);
			}
			else
			{
			setValue(anObject, MessageKeyName.STUDENT_STATUS,bean.getRegistrationStatus());
			}
		}
		if (dataStatus[CLASS_LOAD] ){
			if (bean == null) {
				setValue(anObject, MessageKeyName.CLASS_LOAD,(String)null);
			}
			else
			{
				setValue(anObject, MessageKeyName.CLASS_LOAD,bean.getClassLoad());
			}
		}
		if (dataStatus[ACADEMIC_COLLEGE] ){
			if (anAcadCollege == null) {
				setValue(anObject, MessageKeyName.ACADEMIC_COLLEGE, (String) null);
			}
			else
			{
				setValue(anObject, MessageKeyName.ACADEMIC_COLLEGE,anAcadCollege.getCollegeCode());
			}
		}
		if (dataStatus[ACADEMIC_DEPARTMENT]){
			if (anAcadDept == null) {
				setValue(anObject, MessageKeyName.ACADEMIC_DEPARTMENT, (String) null);

			}
			else
			{
				setValue(anObject, MessageKeyName.ACADEMIC_DEPARTMENT,anAcadDept.getDepartmentCode());
			}
		}
		if (dataStatus[MAJOR]){
			if (aMajor == null) {
				setValue(anObject, MessageKeyName.MAJOR,(String) null);
			}
			else
			{
				setValue(anObject, MessageKeyName.MAJOR,aMajor.getMajorCode());
			}
		}
		if (anObject.length() == 0){
			return null;
		}
		else {

			return anObject;
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
