/* SVN FILE: $Id: ProxyDb.java 7362 2013-05-23 12:32:05Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.proxy;
/**
 * ProxyDb front-ends some database access to determine if there is an <br/>
 * in-core table that can be substituted in place of a database call.
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action
 * @author $Author: jal55 $
 * @version $Rev: 7362 $
 * @lastrevision $Date: 2013-05-23 08:32:05 -0400 (Thu, 23 May 2013) $
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.dao.IdentityProvisioningDAO;

public final class ProxyDb
{
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private ProxyDb()  { }

	/**
	 * Determine if the country list can be returned from memory, <br/>
	 * or if a database call is necessary.
	 * @param application - the application (scope) memory map
	 * @return a Map representing the country list
	 */
	public static Map<String, String> loadCountryListFromDb(Map application)
	{
		Map<String, String> map = new HashMap<String, String>();

		if(application.containsKey(UIConstants.IN_CORE_COUNTRY_MAP_KEY))
		{
			map = (Map<String, String>) application.get(UIConstants.IN_CORE_COUNTRY_MAP_KEY);
		}
		else
		{
			map = IdentityProvisioningDAO.loadCountryListFromDb();
			application.put(UIConstants.IN_CORE_COUNTRY_MAP_KEY, map);
		}
		return map;
	}

	/**
	 * Determine if the state list can be returned from memory, <br/>
	 * or if a database call is necessary.
	 * @param application - the application (scope) memory map
	 * @return a Map representing the US list of states
	 */
	public static Map<String, String> loadStateListFromDb(Map application)
	{
		Map<String, String> map = new HashMap<String, String>();

		if(application.containsKey(UIConstants.IN_CORE_STATE_MAP_KEY))
		{
			map = (Map<String, String>) application.get(UIConstants.IN_CORE_STATE_MAP_KEY);
		}
		else
		{
			map = IdentityProvisioningDAO.loadStateListFromDb();
			application.put(UIConstants.IN_CORE_STATE_MAP_KEY, map);
		}
		return map;
	}

	/**
	 * Determine if the Registration Authority Properties can be returned from memory, <br/>
	 * or if a database call is necessary.
	 * @param application - the application (scope) memory map
	 * @return a Map representing the country list
	 */
	public static Map<String, String> loadRAPropertiestFromDb( Map application
			                                                 , String applicationName
			                                                 , String principalId)
	{
		Map<String, String> map = new HashMap<String, String>();

		String mapName = String.format("%s.%s.%s", UIConstants.IN_CORE_RA_MAP_KEY_PREFIX, applicationName, principalId);
		if(application.containsKey(mapName))
		{
			map = (Map<String, String>) application.get(mapName);
		}
		else
		{
			map = IdentityProvisioningDAO.getRAPropertiesFromDb(applicationName, principalId);
			application.put(mapName, map);
		}
		return map;
	}

	/**
	 * Determine if the Registration Authority Screens map can be returned from memory, <br/>
	 * or if a database call is necessary.
	 * @param application - the application (scope) memory map
	 * @param string
	 * @return a Map representing screen names, and their navigation order
	 */
	public static List<String> loadRAScreens( Map application
			                                , String applicationName
			                                , String principalId
			                                , String csvActionsToRemove)
   {
		List<String> raScreenList = new ArrayList<String>();

		String listName = String.format("%s.%s.%s", UIConstants.IN_CORE_RA_SCREEN_KEY_PREFIX, applicationName, principalId);
		if(application.containsKey(listName))
		{
			raScreenList = (ArrayList<String>)application.get(listName);
		}
		else
		{
			raScreenList = IdentityProvisioningDAO.loadRAScreensFromDb(applicationName, principalId);
			StringTokenizer removeActions = new StringTokenizer(csvActionsToRemove, ",");
			while(removeActions.hasMoreElements())
			{
				raScreenList.remove(removeActions.nextToken());
			}
			application.put(listName, raScreenList);
		}

		return raScreenList;
   }

	/**
	 * Determine if the gender list can be returned from memory, <br/>
	 * or if a database call is necessary.
	 * @param application - the application (scope) memory map
	 * @return a Map representing the valid list of genders
	 */
	public static Map<String, String> loadGenderListFromDb(Map application)
	{
		Map<String, String> map = new HashMap<String, String>();

		if(application.containsKey(UIConstants.IN_CORE_GENDER_MAP_KEY))
		{
			map = (Map<String, String>) application.get(UIConstants.IN_CORE_GENDER_MAP_KEY);
		}
		else
		{
			map = IdentityProvisioningDAO.loadGenderListFromDb();
			application.put(UIConstants.IN_CORE_GENDER_MAP_KEY, map);
		}
		return map;
	}


}
