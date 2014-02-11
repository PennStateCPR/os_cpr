package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import edu.psu.iam.cpr.core.database.beans.VConsumerNotificationDest;

public class VConsumerNotificationDestTest {

	VConsumerNotificationDest v = new VConsumerNotificationDest();
	String s = "abcd";
	Long l = 1L;

	@Test
	public final void _01testVConsumerNotificationDest() {
		AssertJUnit.assertNotNull(v);
	}

	@Test
	public final void _02testGetChangeNotificationTypesKey() {
		v.setChangeNotificationTypesKey(l);
		AssertJUnit.assertEquals(v.getChangeNotificationTypesKey(),l);
	}

	@Test
	public final void _03testGetConsumerDestination() {
		v.setConsumerDestination(s);
		AssertJUnit.assertEquals(v.getConsumerDestination(),s);
	}

	@Test
	public final void _04testGetChangeNotificationsKey() {
		v.setChangeNotificationsKey(l);
		AssertJUnit.assertEquals(v.getChangeNotificationsKey(),l);
	}
	
	@Test
	public final void _05testGetNotificationType() {
		v.setNotificationType(s);
		AssertJUnit.assertEquals(v.getNotificationType(),s);
	}

	@Test
	public final void _06testGetMessageConsumerKey() {
		v.setMessageConsumerKey(l);
		AssertJUnit.assertEquals(v.getMessageConsumerKey(),l);
	}

}
