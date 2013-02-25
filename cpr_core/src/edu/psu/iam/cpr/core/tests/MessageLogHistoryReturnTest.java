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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import edu.psu.iam.cpr.core.service.returns.MessageLogHistoryReturn;

/**
 * The class <code>MessageHistoryLogReturnTest</code> contains tests for the class <code>{@link MessageLogHistoryReturn}</code>.
 *
 * @generatedBy CodePro at 1/16/12 11:33 AM
 * @author cpruser
 * @version $Revision: 1.0 $
 */
public class MessageLogHistoryReturnTest {
	/**
	 * Run the MessageHistoryLogReturn() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testMessageHistoryLogReturn_1()
		throws Exception {

		MessageLogHistoryReturn result = new MessageLogHistoryReturn();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals(null, result.getErrorCode());
		AssertJUnit.assertEquals(null, result.getErrorMessage());
		AssertJUnit.assertEquals(null, result.getMessageId());
		AssertJUnit.assertEquals(null, result.getMessageLogKey());
		AssertJUnit.assertEquals(null, result.getTryNumber());
		AssertJUnit.assertEquals(null, result.getMessageSentTimestamp());
		AssertJUnit.assertEquals(null, result.getMessageReceived());
		AssertJUnit.assertEquals(null, result.getMessageReceivedTimestamp());
	}

	/**
	 * Run the MessageHistoryLogReturn(String,Long,Timestamp,Timestamp,String,String,String,Long) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testMessageHistoryLogReturn_2()
		throws Exception {
		String messageId = "";
		Long messageLogKey = new Long(1L);
		Timestamp messageSentTimestamp = new Timestamp(1L);
		Timestamp messageReceivedTimestamp = new Timestamp(1L);
		String messageReceived = "";
		String errorCode = "";
		String errorMessage = "";
		Long tryNumber = new Long(1L);

		MessageLogHistoryReturn result = new MessageLogHistoryReturn(messageId, messageLogKey, messageSentTimestamp, messageReceivedTimestamp, messageReceived, errorCode, errorMessage, tryNumber);

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals("", result.getErrorCode());
		AssertJUnit.assertEquals("", result.getErrorMessage());
		AssertJUnit.assertEquals("", result.getMessageId());
		AssertJUnit.assertEquals(new Long(1L), result.getMessageLogKey());
		AssertJUnit.assertEquals(new Long(1L), result.getTryNumber());
		AssertJUnit.assertEquals("", result.getMessageReceived());
	}

	/**
	 * Run the String getErrorCode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetErrorCode_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

		String result = fixture.getErrorCode();

		// add additional test code here
		AssertJUnit.assertEquals("", result);
	}

	/**
	 * Run the String getErrorMessage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetErrorMessage_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

		String result = fixture.getErrorMessage();

		// add additional test code here
		AssertJUnit.assertEquals("", result);
	}

	/**
	 * Run the String getMessageId() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetMessageId_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

		String result = fixture.getMessageId();

		// add additional test code here
		AssertJUnit.assertEquals("", result);
	}

	/**
	 * Run the Long getMessageLogKey() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetMessageLogKey_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

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
	 * Run the String getMessageReceived() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetMessageReceived_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

		String result = fixture.getMessageReceived();

		// add additional test code here
		AssertJUnit.assertEquals("", result);
	}

	/**
	 * Run the Timestamp getMessageReceivedTimestamp() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetMessageReceivedTimestamp_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

		Timestamp result = fixture.getMessageReceivedTimestamp();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals(DateFormat.getInstance().format(new Date(1L)), DateFormat.getInstance().format(result));
		AssertJUnit.assertEquals(1L, result.getTime());
	}

	/**
	 * Run the Timestamp getMessageSentTimestamp() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetMessageSentTimestamp_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

		Timestamp result = fixture.getMessageSentTimestamp();

		// add additional test code here
		AssertJUnit.assertNotNull(result);
		AssertJUnit.assertEquals(DateFormat.getInstance().format(new Date(1L)), DateFormat.getInstance().format(result));
		AssertJUnit.assertEquals(1L, result.getTime());
	}

	/**
	 * Run the Long getTryNumber() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testGetTryNumber_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));

		Long result = fixture.getTryNumber();

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
	 * Run the void setErrorCode(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetErrorCode_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		String errorCode = "";

		fixture.setErrorCode(errorCode);

		// add additional test code here
	}

	/**
	 * Run the void setErrorMessage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetErrorMessage_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		String errorMessage = "";

		fixture.setErrorMessage(errorMessage);

		// add additional test code here
	}

	/**
	 * Run the void setMessageId(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetMessageId_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		String messageId = "";

		fixture.setMessageId(messageId);

		// add additional test code here
	}

	/**
	 * Run the void setMessageLogKey(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetMessageLogKey_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		Long messageLogKey = new Long(1L);

		fixture.setMessageLogKey(messageLogKey);

		// add additional test code here
	}

	/**
	 * Run the void setMessageReceived(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetMessageReceived_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		String messageReceived = "";

		fixture.setMessageReceived(messageReceived);

		// add additional test code here
	}

	/**
	 * Run the void setMessageReceivedTimestamp(Timestamp) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetMessageReceivedTimestamp_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		Timestamp messageReceivedTimestamp = new Timestamp(1L);

		fixture.setMessageReceivedTimestamp(messageReceivedTimestamp);

		// add additional test code here
	}

	/**
	 * Run the void setMessageSentTimestamp(Timestamp) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetMessageSentTimestamp_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		Timestamp messageSentTimestamp = new Timestamp(1L);

		fixture.setMessageSentTimestamp(messageSentTimestamp);

		// add additional test code here
	}

	/**
	 * Run the void setTryNumber(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@Test
	public void testSetTryNumber_1()
		throws Exception {
		MessageLogHistoryReturn fixture = new MessageLogHistoryReturn("", new Long(1L), new Timestamp(1L), new Timestamp(1L), "", "", "", new Long(1L));
		Long tryNumber = new Long(1L);

		fixture.setTryNumber(tryNumber);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 1/16/12 11:33 AM
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
	 * @generatedBy CodePro at 1/16/12 11:33 AM
	 */
	@AfterMethod
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}
