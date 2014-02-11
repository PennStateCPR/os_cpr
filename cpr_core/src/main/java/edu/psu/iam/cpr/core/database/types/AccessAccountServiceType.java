/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.types;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 * Access Account Service. 
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public enum AccessAccountServiceType {
	KERBEROS(1),
	DIRECTORY(2),
	PASS(4),
	EMAIL(5);
	
	/**
	 * Contains the index
	 */
	private long index;

	/**
	 * @param index
	 */
	private AccessAccountServiceType(long index) {
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
	private static final Map<Long, AccessAccountServiceType> lookup = new HashMap<Long, AccessAccountServiceType>();
	static{
		for (AccessAccountServiceType p : EnumSet.allOf(AccessAccountServiceType.class)) {
			lookup.put(p.index(),p);
		}
	}
	
	/**
	 * @param index
	 * @return enum
	 */
	public static AccessAccountServiceType get(long index) {
		return lookup.get(index);
	}
}
