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
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.grouper.GrouperPermissions;
import edu.psu.iam.cpr.core.grouper.RAGroupData;




	public class GrouperPermissionsTest {
	
		
	@Test
		public final void testGetGroupNameFromPrincipalFound() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			RAGroupData raGroup= gPerm.getGroupNameFromPrincipal( "hersheyapp");
			AssertJUnit.assertEquals(raGroup.getRegistrationAuthorityGroup(), "HYIAM");
		}
	@Test	
	public final void testGetGroupNameFromPrincipalMultipleGroups() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			RAGroupData raGroup1= gPerm.getGroupNameFromPrincipal("portal1");
			AssertJUnit.assertEquals(raGroup1.getRegistrationAuthorityGroup(),   "IAMTAG");
		}
	@Test	
		public final void testGetGroupNameFromPrincipalNotFound() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			RAGroupData raGroup2= gPerm.getGroupNameFromPrincipal("portal2");
			AssertJUnit.assertNull(raGroup2.getRegistrationAuthorityGroup());
	}
	@Test
		public final void testIsSubjectMemberOfGroupTrue() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertTrue(gPerm.isSubjectMemberOfMasterGroup( "IAMTAG", "llg5"));
		}
	@Test
		public final void testIsSubjectMemberOfGroupFalse() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertFalse(gPerm.isSubjectMemberOfMasterGroup(  "IAMTAG", "llg"));
		}
	@Test
		public final void testIsRequestAuthorizedTrue() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertTrue(gPerm.isRequestAuthorized("AddAddress", "llg5"));
		}
	@Test
		public final void testIsRequestAuthorizedFalse() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertTrue(gPerm.isRequestAuthorized("AddAddress", "vlt"));
		}
	@Test
		public final void testIsDataAccessAuthorizedTrue() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertTrue(gPerm.isDataActionAuthorized(AccessType.PERMANENT_PHONE.toString(), "access_operation_read", "llg5"));
		}
	@Test
		public final void testIsDataAccessAuthorizedFalse() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertTrue(gPerm.isDataActionAuthorized(AccessType.PERMANENT_PHONE.toString(), "access_operation_read", "vlt"));
		}
	@Test(expectedExceptions=Exception.class)
		public final void testIsDataAccessAuthorizedBadDataResource() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertTrue(gPerm.isRequestAuthorized("PHONE", "llg5"));
		}
	@Test(expectedExceptions=Exception.class)
		public final void testIsRequestAuthorizedBadWebService() throws CprException {
			GrouperPermissions gPerm = new GrouperPermissions();
			AssertJUnit.assertTrue(gPerm.isRequestAuthorized("address", "llg5"));
		}
	@Test
	public final void testIsAffiliationAccessAuthorized() throws CprException {
		GrouperPermissions gPerm = new GrouperPermissions();
		AssertJUnit.assertTrue(gPerm.isAfiliationAccessAuthorized("STUDENT_UNDERGRADUATE_CURRENT", "llg5"));
	}
	@Test
	public final void testIsAffiliationAccessAuthorizedFalse() throws CprException {
		GrouperPermissions gPerm = new GrouperPermissions();
		AssertJUnit.assertFalse(gPerm.isAfiliationAccessAuthorized("EMPLOYEE_WAGE_ACTIVE", "vlt"));
	}
}
