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

    @Test
    public final void _82testIsOptionYes1() {
        AssertJUnit.assertTrue(Utility.isOptionYes("yes"));
    }

    @Test
    public final void _82testIsOptionYes2() {
        AssertJUnit.assertTrue(Utility.isOptionYes("Yes"));
    }
    @Test
    public final void _82testIsOptionYes3() {
        AssertJUnit.assertTrue(Utility.isOptionYes("YES"));
    }
    @Test
    public final void _82testIsOptionYes4() {
        AssertJUnit.assertTrue(Utility.isOptionYes("y"));
    }
    @Test
    public final void _82testIsOptionYes5() {
        AssertJUnit.assertTrue(Utility.isOptionYes("Y"));
    }
    @Test
    public final void _82testIsOptionYes6() {
        AssertJUnit.assertTrue(Utility.isOptionYes("yE"));
    }

    @Test
    public final void _82testIsOptionYes7() {
        AssertJUnit.assertFalse(Utility.isOptionYes("No"));
    }

    @Test
    public final void _82testIsOptionYes8() {
        AssertJUnit.assertFalse(Utility.isOptionYes("fOoBar"));
    }

    @Test
    public final void _82testIsOptionNo1() {
        AssertJUnit.assertTrue(Utility.isOptionNo("no"));
    }

    @Test
    public final void _82testIsOptionNo2() {
        AssertJUnit.assertTrue(Utility.isOptionNo("No"));
    }
    @Test
    public final void _82testIsOptionNo3() {
        AssertJUnit.assertTrue(Utility.isOptionNo("NO"));
    }
    @Test
    public final void _82testIsOptionNo4() {
        AssertJUnit.assertTrue(Utility.isOptionNo("n"));
    }
    @Test
    public final void _82testIsOptionNo5() {
        AssertJUnit.assertTrue(Utility.isOptionNo("N"));
    }
    @Test
    public final void _82testIsOptionNo6() {
        AssertJUnit.assertTrue(Utility.isOptionNo("nO"));
    }

    @Test
    public final void _82testIsOptionNo7() {
        AssertJUnit.assertFalse(Utility.isOptionNo("Yes"));
    }

    @Test
    public final void _82testIsOptionNo8() {
        AssertJUnit.assertFalse(Utility.isOptionNo("fOoBar"));
    }

}
