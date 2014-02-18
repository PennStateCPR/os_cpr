/* SVN FILE: $Id: MapHelper.java 5888 2012-12-12 02:14:04Z jal55 $ */
package edu.psu.iam.cpr.ip.util;

/**
 * MapHelper static methods for manipulating Map entries 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.util 
 * @author $Author: jal55 $
 * @version $Rev: 5888 $
 * @lastrevision $Date: 2012-12-11 21:14:04 -0500 (Tue, 11 Dec 2012) $
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MapHelper 
{
	
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private MapHelper()  { }
	
	/**
	 * Create a map of String object inside the argument map
	 * @param argMap - a multipurpose map with diverse objects
	 * @return - A map consisting of only String objects with the original keys
	 */
	
	public static Map<String, String> genericObjToStringHashMap(Map<String, Object> argMap)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Set<String> mapKeys = argMap.keySet();

		for(String key: mapKeys)
		{
			if(argMap.get(key) instanceof String)
			{
				resultMap.put(key, (String)argMap.get(key));
			}
		}
		
		return resultMap;
	}
}
