/* SVN FILE: $Id: UserCommentType.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * UserCommentType Type
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

public enum UserCommentType {
	USER_COMMENT_MISUSE(284),
	USER_COMMENT_DELETED(285),
	USER_COMMENT_OTHER(286),
	USER_COMMENT_EXTENSION(287),
	USER_COMMENT_CREATION(288),
	USER_COMMENT_CHANGE(289);

   /**
    * Contains the index
    */

	private long index;

   /**
    * @param index
    */

	private UserCommentType(long index) {
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

	private static final Map<Long, UserCommentType> LOOKUP = new HashMap<Long,UserCommentType>();
	static {
		for (UserCommentType p : EnumSet.allOf(UserCommentType.class)) {
			LOOKUP.put(p.index(), p);
		}
	}
	
   /**
    * @param index
    * @return enum
    */

	public static UserCommentType get(long index) {
		return LOOKUP.get(index);
	}
}

