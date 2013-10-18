package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.database.beans.VSpNotification;

public class VSpNotificationTest {

	VSpNotification v = new VSpNotification();
	String s = "abcd";
	
	@Test
	public final void _01testVSpNotification() {
		AssertJUnit.assertNotNull(v);
	}

	@Test
	public final void _02testConsumer() {
		v.setConsumer(s);
		AssertJUnit.assertEquals(v.getConsumer(),s);
	}

	@Test
	public final void _03testGetMessageConsumerKey() {
		v.setMessageConsumerKey(1L);
		AssertJUnit.assertEquals(v.getMessageConsumerKey(),new Long(1));
	}

	@Test
	public final void _04testGetWebServiceKey() {
		v.setWebServiceKey(1L);
		AssertJUnit.assertEquals(v.getWebServiceKey(), new Long(1));
	}

	@Test
	public final void _05testGetWebService() {
		v.setWebService(s);
		AssertJUnit.assertEquals(v.getWebService(),s);
	}

	@Test
	public final void _06testConsumerDestination() {
		v.setConsumerDestination(s);
		AssertJUnit.assertEquals(v.getConsumerDestination(),s);
	}
	@Test
	public final void _07testDestinationType() {
		v.setDestinationType(s);
		AssertJUnit.assertEquals(v.getDestinationType(),s);
	}
	@Test
	public final void _08testServiceName() {
		v.setServiceName(s);
		AssertJUnit.assertEquals(v.getServiceName(),s);
	}
	@Test
	public final void _09testServiceKey() {
		v.setServiceKey(1L);
		AssertJUnit.assertEquals(v.getServiceKey(),new Long(1));
	}
}
