/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Date;
import edu.psu.iam.cpr.core.database.beans.WebService;




/**
 * @author llg5
 *
 */
public class WebServiceTest {
	
	WebService ws = new WebService();
	Date d = new Date();
	
@Test
	public final void _01testGetWebServiceKey() {
		ws.setWebServiceKey(1L);
		AssertJUnit.assertEquals(ws.getWebServiceKey(), new Long(1));
	}
@Test
	public final void _02testGetWebService() {
		ws.setWebService("theService");
		AssertJUnit.assertEquals(ws.getWebService(), "theService");
	}
@Test
	public final void _03testGetWebServiceDesc() {
		ws.setWebServiceDesc("theService Desc");
		AssertJUnit.assertEquals(ws.getWebServiceDesc(), "theService Desc");
	}
@Test
	public final void _04testGetStartDate() {
		ws.setStartDate(d);
		AssertJUnit.assertEquals(ws.getStartDate(),d);
}	
@Test
	public final void _05testGetEndDate() {
		ws.setEndDate(d);
		AssertJUnit.assertEquals(ws.getEndDate(),d);
	}
@Test
	public final void _06testGetLastUpdateOn() {
		ws.setLastUpdateOn(d);
		AssertJUnit.assertEquals(ws.getLastUpdateOn(),d);
	}
@Test
	public final void _07testGetLastUpdateBy() {
		ws.setLastUpdateBy("fred");
		AssertJUnit.assertEquals(ws.getLastUpdateBy(),"fred");
	}
@Test
	public final void _08testGetCreatedDate() {
		ws.setCreatedOn(d);
		AssertJUnit.assertEquals(ws.getCreatedOn(),d);
	}
@Test
	public final void _09testGetCreatedBy() {
		ws.setCreatedBy("fred");
		AssertJUnit.assertEquals(ws.getCreatedBy(),"fred");
}
}
