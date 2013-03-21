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
import edu.psu.iam.cpr.core.database.tables.validate.ValidateConfidentiality;

/**
 * @author cpruser
 *
 */
public class ValidateConfidentialityTest {

	private static Database db= new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateConfidentiality#validateAddConfidentialityParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters1() throws Exception {
		ValidateConfidentiality.validateAddConfidentialityParameters(null, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters2() throws Exception {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters3() throws Exception {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "abcd", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters3a() throws Exception {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, null, "abcd");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters4() throws Exception {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters5() throws Exception {
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddConfidentialityParameters6() throws Exception {
		openDbConnection();
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "123456789012345678901234567890123456789012345678901234567890");
		db.closeSession();
	}
	@Test
	public final void testValidateAddConfidentialityParameters7() throws Exception {
		openDbConnection();
		ValidateConfidentiality.validateAddConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "cpruser");
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateConfidentiality#validateArchiveConfidentialityParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters1() throws Exception {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(null, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters2() throws Exception {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters3() throws Exception {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "abcd", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters3a() throws Exception {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, null, "abcd");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters4() throws Exception {
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveConfidentialityParameters5() throws Exception {
		openDbConnection();
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "123456789012345678901234567890123456789012345678901234567890");
		db.closeSession();
	}
	@Test
	public final void testValidateArchiveConfidentialityParameters6() throws Exception {
		openDbConnection();
		ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 0, "ALL_CONFIDENTIALITY", "cpruser");
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateConfidentiality#validateGetConfidentialityParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters1() throws Exception {
		ValidateConfidentiality.validateGetConfidentialityParameters(null, 0, null,null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters2() throws Exception {
		ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, null,null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters3() throws Exception {
		openDbConnection();
		ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "123456789012345678901234567890123456789012345678901234567890",null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetConfidentialityParameters4() throws Exception {
		openDbConnection();
		ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "cpruser","x");
		db.closeSession();
	}
	@Test
	public final void testValidateGetConfidentialityParameters5() throws Exception {
		openDbConnection();
		ConfidentialityTable confidentialityTable = ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "cpruser","Y");
		assertTrue(confidentialityTable.isReturnHistoryFlag());
		db.closeSession();
	}
	@Test
	public final void testValidateGetConfidentialityParameters6() throws Exception {
		openDbConnection();
		ConfidentialityTable confidentialityTable = ValidateConfidentiality.validateGetConfidentialityParameters(db, 0, "cpruser","n");
		assertFalse(confidentialityTable.isReturnHistoryFlag());
		db.closeSession();
	}

}

