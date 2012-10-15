/* SVN FILE: $Id: ValidateEmailTest.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
 * 
 * @package edu.psu.iam.cpr.core.tests
 * @author $Author: jvuccolo $
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
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.util.ValidateEmail;

/**
 * @author jvuccolo
 *
 */
public class ValidateEmailTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
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
		boolean retVal = ValidateEmail.isValidEmail("a.jvuccolo@psu.edu");
		AssertJUnit.assertEquals(retVal, true);
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidateEmailAddressParameters1() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, null, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidateEmailAddressParameters2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home", null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidateEmailAddressParameters3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home", "xyz123@psu.edu", null);
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidateEmailAddressParameters4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home", "xyz123@psu.edu", "jvuccolo");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateEmailAddressParameters5() throws Exception {
		try {
			openDbConnection();
			ValidateEmail.validateEmailAddressParameters(db, 0, "home_email", "xyz123psu.edu", "jvuccolo");
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
	public final void testValidateEmailAddressParameters6() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "UNIVERSITY_EMAIL", "xyz123@psu.edu", "jvuccolo");
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidateEmailAddressParameters7() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "home_email", "xyz123@psu.edu", "1234567890123456789012345678901234");
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidateEmailAddressParameters8() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateEmailAddressParameters(db, 0, "student_email_home", "xyz123@psu.", "jvuccolo");
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidatePrimaryEmailParameters1() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidatePrimaryEmailParameters2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", null);
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidatePrimaryEmailParameters3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", null);
		db.closeSession();
	}
	@Test(expectedExceptions=CprException.class)
	public final void testValidatePrimaryEmailParameters4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", "1234567890123456789012345678901234");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidatePrimaryEmailParameters5() throws Exception {
		try {
			openDbConnection();
			ValidateEmail.validatePrimaryEmailParameters(db, 0, "home", "jvuccolo");
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
	public final void testValidatePrimaryEmailParameters6() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validatePrimaryEmailParameters(db, 0, "UNIVERSITY_EMAIL", "jvuccolo");
		db.closeSession();
	}

	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetEmailAddress1() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateGetEmailAddressParameters(db, 0, null,"N");
		db.closeSession();
	}
	
	@Test
	public final void testValidateGetEmailAddress2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateGetEmailAddressParameters(db, 0, "jvuccolo","N");
		db.closeSession();
	}
	
	@Test(expectedExceptions=CprException.class)
	public final void testValidateGetEmailAddress3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateGetEmailAddressParameters(db, 0, "1234567890123456789012345678901234","N");
		db.closeSession();
	}
	
	@Test(expectedExceptions=CprException.class)
	public final void testValidateDeleteEmailAddress1() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, null, null);
		db.closeSession();
	}

	@Test(expectedExceptions=CprException.class)
	public final void testValidateDeleteEmailAddress2() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "home", null);
		db.closeSession();
	}

	@Test(expectedExceptions=CprException.class)
	public final void testValidateDeleteEmailAddress3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "home", "vuccolo");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateDeleteEmailAddress5() throws Exception {
		try {
			openDbConnection();
			ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "home_email", "1234567890123456789012345678901234");
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
	public final void testValidateDeleteEmailAddress4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidateEmail.validateArchiveEmailAddressParameters(db, 0, "UNIVERSITY_EMAIL", "vuccolo");
		db.closeSession();
	}


	
}
