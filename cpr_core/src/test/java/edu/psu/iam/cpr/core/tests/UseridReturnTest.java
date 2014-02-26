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

import edu.psu.iam.cpr.core.service.returns.UseridReturn;

/**
 * @author cpruser
 *
 */
public class UseridReturnTest {
	
	@Test
	public final void testGetUseridReturn() {
		new UseridReturn();
	}

    @Test
    public final void testConstructor2() {
        final UseridReturn useridReturn = new UseridReturn("cpruser", "N");
        AssertJUnit.assertEquals("cpruser", useridReturn.getUserid());
        AssertJUnit.assertEquals("N", useridReturn.getPrimary());
        AssertJUnit.assertNull(useridReturn.getStartDate());
        AssertJUnit.assertNull(useridReturn.getEndDate());
        AssertJUnit.assertNull(useridReturn.getLastUpdateBy());
        AssertJUnit.assertNull(useridReturn.getLastUpdateOn());
        AssertJUnit.assertNull(useridReturn.getCreatedBy());
        AssertJUnit.assertNull(useridReturn.getCreatedOn());
    }

    @Test
	public final void testGetUseridReturnStringString() {
		new UseridReturn("xyz123", "n","","","","","","");
	}
	
    @Test
	public final void testGetUserid() {
		final UseridReturn u = new UseridReturn("xyz123", "N","","","","","","");
		AssertJUnit.assertEquals("xyz123", u.getUserid());
	}
	
    @Test
	public final void testSetUserid() {
		final UseridReturn u = new UseridReturn();
		u.setUserid("xyz123");
		AssertJUnit.assertEquals("xyz123", u.getUserid());
	}
	
    @Test
	public final void testGetPrimary() {
		final UseridReturn u = new UseridReturn("xyz123", "N","","","","","","");
		AssertJUnit.assertEquals("N", u.getPrimary());
	}
    
	@Test
	public final void testSetPrimary() {
		final UseridReturn u = new UseridReturn();
		u.setPrimary("N");
		AssertJUnit.assertEquals("N", u.getPrimary());
	}
	@Test
	public final void testGetStartDate() {
        final UseridReturn useridReturn = new UseridReturn();
		useridReturn.setStartDate("1/1/2014");
		AssertJUnit.assertEquals("1/1/2014", useridReturn.getStartDate());
	}
	
	@Test 
	public final void testGetEndDate() {
        final UseridReturn useridReturn = new UseridReturn();
        useridReturn.setEndDate("1/1/2014");
		AssertJUnit.assertEquals("1/1/2014", useridReturn.getEndDate());
	}
	
	@Test
	public final void testGetLastUpdateBy() {
        final UseridReturn useridReturn = new UseridReturn();
        useridReturn.setLastUpdateBy("cpruser");
		AssertJUnit.assertEquals("cpruser", useridReturn.getLastUpdateBy());
	}
	
	@Test
	public final void testGetLastUpdateOn() {
        final UseridReturn useridReturn = new UseridReturn();
        useridReturn.setLastUpdateOn("1/1/2014");
		AssertJUnit.assertEquals("1/1/2014", useridReturn.getLastUpdateOn());
	}
	
	@Test
	public final void testGetCreatedBy() {
        final UseridReturn useridReturn = new UseridReturn();
        useridReturn.setCreatedBy("cpruser");
		AssertJUnit.assertEquals("cpruser", useridReturn.getCreatedBy());
	}
	
	@Test
	public final void testGetCreatedOn() {
        final UseridReturn useridReturn = new UseridReturn();
        useridReturn.setCreatedOn("1/1/2014");
		AssertJUnit.assertEquals("1/1/2014", useridReturn.getCreatedOn());
	}

    @Test
    public final void testSetUri() {
        final UseridReturn useridReturn = new UseridReturn();
        useridReturn.setUri("uri");
        AssertJUnit.assertEquals("uri", useridReturn.getUri());
    }
}
