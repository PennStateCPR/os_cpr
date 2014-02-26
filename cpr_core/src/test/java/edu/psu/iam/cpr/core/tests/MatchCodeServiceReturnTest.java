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

import edu.psu.iam.cpr.core.api.returns.MatchCodeServiceReturn;
import edu.psu.iam.cpr.core.gi.AddSsnServiceReturn;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;


public class MatchCodeServiceReturnTest {

    @Test
    public void testDefaultConstructor() {
        final MatchCodeServiceReturn matchCodeServiceReturn = new MatchCodeServiceReturn();
        AssertJUnit.assertNull(matchCodeServiceReturn.getStatusMessage());
        AssertJUnit.assertNull(matchCodeServiceReturn.getMatchCode());
        AssertJUnit.assertEquals(0, matchCodeServiceReturn.getStatusCode());
    }

    @Test
    public void testConstructorParameters2() {
        final MatchCodeServiceReturn matchCodeServiceReturn = new MatchCodeServiceReturn(100, "StatusMessage");
        AssertJUnit.assertEquals(100, matchCodeServiceReturn.getStatusCode());
        AssertJUnit.assertEquals("StatusMessage", matchCodeServiceReturn.getStatusMessage());
        AssertJUnit.assertNull(matchCodeServiceReturn.getMatchCode());
    }

    @Test
    public void testConstructorParameter32() {
        final MatchCodeServiceReturn matchCodeServiceReturn = new MatchCodeServiceReturn(100, "StatusMessage", "MatchCode");
        AssertJUnit.assertEquals(100, matchCodeServiceReturn.getStatusCode());
        AssertJUnit.assertEquals("StatusMessage", matchCodeServiceReturn.getStatusMessage());
        AssertJUnit.assertEquals("MatchCode", matchCodeServiceReturn.getMatchCode());
    }

    @Test
    public void testSetStatusCode() {
        final MatchCodeServiceReturn matchCodeServiceReturn = new MatchCodeServiceReturn();
        matchCodeServiceReturn.setStatusCode(100);
        AssertJUnit.assertEquals(100, matchCodeServiceReturn.getStatusCode());
        AssertJUnit.assertNull(matchCodeServiceReturn.getStatusMessage());
        AssertJUnit.assertNull(matchCodeServiceReturn.getMatchCode());
    }

    @Test
    public void testSetMatchCode() {
        final MatchCodeServiceReturn matchCodeServiceReturn = new MatchCodeServiceReturn();
        matchCodeServiceReturn.setMatchCode("MatchCode");
        AssertJUnit.assertEquals("MatchCode", matchCodeServiceReturn.getMatchCode());
        AssertJUnit.assertNull(matchCodeServiceReturn.getStatusMessage());
        AssertJUnit.assertEquals(0, matchCodeServiceReturn.getStatusCode());
    }

    @Test
    public void testSetStatusMessage() {
        final MatchCodeServiceReturn matchCodeServiceReturn = new MatchCodeServiceReturn();
        matchCodeServiceReturn.setStatusMessage("StatusMessage");
        AssertJUnit.assertEquals("StatusMessage", matchCodeServiceReturn.getStatusMessage());
        AssertJUnit.assertEquals(0, matchCodeServiceReturn.getStatusCode());
        AssertJUnit.assertNull(matchCodeServiceReturn.getMatchCode());
    }
}
