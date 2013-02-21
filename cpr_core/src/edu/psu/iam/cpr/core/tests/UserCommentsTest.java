/* SVN FILE: $Id: UserCommentsTest.java 5340 2012-09-27 14:48:52Z cpruser $ */
/**
 * 
 * @package edu.psu.iam.cpr.core.tests
 * @author $Author: cpruser $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
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

import edu.psu.iam.cpr.core.database.beans.UserComments;

public class UserCommentsTest {

	UserComments c = new UserComments();
	Date d = new Date();
	
	@Test
	public final void testUserComments() {
		AssertJUnit.assertNotNull(c);
	}

	@Test
	public final void testGetStartDate() {
		c.setStartDate(d);
		AssertJUnit.assertEquals(c.getStartDate(),d);
	}

	@Test
	public final void testGetCreatedBy() {
		c.setCreatedBy("abcd");
		AssertJUnit.assertEquals(c.getCreatedBy(),"abcd");
	}

	@Test
	public final void testGetLastUpdateOn() {
		c.setLastUpdateOn(d);
		AssertJUnit.assertEquals(c.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetCreatedOn() {
		c.setCreatedOn(d);
		AssertJUnit.assertEquals(c.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		c.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(c.getLastUpdateBy(),"abcd");
	}

	@Test
	public final void testGetDataTypeKey() {
		c.setDataTypeKey(1L);
		AssertJUnit.assertEquals(c.getDataTypeKey(),new Long(1));
	}

	@Test
	public final void testGetComments() {
		c.setComments("abcd");
		AssertJUnit.assertEquals(c.getComments(),"abcd");
	}

	@Test
	public final void testGetPersonId() {
		c.setPersonId(1L);
		AssertJUnit.assertEquals(c.getPersonId(),new Long(1));
	}

	@Test
	public final void testGetEndDate() {
		c.setEndDate(d);
		AssertJUnit.assertEquals(c.getEndDate(),d);
	}

	@Test
	public final void testGetUserCommentKey() {
		c.setUserCommentKey(1L);
		AssertJUnit.assertEquals(c.getUserCommentKey(),new Long(1));
	}

	@Test
	public final void testGetUserid() {
		c.setUserid("abcd");
		AssertJUnit.assertEquals(c.getUserid(),"abcd");
	}

}
