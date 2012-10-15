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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.DateOfBirth;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.service.returns.DateOfBirthReturn;

/**
 * @author jvuccolo
 *
 */
public class DateOfBirthTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.DateOfBirthTable#DateOfBirthTable(int, java.lang.String, java.lang.String)}.
	 * @throws CprException 
	 */
	@Test
	public final void testDateOfBirthTableIntStringString() throws CprException {
		new DateOfBirthTable(1, "11/11/2010", "jvuccolo");
	}
	
	@Test
	public final void testDateOfBirthTableIntStringString1() throws CprException {
		new DateOfBirthTable(1, "11/11", "jvuccolo");
	}
	
	
	@Test
	public final void testToDobChar1() {
		AssertJUnit.assertNull(DateOfBirthTable.toDobChar("11"));
		
	}
	
	@Test
	public final void testToDobChar2() {
		AssertJUnit.assertNull(DateOfBirthTable.toDobChar("11/11/2011/11"));
		
	}
	
	@Test
	public final void testToDobChar3() {
		AssertJUnit.assertNull(DateOfBirthTable.toDobChar(null));
		
	}
	
	@Test
	public final void testToDobChar4() {
		AssertJUnit.assertNull(DateOfBirthTable.toDobChar(""));
		
	}
	
	@Test
	public final void testToDobChar5() {
		AssertJUnit.assertEquals(DateOfBirthTable.toDobChar("11/11/2011"),"11112011");
		
	}
	
	@Test
	public final void testToDobChar6() {
		AssertJUnit.assertEquals(DateOfBirthTable.toDobChar("6/1"),"06010000");
		
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.DateOfBirthTable#DateOfBirthTable()}.
	 */
	@Test
	public final void testDateOfBirthTable() {
		new DateOfBirthTable();
	}
	
	@Test
	public final void testGetDateOfBirthBean() {
		DateOfBirthTable t = new DateOfBirthTable();
		DateOfBirth bean = new DateOfBirth();
		t.setDateOfBirthBean(bean);
		AssertJUnit.assertEquals(t.getDateOfBirthBean(),bean);
	}


	@Test(expectedExceptions=Exception.class)
	public final void testAddDateOfBirth1() throws CprException, GeneralDatabaseException {
		DateOfBirthTable d = new DateOfBirthTable(100000, "1/1/2010", "jvuccolo");
		d.addDateOfBirth(db);
	}
	
	
	@Test
	public final void testAddDateOfBirth4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		DateOfBirthTable d = new DateOfBirthTable(100000, "1/1", "jvuccolo");
		d.addDateOfBirth(db);
		db.closeSession();
	}
	
	@Test
	public final void testAddDateOfBirth3() throws CprException, GeneralDatabaseException {
		openDbConnection();
		DateOfBirthTable d = new DateOfBirthTable(100000,"12/1/2010", "jvuccolo");
		d.addDateOfBirth(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetDateOfBirth1() throws GeneralDatabaseException, CprException {
		DateOfBirthTable d = new DateOfBirthTable();
		d.setReturnHistoryFlag(false);
		d.getDateOfBirthForPersonId(db,100000);
	}
	
	@Test
	public final void testGetDateOfBirth2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		DateOfBirthTable d = new DateOfBirthTable(100000, "1/1", "jvuccolo");
		d.addDateOfBirth(db);
		d.setReturnHistoryFlag(false);
		DateOfBirthReturn dobReturn[] = d.getDateOfBirthForPersonId(db,100000);
		AssertJUnit.assertEquals(dobReturn[0].getDob(), "01/01");
		db.closeSession();
	}
	
	@Test
	public final void testGetDateOfBirth3() throws GeneralDatabaseException, CprException {
		openDbConnection();
		DateOfBirthTable d = new DateOfBirthTable(100000,"12/1/2010", "jvuccolo");
		d.addDateOfBirth(db);
		d.setReturnHistoryFlag(false);
		DateOfBirthReturn dobReturn[] = d.getDateOfBirthForPersonId(db,100000);
		AssertJUnit.assertEquals(dobReturn[0].getDob(), "12/01/2010");
		db.closeSession();
	}
	
	@Test
	public final void testSetFormattedDateOfBirth1() {
		DateOfBirthTable d = new DateOfBirthTable();
		d.setFormattedDateOfBirth("01010000");
		AssertJUnit.assertEquals(d.getFormattedDateOfBirth(), "01/01");
	}
	
	@Test
	public final void testSetFormattedDateOfBirth2() {
		DateOfBirthTable d = new DateOfBirthTable();
		d.setFormattedDateOfBirth("01011970");
		AssertJUnit.assertEquals(d.getFormattedDateOfBirth(), "01/01/1970");
	}

}
