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

import edu.psu.iam.cpr.core.service.returns.PhoneReturn;

/**
 * @author cpruser
 *
 */
public class PhoneReturnTest {




	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#getPhoneType()}.
	 */
	@Test
	public final void testGetPhoneTypeString() {
		PhoneReturn phoneReturn = new PhoneReturn("STUDENT_HOME", "8148654864", null, null,"02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, phoneReturn.getPhoneType(), "STUDENT_HOME");
	} 


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setPhoneType(java.lang.String)}.
	 */
	@Test
	public final void testSetPhoneTypeString() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setPhoneType("STUDENT_HOME");
		AssertJUnit.assertEquals(null, phoneReturn.getPhoneType(), "STUDENT_HOME");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#getPhoneNumber()}.
	 */
	@Test
	public final void testGetPhoneNumber() {
		PhoneReturn phoneReturn = new PhoneReturn("STUDENT_HOME", "8148654864", null, null,"02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, phoneReturn.getPhoneNumber(), "8148654864");
	} 


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setPhoneNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetPhoneNumber() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setPhoneNumber("8148654864");
		AssertJUnit.assertEquals(null, phoneReturn.getPhoneNumber(), "8148654864");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#getExtension()}.
	 */
	@Test
	public final void testGetExtension() {
		PhoneReturn phoneReturn = new PhoneReturn("STUDENT_HOME", "8148654864", "1234", null,"02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, phoneReturn.getExtension(), "1234");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setExtension(java.lang.String)}.
	 */
	@Test
	public final void testSetExtension() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setExtension("123");
		AssertJUnit.assertEquals(null, phoneReturn.getExtension(), "123");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#getInternationalNumber()}.
	 */
	@Test
	public final void testGetInternationalNumberY() {
		PhoneReturn phoneReturn = new PhoneReturn("STUDENT_HOME", "8148654864", "1234", "Y","02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, phoneReturn.getInternationalNumber(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#getInternationalNumber()}.
	 */
	@Test
	public final void testGetInternationalNumberN() {
		PhoneReturn phoneReturn = new PhoneReturn("STUDENT_HOME", "8148654864", "1234", "N","02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, phoneReturn.getInternationalNumber(), "N");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#getInternationalNumber()}.
	 */
	@Test
	public final void testGetInternationalNumberNull() {
		PhoneReturn phoneReturn = new PhoneReturn("STUDENT_HOME", "8148654864", "1234", null,"02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, phoneReturn.getInternationalNumber(), null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setInternationalNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetInternationalNumberY() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setInternationalNumber("Y");
		AssertJUnit.assertEquals(null, phoneReturn.getInternationalNumber(), "Y");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setStartDate(java.lang.String)}.
	 */
	@Test
	public final void testStartDate() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setStartDate("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, phoneReturn.getStartDate(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setEndDate(java.lang.String)}.
	 */
	@Test
	public final void testEndDate() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setEndDate("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, phoneReturn.getEndDate(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testLastUpdateBy() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(null, phoneReturn.getLastUpdateBy(), "SYSTEM");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setLastUpdateOn(java.lang.String)}.
	 */
	@Test
	public final void testLastUpdateOn() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setLastUpdateOn("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, phoneReturn.getLastUpdateOn(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void testCreatedBy() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(null, phoneReturn.getCreatedBy(), "SYSTEM");
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setCreatedOn(java.lang.String)}.
	 */
	@Test
	public final void testCreatedOn() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setCreatedOn("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, phoneReturn.getCreatedOn(), "02/01/2012 09:09:09");
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setInternationalNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetInternationalNumberN() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setInternationalNumber("N");
		AssertJUnit.assertEquals(null, phoneReturn.getInternationalNumber(), "N");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.PhoneReturn#setInternationalNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetInternationalNumberNull() {
		PhoneReturn phoneReturn = new PhoneReturn();
		phoneReturn.setInternationalNumber(null);
		AssertJUnit.assertEquals(null, phoneReturn.getInternationalNumber(), null);
	}
}
