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
import edu.psu.iam.cpr.core.util.ValidatePersonAffiliation;

public class ValidatePersonAffiliationTest {
	
	private static Database db = new Database();
	
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int,java.lang.String,java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = GeneralDatabaseException.class)
	public final void testValidateAddAffiliationParameters()
	throws CprException, GeneralDatabaseException {
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRAD_PROSPECT", "llg5");
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateAddAffiliationParametersBadParm2()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STU_UNDERGRAD_PROSPECT",  "llg5");
		db.closeSession();
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateAddAffiliationParametersNullParm2()
	throws CprException, GeneralDatabaseException {
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				null,  "llg5");
	}

	
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateAddAffiliationParametersBadParm2a()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRAD PROSPECT",  "llg5");
		db.closeSession();
	}
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateAddAffiliationParametersBadParm2b()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT",  "llg5");
		db.closeSession();
	}
	
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int,java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateAddAffiliationParametersBadParm3Null()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRAD_PROSPECT", null);
		db.closeSession();
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int,java.lang.String,  java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateAddAffiliationParametersBadParm3Blank()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRADUATE_PROSPECT", "");
		db.closeSession();
	}
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int,java.lang.String,  java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateAddAffiliationParametersBadParm32Long()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRADUATE_PROSPECT",  "asdfghjklqwertyuiopzxcvbnmasdfg");
		db.closeSession();
	}
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int,java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test
	public final void testValidateAddAffiliationParametersGood()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRADUATE_PROSPECT", "llg5");
		db.closeSession();
	}
	@Test(expectedExceptions = GeneralDatabaseException.class)
	public final void testValidateGetAffiliationForPersonIdParameters()
	throws CprException, GeneralDatabaseException {
		ValidatePersonAffiliation.validateGetAffiliationsForPersonIdParameters(db, 100001,
		"llg5","N");
	}
	@Test(expectedExceptions = CprException.class)
	public final void testValidatevalidateGetAffiliationForPersonIdParameterseParm2Null()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateGetAffiliationsForPersonIdParameters(db, 100001, null,"Y");
		db.closeSession();
	}
	
	@Test
	public final void testValidateGetAffiliationForPersonIdParametersGood()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateGetAffiliationsForPersonIdParameters(db, 100001,
		"llg5","N");
		db.closeSession();
	}
	@Test(expectedExceptions = GeneralDatabaseException.class)
	public final void testValidateDeleteAffiliationParameters()
	throws CprException, GeneralDatabaseException {
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT", "llg5");
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateDeleteAffiliationParametersBadParm2()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STU", "llg5");
		db.closeSession();
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateDeleteAffiliationParametersNullParm2()
	throws CprException, GeneralDatabaseException {
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, null, "llg5");
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int,java.lang.String,  java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateDeleteAffiliationParametersBadParm2Blank()
	throws CprException, GeneralDatabaseException {
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "", "llg5");
	}
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateDeleteAffiliationParametersBadParm2a()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRAD PROSPECT", "llg5");
		db.closeSession();
	}


	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test(expectedExceptions = CprException.class)
	public final void testValidateDeleteAffiliationParametersBadParm3()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT", null);
		db.closeSession();
	}

	@Test(expectedExceptions = CprException.class)
	public final void testValidateDeleteAffiliationParametersBadParm3Blank()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT","");
		db.closeSession();
	}
	@Test(expectedExceptions = CprException.class)
	public final void testValidateDeleteAffiliationParametersBadParm32Long()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRAD_PROSPECT", "asdfghjklqwertyuiopzxcvbnmasdfg");
		db.closeSession();
	}
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int,java.lang.String,  java.lang.String)}
	 * .
	 * 
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	@Test
	public final void testValidateDeleteAffiliationParametersGood()
	throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT","llg5");
		db.closeSession();
	}


}
