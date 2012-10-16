/* SVN FILE: $Id: PersonServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.returns;

import edu.psu.iam.cpr.core.service.returns.AddressReturn;
import edu.psu.iam.cpr.core.service.returns.AffiliationReturn;
import edu.psu.iam.cpr.core.service.returns.DateOfBirthReturn;
import edu.psu.iam.cpr.core.service.returns.EmailAddressReturn;
import edu.psu.iam.cpr.core.service.returns.GenderReturn;
import edu.psu.iam.cpr.core.service.returns.MatchReturn;
import edu.psu.iam.cpr.core.service.returns.NameReturn;
import edu.psu.iam.cpr.core.service.returns.PersonReturn;
import edu.psu.iam.cpr.core.service.returns.PhoneReturn;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.service.returns.UseridReturn;

/**
 * Provides the implementation of the PersonServiceReturn class.
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
 * @package edu.psu.iam.cpr.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public class PersonServiceReturn {
	
	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/**  Status message returned as the result of executing this service. */
	private String statusMessage;

	/**  Contains the number of addresses returned from a get. */
	private int numberOfAddresses = 0;
	
	/** Contains the address return data. */
	private AddressReturn addressReturnRecord[] = null;
	
	/** Contains the number of affiliations returned from a get. */
	private int numberOfAffiliations = 0;
	
	/** Contains the affiliations data.  */
	private AffiliationReturn affiliationReturnRecord[] = null;
	
	/** Contains the number of date of birth records */
	private int numberOfDateOfBirthdays = 0;
	
	/** Contains the date of birth information. */
	private DateOfBirthReturn dateOfBirthRecord[] = null;
	
	/** Contains the number of email addresses returned from a get. */
	private int numberOfEmailAddresses = 0;
	
	/** Contains the email address information. */
	private EmailAddressReturn emailAddressReturnRecord[] = null;
	
	/** Contains the number of genders */
	private int numberOfGenders = 0;
	
	/** Contains the gender information. */
	private GenderReturn genderReturnRecord[] = null;
	
	/** Contains the number of names returned from a get. */
	private int numberOfNames = 0;
	
	/** Contains the name information. */
	private NameReturn nameReturnRecord[] = null;
	
	/** Contains the person return information. */
	private PersonReturn personReturn = null;
	
	/** Contains the number of phones returned as the result of a get. */
	private int numberOfPhones = 0;
	
	/** Contains the phones data. */
	private PhoneReturn phoneReturnRecord[] = null;
	
	/** Contains the number of PSU IDs */
	private int numberOfPsuIds = 0;
	
	/** Contains the psu id data. */
	private PsuIdReturn psuIdReturnRecord[] = null;
	
	/** Contains the number of userids returned as the result of a get. */
	private int numberOfUserids = 0;
	
	/** Contains the userid. */
	private UseridReturn useridReturnRecord[] = null;
	
	/** Contains the number of matches that the match process has found. */
	private int numberofNearMatches = 0;
	
	/** Contains an array of matches. */
	private MatchReturn nearMatchReturnRecord[] = null;
	
	/** Contains the exact match information */
	private MatchReturn exactMatchReturn = null;
	
	/**
	 * Constructor.
	 */
	public PersonServiceReturn() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param statusCode contains the status code as the result of executing the service.
	 * @param statusMessage contains the status message returned to the user.
	 */
	public PersonServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the numberOfAddresses
	 */
	public int getNumberOfAddresses() {
		return numberOfAddresses;
	}

	/**
	 * @param numberOfAddresses the numberOfAddresses to set
	 */
	public void setNumberOfAddresses(int numberOfAddresses) {
		this.numberOfAddresses = numberOfAddresses;
	}

	/**
	 * @return the addressReturnRecord
	 */
	public AddressReturn[] getAddressReturnRecord() {
		return addressReturnRecord;
	}

	/**
	 * @param addressReturnRecord the addressReturnRecord to set
	 */
	public void setAddressReturnRecord(AddressReturn[] addressReturnRecord) {
		this.addressReturnRecord = addressReturnRecord;
	}

	/**
	 * @return the numberOfAffiliations
	 */
	public int getNumberOfAffiliations() {
		return numberOfAffiliations;
	}

	/**
	 * @param numberOfAffiliations the numberOfAffiliations to set
	 */
	public void setNumberOfAffiliations(int numberOfAffiliations) {
		this.numberOfAffiliations = numberOfAffiliations;
	}

	/**
	 * @return the affiliationReturnRecord
	 */
	public AffiliationReturn[] getAffiliationReturnRecord() {
		return affiliationReturnRecord;
	}

	/**
	 * @param affiliationReturnRecord the affiliationReturnRecord to set
	 */
	public void setAffiliationReturnRecord(
			AffiliationReturn[] affiliationReturnRecord) {
		this.affiliationReturnRecord = affiliationReturnRecord;
	}

	/**
	 * @return the numberOfEmailAddresses
	 */
	public int getNumberOfEmailAddresses() {
		return numberOfEmailAddresses;
	}

	/**
	 * @param numberOfEmailAddresses the numberOfEmailAddresses to set
	 */
	public void setNumberOfEmailAddresses(int numberOfEmailAddresses) {
		this.numberOfEmailAddresses = numberOfEmailAddresses;
	}

	/**
	 * @return the emailAddressReturnRecord
	 */
	public EmailAddressReturn[] getEmailAddressReturnRecord() {
		return emailAddressReturnRecord;
	}

	/**
	 * @param emailAddressReturnRecord the emailAddressReturnRecord to set
	 */
	public void setEmailAddressReturnRecord(
			EmailAddressReturn[] emailAddressReturnRecord) {
		this.emailAddressReturnRecord = emailAddressReturnRecord;
	}

	/**
	 * @return the numberOfNames
	 */
	public int getNumberOfNames() {
		return numberOfNames;
	}

	/**
	 * @param numberOfNames the numberOfNames to set
	 */
	public void setNumberOfNames(int numberOfNames) {
		this.numberOfNames = numberOfNames;
	}

	/**
	 * @return the nameReturnRecord
	 */
	public NameReturn[] getNameReturnRecord() {
		return nameReturnRecord;
	}

	/**
	 * @param nameReturnRecord the nameReturnRecord to set
	 */
	public void setNameReturnRecord(NameReturn[] nameReturnRecord) {
		this.nameReturnRecord = nameReturnRecord;
	}

	/**
	 * @return the personReturn
	 */
	public PersonReturn getPersonReturn() {
		return personReturn;
	}

	/**
	 * @param personReturn the personReturn to set
	 */
	public void setPersonReturn(PersonReturn personReturn) {
		this.personReturn = personReturn;
	}

	/**
	 * @return the numberOfPhones
	 */
	public int getNumberOfPhones() {
		return numberOfPhones;
	}

	/**
	 * @param numberOfPhones the numberOfPhones to set
	 */
	public void setNumberOfPhones(int numberOfPhones) {
		this.numberOfPhones = numberOfPhones;
	}

	/**
	 * @return the phoneReturnRecord
	 */
	public PhoneReturn[] getPhoneReturnRecord() {
		return phoneReturnRecord;
	}

	/**
	 * @param phoneReturnRecord the phoneReturnRecord to set
	 */
	public void setPhoneReturnRecord(PhoneReturn[] phoneReturnRecord) {
		this.phoneReturnRecord = phoneReturnRecord;
	}

	/**
	 * @return the numberOfUserids
	 */
	public int getNumberOfUserids() {
		return numberOfUserids;
	}

	/**
	 * @param numberOfUserids the numberOfUserids to set
	 */
	public void setNumberOfUserids(int numberOfUserids) {
		this.numberOfUserids = numberOfUserids;
	}

	/**
	 * @return the useridReturnRecord
	 */
	public UseridReturn[] getUseridReturnRecord() {
		return useridReturnRecord;
	}

	/**
	 * @param useridReturnRecord the useridReturnRecord to set
	 */
	public void setUseridReturnRecord(UseridReturn[] useridReturnRecord) {
		this.useridReturnRecord = useridReturnRecord;
	}

	/**
	 * @param numberofNearMatches the numberOfMatches to set
	 */
	public void setNumberofNearMatches(int numberofNearMatches) {
		this.numberofNearMatches = numberofNearMatches;
	}

	/**
	 * @return the numberofNearMatches
	 */
	public int getNumberofNearMatches() {
		return numberofNearMatches;
	}

	/**
	 * @param nearMatchReturnRecord the nearMatchReturnRecord to set
	 */
	public void setNearMatchReturnRecord(MatchReturn nearMatchReturnRecord[]) {
		this.nearMatchReturnRecord = nearMatchReturnRecord;
	}

	/**
	 * @return the nearMatchReturnRecord
	 */
	public MatchReturn[] getNearMatchReturnRecord() {
		return nearMatchReturnRecord;
	}

	/**
	 * @return the numberOfDateOfBirthdays
	 */
	public int getNumberOfDateOfBirthdays() {
		return numberOfDateOfBirthdays;
	}

	/**
	 * @param numberOfDateOfBirthdays the numberOfDateOfBirthdays to set
	 */
	public void setNumberOfDateOfBirthdays(int numberOfDateOfBirthdays) {
		this.numberOfDateOfBirthdays = numberOfDateOfBirthdays;
	}

	/**
	 * @return the dateOfBirthRecord
	 */
	public DateOfBirthReturn[] getDateOfBirthRecord() {
		return dateOfBirthRecord;
	}

	/**
	 * @param dateOfBirthRecord the dateOfBirthRecord to set
	 */
	public void setDateOfBirthRecord(DateOfBirthReturn[] dateOfBirthRecord) {
		this.dateOfBirthRecord = dateOfBirthRecord;
	}

	/**
	 * @return the numberOfGenders
	 */
	public int getNumberOfGenders() {
		return numberOfGenders;
	}

	/**
	 * @param numberOfGenders the numberOfGenders to set
	 */
	public void setNumberOfGenders(int numberOfGenders) {
		this.numberOfGenders = numberOfGenders;
	}

	/**
	 * @return the genderReturnRecord
	 */
	public GenderReturn[] getGenderReturnRecord() {
		return genderReturnRecord;
	}

	/**
	 * @param genderReturnRecord the genderReturnRecord to set
	 */
	public void setGenderReturnRecord(GenderReturn[] genderReturnRecord) {
		this.genderReturnRecord = genderReturnRecord;
	}

	/**
	 * @return the numberOfPsuIds
	 */
	public int getNumberOfPsuIds() {
		return numberOfPsuIds;
	}

	/**
	 * @param numberOfPsuIds the numberOfPsuIds to set
	 */
	public void setNumberOfPsuIds(int numberOfPsuIds) {
		this.numberOfPsuIds = numberOfPsuIds;
	}

	/**
	 * @return the psuIdReturnRecord
	 */
	public PsuIdReturn[] getPsuIdReturnRecord() {
		return psuIdReturnRecord;
	}

	/**
	 * @param psuIdReturnRecord the psuIdReturnRecord to set
	 */
	public void setPsuIdReturnRecord(PsuIdReturn[] psuIdReturnRecord) {
		this.psuIdReturnRecord = psuIdReturnRecord;
	}

	/**
	 * @param exactMatchReturn the exactMatchReturn to set
	 */
	public void setExactMatchReturn(MatchReturn exactMatchReturn) {
		this.exactMatchReturn = exactMatchReturn;
	}

	/**
	 * @return the exactMatchReturn
	 */
	public MatchReturn getExactMatchReturn() {
		return exactMatchReturn;
	}

	/**
	 * @return a string value 
	 */
	public String toString() {
		return "";
	}
}
