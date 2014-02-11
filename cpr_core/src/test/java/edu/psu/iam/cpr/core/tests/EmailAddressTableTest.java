/* SVN FILE: $Id: EmailAddressTableTest.java 5340 2012-09-27 14:48:52Z cpruser $ */
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
import edu.psu.iam.cpr.core.database.beans.EmailAddress;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;

/**
 * @author cpruser
 *
 */
public class EmailAddressTableTest {
	
	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#EmailAddressTable()}.
	 */
	@Test
	public final void testEmailAddressTable() {
		new EmailAddressTable();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#EmailAddressTable(int, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void testEmailAddressTableIntStringStringString() throws Exception {
		new EmailAddressTable(0, "OTHER_EMAIL", "xyz123@psu.edu", "Y");
	}
	
	@Test
	public final void testEmailAddressTableIntStringStringString1() throws Exception {
		new EmailAddressTable(0, "OTHER_EMAIL", "Y");
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#getEmailAddressType()}.
	 */
	@Test
	public final void testGetEmailAddressType() {
		EmailAddressTable t = new EmailAddressTable();
		t.setEmailAddressType(EmailAddressType.OTHER_EMAIL);
		AssertJUnit.assertEquals(t.getEmailAddressType(), EmailAddressType.OTHER_EMAIL);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#setEmailAddressType(edu.psu.iam.cpr.core.database.types.EmailAddressType)}.
	 */
	@Test
	public final void testSetEmailAddressTypeEmailAddressType() {
		EmailAddressTable t = new EmailAddressTable();
		t.setEmailAddressType(EmailAddressType.OTHER_EMAIL);
		AssertJUnit.assertEquals(t.getEmailAddressType(), EmailAddressType.OTHER_EMAIL);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#setEmailAddressType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testSetEmailAddressTypeString1() throws Exception {
		EmailAddressTable t = new EmailAddressTable();
		t.setEmailAddressType(t.findEmailAddressEnum("blah"));
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#setEmailAddressType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void testSetEmailAddressTypeString2() throws Exception {
		EmailAddressTable t = new EmailAddressTable();
		t.setEmailAddressType(t.findEmailAddressEnum("OTHER_EMAIL"));
		AssertJUnit.assertEquals(t.getEmailAddressType(), EmailAddressType.OTHER_EMAIL);
	}

	@Test
	public final void testGetEmailAddressBean() {
		EmailAddress bean = new EmailAddress();
		EmailAddressTable t = new EmailAddressTable();
		t.setEmailAddressBean(bean);
		AssertJUnit.assertEquals(t.getEmailAddressBean(),bean);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testAddAddress0() throws Exception {
		EmailAddressTable t = new EmailAddressTable();
		t.addAddress(null);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#addAddress()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testAddAddress1() throws Exception {
		openDbConnection();
		EmailAddressTable t = new EmailAddressTable(0,"home_email","zy1@psu.edu","cpruser");
		t.addAddress(db);
		db.closeSession();
	}
	
//	/**
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#addAddress()}.
//	 * @throws Exception 
//	 */
//	@Test(expected=Exception.class)
//	public final void testAddAddress2() throws Exception {
//		openDbConnection();
//		EmailAddressTable t = new EmailAddressTable(100000,"OTHER_EMAIL","zy123@psu.edu","cpruser");
//		t.addAddress(db);
//		db.closeSession();
//	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#addAddress()}.
	 * @throws Exception 
	 */
	@Test
	public final void testAddAddress3() throws Exception {
		openDbConnection();
		EmailAddressTable t = new EmailAddressTable(100020,"OTHER_EMAIL","zy1234@psu.edu","cpruser");
		t.addAddress(db);
		db.closeSession();
	}
	

	@Test(expectedExceptions=Exception.class)
	public final void testUpdateAddress0() throws Exception {
		EmailAddressTable t = new EmailAddressTable();
		t.updateAddress(null);

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#updateAddress()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testUpdateAddress1() throws Exception {
		openDbConnection();
		EmailAddressTable t = new EmailAddressTable(0,"home_email","zy1@psu.edu","cpruser");
		t.updateAddress(db);
		db.closeSession();

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#updateAddress()}.
	 * @throws Exception 
	 */
	@Test
	public final void testUpdateAddress2() throws Exception {
		openDbConnection();
		EmailAddressTable t = new EmailAddressTable(100020,"other_email","zy12@psu.edu","cpruser");
		t.updateAddress(db);
		db.closeSession();

	}

	@Test(expectedExceptions=Exception.class)
	public final void testGetEmailAddressForPersonId0() throws Exception  {
		EmailAddressTable e = new EmailAddressTable();
		e.setReturnHistoryFlag(true);
		e.getEmailAddressForPersonId(null,0);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#getEmailAddressForPersonId(int)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetEmailAddressForPersonId1() throws Exception {
		openDbConnection();
		EmailAddressTable e = new EmailAddressTable();
		e.setReturnHistoryFlag(true);
		e.getEmailAddressForPersonId(db,0);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#getEmailAddressForPersonId(int)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetEmailAddressForPersonId2() throws Exception {
		openDbConnection();
		EmailAddressTable e = new EmailAddressTable();
		e.setReturnHistoryFlag(false);
		e.getEmailAddressForPersonId(db,100000);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.EmailAddressTable#getEmailAddressForPersonId(int)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetEmailAddressForPersonId3() throws Exception {
		openDbConnection();
		EmailAddressTable e = new EmailAddressTable();
		e.setReturnHistoryFlag(false);
		e.getEmailAddressForPersonId(db,1000);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testDeleteEmailAddress1() throws Exception {
		EmailAddressTable t = new EmailAddressTable();
		t.archiveEmailAddress(db);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testDeleteEmailAddress2() throws Exception {
		openDbConnection();
		EmailAddressTable t = new EmailAddressTable(100002,"home_email","cpruser");
		t.archiveEmailAddress(db);
		db.closeSession();
	}
}
