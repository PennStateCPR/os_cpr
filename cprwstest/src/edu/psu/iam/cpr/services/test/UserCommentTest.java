/**
 * 
 */
package edu.psu.iam.cpr.services.test;

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
import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.ServiceReturn;
import edu.psu.iam.cpr.service.UserCommentServiceReturn;

public class UserCommentTest {
	
	static final Cprws userid = new Cprws();
	static final CprwsSEI port = userid.getCprwsPort();


	
	// No comments found.
	@Test
	public void _01testGetUserComment4() throws Exception {
		UserCommentServiceReturn userCommentServiceReturn = port.getUserComments(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "tuj420", null, "N");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Success.
	@Test
	public void _02testAddUserComment() throws Exception {
		ServiceReturn userCommentServiceReturn = port.addUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "dummy1", "USER_COMMENT_MISUSE", "test comment");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	
	// Success without history.
	@Test
	public void _03testGetUserComment5() throws Exception {
		UserCommentServiceReturn userCommentServiceReturn = port.getUserComments(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "dummy1", null,  "N");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Success.
	@Test
	public void _04testUpdateUserComment() throws Exception {
		ServiceReturn userCommentServiceReturn = port.updateUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "dummy1", "USER_COMMENT_MISUSE", "second comment");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}	
	// Success with history.
	@Test
	public void _05testGetUserCommentHistory1() throws Exception {
		UserCommentServiceReturn userCommentServiceReturn = port.getUserComments(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "tuj420", null ,"Y");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Success with history.
	@Test
	public void _06testGetUserCommentByType1() throws Exception {
		UserCommentServiceReturn userCommentServiceReturn = port.getUserComments(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "tuj420", "USER_COMMENT_MISUSE", "N");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Success.
	@Test
	public void _07testArchiveUserComment5() throws Exception {
		ServiceReturn userCommentServiceReturn = port.archiveUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "dummy1", "USER_COMMENT_MISUSE" );
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}	
	// Invalid password.
	@Test(expectedExceptions=Exception.class)
	public void _08testGetUserComment1() throws Exception {
		UserCommentServiceReturn userCommentServiceReturn = port.getUserComments(ServiceAuthentication.GOOD_USERID, "blah", "raw121", "person_id", "100000", "", null, "N");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Not authorized user.
	@Test(expectedExceptions=Exception.class)
	public void _09testGetUserComment2() throws Exception {
		UserCommentServiceReturn userCommentServiceReturn = port.getUserComments(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jxs", "person_id", "100000", "", null, "N");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Invalid person id.
	@Test(expectedExceptions=Exception.class)
	public void _10testGetUserComment3() throws Exception {
		UserCommentServiceReturn userCommentServiceReturn = port.getUserComments(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jxs", "person_id", "1", "", null, "N");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}	
	// Invalid password.
	@Test(expectedExceptions=Exception.class)
	public void _11testAddUserComment1() throws Exception {
		ServiceReturn userCommentServiceReturn = port.addUserComment(ServiceAuthentication.GOOD_USERID, "blah", "raw121", "person_id", "100000", "", "", "");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Not authorized user.
	@Test(expectedExceptions=Exception.class)
	public void _12testAddUserComment2() throws Exception {
		ServiceReturn userCommentServiceReturn = port.addUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jxs", "person_id", "100000", "", "", "");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Invalid person id.
	@Test(expectedExceptions=Exception.class)
	public void _13testAddUserComment3() throws Exception {
		ServiceReturn userCommentServiceReturn = port.addUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jxs", "person_id", "1", "", "", "");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Invalid userid.
	@Test(expectedExceptions=Exception.class)
	public void _14testAddUserComment4() throws Exception {
		ServiceReturn userCommentServiceReturn = port.addUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "", "USER_COMMENT_MISUSE", "test comment");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Invalid comment type.
	@Test(expectedExceptions=Exception.class)
	public void _15testAddUserComment5() throws Exception {
		ServiceReturn userCommentServiceReturn = port.addUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jxs", "person_id", "100000", "tuj420", "USER_COMMENT_BAD", "test comment");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// No comment.
	@Test(expectedExceptions=Exception.class)
	public void _16testAddUserComment6() throws Exception {
		ServiceReturn userCommentServiceReturn = port.addUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "tuj420", "USER_COMMENT_MISUSE", "");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Missing user comment type.
	@Test(expectedExceptions=Exception.class)
	public void _17testArchiveUserComment1() throws Exception {
		ServiceReturn userCommentServiceReturn = port.archiveUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "", "tuj420");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}

	// Invalid user comment type.
	@Test(expectedExceptions=Exception.class)
	public void _18testArchiveUserComment2() throws Exception {
		ServiceReturn userCommentServiceReturn = port.archiveUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "tuj420", "USER_COMMENT_BAD");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Missing userid.
	@Test(expectedExceptions=Exception.class)
	public void _19testArchiveUserComment3() throws Exception {
		ServiceReturn userCommentServiceReturn = port.archiveUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "", "USER_COMMENT_MISUSE");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}
	// Invalid userid.
	@Test(expectedExceptions=Exception.class)
	public void _20testArchiveUserComment4() throws Exception {
		ServiceReturn userCommentServiceReturn = port.archiveUserComment(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "raw121", "person_id", "100000", "ux105", "USER_COMMENT_MISUSE");
		if (userCommentServiceReturn.getStatusCode() != 0) {
			throw new Exception(userCommentServiceReturn.getStatusMessage());
		}
	}

}
