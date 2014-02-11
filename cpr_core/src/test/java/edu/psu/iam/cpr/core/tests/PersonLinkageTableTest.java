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
import edu.psu.iam.cpr.core.database.tables.PersonLinkageTable;

public class PersonLinkageTableTest {

	private static Database db= new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test(expectedExceptions=Exception.class)
	public final void testPersonLinkageTable1() throws Exception {
		new PersonLinkageTable(1L, 2L, null,null);
		
	}
	@Test(expectedExceptions=Exception.class)
	public final void testPersonLinkageTable2() throws Exception {
		new PersonLinkageTable(1L, 2L, "",null);
		
	}
	@Test
	public final void testPersonLinkageTable3() throws Exception {
		new PersonLinkageTable(1L, 2L, "LINKAGE_TYPE_DEPENDANT",null);
		
	}

	@Test
	public final void testGetPersonLinkageBean() throws Exception {
		PersonLinkageTable p = new PersonLinkageTable(1L, 2L, "LINKAGE_TYPE_DEPENDANT",null);
		AssertJUnit.assertNotNull(p.getPersonLinkageBean());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testAddPersonLinkage1() throws Exception {
		PersonLinkageTable p = new PersonLinkageTable(1L, 2L, "LINKAGE_TYPE_DEPENDANT","cpruser");
		p.addPersonLinkage(db);
	}
	
//	@Test(expected=Exception.class)
//	public final void testAddPersonLinkage2() throws Exception {
//		openDbConnection();
//		PersonLinkageTable p = new PersonLinkageTable(1L, 2L, "LINKAGE_TYPE_DEPENDANT","cpruser");
//		p.addPersonLinkage(db);
//		db.closeSession();
//	}
	
	@Test
	public final void testAddPersonLinkage3() throws Exception {
		openDbConnection();
		PersonLinkageTable p = new PersonLinkageTable(100000L, 100002L, "LINKAGE_TYPE_DEPENDANT","cpruser");
		p.addPersonLinkage(db);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testArchivePersonLinkage1() throws Exception {
		PersonLinkageTable p = new PersonLinkageTable(100000L, 100002L, "LINKAGE_TYPE_DEPENDANT","cpruser");
		p.archivePersonLinkage(db);

	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testArchivePersonLinkage2() throws Exception {
		openDbConnection();
		PersonLinkageTable p = new PersonLinkageTable(100001L, 100002L, "LINKAGE_TYPE_DEPENDANT","cpruser");
		p.archivePersonLinkage(db);
		db.closeSession();
	}
	
	@Test
	public final void testArchivePersonLinkage3() throws Exception {
		openDbConnection();
		PersonLinkageTable p = new PersonLinkageTable(100000L, 100002L, "LINKAGE_TYPE_DEPENDANT","cpruser");
		p.archivePersonLinkage(db);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testArchivePersonLinkage4() throws Exception {
		try {
		openDbConnection();
		PersonLinkageTable p = new PersonLinkageTable(100000L, 100002L, "LINKAGE_TYPE_DEPENDANT","cpruser");
		p.archivePersonLinkage(db);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		finally {
			try {
				db.closeSession();
			}
			catch (Exception e) {
				
			}
		}
	}

	@Test(expectedExceptions=Exception.class)
	public final void testGetPersonLinkage1() throws Exception {
		PersonLinkageTable p = new PersonLinkageTable();
		p.getPersonLinkage(db, 1L);
		
	}
	
	@Test
	public final void testGetPersonLinkage2() throws Exception {
		openDbConnection();
		PersonLinkageTable p = new PersonLinkageTable();
		p.setReturnHistoryFlag(false);
		p.getPersonLinkage(db, 1L);
		db.closeSession();
		
	}
	@Test
	public final void testGetPersonLinkage3() throws Exception {
		openDbConnection();
		PersonLinkageTable p = new PersonLinkageTable();
		p.setReturnHistoryFlag(true);
		p.getPersonLinkage(db, 100000L);
		db.closeSession();
		
	}

}
