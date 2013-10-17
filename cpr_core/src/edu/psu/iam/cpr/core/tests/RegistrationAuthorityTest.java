package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Date;
import edu.psu.iam.cpr.core.database.beans.RegistrationAuthority;



public class RegistrationAuthorityTest {

	
		RegistrationAuthority ra = new RegistrationAuthority();
		Date d = new Date();
		
	@Test
		public final void _01testGetRegistrationAuthorityKey() {
			ra.setRegistrationAuthorityKey(1L);
			AssertJUnit.assertEquals(ra.getRegistrationAuthorityKey(), new Long(1));
		}
	@Test
		public final void _02testGetRegistrationAuthority() {
			ra.setRegistrationAuthority("theRA");
			AssertJUnit.assertEquals(ra.getRegistrationAuthority(), "theRA");
		}
	@Test
		public final void _03testGetRegistrationAuthorityDesc() {
			ra.setRegistrationAuthorityDesc("theRA Desc");
			AssertJUnit.assertEquals(ra.getRegistrationAuthorityDesc(), "theRA Desc");
		}
	@Test
	public final void _04testGetSuspendFlag() {
		ra.setSuspendFlag("Y");
		AssertJUnit.assertEquals(ra.getSuspendFlag(),"Y");
	}	
	@Test
	public final void _05testGetRaContactKey() {
		ra.setRaContactKey(1L);
		AssertJUnit.assertEquals(ra.getRaContactKey(), new Long(1));
	}
	@Test
		public final void _06testGetStartDate() {
			ra.setStartDate(d);
			AssertJUnit.assertEquals(ra.getStartDate(),d);
	}	
	@Test
		public final void _07testGetEndDate() {
			ra.setEndDate(d);
			AssertJUnit.assertEquals(ra.getEndDate(),d);
		}
	@Test
		public final void _08testGetLastUpdateOn() {
			ra.setLastUpdateOn(d);
			AssertJUnit.assertEquals(ra.getLastUpdateOn(),d);
		}
	@Test
		public final void _09testGetLastUpdateBy() {
			ra.setLastUpdateBy("fred");
			AssertJUnit.assertEquals(ra.getLastUpdateBy(),"fred");
		}
	@Test
		public final void _10testGetCreatedDate() {
			ra.setCreatedOn(d);
			AssertJUnit.assertEquals(ra.getCreatedOn(),d);
		}
	@Test
		public final void _11testGetCreatedBy() {
			ra.setCreatedBy("fred");
			AssertJUnit.assertEquals(ra.getCreatedBy(),"fred");
	}
}
