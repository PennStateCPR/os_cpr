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
import edu.psu.iam.cpr.core.database.beans.VSpNotification;

public class VSpNotificationTest {

	VSpNotification v = new VSpNotification();
	String s = "abcd";
	
	@Test
	public final void testVSpNotification() {
		AssertJUnit.assertNotNull(v);
	}

	@Test
	public final void testGetServiceProvisionerQueue() {
		v.setServiceProvisionerQueue(s);
		AssertJUnit.assertEquals(v.getServiceProvisionerQueue(),s);
	}

	@Test
	public final void testGetServiceProvisionerKey() {
		v.setServiceProvisionerKey(1L);
		AssertJUnit.assertEquals(v.getServiceProvisionerKey(),new Long(1));
	}

	@Test
	public final void testGetWebServiceKey() {
		v.setWebServiceKey(1L);
		AssertJUnit.assertEquals(v.getWebServiceKey(), new Long(1));
	}

	@Test
	public final void testGetWebService() {
		v.setWebService(s);
		AssertJUnit.assertEquals(v.getWebService(),s);
	}

	@Test
	public final void testGetServiceProvisioner() {
		v.setServiceProvisioner(s);
		AssertJUnit.assertEquals(v.getServiceProvisioner(),s);
	}

}
