package eduserCommentReturnpsuserCommentReturniam.cpr.core.tests;

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
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setUserCommentType("USER_COMMENT_MISUSE");
		AssertJUnit.assertEquals(userCommentReturn.getUserCommentType(),"USER_COMMENT_MISUSE");
	}

	@Test
	public final void testGetCommentDateString() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setCommentDateString("1/1/2014");
		AssertJUnit.assertEquals("1/1/2014", userCommentReturn.getCommentDateString());
	}

	@Test
	public final void testGetEndDate() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setEndDate("1/1/2014");
		AssertJUnit.assertEquals("1/1/2014", userCommentReturn.getEndDate());
	}

	@Test
	public final void testGetCommenter() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setCommenter("cpruser");
		AssertJUnit.assertEquals("cpruser", userCommentReturn.getCommenter());
	}

	@Test
	public final void testGetComment() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setComment("comment");
		AssertJUnit.assertEquals("comment", userCommentReturn.getComment());
	}

	@Test
	public final void testGetLastUpdatedBy() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setLastUpdatedBy("cpruser");
		AssertJUnit.assertEquals("cpruser", userCommentReturn.getLastUpdatedBy());
	}

	@Test
	public final void testGetLastUpdateOn() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setLastUpdateOn("1/1/2014");
		AssertJUnit.assertEquals("1/1/2014", userCommentReturn.getLastUpdateOn());
	}

    @Test
    public final void testGetCommentKey() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setCommentKey("commentKey");
        AssertJUnit.assertEquals("commentKey", userCommentReturn.getCommentKey());
    }

    @Test
    public final void testGetUri() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        userCommentReturn.setUri("uri");
        AssertJUnit.assertEquals("uri", userCommentReturn.getUri());
    }

    @Test
    public final void testToString() {
        final UserCommentReturn userCommentReturn = new UserCommentReturn();
        AssertJUnit.assertEquals("UserCommentReturn", userCommentReturn.toString());
    }
}
