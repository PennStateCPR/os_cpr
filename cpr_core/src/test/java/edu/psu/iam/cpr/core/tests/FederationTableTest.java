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
import edu.psu.iam.cpr.core.database.tables.FederationTable;


public class FederationTableTest {

	
	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void  testIsFederationInValid() throws Exception {
		openDbConnection();	
		FederationTable aFed = new FederationTable();
		AssertJUnit.assertTrue( aFed.isFederationValid(db, "InCommonIncommonOutCommonOutCommon"));
		db.closeSession();
	}
	@Test
	public final void  testIsFederationValid() throws Exception {
		openDbConnection();	
		FederationTable aFed = new FederationTable();
		AssertJUnit.assertTrue( aFed.isFederationValid(db, "InCommon"));
		db.closeSession();
	}
	@Test
	public final void  testIsFederationValidUpper() throws Exception {
		openDbConnection();	
		FederationTable aFed = new FederationTable();
		AssertJUnit.assertTrue( aFed.isFederationValid(db, "INCOMMON"));
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void  testIsFederationValidNot() throws  Exception {
		openDbConnection();	
		FederationTable aFed = new FederationTable();
		AssertJUnit.assertFalse( aFed.isFederationValid(db, "InComm"));
		db.closeSession();
	}
	@Test
	public final void  testIsFederationValidLowerCase() throws  Exception {
		openDbConnection();	
		FederationTable aFed = new FederationTable();
		AssertJUnit.assertTrue( aFed.isFederationValid(db, "incommon"));
		db.closeSession();
	}
}
