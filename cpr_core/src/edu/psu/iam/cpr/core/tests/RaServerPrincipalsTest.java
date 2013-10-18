/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Date;
import edu.psu.iam.cpr.core.database.beans.RaServerPrincipals;

/**
 * @author llg5
 *
 */
public class RaServerPrincipalsTest {
	RaServerPrincipals rsp = new RaServerPrincipals();
	Date d = new Date();
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#RaServerPrincipals()}.
	 */
	@Test
	public final void _01testRaServerPrincipals() {
		RaServerPrincipals thePrincipal = new RaServerPrincipals();
		AssertJUnit.assertNotNull(thePrincipal);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getStartDate()}.
	 */
	@Test
	public final void _02testGetStartDate() {
		rsp.setStartDate(d);
		AssertJUnit.assertEquals(rsp.getStartDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setStartDate(java.util.Date)}.
	 */
	@Test
	public final void _03testSetStartDate() {
		rsp.setStartDate(d);
		AssertJUnit.assertEquals(rsp.getStartDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getRaServerPrincipalKey()}.
	 */
	@Test
	public final void _04testGetRaServerPrincipalKey() {
		rsp.setRaServerPrincipalKey(new Long(6));
		AssertJUnit.assertEquals(rsp.getRaServerPrincipalKey(),new Long(6));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setRaServerPrincipalKey(java.lang.Long)}.
	 */
	@Test
	public final void _05testSetRaServerPrincipalKey() {
		rsp.setRaServerPrincipalKey(new Long(6));
		AssertJUnit.assertEquals(rsp.getRaServerPrincipalKey(),new Long(6));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getCreatedBy()}.
	 */
	@Test
	public final void _06testGetCreatedBy() {
		rsp.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(rsp.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void _07testSetCreatedBy() {
		rsp.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(rsp.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getLastUpdateOn()}.
	 */
	@Test
	public final void _08testGetLastUpdateOn() {
		rsp.setLastUpdateOn(d);
		AssertJUnit.assertEquals(rsp.getLastUpdateOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void _09testSetLastUpdateOn() {
		rsp.setLastUpdateOn(d);
		AssertJUnit.assertEquals(rsp.getLastUpdateOn(), d);
	}
	

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getCreatedOn()}.
	 */
	@Test
	public final void _10testGetCreatedOn() {
		rsp.setCreatedOn(d);
		AssertJUnit.assertEquals(rsp.getCreatedOn(), d);
	
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void _11testSetCreatedOn() {
		rsp.setCreatedOn(d);
		AssertJUnit.assertEquals(rsp.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getLastUpdateBy()}.
	 */
	@Test
	public final void _12testGetLastUpdateBy() {
		rsp.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(rsp.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void _13testSetLastUpdateBy() {
		rsp.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(rsp.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getRegistrationAuthorityKey()}.
	 */
	@Test
	public final void _14testGetRegistrationAuthorityKey() {
		rsp.setRegistrationAuthorityKey(new Long(20));
		AssertJUnit.assertEquals(rsp.getRegistrationAuthorityKey(),new Long(20));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setRegistrationAuthorityKey(java.lang.Long)}.
	 */
	@Test
	public final void _15testSetRegistrationAuthorityKey() {
		rsp.setRegistrationAuthorityKey(new Long(20));
		AssertJUnit.assertEquals(rsp.getRegistrationAuthorityKey(),new Long(20));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getEndDate()}.
	 */
	@Test
	public final void _16testGetEndDate() {
		rsp.setEndDate(d);
		AssertJUnit.assertEquals(rsp.getEndDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setEndDate(java.util.Date)}.
	 */
	@Test
	public final void _17testSetEndDate() {
		rsp.setEndDate(d);
		AssertJUnit.assertEquals(rsp.getEndDate(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#getRaServerPrincipal()}.
	 */
	@Test
	public final void _18testGetRaServerPrincipal() {
		rsp.setRaServerPrincipal(UnitTestHelper.TEST_USERID);
		AssertJUnit.assertEquals(rsp.getRaServerPrincipal(),UnitTestHelper.TEST_USERID);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.RaServerPrincipals#setRaServerPrincipal(java.lang.String)}.
	 */
	@Test
	public final void _19testSetRaServerPrincipal() {
		rsp.setRaServerPrincipal(UnitTestHelper.TEST_USERID);
		AssertJUnit.assertEquals(rsp.getRaServerPrincipal(),UnitTestHelper.TEST_USERID);
	}

}
