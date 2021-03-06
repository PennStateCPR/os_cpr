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
import edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress;
import edu.psu.iam.cpr.core.database.types.AddressType;

/**
 * @author cpruser
 *
 */
public class ValidateAddressTest {

	private static Database db = new Database();
	
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeBlank() throws Exception {
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "",null, "cpruser"), null);
	}
	@Test
	public final void testIsValidAddress() throws Exception {
		openDbConnection();
		AssertJUnit.assertEquals(ValidateAddress.isAddressValid(db, null, null, null, null, null,null, true), false);
		db.closeSession();
	} 
	@Test
	public final void testIsValidAddressNonUSA() throws Exception {
		openDbConnection();
		AssertJUnit.assertEquals(ValidateAddress.isAddressValid(db, null, null, null, null, null,null, false), false);
		db.closeSession();
	} 
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeLower() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "up",s, "cpruser"), new Long(100048));
		AssertJUnit.assertEquals(s.toString(), "UNIVERSITY PARK CAMPUS");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeMixed() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "Up", s,"cpruser"), new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeNull() throws Exception{
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, null,null, "cpruser"), null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeSpaceUP() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, " UP",s, "cpruser"),new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUP() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UP",s, "cpruser"), new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUPSpace() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UP ", s, "cpruser"), new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test (expectedExceptions=Exception.class)
	public final void testvalidCampusCodeUPNoDB() throws Exception {
		StringBuilder s = new StringBuilder();
		ValidateAddress.validCampusCode(db, "UP", s, "cpruser");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUp123() throws Exception {
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UP123",s, "cpruser"), new Long(-1));
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUpa() throws Exception {
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UPa",s, "cpruser"), new Long(-1));
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testvalidCampusCodeUz() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "Uz",s, "cpruser"), new Long(-1));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuilder, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testIsValidCampusDBProblem() throws Exception {
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "Uz", s , "cpruser"), new Long(-1));

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeBlank() throws Exception {
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "", null, "cpruser"), -1);
	}

	/**
	 *  Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeLower() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "usa",s, "cpruser"),100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeMixed() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "UsA",s, "cpruser"), 100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeNull() throws Exception{
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, null,s, "cpruser"), -1);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeSpaceUSA() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, " USA",s, "cpruser"), 100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUSA() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "USA",s, "cpruser"), 100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUSASpace() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "USA ",s, "cpruser"), 100069);
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUsa123() throws Exception {
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "Usa123",s, "cpruser"), -1);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUsaa() throws Exception {
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "Usaa",s, "cpruser"),-1);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testvalidCountryCodeUSANoDB() throws Exception {
		StringBuilder s = new StringBuilder();
		ValidateAddress.validCountryCode(db, "USA",s, "cpruser");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testvalidCountryCodeUz() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "UPA",s, "cpruser"), -1);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUSADB() throws Exception {
		openDbConnection();
		StringBuilder s = new StringBuilder();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "USA",s, "cpruser"), 100069);
		db.closeSession();
	}
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeNull() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(null), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeSpace01234() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(" 01234"), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCode01234Space() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("01234 "), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCode1() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("1"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCode01() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("01"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodea1234a() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("a1234"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeSpacea135() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(" a135"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeSpace012345() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(" 012345"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeBlank() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(""), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePA() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PA"), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePASpace() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PA "), true);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateSpacePASpace() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState(" PA "), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateLower() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("pa"), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateMixed() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("Pa"), true);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateNull() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState(null), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePEN() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PEN"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePN() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PN"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateEmptyString() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState(""), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String, java.lang.String, java.lang.String)}.
	 * AddressType not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersNullRequestedBy() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, null,null,null,  null,  null, null, null, null,  null,  null, null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersNullType() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0,null, null,  "cpruser","address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersInvalidType() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "STUDENT",null,  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentTypeNull() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS",null,  "cpruser","address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentTypeBlank() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS"," ", "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentBadCombo() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","LEGAL_NAME",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersRequestedByNull() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "LOCAL_ADDRESS","Fred",  null, "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentBadDocumentType() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","Fred",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersBadAddressTypeWithDocument() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS","PASSPORT",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null );
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersBadDocumentTypeNameWithDocument() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_NAME","PASSPORT",  "cpruser", "address1", null, null, "nowhere", null,  null,  "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersTypeBlank() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, " ",null, "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateAddAddressParametersTypeRightTrim() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS   ",null,  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateAddAddressParametersTypeLeftTrim() throws Exception {
		openDbConnection(); 
		ValidateAddress.validateAddAddressParameters(db, 0, "   PERMANENT_ADDRESS",null,  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateAddAddressParametersTypeBothTrim() throws Exception {
		openDbConnection(); 
		ValidateAddress.validateAddAddressParameters(db, 0, "   PERMANENT_ADDRESS    ",null,  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 *
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTrimRight() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS  ","PASSPORT",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTrimLeft() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "   DOCUMENTED_ADDRESS","PASSPORT",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTrimBoth() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "   DOCUMENTED_ADDRESS   ","PASSPORT",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTypeTrimRight() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","PASSPORT  ",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTypeTrimLeft() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","    PASSPORT",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTypeTrimBoth() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","    PASSPORT    ",  "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersInvalidCampusCode() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Local_address", null,"cpruser", "address1", null, null,"nowhere", "PA", null,  null, "ub");
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersInvalidCampusCodeBlank() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Local_address",null,  "cpruser", "address1", null, null, "nowhere", "PA", null,  "usa", " ");
		
		db.closeSession();
	}
/**
 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
 * last updated by may not be null
 * @throws Exception 
 * @throws CprException 
 */
@Test(expectedExceptions=Exception.class)
public final void testValidateAddAddressParametersNullUpdateBy() throws Exception {
	openDbConnection();
	ValidateAddress.validateAddAddressParameters(db, 0, "Permanent_Address",null,  null, "address1", null, null, "nowhere", "PA", null,  "usa", " ");
	db.closeSession();
}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * last updated by may not be null and must be less than 31 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersUpdateByTooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Permanent_address", null, "asdfghjklzxcvbnmqwertyuiopasdfgh", "address1", null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * last updated by may not be null and must be less than 31 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersUpdateByBlank() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Permanent_address", null,  " ", "address1", null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address1 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddress1TooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS", null, "cpruser", "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl",  null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address2 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddress2TooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "LOCAL_ADDRESS", null,  "cpruser","address1",  "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl",   null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddress3TooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "cpruser",null,  null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl", "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddresss123Null() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "cpruser",null,  null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddresss123Blank() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "cpruser"," ", " ", " ", "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * city must be less than 41 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersCityNull() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "cpruser","address1",  null, null, null, "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * city must be less than 41 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersCityTooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "cpruser","address1",  null, null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * state must be 2 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadState() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "cpruser","address1",  null, null, "nowhere", "PEN", null, "usa",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadPostalCode() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,  "cpruser","address1",  null, null,"nowhere", null, "Absr1", "usa",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadPostalCodeNoDigits() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "cpruser","address1",  null, null, "nowhere", null,  "ABCE",  "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String, java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String )}.
	 * Country code must be 3 alpha
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadCountryCode() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,  "cpruser","address1",  null, null, "nowhere", null, null,   "BADone", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Campus code must be 2 alpha
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterAddress123NullNotUSA() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "cpruser",null,  null, null, null, null, null,"CAN",  null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Campus code must be 2 alpha
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadCampusCode() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,  "cpruser","address1",  null, null, "nowhere", null, null, "usa",  "Uj");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterBadGroupId() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null, null, "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterBadRequestor() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null, 1L, "asdfghjklzxcvbnmqwertyuiopasdfgh", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterBlankRequestor() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null, 1L, " ", null);
		db.closeSession();
	}/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterNullRequestor() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null,1l, null, null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test()
	public final void testValidateArchiveAndSetPrimaryAddressParametersGoodData() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"PERMANENT_ADDRESS", null, 1L, "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocTypeNull() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", null, 1L, "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocTypeBlank() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", " ", 1L, "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocBadType() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", "Legal_Name ", 1L, "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test()
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocGoodTypeTrim() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", " PASSPORT ", 1L, "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String,  java.lang.String)}.
	 * type is invalid
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersBadType() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"STUDENT", null,1L,  "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String,  java.lang.String)}.
	 * type is invalid
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersBadTypeTrim() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"STUDENT   ", null,1L,  "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String)}.
	 * type is invalid - must be specified
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersNoType() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"", null, 1L,"cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int,  java.lang.String, java.lang.String)}.
	 * type is invalid - must be specified
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersNullType() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0, null, null, 1L, "cpruser", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int,  java.lang.String, java.lang.String)}.
	 * type is invalid - must be specified
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateArchiveAndSetPrimaryAddressParametersTrimType() throws Exception {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0, "LOCAL_ADDRESS ", null, 1L, "cpruser", null);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateGetAddressParameters(int, java.lang.String )}.
	 * invalid request or service principal
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressParameterBadRequestor() throws Exception {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 0,  "asdfghjklzxcvbnmqwertyuiopasdfgh",null,"Y", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateGetAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressParametersNullRequestBy() throws Exception {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 0, null,null,"Y", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateGetAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test()
	public final void testValidateGetAddressParametersGoodData() throws Exception {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 100010, "cpruser",null,"Y", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateGetAddressParameters(int, java.lang.String )}.
	 * invalid request or service principal
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressByTypeParameterBadRequestor() throws Exception {
		openDbConnection();
		AddressesTable addressesTable = ValidateAddress.validateGetAddressParameters(db, 0,   "asdfghjklzxcvbnmqwertyuiopasdfgh","DOCUMENTED_ADDRESS","n", null);
		AssertJUnit.assertEquals(addressesTable.getAddressType(),AddressType.DOCUMENTED_ADDRESS);
		AssertJUnit.assertFalse(addressesTable.isReturnHistoryFlag());
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateGetAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressByTypeParametersNullRequestBy() throws Exception {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 0,"cpruser", "abc","n", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * AddressType not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersNullGroupId() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null, null, "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersTypeNull() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, null, null, 1L, "cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersTypeBlank() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, " ", null, 1L,"cpruser", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersInvalidType() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "STUDENT", null,1L,"cpruser", "address1", null, null, "nowhere", null, null,   "usa", null );
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersNullUpdateBy() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L,  null, "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersBlankUpdateBy() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L, " ", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * last updated by may not be null and must be less than 31 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersBadUpdateBy() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null,  1L,"asdfghjklzxcvbnmqwertyuiopasdfgh", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address1 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress1TooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "cpruser", "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address2 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress2TooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "cpruser",null,  "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl",  null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress3TooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "cpruser",null,  null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl", "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress123Null() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "cpruser",null,  null, null,  "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress123Blank() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "cpruser"," ",  "", " ", "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * city must be less than 41 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersCityTooLong() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS", null,1L, "cpruser","address1",  null, null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm",null, null,   "usa", null);
			
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadState() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS", null,1L, "cpruser","address1",  null, null, "nowhere", "PEN",  null,"USA",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * stateOrProvince > 2 character when a province
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateUpdateAddressParameterGoodProvince() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "PERMANENT_ADDRESS", null,1L, "cpruser","address1",  null, null, "Toronto", "Ontario",null,"CAN",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * stateOrProvince > 2 character when a province
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadStateTest() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null, 1L, "cpruser","address1",  null, null, "Toronto", "Ontario",  null,"USA",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * stateOrProvince > 2 character when a province
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterAddress123NullNonUsa() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L, "cpruser",null,  null, null, "Toronto", "Ontario",  null,"CAN",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadPostalCode() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null,1L, "cpruser","address1",  null, null, "nowhere", null, "Absr1",  null,null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadPlus4() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null,1L, "cpruser","address1",  null, null, "nowhere", null, "ABDG","usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * Country code must be 3 alpha
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadCountryCode() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L,"cpruser","address1",  null, null, "nowhere", null, null,  "BADone", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * Country code must be 3 alpha
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadCountryCodeNodb() throws Exception {
		
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L, "cpruser","address1",  null, null, "nowhere", null, null,  "BADone", null);
	
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * Campus code must be 2 alpha
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadCampusCode() throws Exception {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null,1L, "cpruser","address1",  null, null, "nowhere", null,  null, "USA", "Uj");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersDocumentBadDocument() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","FRED",  "cpruser", "address1",  null, null, "nowhere", null,  null, "USA", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersBadDocumentTypeBadAddressType() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS","PASSPORT", "cpruser", "address1",  null, null, "nowhere", null,  null, "USA", null);
		
		db.closeSession();
	}
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersNoCityState() throws Exception {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS", null, "cpruser", "address1",  null, null, null, null, "16801", "USA", null);
		
		db.closeSession();
	}
	
		
	
	
}
