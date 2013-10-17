/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Date;
import edu.psu.iam.cpr.core.database.beans.WebServiceAccess;

/**
 * @author llg5
 *
 */
public class WebServiceAccessTest {
	
	WebServiceAccess wsAccess = new  WebServiceAccess();
	Date d = new Date();
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#WebServiceAccess()}.
	 */
	@Test
	public final void _01testWebServiceAccess() {
		AssertJUnit.assertNotNull(wsAccess);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getCreatedBy()}.
	 */
	@Test
	public final void _02testGetCreatedBy() {
		wsAccess.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(wsAccess.getCreatedBy(),"SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void _03testSetCreatedBy() {
		wsAccess.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(wsAccess.getCreatedBy(),"SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getEndDate()}.
	 */
	@Test
	public final void _04testGetEndDate() {
		wsAccess.setEndDate(d);
		AssertJUnit.assertEquals(wsAccess.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setEndDate(java.util.Date)}.
	 */
	@Test
	public final void _05testSetEndDate() {
		wsAccess.setEndDate(d);
		AssertJUnit.assertEquals(wsAccess.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getSuspendFlag()}.
	 */
	@Test
	public final void _06testGetSuspendFlag() {
		wsAccess.setSuspendFlag("Y");
		AssertJUnit.assertEquals(wsAccess.getSuspendFlag(),"Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setSuspendFlag(java.lang.String)}.
	 */
	@Test
	public final void _07testSetSuspendFlag() {
		wsAccess.setSuspendFlag("Y");
		AssertJUnit.assertEquals(wsAccess.getSuspendFlag(),"Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getWebServiceKey()}.
	 */
	@Test
	public final void _08testGetWebServiceKey() {
		wsAccess.setWebServiceKey(1L);
		AssertJUnit.assertEquals(wsAccess.getWebServiceKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setWebServiceKey(java.lang.Long)}.
	 */
	@Test
	public final void _09testSetWebServiceKey() {
		wsAccess.setWebServiceKey(1L);
		AssertJUnit.assertEquals(wsAccess.getWebServiceKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getCprAccessGroupsKey()}.
	 */
	@Test
	public final void _10testGetCprAccessGroupsKey() {
		wsAccess.setCprAccessGroupsKey(1L);
		AssertJUnit.assertEquals(wsAccess.getCprAccessGroupsKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setCprAccessGroupsKey(java.lang.Long)}.
	 */
	@Test
	public final void _11testSetCprAccessGroupsKey() {
		wsAccess.setCprAccessGroupsKey(1L);
		AssertJUnit.assertEquals(wsAccess.getCprAccessGroupsKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getCreatedOn()}.
	 */
	@Test
	public final void _12testGetCreatedOn() {
		wsAccess.setCreatedOn(d);
		AssertJUnit.assertEquals(wsAccess.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void _13testSetCreatedOn() {
		wsAccess.setCreatedOn(d);
		AssertJUnit.assertEquals(wsAccess.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getLastUpdateBy()}.
	 */
	@Test
	public final void _14testGetLastUpdateBy() {
		wsAccess.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(wsAccess.getLastUpdateBy(),"SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void _15testSetLastUpdateBy() {
		wsAccess.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(wsAccess.getLastUpdateBy(),"SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getStartDate()}.
	 */
	@Test
	public final void _16testGetStartDate() {
		wsAccess.setStartDate(d);
		AssertJUnit.assertEquals(wsAccess.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setStartDate(java.util.Date)}.
	 */
	@Test
	public final void _17testSetStartDate() {
		wsAccess.setStartDate(d);
		AssertJUnit.assertEquals(wsAccess.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getLastUpdateOn()}.
	 */
	@Test
	public final void _18testGetLastUpdateOn() {
		wsAccess.setLastUpdateOn(d);
		AssertJUnit.assertEquals(wsAccess.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void _19testSetLastUpdateOn() {
		wsAccess.setLastUpdateOn(d);
		AssertJUnit.assertEquals(wsAccess.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#getWebServiceAccessKey()}.
	 */
	@Test
	public final void _20testGetWebServiceAccessKey() {
		wsAccess.setWebServiceAccessKey(1L);
		AssertJUnit.assertEquals(wsAccess.getWebServiceAccessKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.WebServiceAccess#setWebServiceAccessKey(java.lang.Long)}.
	 */
	@Test
	public final void _21testSetWebServiceAccessKey() {
		wsAccess.setWebServiceAccessKey(1L);
		AssertJUnit.assertEquals(wsAccess.getWebServiceAccessKey(), new Long(1));
	}

}
