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
import edu.psu.iam.cpr.core.authentication.AuthenticateService;
import edu.psu.iam.cpr.core.error.CprException;

public class AuthenticateServiceTest {


	@Test(expectedExceptions=CprException.class)
	public void test1() throws CprException {
		AuthenticateService.authenticate(null, null);
	}
	
	@Test(expectedExceptions=CprException.class)
	public void test2() throws CprException {
		AuthenticateService.authenticate("portal1", null);
	}
	
	@Test(expectedExceptions=CprException.class)
	public void test3() throws CprException {
		AuthenticateService.authenticate(null, "password");
	}
	
	@Test(expectedExceptions=CprException.class)
	public void test4() throws CprException {
		AuthenticateService.authenticate("cpruser", "defghi");
	}
	@Test
	public void test5() throws CprException {
		AuthenticateService.authenticate("cpruser", "abcd1234");
	}
}
