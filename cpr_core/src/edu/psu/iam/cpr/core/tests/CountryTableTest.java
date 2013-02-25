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
import edu.psu.iam.cpr.core.database.tables.CountryTable;

/**
 * @author cpruser
 *
 */
public class CountryTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testGetCountryInfoCodeBadDB() throws Exception {
	
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "TR2", "cpruser");	
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetCountryInfoCodeNull() throws Exception {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, null, "cpruser");
		AssertJUnit.assertEquals( cTable.getCountryBean().getCountryKey(), null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetCountryInfoCodeBlank() throws Exception {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "" , "cpruser");
		AssertJUnit.assertEquals( cTable.getCountryBean().getCountryKey(), null);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testGetCountryInfoCodeBad() throws Exception {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "TR2", "cpruser");
		AssertJUnit.assertEquals( cTable.getCountryBean().getCountryKey(), new Long(-1));
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetCountryInfoCodeGood() throws Exception {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "USA", "cpruser");
		db.closeSession();
	}
	
	
	
}
