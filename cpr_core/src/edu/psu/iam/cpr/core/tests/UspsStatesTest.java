/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;


import edu.psu.iam.cpr.core.database.beans.UspsStates;

/**
 * @author llg5
 *
 */
public class UspsStatesTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.UspsStates#UspsStates()}.
	 */
	@Test
	public final void _01testUspsStates() {
		UspsStates theStates = new UspsStates();
		AssertJUnit.assertNotNull(theStates);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.UspsStates#getStateName()}.
	 */
	@Test
	public final void _02testGetStateName() {
		UspsStates theStates = new UspsStates();
		theStates.setStateName("Alabama");
		AssertJUnit.assertTrue(theStates.getStateName().equals("Alabama"));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.UspsStates#setStateName(java.lang.String)}.
	 */
	@Test
	public final void _03testSetStateName() {
		UspsStates theStates = new UspsStates();
		theStates.setStateName("Alabama");
		AssertJUnit.assertTrue(theStates.getStateName().equals("Alabama"));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.UspsStates#getStateCode()}.
	 */
	@Test
	public final void _04testGetStateCode() {
		UspsStates theStates = new UspsStates();
		theStates.setStateCode("AL");
		AssertJUnit.assertTrue(theStates.getStateCode().equals("AL"));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.UspsStates#setStateCode(java.lang.String)}.
	 */
	@Test
	public final void _05testSetStateCode() {
		UspsStates theStates = new UspsStates();
		theStates.setStateCode("AL");
		AssertJUnit.assertTrue(theStates.getStateCode().equals("AL"));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.UspsStates#getUspsStateTypeKey()}.
	 */
	@Test
	public final void _06testGetUspsStateTypeKey() {
		UspsStates theStates = new UspsStates();
		theStates.setUspsStateTypeKey(new Long(1));
		AssertJUnit.assertTrue(theStates.getUspsStateTypeKey().equals(new Long(1)));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.UspsStates#setUspsStateTypeKey(java.lang.Long)}.
	 */
	@Test
	public final void _07testSetUspsStateTypeKey() {
		UspsStates theStates = new UspsStates();
		theStates.setUspsStateTypeKey(new Long(1));
		AssertJUnit.assertTrue(theStates.getUspsStateTypeKey().equals(new Long(1)));
	}

}
