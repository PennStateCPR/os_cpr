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
import edu.psu.iam.cpr.core.database.tables.ServiceLogTable;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
 * @author jvuccolo
 *
 */
public class ServiceCoreReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn#ServiceCoreReturn()}.
	 */
	@Test
	public final void testServiceCoreReturn() {
		new ServiceCoreReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
		ServiceCoreReturn s = new ServiceCoreReturn();
		s.setPersonId(-1);
		AssertJUnit.assertEquals(s.getPersonId(), -1);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn#setPersonId(int)}.
	 */
	@Test
	public final void testSetPersonId() {
		ServiceCoreReturn s = new ServiceCoreReturn();
		s.setPersonId(-1);
		AssertJUnit.assertEquals(s.getPersonId(), -1);
	}
	
	@Test
	public final void testSetWebServiceId() {
		ServiceCoreReturn s = new ServiceCoreReturn();
		s.setServiceLogTable(new ServiceLogTable());
	}
	
	@Test
	public final void testGetWebServiceId() {
		ServiceCoreReturn s = new ServiceCoreReturn();
		s.setServiceLogTable(new ServiceLogTable());
		s.getServiceLogTable();
	}
	
}
