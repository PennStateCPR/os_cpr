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
import edu.psu.iam.cpr.core.database.tables.CampusCsTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

public class CampusCsTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCampusInfo(java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
//	@Test (expectedExceptions=Exception.class)
//	public final void _1testGetCampusInfoNoDB() throws GeneralDatabaseException, CprException {
//		
//		CampusCsTable cTable = new CampusCsTable();
//		cTable.getCampusInfo(db, "UP", "llg5");
//	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCampusInfo(java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void _2testGetCampusInfoName() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CampusCsTable cTable = new CampusCsTable();
		cTable.getCampusInfo(db, "UP", "llg5");
		AssertJUnit.assertEquals( cTable.getCampusCsBean().getCampus(), "UNIVERSITY PARK CAMPUS");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCampusName(java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void _3testGetCampusInfoCode() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CampusCsTable cTable = new CampusCsTable();
		cTable.getCampusInfo(db, "UP", "llg5");
		AssertJUnit.assertEquals( cTable.getCampusCsBean().getCampusCode(), "UP");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCampusName(java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void _4testGetCampusInfoCodeKey() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CampusCsTable cTable = new CampusCsTable();
		cTable.getCampusInfo(db, "UP", "llg5");
		AssertJUnit.assertEquals( cTable.getCampusCsBean().getCampusCodeKey(), new Long(100048));
		db.closeSession();
	}
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCampusInfo(java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _5testGetCampusInfoBad() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CampusCsTable cTable = new CampusCsTable();
		cTable.getCampusInfo(db, "U1", "llg5");
		AssertJUnit.assertEquals( cTable.getCampusCsBean().getCampusCodeKey(), new Long(-1));
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCampusInfo(java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void _6testGetCampusInfoBadTestCodeKey() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CampusCsTable cTable = new CampusCsTable();
		cTable.getCampusInfo(db, null, "llg5");
		AssertJUnit.assertEquals( cTable.getCampusCsBean().getCampusCodeKey(), null);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.CampusCsTable#getCampusInfo(java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test
	public final void _7testGetCampusInfoBadTestCodeKeyBlank() throws GeneralDatabaseException, CprException {
		openDbConnection();
		CampusCsTable cTable = new CampusCsTable();
		cTable.getCampusInfo(db, "", "llg5");
		AssertJUnit.assertEquals( cTable.getCampusCsBean().getCampusCodeKey(), null);
		db.closeSession();
	}
	

}
