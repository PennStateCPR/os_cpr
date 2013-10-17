/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.types;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
*
* This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
* view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
* Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
* Access Account Status. 
* @package edu.psu.iam.cpr.core.database.types
* @author $Author$
* @version $Rev$
* @lastrevision $Date$
*/
public enum AccessAccountStatusType {

	FULL_ACCOUNT("J"),
	PARTIAL_ACCOUNT("P"),
	LOCKED_ACCOUNT("L"),
	DELETED_ACCOUNT("D");

	/**
	 * Contains the string value.
	 */
	private String key;

	/**
	 * @param key
	 */
	private AccessAccountStatusType(String key) {
		this.key = key;
	}


	/**
	 * Return the value of the enum 
	 * @return key
	 */
	public String key() {
		return key;
	}
	
	/**
	 * 
	 */
	private static final Map<String, AccessAccountStatusType> lookup = new HashMap<String, AccessAccountStatusType>();
	static{
		for (AccessAccountStatusType p : EnumSet.allOf(AccessAccountStatusType.class)) {
			lookup.put(p.key(),p);
		}
	}
	
	/**
	 * @param key
	 * @return enum
	 */
	public static AccessAccountStatusType get(String key) {
		return lookup.get(key);
	}
}
