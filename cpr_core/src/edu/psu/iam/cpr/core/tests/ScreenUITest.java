package edu.psu.iam.cpr.core.tests;

import java.util.ArrayList;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.ui.FieldUI;
import edu.psu.iam.cpr.core.ui.ScreenUI;

/**
 * @author slk24
 *
 */
public class ScreenUITest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#ScreenUI()}.
	 */
	@Test
	public final void testScreenUI() {
		ScreenUI test = new ScreenUI();
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#ScreenUI(String screenName, ArrayList<FieldUI> fieldList)}.
	 */
	@Test
	public final void testScreenUIArgs() {
		ScreenUI test = new ScreenUI("TestScreen", new ArrayList<FieldUI>());
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#getScreenName()}.
	 */
	@Test
	public final void testGetScreenName() {
		ScreenUI test = new ScreenUI();
		test.setScreenName("TestScreen");
		AssertJUnit.assertEquals("TestScreen", test.getScreenName());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#getFieldList())}.
	 */
	@Test
	public final void testGetFieldList() {
		ScreenUI test = new ScreenUI();
		test.setFieldList(new ArrayList<FieldUI>());
		AssertJUnit.assertNotNull(test.getFieldList());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#toString())}.
	 */
	@Test
	public final void testToString() {
		ScreenUI test = new ScreenUI("TestScreen", new ArrayList<FieldUI>());
		AssertJUnit.assertTrue(test.toString() instanceof java.lang.String);
	}
}