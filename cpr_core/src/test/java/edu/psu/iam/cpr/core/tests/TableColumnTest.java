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
import edu.psu.iam.cpr.core.database.TableColumn;

/**
 * @author cpruser
 *
 */
public class TableColumnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#TableColumn()}.
	 */
	@Test
	public final void testTableColumn() {
		new TableColumn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#TableColumn(java.lang.String, short, int, int, int)}.
	 */
	@Test
	public final void testTableColumnStringShortIntIntInt() {
		new TableColumn("abc", (short) 1, 1, 1, 1);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#getColumnName()}.
	 */
	@Test
	public final void testGetColumnName() {
		TableColumn t = new TableColumn();
		t.setColumnName("abc");
		AssertJUnit.assertEquals(t.getColumnName(), "abc");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#setColumnName(java.lang.String)}.
	 */
	@Test
	public final void testSetColumnName() {
		TableColumn t = new TableColumn();
		t.setColumnName("abc");
		AssertJUnit.assertEquals(t.getColumnName(), "abc");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#getDataType()}.
	 */
	@Test
	public final void testGetDataType() {
		TableColumn t = new TableColumn();
		t.setDataType((short) 1);
		AssertJUnit.assertEquals(t.getDataType(), (short) 1);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#setDataType(short)}.
	 */
	@Test
	public final void testSetDataType() {
		TableColumn t = new TableColumn();
		t.setDataType((short) 1);
		AssertJUnit.assertEquals(t.getDataType(), (short) 1);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#getColumnSize()}.
	 */
	@Test
	public final void testGetColumnSize() {
		TableColumn t = new TableColumn();
		t.setColumnSize(2);
		AssertJUnit.assertEquals(t.getColumnSize(), 2);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#setColumnSize(int)}.
	 */
	@Test
	public final void testSetColumnSize() {
		TableColumn t = new TableColumn();
		t.setColumnSize(2);
		AssertJUnit.assertEquals(t.getColumnSize(), 2);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#getDecimalDigits()}.
	 */
	@Test
	public final void testGetDecimalDigits() {
		TableColumn t = new TableColumn();
		t.setDecimalDigits(2);
		AssertJUnit.assertEquals(t.getDecimalDigits(), 2);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#setDecimalDigits(int)}.
	 */
	@Test
	public final void testSetDecimalDigits() {
		TableColumn t = new TableColumn();
		t.setDecimalDigits(2);
		AssertJUnit.assertEquals(t.getDecimalDigits(), 2);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#getNullable()}.
	 */
	@Test
	public final void testGetNullable() {
		TableColumn t = new TableColumn();
		t.setNullable(0);
		AssertJUnit.assertEquals(t.getNullable(), 0);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.TableColumn#setNullable(int)}.
	 */
	@Test
	public final void testSetNullable() {
		TableColumn t = new TableColumn();
		t.setNullable(0);
		AssertJUnit.assertEquals(t.getNullable(), 0);
	}

}
