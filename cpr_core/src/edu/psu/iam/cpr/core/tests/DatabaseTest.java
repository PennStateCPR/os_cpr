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
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.helpers.DBTypesHelper;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.*;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

public class DatabaseTest {

	private static Database db = new Database();
	
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	// kludge open a direct connection to the db.using the hard way.
	@Test
	public final void _01testOpenDbConnection() throws GeneralDatabaseException {
		openDbConnection();
	}
	
	// Test the close connection command.
	@Test
	public final void _02testcloseSession() {
		db.closeSession();
		AssertJUnit.assertFalse(db.isSessionOpen());
	}

	@Test
	public final void _03testIsDbConnectionOpen() throws GeneralDatabaseException {
		AssertJUnit.assertFalse(db.isSessionOpen());
		openDbConnection();
		AssertJUnit.assertTrue(db.isSessionOpen());
		db.closeSession();
	}

	@Test
	public final void _04testgetSession() throws GeneralDatabaseException {
		AssertJUnit.assertNull(db.getSession());
		openDbConnection();
		AssertJUnit.assertNotNull(db.getSession());
		db.closeSession();
		AssertJUnit.assertNull(db.getSession());
	}

	@Test(expectedExceptions=Exception.class)
	public final void _05testIsRequestorAuthorizedWebClientNotFoundException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized(null, null, "AddPerson",null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _06testIsRequestorAuthorizedWebClientNotFoundException2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("abcd1234", "jvuccolo", "AddPerson",new ServiceCoreReturn());
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _07testIsRequestorAuthorizedWebServiceNotFoundException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("portal1", "jvuccolo", null,new ServiceCoreReturn());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _08testIsRequestorAuthorizedWebServiceNotFoundException2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("portal1", "jvuccolo", "some_service_goes_here", new ServiceCoreReturn());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _09testIsRequestorAuthorizedCprException() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("portal2", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.closeSession();
	}
	
	@Test
	public final void _10testIsRequestorAuthorizedSuccess() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.closeSession();
	}
	@Test
	public final void _11testIsRequestorAuthorizedSuccessCreds() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("cpruser", "jvuccolo", "AddCredential", new ServiceCoreReturn());
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _12testGetPersonIdUsingPsuIdException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonIdUsingPsuId(null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _13testGetPersonIdUsingPsuIdException2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonIdUsingPsuId("abcd1234");
		db.closeSession();
	}
	
	@Test
	public final void _14testGetPersonIdUsingPsuId() throws GeneralDatabaseException, CprException {
		openDbConnection();
		long personId = db.getPersonIdUsingPsuId("812345678");
		db.closeSession();
		AssertJUnit.assertEquals(personId, 100001);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _15testGetPersonIdUsingIdCard() throws GeneralDatabaseException, CprException {
		openDbConnection();
		long personId = db.getPersonIdUsingIdCard("812345678");
		db.closeSession();
		AssertJUnit.assertEquals(personId, 100001);
	}

	@Test(expectedExceptions=Exception.class)
	public final void _16testGetPersonIdUsingUseridException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonIdUsingUserid(null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _17testGetPersonIdUsingUseridException2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonIdUsingUserid("abcd");
		db.closeSession();
	}

	@Test
	public final void _18testGetPersonIdUsingUserid() throws GeneralDatabaseException, CprException {
		openDbConnection();
		long personId = db.getPersonIdUsingUserid("dummy");
		db.closeSession();
		AssertJUnit.assertEquals(personId, 100000);
	}

	@Test(expectedExceptions=Exception.class)
	public final void _19testGetPersonIdUsingPersonIdException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonIdUsingPersonId(0);
		db.closeSession();
	}
	
	@Test
	public final void _20testGetPersonIdUsingPersonId() throws GeneralDatabaseException, CprException {
		openDbConnection();
		long personId = db.getPersonIdUsingPersonId(100000);
		db.closeSession();
		AssertJUnit.assertEquals(personId, 100000);
	}

	@Test(expectedExceptions=Exception.class)
	public final void _21testGetPersonIdUsingIdentifierException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.PERSON_ID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _22testGetPersonIdUsingIdentifierException2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.USERID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _23testGetPersonIdUsingIdentifierException3() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.PSU_ID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _24testGetPersonIdUsingIdentifierException4() throws GeneralDatabaseException, CprException{
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.PERSON_ID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, "abcd");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _25testGetPersonIdUsingIdentifierException5() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.USERID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, "abcd");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _26testGetPersonIdUsingIdentifierException6() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.PSU_ID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, "abcd");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _27testGetPersonIdUsingIdentifierException7() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.PERSON_ID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, "1");
		db.closeSession();
	}
	
	@Test
	public final void _28testGetPersonIdUsingIdentifier1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.PERSON_ID_IDENTIFIER);
		db.getPersonIdUsingIdentifier(i, "100000");
		db.closeSession();
	}
	
	@Test
	public final void _29testGetPersonIdUsingIdentifier2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.USERID_IDENTIFIER);
		long personId = db.getPersonIdUsingIdentifier(i, "dummy");
		db.closeSession();
		AssertJUnit.assertEquals(personId, 100000);
	}
	
	@Test
	public final void _30testGetPersonIdUsingIdentifier3() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = new IdentifierType();
		i.setTypeName(Database.PSU_ID_IDENTIFIER);
		long personId = db.getPersonIdUsingIdentifier(i, "812345678");
		db.closeSession();
		AssertJUnit.assertEquals(personId, 100001);
	}
	
	@Test
	public final void _31testGetPersonIdUsingIdentifier4() throws GeneralDatabaseException, CprException {
		openDbConnection();
		IdentifierType i = (IdentifierType) DBTypesHelper.getInstance(db).getTypeMaps(DBTypesHelper.IDENTIFIER_TYPE).get("UNIT_TEST_IDENTIFIER");
		long personId = db.getPersonIdUsingIdentifier(i, "TEST_VALUE");
		db.closeSession();
		AssertJUnit.assertEquals(personId, 100000);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _32testIsPersonActiveException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.isPersonActive(1);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _33testIsPersonActiveException2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.isPersonActive(100001);
		db.closeSession();
	}

	@Test
	public final void _34testIsPersonActive() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.isPersonActive(100000);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _35testDoesPsuIdExistException1() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.doesPsuIdExist(null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _36testDoesPsuIdExistException2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.doesPsuIdExist("abcd1234");
		db.closeSession();
	}
	
	@Test
	public final void _37testDoesPsuIdExist() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.doesPsuIdExist("812345678");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _38testDoesUseridExistException1() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.doesUseridExist(null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _39testDoesUseridExistException2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.doesUseridExist("abcd1234");
		db.closeSession();
	}
	
	@Test
	public final void _40testDoesUseridExist() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.doesUseridExist("dummy");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _41testGetPersonIdException1() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.getPersonId(null, null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _42testGetPersonIdException2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.getPersonId("person", null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _43testGetPersonIdException3() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonId("person_id", null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _44testGetPersonIdException4() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonId("person", "1234");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _45testGetPersonIdException5() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonId("person_id", "1234");
		db.closeSession();
	}

//	@Test(expected=Exception.class)
//	public final void _46testGetPersonIdException6() throws GeneralDatabaseException, CprException {
//		openDbConnection();
//		db.getPersonId("person_id", "100001");
//		db.closeSession();
//	}

	@Test(expectedExceptions=Exception.class)
	public final void _47testGetPersonIdNoExceptions1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonId("userid", "1111111111111111111111111111111111111111111111111111111111111111111111111");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _48testGetAllTableColumns1() throws GeneralDatabaseException, CprException {
		db.getAllTableColumns(null);
	}

	@Test
	public final void _49testGetPersonIdNoExceptions() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getPersonId("person_id", "100000");
		db.closeSession();
	}
	
	@Test(expectedExceptions=GeneralDatabaseException.class)
	public final void _50testGetAllTableColumns2() throws GeneralDatabaseException, CprException {
		db.getAllTableColumns("address");
	}

	@Test
	public final void _51testGetAllTableColumns3() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getAllTableColumns("address");
		AssertJUnit.assertEquals(db.getTableColumns().size(), 0);
		db.closeSession();
	}
	
	@Test
	public final void _52testGetAllTableColumns4() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.getAllTableColumns("addresses");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _53testGetColumn1() throws CprException, GeneralDatabaseException {
		db.getColumn(null);
	}
	
	@Test(expectedExceptions=GeneralDatabaseException.class)
	public final void _54testGetColumn2() throws CprException, GeneralDatabaseException {
		db.getColumn("blah");
	}
	
	@Test
	public final void _55testGetColumn3() throws CprException, GeneralDatabaseException {
		db.getColumn("address1");
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _56testisDataActionAuthorizedOneMore() throws CprException, GeneralDatabaseException {
		db.isDataActionAuthorized(null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _57testisDataActionAuthorized2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		db.isDataActionAuthorized(null, null, null, null);
		db.closeSession();
	}
	@Test
	public final void _58testisDataActionAuthorized3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized(serviceCoreReturn, AccessType.LEGAL_NAME.toString(), AccessType.ACCESS_OPERATION_ARCHIVE.toString(), "jvuccolo");
		db.closeSession();
	}
	@Test
	public final void _59testisDataActionAuthorized4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized(serviceCoreReturn, AccessType.LEGAL_NAME.toString(), AccessType.ACCESS_OPERATION_READ.toString(), "jvuccolo");
		db.closeSession();
	}
	@Test
	public final void _60testisDataActionAuthorized5() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized(serviceCoreReturn, AccessType.LEGAL_NAME.toString(), AccessType.ACCESS_OPERATION_WRITE.toString(), "jvuccolo");
		db.closeSession();
	}
	
	@Test
	public final void _61testRollbackSession() throws GeneralDatabaseException {
		openDbConnection();
		db.rollbackSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _62testIsRequestorAuthorizedGrouperWebServiceNotFoundException1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("portal1", "jvuccolo", null,new ServiceCoreReturn());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _63testIsRequestorAuthorizedWebServiceNotFoundExceptionAgain() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("portal1", "jvuccolo", "some_service_goes_here", new ServiceCoreReturn());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _64testIsRequestorAuthorizedGrouperCprException() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("portal2", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.closeSession();
	}
	
	@Test
	public final void _65testIsRequestorAuthorizedSuccessportal1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _66testisDataActionAuthorized1() throws CprException, GeneralDatabaseException{
		db.isDataActionAuthorized(null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _67testisDataActionAuthorizedGrouper2() throws CprException, GeneralDatabaseException {
		db.isDataActionAuthorized(null, null, null,  null);
	}
	@Test
	public final void _68testisDataActionAuthorizedGrouper3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized(serviceCoreReturn, "PERMANENT_PHONE", "ACCESS_OPERATION_ARCHIVE".toLowerCase(), "jvuccolo");
		db.closeSession();
	}
	@Test
	public final void _69testisDataActionAuthorized() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized( serviceCoreReturn,"PERMANENT_PHONE", "ACCESS_OPERATION_READ", "jvuccolo" );
		db.closeSession();
	}
	@Test
	public final void _70testisDataActionAuthorizedGrouper5() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized(serviceCoreReturn ,  "PERMANENT_PHONE", "ACCESS_OPERATION_WRITE", "jvuccolo");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _71testisDataActionAuthorized6() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized( serviceCoreReturn, "EMPLOYEE_PHONE_", "ACCESS_OPERATION_ARCHIVE", "jvuccolo" );
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _72testisDataActionAuthorized7() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized(serviceCoreReturn,  "EMPLOYEE_PHONE_", "ARCE", "jvuccolo" );
		db.closeSession();
	}
	@Test
	public final void _73testisDataActionAuthorized8() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "jvuccolo", "AddPerson", new ServiceCoreReturn());
		db.isDataActionAuthorized(serviceCoreReturn,  "LOCAL_ADDRESS", "ACCESS_OPERATION_WRITE", "llg5" );
		db.closeSession();
	}
	@Test
	public final void _74testAreFieldsEqual1() {
		String s1 = null;
		String s2 = null;
		AssertJUnit.assertTrue(db.areStringFieldsEqual(s1,s2));
	}
	@Test
	public final void _75testAreFieldsEqual2() {
		AssertJUnit.assertFalse(db.areStringFieldsEqual(null, "abcd"));
	}
	@Test
	public final void _76testAreFieldsEqual3() {
		AssertJUnit.assertTrue(db.areStringFieldsEqual("", ""));
	}
	@Test
	public final void _77testAreFieldsEqual4() {
		AssertJUnit.assertFalse(db.areStringFieldsEqual("", "abcd"));
	}
	@Test
	public final void _78testAreFieldsEqual6() {
		AssertJUnit.assertFalse(db.areStringFieldsEqual("ABCD1", "abcd"));
	}
	@Test
	public final void _79testAreFieldsEqual7() {
		Long l1 = null;
		Long l2 = null;
		AssertJUnit.assertTrue(db.areLongFieldsEqual(l1,l2));
	}
	@Test
	public final void _80testAreFieldsEqual8() {
		Long l1 = null;
		Long l2 = 1L;
		AssertJUnit.assertFalse(db.areLongFieldsEqual(l1,l2));
	}
	@Test
	public final void _81testAreFieldsEqual9() {
		Long l1 = 1L;
		Long l2 = 1L;
		AssertJUnit.assertTrue(db.areLongFieldsEqual(l1,l2));
	}
	
	@Test
	public final void _82testIsValidUserid1() throws GeneralDatabaseException {
		openDbConnection();
		AssertJUnit.assertFalse(db.isValidUserid(2L, "jvuccolo"));
		db.closeSession();
	}
		
	@Test(expectedExceptions=CprException.class)
	public final void _83testIsAffiliationRANotAuthorized() throws Exception {
	openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "vlt", "AddAffiliation", new ServiceCoreReturn());
		boolean bTest =db.isAffiliationAccessAuthorized(serviceCoreReturn, "EMPLOYEE_WAGE_ACTIVE", "vlt");
		AssertJUnit.assertTrue(bTest);
		db.closeSession();
	}

	@Test(expectedExceptions=CprException.class)
	public final void _84testIsAffiliationNotAuthorized() throws Exception {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("portal5", "vlt", "AddAffiliation", new ServiceCoreReturn());
		
		boolean bTest =db.isAffiliationAccessAuthorized(serviceCoreReturn, "EMPLOYEE_STAFF_ACTIVE",  "vlt");
		AssertJUnit.assertFalse(bTest);
		db.closeSession();
	}

	@Test
	public final void _85testIsAffiliationAuthorized() throws Exception {
		openDbConnection();
		ServiceCoreReturn serviceCoreReturn = db.requestorAuthorized("cpruser", "llg5", "AddAffiliation", new ServiceCoreReturn());
		
		boolean bTest =db.isAffiliationAccessAuthorized(serviceCoreReturn, "EMPLOYEE_STAFF_ACTIVE",  "llg5");
		AssertJUnit.assertTrue(bTest);
		db.closeSession();
	}

	
}
