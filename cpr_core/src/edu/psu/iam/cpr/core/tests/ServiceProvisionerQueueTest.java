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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.VSpNotification;
import edu.psu.iam.cpr.core.messaging.ServiceProvisionerQueue;

/**
 * @author jvuccolo
 *
 */
public class ServiceProvisionerQueueTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.messaging.ServiceProvisionerQueue#ServiceProvisionerServiceMapping()}.
	 */
	@Test
	public final void testServiceProvisionerServiceMapping() {
		new ServiceProvisionerQueue();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.messaging.ServiceProvisionerQueue#ServiceProvisionerServiceMapping(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testServiceProvisionerServiceMappingStringStringString() {
		new ServiceProvisionerQueue(1L, "authentication", 1L, "AddPerson", "/queue/authentication");
	}
	
	@Test public final void testGetServiceNotificationView() {
		VSpNotification view = new VSpNotification();
		ServiceProvisionerQueue q = new ServiceProvisionerQueue();
		q.setSpNotificationView(view);
		AssertJUnit.assertEquals(q.getSpNotificationView(), view);
	}

	@Test(expectedExceptions=Exception.class)
	public final void testGetServiceProvisionerServiceMapping1() throws Exception {
		ServiceProvisionerQueue.getServiceProvisionerQueues(db,"AddPerson");
	}
	
	@Test
	public final void testGetServiceProvisionerServiceMapping2() throws Exception {
		openDbConnection();
		ServiceProvisionerQueue.getServiceProvisionerQueues(db,null);
		db.closeSession();
	}
	@Test
	public final void testGetServiceProvisionerServiceMapping3() throws Exception {
		openDbConnection();
		ServiceProvisionerQueue.getServiceProvisionerQueues(db,"who cares");
		db.closeSession();
	}
	@Test
	public final void testGetServiceProvisionerServiceMapping4() throws Exception {
		openDbConnection();
		ServiceProvisionerQueue.getServiceProvisionerQueues(db,"AddPerson");
		db.closeSession();
	}	
}
