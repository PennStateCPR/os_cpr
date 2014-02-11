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

import edu.psu.iam.cpr.core.database.beans.UseridPool;

public class UseridPoolTest {

	UseridPool u = new UseridPool();
	String s = "abcd";
	Date d = new Date();
	
	@Test
	public final void testUseridPool() {
		new UseridPool();
	}

	@Test
	public final void testGetNumPart() {
		u.setNumPart(1L);
		AssertJUnit.assertEquals(u.getNumPart(), new Long(1L));
	}

	@Test
	public final void testGetCreatedBy() {
		u.setCreatedBy(s);
		AssertJUnit.assertEquals(u.getCreatedBy(),s);
	}

	@Test
	public final void testGetCreatedOn() {
		u.setCreatedOn(d);
		AssertJUnit.assertEquals(u.getCreatedOn(),d);
	}

	@Test
	public final void testGetCharPart() {
		u.setCharPart(s);
		AssertJUnit.assertEquals(u.getCharPart(),s);
	}

	@Test
	public final void testGetUseridPoolKey() {
		u.setUseridPoolKey(1L);
		AssertJUnit.assertEquals(u.getUseridPoolKey(),new Long(1L));
	}

	@Test
	public final void testGetUserid() {
		u.setUserid(s);
		AssertJUnit.assertEquals(u.getUserid(),s);
	}

}
