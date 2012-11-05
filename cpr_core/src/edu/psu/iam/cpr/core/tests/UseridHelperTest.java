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
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.helpers.UseridHelper;
import edu.psu.iam.cpr.core.database.tables.GeneratedIdentityTable;

public class UseridHelperTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test
	public final void testGenerateUserid() throws Exception {
		Userid bean = new Userid();
		bean.setPersonId(100000L);
		bean.setLastUpdateBy("jvuccolo");
		bean.setCreatedBy("jvuccolo");
		UseridHelper u = new UseridHelper();
		openDbConnection();
		u.generateUserid(db.getSession(), bean);
		u.getGeneratedIdentityTable().removeGeneratedIdentity(db.getSession());
		db.closeSession();
		
	}

	@Test
	public final void testSetGeneratedIdentityTable() {
		GeneratedIdentityTable g = new GeneratedIdentityTable(0, null, null);
		UseridHelper u = new UseridHelper();
		u.setGeneratedIdentityTable(g);
		AssertJUnit.assertEquals(u.getGeneratedIdentityTable(),g);
		
	}

}
