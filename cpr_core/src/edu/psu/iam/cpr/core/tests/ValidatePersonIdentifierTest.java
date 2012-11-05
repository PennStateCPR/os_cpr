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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.util.ValidatePersonIdentifier;

public class ValidatePersonIdentifierTest {

	private static Database db = new Database();

	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPersonIdentifierParameters1() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateGetPersonIdentifierParameters(db, 1L, null, null, null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPersonIdentifierParameters3() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateGetPersonIdentifierParameters(db, 1L, null, "jvuccolo", "X");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPersonIdentifierParameters4() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateGetPersonIdentifierParameters(db, 1L, "abcd", "jvuccolo", "Y");
		db.closeSession();
	}

	@Test
	public final void testValidateGetPersonIdentifierParameters5() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateGetPersonIdentifierParameters(db, 1L, null, "jvuccolo", "n");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchivePersonIdentifierParameters1() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateArchivePersonIdentifierParameters(db, 1L, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchivePersonIdentifierParameters2() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateArchivePersonIdentifierParameters(db, 1L, null, "abcd");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchivePersonIdentifierParameters3() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateArchivePersonIdentifierParameters(db, 1L, "abcd", "abcd");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchivePersonIdentifierParameters4() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateArchivePersonIdentifierParameters(db, 1L, "userid", "jvuccolo");
		db.closeSession();
	}
	
	@Test
	public final void testValidateArchivePersonIdentifierParameters5() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateArchivePersonIdentifierParameters(db, 1L, "unit_test_identifier", "jvuccolo");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPersonIdentifierParameters1() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateAddPersonIdentifierParameters(db, 1L, "unit_test_identifier", null, "jvuccolo");
		db.closeSession();
	}
	
	@Test
	public final void testValidateAddPersonIdentifierParameters2() throws Exception {
		openDbConnection();
		ValidatePersonIdentifier.validateAddPersonIdentifierParameters(db, 1L, "unit_test_identifier", "abcd", "jvuccolo");
		db.closeSession();
	}

}
