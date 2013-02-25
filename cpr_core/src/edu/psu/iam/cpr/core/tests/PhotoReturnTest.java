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

import edu.psu.iam.cpr.core.service.returns.PhotoReturn;

public class PhotoReturnTest {

	String s = "abcd";
	PhotoReturn p = new PhotoReturn();
	
	@Test
	public final void testPhotoReturn() {
		new PhotoReturn();
	}

	@Test
	public final void testPhotoReturnByteArrayStringStringStringStringStringStringString() {
		new PhotoReturn(null,null,null,null,null,null);
	}

	@Test
	public final void testGetPhoto() {
		byte[] b = new byte[1];
		p.setPhoto(b);
		AssertJUnit.assertEquals(p.getPhoto(),b);
		
	}

	@Test
	public final void testGetDateTaken() {
		p.setDateTaken(s);
		AssertJUnit.assertEquals(p.getDateTaken(),s);
	}

	@Test
	public final void testGetLastUpdateBy() {
		p.setLastUpdateBy(s);
		AssertJUnit.assertEquals(p.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		p.setLastUpdateOn(s);
		AssertJUnit.assertEquals(p.getLastUpdateOn(),s);
	}

	@Test
	public final void testGetCreatedBy() {
		p.setCreatedBy(s);
		AssertJUnit.assertEquals(p.getCreatedBy(),s);
	}

	@Test
	public final void testGetCreatedOn() {
		p.setCreatedOn(s);
		AssertJUnit.assertEquals(p.getCreatedOn(),s);
	}

}
