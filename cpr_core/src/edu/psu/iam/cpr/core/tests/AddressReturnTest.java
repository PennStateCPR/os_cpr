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

import edu.psu.iam.cpr.core.service.returns.AddressReturn;

/**
 * @author cpruser
 *
 */
public class AddressReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getAddressType()}.
	 */
	@Test
	public final void testGetAddressType() {
		
		AddressReturn aReturn = new AddressReturn("STUDENT_ADDRESS_HOME",  null, null, null, null, null, null, null, null,  null,null, null, "F", "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, aReturn.getAddressType(),"STUDENT_ADDRESS_HOME");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setAddressType(java.lang.String)}.
	 */
	@Test
	public final void testSetAddressType() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setAddressType("STUDENT_ADDRESS_HOME");
		AssertJUnit.assertEquals(null, aReturn.getAddressType(),"STUDENT_ADDRESS_HOME");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getAddress1()}.
	 */
	@Test
	public final void testGetAddress1() {
		AddressReturn aReturn = new AddressReturn(null,  null, "address1", null, null, null, null, null, null, null, null, null, "F","02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getAddress1(),"address1");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setAddress1(java.lang.String)}.
	 */
	@Test
	public final void testSetAddress1() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setAddress1("addressline1");
		AssertJUnit.assertEquals(null, aReturn.getAddress1(),"addressline1");

	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getAddress2()}.
	 */
	@Test
	public final void testGetAddress2() {
		AddressReturn aReturn = new AddressReturn(null,  null, null, "address2", null, null, null, null, null,null, null, null, "F","02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getAddress2(),"address2");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setAddress2(java.lang.String)}.
	 */
	@Test
	public final void testSetAddress2() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setAddress2("addressline2");
		AssertJUnit.assertEquals(null, aReturn.getAddress2(),"addressline2");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getAddress3()}.
	 */
	@Test
	public final void testGetAddress3() {
		AddressReturn aReturn = new AddressReturn(null, null, null , null, "address3", null, null, null, null, null, null, null, "F","02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getAddress3(),"address3");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setAddress3(java.lang.String)}.
	 */
	@Test
	public final void testSetAddress3() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setAddress3("addressline3");
		AssertJUnit.assertEquals(null, aReturn.getAddress3(),"addressline3");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getCity()}.
	 */
	@Test
	public final void testGetCity() {
		AddressReturn aReturn = new AddressReturn(null, null, null , null, null, "city", null, null, null, null,null, null, "F", "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getCity(),"city");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setCity(java.lang.String)}.
	 */
	@Test
	public final void testSetCity() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setCity("city");
		AssertJUnit.assertEquals(null, aReturn.getCity(),"city");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getStateOrProvince()}.
	 */
	@Test
	public final void testGetStateOrProvince() {
		AddressReturn aReturn = new AddressReturn(null,  null,null , null, null, null, "PA", null, null,null, null, null, "F", "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getStateOrProvince(),"PA");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setStateOrProvince(java.lang.String)}.
	 */
	@Test
	public final void testSetStateOrProvince() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setStateOrProvince("PA");
		AssertJUnit.assertEquals(null, aReturn.getStateOrProvince(),"PA");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getPostalCode()}.
	 */
	@Test
	public final void testGetPostalCode() {
		AddressReturn aReturn = new AddressReturn(null,  null,null , null, null, null, null, "12345", null, null, null, null, "F","02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getPostalCode(),"12345");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setPostalCode(java.lang.String)}.
	 */
	@Test
	public final void testSetPostalCode() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setPostalCode("12345");
		AssertJUnit.assertEquals(null, aReturn.getPostalCode(),"12345");
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.AddressReturn#getPlus4()}.
	 */
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getCampusCode()}.
	 */
	@Test
	public final void testGetCampusCode() {
		AddressReturn aReturn = new AddressReturn(null, null, null , null, null, null, null, null, "UP",  null,null , null, "F","02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getCampusCode(),"UP");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setCampusCode(java.lang.String)}.
	 */
	@Test
	public final void testSetCampusCode() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setCampusCode("UP");
		AssertJUnit.assertEquals(null, aReturn.getCampusCode(),"UP");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#getCountryCode()}.
	 */
	@Test
	public final void testGetCountryCode() {
		AddressReturn aReturn = new AddressReturn(null,  null,null , null, null, null, null, null, null, null,  "USA", null, "F", "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getCountryCode(),"USA");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setCountryCode(java.lang.String)}.
	 */
	@Test
	public final void testSetCountryCode() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setCountryCode("USA");
		AssertJUnit.assertEquals(null, aReturn.getCountryCode(),"USA");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setVerifiedFlag(java.lang.String)}.
	 */
	@Test
	public final void testSetVerifiedFlag() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setVerifiedFlag("F");
		AssertJUnit.assertEquals(null, aReturn.getVerifiedFlag(),"F");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setStartDate(java.lang.String)}.
	 */
	@Test
	public final void testSetStartDate() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setStartDate("02/01/2012");
		AssertJUnit.assertEquals(null, aReturn.getStartDate(),"02/01/2012");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setEndDate(java.lang.String)}.
	 */
	@Test
	public final void testSetEndDate() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setEndDate("02/01/2012");
		AssertJUnit.assertEquals(null, aReturn.getEndDate(),"02/01/2012");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateBy() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getLastUpdateBy(),"SYSTEM");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setLastUpdateOn(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateOn() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setLastUpdateOn("02/01/2012");
		AssertJUnit.assertEquals(null, aReturn.getLastUpdateOn(),"02/01/2012");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setCreatedOn(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedOn() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setCreatedOn("02/01/2012");
		AssertJUnit.assertEquals(null, aReturn.getCreatedOn(),"02/01/2012");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedBy() {
		AddressReturn aReturn = new AddressReturn();
		aReturn.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(null, aReturn.getCreatedBy(),"SYSTEM");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#AddressReturn()}.
	 */
	@Test
	public final void testAddressReturn() {
		new AddressReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AddressReturn#AddressReturn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testAddressReturnStringStringStringStringStringStringStringStringStringString() {
		new AddressReturn("STUDENT_ADDRESS_HOME", "DOCUMENTED_ADDRESS",
				
				"address1", "address2", "address3","city", "PA","12345", "1234" ,null, 
				"UP", "USA", "F", "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
	}

}
