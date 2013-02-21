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


import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;

/**
 * @author cpruser
 *
 */

public class MessagingCoreTest {	
	
	private static Database db = new Database();
	
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	private static String webService = "TestService";
	
	@Test
	public final void testMessagingCore() throws Exception {
		new MessagingCore();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testMessagingCoreNoDb() throws Exception {
		new MessagingCore(db, webService);
	}
	
	@Test
	public final void testMessagingCoreDbBadServiceName() throws Exception {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, "Testing");
		assertEquals(msging.getMsgQueues().size(), 0);
		db.closeSession();
	}
	
	@Test
	public final void testMessagingCoreDbJndi() throws Exception {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, webService);
		assertEquals(msging.getMsgQueues().size(), 1);
		db.closeSession();
	}
	
	@Test
	public final void testMessagingCoreBadServiceName() throws Exception {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, "Address");
		assertEquals(msging.getMsgQueues().size(), 0);
		db.closeSession();
	}
	
	@Test
	public final void testMessagingCoreSendMsg() throws Exception {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, webService);
		msging.initializeMessaging();
		JsonMessage testMsg = new JsonMessage(db, 100000, webService, "cpruser");
		msging.sendMessage(db, testMsg);
		msging.closeMessaging();
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testMessagingCoreSendMsgNull() throws Exception {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, webService);
		msging.initializeMessaging();
		msging.sendMessage(db, null);
		msging.closeMessaging();
		db.closeSession();
	}
	
}
