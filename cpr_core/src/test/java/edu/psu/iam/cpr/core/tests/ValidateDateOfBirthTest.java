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


import static org.testng.AssertJUnit.assertTrue;

import java.text.ParseException;

import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateDateOfBirth;
import edu.psu.iam.cpr.core.error.CprException;

/**
 * @author cpruser
 *
 */
public class ValidateDateOfBirthTest {

	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddDateOfBirthParameters1() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0,null, null);
		
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddDateOfBirthParameters2() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "11/11/2001", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddDateOfBirthParameters3() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "11/222/2010", "cpruser");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddDateOfBirthParameters6() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "11-22-", "cpruser");
	}
	@Test
	public final void testValidateAddDateOfBirthParameters7() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "1/1", "cpruser");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddDateOfBirthParameters8() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "1/90", "cpruser");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddDateOfBirthParameters9() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "1/1/2011/1", "cpruser");
	}
	
	@Test
	public final void testValidateAddDateOfBirthParameters4() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "11/11/2010", "cpruser");
	}
	@Test
	public final void testValidateAddDateOfBirthParameters5() throws CprException, ParseException {
		ValidateDateOfBirth.validateAddDateOfBirthParameters(0, "11-11-2010", "cpruser");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetDateOfBirthParameters1() throws CprException {
		ValidateDateOfBirth.validateGetDateOfBirthParameters(0, null,"");
	}
	@Test
	public final void testValidateGetDateOfBirthParameters2() throws CprException {
		DateOfBirthTable dateOfBirthTable = ValidateDateOfBirth.validateGetDateOfBirthParameters(0, "cpruser","Y");
		assertTrue(dateOfBirthTable.isReturnHistoryFlag());
	}

}
