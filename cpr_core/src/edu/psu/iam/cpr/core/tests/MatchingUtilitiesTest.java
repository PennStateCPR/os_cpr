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


import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.util.MatchingUtilities;

/**
 * @author E. Hayes
 *
 */

public class MatchingUtilitiesTest {
	
	@Test
	public final void testNameFormatting() {
		String s = MatchingUtilities.formatNameMatchInput("First", null, null);
		assertEquals("First", s);
		
		s = MatchingUtilities.formatNameMatchInput("First", "", "");
		assertEquals("First", s);
		
		s = MatchingUtilities.formatNameMatchInput(null, "Middle", null);
		assertEquals("", s);
		
		s = MatchingUtilities.formatNameMatchInput(null, null, "Last");
		assertEquals("Last", s);
		
		s = MatchingUtilities.formatNameMatchInput("First", null, "Last");
		assertEquals("First Last", s);
		
		s = MatchingUtilities.formatNameMatchInput("First", "Middle", null);
		assertEquals("First", s);
		
		s = MatchingUtilities.formatNameMatchInput("First", "Middle", "Last");
		assertEquals("First Last", s);
		
		s = MatchingUtilities.formatNameMatchInput("", "Middle", "Last");
		assertEquals("Last", s);
		
		s = MatchingUtilities.formatNameMatchInput("First    ", "   Middle    ", "  Last  ");
		assertEquals("First Last", s);
	}
	
	@Test
	public final void testAddressFormatting() {
		String s = MatchingUtilities.formatAddressMatchInput(null, null, null);
		assertEquals(s, "");
		
		s = MatchingUtilities.formatAddressMatchInput("", "", "");
		assertEquals(s, "");
		
		s = MatchingUtilities.formatAddressMatchInput("addr1", null, "");
		assertEquals(s, "addr1");
		
		s = MatchingUtilities.formatAddressMatchInput("", "addr2", null);
		assertEquals(s, "addr2");
		
		s = MatchingUtilities.formatAddressMatchInput(null, "", "addr3");
		assertEquals(s, "addr3");
		
		s = MatchingUtilities.formatAddressMatchInput("addr1", "addr2", null);
		assertEquals(s, "addr1 addr2");
		
		s = MatchingUtilities.formatAddressMatchInput("addr1", "addr2", "addr3");
		assertEquals(s, "addr1 addr2 addr3");
	}
	
	@Test
	public final void testCityFormatting() {
		String s = MatchingUtilities.formatCityMatchInput("");
		assertEquals("", s);
		
		s = MatchingUtilities.formatCityMatchInput(" ");
		assertEquals("", s);
		
		s = MatchingUtilities.formatCityMatchInput(null);
		assertEquals("", s);
		
		s = MatchingUtilities.formatCityMatchInput("City");
		assertEquals("City", s);
		
		s = MatchingUtilities.formatCityMatchInput("  City   ");
		assertEquals("City", s);
	}
	
	@Test
	public final void testLastLineFormatting() {
		String s = MatchingUtilities.formatLastLineMatchInput("", "", "");
		assertEquals("", s);
		
		s = MatchingUtilities.formatLastLineMatchInput("City", "", "");
		assertEquals("City", s);
		
		s = MatchingUtilities.formatLastLineMatchInput("", "State", "");
		assertEquals("State", s);
		
		s = MatchingUtilities.formatLastLineMatchInput("", "", "16801");
		assertEquals("16801", s);
		
		s = MatchingUtilities.formatLastLineMatchInput("City", "State", "");
		assertEquals("City State", s);
		
		s = MatchingUtilities.formatLastLineMatchInput("City", "State", "16801");
		assertEquals("City State 16801", s);
	}
}
