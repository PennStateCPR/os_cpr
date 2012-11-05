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
import edu.psu.iam.cpr.core.database.tables.PsuIdTable;

/**
 * @author jvuccolo
 *
 */
public class PsuIdTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PsuIdTable#PsuIdTable()}.
	 */
	@Test
	public final void testPsuIdTable() {
		new PsuIdTable();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PsuIdTable#PsuIdTable(int, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testPsuIdTableIntStringString() {
		new PsuIdTable(100000, null, "jvuccolo");
	}

	@Test 
	public final void testGetSetId() {
		PsuIdTable p  = new PsuIdTable();
		p.setSetId(1);
		AssertJUnit.assertEquals(p.getSetId(),1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetPsuIdForPersonId1() throws Exception {
		PsuIdTable p = new PsuIdTable();
		p.setReturnHistoryFlag(false);
		p.getPsuIdForPersonId(db,100000);
	}
	
	@Test
	public final void testGetPsuIdForPersonId3() throws Exception {
		openDbConnection();
		PsuIdTable p = new PsuIdTable();
		p.setReturnHistoryFlag(false);
		p.getPsuIdForPersonId(db,2);
		db.closeSession();
	}

	@Test
	public final void testGetPsuIdForPersonId2() throws Exception {
		openDbConnection();
		PsuIdTable p = new PsuIdTable();
		p.setReturnHistoryFlag(false);
		p.getPsuIdForPersonId(db,100000);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testAddPsuId1() throws Exception {
		PsuIdTable p = new PsuIdTable(100000,null,"jvuccolo");
		p.addPsuIdForPersonId(null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testAddPsuId2() throws Exception {
		PsuIdTable p = new PsuIdTable(100000,null,"jvuccolo");
		p.addPsuIdForPersonId(db);
	}
	
	@Test
	public final void testAddPsuId3() throws Exception {
		openDbConnection();
		PsuIdTable p = new PsuIdTable(100000,null,"jvuccolo");
		p.addPsuIdForPersonId(db);
		db.closeSession();
	}
}
