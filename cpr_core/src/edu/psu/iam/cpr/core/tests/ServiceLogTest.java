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

import edu.psu.iam.cpr.core.database.beans.ServiceLog;

/**
 * @author cpruser
 *
 */
public class ServiceLogTest {
	
	ServiceLog s = new ServiceLog();
	Date d = new Date();
	

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#ServiceLog()}.
	 */
	@Test
	public final void testServiceLog() {
		AssertJUnit.assertNotNull(s);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getRequestEnd()}.
	 */
	@Test
	public final void testGetRequestEnd() {
		s.setRequestEnd(d);
		AssertJUnit.assertEquals(s.getRequestEnd(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getRequestStart()}.
	 */
	@Test
	public final void testGetRequestStart() {
		s.setRequestStart(d);
		AssertJUnit.assertEquals(s.getRequestStart(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getRequestUserid()}.
	 */
	@Test
	public final void testGetRequestUserid() {
		s.setRequestUserid("abcd");
		AssertJUnit.assertEquals(s.getRequestUserid(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getWebServiceKey()}.
	 */
	@Test
	public final void testGetWebServiceKey() {
		s.setWebServiceKey(1L);
		AssertJUnit.assertEquals(s.getWebServiceKey(),new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getServiceLogKey()}.
	 */
	@Test
	public final void testGetServiceLogKey() {
		s.setServiceLogKey(1L);
		AssertJUnit.assertEquals(s.getServiceLogKey(),new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getRequestString()}.
	 */
	@Test
	public final void testGetRequestString() {
		s.setRequestString("abcd");
		AssertJUnit.assertEquals(s.getRequestString(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getResultString()}.
	 */
	@Test
	public final void testGetResultString() {
		s.setResultString("abcd");
		AssertJUnit.assertEquals(s.getResultString(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getRequestDuration()}.
	 */
	@Test
	public final void testGetRequestDuration() {
		s.setRequestDuration(1L);
		AssertJUnit.assertEquals(s.getRequestDuration(),new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServiceLog#getIpAddress()}.
	 */
	@Test
	public final void testGetIpAddress() {
		s.setIpAddress("abcd");
		AssertJUnit.assertEquals(s.getIpAddress(),"abcd");
	}

}
