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



import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import java.util.Date;

import edu.psu.iam.cpr.core.util.Utility;

public class UtilityTest {

	@Test
	public final void testConvertDateToString() {
		Utility.convertDateToString(new Date());
	}

	@Test
	public final void testConvertTimestampToString() {
		Utility.convertTimestampToString(new Date());
	}

	@Test
	public final void _74testAreFieldsEqual1() {
		String s1 = null;
		String s2 = null;
		AssertJUnit.assertTrue(Utility.areStringFieldsEqual(s1,s2));
	}
	@Test
	public final void _75testAreFieldsEqual2() {
		AssertJUnit.assertFalse(Utility.areStringFieldsEqual(null, "abcd"));
	}
	@Test
	public final void _76testAreFieldsEqual3() {
		AssertJUnit.assertTrue(Utility.areStringFieldsEqual("", ""));
	}
	@Test
	public final void _77testAreFieldsEqual4() {
		AssertJUnit.assertFalse(Utility.areStringFieldsEqual("", "abcd"));
	}
	@Test
	public final void _78testAreFieldsEqual6() {
		AssertJUnit.assertFalse(Utility.areStringFieldsEqual("ABCD1", "abcd"));
	}
	@Test
	public final void _79testAreFieldsEqual7() {
		Long l1 = null;
		Long l2 = null;
		AssertJUnit.assertTrue(Utility.areLongFieldsEqual(l1,l2));
	}
	@Test
	public final void _80testAreFieldsEqual8() {
		Long l1 = null;
		Long l2 = 1L;
		AssertJUnit.assertFalse(Utility.areLongFieldsEqual(l1,l2));
	}
	@Test
	public final void _81testAreFieldsEqual9() {
		Long l1 = 1L;
		Long l2 = 1L;
		AssertJUnit.assertTrue(Utility.areLongFieldsEqual(l1,l2));
	}
	

}
