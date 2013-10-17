/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;


import java.util.Date;


import edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess;

/**
 * @author llg5
 *
 */
public class GroupDataTypeAccessTest {
	GroupDataTypeAccess gdta = new GroupDataTypeAccess();
	Date d = new Date();
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#GroupDataTypeAccess()}.
	 */
	@Test
	public final void _01testGroupDataTypeAccess() {
		GroupDataTypeAccess theAccess = new GroupDataTypeAccess();
		AssertJUnit.assertNotNull(theAccess);
		
	}
		

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getCreatedBy()}.
	 */
	@Test
	public final void _02testGetCreatedBy() {
		gdta.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(gdta.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void _03testSetCreatedBy() {
		gdta.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(gdta.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getLastUpdateOn()}.
	 */
	@Test
	public final void _04testGetLastUpdateOn() {
		gdta.setLastUpdateOn(d);
		AssertJUnit.assertEquals(gdta.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void _05testSetLastUpdateOn() {
		gdta.setLastUpdateOn(d);
		AssertJUnit.assertEquals(gdta.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getReadFlag()}.
	 */
	@Test
	public final void _06testGetReadFlag() {
		gdta.setReadFlag("Y");
		AssertJUnit.assertEquals(gdta.getReadFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setReadFlag(java.lang.String)}.
	 */
	@Test
	public final void _07testSetReadFlag() {
		gdta.setReadFlag("Y");
		AssertJUnit.assertEquals(gdta.getReadFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getCreatedOn()}.
	 */
	@Test
	public final void _08testGetCreatedOn() {
		gdta.setCreatedOn(d);
		AssertJUnit.assertEquals(gdta.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void _09testSetCreatedOn() {
		gdta.setCreatedOn(d);
		AssertJUnit.assertEquals(gdta.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getLastUpdateBy()}.
	 */
	@Test
	public final void _10testGetLastUpdateBy() {
		gdta.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(gdta.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void _11testSetLastUpdateBy() {
		gdta.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(gdta.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getDataTypeKey()}.
	 */
	@Test
	public final void _12testGetDataTypeKey() {
		gdta.setDataTypeKey(new Long(20));
		AssertJUnit.assertEquals(gdta.getDataTypeKey(),new Long(20) );
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setDataTypeKey(java.lang.Long)}.
	 */
	@Test
	public final void _13testSetDataTypeKey() {
		gdta.setDataTypeKey(new Long(20));
		AssertJUnit.assertEquals(gdta.getDataTypeKey(),new Long(20) );
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getArchiveFlag()}.
	 */
	@Test
	public final void _14testGetArchiveFlag() {
		gdta.setArchiveFlag("Y");
		AssertJUnit.assertEquals(gdta.getArchiveFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setArchiveFlag(java.lang.String)}.
	 */
	@Test
	public final void _15testSetArchiveFlag() {
		gdta.setArchiveFlag("Y");
		AssertJUnit.assertEquals(gdta.getArchiveFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getCprAccessGroupsKey()}.
	 */
	@Test
	public final void _16testGetCprAccessGroupsKey() {
		gdta.setCprAccessGroupsKey(new Long(20));
		AssertJUnit.assertEquals(gdta.getCprAccessGroupsKey(),new Long(20) );
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setCprAccessGroupsKey(java.lang.Long)}.
	 */
	@Test
	public final void _17testSetCprAccessGroupsKey() {
		gdta.setCprAccessGroupsKey(new Long(20));
		AssertJUnit.assertEquals(gdta.getCprAccessGroupsKey(),new Long(20) );
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getGroupDataTypeAccessKey()}.
	 */
	@Test
	public final void _18testGetGroupDataTypeAccessKey() {
		gdta.setGroupDataTypeAccessKey(new Long(20));
		AssertJUnit.assertEquals(gdta.getGroupDataTypeAccessKey(),new Long(20) );
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setGroupDataTypeAccessKey(java.lang.Long)}.
	 */
	@Test
	public final void _19testSetGroupDataTypeAccessKey() {
		gdta.setGroupDataTypeAccessKey(new Long(20));
		AssertJUnit.assertEquals(gdta.getGroupDataTypeAccessKey(),new Long(20) );
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#getWriteFlag()}.
	 */
	@Test
	public final void _20testGetWriteFlag() {
		gdta.setWriteFlag("Y");
		AssertJUnit.assertEquals(gdta.getWriteFlag(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.GroupDataTypeAccess#setWriteFlag(java.lang.String)}.
	 */
	@Test
	public final void _21testSetWriteFlag() {
		gdta.setWriteFlag("Y");
		AssertJUnit.assertEquals(gdta.getWriteFlag(), "Y");
	}

}
