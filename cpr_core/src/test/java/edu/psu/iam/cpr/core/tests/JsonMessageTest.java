/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

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
 */


import java.text.ParseException;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;

/**
 * @author cpruser
 *
 */
public class JsonMessageTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test
	public final void testJsonMessage() {
		new JsonMessage();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testJsonMessageIntStringString1() throws Exception {
		new JsonMessage(db, 1, CprServiceName.AddPerson.toString(), "cpruser");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testJsonMessageIntStringString2() throws Exception {
		openDbConnection();
		new JsonMessage(db, 1, CprServiceName.AddPerson.toString(), "cpruser");
		db.closeSession();
	}
	@Test
	public final void testJsonMessageIntStringString3() throws Exception {
		openDbConnection();
		new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		db.closeSession();
	}

	@Test
	public final void testGetJsonObject() throws Exception {
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		AssertJUnit.assertNotNull(j.getJsonObject());
		db.closeSession();
	}

	@Test
	public final void testSetServiceName() throws Exception {
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setServiceName(CprServiceName.AddPerson.toString());
		AssertJUnit.assertEquals(j.getServiceName(), CprServiceName.AddPerson.toString());
		db.closeSession();
	}

	@Test
	public final void testGetServiceName() throws Exception {
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setServiceName(CprServiceName.AddPerson.toString());
		AssertJUnit.assertEquals(j.getServiceName(), CprServiceName.AddPerson.toString());
		db.closeSession();
	}

	@Test
	public final void testGetRequestBy() throws Exception {
		openDbConnection();
		String requester = "cpruser";
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setRequestedBy(requester);
		AssertJUnit.assertEquals(j.getRequestedBy(), requester);
		db.closeSession();
	}
	
	@Test
	public final void testSetName() throws Exception {
		openDbConnection();
		NamesTable namesTable = new NamesTable(100000, NameType.LEGAL_NAME.toString(), null, "James", null, "Vuccolo", null, null, "cpruser");
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setName(namesTable);
		db.closeSession();
	}

	@Test
	public final void testSetPhone() throws Exception {
		PhonesTable phonesTable = new PhonesTable(100000, PhoneType.WORK_PHONE.toString(), null,  "8142789153", null, null, "cpruser");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setPhone(phonesTable);
		db.closeSession();

	}

	@Test
	public final void testSetAddress() throws Exception {
		AddressesTable addressesTable = new AddressesTable(100000,  AddressType.WORK_ADDRESS.toString(), null, 1L,  "cpruser", "address1", null, null,  "city", "PA", "12345", null,1L, 1L, "usa", "univeristy park", "USA");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setAddress(addressesTable);
		db.closeSession();
	}

	@Test
	public final void testSetDateOfBirth() throws Exception, ParseException {
		DateOfBirthTable dateOfBirthTable = new DateOfBirthTable(100000, "11/11/2010", "cpruser");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setDateOfBirth(dateOfBirthTable);
		db.closeSession();

	}

	@Test
	public final void testSetEmailAddress() throws Exception {
		EmailAddressTable emailAddressTable = new EmailAddressTable(100000, EmailAddressType.OTHER_EMAIL.toString(), "blah@psu.edu", "cpruser");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setEmailAddress(emailAddressTable);
		db.closeSession();
	}

	@Test
	public final void testSetGender() throws Exception {
		PersonGenderTable perGenderRelTable = new PersonGenderTable(100000, GenderType.GENDER_MALE.toString(), "cpruser");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setGender(perGenderRelTable);
		db.closeSession();
	}

	@Test
	public final void testSetAffiliation() throws Exception {
		PersonAffiliationTable affiliationsTable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_CURRENT", "cpruser");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setAffiliation(affiliationsTable);
		db.closeSession();	
	}

	@Test
	public final void testSetUserid() throws Exception {
		UseridTable useridTable = new UseridTable(100000,"cpruser");
		useridTable.getUseridBean().setUserid("cpruser");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setUserid(useridTable);
		db.closeSession();
	}
	
	@Test
	public final void testSetConfidentiality() throws Exception {
		ConfidentialityTable confidentialityTable = new ConfidentialityTable(100000L, "ALL_CONFIDENTIALITY", "cpruser");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setConfidentiality(confidentialityTable);
		db.closeSession();
	}
	
	@Test
	public final void testUserComment() throws Exception {
		UserCommentTable c = new UserCommentTable(1, "xyz123", "USER_COMMENT_MISUSE", "TEST", "cpruser" );
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "cpruser");
		j.setUserComment(c);
		db.closeSession();

	}
	
	
}
