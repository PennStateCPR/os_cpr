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



import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.util.ValidateConfidentiality;

/**
 * @author jvuccolo
 *
 */
public class ValidateConfidentialityTest {

	private static Database db= new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateConfidentiality#validateAddConfidentialityParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters1() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateAddConfidentialityParameters(null, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters2() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters3() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "abcd", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters3a() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, null, "abcd");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters4() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters5() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters6() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "123456789012345678901234567890123456789012345678901234567890");
		db.closeSession();
	}
	@Test
	public final void testValidateAddConfidentialityParameters7() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "jvuccolo");
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateConfidentiality#validateArchiveConfidentialityParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters1() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(null, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters2() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters3() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "abcd", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters3a() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, null, "abcd");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters4() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters5() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "123456789012345678901234567890123456789012345678901234567890");
		db.closeSession();
	}
	@Test
	public final void testValidateArchiveConfidentialityParameters6() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "jvuccolo");
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateConfidentiality#validateGetConfidentialityParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters1() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateGetConfidentialityParameters(null, 0, null,null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters2() throws CprException, GeneralDatabaseException {
		ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, null,null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "123456789012345678901234567890123456789012345678901234567890",null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "jvuccolo","x");
		db.closeSession();
	}
	@Test
	public final void testValidateGetConfidentialityParameters5() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ConfidentialityTable confidentialityTable = ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "jvuccolo","Y");
		assertTrue(confidentialityTable.isReturnHistoryFlag());
		db.closeSession();
	}
	@Test
	public final void testValidateGetConfidentialityParameters6() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ConfidentialityTable confidentialityTable = ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "jvuccolo","n");
		assertFalse(confidentialityTable.isReturnHistoryFlag());
		db.closeSession();
	}

}

