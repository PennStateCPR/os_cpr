/* SVN FILE: $Id: ApplicationPropertyHelper.java 5898 2012-12-12 15:02:28Z jal55 $ */
package edu.psu.iam.cpr.ip.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * ApplicationPropertyHelper retrieves information from the appliciation-scope 
 * memory.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.util 
 * @author $Author: jal55 $
 * @version $Rev: 5898 $
 * @lastrevision $Date: 2012-12-12 10:02:28 -0500 (Wed, 12 Dec 2012) $
 */
public final class ApplicationPropertyHelper 
{
	
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private ApplicationPropertyHelper()  { }
	
	/**
	 * Get the list of items from Application memory based on a count field
	 * @return - an ArrayList containing the separate regular expression segments
	 */
	public static List<String> getPropertyListByCountAndName(String countKey, Map<String, Object> applicationMap)
	{
		List<String> list = new ArrayList<String>();
		
		String dataKey =  countKey.substring(0, countKey.lastIndexOf('.'));
		
		for(int i = 1; i <= FieldUtility.convertStringToInt((String)applicationMap.get(countKey)); i++ )
		{
			list.add((String)applicationMap.get(dataKey+i) );
		}
		
		return list;
	}
	
	/**
	 * Retrieve a list of property values, where the last character of the key increments by '1'
	 * @param key - the key without a number on the end
	 * @param applicationMap - the map to search for properties by key
	 * @return - an ArrayList<String>, or an empty ArrayList<String> if there are no properties
	 */
	public static List<String> getPropertyListByName(String key, Map<String, Object> applicationMap)
	{
		List<String> list = new ArrayList<String>();
		
		int i = 1;
		String element = null;
		while((element = (String)applicationMap.get(key + i++)) != null )
		{
			list.add(element );
		}
		
		return list;
	}
}
