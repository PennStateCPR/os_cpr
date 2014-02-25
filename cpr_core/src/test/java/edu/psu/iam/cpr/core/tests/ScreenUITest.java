package edu.psu.iam.cpr.core.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.ui.FieldUI;
import edu.psu.iam.cpr.core.ui.ScreenUI;

/**
 * @author cpruser
 *
 */
public class ScreenUITest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#ScreenUI()}.
	 */
	@Test
	public final void testScreenUI() {
		final ScreenUI test = new ScreenUI();
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#ScreenUI(String screenName, List<FieldUI> fieldList)}.
	 */
	@Test
	public final void testScreenUIArgs() {
		final ScreenUI test = new ScreenUI("TestScreen", new ArrayList<FieldUI>());
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#getScreenName()}.
	 */
	@Test
	public final void testGetScreenName() {
		final ScreenUI test = new ScreenUI();
		test.setScreenName("TestScreen");
		AssertJUnit.assertEquals("TestScreen", test.getScreenName());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#getFieldList())}.
	 */
	@Test
	public final void testGetFieldList() {
		final ScreenUI test = new ScreenUI();
		test.setFieldList(new ArrayList<FieldUI>());
		AssertJUnit.assertNotNull(test.getFieldList());
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.ScreenUI#toString())}.
	 */
	@Test
	public final void testToString() {
        final FieldUI foo = new FieldUI("foo", true);
        final FieldUI bar = new FieldUI("bar", false);
        final FieldUI baz = new FieldUI("baz", true);

        final List<FieldUI> fieldUIList = new ArrayList<FieldUI>();
        AssertJUnit.assertTrue(fieldUIList.add(foo));
        AssertJUnit.assertTrue(fieldUIList.add(bar));
        AssertJUnit.assertTrue(fieldUIList.add(baz));

		final ScreenUI test = new ScreenUI("TestScreen", fieldUIList);
		AssertJUnit.assertEquals("TestScreen[foo true,bar false,baz true]", test.toString());
	}
}
