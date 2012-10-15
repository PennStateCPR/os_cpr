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
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.util.ValidateUserid;

/**
 * @author jvuccolo
 *
 */
public class ValidateUseridTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws CprException 
	 * @throws GeneralDatabaseException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _01testValidateUseridParameters1() throws CprException, GeneralDatabaseException {
		ValidateUserid.validateUseridParameters(db, 100000, null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws CprException 
	 * @throws GeneralDatabaseException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _02testValidateUseridParameters2() throws CprException, GeneralDatabaseException {
		ValidateUserid.validateUseridParameters(db, 100000, "");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws CprException 
	 * @throws GeneralDatabaseException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _03testValidateUseridParameters4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 100000, "1234567890123456789012345678901");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateUserid#validateUseridParameters(int, java.lang.String)}.
	 * @throws CprException 
	 * @throws GeneralDatabaseException 
	 */
	@Test
	public final void _04testValidateUseridParameters3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 100000, "jvuccolo");
		db.closeSession();
	}
	
	@Test
	public final void _05testValidateUseridParameters20() throws CprException, GeneralDatabaseException {
		openDbConnection();
		UseridTable useridTable = ValidateUserid.validateGetUseridParameters(db, 100000, "jvuccolo","Y");
		assertTrue(useridTable.isReturnHistoryFlag());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _06testValidateUseridParameters5() throws CprException, GeneralDatabaseException {
		ValidateUserid.validateUseridParameters(null, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _07testValidateUseridParameters6() throws CprException, GeneralDatabaseException {
		ValidateUserid.validateUseridParameters(db, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _08testValidateUseridParameters7() throws CprException, GeneralDatabaseException {
		ValidateUserid.validateUseridParameters(db, 0, "xyz", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void _09testValidateUseridParameters8() throws CprException, GeneralDatabaseException {
		ValidateUserid.validateUseridParameters(db, 0, "xyz", "jvuccolo");
	}
	@Test(expectedExceptions=Exception.class)
	public final void _10testValidateUseridParameters9() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 0, null, "jvuccolo");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _11testValidateUseridParameters10() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 0, "1234567890123456789012345678901234567890", "jvuccolo");
		db.closeSession();
	}
	@Test
	public final void _12testValidateUseridParameters11() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateUserid.validateUseridParameters(db, 0, "jvuccolo", "jvuccolo");
		db.closeSession();
	}
}
