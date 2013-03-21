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
import edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable;
import edu.psu.iam.cpr.core.database.types.IapType;
import edu.psu.iam.cpr.core.service.returns.IAPReturn;

/**
 * @author cpruser
 *
 */
public class PersonUseridIapTableTest {
	
	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable()}.
	 */
	@Test
	public final void testPersonUseridPersonUseridIapTable() {
		new PersonUseridIapTable();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int,  java.lang.String, java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test
	public final void testPersonUseridIapTableArgs() throws Exception {
		new PersonUseridIapTable(1000000, "dummy",  "BRONZE", "cpruser");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int, java.lang.String,  java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test(expectedExceptions=Exception.class)
	public final void testPersonUseridIapTableArgsBadIap() throws Exception {
		new PersonUseridIapTable(1000000, "dummy", "PURPLE", "cpruser");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int, java.lang.String, java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test(expectedExceptions=Exception.class)
	public final void testPersonUseridIapTableArgsNullIap() throws Exception {
		new PersonUseridIapTable(1000000,  "dummy",null, "cpruser");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int,  java.lang.String,java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test(expectedExceptions=Exception.class)
	public final void testPersonUseridIapTableArgsBlankIap() throws Exception {
		new PersonUseridIapTable(1000000, "dummy", "", "cpruser");
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int, java.lang.String,  java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test(expectedExceptions=Exception.class)
	public final void testPersonUseridIapTableArgsBadUserId() throws Exception {
		new PersonUseridIapTable(1000000, "abcdesfyths", "PURPLE", "cpruser");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int, java.lang.String, java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test(expectedExceptions=Exception.class)
	public final void testPersonUseridIapTableArgsNullUserId() throws Exception {
		new PersonUseridIapTable(1000000,  null,null, "cpruser");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int,  java.lang.String,java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test(expectedExceptions=Exception.class)
	public final void testPersonUseridIapTableArgsBlankUserId() throws Exception {
		new PersonUseridIapTable(1000000, "", "", "cpruser");
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int,  java.lang.String,java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test
	public final void testPersonUseridIapTableArgsNulUpdatedById() throws Exception {
		new PersonUseridIapTable(1000000,  "tuj139","BRONZE", null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int, java.lang.String, java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test
	public final void testPersonUseridIapTableArgsBlankUpdatedById() throws Exception {
		new PersonUseridIapTable(1000000,  "tuj139","BRONZE", "");
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#PersonUseridIapTable(int, java.lang.String, java.lang.String, java.lang.String)}.
	 *  * @throws Exception 
	 */
	
	@Test
	public final void testPersonUseridIapTableArgsCheckIAP() throws Exception {
		PersonUseridIapTable PersonUseridIapTable = new PersonUseridIapTable(1000000,  "tuj139","BRONZE", "cpruser");
		AssertJUnit.assertEquals(PersonUseridIapTable.getIapType(), IapType.BRONZE);
	}
	
	

	

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#getIapType()}.
	 * @throws Exception 
	 */
	@Test
	public final void testGetIapType() throws Exception {
		PersonUseridIapTable  PersonUseridIapTable = new PersonUseridIapTable(1000000,  "tuj139","SILVER", "cpruser");
		AssertJUnit.assertEquals(PersonUseridIapTable.getIapType(), IapType.SILVER);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#setIapType(edu.psu.iam.cpr.core.database.types.IapType)}.
	 */
	@Test
	public final void testSetIapTypeIapType() {
		PersonUseridIapTable PersonUseridIapTable = new PersonUseridIapTable();
		PersonUseridIapTable.setIapType(IapType.BRONZE);
		AssertJUnit.assertEquals(PersonUseridIapTable.getIapType(), IapType.BRONZE);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#setIapType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void testSetIapTypeString() throws Exception {
		PersonUseridIapTable PersonUseridIapTable = new PersonUseridIapTable();
		PersonUseridIapTable.setIapType(PersonUseridIapTable.findIapTypeEnum("Bronze"));
		AssertJUnit.assertEquals(PersonUseridIapTable.getIapType(), IapType.BRONZE);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#getIapTypeId()}.
	 * @throws Exception 
	 */
	@Test
	public final void testGetIapTypeId() throws Exception {
		PersonUseridIapTable  PersonUseridIapTable = new PersonUseridIapTable(1000000, "tuj139", "BRONZE", "cpruser");
		AssertJUnit.assertEquals(PersonUseridIapTable.getIapType(), IapType.BRONZE);
		
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTable#setIapTypeId(int)}.
	 * @throws Exception 
	 */
	@Test
	public final void testSetIapTypeId() throws Exception {
		PersonUseridIapTable PersonUseridIapTable = new PersonUseridIapTable();
		PersonUseridIapTable.setIapType(PersonUseridIapTable.findIapTypeEnum("Bronze"));
		AssertJUnit.assertEquals(PersonUseridIapTable.getIapType().toString(), "BRONZE");
	}


	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getExternalIap()}.
	 */
	@Test
	public final void testGetExternalIAPInValidUserid()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		IAPReturn[] iapReturn = pATable.getExternalIAP(db, 100000, "dummy", "InCommon");
		AssertJUnit.assertEquals(iapReturn.length, 0);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getExternalIap()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testGetExternalIAPInValidPesonIdUserid()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		IAPReturn[] iapReturn = pATable.getExternalIAP(db, 100000, "xxxx", "InCommon");
		AssertJUnit.assertNull(iapReturn);
		db.closeSession();
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test
	public final void testGetExternalInvalidFed()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		IAPReturn[] iapReturn = pATable.getExternalIAP(db,  100000, "dummy", "common");
		AssertJUnit.assertEquals(iapReturn.length, 0);
		db.closeSession();
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test
	@SuppressWarnings("unused")
	public final void testGetExternalValidFed()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		IAPReturn[] iapReturn = pATable.getExternalIAP(db,  100000, "dummy", "InCommon");
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test(expectedExceptions=Exception.class)
	@SuppressWarnings("unused")
	public final void testGetExternalValidFedNoDb()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		IAPReturn[] iapReturn = pATable.getExternalIAP(null, 751116,  "cpruser", "InCommon");
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testGetPSUIAPs()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		pATable.setReturnHistoryFlag(false);
		IAPReturn[] iapReturn = pATable.getPSUIAP(db, 1000000, "aa");
		AssertJUnit.assertNull(iapReturn);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test
	@SuppressWarnings("unused")
	public final void testGetPSUIAPValid()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		pATable.setReturnHistoryFlag(false);		
		IAPReturn[] iapReturn = pATable.getPSUIAP(db, 100000, "dummy");
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test
	@SuppressWarnings("unused")
	public final void testGetPSUIAPValidAll()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		pATable.setReturnHistoryFlag(true);		
		IAPReturn[] iapReturn = pATable.getPSUIAP(db, 100000, "dummy");
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test
	@SuppressWarnings("unused")
	public final void testGetPSUIAPValidNoIAP()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		pATable.setReturnHistoryFlag(false);		
		IAPReturn[] iapReturn = pATable.getPSUIAP(db, 100000, "dummy");
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonUseridIapTables#getPSUIAPs()}.
	 */
	@Test(expectedExceptions=Exception.class)
	@SuppressWarnings("unused")
	public final void testGetPSUIAPValidNoDB()  throws Exception  {
		openDbConnection();
		PersonUseridIapTable pATable = new PersonUseridIapTable();
		pATable.setReturnHistoryFlag(false);				
		IAPReturn[] iapReturn = pATable.getPSUIAP(null, 100002, "tuj20");
		db.closeSession();
	}
	
}
