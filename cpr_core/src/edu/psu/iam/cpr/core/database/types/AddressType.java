/* SVN FILE: $Id: AddressType.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * AddressType Type
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

public enum AddressType {
	PERMANENT_ADDRESS(258),
	LOCAL_ADDRESS(259),
	DOCUMENTED_ADDRESS(260),
	WORK_ADDRESS(261),
	BILLING_ACADEMIC_ADDRESS(262),
	BILLING_ADMINISTRATIVE_ADDRESS(263);

   /**
    * Contains the index
    */

	private long index;

   /**
    * @param index
    */

	private AddressType(long index) {
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

	private static final Map<Long, AddressType> LOOKUP = new HashMap<Long,AddressType>();
	static {
		for (AddressType p : EnumSet.allOf(AddressType.class)) {
			LOOKUP.put(p.index(), p);
		}
	}
	
   /**
    * @param index
    * @return enum
    */

	public static AddressType get(long index) {
		return LOOKUP.get(index);
	}
}

