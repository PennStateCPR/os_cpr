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

import edu.psu.iam.cpr.core.database.tables.validate.ValidatePerGenderRel;

/**
 * @author cpruser
 *
 */
public class ValidatePerGenderRelTest {

	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddGenderParameters1() throws Exception {
		ValidatePerGenderRel.validateAddGenderParameters(0, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddGenderParameters2() throws Exception {
		ValidatePerGenderRel.validateAddGenderParameters(0, "M", null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddGenderParameters3() throws Exception {
		ValidatePerGenderRel.validateAddGenderParameters(0, "", "");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddGenderParameters4() throws Exception {
		ValidatePerGenderRel.validateAddGenderParameters(0, "M", "");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddGenderParameters5() throws Exception {
		ValidatePerGenderRel.validateAddGenderParameters(0, "a", "cpruser");
	}
	@Test
	public final void testValidateAddGenderParameters6() throws Exception {
		ValidatePerGenderRel.validateAddGenderParameters(0, "GENDER_MALE", "cpruser");
	}

}
