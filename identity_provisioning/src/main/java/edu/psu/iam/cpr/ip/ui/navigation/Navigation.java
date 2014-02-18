/* SVN FILE: $Id: Navigation.java 5968 2013-01-04 14:57:59Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.navigation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;

/**
 * Determine if navigation from one web page to another is allowed.  
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.navigation 
 * @author $Author: jal55 $
 * @version $Rev: 5968 $
 * @lastrevision $Date: 2013-01-04 09:57:59 -0500 (Fri, 04 Jan 2013) $
 */
@SuppressWarnings("ALL")
public final class Navigation
{
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private Navigation()  { }
	
	/**
	 * This static method will [at a later time] make the decision to allow </br>
	 * this particular action to be invoked, or not. </br>
	 * </br>
	 * At the present time, the stack is just being maintained, but will become
	 * an active participant in navigation.
	 * @param className
	 * @param map
	 * @return true if prohibited, or false is not prohibited
	 */
	public static boolean isProhibitedByLock(String className, Map<String, Object> sessionMap)
	{
		// default is to allow
		boolean prohibited = false;      
		
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, NavigationElement> navMap = (LinkedHashMap<String, NavigationElement>)sessionMap.get(UIConstants.NAVIGATION_KEY);
		
		if(navMap.containsKey(className) && navMap.get(className).locked == true)
		{
			prohibited = true;
		}
		
		return prohibited;
	}
	
	/**
	 * Add the current action to the navigation elements
	 * @param className - The actual class name minus the package
	 * @param actionName - The action name for the Struts action
	 * @param sessionMap - A map of variable names with their values in a Java 'Map'
	 */
	@SuppressWarnings("unchecked")
	public static void addAction(String className, String actionName,  Map<String, Object> sessionMap)
	{
		Map<String, NavigationElement> navMap = (LinkedHashMap<String, NavigationElement>)sessionMap.get(UIConstants.NAVIGATION_KEY);
		if(sessionMap.get("nav.navigation.map") == null)
		{
			navMap = new LinkedHashMap<String, NavigationElement>();
		}
		
		navMap.put(className, new NavigationElement(className, actionName));
		
		sessionMap.put("nav.navigation.map", navMap);
	}

	/**
	 * Upon entry this routine will indicate that a page is locked
	 * @param sessionMap - A map of variables names, and their values
	 */
	@SuppressWarnings("unchecked")
	public static void lock(Map<String, Object> sessionMap)
	{
		LinkedHashMap<String, NavigationElement> navMap = (LinkedHashMap<String, NavigationElement>)sessionMap.get(UIConstants.NAVIGATION_KEY);
		if(navMap != null && !navMap.isEmpty())
		{
			Set<Map.Entry<String, NavigationElement>> navEntries = navMap.entrySet();
			for(Map.Entry<String, NavigationElement>  navEntry : navEntries)
			{
				navEntry.getValue().locked = true;
			}
		}
			
		sessionMap.put("nav.navigation.map", navMap);
	}
	
	/**
	 * Return the previous action.  For example legal_name, or welcome, or data_accuracy
	 * @param sessionMap - A map of variables
	 * @param className - The current class name of where the user is attempting to navigate to
	 * @param type - Latter means the most recent previous, while former means prior to latter 
	 * @return A String object with the previous action name
	 */
	public static String getPreviousAction(Map<String, Object> sessionMap, String className, Navigation.RETURN_TYPE type)
	{
		// Previous Action name -- what we return
		String previousAction = null;
		String currentAction  = null;
		
		List<String> actionList = new ArrayList<String>();
		
		LinkedHashMap<String, NavigationElement> navMap = (LinkedHashMap<String, NavigationElement>)sessionMap.get(UIConstants.NAVIGATION_KEY);
		
		// We have to transcend the entire table, at present to get the last entry, the previous action
		// LinkedHashMaps entries are not continuous, but they are double linked
		if(navMap != null && !navMap.isEmpty())
		{
			Set<Map.Entry<String, NavigationElement>> navEntries = navMap.entrySet();
			for(Map.Entry<String, NavigationElement>  navEntry : navEntries)
			{
				String actionName = navEntry.getValue().actionName;
				actionName = actionName.substring(actionName.lastIndexOf('/') +1); 
				actionList.add(actionName);
				if(navEntry.getValue().getCurrentClassName().equals(className))
					currentAction = actionName;
			}
			switch(type)
			{
				case LATTER:  previousAction = actionList.get(actionList.indexOf(currentAction) -RETURN_TYPE.LATTER.getReturnType());  break; 
				case FORMER:  previousAction = actionList.get(actionList.indexOf(currentAction) -RETURN_TYPE.FORMER.getReturnType());  break;
			}
			
		}

		return previousAction;
	}
	
	public static enum RETURN_TYPE 
	{ 
		LATTER(1),
		FORMER(2);
		
		private int returnType;
		
		private RETURN_TYPE(int returnType){
			this.returnType = returnType;
		}
		
		public int getReturnType() {
			return returnType;
		}
	}

	public static class NavigationElement implements Serializable
	{
		private String  time;
		private String  ip;
		private String  currentClassName;
		private String  actionName;
		private boolean locked;
		
		public NavigationElement(String currentClassName, String actionName)
		{
			time = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss.S").format(new Date());
			this.ip = "";
			this.currentClassName = currentClassName;
			this.actionName = actionName;
		}
		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException 
		{
			time = in.readUTF();
			ip = in.readUTF();
			currentClassName = in.readUTF();
			actionName = in.readUTF();
			locked = in.readBoolean();
		}
		private void writeObject(ObjectOutputStream out) throws IOException 
		{
			out.writeUTF(time);
			out.writeUTF(ip);
			out.writeUTF(currentClassName);
			out.writeUTF(actionName);
			out.writeBoolean(locked);		
		}
		/**
		 * @return the time
		 */
		public String getTime() {
			return time;
		}
		/**
		 * @param time the time to set
		 */
		public void setTime(String time) {
			this.time = time;
		}
		/**
		 * @return the ip
		 */
		public String getIp() {
			return ip;
		}
		/**
		 * @param ip the ip to set
		 */
		public void setIp(String ip) {
			this.ip = ip;
		}
		/**
		 * @return the currentClassName
		 */
		public String getCurrentClassName() {
			return currentClassName;
		}
		/**
		 * @param currentClassName the currentClassName to set
		 */
		public void setCurrentClassName(String currentClassName) {
			this.currentClassName = currentClassName;
		}
		/**
		 * @return the actionName
		 */
		public String getActionName() {
			return actionName;
		}
		/**
		 * @param actionName the actionName to set
		 */
		public void setActionName(String actionName) {
			this.actionName = actionName;
		}
		/**
		 * @return the locked
		 */
		public boolean isLocked() {
			return locked;
		}
		/**
		 * @param locked the locked to set
		 */
		public void setLocked(boolean locked) {
			this.locked = locked;
		}
	}
}



