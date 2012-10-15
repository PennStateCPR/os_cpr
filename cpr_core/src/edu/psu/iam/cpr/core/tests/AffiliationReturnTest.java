/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

/**
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.service.returns.AffiliationReturn;

/**
 * Test for the PsuAffiliationReturn class
 * @author llg5
 *
 */
public class AffiliationReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#getAffiliation()}.
	 */
	@Test
	public final void testGetAffiliation() {
		AffiliationReturn pARTable = new AffiliationReturn( "STUDENT_UNDERGRADUATE_PROSPECT","Y", null,"02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, pARTable.getAffiliation(), "STUDENT_UNDERGRADUATE_PROSPECT");
	}

	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setAffiliation(java.lang.String)}.
	 */
	@Test
	public final void testSetPsuAffiliation() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setAffiliation("STUDENT_UNDERGRADUATE_PROSPECT");
		AssertJUnit.assertEquals(null, pARTable.getAffiliation(), "STUDENT_UNDERGRADUATE_PROSPECT");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#getPrimary()}.
	 */
	@Test
	public final void testGetPrimary() {
		AffiliationReturn pARTable = new AffiliationReturn( "STUDENT_UNDERGRADUATE_PROSPECT", "Y", null,"02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, pARTable.getPrimary(), "Y");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setPrimary(java.lang.String)}.
	 */
	@Test
	public final void testSetPrimary() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setPrimary("Y");
		AssertJUnit.assertEquals(null, pARTable.getPrimary(), "Y");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setStartDate(java.lang.String)}.
	 */
	@Test
	public final void testSetStartDate() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setStartDate("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, pARTable.getStartDate(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setEndDate(java.lang.String)}.
	 */
	@Test
	public final void testSetEndDate() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setEndDate("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, pARTable.getEndDate(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testLastUpdateBy() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(null, pARTable.getLastUpdateBy(), "SYSTEM");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setLastUpdateOn(java.lang.String)}.
	 */
	@Test
	public final void testLastUpdateOn() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setLastUpdateOn("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, pARTable.getLastUpdateOn(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void testCreatedBy() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(null, pARTable.getCreatedBy(), "SYSTEM");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#setCreatedOn(java.lang.String)}.
	 */
	@Test
	public final void testCreatedOn() {
		AffiliationReturn pARTable = new AffiliationReturn();
		pARTable.setCreatedOn("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(null, pARTable.getCreatedOn(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#AffiliationReturn()}.
	 */
	@Test
	public final void testAffiliationReturn() {
		new AffiliationReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#AffiliationReturn(java.lang.String, java.lang.String,java.lang.String)}.
	 */
	@Test
	public final void testAffiliationReturnStringString() {
		AffiliationReturn pARTable = new AffiliationReturn( "STUDENT_UNDERGRADUAT_PROSPECT","Y", null, "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, pARTable.getPrimary(), "Y");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#AffiliationReturn(java.lang.String, java.lang.String,java.lang.String)}.
	 */
	@Test
	public final void testAffiliationType() {
		AffiliationReturn pARTable = new AffiliationReturn( "STUDENT_UNDERGRADUAT_PROSPECT","Y", "eduPerson", "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertEquals(null, pARTable.getAffiliationType(), "eduPerson");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.AffiliationReturn#AffiliationReturn(java.lang.String, java.lang.String,java.lang.String)}.
	 */
	@Test
	public final void testAffiliationTypeNull() {
		AffiliationReturn pARTable = new AffiliationReturn( "STUDENT_UNDERGRADUAT_PROSPECT","Y",  null, "02/01/2012 09:09:09" ,null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM" );
		AssertJUnit.assertNull(pARTable.getAffiliationType());
	}
}
