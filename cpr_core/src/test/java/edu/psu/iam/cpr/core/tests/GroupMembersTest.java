/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Date;


import edu.psu.iam.cpr.core.database.beans.GroupMembers;

/**
 * @author llg5
 *
 */
public class GroupMembersTest {
	GroupMembers gm = new GroupMembers();
	Date d = new Date();
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#GroupMembers()}.
	 */
	@Test
	public final void _01testGroupMembers() {
		GroupMembers theGroup = new GroupMembers();
		AssertJUnit.assertNotNull(theGroup);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getStartDate()}.
	 */
	@Test
	public final void _02testGetStartDate() {
		gm.setStartDate(d);
		AssertJUnit.assertEquals(gm.getStartDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setStartDate(java.util.Date)}.
	 */
	@Test
	public final void _03testSetStartDate() {
		gm.setStartDate(d);
		AssertJUnit.assertEquals(gm.getStartDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getGroupMemberKey()}.
	 */
	@Test
	public final void _04testGetGroupMemberKey() {
		gm.setGroupMemberKey(new Long(2));
		AssertJUnit.assertEquals(gm.getGroupMemberKey(),new Long(2));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setGroupMemberKey(java.lang.Long)}.
	 */
	@Test
	public final void _05testSetGroupMemberKey() {
		gm.setGroupMemberKey(new Long(2));
		AssertJUnit.assertEquals(gm.getGroupMemberKey(),new Long(2));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getCreatedBy()}.
	 */
	@Test
	public final void _06testGetCreatedBy() {
		gm.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(gm.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void _07testSetCreatedBy() {
		gm.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(gm.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getLastUpdateOn()}.
	 */
	@Test
	public final void _08testGetLastUpdateOn() {
		gm.setLastUpdateOn(d);
		AssertJUnit.assertEquals(gm.getLastUpdateOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void _09testSetLastUpdateOn() {
		gm.setLastUpdateOn(d);
		AssertJUnit.assertEquals(gm.getLastUpdateOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getCreatedOn()}.
	 */
	@Test
	public final void _10testGetCreatedOn() {
		gm.setCreatedOn(d);
		AssertJUnit.assertEquals(gm.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void _11testSetCreatedOn() {
		gm.setCreatedOn(d);
		AssertJUnit.assertEquals(gm.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getLastUpdateBy()}.
	 */
	@Test
	public final void _12testGetLastUpdateBy() {
		gm.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(gm.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void _13testSetLastUpdateBy() {
		gm.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(gm.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getPersonId()}.
	 */
	@Test
	public final void _14testGetPersonId() {
		gm.setPersonId(new Long(2));
		AssertJUnit.assertEquals(gm.getPersonId(),new Long(2));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setPersonId(java.lang.Long)}.
	 */
	@Test
	public final void _15testSetPersonId() {
		gm.setPersonId(new Long(2));
		AssertJUnit.assertEquals(gm.getPersonId(),new Long(2));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getEndDate()}.
	 */
	@Test
	public final void _16testGetEndDate() {
		gm.setEndDate(d);
		AssertJUnit.assertEquals(gm.getEndDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setEndDate(java.util.Date)}.
	 */
	@Test
	public final void _17testSetEndDate() {
		gm.setEndDate(d);
		AssertJUnit.assertEquals(gm.getEndDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getCprAccessGroupsKey()}.
	 */
	@Test
	public final void _18testGetCprAccessGroupsKey() {
		gm.setCprAccessGroupsKey(new Long(2));
		AssertJUnit.assertEquals(gm.getCprAccessGroupsKey(),new Long(2));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setCprAccessGroupsKey(java.lang.Long)}.
	 */
	@Test
	public final void _19testSetCprAccessGroupsKey() {
		gm.setCprAccessGroupsKey(new Long(2));
		AssertJUnit.assertEquals(gm.getCprAccessGroupsKey(),new Long(2));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getSuspendFlag()}.
	 */
	@Test
	public final void _20testGetSuspendFlag() {
		gm.setSuspendFlag("Y");
		AssertJUnit.assertEquals(gm.getSuspendFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setSuspendFlag(java.lang.String)}.
	 */
	@Test
	public final void _21testSetSuspendFlag() {
		gm.setSuspendFlag("Y");
		AssertJUnit.assertEquals(gm.getSuspendFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#getUserid()}.
	 */
	@Test
	public final void _22testGetUserid() {
		gm.setUserid("SYSTEM");
		AssertJUnit.assertEquals(gm.getUserid(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupMembers#setUserid(java.lang.String)}.
	 */
	@Test
	public final void _23testSetUserid() {
		gm.setUserid("SYSTEM");
		AssertJUnit.assertEquals(gm.getUserid(), "SYSTEM");
	}

}
