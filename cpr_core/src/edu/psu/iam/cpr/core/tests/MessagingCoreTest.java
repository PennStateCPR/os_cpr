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
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;

/**
 * @author slk24
 *
 */

public class MessagingCoreTest {	
	
	private static Database db = new Database();
	
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	private static String webService = "TestService";
	
	@Test
	public final void testMessagingCore() throws CprException {
		new MessagingCore();
	}
	
	@Test(expectedExceptions=CprException.class)
	public final void testMessagingCoreNoDb() throws CprException {
		new MessagingCore(db, webService);
	}
	
	@Test
	public final void testMessagingCoreDbBadServiceName() throws CprException, GeneralDatabaseException {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, "Testing");
		assertEquals(msging.getMsgQueues().size(), 0);
		db.closeSession();
	}
	
	@Test
	public final void testMessagingCoreDbJndi() throws CprException, GeneralDatabaseException {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, webService);
		assertEquals(msging.getMsgQueues().size(), 1);
		db.closeSession();
	}
	
	@Test
	public final void testMessagingCoreBadServiceName() throws CprException, GeneralDatabaseException {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, "Address");
		assertEquals(msging.getMsgQueues().size(), 0);
		db.closeSession();
	}
	
	@Test
	public final void testMessagingCoreSendMsg() throws CprException, GeneralDatabaseException {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, webService);
		msging.initializeMessaging();
		JsonMessage testMsg = new JsonMessage(db, 100000, webService, "slk24");
		msging.sendMessage(db, testMsg);
		db.closeSession();
	}
	
	@Test(expectedExceptions=CprException.class)
	public final void testMessagingCoreSendMsgNull() throws CprException, GeneralDatabaseException {
		openDbConnection();
		MessagingCore msging = new MessagingCore(db, webService);
		msging.initializeMessaging();
		msging.sendMessage(db, null);
		db.closeSession();
	}
	
//	@Test
//	public final void testMessagingCoreRcvNoMsg() throws CprException, GeneralDatabaseException {
//		openDbConnection();
//		MessagingCore msging = new MessagingCore(db, webService, connection);
//		ServiceProvisionerQueue testSPQueue = msging.getMsgQueues().get(0);
//		Queue testQueue = testSPQueue.getServiceProvisionerQueue();
//		String rcvMsg = "Initial read";
//		while (rcvMsg != null) {
//			rcvMsg = msging.receiveMessageNoWait(testQueue);
//		}
//		assertNull(rcvMsg);
//		db.closeSession();
//	}
//	
//	@Test
//	public final void testMessagingCoreRcvMsg() throws GeneralDatabaseException, CprException {
//		openDbConnection();
//		MessagingCore msging = new MessagingCore(db, webService, connection);
//		ServiceProvisionerQueue testQueue = msging.getMsgQueues().get(0);
//		String rcvMsg = msging.receiveMessageWait(testQueue.getServiceProvisionerQueue());
//		assertNotNull(rcvMsg);
//		db.closeSession();
//	}
//	
//	@Test(expected=CprException.class)
//	public final void testMessagingCoreSendMsgNotAuthorized() throws GeneralDatabaseException, CprException {
//		openDbConnection();
//		MessagingCore msging = new MessagingCore(db, webService, connection, true);
//		JsonMessage testMsg = new JsonMessage(db, 100000, webService, "slk24");
//		System.out.println("testing");
//		msging.sendMessage(db, testMsg);
//		db.closeSession();
//	}

}
