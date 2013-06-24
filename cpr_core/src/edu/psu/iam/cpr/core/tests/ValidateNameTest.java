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
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateName;
import edu.psu.iam.cpr.core.database.types.NameType;

/**
 * @author cpruser
 *
 */
public class ValidateNameTest {

	private static Database db = new Database();
	
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test(expectedExceptions=Exception.class)
	public final void _01testValidateGetNameParameters1() throws Exception {
		openDbConnection();
		ValidateName.validateGetNameParameters(db, 1000000, null,null,null);
		db.closeSession();
	}
	@Test
	public final void _02testValidateGetNameParameters2() throws Exception {
		openDbConnection();
		ValidateName.validateGetNameParameters(db, 100000, "jimmy",null,"Y");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _03testValidateGetNameParameters3() throws Exception {
		openDbConnection();
		ValidateName.validateGetNameParameters(db, 100000, "1234567890123456789012345678901",null,"Y");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _04testValidateGetNameParameters4() throws Exception {
		openDbConnection();
		ValidateName.validateGetNameParameters(db, 100000, "                                       ",null,"Y");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _05testValidateGetNameParameters5() throws Exception {
		openDbConnection();
		ValidateName.validateGetNameParameters(db, 100000, "jimmy","legal","Y");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _06testValidateGetNameParameters6() throws Exception {
		openDbConnection();
		ValidateName.validateGetNameParameters(db, 100000, "jimmy",null,"dabc");
		db.closeSession();
	}
	@Test
	public final void _07testValidateGetNameParameters7() throws Exception {
		openDbConnection();
		NamesTable namesTable = ValidateName.validateGetNameParameters(db, 100000, "jimmy","legal_name","y");
		AssertJUnit.assertEquals(namesTable.getNameType(),NameType.LEGAL_NAME);
		AssertJUnit.assertEquals(namesTable.isReturnHistoryFlag(),true);
		db.closeSession();
	}
	@Test
	public final void _08testValidateGetNameParameters8() throws Exception {
		openDbConnection();
		NamesTable namesTable = ValidateName.validateGetNameParameters(db, 100000, "jimmy",null,"y");
		AssertJUnit.assertNull(namesTable.getNameType());
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _09testValidateDeleteNameParameters1() throws Exception {
		openDbConnection();
		ValidateName.validateArchiveNameParameters(db, 100000, null, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _10testValidateDeleteNameParameters2() throws Exception {
		openDbConnection();
		ValidateName.validateArchiveNameParameters(db, 100000, "home", null, null);
		db.closeSession();

	}
	@Test(expectedExceptions=Exception.class)
	public final void _11testValidateDeleteNameParameters3() throws Exception {
		openDbConnection();
		ValidateName.validateArchiveNameParameters(db, 100000, "home", "", "cpruser");
		db.closeSession();

	}

	@Test
	public final void _12testValidateDeleteNameParameters4() throws Exception {
		openDbConnection();
		ValidateName.validateArchiveNameParameters(db, 100000, "legal_name", "", "cpruser");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _13testValidateDeleteNameParameters5() throws Exception {
		openDbConnection();
		ValidateName.validateArchiveNameParameters(db, 100000, "common_name", "", "1234567890123456789012345678902121");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _14testValidateDeleteNameParameters6() throws Exception {
		openDbConnection();
		ValidateName.validateArchiveNameParameters(db, 100000, "common_name", "", "                                           ");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _15testValidateAddNameParameters1() throws Exception {
		openDbConnection();
		ValidateName.validateAddNameParameters(db, 0, null, null, null, null, null, null, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _16testValidateAddNameParameters2() throws Exception {
		openDbConnection();
		ValidateName.validateAddNameParameters(db, 0, "name_type", null, null, null, null, null, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _17testValidateAddNameParameters3() throws Exception {
		openDbConnection();
		ValidateName.validateAddNameParameters(db, 0, "name_type", null, null, null, "last", null, null, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _18testValidateAddNameParameters4() throws Exception {
		openDbConnection();
		ValidateName.validateAddNameParameters(db, 0, "name_type", null, null, null, "last", null, null, "cpruser");
		db.closeSession();
	}
	@Test
	public final void _19testValidateAddNameParameters5() throws Exception {
		openDbConnection();
		ValidateName.validateAddNameParameters(db, 0, "legal_name", null, null, null, "last", null, null, "cpruser");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _20testValidateAddNameParameters6() throws Exception {
		openDbConnection();
		ValidateName.validateAddNameParameters(db, 0, "common_name", null, "1234567890123456789012345678901", null, null, null, null, "cpruser");
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void _21testValidateAddNameParameters7() throws Exception {
		openDbConnection();
		ValidateName.validateAddNameParameters(db, 0, "common_name", null, "Jimmy", null, "Vuccolo", null, null, "                                                 ");
		db.closeSession();
	}
	
	@Test
	public final void _23testIsValidCombination2() {
		AssertJUnit.assertFalse(ValidateName.isValidCombination("", ""));
	}
	@Test
	public final void _24testIsValidCombination3() {
		AssertJUnit.assertFalse(ValidateName.isValidCombination("", "passport"));
	}
	@Test
	public final void _25testIsValidCombination4() {
		AssertJUnit.assertFalse(ValidateName.isValidCombination("legal_name", "passport"));
	}
	@Test
	public final void _26testIsValidCombination5() {
		AssertJUnit.assertFalse(ValidateName.isValidCombination("documented_name", null));
	}
	@Test
	public final void _27testIsValidCombination6() {
		AssertJUnit.assertTrue(ValidateName.isValidCombination("LEGAL_NAME", null));
	}
	@Test
	public final void _28testIsValidCombination7() {
		AssertJUnit.assertTrue(ValidateName.isValidCombination("DOCUMENTED_NAME", "PASSPORT"));
	}
	

}
