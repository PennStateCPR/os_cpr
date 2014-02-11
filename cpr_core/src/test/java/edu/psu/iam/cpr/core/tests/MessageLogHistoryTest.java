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

import edu.psu.iam.cpr.core.database.beans.MessageLogHistory;


public class MessageLogHistoryTest {

	MessageLogHistory m = new MessageLogHistory();
	Date d = new Date();
	String s = "abcd";
	Long l = 0L;
	
	@Test
	public final void testMessageLogHistory() {
		AssertJUnit.assertNotNull(m);
	}
	
	@Test
	public final void testGetMessageId() {
		m.setMessageId(s);
		AssertJUnit.assertEquals(m.getMessageId(), s);
	}
 
	@Test
	public final void testGetCreatedOn() {
		m.setCreatedOn(d);
		AssertJUnit.assertEquals(m.getCreatedOn(), d);
	}

	@Test
    public final void testGetMessageSentTimestamp() {
		m.setMessageSentTimestamp(d);
		AssertJUnit.assertEquals(m.getMessageSentTimestamp(), d);
    }
	
	@Test
    public final void testGetMessageReceived() {
		m.setMessageReceived(s);
		AssertJUnit.assertEquals(m.getMessageReceived(), s);
    }

	@Test
    public final void testGetMessageReceivedTimestamp() {
		m.setMessageReceivedTimestamp(d);
		AssertJUnit.assertEquals(m.getMessageReceivedTimestamp(), d);
    }

	@Test
    public final void testGetErrorCode() {
		m.setErrorCode(s);
		AssertJUnit.assertEquals(m.getErrorCode(), s);
    }

	@Test
    public final void testGetTryNumber() {
		m.setTryNumber(l);
		AssertJUnit.assertEquals(m.getTryNumber(), l);
    }

	@Test
    public final void testGetErrorMessage() {
		m.setErrorMessage(s);
		AssertJUnit.assertEquals(m.getErrorMessage(), s);
    }
	
//	@Test
//    public final void testGetMessageLogHistoryKey() {
//		m.setMessageLogHistoryKey(l);
//		assertEquals(m.getMessageLogHistoryKey(), l);
//    }
//  
//	@Test
//    public final void testGetMessageLogKey() {
//		m.setMessageLogKey(l);
//		assertEquals(m.getMessageLogKey(), l);
//    }

}
