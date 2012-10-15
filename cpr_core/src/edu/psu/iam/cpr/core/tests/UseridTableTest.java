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
import org.testng.AssertJUnit;
import java.util.Random;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * @author jvuccolo
 *
 */
public class UseridTableTest {

	private static Database db = new Database();
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UseridTable#UseridTable()}.
	 */
	@Test
	public final void testUseridTable() {
		new UseridTable();
	}
	
	@Test
	public final void testUseridTableIntStringStringString() {
		UseridTable u = new UseridTable(100000, "jvuccolo");
		AssertJUnit.assertEquals(u.getUseridBean().getPersonId(), new Long(100000));
		AssertJUnit.assertEquals(u.getUseridBean().getLastUpdateBy(), "jvuccolo");
	}
	
	@Test
	public final void testGetUseridBean() {
		UseridTable u = new UseridTable();
		Userid bean = new Userid();
		u.setUseridBean(bean);
		AssertJUnit.assertEquals(u.getUseridBean(),bean);
	}
	
	@Test
	public final void testGetChar1() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertNull(u.getCharacterPart(null));
	}
	@Test
	public final void testGetChar2() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertNull(u.getCharacterPart(""));
	}
	@Test
	public final void testGetChar3() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertEquals(u.getCharacterPart("b2s"),"b");
	}
	@Test
	public final void testGetChar4() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertEquals(u.getCharacterPart("xyz"),"xyz");
	}
	@Test
	public final void testGetChar5() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertEquals(u.getCharacterPart("xyz112"),"xyz");
	}
	@Test
	public final void testGetChar6() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertEquals(u.getCharacterPart("xyz112$"),"xyz");
	}
	@Test
	public final void testGetNum1() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertNull(u.getNumberPart(null,null));
	}
	@Test
	public final void testGetNum2() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertNull(u.getNumberPart("",""));
	}
	@Test
	public final void testGetNum3() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertEquals(u.getNumberPart("b2s","b"),new Long(2));
	}
	@Test
	public final void testGetNum4() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertNull(u.getNumberPart("xyz","xyz"));
	}
	@Test
	public final void testGetNum5() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertEquals(u.getNumberPart("xyz112","xyz"),new Long(112));
	}
	@Test
	public final void testGetNum6() {
		UseridTable u = new UseridTable();
		AssertJUnit.assertEquals(u.getNumberPart("xyz112$","xyz"),new Long(112));
	}

	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test
	public final void testUserid1() throws GeneralDatabaseException {
		openDbConnection();
		UseridTable u = new UseridTable();
		AssertJUnit.assertFalse(u.isUseridValid(db, " "));
		db.closeSession();
	}
	@Test
	public final void testUserid2() throws GeneralDatabaseException {
		openDbConnection();
		UseridTable u = new UseridTable();
		AssertJUnit.assertFalse(u.isUseridValid(db, "123#"));
		db.closeSession();
	}
	@Test
	public final void testUserid3() throws GeneralDatabaseException {
		openDbConnection();
		UseridTable u = new UseridTable();
		AssertJUnit.assertFalse(u.isUseridValid(db, "cum"));
		db.closeSession();
	}
	@Test
	public final void testUserid4() throws GeneralDatabaseException {
		openDbConnection();
		UseridTable u = new UseridTable();
		AssertJUnit.assertFalse(u.isUseridValid(db, "dummy"));
		db.closeSession();
	}
	@Test
	public final void testUserid5() throws GeneralDatabaseException {
		openDbConnection();
		UseridTable u = new UseridTable();
		AssertJUnit.assertTrue(u.isUseridValid(db, "xyz7777"));
		db.closeSession();
	}
	
	@Test
	public final void testGetSetId() {
		UseridTable u = new UseridTable();
		u.setSetId(1);
		AssertJUnit.assertEquals(u.getSetId(),1);
	}
	
//	@Test(expected=Exception.class)
//	public final void testAddUseridException1() throws GeneralDatabaseException, CprException {
//		openDbConnection();
//		UseridTable u = new UseridTable(2, "jvuccolo");
//		u.addUserid(db);
//		db.closeSession();
//	}
//	
//	@Test(expected=Exception.class)
//	public final void testAddUseridException2() throws GeneralDatabaseException, CprException {
//		openDbConnection();
//		UseridTable u = new UseridTable(100003, "jvuccolo");
//		u.addUserid(db);
//		db.closeSession();
//	}
	
		@Test
		public final void testAddUserid() throws GeneralDatabaseException, CprException {
			openDbConnection();
			UseridTable u = new UseridTable(100002, "jvuccolo");
			u.addUserid(db);
			db.closeSession();
		}
		
		
	@Test
	public final void testgetUseridsForPersonId1() throws GeneralDatabaseException, CprException {
		openDbConnection();
		UseridTable u  = new UseridTable();
		u.setReturnHistoryFlag(true);
		u.getUseridsForPersonId(db, 100001);
		db.closeSession();
	}
	
	@Test
	public final void testgetUseridsForPersonId2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		UseridTable u  = new UseridTable();
		u.setReturnHistoryFlag(false);
		u.getUseridsForPersonId(db, 100000);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testSetPrimaryUserid1() throws GeneralDatabaseException, CprException {
		UseridTable u = new UseridTable(100000,"jvuccolo");
		u.getUseridBean().setUserid("txj1");
		u.setPrimaryUserid(db);
	}
	
	@Test
	public final void testSetPrimaryUserid2() throws GeneralDatabaseException, CprException {
		
		openDbConnection();
		UseridTable u = new UseridTable(100002,"jvuccolo");
		u.addUserid(db);
		db.closeSession();
		openDbConnection();
		u.setPrimaryUserid(db);
		db.closeSession();
	}
	
//	@Test(expected=Exception.class)
//	public final void testArchiveUserid1() throws GeneralDatabaseException, CprException {
//		UseridTable u = new UseridTable(100000,"jvuccolo");
//		u.getUseridBean().setUserid("tzj2");
//		u.archiveUserid(db);		
//	}
//	
	@Test
	public final void testArchiveUserid2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		UseridTable u = new UseridTable(100000,"jvuccolo");
		u.getUseridBean().setUserid("dummy1");
		u.archiveUserid(db);
		db.closeSession();
	}
	
	
	@Test(expectedExceptions=Exception.class)
	public final void testUnarchiveUserid1() throws GeneralDatabaseException, CprException {
		UseridTable u = new UseridTable(100000,"jvuccolo");
		u.getUseridBean().setUserid("tzj2");
		u.unarchiveUserid(db);		
	}
	
	@Test
	public final void testUnarchiveUserid2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		UseridTable u = new UseridTable(100000,"jvuccolo");
		u.getUseridBean().setUserid("dummy1");
		u.unarchiveUserid(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testAddSpecialUserid1() throws GeneralDatabaseException, CprException {
		UseridTable u = new UseridTable(100000,"jvuccolo");
		u.addSpecialUserid(db);		
	}
	
//	@Test(expected=Exception.class)
//	public final void testAddSpecialUserid3() throws GeneralDatabaseException, CprException {
//		openDbConnection();
//		UseridTable u = new UseridTable(100000,"jvuccolo");
//		u.getUseridBean().setUserid("smj5018");
//		u.addSpecialUserid(db);
//		db.closeSession();
//	}
	
	@Test
	public final void testAddSpecialUserid2() throws GeneralDatabaseException, CprException {
		Random r = new Random();
		openDbConnection();
		UseridTable u = new UseridTable(100000,"jvuccolo");
		u.getUseridBean().setUserid("IAMDemo" + Math.abs(r.nextInt()));
		u.addSpecialUserid(db);
		db.closeSession();
	}
}
