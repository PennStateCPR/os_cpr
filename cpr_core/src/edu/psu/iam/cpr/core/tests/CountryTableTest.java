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
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * @author llg5
 *
 */
public class CountryTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testGetCountryInfoCodeBadDB() throws GeneralDatabaseException, CprException {
	
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "TR2", "llg5");	
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testGetCountryInfoCodeNull() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, null, "llg5");
		AssertJUnit.assertEquals( cTable.getCountryBean().getCountryKey(), null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testGetCountryInfoCodeBlank() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "" , "llg5");
		AssertJUnit.assertEquals( cTable.getCountryBean().getCountryKey(), null);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testGetCountryInfoCodeBad() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "TR2", "llg5");
		AssertJUnit.assertEquals( cTable.getCountryBean().getCountryKey(), new Long(-1));
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCountryInfo(Database, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void testGetCountryInfoCodeGood() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CountryTable cTable = new CountryTable();
		cTable.getCountryInfo(db, "USA", "llg5");
		db.closeSession();
	}
	
	
	
}