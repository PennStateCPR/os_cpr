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


import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.PersonLinkageTable;
import edu.psu.iam.cpr.core.util.ValidatePersonLinkage;

public class ValidatePersonLinkageTest {

	private static Database db = new Database();

	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPersonLinkageParameters1() throws Exception {
		ValidatePersonLinkage.validateGetPersonLinkageParameters(db, 100000L, null,"N");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPersonLinkageParameters2() throws Exception {
		ValidatePersonLinkage.validateGetPersonLinkageParameters(db, 100000L, "","N");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPersonLinkageParameters3() throws Exception {
		openDbConnection();
		ValidatePersonLinkage.validateGetPersonLinkageParameters(db, 100000L, "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111","N");
		db.closeSession();
	}
	@Test
	public final void testValidateGetPersonLinkageParameters4() throws Exception {
		openDbConnection();
		PersonLinkageTable personLinkageTable = ValidatePersonLinkage.validateGetPersonLinkageParameters(db, 100000L, "jvuccolo","Y");
		assertTrue(personLinkageTable.isReturnHistoryFlag());
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidatePersonLinkageParameters1() throws Exception {
		openDbConnection();
		ValidatePersonLinkage.validatePersonLinkageParameters(db, 100000L, "person_id", "1", null, "jvuccolo");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePersonLinkageParameters2() throws Exception {
		openDbConnection();
		ValidatePersonLinkage.validatePersonLinkageParameters(db, 100000L, "person_id", "100001", null, "jvuccolo");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePersonLinkageParameters3() throws Exception {
		openDbConnection();
		ValidatePersonLinkage.validatePersonLinkageParameters(db, 100000L, "person_id", "100002", null, "jvuccolo");
		db.closeSession();
	}
	
	@Test
	public final void testValidatePersonLinkageParameters4() throws Exception {
		openDbConnection();
		ValidatePersonLinkage.validatePersonLinkageParameters(db, 100000L, "person_id", "100002", "LINKAGE_TYPE_DEPENDANT", "jvuccolo");
		db.closeSession();
	}

}
