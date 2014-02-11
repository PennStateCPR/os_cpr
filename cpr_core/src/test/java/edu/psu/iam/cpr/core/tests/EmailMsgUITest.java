package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;


import edu.psu.iam.cpr.core.ui.EmailMsgUI;

/**
 * @author cpruser
 *
 */
public class EmailMsgUITest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.EmailMsgUI#EmailMsgUI()}.
	 */
	@Test
	public final void testEmailMsgUI() {
		EmailMsgUI test = new EmailMsgUI();
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.EmailMsgUI#EmailMsgUI(String screenName, ArrayList<FieldUI> fieldList)}.
	 */
	@Test
	public final void testEmailMsgUIArgs() {
		EmailMsgUI test = new EmailMsgUI("TestEmailMsg", "Test Body", "<b>HTML</b> Test Body", "Test Notification");
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.EmailMsgUI#getEmailMsgSubject()}.
	 */
	@Test
	public final void testGetEmailSubject() {
		EmailMsgUI test = new EmailMsgUI();
		test.setEmailSubject("TestEmailMsg Subject");
		AssertJUnit.assertEquals("TestEmailMsg Subject", test.getEmailSubject());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.EmailMsgUI#getEmailMsgSubject()}.
	 */
	@Test
	public final void testGetEmailText() {
		EmailMsgUI test = new EmailMsgUI();
		test.setEmailText("TestEmailMsg text body");
		AssertJUnit.assertEquals("TestEmailMsg text body", test.getEmailText());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.EmailMsgUI#getEmailMsgSubject()}.
	 */
	@Test
	public final void testGetEmailHtml() {
		EmailMsgUI test = new EmailMsgUI();
		test.setEmailHtml("TestEmailMsg <b>HTML</b> body");
		AssertJUnit.assertEquals("TestEmailMsg <b>HTML</b> body", test.getEmailHtml());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.EmailMsgUI#getEmailMsgSubject()}.
	 */
	@Test
	public final void testGetNotificationProcess() {
		EmailMsgUI test = new EmailMsgUI();
		test.setNotificationProcess("Test Notification");
		AssertJUnit.assertEquals("Test Notification", test.getNotificationProcess());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.EmailMsgUI#toString())}.
	 */
	@Test
	public final void testToString() {
		EmailMsgUI test = new EmailMsgUI("TestEmailMsg", "Test Body", "<b>HTML</b> Test Body", "Test Notification");
		AssertJUnit.assertTrue(test.toString() instanceof java.lang.String);
	}


}
