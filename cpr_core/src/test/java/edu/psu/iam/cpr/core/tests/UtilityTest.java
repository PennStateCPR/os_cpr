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



import edu.psu.iam.cpr.core.database.types.GenderType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.psu.iam.cpr.core.util.Utility;

public class UtilityTest {

	@Test
	public final void testConvertDateToString() {
        AssertJUnit.assertNull(Utility.convertDateToString(null));
	}

	@Test
	public final void testConvertTimestampToString() {
        AssertJUnit.assertNull(Utility.convertTimestampToString(null));
	}

	@Test
	public final void testAreFieldsEqual1() {
		final String s1 = null;
		final String s2 = null;
		AssertJUnit.assertTrue(Utility.areStringFieldsEqual(s1, s2));
	}

    @Test
	public final void testAreFieldsEqual2() {
		AssertJUnit.assertFalse(Utility.areStringFieldsEqual(null, "abcd"));
	}

    @Test
	public final void testAreFieldsEqual3() {
		AssertJUnit.assertTrue(Utility.areStringFieldsEqual("", ""));
	}

    @Test
	public final void testAreFieldsEqual4() {
		AssertJUnit.assertFalse(Utility.areStringFieldsEqual("", "abcd"));
	}

    @Test
	public final void testAreFieldsEqual6() {
		AssertJUnit.assertFalse(Utility.areStringFieldsEqual("ABCD1", "abcd"));
	}

    @Test
	public final void testAreFieldsEqual7() {
		final Long l1 = null;
		final Long l2 = null;
		AssertJUnit.assertTrue(Utility.areLongFieldsEqual(l1, l2));
	}

    @Test
	public final void testAreFieldsEqual8() {
		final Long l1 = null;
		final Long l2 = 1L;
		AssertJUnit.assertFalse(Utility.areLongFieldsEqual(l1, l2));
	}

    @Test
	public final void testAreFieldsEqual9() {
		final Long l1 = 1L;
		final Long l2 = 1L;
		AssertJUnit.assertTrue(Utility.areLongFieldsEqual(l1, l2));
	}

    @Test
    public final void testIsOptionYes1() {
        AssertJUnit.assertTrue(Utility.isOptionYes("yes"));
    }

    @Test
    public final void testIsOptionYes2() {
        AssertJUnit.assertTrue(Utility.isOptionYes("Yes"));
    }
    
    @Test
    public final void testIsOptionYes3() {
        AssertJUnit.assertTrue(Utility.isOptionYes("YES"));
    }
    
    @Test
    public final void testIsOptionYes4() {
        AssertJUnit.assertTrue(Utility.isOptionYes("y"));
    }
    
    @Test
    public final void testIsOptionYes5() {
        AssertJUnit.assertTrue(Utility.isOptionYes("Y"));
    }
    
    @Test
    public final void testIsOptionYes6() {
        AssertJUnit.assertTrue(Utility.isOptionYes("yE"));
    }

    @Test
    public final void testIsOptionYes7() {
        AssertJUnit.assertFalse(Utility.isOptionYes("No"));
    }

    @Test
    public final void testIsOptionYes8() {
        AssertJUnit.assertFalse(Utility.isOptionYes("fOoBar"));
    }

    @Test
    public final void testIsOptionYes9() {
        AssertJUnit.assertFalse(Utility.isOptionYes(null));
    }

    @Test
    public final void testIsOptionNo1() {
        AssertJUnit.assertTrue(Utility.isOptionNo("no"));
    }

    @Test
    public final void testIsOptionNo2() {
        AssertJUnit.assertTrue(Utility.isOptionNo("No"));
    }
    
    @Test
    public final void testIsOptionNo3() {
        AssertJUnit.assertTrue(Utility.isOptionNo("NO"));
    }
    
    
    @Test
    public final void testIsOptionNo4() {
        AssertJUnit.assertTrue(Utility.isOptionNo("n"));
    }
    
    @Test
    public final void testIsOptionNo5() {
        AssertJUnit.assertTrue(Utility.isOptionNo("N"));
    }
    
    @Test
    public final void testIsOptionNo6() {
        AssertJUnit.assertTrue(Utility.isOptionNo("nO"));
    }

    @Test
    public final void testIsOptionNo7() {
        AssertJUnit.assertFalse(Utility.isOptionNo("Yes"));
    }

    @Test
    public final void testIsOptionNo8() {
        AssertJUnit.assertFalse(Utility.isOptionNo("fOoBar"));
    }

    @Test
    public final void testIsOptionNo9() {
        AssertJUnit.assertFalse(Utility.isOptionNo(null));
    }

    @Test
    public final void testGenderNull() {
        AssertJUnit.assertNull(Utility.genderStringToType(null));
    }

    @Test
    public final void testGenderFemale() {
        AssertJUnit.assertEquals(GenderType.GENDER_FEMALE, Utility.genderStringToType("F"));
        AssertJUnit.assertEquals(GenderType.GENDER_FEMALE, Utility.genderStringToType("f"));
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("Female"));
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("female"));
    }

    @Test
    public final void testGenderMale() {
        AssertJUnit.assertEquals(GenderType.GENDER_MALE, Utility.genderStringToType("M"));
        AssertJUnit.assertEquals(GenderType.GENDER_MALE, Utility.genderStringToType("m"));
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("Male"));
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("male"));
    }

    @Test
    public final void testGenderOther() {
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("O"));
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("o"));
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("Other"));
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("other"));
    }

    @Test
    public final void testGenderDefault() {
        AssertJUnit.assertEquals(GenderType.GENDER_OTHER, Utility.genderStringToType("foobar"));
    }

    @Test
    public final void testSafeConvertLongToString() {
        AssertJUnit.assertNull(Utility.safeConvertLongToString(null));
        AssertJUnit.assertEquals("9223372036854775807", Utility.safeConvertLongToString(Long.MAX_VALUE));
    }

    @Test
    public final void testSafeConvertStringToLong() {
        AssertJUnit.assertEquals(new Long(-1L), Utility.safeConvertStringToLong(null));
        AssertJUnit.assertEquals(new Long(Long.MAX_VALUE), Utility.safeConvertStringToLong("9223372036854775807"));
    }

    @Test
    public final void testFieldIsPresent() {
        AssertJUnit.assertFalse(Utility.fieldIsPresent((String[]) null));
        AssertJUnit.assertFalse(Utility.fieldIsPresent("foo", null, "bar"));
        AssertJUnit.assertTrue(Utility.fieldIsPresent("foo"));
        AssertJUnit.assertTrue(Utility.fieldIsPresent(new String[]{}));
        AssertJUnit.assertTrue(Utility.fieldIsPresent(new String[]{"foo", "bar", "baz"}));
    }

    @Test
    public final void testFieldIsNotPresent() {
        AssertJUnit.assertTrue(Utility.fieldIsNotPresent((String[]) null));
        AssertJUnit.assertTrue(Utility.fieldIsNotPresent("foo", null, "bar"));
        AssertJUnit.assertFalse(Utility.fieldIsNotPresent("foo"));
        AssertJUnit.assertFalse(Utility.fieldIsNotPresent(new String[]{}));
        AssertJUnit.assertFalse(Utility.fieldIsNotPresent(new String[]{"foo", "bar", "baz"}));
    }

    @Test
    public final void testConvertStringToInt() {
        AssertJUnit.assertEquals(0, Utility.convertStringToInt(null));
        AssertJUnit.assertEquals(0, Utility.convertStringToInt("foo"));
        AssertJUnit.assertEquals(Integer.MAX_VALUE, Utility.convertStringToInt("2147483647"));
    }

    @Test
    public final void _89testIntegerOrDefault() {
        AssertJUnit.assertEquals(0, Utility.integerOrDefault(null, 0));
        AssertJUnit.assertEquals(0, Utility.integerOrDefault("foo", 0));
        AssertJUnit.assertEquals(Integer.MAX_VALUE, Utility.integerOrDefault("2147483647", 0));
    }

    @Test
    public final void testFormatInt() {
        AssertJUnit.assertEquals("2,147,483,647", Utility.formatInt(Integer.MAX_VALUE));
    }

    @Test
    public final void testFormatLong() {
        AssertJUnit.assertEquals("9,223,372,036,854,775,807", Utility.formatLong(Long.MAX_VALUE));
    }

    @Test
    public final void testGetNameTokens() {
        final List<String> emptyList = new ArrayList<String>();
        final List<String> populatedList = new ArrayList<String>();

        AssertJUnit.assertTrue(populatedList.add("foo"));
        AssertJUnit.assertTrue(populatedList.add("bar"));
        AssertJUnit.assertTrue(populatedList.add("baz"));

        AssertJUnit.assertEquals(emptyList, Utility.getNameTokens(null));
        AssertJUnit.assertEquals(emptyList, Utility.getNameTokens(""));
        AssertJUnit.assertEquals(populatedList, Utility.getNameTokens("foo,bar,baz"));
        AssertJUnit.assertEquals(populatedList, Utility.getNameTokens("foo, bar, baz"));
        AssertJUnit.assertEquals(populatedList, Utility.getNameTokens("foo,      bar,  baz"));
    }

    @Test
    public void testMakeStartDate() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        final Calendar endCal = Calendar.getInstance();
        endCal.setTime(cal.getTime());
        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);

        AssertJUnit.assertEquals(0, endCal.getTime().compareTo(Utility.makeStartDate(cal.getTime())));
    }

    @Test
    public void testMakeEndDate() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        final Calendar endCal = Calendar.getInstance();
        endCal.setTime(cal.getTime());
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 0);

        AssertJUnit.assertEquals(0, endCal.getTime().compareTo(Utility.makeEndDate(cal.getTime())));
    }

    @Test
    public void testAreDateFieldsEqual() {
        final Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2014);
        cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal1.set(Calendar.DAY_OF_MONTH, 1);

        final Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2014);
        cal2.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 1);

        final Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR, 2016);
        cal3.set(Calendar.MONTH, Calendar.MARCH);
        cal3.set(Calendar.DAY_OF_MONTH, 15);

        AssertJUnit.assertTrue(Utility.areDateFieldsEqual(null, null));
        AssertJUnit.assertFalse(Utility.areDateFieldsEqual(cal1.getTime(), null));
        AssertJUnit.assertFalse(Utility.areDateFieldsEqual(null, cal2.getTime()));
        AssertJUnit.assertTrue(Utility.areDateFieldsEqual(cal1.getTime(), cal2.getTime()));
        AssertJUnit.assertFalse(Utility.areDateFieldsEqual(cal1.getTime(), cal3.getTime()));
    }

    @Test
    public void testFormatDateToISO8601() {
        final Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2014);
        cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal1.set(Calendar.DAY_OF_MONTH, 23);
        cal1.set(Calendar.HOUR_OF_DAY, 14);
        cal1.set(Calendar.MINUTE, 46);
        cal1.set(Calendar.SECOND, 3);

        AssertJUnit.assertEquals("2014-02-23T19:46:03+0000", Utility.formatDateToISO8601(cal1.getTime()));
    }

    @Test
    public void testformatTimeMsToISO8601() {
        final Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2014);
        cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        cal1.set(Calendar.HOUR_OF_DAY, 10);
        cal1.set(Calendar.MINUTE, 30);
        cal1.set(Calendar.SECOND, 5);

        AssertJUnit.assertEquals("2014-02-01T15:30:05+0000", Utility.formatTimeMsToISO8601(cal1.getTime().getTime()));
    }

    @Test
    public void testConstructUri() {
        final String path = "path/to/some/service";
        final String key = "serviceName";

        AssertJUnit.assertEquals("/" + path + "/" + "null", Utility.constructUri(path, null));
        AssertJUnit.assertEquals("/null/" + key, Utility.constructUri(null, key));
        AssertJUnit.assertEquals("/" + path + "/" + key, Utility.constructUri(path, key));
    }

    @Test
    public void testMayBeIPv6Address() {
        AssertJUnit.assertFalse(Utility.mayBeIPv6Address(null));

        AssertJUnit.assertTrue(Utility.mayBeIPv6Address("::1"));
        AssertJUnit.assertTrue(Utility.mayBeIPv6Address("::"));
        AssertJUnit.assertTrue(Utility.mayBeIPv6Address("2001:db8:0:0:1:0:0:1"));

        AssertJUnit.assertFalse(Utility.mayBeIPv6Address(""));
        AssertJUnit.assertFalse(Utility.mayBeIPv6Address(":1"));
        AssertJUnit.assertFalse(Utility.mayBeIPv6Address("123.123.123.123"));
        AssertJUnit.assertFalse(Utility.mayBeIPv6Address("tomcat.eu.apache.org:443"));
    }

    @Test
    public void testcanonicalizeAddress() {
        AssertJUnit.assertNull(Utility.canonicalizeAddress(null));
        AssertJUnit.assertEquals("", Utility.canonicalizeAddress(""));

        // IPv4-safe
        AssertJUnit.assertEquals("123.123.123.123", Utility.canonicalizeAddress("123.123.123.123"));
        AssertJUnit.assertEquals("123.1.2.23", Utility.canonicalizeAddress("123.1.2.23"));

        // Introductory RFC 5952 examples
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:db8:0:0:1:0:0:1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:0db8:0:0:1:0:0:1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:db8::1:0:0:1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:db8::0:1:0:0:1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:0db8::1:0:0:1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:db8:0:0:1::1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:db8:0000:0:1::1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:DB8:0:0:1::1"));

        // Strip leading zeros (2.1)
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:eeee:1", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:eeee:0001"));
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:eeee:1", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:eeee:001"));
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:eeee:1", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:eeee:01"));
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:eeee:1", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:eeee:1"));

        // Zero compression (2.2)
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:0:1", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd::1"));
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:0:1", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:0:1"));

        AssertJUnit.assertEquals("2001:db8::1", Utility.canonicalizeAddress("2001:db8:0:0:0::1"));
        AssertJUnit.assertEquals("2001:db8::1", Utility.canonicalizeAddress("2001:db8:0:0::1"));
        AssertJUnit.assertEquals("2001:db8::1", Utility.canonicalizeAddress("2001:db8:0::1"));
        AssertJUnit.assertEquals("2001:db8::1", Utility.canonicalizeAddress("2001:db8::1"));

        AssertJUnit.assertEquals("2001:db8::aaaa:0:0:1", Utility.canonicalizeAddress("2001:db8::aaaa:0:0:1"));
        AssertJUnit.assertEquals("2001:db8::aaaa:0:0:1", Utility.canonicalizeAddress("2001:db8:0:0:aaaa::1"));

        // Uppercase or lowercase (2.3)
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:eeee:aaaa", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:eeee:aaaa"));
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:eeee:aaaa", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:eeee:AAAA"));
        AssertJUnit.assertEquals("2001:db8:aaaa:bbbb:cccc:dddd:eeee:aaaa", Utility.canonicalizeAddress("2001:db8:aaaa:bbbb:cccc:dddd:eeee:AaAa"));

        // Some more zero compression for localhost addresses
        AssertJUnit.assertEquals("::1", Utility.canonicalizeAddress("0:0:0:0:0:0:0:1"));
        AssertJUnit.assertEquals("::1", Utility.canonicalizeAddress("0000:0:0:0:0:0:0:0001"));
        AssertJUnit.assertEquals("::1", Utility.canonicalizeAddress("00:00:0:0:00:00:0:01"));
        AssertJUnit.assertEquals("::1", Utility.canonicalizeAddress("::0001"));
        AssertJUnit.assertEquals("::1", Utility.canonicalizeAddress("::1"));

        // IPv6 unspecified address
        AssertJUnit.assertEquals("::", Utility.canonicalizeAddress("0:0:0:0:0:0:0:0"));
        AssertJUnit.assertEquals("::", Utility.canonicalizeAddress("0000:0:0:0:0:0:0:0000"));
        AssertJUnit.assertEquals("::", Utility.canonicalizeAddress("00:00:0:0:00:00:0:00"));
        AssertJUnit.assertEquals("::", Utility.canonicalizeAddress("::0000"));
        AssertJUnit.assertEquals("::", Utility.canonicalizeAddress("::0"));
        AssertJUnit.assertEquals("::", Utility.canonicalizeAddress("::"));

        // Leading zeros (4.1)
        AssertJUnit.assertEquals("2001:db8::1", Utility.canonicalizeAddress("2001:0db8::0001"));

        // Shorten as much as possible (4.2.1)
        AssertJUnit.assertEquals("2001:db8::2:1", Utility.canonicalizeAddress("2001:db8:0:0:0:0:2:1"));
        AssertJUnit.assertEquals("2001:db8::", Utility.canonicalizeAddress("2001:db8:0:0:0:0:0:0"));

        // Handling One 16-Bit 0 Field (4.2.2)
        AssertJUnit.assertEquals("2001:db8:0:1:1:1:1:1", Utility.canonicalizeAddress("2001:db8:0:1:1:1:1:1"));
        AssertJUnit.assertEquals("2001:db8:0:1:1:1:1:1", Utility.canonicalizeAddress("2001:db8::1:1:1:1:1"));

        // Choice in Placement of "::" (4.2.3)
        AssertJUnit.assertEquals("2001:0:0:1::1", Utility.canonicalizeAddress("2001:0:0:1:0:0:0:1"));
        AssertJUnit.assertEquals("2001:db8::1:0:0:1", Utility.canonicalizeAddress("2001:db8:0:0:1:0:0:1"));

        // IPv4 inside IPv6
        AssertJUnit.assertEquals("::ffff:192.0.2.1", Utility.canonicalizeAddress("::ffff:192.0.2.1"));
        AssertJUnit.assertEquals("::ffff:192.0.2.1", Utility.canonicalizeAddress("0:0:0:0:0:ffff:192.0.2.1"));
        AssertJUnit.assertEquals("::192.0.2.1", Utility.canonicalizeAddress("::192.0.2.1"));
        AssertJUnit.assertEquals("::192.0.2.1", Utility.canonicalizeAddress("0:0:0:0:0:0:192.0.2.1"));

        // Zone ID
        AssertJUnit.assertEquals("fe80::f0f0:c0c0:1919:1234%4", Utility.canonicalizeAddress("fe80::f0f0:c0c0:1919:1234%4"));
        AssertJUnit.assertEquals("fe80::f0f0:c0c0:1919:1234%4", Utility.canonicalizeAddress("fe80:0:0:0:f0f0:c0c0:1919:1234%4"));

        AssertJUnit.assertEquals("::%4", Utility.canonicalizeAddress("::%4"));
        AssertJUnit.assertEquals("::%4", Utility.canonicalizeAddress("::0%4"));
        AssertJUnit.assertEquals("::%4", Utility.canonicalizeAddress("0:0::0%4"));
        AssertJUnit.assertEquals("::%4", Utility.canonicalizeAddress("0:0:0:0:0:0:0:0%4"));

        AssertJUnit.assertEquals("::1%4", Utility.canonicalizeAddress("::1%4"));
        AssertJUnit.assertEquals("::1%4", Utility.canonicalizeAddress("0:0::1%4"));
        AssertJUnit.assertEquals("::1%4", Utility.canonicalizeAddress("0:0:0:0:0:0:0:1%4"));

        AssertJUnit.assertEquals("::1%eth0", Utility.canonicalizeAddress("::1%eth0"));
        AssertJUnit.assertEquals("::1%eth0", Utility.canonicalizeAddress("0:0::1%eth0"));
        AssertJUnit.assertEquals("::1%eth0", Utility.canonicalizeAddress("0:0:0:0:0:0:0:1%eth0"));

        // Hostname safety
        AssertJUnit.assertEquals("www.apache.org", Utility.canonicalizeAddress("www.apache.org"));
        AssertJUnit.assertEquals("ipv6.google.com", Utility.canonicalizeAddress("ipv6.google.com"));
    }

    @Test
    public void testIsStringEmptyNullInput() {
        AssertJUnit.assertTrue(Utility.isStringEmpty(null));
    }

    @Test
    public void testStringEmptyEmptyInput() {
        AssertJUnit.assertTrue(Utility.isStringEmpty(""));
    }

    @Test
    public void testStringEmptyWhitespaceInput() {
        AssertJUnit.assertTrue(Utility.isStringEmpty("    "));
    }

    @Test
    public void testStringEmptyNonemptyInput() {
        AssertJUnit.assertFalse(Utility.isStringEmpty("  foo    "));
    }
}
