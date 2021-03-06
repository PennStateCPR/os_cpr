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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;

public class ServiceCoreTest {


	@Test(expectedExceptions=Exception.class)
	public final void testInitalizeService1() throws Exception {
		new ServiceCore().initializeService(null, null, null, null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testInitalizeService2() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", null, null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testInitalizeService3() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", "abcd", null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testInitalizeService4() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", "dun4IAM", null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testInitalizeService5() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", "dun4IAM", "AddPerson", null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testInitalizeService6() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", "dun4IAM", "AddPerson", null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testIntializeService7() throws Exception {
		new ServiceCore().initializeService(null, null, null, null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testIntializeService8() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", null, null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testIntializeService9() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", "abcd", null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testIntializeService10() throws Exception {
		new ServiceCore().initializeService(new Database(), "cprblah", "dun4IAM", null, null, null, null, null);
	}
	
	
	@Test
	public final void testDumpParameter1() {
		AssertJUnit.assertEquals(new ServiceCore().dumpParameter(null), "NOT SPECIFIED");
	}
	@Test
	public final void testDumpParameter2() {
		AssertJUnit.assertEquals(new ServiceCore().dumpParameter("abcd"), "abcd");
	}
	

}
