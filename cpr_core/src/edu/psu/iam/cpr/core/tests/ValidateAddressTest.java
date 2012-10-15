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
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.util.ValidateAddress;

/**
 * @author llg5
 *
 */
public class ValidateAddressTest {

	private static Database db = new Database();
	
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeBlank() throws Exception {
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "",null, "llg5"), null);
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
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeLower() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "up",s, "llg5"), new Long(100048));
		AssertJUnit.assertEquals(s.toString(), "UNIVERSITY PARK CAMPUS");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeMixed() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "Up", s,"llg5"), new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeNull() throws Exception{
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, null,null, "llg5"), null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeSpaceUP() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, " UP",s, "llg5"),new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUP() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UP",s, "llg5"), new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUPSpace() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UP ", s, "llg5"), new Long(100048));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test (expectedExceptions=Exception.class)
	public final void testvalidCampusCodeUPNoDB() throws Exception {
		StringBuffer s = new StringBuffer();
		ValidateAddress.validCampusCode(db, "UP", s, "llg5");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUp123() throws Exception {
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UP123",s, "llg5"), new Long(-1));
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCampusCodeUpa() throws Exception {
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "UPa",s, "llg5"), new Long(-1));
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testvalidCampusCodeUz() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "Uz",s, "llg5"), new Long(-1));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCampusCode(Database, java.lang.String, java.lang.StringBuffer, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testIsValidCampusDBProblem() throws Exception {
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCampusCode(db, "Uz", s , "llg5"), new Long(-1));

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeBlank() throws Exception {
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "", null, "llg5"), -1);
	}

	/**
	 *  Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeLower() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "usa",s, "llg5"),100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeMixed() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "UsA",s, "llg5"), 100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeNull() throws Exception{
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, null,s, "llg5"), -1);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeSpaceUSA() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, " USA",s, "llg5"), 100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUSA() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "USA",s, "llg5"), 100069);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUSASpace() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "USA ",s, "llg5"), 100069);
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUsa123() throws Exception {
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "Usa123",s, "llg5"), -1);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUsaa() throws Exception {
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "Usaa",s, "llg5"),-1);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testvalidCountryCodeUSANoDB() throws Exception {
		StringBuffer s = new StringBuffer();
		ValidateAddress.validCountryCode(db, "USA",s, "llg5");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testvalidCountryCodeUz() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "UPA",s, "llg5"), -1);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validCountryCode(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception
	 */
	@Test
	public final void testvalidCountryCodeUSADB() throws Exception {
		openDbConnection();
		StringBuffer s = new StringBuffer();
		AssertJUnit.assertEquals(ValidateAddress.validCountryCode(db, "USA",s, "llg5"), 100069);
		db.closeSession();
	}
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeNull() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(null), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeSpace01234() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(" 01234"), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCode01234Space() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("01234 "), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCode1() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("1"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCode01() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("01"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodea1234a() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode("a1234"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeSpacea135() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(" a135"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeSpace012345() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(" 012345"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidZipCode(java.lang.String)}.
	 */
	@Test
	public final void testIsValidZipCodeBlank() {
		AssertJUnit.assertEquals(ValidateAddress.isValidZipCode(""), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePA() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PA"), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePASpace() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PA "), true);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateSpacePASpace() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState(" PA "), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateLower() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("pa"), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateMixed() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("Pa"), true);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateNull() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState(null), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePEN() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PEN"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStatePN() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState("PN"), false);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#isValidState(java.lang.String)}.
	 */
	@Test
	public final void testIsValidStateEmptyString() {
		AssertJUnit.assertEquals(ValidateAddress.isValidState(""), true);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String, java.lang.String, java.lang.String)}.
	 * AddressType not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersNullRequestedBy() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, null,null,null,  null,  null, null, null, null,  null,  null, null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersNullType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0,null, null,  "llg5","address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersInvalidType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "STUDENT",null,  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentTypeNull() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS",null,  "llg5","address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentTypeBlank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS"," ", "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentBadCombo() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","LEGAL_NAME",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersRequestedByNull() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "LOCAL_ADDRESS","Fred",  null, "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersDocumentBadDocumentType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","Fred",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersBadAddressTypeWithDocument() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS","PASSPORT",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null );
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersBadDocumentTypeNameWithDocument() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_NAME","PASSPORT",  "llg5", "address1", null, null, "nowhere", null,  null,  "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersTypeBlank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, " ",null, "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateAddAddressParametersTypeRightTrim() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS   ",null,  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateAddAddressParametersTypeLeftTrim() throws CprException, GeneralDatabaseException {
		openDbConnection(); 
		ValidateAddress.validateAddAddressParameters(db, 0, "   PERMANENT_ADDRESS",null,  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateAddAddressParametersTypeBothTrim() throws CprException, GeneralDatabaseException {
		openDbConnection(); 
		ValidateAddress.validateAddAddressParameters(db, 0, "   PERMANENT_ADDRESS    ",null,  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 *
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTrimRight() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS  ","PASSPORT",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTrimLeft() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "   DOCUMENTED_ADDRESS","PASSPORT",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTrimBoth() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "   DOCUMENTED_ADDRESS   ","PASSPORT",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTypeTrimRight() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","PASSPORT  ",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTypeTrimLeft() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","    PASSPORT",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersDocumentAddressTypeTrimBoth() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","    PASSPORT    ",  "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersInvalidCampusCode() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Local_address", null,"llg5", "address1", null, null,"nowhere", "PA", null,  null, "ub");
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * 
	 */
	@Test
	public final void testValidateAddAddressParametersInvalidCampusCodeBlank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Local_address",null,  "llg5", "address1", null, null, "nowhere", "PA", null,  "usa", " ");
		
		db.closeSession();
	}
/**
 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
 * last updated by may not be null
 * @throws GeneralDatabaseException 
 * @throws CprException 
 */
@Test(expectedExceptions=Exception.class)
public final void testValidateAddAddressParametersNullUpdateBy() throws CprException, GeneralDatabaseException {
	openDbConnection();
	ValidateAddress.validateAddAddressParameters(db, 0, "Permanent_Address",null,  null, "address1", null, null, "nowhere", "PA", null,  "usa", " ");
	db.closeSession();
}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * last updated by may not be null and must be less than 31 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersUpdateByTooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Permanent_address", null, "asdfghjklzxcvbnmqwertyuiopasdfgh", "address1", null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * last updated by may not be null and must be less than 31 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersUpdateByBlank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "Permanent_address", null,  " ", "address1", null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address1 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddress1TooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS", null, "llg5", "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl",  null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address2 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddress2TooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "LOCAL_ADDRESS", null,  "llg5","address1",  "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl",   null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddress3TooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "llg5",null,  null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl", "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddresss123Null() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "llg5",null,  null, null, "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersAddresss123Blank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "llg5"," ", " ", " ", "nowhere", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * city must be less than 41 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersCityNull() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "llg5","address1",  null, null, null, "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * city must be less than 41 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParametersCityTooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "llg5","address1",  null, null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm", "PA", null,  "usa", " ");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * state must be 2 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadState() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address", null,  "llg5","address1",  null, null, "nowhere", "PEN", null, "usa",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadPostalCode() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,  "llg5","address1",  null, null,"nowhere", null, "Absr1", "usa",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadPostalCodeNoDigits() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "llg5","address1",  null, null, "nowhere", null,  "ABCE",  "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String, java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String )}.
	 * Country code must be 3 alpha
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadCountryCode() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,  "llg5","address1",  null, null, "nowhere", null, null,   "BADone", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Campus code must be 2 alpha
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterAddress123NullNotUSA() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,   "llg5",null,  null, null, null, null, null,"CAN",  null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Campus code must be 2 alpha
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddAddressParameterBadCampusCode() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "local_address",null,  "llg5","address1",  null, null, "nowhere", null, null, "usa",  "Uj");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterBadGroupId() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null, null, "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterBadRequestor() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null, 1L, "asdfghjklzxcvbnmqwertyuiopasdfgh");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterBlankRequestor() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null, 1L, " ");
		db.closeSession();
	}/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String  )}.
	 * invalid request or service principal
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParameterNullRequestor() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Local_address", null,1l, null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test()
	public final void testValidateArchiveAndSetPrimaryAddressParametersGoodData() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"PERMANENT_ADDRESS", null, 1L, "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocTypeNull() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", null, 1L, "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocTypeBlank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", " ", 1L, "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocBadType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", "Legal_Name ", 1L, "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test()
	public final void testValidateArchiveAndSetPrimaryAddressParametersAddressDocGoodTypeTrim() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"Documented_ADDRESS", " PASSPORT ", 1L, "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String,  java.lang.String)}.
	 * type is invalid
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersBadType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"STUDENT", null,1L,  "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String,  java.lang.String)}.
	 * type is invalid
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersBadTypeTrim() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"STUDENT   ", null,1L,  "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int, java.lang.String, java.lang.String)}.
	 * type is invalid - must be specified
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersNoType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0,"", null, 1L,"llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int,  java.lang.String, java.lang.String)}.
	 * type is invalid - must be specified
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveAndSetPrimaryAddressParametersNullType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0, null, null, 1L, "llg5");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateArchiveAndSetPrimaryAddressParameters(int,  java.lang.String, java.lang.String)}.
	 * type is invalid - must be specified
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateArchiveAndSetPrimaryAddressParametersTrimType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateArchiveAndSetPrimaryAddressParameters(db, 0, "LOCAL_ADDRESS ", null, 1L, "llg5");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateGetAddressParameters(int, java.lang.String )}.
	 * invalid request or service principal
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressParameterBadRequestor() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 0,  "asdfghjklzxcvbnmqwertyuiopasdfgh",null,"Y");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateGetAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressParametersNullRequestBy() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 0, null,null,"Y");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateGetAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test()
	public final void testValidateGetAddressParametersGoodData() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 100010, "llg5",null,"Y");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateGetAddressParameters(int, java.lang.String )}.
	 * invalid request or service principal
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressByTypeParameterBadRequestor() throws CprException, GeneralDatabaseException {
		openDbConnection();
		AddressesTable addressesTable = ValidateAddress.validateGetAddressParameters(db, 0,   "asdfghjklzxcvbnmqwertyuiopasdfgh","DOCUMENTED_ADDRESS","n");
		AssertJUnit.assertEquals(addressesTable.getAddressType(),AddressType.DOCUMENTED_ADDRESS);
		AssertJUnit.assertFalse(addressesTable.isReturnHistoryFlag());
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateGetAddressParameters(int, java.lang.String)}.
	 * all the data is valid in this call
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetAddressByTypeParametersNullRequestBy() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateGetAddressParameters(db, 0,"jvuccolo", "abc","n");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * AddressType not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersNullGroupId() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null, null, "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersTypeNull() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, null, null, 1L, "llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersTypeBlank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, " ", null, 1L,"llg5", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * addresstype must be of valid type
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersInvalidType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "STUDENT", null,1L,"llg5", "address1", null, null, "nowhere", null, null,   "usa", null );
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersNullUpdateBy() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L,  null, "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersBlankUpdateBy() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L, " ", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * last updated by may not be null and must be less than 31 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersBadUpdateBy() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null,  1L,"asdfghjklzxcvbnmqwertyuiopasdfgh", "address1", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address1 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress1TooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "llg5", "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl", null, null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address2 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress2TooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "llg5",null,  "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl",  null, "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress3TooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "llg5",null,  null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl", "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress123Null() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "llg5",null,  null, null,  "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersAddress123Blank() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address", null, 1L, "llg5"," ",  "", " ", "nowhere", null, null,   "usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * city must be less than 41 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersCityTooLong() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS", null,1L, "llg5","address1",  null, null, "asdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm",null, null,   "usa", null);
			
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * address3 must be less than 61 characters
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadState() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS", null,1L, "llg5","address1",  null, null, "nowhere", "PEN",  null,"USA",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * stateOrProvince > 2 character when a province
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testValidateUpdateAddressParameterGoodProvince() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "PERMANENT_ADDRESS", null,1L, "llg5","address1",  null, null, "Toronto", "Ontario",null,"CAN",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * stateOrProvince > 2 character when a province
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadStateTest() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null, 1L, "llg5","address1",  null, null, "Toronto", "Ontario",  null,"USA",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * stateOrProvince > 2 character when a province
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterAddress123NullNonUsa() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L, "llg5",null,  null, null, "Toronto", "Ontario",  null,"CAN",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadPostalCode() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null,1L, "llg5","address1",  null, null, "nowhere", null, "Absr1",  null,null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * postal code must be all numeric
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadPlus4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null,1L, "llg5","address1",  null, null, "nowhere", null, "ABDG","usa", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * Country code must be 3 alpha
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadCountryCode() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L,"llg5","address1",  null, null, "nowhere", null, null,  "BADone", null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * Country code must be 3 alpha
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadCountryCodeNodb() throws CprException, GeneralDatabaseException {
		
		ValidateAddress.validateUpdateAddressParameters(db, 0, "local_address",null, 1L, "llg5","address1",  null, null, "nowhere", null, null,  "BADone", null);
	
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateUpdateAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String)}.
	 * Campus code must be 2 alpha
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParameterBadCampusCode() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateUpdateAddressParameters(db, 0, "LOCAL_ADDRESS",null,1L, "llg5","address1",  null, null, "nowhere", null,  null, "USA", "Uj");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersDocumentBadDocument() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "DOCUMENTED_ADDRESS","FRED",  "llg5", "address1",  null, null, "nowhere", null,  null, "USA", null);
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersBadDocumentTypeBadAddressType() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS","PASSPORT", "llg5", "address1",  null, null, "nowhere", null,  null, "USA", null);
		
		db.closeSession();
	}
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateAddress#validateAddAddressParameters(int, java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String,java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,java.lang.String)}.
	 * last updated by may not be null
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUpdateAddressParametersNoCityState() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateAddress.validateAddAddressParameters(db, 0, "PERMANENT_ADDRESS", null, "llg5", "address1",  null, null, null, null, "16801", "USA", null);
		
		db.closeSession();
	}
	
		
	
	
}
