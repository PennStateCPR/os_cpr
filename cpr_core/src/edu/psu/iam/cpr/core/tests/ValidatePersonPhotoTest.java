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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonPhoto;

public class ValidatePersonPhotoTest {

	private static Database db = new Database();

	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters1() throws Exception {
		ValidatePersonPhoto.validateAddPhotoParameters(null, null, null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters2() throws Exception {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters3() throws Exception {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters4() throws Exception {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], "1/1/2011", null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters5() throws Exception {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], "1/1/", "cpruser");
	}
	
	@Test
	public final void testValidateAddPhotoParameters6() throws Exception {
		openDbConnection();
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], "1/1/2011", "cpruser");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPhotoParameters1() throws Exception {
		ValidatePersonPhoto.validateGetPhotoParameters(null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPhotoParameters2() throws Exception {
		ValidatePersonPhoto.validateGetPhotoParameters(db, 1L, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPhotoParameters3() throws Exception {
		ValidatePersonPhoto.validateGetPhotoParameters(db, 1L, "");
	}
	
	@Test
	public final void testValidateGetPhotoParameters4() throws Exception {
		openDbConnection();
		ValidatePersonPhoto.validateGetPhotoParameters(db, 1L, "cpruser");
		db.closeSession();
	}

}
