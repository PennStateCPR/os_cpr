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
import edu.psu.iam.cpr.core.database.beans.PsuId;
import edu.psu.iam.cpr.core.database.helpers.PsuIdHelper;
import edu.psu.iam.cpr.core.database.tables.GeneratedIdentityTable;

public class PsuIdHelperTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test
	public final void testGeneratePSUIdNumber() throws Exception {
		PsuIdHelper p = new PsuIdHelper();
		PsuId bean = new PsuId();
		bean.setPersonId(100000L);
		bean.setLastUpdateBy("jvuccolo");
		bean.setCreatedBy("jvuccolo");
		openDbConnection();
		p.generatePSUIdNumber(db.getSession(), bean);
		p.getGeneratedIdentityTable().removeGeneratedIdentity(db.getSession());
		db.closeSession();
	}

	@Test
	public final void testGetGeneratedIdentityTable() {
		GeneratedIdentityTable g = new GeneratedIdentityTable(0, null, null);
		PsuIdHelper p = new PsuIdHelper();
		p.setGeneratedIdentityTable(g);
		AssertJUnit.assertEquals(p.getGeneratedIdentityTable(),g);
	}


}
