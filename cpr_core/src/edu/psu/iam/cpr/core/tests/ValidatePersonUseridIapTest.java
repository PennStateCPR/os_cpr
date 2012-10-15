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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.util.ValidatePersonUseridIap;

/**
 * @author llg5
 *
 */
public class ValidatePersonUseridIapTest {
	
	private static Database db = new Database();

	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIapP#validateGetPsuIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetPsuIapParametersRequestedByNull() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetPsuIapParameters(db, 1000000,"tuj139", null,"N");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetPsuIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetPsuIapParametersRequestedByBlank() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetPsuIapParameters(db, 1000000,"tuj139", "","N");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetPsuIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetPsuIapParametersRequestedByTooLong() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetPsuIapParameters(db, 1000000, "tuj139","1234567890123456789012345678901","N");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIapP#validateGetPsuIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetPsuIapParametersUseridNull() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetPsuIapParameters(db, 1000000,null, "SYSTEM","Y");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetPsuIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetPsuIapParametersUseridBlank() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetPsuIapParameters(db, 1000000,"", "SYSTEM","Y");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetPsuIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetPsuIapParametersUseridTooLong() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetPsuIapParameters(db, 1000000, "1234567890123456789012345678901", "SYSTEM","N");
		db.closeSession();
	}
	
	
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersRequestedByBlank() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000,"tuj139", "", "federation");
		db.closeSession();
	}
	
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersRequestedByTooLong() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000, "tuj139","1234567890123456789012345678901", "Federation");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIapP#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersRequestedByNull() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000,"tuj139", null, "federation");
		db.closeSession();
	}
	

	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersUseridBlank() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000,"", "SYSTEM", "federation");
		db.closeSession();
	}
	
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersUseridTooLong() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000, "1234567890123456789012345678901","SYSTEM",  "Federation");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIapP#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersUseridNull() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000, null,"SYSTEM", "federation");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersFederationBlank() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000,"tuj139", "SYSTEM", "" +
				"");
		db.closeSession();
	}
	
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIap#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersFederationTooLong() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000, "tuj139","SYSTEM",  "1234567890123456789012345678901");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidatePersonUseridIapP#validateGetExternalIapParameters(edu.psu.iam.cpr.core.database.Database, int, java.lang.String, java.lang.String)}.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetExternalIapParametersFederationNull() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidatePersonUseridIap.validateGetExternalIapParameters(db, 1000000,"tuj139" ,"SYSTEM", null);
		db.closeSession();
	}
}
