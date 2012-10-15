/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.util;
/**
 * This is a helper class for formatting arguments.
 * It contains at present one method.
 * 
 * Methods in this class should be static, and require
 * no instantiation.
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
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: jal55 $
 * @version $Rev: 2576 $
 * @lastrevision $Date: 2012-01-31 14:49:33 -0500 (Wed, 15 Feb 2012) $
 */

public class ArgumentHelper {
	
	/**
	 * Format arguments to service routines.
	 * Nulls are allowed for any or all of the inputs.
	 * 
	 * Sample output, minus apostrophes: 'elementName=[elementValue] '
	 *  
	 * @param elementName the element name 
	 * @param elementValue the element value
	 * @return 'elementName=[elementValue] '
	 */
	public static String bracket(String elementName, String elementValue)
	{
		return String.format("%s=[%s] ", elementName, elementValue);
	}
}
