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

import edu.psu.iam.cpr.core.database.beans.GeneratedIdentity;

public class GeneratedIdentityTest {

	String s = "abcd";
	Date d = new Date();
	GeneratedIdentity g = new GeneratedIdentity();
	
	@Test
	public final void testGetNumPart() {
		g.setNumPart(1L);
		AssertJUnit.assertEquals(g.getNumPart(),new Long(1L));
		
	}

	@Test
	public final void testGetCreatedBy() {
		g.setCreatedBy(s);
		AssertJUnit.assertEquals(g.getCreatedBy(),s);
	}

	@Test
	public final void testGetCreatedOn() {
		g.setCreatedOn(d);
		AssertJUnit.assertEquals(g.getCreatedOn(),d);
	}

	@Test
	public final void testGetGeneratedIdentityKey() {
		g.setGeneratedIdentityKey(1L);
		AssertJUnit.assertEquals(g.getGeneratedIdentityKey(),new Long(1L));
	}

	@Test
	public final void testGetCharPart() {
		g.setCharPart(s);
		AssertJUnit.assertEquals(g.getCharPart(),s);
	}

	@Test
	public final void testGetPersonId() {
		g.setPersonId(1L);
		AssertJUnit.assertEquals(g.getPersonId(),new Long(1L));
	}

	@Test
	public final void testGetSetId() {
		g.setSetId(1L);
		AssertJUnit.assertEquals(g.getSetId(),new Long(1));
	}

	@Test
	public final void testGetGeneratedIdentity() {
		g.setGeneratedIdentity(s);
		AssertJUnit.assertEquals(g.getGeneratedIdentity(),s);
	}

}
