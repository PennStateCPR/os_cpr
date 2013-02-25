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

import edu.psu.iam.cpr.core.service.returns.IdCardReturn;
import edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn;
import edu.psu.iam.cpr.core.service.returns.PhotoReturn;

/**
 * @author cpruser
 *
 */
public class IdCardReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardReturn#getaPersonIdCardReturn()}.
	 */
	@Test
	public final void testGetaPersonIdCardReturn() {
		PersonIdCardReturn aPersonIdCardReturn = new PersonIdCardReturn();
		IdCardReturn aIdCardReturn = new IdCardReturn();
		aIdCardReturn.setaPersonIdCardReturn(aPersonIdCardReturn);
		AssertJUnit.assertNotNull(aIdCardReturn.getaPersonIdCardReturn());
	}
	

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardReturn#setaPersonIdCardReturn(edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn)}.
	 */
	@Test
	public final void testSetaPersonIdCardReturn() {
		
		PersonIdCardReturn aPersonIdCardReturn = new PersonIdCardReturn();
		IdCardReturn aIdCardReturn = new IdCardReturn();
		aIdCardReturn.setaPersonIdCardReturn(aPersonIdCardReturn);
		AssertJUnit.assertNotNull(aIdCardReturn.getaPersonIdCardReturn());
	}
	

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardReturn#getaPhotoReturn()}.
	 */
	@Test
	public final void testGetaPhotoReturn() {
		PhotoReturn aPhotoReturn = new PhotoReturn();
		IdCardReturn aIdCardReturn = new IdCardReturn();
		aIdCardReturn.setaPhotoReturn(aPhotoReturn);
		AssertJUnit.assertNotNull(aIdCardReturn.getaPhotoReturn());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardReturn#setaPhotoReturn(edu.psu.iam.cpr.core.service.returns.PhotoReturn)}.
	 */
	@Test
	public final void testSetaPhotoReturn() {
		PhotoReturn aPhotoReturn = new PhotoReturn();
		IdCardReturn aIdCardReturn = new IdCardReturn();
		aIdCardReturn.setaPhotoReturn(aPhotoReturn);
		AssertJUnit.assertNotNull(aIdCardReturn.getaPhotoReturn());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardReturn#IdCardReturn()}.
	 */
	@Test
	public final void testIdCardReturn() {
		
		
		IdCardReturn aIdCardReturn = new IdCardReturn();
		AssertJUnit.assertNotNull(aIdCardReturn);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardReturn#IdCardReturn(edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn, edu.psu.iam.cpr.core.service.returns.PhotoReturn)}.
	 */
	@Test
	public final void testIdCardReturnPersonIdCardReturnPhotoReturn() {
		PhotoReturn aPhotoReturn = new PhotoReturn();
		PersonIdCardReturn aPersonIdCardReturn = new PersonIdCardReturn();
		IdCardReturn aIdCardReturn = new IdCardReturn(aPersonIdCardReturn, aPhotoReturn);
		AssertJUnit.assertNotNull(aIdCardReturn);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardReturn#IdCardReturn(edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn)}.
	 */
	@Test
	public final void testIdCardReturnPersonIdCardReturn() {
		PersonIdCardReturn aPersonIdCardReturn = new PersonIdCardReturn();
		IdCardReturn aIdCardReturn = new IdCardReturn(aPersonIdCardReturn);
		AssertJUnit.assertNotNull(aIdCardReturn);
	}

}
