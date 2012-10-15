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


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.service.returns.MessageLogReturn;

/**
 * The class <code>MessageLogReturnTest</code> contains tests for the class <code>{@link MessageLogReturn}</code>.
 *
 * @generatedBy CodePro at 1/16/12 11:32 AM
 * @author slk24
 * @version $Revision: 1.0 $
 */
public class MessageLogReturnTest {
	/**
	 * Run the MessageLogReturn() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testMessageLogReturn_1()
		throws Exception {

		MessageLogReturn result = new MessageLogReturn();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals(null, result.getMessageLogKey());
		AssertJUnit.assertEquals(null, result.getSuccessFlag());
		AssertJUnit.assertEquals(null, result.getServiceProvisionerKey());
		AssertJUnit.assertEquals(null, result.getRequestUserid());
		AssertJUnit.assertEquals(null, result.getWebServiceKey());
		AssertJUnit.assertEquals(null, result.getNumberOfTries());
		AssertJUnit.assertEquals(null, result.getMessageSent());
	}

	/**
	 * Run the MessageLogReturn(Long,Long,Long,String,Long,String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testMessageLogReturn_2()
		throws Exception {
		Long messageLogKey = new Long(1L);
		Long webServiceKey = new Long(1L);
		Long serviceProvisionerKey = new Long(1L);
		String messageSent = "";
		Long numberOfTries = new Long(1L);
		String successFlag = "";
		String requestUserid = "";

		MessageLogReturn result = new MessageLogReturn(messageLogKey, webServiceKey, serviceProvisionerKey, messageSent, numberOfTries, successFlag, requestUserid);

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals(new Long(1L), result.getMessageLogKey());
		AssertJUnit.assertEquals("", result.getSuccessFlag());
		AssertJUnit.assertEquals(new Long(1L), result.getServiceProvisionerKey());
		AssertJUnit.assertEquals("", result.getRequestUserid());
		AssertJUnit.assertEquals(new Long(1L), result.getWebServiceKey());
		AssertJUnit.assertEquals(new Long(1L), result.getNumberOfTries());
		AssertJUnit.assertEquals("", result.getMessageSent());
	}

	/**
	 * Run the Long getMessageLogKey() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testGetMessageLogKey_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));

		Long result = fixture.getMessageLogKey();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals("1", result.toString());
		AssertJUnit.assertEquals((byte) 1, result.byteValue());
		AssertJUnit.assertEquals((short) 1, result.shortValue());
		AssertJUnit.assertEquals(1, result.intValue());
		AssertJUnit.assertEquals(1L, result.longValue());
		AssertJUnit.assertEquals(1.0f, result.floatValue(), 1.0f);
		AssertJUnit.assertEquals(1.0, result.doubleValue(), 1.0);
	}

	/**
	 * Run the String getMessageSent() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testGetMessageSent_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));

		String result = fixture.getMessageSent();

		// add additional test code here
		AssertJUnit.assertEquals("", result);
	}

	/**
	 * Run the Long getNumberOfTries() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testGetNumberOfTries_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));

		Long result = fixture.getNumberOfTries();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals("1", result.toString());
		AssertJUnit.assertEquals((byte) 1, result.byteValue());
		AssertJUnit.assertEquals((short) 1, result.shortValue());
		AssertJUnit.assertEquals(1, result.intValue());
		AssertJUnit.assertEquals(1L, result.longValue());
		AssertJUnit.assertEquals(1.0f, result.floatValue(), 1.0f);
		AssertJUnit.assertEquals(1.0, result.doubleValue(), 1.0);
	}

	/**
	 * Run the String getRequestUserid() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testGetRequestUserid_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));

		String result = fixture.getRequestUserid();

		// add additional test code here
		AssertJUnit.assertEquals("", result);
	}

	/**
	 * Run the Long getServiceProvisionerKey() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testGetServiceProvisionerKey_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));

		Long result = fixture.getServiceProvisionerKey();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals("1", result.toString());
		AssertJUnit.assertEquals((byte) 1, result.byteValue());
		AssertJUnit.assertEquals((short) 1, result.shortValue());
		AssertJUnit.assertEquals(1, result.intValue());
		AssertJUnit.assertEquals(1L, result.longValue());
		AssertJUnit.assertEquals(1.0f, result.floatValue(), 1.0f);
		AssertJUnit.assertEquals(1.0, result.doubleValue(), 1.0);
	}

	/**
	 * Run the String getSuccessFlag() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testGetSuccessFlag_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));

		String result = fixture.getSuccessFlag();

		// add additional test code here
		AssertJUnit.assertEquals("", result);
	}

	/**
	 * Run the Long getWebServiceKey() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testGetWebServiceKey_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));

		Long result = fixture.getWebServiceKey();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals("1", result.toString());
		AssertJUnit.assertEquals((byte) 1, result.byteValue());
		AssertJUnit.assertEquals((short) 1, result.shortValue());
		AssertJUnit.assertEquals(1, result.intValue());
		AssertJUnit.assertEquals(1L, result.longValue());
		AssertJUnit.assertEquals(1.0f, result.floatValue(), 1.0f);
		AssertJUnit.assertEquals(1.0, result.doubleValue(), 1.0);
	}

	/**
	 * Run the void setMessageLogKey(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testSetMessageLogKey_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));
		Long messageLogKey = new Long(1L);

		fixture.setMessageLogKey(messageLogKey);

		// add additional test code here
	}

	/**
	 * Run the void setMessageSent(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testSetMessageSent_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));
		String messageSent = "";

		fixture.setMessageSent(messageSent);

		// add additional test code here
	}

	/**
	 * Run the void setNumberOfTries(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testSetNumberOfTries_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));
		Long numberOfTries = new Long(1L);

		fixture.setNumberOfTries(numberOfTries);

		// add additional test code here
	}

	/**
	 * Run the void setRequestUserid(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testSetRequestUserid_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));
		String requestUserid = "";

		fixture.setRequestUserid(requestUserid);

		// add additional test code here
	}

	/**
	 * Run the void setServiceProvisionerKey(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testSetServiceProvisionerKey_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));
		Long serviceProvisionerKey = new Long(1L);

		fixture.setServiceProvisionerKey(serviceProvisionerKey);

		// add additional test code here
	}

	/**
	 * Run the void setSuccessFlag(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testSetSuccessFlag_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));
		String successFlag = "";

		fixture.setSuccessFlag(successFlag);

		// add additional test code here
	}

	/**
	 * Run the void setWebServiceKey(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@Test
	public void testSetWebServiceKey_1()
		throws Exception {
		MessageLogReturn fixture = new MessageLogReturn(new Long(1L), new Long(1L), new Long(1L), "", new Long(1L), "", "");
		fixture.setMessageLogKey(new Long(1L));
		Long webServiceKey = new Long(1L);

		fixture.setWebServiceKey(webServiceKey);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@BeforeMethod
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 1/16/12 11:32 AM
	 */
	@AfterMethod
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}
