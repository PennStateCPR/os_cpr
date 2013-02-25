/**
 * 
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

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.ServiceLog;
import edu.psu.iam.cpr.core.database.tables.ServiceLogTable;
/**
 * @author cpruser
 *
 */
public class ServiceLogTableTest {
	
	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.ServiceLogTable#ServiceLogTable()}.
	 */
	@Test
	public final void testServiceLogTable() {
		new ServiceLogTable();
	}


	@Test
	public final void testSetServiceLogBean() {
		ServiceLogTable t = new ServiceLogTable();
		t.setServiceLogBean(new ServiceLog());
		AssertJUnit.assertNotNull(t.getServiceLogBean());
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.ServiceLogTable#getWebServiceKey()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testGetWebServiceKey1() throws Exception {
		ServiceLogTable t = new ServiceLogTable();
		t.getWebServiceKey(db, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testGetWebServiceKey2() throws Exception {
		openDbConnection();
		ServiceLogTable t = new ServiceLogTable();
		t.getWebServiceKey(db, null);
		db.closeSession();
	}
	@Test(expectedExceptions=Exception.class)
	public final void testGetWebServiceKey3() throws Exception {
		openDbConnection();
		ServiceLogTable t = new ServiceLogTable();
		t.getWebServiceKey(db, "who cares");
		db.closeSession();
	}
	
	@Test
	public final void testGetWebServiceKey4() throws Exception {
		openDbConnection();
		ServiceLogTable t = new ServiceLogTable();
		AssertJUnit.assertEquals(t.getWebServiceKey(db, "GetUserid"),new Long(100068));
		db.closeSession();
	}


	
	private ServiceLog makeBean() {
		
		ServiceLog bean = new ServiceLog();
		bean.setIpAddress("128.118.57.18");
		bean.setRequestDuration(0L);
		bean.setRequestEnd(null);
		bean.setRequestStart(new Date());
		bean.setRequestString("This is the request string from a junit test");
		bean.setRequestUserid("cpruser");
		bean.setResultString(null);
		bean.setWebServiceKey(100068L);
		
		return bean;
	}
	
	@Test
	public final void testStartLog3() throws Exception {
		ServiceLogTable t = new ServiceLogTable();
		openDbConnection();
		ServiceLog bean = makeBean();
		t.setServiceLogBean(bean);
		t.endLog(db, "SUCCESS!");
		db.closeSession();
	}
	
}
