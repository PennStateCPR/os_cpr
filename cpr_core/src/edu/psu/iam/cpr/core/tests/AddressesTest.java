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
import java.util.Date;

import edu.psu.iam.cpr.core.database.beans.Addresses;

/**
 * @author cpruser
 *
 */
public class AddressesTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#Addresses()}.
	 */
	@Test
	public final void testAddresses() {
		Addresses t = new Addresses();
		AssertJUnit.assertNotNull(t);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getAddressKey()}.
	 */
	@Test
	public final void testGetAddressKey() {
		Addresses t = new Addresses();
		t.setAddressKey(1L);
		AssertJUnit.assertEquals(t.getAddressKey(),new Long(1));
	}
	@Test
	public final void testGetPrimaryFlag() {
		Addresses t = new Addresses();
		t.setPrimaryFlag("Y");
		AssertJUnit.assertEquals(t.getPrimaryFlag(),"Y");
	}
	
	@Test
	public final void testGetGroupId() {
		Addresses t = new Addresses();
		t.setGroupId(1L);
		AssertJUnit.assertEquals(t.getGroupId(), new Long(1));	
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getCityMatchCode()}.
	 */
	@Test
	public final void testGetCityMatchCode() {
		Addresses t = new Addresses();
		t.setCityMatchCode("abcd");
		AssertJUnit.assertEquals(t.getCityMatchCode(), "abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getState()}.
	 */
	@Test
	public final void testGetState() {
		Addresses t = new Addresses();
		t.setState("PA");
		AssertJUnit.assertEquals(t.getState(), "PA");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getCountryCodeKey()}.
	 */
	@Test
	public final void testGetCountryCodeKey() {
		Addresses t = new Addresses();
		t.setCountryKey(1L);
		AssertJUnit.assertEquals(t.getCountryKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getLastUpdateBy()}.
	 */
	@Test
	public final void testGetLastUpdateBy() {
		Addresses t = new Addresses();
		t.setLastUpdateBy("cpruser");
		AssertJUnit.assertEquals(t.getLastUpdateBy(), "cpruser");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getDataTypeKey()}.
	 */
	@Test
	public final void testGetDataTypeKey() {
		Addresses t = new Addresses();
		t.setDataTypeKey(1L);
		AssertJUnit.assertEquals(t.getDataTypeKey(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		Addresses t = new Addresses();
		Date d = new Date();
		t.setEndDate(d);
		AssertJUnit.assertEquals(t.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getAddress2()}.
	 */
	@Test
	public final void testGetAddress2() {
		Addresses t = new Addresses();
		t.setAddress2("abcd");
		AssertJUnit.assertEquals(t.getAddress2(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		Addresses t = new Addresses();
		Date d = new Date();
		t.setStartDate(d);
		AssertJUnit.assertEquals(t.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getAddress3()}.
	 */
	@Test
	public final void testGetAddress3() {
		Addresses t = new Addresses();
		t.setAddress3("abcd");
		AssertJUnit.assertEquals(t.getAddress3(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getCreatedBy()}.
	 */
	@Test
	public final void testGetCreatedBy() {
		Addresses t = new Addresses();
		t.setCreatedBy("cpruser");
		AssertJUnit.assertEquals(t.getCreatedBy(), "cpruser");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getLastUpdateOn()}.
	 */
	@Test
	public final void testGetLastUpdateOn() {
		Date d = new Date();
		Addresses t = new Addresses();
		t.setLastUpdateOn(d);
		AssertJUnit.assertEquals(t.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getAddress1()}.
	 */
	@Test
	public final void testGetAddress1() {
		Addresses t = new Addresses();
		t.setAddress1("abcd");
		AssertJUnit.assertEquals(t.getAddress1(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getCreatedOn()}.
	 */
	@Test
	public final void testGetCreatedOn() {
		Addresses t = new Addresses();
		Date d = new Date();
		t.setCreatedOn(d);
		AssertJUnit.assertEquals(t.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
		Addresses t = new Addresses();
		t.setPersonId(1L);
		AssertJUnit.assertEquals(t.getPersonId(),new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getProvince()}.
	 */
	@Test
	public final void testGetProvince() {
		Addresses t = new Addresses();
		t.setProvince("abcd");
		AssertJUnit.assertEquals(t.getProvince(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getCity()}.
	 */
	@Test
	public final void testGetCity() {
		Addresses t = new Addresses();
		t.setCity("abcd");
		AssertJUnit.assertEquals(t.getCity(), "abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getPostalCode()}.
	 */
	@Test
	public final void testGetPostalCode() {
		Addresses t = new Addresses();
		t.setPostalCode("abcd");
		AssertJUnit.assertEquals(t.getPostalCode(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getCampusCodeKey()}.
	 */
	@Test
	public final void testGetCampusCodeKey() {
		Addresses t = new Addresses();
		t.setCampusCodeKey(1L);
		AssertJUnit.assertEquals(t.getCampusCodeKey(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Addresses#getAddressMatchCode()}.
	 */
	@Test
	public final void testGetAddressMatchCode() {
		Addresses t = new Addresses();
		t.setAddressMatchCode("abcd");
		AssertJUnit.assertEquals(t.getAddressMatchCode(),"abcd");
	}

}
