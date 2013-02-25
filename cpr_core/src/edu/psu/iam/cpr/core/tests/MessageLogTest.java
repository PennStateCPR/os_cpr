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

import edu.psu.iam.cpr.core.database.beans.MessageLog;

public class MessageLogTest {

	MessageLog m = new MessageLog();
	Date d = new Date();
	String s= "abcd";
	
	@Test
	public final void testMessageLog() {
		AssertJUnit.assertNotNull(m);
	}

	@Test
	public final void testGetSuccessFlag() {
		m.setSuccessFlag(s);
		AssertJUnit.assertEquals(m.getSuccessFlag(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		m.setLastUpdateOn(d);
		AssertJUnit.assertEquals(m.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetServiceProvisionerKey() {
		m.setServiceProvisionerKey(1L);
		AssertJUnit.assertEquals(m.getServiceProvisionerKey(), new Long(1));
	}

	@Test
	public final void testGetCreatedOn() {
		m.setCreatedOn(d);
		AssertJUnit.assertEquals(m.getCreatedOn(),d);
	}

	@Test
	public final void testGetRequestUserid() {
		m.setRequestUserid(s);
		AssertJUnit.assertEquals(m.getRequestUserid(),s);
	}

	@Test
	public final void testGetWebServiceKey() {
		m.setWebServiceKey(1L);
		AssertJUnit.assertEquals(m.getWebServiceKey(),new Long(1L));
	}

	@Test
	public final void testGetNumberOfTries() {
		m.setNumberOfTries(1L);
		AssertJUnit.assertEquals(m.getNumberOfTries(), new Long(1));
	}

	@Test
	public final void testGetMessageSent() {
		m.setMessageSent(s);
		AssertJUnit.assertEquals(m.getMessageSent(),s);
	}

	@Test
	public final void testGetMessageLogKey() {
		m.setMessageLogKey(1L);
		AssertJUnit.assertEquals(m.getMessageLogKey(), new Long(1));
	}

}
