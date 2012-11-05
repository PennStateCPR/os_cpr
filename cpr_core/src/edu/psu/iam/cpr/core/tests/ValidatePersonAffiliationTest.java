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
import edu.psu.iam.cpr.core.util.ValidatePersonAffiliation;

public class ValidatePersonAffiliationTest {
	
	private static Database db = new Database();
	
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int,java.lang.String,java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParameters()
	throws Exception {
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRAD_PROSPECT", "llg5");
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParametersBadParm2()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParametersNullParm2()
	throws Exception {
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				null,  "llg5");
	}

	
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateAddAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParametersBadParm2a()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParametersBadParm2b()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParametersBadParm3Null()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParametersBadParm3Blank()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateAddAffiliationParametersBadParm32Long()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
	public final void testValidateAddAffiliationParametersGood()
	throws Exception {
		openDbConnection();
		ValidatePersonAffiliation.validateAddAffiliationParameters(db, 100001,
				"STUDENT_UNDERGRADUATE_PROSPECT", "llg5");
		db.closeSession();
	}
	@Test(expectedExceptions = Exception.class)
	public final void testValidateGetAffiliationForPersonIdParameters()
	throws Exception {
		ValidatePersonAffiliation.validateGetAffiliationsForPersonIdParameters(db, 100001,
		"llg5","N");
	}
	@Test(expectedExceptions = Exception.class)
	public final void testValidatevalidateGetAffiliationForPersonIdParameterseParm2Null()
	throws Exception {
		openDbConnection();
		ValidatePersonAffiliation.validateGetAffiliationsForPersonIdParameters(db, 100001, null,"Y");
		db.closeSession();
	}
	
	@Test
	public final void testValidateGetAffiliationForPersonIdParametersGood()
	throws Exception {
		openDbConnection();
		ValidatePersonAffiliation.validateGetAffiliationsForPersonIdParameters(db, 100001,
		"llg5","N");
		db.closeSession();
	}
	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParameters()
	throws Exception {
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT", "llg5");
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParametersBadParm2()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParametersNullParm2()
	throws Exception {
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, null, "llg5");
	}

	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int,java.lang.String,  java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParametersBadParm2Blank()
	throws Exception {
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "", "llg5");
	}
	/**
	 * Test method for
	 * {@link edu.psu.iam.cpr.core.util.ValidatePersonAffiliation#validateDeleteAffiliationParameters(int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParametersBadParm2a()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParametersBadParm3()
	throws Exception {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT", null);
		db.closeSession();
	}

	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParametersBadParm3Blank()
	throws Exception {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT","");
		db.closeSession();
	}
	@Test(expectedExceptions = Exception.class)
	public final void testValidateDeleteAffiliationParametersBadParm32Long()
	throws Exception {
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
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
	public final void testValidateDeleteAffiliationParametersGood()
	throws Exception {
		openDbConnection();
		ValidatePersonAffiliation.validateArchiveAffiliationParameters(db, 
				100001, "STUDENT_UNDERGRADUATE_PROSPECT","llg5");
		db.closeSession();
	}


}
