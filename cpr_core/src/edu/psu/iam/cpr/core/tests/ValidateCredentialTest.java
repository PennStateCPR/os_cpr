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
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.CredentialTable;
import edu.psu.iam.cpr.core.database.types.CredentialType;
import edu.psu.iam.cpr.core.util.ValidateCredential;

public class ValidateCredentialTest {

	private static Database db = new Database();
	
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetCredentialParameters1() throws Exception {
		ValidateCredential.validateGetCredentialParameters(null, 1L, null,null,null);
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetCredentialParameters2() throws Exception {
		openDbConnection();
		ValidateCredential.validateGetCredentialParameters(db, 1L, null,null,"Y");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetCredentialParameters3() throws Exception {
		openDbConnection();
		ValidateCredential.validateGetCredentialParameters(db, 1L, "1234567890123456789012345678901234567890",null,"Y");
		db.closeSession();
	}
	
	@Test
	public final void testValidateGetCredentialParameters4() throws Exception {
		openDbConnection();
		CredentialTable credentialTable = ValidateCredential.validateGetCredentialParameters(db, 1L, "jvuccolo",null,"Y");
		assertTrue(credentialTable.isReturnHistoryFlag());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetCredentialParameters5() throws Exception {
		openDbConnection();
		CredentialTable credentialTable = ValidateCredential.validateGetCredentialParameters(db, 1L, "jvuccolo","abcd","Y");
		assertTrue(credentialTable.isReturnHistoryFlag());
		db.closeSession();
	}
	
	@Test
	public final void testValidateGetCredentialParameters6() throws Exception {
		openDbConnection();
		CredentialTable credentialTable = ValidateCredential.validateGetCredentialParameters(db, 1L, "jvuccolo","CREDENTIAL_TYPE_OPENID","N");
		assertFalse(credentialTable.isReturnHistoryFlag());
		assertEquals(credentialTable.getCredentialType(),CredentialType.CREDENTIAL_TYPE_OPENID);
		db.closeSession();
	}
	

	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddCredentialParameters1() throws Exception {
		ValidateCredential.validateAddCredentialParameters(null, 1L, null, null, null);
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddCredentialParameters2() throws Exception {
		openDbConnection();
		ValidateCredential.validateAddCredentialParameters(db, 1L, null, null, "jvuccolo");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddCredentialParameters3() throws Exception {
		openDbConnection();
		ValidateCredential.validateAddCredentialParameters(db, 1L, "secure_id", "abcd", "jvuccolo");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddCredentialParameters4() throws Exception {
		openDbConnection();
		ValidateCredential.validateAddCredentialParameters(db, 1L, "credential_type_secureid", "", "jvuccolo");
		db.closeSession();
	}
	
	@Test
	public final void testValidateAddCredentialParameters5() throws Exception {
		openDbConnection();
		ValidateCredential.validateAddCredentialParameters(db, 1L, "credential_type_secureid", "11231", "jvuccolo");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveCredentialParameters1() throws Exception {
		ValidateCredential.validateArchiveCredentialParameters(null, 1L, null, null);
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveCredentialParameters2() throws Exception {
		openDbConnection();
		ValidateCredential.validateArchiveCredentialParameters(db, 1L, null, "jvuccolo");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveCredentialParameters3() throws Exception {
		openDbConnection();
		ValidateCredential.validateArchiveCredentialParameters(db, 1L, "secureid", "jvuccolo");
		db.closeSession();
	}
	
	@Test
	public final void testValidateArchiveCredentialParameters4() throws Exception {
		openDbConnection();
		ValidateCredential.validateArchiveCredentialParameters(db, 1L, "credential_type_secureid", "jvuccolo");
		db.closeSession();
	}

}
