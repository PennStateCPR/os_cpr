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
import edu.psu.iam.cpr.core.database.beans.CprComponentStatus;
import edu.psu.iam.cpr.core.database.tables.CprComponentStatusTable;
import edu.psu.iam.cpr.core.database.types.ComponentStatus;
import edu.psu.iam.cpr.core.database.types.CprComponent;

public class CprComponentStatusTableTest {

	private static Database db = new Database();
	
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test
	public final void testCprComponentStatusTable() {
		new CprComponentStatusTable();
	}

	@Test
	public final void testIsCprComponentActive() throws Exception {
		openDbConnection();
		CprComponentStatusTable t = new CprComponentStatusTable();
		t.changeComponentStatus(db, CprComponent.MESSAGING, ComponentStatus.DISABLED);
		AssertJUnit.assertFalse(t.isCprComponentActive(db, CprComponent.MESSAGING));
		db.closeSession();
	}

	@Test
	public final void testChangeComponentStatus() throws Exception {
		openDbConnection();
		CprComponentStatusTable t = new CprComponentStatusTable();
		t.changeComponentStatus(db, CprComponent.MESSAGING, ComponentStatus.ACTIVE);
		AssertJUnit.assertTrue(t.isCprComponentActive(db, CprComponent.MESSAGING));
		db.closeSession();
	}

	@Test
	public final void testSetCprComponentStatusBean() {
		CprComponentStatus bean = new CprComponentStatus();
		CprComponentStatusTable t = new CprComponentStatusTable();
		t.setCprComponentStatusBean(bean);
		AssertJUnit.assertEquals(t.getCprComponentStatusBean(),bean);
	}

}
