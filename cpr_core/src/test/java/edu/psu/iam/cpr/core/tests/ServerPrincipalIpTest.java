/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Date;
import edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp;

/**
 * @author llg5
 *
 */
public class ServerPrincipalIpTest {
	ServerPrincipalIp serverIP = new ServerPrincipalIp();
	Date d = new Date();
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#ServerPrincipalIp()}.
	 */
	@Test
	public final void _01testServerPrincipalIp() {
		AssertJUnit.assertNotNull(serverIP);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getCreatedBy()}.
	 */
	@Test
	public final void _02testGetCreatedBy() {
		serverIP.setCreatedOn(d);
		AssertJUnit.assertEquals(serverIP.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void _03testSetCreatedBy() {
		serverIP.setCreatedOn(d);
		AssertJUnit.assertEquals(serverIP.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getEndDate()}.
	 */
	@Test
	public final void _04testGetEndDate() {
		serverIP.setEndDate(d);
		AssertJUnit.assertEquals(serverIP.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setEndDate(java.util.Date)}.
	 */
	@Test
	public final void _05testSetEndDate() {
		serverIP.setEndDate(d);
		AssertJUnit.assertEquals(serverIP.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getIpAddress()}.
	 */
	@Test
	public final void _06testGetIpAddress() {
		serverIP.setIpAddress("128.118.89.1");
		AssertJUnit.assertEquals(serverIP.getIpAddress(), "128.118.89.1");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setIpAddress(java.lang.String)}.
	 */
	@Test
	public final void _07testSetIpAddress() {
		serverIP.setIpAddress("128.118.89.1");
		AssertJUnit.assertEquals(serverIP.getIpAddress(), "128.118.89.1");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getCreatedOn()}.
	 */
	@Test
	public final void _08testGetCreatedOn() {
		serverIP.setCreatedOn(d);
		AssertJUnit.assertEquals(serverIP.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void _09testSetCreatedOn() {
		serverIP.setCreatedOn(d);
		AssertJUnit.assertEquals(serverIP.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getServerPrincipalIpKey()}.
	 */
	@Test
	public final void _10testGetServerPrincipalIpKey() {
		serverIP.setServerPrincipalIpKey(1L);
		AssertJUnit.assertEquals(serverIP.getServerPrincipalIpKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setServerPrincipalIpKey(java.lang.Long)}.
	 */
	@Test
	public final void _11testSetServerPrincipalIpKey() {
		serverIP.setServerPrincipalIpKey(1L);
		AssertJUnit.assertEquals(serverIP.getServerPrincipalIpKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getLastUpdateBy()}.
	 */
	@Test
	public final void _12testGetLastUpdateBy() {
		serverIP.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(serverIP.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void _13testSetLastUpdateBy() {
		serverIP.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(serverIP.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getRaServerPrincipalKey()}.
	 */
	@Test
	public final void _14testGetRaServerPrincipalKey() {
		serverIP.setRaServerPrincipalKey(1L);
		AssertJUnit.assertEquals(serverIP.getRaServerPrincipalKey(), new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setRaServerPrincipalKey(java.lang.Long)}.
	 */
	@Test
	public final void _15testSetRaServerPrincipalKey() {
		serverIP.setRaServerPrincipalKey(1L);
		AssertJUnit.assertEquals(serverIP.getRaServerPrincipalKey(), new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getStartDate()}.
	 */
	@Test
	public final void _16testGetStartDate() {
		serverIP.setStartDate(d);
		AssertJUnit.assertEquals(serverIP.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setStartDate(java.util.Date)}.
	 */
	@Test
	public final void _17testSetStartDate() {
		serverIP.setStartDate(d);
		AssertJUnit.assertEquals(serverIP.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#getLastUpdateOn()}.
	 */
	@Test
	public final void _18testGetLastUpdateOn() {
		serverIP.setLastUpdateOn(d);
		AssertJUnit.assertEquals(serverIP.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.ServerPrincipalIp#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void _19testSetLastUpdateOn() {
		serverIP.setLastUpdateOn(d);
		AssertJUnit.assertEquals(serverIP.getLastUpdateOn(),d);
	}

}
