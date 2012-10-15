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
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;

/**
 * @author jvuccolo
 *
 */
public class JsonMessageTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test
	public final void testJsonMessage() {
		new JsonMessage();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testJsonMessageIntStringString1() throws CprException, GeneralDatabaseException {
		new JsonMessage(db, 1, CprServiceName.AddPerson.toString(), "jvuccolo");
	}
	@Test(expectedExceptions=CprException.class)
	public final void testJsonMessageIntStringString2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		new JsonMessage(db, 1, CprServiceName.AddPerson.toString(), "jvuccolo");
		db.closeSession();
	}
	@Test
	public final void testJsonMessageIntStringString3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		db.closeSession();
	}

	@Test
	public final void testGetJsonObject() throws CprException, GeneralDatabaseException {
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		AssertJUnit.assertNotNull(j.getJsonObject());
		db.closeSession();
	}

	@Test
	public final void testSetServiceName() throws CprException, GeneralDatabaseException {
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setServiceName(CprServiceName.AddPerson.toString());
		AssertJUnit.assertEquals(j.getServiceName(), CprServiceName.AddPerson.toString());
		db.closeSession();
	}

	@Test
	public final void testGetServiceName() throws CprException, GeneralDatabaseException {
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setServiceName(CprServiceName.AddPerson.toString());
		AssertJUnit.assertEquals(j.getServiceName(), CprServiceName.AddPerson.toString());
		db.closeSession();
	}

	@Test
	public final void testGetRequestBy() throws CprException, GeneralDatabaseException {
		openDbConnection();
		String requester = "slk24";
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setRequestedBy(requester);
		AssertJUnit.assertEquals(j.getRequestedBy(), requester);
		db.closeSession();
	}
	
	@Test
	public final void testSetName() throws Exception {
		openDbConnection();
		NamesTable namesTable = new NamesTable(100000, NameType.LEGAL_NAME.toString(), null, "James", null, "Vuccolo", null, "jvuccolo");
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setName(namesTable);
		db.closeSession();
	}

	@Test
	public final void testSetPhone() throws Exception {
		PhonesTable phonesTable = new PhonesTable(100000, PhoneType.WORK_PHONE.toString(), null,  "8142789153", null, null, "jvuccolo");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setPhone(phonesTable);
		db.closeSession();

	}

	@Test
	public final void testSetAddress() throws Exception {
		AddressesTable addressesTable = new AddressesTable(100000,  AddressType.WORK_ADDRESS.toString(), null, 1L,  "jvuccolo", "address1", null, null,  "city", "PA", "12345", null,1L, 1L, "usa", "univeristy park", "USA");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setAddress(addressesTable);
		db.closeSession();
	}

	@Test
	public final void testSetDateOfBirth() throws CprException, GeneralDatabaseException {
		DateOfBirthTable dateOfBirthTable = new DateOfBirthTable(100000, "11/11/2010", "jvuccolo");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setDateOfBirth(dateOfBirthTable);
		db.closeSession();

	}

	@Test
	public final void testSetEmailAddress() throws Exception {
		EmailAddressTable emailAddressTable = new EmailAddressTable(100000, EmailAddressType.OTHER_EMAIL.toString(), "blah@psu.edu", "jvuccolo");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setEmailAddress(emailAddressTable);
		db.closeSession();
	}

	@Test
	public final void testSetGender() throws Exception {
		PersonGenderTable perGenderRelTable = new PersonGenderTable(100000, GenderType.GENDER_MALE.toString(), "jvuccolo");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setGender(perGenderRelTable);
		db.closeSession();
	}

	@Test
	public final void testSetAffiliation() throws Exception {
		PersonAffiliationTable affiliationsTable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_CURRENT", "jvuccolo");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setAffiliation(affiliationsTable);
		db.closeSession();	
	}

	@Test
	public final void testSetUserid() throws Exception {
		UseridTable useridTable = new UseridTable(100000,"jvuccolo");
		useridTable.getUseridBean().setUserid("jvuccolo");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setUserid(useridTable);
		db.closeSession();
	}
	
	@Test
	public final void testSetConfidentiality() throws Exception {
		ConfidentialityTable confidentialityTable = new ConfidentialityTable(100000L, "ALL_CONFIDENTIALITY", "jvuccolo");
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setConfidentiality(confidentialityTable);
		db.closeSession();
	}
	
	@Test
	public final void testUserComment() throws Exception {
		UserCommentTable c = new UserCommentTable(1, "xyz123", "USER_COMMENT_MISUSE", "TEST", "raw121" );
		openDbConnection();
		JsonMessage j = new JsonMessage(db, 100000, CprServiceName.AddPerson.toString(), "jvuccolo");
		j.setUserComment(c);
		db.closeSession();

	}
	
	
}
