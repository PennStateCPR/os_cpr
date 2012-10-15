
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
import java.util.UUID;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.service.returns.NameReturn;

/**
 * @author jvuccolo
 *
 */
public class NamesTableTest {

	private static Database db= new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.NamesTable#NamesTable()}.
	 */
	@Test
	public final void _01testNamesTable() {
		new NamesTable();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.NamesTable#NamesTable(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _02testNamesTableIntStringStringStringStringStringString() throws Exception {
		new NamesTable(1, "legal_name", "", "jeff", "", "jones", "", "jvuccolo" );
	}
	@Test
	public final void _03testNamesTableIntStringStringStringStringStringString1() throws Exception {
		new NamesTable(1, "legal_name", "", "jvuccolo" );
	}
	@Test
	public final void _04testGetNamesBean() {
		Names bean = new Names();
		NamesTable t = new NamesTable();
		t.setNamesBean(bean);
		AssertJUnit.assertEquals(t.getNamesBean(),bean);
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.NamesTable#getNameType()}.
	 */
	@Test
	public final void _05testGetNameType() {
		NamesTable n = new NamesTable();
		n.setNameType(NameType.LEGAL_NAME);
		AssertJUnit.assertEquals(n.getNameType(), NameType.LEGAL_NAME);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.NamesTable#setNameType(edu.psu.iam.cpr.core.database.types.NameType)}.
	 */
	@Test
	public final void _06testSetNameTypeNameType() {
		NamesTable n = new NamesTable();
		n.setNameType(NameType.LEGAL_NAME);
		AssertJUnit.assertEquals(n.getNameType(), NameType.LEGAL_NAME);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.NamesTable#setNameType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _07testSetNameTypeString1() throws Exception {
		NamesTable n = new NamesTable();
		n.setNameType("");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.NamesTable#setNameType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _08testSetNameTypeString2() throws Exception {
		NamesTable n = new NamesTable();
		n.setNameType("my name");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.NamesTable#setNameType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _09testSetNameTypeString3() throws Exception {
		NamesTable n = new NamesTable();
		n.setNameType("LEGAL_NAME");
		AssertJUnit.assertEquals(n.getNameType(), NameType.LEGAL_NAME);
	}

	
	@Test(expectedExceptions=Exception.class) 
	public final void _10testGetNamesForPersonId1() throws GeneralDatabaseException, CprException {
		NamesTable n = new NamesTable();
		n.getNamesForPersonId(db, 0);
	}
		
	@Test
	public final void _11testGetNamesForPersonId2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		NamesTable n = new NamesTable();
		n.setReturnHistoryFlag(false);
		NameReturn[] results = n.getNamesForPersonId(db, 100001);
		db.closeSession();
		AssertJUnit.assertEquals(results.length, 0);
	}
	
	@Test
	public final void _12testGetUseridsForPersonId3() throws GeneralDatabaseException, CprException {
		openDbConnection();
		NamesTable n = new NamesTable();
		n.setReturnHistoryFlag(true);
		n.getNamesForPersonId(db, 100002);
		db.closeSession();
	}
	
	@Test
	public final void _13testGetUseridsForPersonId4() throws GeneralDatabaseException, CprException {
		openDbConnection();
		NamesTable n = new NamesTable();
		n.setReturnHistoryFlag(true);
		n.setNameType(NameType.LEGAL_NAME);
		n.getNamesForPersonId(db, 100000);
		db.closeSession();
	}
	
	@Test(expectedExceptions=CprException.class)
	public final void _14testDeleteName1() throws GeneralDatabaseException, CprException {
		NamesTable n = new NamesTable();
		n.archiveName(null);
	}
	
//	@Test
//	public final void _15testSetMatchName() throws Exception {
//		NamesTable n = new NamesTable(10000, "LEGAL_NAME", "", "Jimmy", "", "Vuccolo", "","jvuccolo");
//		
//		AssertJUnit.assertEquals(n.getMatchName(),"Jimmy Vuccolo");
//	}

	@Test(expectedExceptions=Exception.class)
	public final void _16testDeleteName5() throws Exception {
		openDbConnection();
		NamesTable n =null;
		n = new NamesTable(10000, "LEGAL_NAME", "", "jvuccolo");
		n.archiveName(db);
		db.closeSession();
	}
	
//	@Test(expected=Exception.class)
//	public final void _17testDeleteName2() throws Exception {
//		openDbConnection();
//		NamesTable n = new NamesTable(100000, "LEGAL_NAME", "", "jvuccolo");
//		n.archiveName(db);
//		db.closeSession();
//	}
	
	@Test
	public final void _18testDeleteName3() throws Exception {
		NamesTable n = null;
		try {
			openDbConnection();
			n = new NamesTable(100000, "LEGAL_NAME", "", "ted", "eff", "vuccolo", "", "jvuccolo");
			n.addName(db);
			db.closeSession();
		}
		catch (Exception e) {
			
		}
		openDbConnection();
		n.archiveName(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _19testAddName0() throws Exception {
		NamesTable n = new NamesTable();
		n.addName(null);
	}

	@Test
	public final void _20testAddName() throws Exception {
		openDbConnection();
		NamesTable n = new NamesTable(100002, "LEGAL_NAME", "", "ted", "", UUID.randomUUID().toString() + "jeff", "", "jvuccolo");
		n.addName(db);
		db.closeSession();
	}
	
	@Test
	public final void _21testAddName2() throws Exception {
		openDbConnection();
		NamesTable n = new NamesTable(100002, "DOCUMENTED_NAME", "PASSPORT", "ted", "", UUID.randomUUID().toString() + "jeff", "", "jvuccolo");
		n.addName(db);
		db.closeSession();
	}
	
}
