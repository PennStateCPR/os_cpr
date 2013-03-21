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


import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateUserid;

/**
 * @author cpruser
 *
 */
public class ValidateUseridTest {

	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _01testValidateUseridParameters1() throws Exception {
		ValidateUserid.validateUseridParameters(db, 100000, null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _02testValidateUseridParameters2() throws Exception {
		ValidateUserid.validateUseridParameters(db, 100000, "");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _03testValidateUseridParameters4() throws Exception {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 100000, "1234567890123456789012345678901");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test
	public final void _04testValidateUseridParameters3() throws Exception {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 100000, "cpruser");
		db.closeSession();
	}
	
	@Test
	public final void _05testValidateUseridParameters20() throws Exception {
		openDbConnection();
		UseridTable useridTable = ValidateUserid.validateGetUseridParameters(db, 100000, "cpruser","Y");
		assertTrue(useridTable.isReturnHistoryFlag());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _06testValidateUseridParameters5() throws Exception {
		ValidateUserid.validateUseridParameters(null, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _07testValidateUseridParameters6() throws Exception {
		ValidateUserid.validateUseridParameters(db, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _08testValidateUseridParameters7() throws Exception {
		ValidateUserid.validateUseridParameters(db, 0, "xyz", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _09testValidateUseridParameters8() throws Exception {
		ValidateUserid.validateUseridParameters(db, 0, "xyz", "cpruser");
	}
	@Test(expectedExceptions=Exception.class)
	public final void _10testValidateUseridParameters9() throws Exception {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 0, null, "cpruser");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _11testValidateUseridParameters10() throws Exception {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 0, "1234567890123456789012345678901234567890", "cpruser");
		db.closeSession();
	}
	@Test
	public final void _12testValidateUseridParameters11() throws Exception {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 0, "cpruser", "cpruser");
		db.closeSession();
	}
}
