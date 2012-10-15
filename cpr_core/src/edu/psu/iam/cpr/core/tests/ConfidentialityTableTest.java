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
import edu.psu.iam.cpr.core.database.beans.Confidentiality;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.database.types.ConfidentialityType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * @author jvuccolo
 *
 */
public class ConfidentialityTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.ConfidentialityTable#ConfidentialityTable()}.
	 */
	@Test
	public final void _01testConfidentialityTable() {
		new ConfidentialityTable();
	}

	@Test 
	public final void _02testGetConfidentialityBean() {
		Confidentiality bean = new Confidentiality();
		ConfidentialityTable t = new ConfidentialityTable();
		t.setConfidentialityBean(bean);
		AssertJUnit.assertEquals(t.getConfidentialityBean(),bean);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.ConfidentialityTable#ConfidentialityTable(int, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _03testConfidentialityTableIntStringStringString1() throws Exception {
		new ConfidentialityTable(0,null,null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _04testConfidentialityTableIntStringStringString2() throws Exception {
		new ConfidentialityTable(0,"test",null);
	}
	@Test
	public final void _05testConfidentialityTableIntStringStringString3() throws Exception {
		new ConfidentialityTable(0,"ALL_CONFIDENTIALITY",null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _06testGetConfidentialityType2() throws Exception {
		ConfidentialityTable t = new ConfidentialityTable();
		t.setConfidentialityType("abcd");
		AssertJUnit.assertEquals(t.getConfidentialityType(),ConfidentialityType.ALL_CONFIDENTIALITY);
	}
	
	@Test
	public final void _07testGetConfidentialityType3() throws Exception {
		ConfidentialityTable t = new ConfidentialityTable();
		t.setConfidentialityType("ALL_CONFIDENTIALITY");
		AssertJUnit.assertEquals(t.getConfidentialityType(),ConfidentialityType.ALL_CONFIDENTIALITY);
	}


	@Test(expectedExceptions=Exception.class)
	public final void _08testAddConfidentiality1() throws Exception {
		ConfidentialityTable t = new ConfidentialityTable(100000,"ALL_CONFIDENTIALITY","jvuccolo");
		t.addConfidentiality(db);
	}
	
	@Test
	public final void _09testAddConfidentiality2() throws Exception {
		openDbConnection();
		ConfidentialityTable t = new ConfidentialityTable(100000,"ALL_CONFIDENTIALITY","jvuccolo");
		t.addConfidentiality(db);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _10testArchiveConfidentiality1() throws Exception {
		ConfidentialityTable t = new ConfidentialityTable(100000,"ALL_CONFIDENTIALITY","jvuccolo");
		t.archiveConfidentiality(db);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _11testArchiveConfidentiality2() throws Exception {
		openDbConnection();
		ConfidentialityTable t = new ConfidentialityTable(100002,"ALL_CONFIDENTIALITY","jvuccolo");
		t.archiveConfidentiality(db);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _12testGetConfidentiality1() throws CprException, GeneralDatabaseException {
		db.closeSession();
		ConfidentialityTable t = new ConfidentialityTable();
		t.getConfidentiality(db,100000);
	}
	
	@Test
	public final void _13testAddConfidentiality3() throws Exception {
		openDbConnection();
		ConfidentialityTable t = new ConfidentialityTable(100000,"ALL_CONFIDENTIALITY","jvuccolo");
		t.archiveConfidentiality(db);
		t.setReturnHistoryFlag(false);
		t.getConfidentiality(db,100000);
		t.addConfidentiality(db);
		db.closeSession();
	}
	
	@Test
	public final void _14testGetConfidentiality2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ConfidentialityTable t = new ConfidentialityTable();
		t.setReturnHistoryFlag(true);
		t.getConfidentiality(db,100000);
		db.closeSession();
		
	}

}
