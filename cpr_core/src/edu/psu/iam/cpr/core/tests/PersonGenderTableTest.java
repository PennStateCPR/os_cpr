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
import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * @author jvuccolo
 *
 */
public class PersonGenderTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	@Test
	public final void testPerGenderRelTable() {
		new PersonGenderTable();
	}
	@Test
	public final void testPerGenderRelTableIntStringString1() throws Exception {
		new PersonGenderTable(100000, "GENDER_MALE", "jvuccolo");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testPerGenderRelTableIntStringString2() throws Exception {
		new PersonGenderTable(100000, "", "jvuccolo");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testPerGenderRelTableIntStringString3() throws Exception {
		new PersonGenderTable(100000, null, "jvuccolo");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testPerGenderRelTableIntStringString4() throws Exception {
		new PersonGenderTable(100000, "x", "jvuccolo");
	}
	@Test
	public final void testGetGenderType() {
		PersonGenderTable t = new PersonGenderTable();
		t.setGenderType(GenderType.GENDER_FEMALE);
		AssertJUnit.assertEquals(t.getGenderType(), GenderType.GENDER_FEMALE);
	}
	@Test
	public final void testSetGenderTypeGenderType() {
		PersonGenderTable t = new PersonGenderTable();
		t.setGenderType(GenderType.GENDER_FEMALE);
		AssertJUnit.assertEquals(t.getGenderType(), GenderType.GENDER_FEMALE);
	}
	@Test
	public final void testSetGenderTypeString() throws Exception {
		PersonGenderTable t = new PersonGenderTable();
		t.setGenderType("GENDER_MALE");
		AssertJUnit.assertEquals(t.getGenderType(), GenderType.GENDER_MALE);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testAddGender1() throws Exception {
		PersonGenderTable t = new PersonGenderTable(100000, "GENDER_MALE", "jvuccolo");
		t.addGender(db);
	}	
	@Test
	public final void testAddGender2() throws Exception {
		openDbConnection();
		PersonGenderTable t = new PersonGenderTable(100000, "GENDER_MALE", "jvuccolo");
		t.addGender(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetGender1() throws GeneralDatabaseException, CprException {
		PersonGenderTable p = new PersonGenderTable();
		p.setReturnHistoryFlag(false);
		p.getGenderForPersonId(db, 100000);
	}
	
	@Test
	public final void testGetGender3() throws GeneralDatabaseException, CprException {
		openDbConnection();
		PersonGenderTable p = new PersonGenderTable();
		p.setReturnHistoryFlag(false);
		AssertJUnit.assertEquals(p.getGenderForPersonId(db, 10).length, 0);
		db.closeSession();
	}

	@Test
	public final void testGetGender2() throws GeneralDatabaseException, CprException {
		openDbConnection();
		PersonGenderTable p = new PersonGenderTable();
		p.setReturnHistoryFlag(false);
		AssertJUnit.assertNotNull(p.getGenderForPersonId(db, 100000));
		db.closeSession();
	}
}
