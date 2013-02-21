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


import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import org.testng.annotations.Test;
import java.util.Date;

import edu.psu.iam.cpr.core.database.beans.PersonUseridIap;

public class PersonUseridIapTest {
	
	PersonUseridIap p = new PersonUseridIap();
	Date d = new Date();
	
	@Test
	public final void testPersonUseridIap() {
		assertNotNull(p);
	}
	
	@Test
	public final void testGetPersonId() {
		p.setPersonId(1L);
		assertEquals(p.getPersonId(),new Long(1));
	}
	
	@Test
	public final void testGetUserid() {
		p.setUserid("cpruser");
		assertEquals(p.getUserid(),"cpruser");
	}
	@Test
	public final void testGetIapKey() {
		p.setIapKey(1L);
		assertEquals(p.getIapKey(),new Long(1));
	}
	
	@Test 
	public final void testSetStartDate() {
		p.setStartDate(d);
		assertEquals(p.getStartDate(), d);
	}


	@Test 
 	public final void testSetEndDate() {
		p.setEndDate(d);
		assertEquals(p.getEndDate(), d);
	}

	@Test 
	public final void testSetCreatedBy() {
		p.setCreatedBy("cpruser");
		assertEquals(p.getCreatedBy(), "cpruser");
	}	
	@Test 
	public final void testSetCreatedOn() {
		p.setCreatedOn(d);
		assertEquals(p.getCreatedOn(), d);
	}

	@Test
	public final void testLastUpdateBy( ){
		p.setLastUpdateBy("cpruser");
		assertEquals(p.getLastUpdateBy(), "cpruser");
	}
	@Test 
	public final void testLastUpdateOn() {
		p.setLastUpdateOn(d);
		assertEquals(p.getLastUpdateOn(), d);
	}
	
}
