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
import edu.psu.iam.cpr.core.database.beans.Person;
import edu.psu.iam.cpr.core.database.tables.PersonTable;

/**
 * @author cpruser
 *
 */
public class PersonTableTest {

	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test
	public final void testPersonTable() {
		new PersonTable();
	}
	@Test
	public final void testPersonTable1() {
		new PersonTable("cpruser");
	}
	@Test 
	public final void testGetPersonBean() {
		Person bean = new Person();
		PersonTable t = new PersonTable();
		t.setPersonBean(bean);
		AssertJUnit.assertEquals(t.getPersonBean(),bean);
		
	}
	@Test
	public final void testPersonTableInt() {
		new PersonTable(12345, "cpruser");
	}

	@Test(expectedExceptions=Exception.class)
	public final void testAddPerson1() throws Exception {
		PersonTable p = new PersonTable("cpruser");
		p.addPerson(db);
	}
	
	@Test
	public final void testAddPerson2() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable("cpruser");
		p.addPerson(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testDeletePerson1() throws Exception {
		PersonTable p = new PersonTable(1000,"cpruser");
		p.archivePerson(db);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testDeletePerson2() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable(1000,"cpruser");
		p.archivePerson(db);
		db.closeSession();
	}
	
	@Test
	public final void testDeletePerson3() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable(100000,"cpruser");
		p.archivePerson(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testDeletePerson4() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable(100000,"cpruser");
		p.archivePerson(db);
		db.closeSession();
	}
	
	
	@Test(expectedExceptions=Exception.class)
	public final void testUnarchivePerson1() throws Exception {
		PersonTable p = new PersonTable();
		p.unarchivePerson(db);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testUnarchivePerson4() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable(1000,"cpruser");
		p.unarchivePerson(db);
		db.closeSession();
	}
	
	@Test
	public final void testUnarchivePerson2() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable(100000,"cpruser");
		p.unarchivePerson(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testUnarchivePerson3() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable(100000,"cpruser");
		p.unarchivePerson(db);
		db.closeSession();
	}
	
	@Test
	public final void testAddPerson3() throws Exception {
		openDbConnection();
		PersonTable p = new PersonTable("cpruser");
		p.addPerson(db);
		db.closeSession();
	}

}
