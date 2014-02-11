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

import edu.psu.iam.cpr.core.service.returns.UserCommentReturn;

public class UserCommentReturnTest {

	UserCommentReturn u = new UserCommentReturn();
	String s = "abcd";
	
	@Test
	public final void testUserCommentReturn() {
		new UserCommentReturn();
	}

	@Test
	public final void testUserCommentReturnStringStringStringStringStringStringString() {
		new UserCommentReturn(null,null,null,null,null,null,null, null);
	}

	@Test
	public final void testGetUserCommentType() {
		u.setUserCommentType("USER_COMMENT_MISUSE");
		AssertJUnit.assertEquals(u.getUserCommentType(),"USER_COMMENT_MISUSE");
	}

	@Test
	public final void testGetCommentDateString() {
		u.setCommentDateString(s);
		AssertJUnit.assertEquals(u.getCommentDateString(),s);
	}

	@Test
	public final void testGetEndDate() {
		u.setEndDate(s);
		AssertJUnit.assertEquals(u.getEndDate(),s);
	}

	@Test
	public final void testGetCommenter() {
		u.setCommenter(s);
		AssertJUnit.assertEquals(u.getCommenter(),s);
	}

	@Test
	public final void testGetComment() {
		u.setComment(s);
		AssertJUnit.assertEquals(u.getComment(),s);
	}

	@Test
	public final void testGetLastUpdatedBy() {
		u.setLastUpdatedBy(s);
		AssertJUnit.assertEquals(u.getLastUpdatedBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		u.setLastUpdateOn(s);
		AssertJUnit.assertEquals(u.getLastUpdateOn(),s);
	}

}
