package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.ui.FieldUI;

/**
 * @author slk24
 *
 */
public class FieldUITest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.FieldUI#FieldUI()}.
	 */
	@Test
	public final void testFieldUI() {
		FieldUI test = new FieldUI();
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.FieldUI#FieldUI(String fieldName, boolean requiredFlag)}.
	 */
	@Test
	public final void testFieldUIArgs() {
		FieldUI test = new FieldUI("TestField", false);
		AssertJUnit.assertNotNull(test);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.FieldUI#getFieldName()}.
	 */
	@Test
	public final void testGetFieldName() {
		FieldUI test = new FieldUI();
		test.setFieldName("TestField");
		AssertJUnit.assertEquals("TestField", test.getFieldName());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.FieldUI#getRequiredFlag())}.
	 */
	@Test
	public final void testGetRequiredFlag() {
		FieldUI test = new FieldUI();
		test.setRequiredFlag(true);
		AssertJUnit.assertEquals(true, test.getRequiredFlag());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.ui.FieldUI#toString())}.
	 */
	@Test
	public final void testToString() {
		FieldUI test = new FieldUI("TestField", false);
		AssertJUnit.assertTrue(test.toString() instanceof java.lang.String);
	}
	
}
