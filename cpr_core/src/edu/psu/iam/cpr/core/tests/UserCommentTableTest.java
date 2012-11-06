/* SVN FILE: $Id: UserCommentTableTest.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
 * 
 * @package edu.psu.iam.cpr.core.tests
 * @author $Author: jvuccolo $
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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.UserComments;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.database.types.UserCommentType;
import edu.psu.iam.cpr.core.service.returns.UserCommentReturn;

/**
 * @author jvuccolo
 *
 */
public class UserCommentTableTest {

	private static Database db= new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UserCommentTable#UserCommentTable()}.
	 */
	@Test
	public final void _01testUserCommentTable() {
		new UserCommentTable();
	}

	@Test
	public final void _02testGetUserCommentsBean() {
		UserComments bean = new UserComments();
		UserCommentTable t = new UserCommentTable();
		t.setUserCommentsBean(bean);
		AssertJUnit.assertEquals(t.getUserCommentsBean(),bean);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UserCommentTable#UserCommentTable(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _03testUserCommentTableIntStringStringStringString() throws Exception {
		new UserCommentTable(1, "xyz123", "USER_COMMENT_MISUSE", "TEST", "raw121" );
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UserCommentTable#getUserCommentType()}.
	 */
	@Test
	public final void _04testGetUserCommentType() {
		UserCommentTable n = new UserCommentTable();
		n.setUserCommentType(UserCommentType.USER_COMMENT_MISUSE);
		AssertJUnit.assertEquals(n.getUserCommentType(), UserCommentType.USER_COMMENT_MISUSE);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UserCommentTable#setUserCommentType(edu.psu.iam.cpr.core.database.types.UserCommentType)}.
	 */
	@Test
	public final void _05testSetUserCommentType() {
		UserCommentTable n = new UserCommentTable();
		n.setUserCommentType(UserCommentType.USER_COMMENT_MISUSE);
		AssertJUnit.assertEquals(n.getUserCommentType(), UserCommentType.USER_COMMENT_MISUSE);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UserCommentTable#setUserCommentType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _06testSetUserCommentTypeString1() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.setUserCommentType(n.findUserCommentTypeEnum(""));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UserCommentTable#setNameType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _07testSetUserCommentTypeString2() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.setUserCommentType(n.findUserCommentTypeEnum("misuse"));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.UserCommentTable#setNameType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _08testSetUserCommentTypeString3() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.setUserCommentType(n.findUserCommentTypeEnum("user_comment_misuse"));
		AssertJUnit.assertEquals(n.getUserCommentType(), UserCommentType.USER_COMMENT_MISUSE);
	}

	@Test(expectedExceptions=Exception.class)
	public final void _09testAddUserComment1() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.addUserComment(null);
	}

	@Test
	public final void _10testAddUserComment2() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable(100000,"dummy1","USER_COMMENT_MISUSE", "bad", "jvuccolo" );
		n.addUserComment(db);
		db.closeSession();
	}
	

	
	@Test(expectedExceptions=Exception.class) 
	public final void _11testGetUserCommentByType1() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.getUserComments(db, null);
	}
	
	@Test
	public final void _12testGetUserCommentByType2() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable();
		n.setUserCommentType(n.findUserCommentTypeEnum("user_comment_misuse"));
		n.setReturnHistoryFlag(false);
		UserCommentReturn[] results = n.getUserComments(db, "dummy");
		db.closeSession();
		AssertJUnit.assertEquals(results.length, 0);
	}
	
	@Test
	public final void _13testGetUserCommentByType3() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable();
		n.setUserCommentType(n.findUserCommentTypeEnum("user_comment_misuse"));
		n.setReturnHistoryFlag(false);
		n.getUserComments(db, "dummy1");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class) 
	public final void _14testGetUserComments1() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.getUserComments(db, null);
	}
	
	@Test
	public final void _15testGetUserComments2() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable();
		n.setReturnHistoryFlag(true);
		UserCommentReturn[] results = n.getUserComments(db, "dummy");
		db.closeSession();
		AssertJUnit.assertEquals(results.length,0);
	}
	
	@Test
	public final void _16testGetUserComments3() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable();
		n.setReturnHistoryFlag(true);
		n.getUserComments(db, "dummy1");
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _17testArchiveUserComment1() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.archiveUserComment(null);
	}

	@Test
	public final void _18testArchiveUserComment3() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable(100000,"dummy2","USER_COMMENT_MISUSE", "test comment", "raw121" );
		n.addUserComment(db);
		n.archiveUserComment(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _19testArchiveUserComment4() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable(100000,"dummy2","USER_COMMENT_MISUSE",  "raw121" );
		n.archiveUserComment(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _20testUpdateUserComment() throws Exception {
		UserCommentTable n = new UserCommentTable();
		n.updateUserComment(null);
	}
	
//	@Test(expected=Exception.class)
//	public final void _21testUpdateUserComment1() throws Exception {
//		openDbConnection();
//		UserCommentTable n = new UserCommentTable(100000,"dummy1","USER_COMMENT_MISUSE", "bad", "jvuccolo" );
//		n.updateUserComment(db);
//		db.closeSession();
//	}

	@Test
	public final void _22testUpdateUserComment2() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable(100000,"dummy1","USER_COMMENT_MISUSE", "very bad", "jvuccolo" );
		n.updateUserComment(db);
		db.closeSession();
	}
	
	@Test
	public final void _23testAddUserComment2CleanUp() throws Exception {
		openDbConnection();
		UserCommentTable n = new UserCommentTable(100000,"dummy1","USER_COMMENT_MISUSE",  "jvuccolo" );
		n.archiveUserComment(db);
		db.closeSession();
	}
}
