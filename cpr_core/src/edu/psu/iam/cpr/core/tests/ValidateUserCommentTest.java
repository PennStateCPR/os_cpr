/* SVN FILE: $Id: ValidateUserCommentTest.java 5340 2012-09-27 14:48:52Z cpruser $ */
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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment;
import edu.psu.iam.cpr.core.database.types.UserCommentType;


/**
 * @author Bob Walters
 *
 */
public class ValidateUserCommentTest {

	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentsParameters(Database, long, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters1() throws Exception {
		ValidateUserComment.validateGetUserCommentsParameters(db, 100000, null,null,"N");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentsParameters(Database, long, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters2() throws Exception {
		ValidateUserComment.validateGetUserCommentsParameters(db, 100000, "",null,"N");
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentsParameters(Database, long, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters3() throws Exception {
		openDbConnection();
		ValidateUserComment.validateGetUserCommentsParameters(db, 100000, "1234567890123456789012345678901",null,"N");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentsParameters(Database, long, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test
	public final void testValidateUserCommentParameters4() throws Exception {
		openDbConnection();
		ValidateUserComment.validateGetUserCommentsParameters(db, 100000, "cpruser",null,"Y");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentsParameters(Database, long, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test
	public final void testValidateUserCommentParameters37() throws Exception {
		openDbConnection();
		UserCommentTable userCommentTable  = ValidateUserComment.validateGetUserCommentsParameters(db, 100000, "cpruser","USER_COMMENT_MISUSE","Y");
		AssertJUnit.assertTrue(userCommentTable.isReturnHistoryFlag());
		AssertJUnit.assertEquals(userCommentTable.getUserCommentType(),UserCommentType.USER_COMMENT_MISUSE);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters5() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, null, null, null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters6() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, "", null, null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters7() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", null, null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters8() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", "", null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters9() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters10() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "", null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters11() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "comment", null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters12() throws Exception {
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "comment", "" ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters13() throws Exception {
		openDbConnection();
		ValidateUserComment.validateUserCommentParameters( db, 100000, "1234567890123456789012345678901", "USER_COMMENT_MISUSE", "comment", "cpruser" );
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters14() throws Exception {
		openDbConnection();
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "comment", "1234567890123456789012345678901" );
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters15() throws Exception {
		openDbConnection();
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", "cpruser" );
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters16() throws Exception {
		openDbConnection();
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tuj355", "USER_COMMENT_MISUSE!", "comment", "cpruser" );
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateUserCommentsParameters(Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void testValidateUserCommentParameters17() throws Exception {
		openDbConnection();
		ValidateUserComment.validateUserCommentParameters( db, 100000, "tuj355", "USER_COMMENT_MISUSE", "comment", "cpruser" );
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters18() throws Exception {
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, null, null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters19() throws Exception {
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "", null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters20() throws Exception {
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "tzj115", null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters21() throws Exception {
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "tzj115", "", null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters22() throws Exception {
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters23() throws Exception {
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "" ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters24() throws Exception {
		openDbConnection();
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "1234567890123456789012345678901", "USER_COMMENT_MISUSE", "cpruser" ); 
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters25() throws Exception {
		openDbConnection();
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "1234567890123456789012345678901" ); 
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters26() throws Exception {
		openDbConnection();
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE!", "cpruser" ); 
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateArchiveUserCommentParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test
	public final void testValidateUserCommentParameters27() throws Exception {
		openDbConnection();
		ValidateUserComment.validateArchiveUserCommentParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "cpruser" ); 
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters28() throws Exception {
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, null, null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters29() throws Exception {
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "", null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters30() throws Exception {
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "tzj115", null, null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters31() throws Exception {
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "tzj115", "", null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters32() throws Exception {
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", null ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters33() throws Exception {
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "" ); 
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters34() throws Exception {
		openDbConnection();
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "1234567890123456789012345678901", "USER_COMMENT_MISUSE", "cpruser" ); 
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateUserCommentParameters35() throws Exception {
		openDbConnection();
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "1234567890123456789012345678901" ); 
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment#validateGetUserCommentByTypeParameters(Database, long, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test
	public final void testValidateUserCommentParameters36() throws Exception {
		openDbConnection();
		ValidateUserComment.validateGetUserCommentByTypeParameters( db, 100000, "tzj115", "USER_COMMENT_MISUSE", "cpruser" ); 
		db.closeSession();
	}
}
