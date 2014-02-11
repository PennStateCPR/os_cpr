/* SVN FILE: $Id: TableColumn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database;

/**
 * This class is used to store information about an database column metadata.  This information is
 * gathered via the database's metadata facility.
 * 
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
 *
 * @package edu.psu.iam.cpr.core.database
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class TableColumn {
	
	/** Database column name. */
	private String columnName;
	
	/** Database column type.  (see java.sql.Types). */
	private short dataType;
	
	/** Database column Size. */
	private int columnSize;
	
	/** Database column precision. */
	private int decimalDigits;
	
	/** Indicator as to whether the database table is null or not, 1 - yes, 0 - no. */
	private int nullable;

	/**
	 * Constructor.
	 */
	public TableColumn() {
		super();
		
	}
	
	/**
	 * Constructor.
	 * @param columnName database column name.
	 * @param dataType database column data type.
	 * @param columnSize database column size (in bytes).
	 * @param decimalDigits database column precision for numeric values.
	 * @param nullable indicates whether the field allows nulls or not. 
	 */
	public TableColumn(String columnName, short dataType, int columnSize,
			int decimalDigits, int nullable) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.columnSize = columnSize;
		this.decimalDigits = decimalDigits;
		this.nullable = nullable;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the dataType
	 */
	public short getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(short dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the columnSize
	 */
	public int getColumnSize() {
		return columnSize;
	}

	/**
	 * @param columnSize the columnSize to set
	 */
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	/**
	 * @return the decimalDigits
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * @param decimalDigits the decimalDigits to set
	 */
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	/**
	 * @return the nullable
	 */
	public int getNullable() {
		return nullable;
	}

	/**
	 * @param nullable the nullable to set
	 */
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	
}
