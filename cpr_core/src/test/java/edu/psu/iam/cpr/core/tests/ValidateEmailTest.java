/* SVN FILE: $Id: ValidateEmailTest.java 5340 2012-09-27 14:48:52Z cpruser $ */
/**
 * 
 * @package edu.psu.iam.cpr.core.tests
 * @author $Author: cpruser $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
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
import org.testng.AssertJUnit;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateEmail;

/**
 * @author cpruser
 *
 */
public class ValidateEmailTest {

	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}


	@Test
	public final void testIsValidEmail1() {
		boolean retVal = ValidateEmail.isValidEmail(null);
		AssertJUnit.assertEquals(retVal, false);
	}
	@Test
	public final void testIsValidEmail2() {
		boolean retVal = ValidateEmail.isValidEmail("");
		AssertJUnit.assertEquals(retVal, false);
	}
	@Test
	public final void testIsValidEmail3() {
		boolean retVal = ValidateEmail.isValidEmail("abc123");
		AssertJUnit.assertEquals(retVal, false);
	}
	@Test
	public final void testIsValidEmail4() {
		boolean retVal = ValidateEmail.isValidEmail("abc123@");
		AssertJUnit.assertEquals(retVal, false);
	}
	@Test
	public final void testIsValidEmail5() {
		boolean retVal = ValidateEmail.isValidEmail("abc123@psu");
		AssertJUnit.assertEquals(retVal, false);
	}
	@Test
	public final void testIsValidEmail6() {
		boolean retVal = ValidateEmail.isValidEmail("abc123@psu.edu");
		AssertJUnit.assertEquals(retVal, true);
	}
	@Test
	public final void testIsValidEmail7() {
		boolean retVal = ValidateEmail.isValidEmail("a.cpruser@psu.edu");
		AssertJUnit.assertEquals(retVal, true);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters1() throws Exception {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, null, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters2() throws Exception {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home", null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters3() throws Exception {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home", "xyz123@psu.edu", null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters4() throws Exception {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home", "xyz123@psu.edu", "cpruser");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters5() throws Exception {
		try {
			openDbConnection();
			ValidateEmail.validateEmailAddressParameters(db, 0, "home_email", "xyz123psu.edu", "cpruser");
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			try {
				db.closeSession();
			}
			catch (Exception e) {

			}
		}
	}
	@Test
	public final void testValidateEmailAddressParameters6() throws Exception {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "UNIVERSITY_EMAIL", "xyz123@psu.edu", "cpruser");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters7() throws Exception {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home_email", "xyz123@psu.edu", "1234567890123456789012345678901234");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters8() throws Exception {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "student_email_home", "xyz123@psu.", "cpruser");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePrimaryEmailParameters1() throws Exception {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePrimaryEmailParameters2() throws Exception {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePrimaryEmailParameters3() throws Exception {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePrimaryEmailParameters4() throws Exception {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", "1234567890123456789012345678901234");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePrimaryEmailParameters5() throws Exception {
		try {
			openDbConnection();
			ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", "cpruser");
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			try {
				db.closeSession();
			}
			catch (Exception e) { }
		}
	}
	@Test
	public final void testValidatePrimaryEmailParameters6() throws Exception {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "UNIVERSITY_EMAIL", "cpruser");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetEmailAddress1() throws Exception {
		openDbConnection();
		ValidateEmail.validateGetEmailAddressParameters(db, 0, null,"N", null);
		db.closeSession();
	}
	
	@Test
	public final void testValidateGetEmailAddress2() throws Exception {
		openDbConnection();
		ValidateEmail.validateGetEmailAddressParameters(db, 0, "cpruser","N", null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetEmailAddress3() throws Exception {
		openDbConnection();
		ValidateEmail.validateGetEmailAddressParameters(db, 0, "1234567890123456789012345678901234","N", null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateDeleteEmailAddress1() throws Exception {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, null, null, null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateDeleteEmailAddress2() throws Exception {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "home", null, null);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateDeleteEmailAddress3() throws Exception {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "home", "vuccolo", null);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateDeleteEmailAddress5() throws Exception {
		try {
			openDbConnection();
			ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "home_email", "1234567890123456789012345678901234", null);
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			try {
				db.closeSession();
			}
			catch (Exception e) { }
		}
	}
	
	@Test
	public final void testValidateDeleteEmailAddress4() throws Exception {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "UNIVERSITY_EMAIL", "vuccolo", null);
		db.closeSession();
	}


	
}
