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
import edu.psu.iam.cpr.core.util.ValidatePerson;

public class ValidatePersonTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPersonParameters1() throws Exception {
		ValidatePerson.validatePersonParameters(db, 1, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPersonParameters2() throws Exception {
		ValidatePerson.validatePersonParameters(db, 1, "jvuccolo");
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPersonParameters3() throws Exception {
		openDbConnection();
		ValidatePerson.validatePersonParameters(db, 1, "12345678901234567890123456789012234567890");
		db.closeSession();
	}
	@Test
	public final void testValidateAddPersonParameters4() throws Exception {
		openDbConnection();
		ValidatePerson.validatePersonParameters(db, 1, "jvuccolo");
		db.closeSession();
	}

}
