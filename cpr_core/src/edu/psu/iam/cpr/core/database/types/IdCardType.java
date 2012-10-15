/* SVN FILE: $Id$ */
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
 * IdCardType Type
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */

public enum IdCardType {
	ID_CARD_ID_PLUS_CARD_FACULTY_STAFF(153),
	ID_CARD_ID_PLUS_CARD_FACULTY_STAFF_PEMA(154),
	ID_CARD_ID_PLUS_CARD_FACULTY_STAFF_PROX(155),
	ID_CARD_ID_PLUS_CARD_FACULTY_STAFF_STUDENT(156),
	ID_CARD_ID_PLUS_CARD_AFFILIATE(157),
	ID_CARD_ID_PLUS_CARD_RETIREE(158),
	ID_CARD_ID_PLUS_CARD_EMERITUS(159),
	ID_CARD_ID_PLUS_CARD_CONTRACTOR(160),
	ID_CARD_ID_PLUS_CARD_WORLD_CAMPUS(161),
	ID_CARD_ID_PLUS_CARD_STUDENT(327),
	ID_CARD_ID_PLUS_CARD_HERSHEY_STUDENT(328),
	ID_CARD_ID_PLUS_CARD_HERSHEY_FACULTY_STAFF(329),
	ID_CARD_ID_PLUS_CARD_HERSHEY_FACULTY_STAFF_STUDENT(330),
	ID_CARD_ID_PLUS_CARD_HERSHEY_RETIREE(331),
	ID_CARD_ID_PLUS_CARD_HERSHEY_EMERITUS(332),
	ID_CARD_ID_PLUS_CARD_HERSHEY_EXTERNAL(333),
	ID_CARD_ID_CARD_HEALTH_SERVICES_PHOTO_ID(334),
	ID_CARD_ID_CARD_RESCOM_PHOTO_ID(335),
	ID_CARD_ID_CARD_VILLAGE_PHOTO_ID(336),
	ID_CARD_ID_CARD_ARL_PHOTO_ID(337);

   /**
    * Contains the index
    */

	private long index;

   /**
    * @param index
    */

	private IdCardType(long index) {
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

	private static final Map<Long, IdCardType> lookup = new HashMap<Long,IdCardType>();
	static {
		for (IdCardType p : EnumSet.allOf(IdCardType.class)) {
			lookup.put(p.index(), p);
		}
	}
	
   /**
    * @param index
    * @return enum
    */

	public static IdCardType get(long index) {
		return lookup.get(index);
	}
}

