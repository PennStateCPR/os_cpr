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
import edu.psu.iam.cpr.core.database.beans.Credential;
import edu.psu.iam.cpr.core.database.tables.CredentialTable;
import edu.psu.iam.cpr.core.database.types.CredentialType;
import edu.psu.iam.cpr.core.service.returns.CredentialReturn;

public class CredentialTableTest {

	private static Database db = new Database();
	
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test
	public final void _01testCredentialTable() {
		new CredentialTable();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _02testCredentialTableLongStringString1() throws Exception {
		new CredentialTable(1L, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _03testCredentialTableLongStringString2() throws Exception {
		new CredentialTable(1L, null, "cpruser");
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _04testCredentialTableLongStringString3() throws Exception {
		new CredentialTable(1L, "secure_id", "cpruser");
	}

	@Test
	public final void _05testCredentialTableLongStringString4() throws Exception {
		new CredentialTable(1L, "credential_type_secureid", "cpruser");
	}

	@Test
	public final void _06testCredentialTableLongStringStringString1() throws Exception {
		new CredentialTable(1L, "credential_type_secureid", "abcd", "cpruser");
	}

	@Test
	public final void _08testGetCredentialBean() {
		Credential bean = new Credential();
		CredentialTable t = new CredentialTable();
		t.setCredentialBean(bean);
		AssertJUnit.assertEquals(t.getCredentialBean(), bean);
	}

	@Test
	public final void _09testGetCredentialType() {
		CredentialTable t = new CredentialTable();
		t.setCredentialType(CredentialType.CREDENTIAL_TYPE_SECUREID);
		AssertJUnit.assertEquals(t.getCredentialType(),CredentialType.CREDENTIAL_TYPE_SECUREID);
	}

	@Test
	public final void _10testAddCredential1() throws Exception {
		CredentialTable t = new CredentialTable(100000,"credential_type_secureid", "111211", "cpruser");
		openDbConnection();
		t.addCredential(db);
		db.closeSession();
		
	}
	
	@Test
	public final void _11testAddCredential2() throws Exception {
		CredentialTable t = new CredentialTable(100000,"credential_type_openid", "abcd", "cpruser");
		openDbConnection();
		t.addCredential(db);
		db.closeSession();
		
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _12testAddCredential3() throws Exception {
		CredentialTable t = new CredentialTable(100000,"credential_type_openid", "abcd", "cpruser");
		openDbConnection();
		t.addCredential(db);
		db.closeSession();
	}

	@Test
	public final void _13testGetCredentialForPersonId() throws Exception {
		CredentialTable t = new CredentialTable();
		openDbConnection();
		t.setReturnHistoryFlag(false);
		CredentialReturn results[] = t.getCredentialForPersonId(db, 100000);
		db.closeSession();
		AssertJUnit.assertEquals(results.length,2);
	}

	@Test
	public final void _14testGetCredentialForPersonId1() throws Exception {
		CredentialTable t = new CredentialTable();
		openDbConnection();
		t.setReturnHistoryFlag(true);
		t.getCredentialForPersonId(db, 100000);
		db.closeSession();
	}

	@Test
	public final void _15testGetCredentialForPersonIdByType1() throws Exception {
		CredentialTable t = new CredentialTable();
		openDbConnection();
		t.setReturnHistoryFlag(false);
		t.setCredentialType(CredentialType.CREDENTIAL_TYPE_OPENID);
		t.getCredentialForPersonId(db, 100000);
		db.closeSession();
	}
	
	@Test
	public final void _16testGetCredentialForPersonIdByType2() throws Exception {
		CredentialTable t = new CredentialTable();
		openDbConnection();
		t.setReturnHistoryFlag(true);
		t.setCredentialType(CredentialType.CREDENTIAL_TYPE_OPENID);
		t.getCredentialForPersonId(db, 100000);
		db.closeSession();
	}
	
	@Test
	public final void _17testArchiveCredential1() throws Exception {
		CredentialTable t = new CredentialTable(100000,"credential_type_secureid", "cpruser");
		openDbConnection();
		t.archiveCredential(db);
		db.closeSession();
	}
	@Test
	public final void _18testArchiveCredential2() throws Exception {
		CredentialTable t = new CredentialTable(100000,"credential_type_openid", "cpruser");
		openDbConnection();
		t.archiveCredential(db);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _19testArchiveCredential3() throws Exception {
		CredentialTable t = new CredentialTable(100000,"credential_type_secureid", "cpruser");
		openDbConnection();
		t.archiveCredential(db);
		db.closeSession();
	}

}
