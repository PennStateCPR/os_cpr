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
import edu.psu.iam.cpr.core.database.beans.PsuDirectory;
import edu.psu.iam.cpr.core.database.tables.PsuDirectoryTable;

/**
 * @author cpruser
 *
 */
public class PsuDirectoryTableTest {
	
	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PsuDirectoryTable#PsuDirectoryTable()}.
	 */
	@Test
	public final void testPsuDirectoryTable() {
		new PsuDirectoryTable();
	}
	
	@Test
	public final void testPsuDirectoryTable1() {
		new PsuDirectoryTable(1L, "cpruser", "cpruser");
	}
	
	@Test
	public final void testGetPsuDirectoryTableBean() {
		PsuDirectory bean = new PsuDirectory();
		PsuDirectoryTable t = new PsuDirectoryTable();
		t.setPsuDirectoryBean(bean);
		AssertJUnit.assertNotNull(t.getPsuDirectoryBean());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetDirectoryTable1() throws Exception {
		PsuDirectoryTable t = new PsuDirectoryTable();
		t.getPsuDirectoryTable(db,10L);
	}

	@Test(expectedExceptions=Exception.class)
	public final void testGetDirectoryTable2() throws Exception {
		openDbConnection();
		PsuDirectoryTable t = new PsuDirectoryTable();
		t.getPsuDirectoryTable(db,10L);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testAddDirectoryTable1() throws Exception {
		PsuDirectoryTable t = new PsuDirectoryTable(100000,"dummy","cpruser");
		t.addDirectoryTable(db);
		
	}
	@Test(expectedExceptions=Exception.class)
	public final void testAddDirectoryTable2() throws Exception {
		openDbConnection();
		PsuDirectoryTable t = new PsuDirectoryTable(100000,"dummy","cpruser");
		t.addDirectoryTable(db);
		db.closeSession();
	}
	
	@Test
	public final void testGetDirectoryTable3() throws Exception {
		openDbConnection();
		PsuDirectoryTable t = new PsuDirectoryTable();
		t.getPsuDirectoryTable(db,100000L);
		db.closeSession();
	}
	
	
	@Test
	public final void testAddDirectoryTable3() throws Exception {
		openDbConnection();
		PsuDirectoryTable t = new PsuDirectoryTable(100000,"dummy","cpruser");
		t.junitCleanUp(db);
		db.closeSession();
		
		openDbConnection();
		t.addDirectoryTable(db);
		db.closeSession();
	}
	
	@Test
	public final void testAddDirectoryTable4() throws Exception {
		openDbConnection();
		PsuDirectoryTable t = new PsuDirectoryTable(100002,"cpruser");
		t.junitCleanUp(db);
		db.closeSession();
		
		openDbConnection();
		t.addDirectoryTable(db);
		db.closeSession();
	}
	
	@Test
	public final void testAddDirectoryTable5() throws Exception {
		openDbConnection();
		PsuDirectoryTable t = new PsuDirectoryTable(100002,"tuj20", "cpruser");
		t.addDirectoryTable(db);
		db.closeSession();
	}
}
