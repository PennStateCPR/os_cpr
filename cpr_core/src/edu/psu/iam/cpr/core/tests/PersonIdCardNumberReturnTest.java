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

import edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn;

/**
 * @author llg5
 *
 */
public class PersonIdCardNumberReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn#getIdCardType()}.
	 */
	@Test
	public final void testGetIdCardType() {
		PersonIdCardNumberReturn idCardReturn = new PersonIdCardNumberReturn();
		idCardReturn.setIdCardType("ID_CARD_STUDENT");
		AssertJUnit.assertEquals(idCardReturn.getIdCardType(), "ID_CARD_STUDENT");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn#setIdCardType(java.lang.String)}.
	 */
	@Test
	public final void testSetIdCardType() {
		PersonIdCardNumberReturn idCardReturn = new PersonIdCardNumberReturn();
		idCardReturn.setIdCardType("ID_CARD_STUDENT");
		AssertJUnit.assertEquals(idCardReturn.getIdCardType(), "ID_CARD_STUDENT");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn#getIdCardNumber()}.
	 */
	@Test
	public final void testGetIdCardNumber() {
		PersonIdCardNumberReturn idCardReturn = new PersonIdCardNumberReturn();
		idCardReturn.setIdCardNumber("1234567890123456");
		AssertJUnit.assertEquals(idCardReturn.getIdCardNumber(), "1234567890123456");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn#setIdCardNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetIdCardNumber() {
		PersonIdCardNumberReturn idCardReturn = new PersonIdCardNumberReturn();
		idCardReturn.setIdCardNumber("1234567890123456");
		AssertJUnit.assertEquals(idCardReturn.getIdCardNumber(), "1234567890123456");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn#IdCardNumberReturn()}.
	 */
	@Test
	public final void testIdCardNumberReturn() {
		AssertJUnit.assertNotNull(new PersonIdCardNumberReturn());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn#IdCardNumberReturn(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIdCardNumberReturnStringString() {
		PersonIdCardNumberReturn idCardReturn = new PersonIdCardNumberReturn("ID_CARD_STUDENT", "1234567890123456");
		AssertJUnit.assertEquals(idCardReturn.getIdCardType(), "ID_CARD_STUDENT");
		AssertJUnit.assertEquals(idCardReturn.getIdCardNumber(), "1234567890123456");
	}

}
