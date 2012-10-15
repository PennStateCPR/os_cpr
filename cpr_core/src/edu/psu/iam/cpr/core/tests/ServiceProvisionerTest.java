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

import edu.psu.iam.cpr.core.database.beans.ServiceProvisioner;

public class ServiceProvisionerTest {

	ServiceProvisioner sp = new ServiceProvisioner();
	Date d = new Date();
	String s = "abcd";
	
	@Test
	public final void testServiceProvisioner() {
		AssertJUnit.assertNotNull(sp);
	}

	@Test
	public final void testGetServiceProvisionerEmail() {
		sp.setServiceProvisionerEmail(s);
		AssertJUnit.assertEquals(sp.getServiceProvisionerEmail(),s);
	}

	@Test
	public final void testGetServiceProvisionerKey() {
		sp.setServiceProvisionerKey(1L);
		AssertJUnit.assertEquals(sp.getServiceProvisionerKey(),new Long(1));
	}

	@Test
	public final void testGetServiceProvisionerQueue() {
		sp.setServiceProvisionerQueue(s);
		AssertJUnit.assertEquals(sp.getServiceProvisionerQueue(),s);
	}

	@Test
	public final void testGetRetryInterval() {
		sp.setRetryInterval(1L);
		AssertJUnit.assertEquals(sp.getRetryInterval(),new Long(1));
	}

	@Test
	public final void testGetLastUpdateBy() {
		sp.setLastUpdateBy(s);
		AssertJUnit.assertEquals(sp.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetEndDate() {
		sp.setEndDate(d);
		AssertJUnit.assertEquals(sp.getEndDate(),d);
	}

	@Test
	public final void testGetStartDate() {
		sp.setStartDate(d);
		AssertJUnit.assertEquals(sp.getStartDate(),d);
	}

	@Test
	public final void testGetCreatedBy() {
		sp.setCreatedBy(s);
		AssertJUnit.assertEquals(sp.getCreatedBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		sp.setLastUpdateOn(d);
		AssertJUnit.assertEquals(sp.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetCreatedOn() {
		sp.setCreatedOn(d);
		AssertJUnit.assertEquals(sp.getCreatedOn(),d);
	}

	@Test
	public final void testGetMaximumFailure() {
		sp.setMaximumFailure(1L);
		AssertJUnit.assertEquals(sp.getMaximumFailure(),new Long(1));
	}

	@Test
	public final void testGetServiceProvisionerRegDate() {
		sp.setServiceProvisionerRegDate(d);
		AssertJUnit.assertEquals(sp.getServiceProvisionerRegDate(),d);
	}

	@Test
	public final void testGetServiceProvisioner() {
		sp.setServiceProvisioner(s);
		AssertJUnit.assertEquals(sp.getServiceProvisioner(),s);
	}

	@Test
	public final void testGetSuspendFlag() {
		sp.setSuspendFlag(s);
		AssertJUnit.assertEquals(sp.getSuspendFlag(),s);
	}

	@Test
	public final void testGetMaximumRetries() {
		sp.setMaximumRetries(1L);
		AssertJUnit.assertEquals(sp.getMaximumRetries(),new Long(1));
	}

	@Test
	public final void testGetMaximumQueueSize() {
		sp.setMaximumQueueSize(1L);
		AssertJUnit.assertEquals(sp.getMaximumQueueSize(), new Long(1));
	}

}
