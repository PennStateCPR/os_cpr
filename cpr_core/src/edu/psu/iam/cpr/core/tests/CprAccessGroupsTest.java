/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Date;

import edu.psu.iam.cpr.core.database.beans.CprAccessGroups;

/**
 * @author llg5
 *
 */
public class CprAccessGroupsTest {
	CprAccessGroups cprAccess = new CprAccessGroups();
	Date d = new Date();
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#CprAccessGroups()}.
	 */
	@Test
	public final void _01testCprAccessGroups() {
		AssertJUnit.assertNotNull(cprAccess);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getCreatedBy()}.
	 */
	@Test
	public final void _02testGetCreatedBy() {
		cprAccess.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(cprAccess.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void _03testSetCreatedBy() {
		cprAccess.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(cprAccess.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getEndDate()}.
	 */
	@Test
	public final void _04testGetEndDate() {
		cprAccess.setEndDate(d);
		AssertJUnit.assertEquals(cprAccess.getEndDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setEndDate(java.util.Date)}.
	 */
	@Test
	public final void _05testSetEndDate() {
		cprAccess.setEndDate(d);
		AssertJUnit.assertEquals(cprAccess.getEndDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getAccessGroup()}.
	 */
	@Test
	public final void _06testGetAccessGroup() {
		cprAccess.setAccessGroup("A Group");
		AssertJUnit.assertEquals(cprAccess.getAccessGroup(), "A Group");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setAccessGroup(java.lang.String)}.
	 */
	@Test
	public final void _07testSetAccessGroup() {
		cprAccess.setAccessGroup("A Group");
		AssertJUnit.assertEquals(cprAccess.getAccessGroup(), "A Group");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getSuspendFlag()}.
	 */
	@Test
	public final void _08testGetSuspendFlag() {
		cprAccess.setSuspendFlag("Y");
		AssertJUnit.assertEquals(cprAccess.getSuspendFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setSuspendFlag(java.lang.String)}.
	 */
	@Test
	public final void _09testSetSuspendFlag() {
		cprAccess.setSuspendFlag("Y");
		AssertJUnit.assertEquals(cprAccess.getSuspendFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getCprAccessGroupsKey()}.
	 */
	@Test
	public final void _10testGetCprAccessGroupsKey() {
		cprAccess.setCprAccessGroupsKey(1L );
		AssertJUnit.assertEquals(cprAccess.getCprAccessGroupsKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setCprAccessGroupsKey(java.lang.Long)}.
	 */
	@Test
	public final void _11testSetCprAccessGroupsKey() {
		cprAccess.setCprAccessGroupsKey(1L );
		AssertJUnit.assertEquals(cprAccess.getCprAccessGroupsKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getCreatedOn()}.
	 */
	@Test
	public final void _12testGetCreatedOn() {
		cprAccess.setCreatedOn(d);
		AssertJUnit.assertEquals(cprAccess.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void _13testSetCreatedOn() {
		cprAccess.setCreatedOn(d);
		AssertJUnit.assertEquals(cprAccess.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getLastUpdateBy()}.
	 */
	@Test
	public final void _14testGetLastUpdateBy() {
		cprAccess.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(cprAccess.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void _15testSetLastUpdateBy() {
		cprAccess.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(cprAccess.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getRaServerPrincipalKey()}.
	 */
	@Test
	public final void _16testGetRaServerPrincipalKey() {
		cprAccess.setRaServerPrincipalKey(1L );
		AssertJUnit.assertEquals(cprAccess.getRaServerPrincipalKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setRaServerPrincipalKey(java.lang.Long)}.
	 */
	@Test
	public final void _17testSetRaServerPrincipalKey() {
		cprAccess.setRaServerPrincipalKey(1L );
		AssertJUnit.assertEquals(cprAccess.getRaServerPrincipalKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getStartDate()}.
	 */
	@Test
	public final void _18testGetStartDate() {
		cprAccess.setStartDate(d);
		AssertJUnit.assertEquals(cprAccess.getStartDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setStartDate(java.util.Date)}.
	 */
	@Test
	public final void _19testSetStartDate() {
		cprAccess.setStartDate(d);
		AssertJUnit.assertEquals(cprAccess.getStartDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#getLastUpdateOn()}.
	 */
	@Test
	public final void _20testGetLastUpdateOn() {
		cprAccess.setLastUpdateOn(d);
		AssertJUnit.assertEquals(cprAccess.getLastUpdateOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.CprAccessGroups#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void _21testSetLastUpdateOn() {
		cprAccess.setLastUpdateOn(d);
		AssertJUnit.assertEquals(cprAccess.getLastUpdateOn(), d);
	}

}
