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

import edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn;

/**
 * @author jal55
 *
 */
public class PersonIdCardReturnTest {
	
	private PersonIdCardReturn idcr = new PersonIdCardReturn();
	private String str = "abc";
	private String date = "2012-02-01 10:30:03.0";

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#IdCardReturn()}.
	 */
	@Test
	public final void testPersonIdCardReturn() {
		new PersonIdCardReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#IdCardReturn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIdCardReturnStringStringStringStringStringStringStringStringStringString() {
		PersonIdCardReturn aPersonIdCardReturn= new PersonIdCardReturn("STUDENT", "123456789012345", "12345",  date, null, str, date, str, date); 
		AssertJUnit.assertNotNull(aPersonIdCardReturn);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getIdCardType()}.
	 */
	@Test
	public final void testGetIdCardType() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setIdCardType("ID_CARD_STUDENT");
		AssertJUnit.assertEquals(x.getIdCardType(),"ID_CARD_STUDENT");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setIdCardType(java.lang.String)}.
	 */
	@Test
	public final void testSetIdCardType() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setIdCardType("STUDENT");
		AssertJUnit.assertEquals(x.getIdCardType(),"STUDENT");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getIdCardNumber()}.
	 */
	@Test
	public final void testGetIdCardNumber() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setIdCardNumber("1234567890123456");
		AssertJUnit.assertEquals(x.getIdCardNumber(),"1234567890123456");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setIdCardNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetIdCardNumber() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setIdCardNumber("1234567890123456");
		AssertJUnit.assertEquals(x.getIdCardNumber(),"1234567890123456");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getIdSerialNumber()}.
	 */
	@Test
	public final void testGetIdSerialNumber() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setIdSerialNumber("1234567890");
		AssertJUnit.assertEquals(x.getIdSerialNumber(),"1234567890");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setIdSerialNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetIdSerialNumber() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setIdSerialNumber("1234567890");
		AssertJUnit.assertEquals(x.getIdSerialNumber(),"1234567890");
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setStartDate(date);
		AssertJUnit.assertEquals(x.getStartDate(),date);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setStartDate(java.lang.String)}.
	 */
	@Test
	public final void testSetStartDate() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setStartDate(date);
		AssertJUnit.assertEquals(x.getStartDate(),date);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setEndDate(date);
		AssertJUnit.assertEquals(x.getEndDate(),date);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setEndDate(java.lang.String)}.
	 */
	@Test
	public final void testSetEndDate() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setEndDate(date);
		AssertJUnit.assertEquals(x.getEndDate(),date);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getLastUpdateBy()}.
	 */
	@Test
	public final void testGetLastUpdateBy() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setLastUpdateBy(str);
		AssertJUnit.assertEquals(x.getLastUpdateBy(),str);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateBy() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setLastUpdateBy(str);
		AssertJUnit.assertEquals(x.getLastUpdateBy(),str);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getLastUpdateOn()}.
	 */
	@Test
	public final void testGetLastUpdateOn() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setLastUpdateOn(date);
		AssertJUnit.assertEquals(x.getLastUpdateOn(),date);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setLastUpdateOn(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateOn() {
		PersonIdCardReturn x = new PersonIdCardReturn();
		x.setLastUpdateOn(date);
		AssertJUnit.assertEquals(x.getLastUpdateOn(),date);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getCreatedBy()}.
	 */
	@Test
	public final void testGetCreatedBy() {
		idcr.setCreatedBy(str);
		AssertJUnit.assertEquals(idcr.getCreatedBy(),str);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedBy() {
		idcr.setCreatedBy(str);
		AssertJUnit.assertEquals(idcr.getCreatedBy(),str);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#getCreatedOn()}.
	 */
	@Test
	public final void testGetCreatedOn() {
		idcr.setCreatedOn(date);
		AssertJUnit.assertEquals(idcr.getCreatedOn(),date);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn#setCreatedOn(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedOn() {
		idcr.setCreatedOn(date);
		AssertJUnit.assertEquals(idcr.getCreatedOn(),date);
	}

}
