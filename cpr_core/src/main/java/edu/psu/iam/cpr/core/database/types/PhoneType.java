/* SVN FILE: $Id: PhoneType.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.types;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
/**
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
 * PhoneType Type
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

public enum PhoneType {
	PERMANENT_PHONE(265),
	LOCAL_PHONE(266),
	WORK_PHONE(267);

   /**
    * Contains the index
    */

	private long index;

   /**
    * @param index
    */

	private PhoneType(long index) {
		this.index = index;
	}
	
   /**
    * Return the value of the enum
    * @return index
    */

	public long index() {
		return index;
	}
	
   /**
    *
    */   

	private static final Map<Long, PhoneType> LOOKUP = new HashMap<Long,PhoneType>();
	static {
		for (PhoneType p : EnumSet.allOf(PhoneType.class)) {
			LOOKUP.put(p.index(), p);
		}
	}
	
   /**
    * @param index
    * @return enum
    */

	public static PhoneType get(long index) {
		return LOOKUP.get(index);
	}
}

