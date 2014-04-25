/* SVN FILE: $Id: DebugAction.java 5876 2012-12-11 13:39:44Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * DebugAction sets a session switch that enables the debug option 
 * link to be inserted into rendering JSP pages.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5876 $
 * @lastrevision $Date: 2012-12-11 08:39:44 -0500 (Tue, 11 Dec 2012) $
 */
public class DebugAction extends BaseAction {

	private static final String ENABLED = "enabled";
	private static final String DISABLED = "disabled";
	private static final String DEBUG_SKIP_VALIDATION = "debug.skip.validation";
	private static final String DEPLOYED = "deployed";
	
	private static final String ON    = "on";
	private static final String OFF   = "off";
	private static final String YES   = "yes";
	private static final String DEBUG = "debug";
		
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ip.ui.action.BaseAction#execute()
	 */
	private String debugSkipValidation;
	private String deployed;
	private String debug;
	private String allPages;
	
	@Override
	@Action(value="debug",results={ @Result(name="success",location="/jsp/debug.jsp")})
	/**
	 * The action business logic
	 */
	public String execute() 
	{
		boolean argumentsSpecified = false;

		// Allow for resetting of the deployed switch
		processDeployed(deployed);
		
		// process the skip validation field
		argumentsSpecified = processSkipValidation(debugSkipValidation, argumentsSpecified);
	

		//Sometimes, we may want to turn on all pages, even though database options for the RA excludes a few
		argumentsSpecified = processAllPages(argumentsSpecified);
		

		// Flip the debug option on if off, if no parameters are specified, else follow the specified parameters of on and off
		Map map = this.getSessionMap();
		
        String debugOption = (String)map.get(DEBUG);
        
        // Only flip the debug option if there were no arguments
        if(argumentsSpecified == false)
        {
        	if( FieldUtility.fieldIsNotPresent(debugOption)  || debugOption.equalsIgnoreCase(DISABLED)) 
        	{
        		log.info(String.format("%s debug option now enabled", getUniqueId()));
        		map.put(DEBUG, ENABLED);
        	}
        	else
        	{
        		log.info(String.format("%s debug option now disabled", getUniqueId()));
        		map.put(DEBUG, DISABLED);
        	}
        }
        else
        {
        	// Arguments were specified for other features, so this item specification also requires specification, if desired
        	if(FieldUtility.fieldIsPresent(debug) && debug.equalsIgnoreCase(ON))
        	{
        		log.info(String.format("%s debug option now enabled", getUniqueId()));
        		map.put(DEBUG, ENABLED);
        	}
        	else
        	if(FieldUtility.fieldIsPresent(debug) && debug.equalsIgnoreCase(OFF))        		
        	{
        		log.info(String.format("%s debug option now disabled", getUniqueId()));
        		map.put(DEBUG, DISABLED);
        	}
        }

		return SUCCESS;
	}

	/**
	 * Set or reset the deployed optin to whatever is entered: 'Y' or 'N'
	 * @param deployed
	 */
	public void processDeployed(String deployed)
	{
		if(FieldUtility.fieldIsPresent(deployed))
		{
			getSessionMap().put(DEPLOYED, deployed);
		}
	}
	
	/**
	 * Process skipValidation option requests for turning option on or off
	 * @param skipValidation
	 * @param specified
	 * @return
	 */
	public boolean processSkipValidation(String skipValidation, boolean initialBoolean)
	{
		boolean argumentsSpecified = initialBoolean;
		
		if(FieldUtility.fieldIsPresent(debugSkipValidation))
		{
			if(debugSkipValidation.equalsIgnoreCase(ON))
			{
				getSessionMap().put(DEBUG_SKIP_VALIDATION, debugSkipValidation.toLowerCase());
				log.info(String.format("%s server-side validation [debugSkipValidation]is %s" , getUniqueId(), getSessionString(DEBUG_SKIP_VALIDATION)));
				argumentsSpecified = true;
			}
			else
			{
				debugSkipValidation = OFF;
				getSessionMap().put(DEBUG_SKIP_VALIDATION, debugSkipValidation.toLowerCase());
				log.info(String.format("%s server-side validation [debugSkipValidation]is %s" , getUniqueId(), getSessionString(DEBUG_SKIP_VALIDATION)));
				argumentsSpecified = true;
			}	
		}
		
		return argumentsSpecified;
	}
	
	/**
	 * Process and set the option to re-insert additional pages, excluded via db options, into this user's session-- development option
	 * @param initialBoolean - The current boolean setting for arguments fromt he caller
	 * @return - boolean true when arguments have been specified, else false;
	 */
	public boolean processAllPages(boolean initialBoolean)
	{
		boolean argumentsSpecified = initialBoolean;
		
		if(FieldUtility.fieldIsPresent(allPages))
		{
			List list = (ArrayList) getApplicationMap().get(getSessionString("rac.raScreens"));
			argumentsSpecified = true;
			
			if(isYes(allPages))
			{
				log.info(String.format("%s All page option is set to  [%s]", getUniqueId(), allPages));
				
				// Insert the missing pages: FormerName, and previous_address for testing purposes

				// determine which screen FormerName should follow
				StringTokenizer insertAfter   = new StringTokenizer(getApplicationString("logic.optional.insert.after"), ",");
				StringTokenizer insertActions = new StringTokenizer(getApplicationString("logic.optional.screens"), ",");
				
				if(insertAfter.countTokens() != insertActions.countTokens())
				{
					log.info(String.format("%s YOU MUST HAVE THE SAME NUMBER OF PARAMETERS ON BOTH THE \n.. [%s] property and \n.. [%s] property \n",
							        getUniqueId(), "logic.optional.insert.after", "logic.optional.screens"));
				}
				
				while(insertActions.hasMoreElements())
				{
					list = insertAction(insertActions.nextToken(), insertAfter.nextToken(), list);
				}
				
				
				// Save the updated list
				getApplicationMap().put(getSessionString("rac.raScreens"), list);
					
				log.info(String.format("%s the new screen array list --> %s", getUniqueId(), list));

			}
			else
			{
				// Remove the optional items from the list
				log.info(String.format("%s All page option is set to  [%s]", getUniqueId(), allPages));
				StringTokenizer stkOptionalScreens = new StringTokenizer(getApplicationString("logic.optional.screens"), ",");
				while(stkOptionalScreens.hasMoreElements())
				{
					list.remove(stkOptionalScreens.nextToken());
				}
				
				((ArrayList)list).trimToSize();    
			}
		}
		return argumentsSpecified;
	}

	/**
	 * Insert new actions into the work flow.  
	 * @param actionToInsert - The name of the new action to be inserted
	 * @param insertAfter - The existing action the new action will follow behind
	 * @param actionList - The list of actions representing the work flow
	 * @return
	 */
	public List insertAction(String actionToInsert, String insertAfter, List actionList)
	{
		if(!actionList.contains(actionToInsert) && actionList.contains(insertAfter))
		{
			int x = actionList.lastIndexOf(insertAfter);
			actionList.add(++x , actionToInsert);
		}
		
		return actionList;
	}

	/**
	 * Return true if the passed paremeter has the significant characters represent 'yes' </b>
	 * <li>yes</li>
	 * <li>Ye </li>
	 * <li>Y  </li>
	 * @param parmeter The argment to check for 'yes'
	 * @return
	 */
	public boolean isYes(String parmeter)
	{
		return (YES.substring(0,allPages.length()).equalsIgnoreCase(allPages));
	}
	
	/**
	 * @return the debugSkipValidation
	 */
	public String getDebugSkipValidation() {
		return debugSkipValidation;
	}


	/**
	 * @param debugValidation the debugSkipValidation to set
	 */
	public void setDebugSkipValidation(String debugSkipValidation) {
		this.debugSkipValidation = debugSkipValidation;
	}


	/**
	 * @return the allPages
	 */
	public String getAllPages() {
		return allPages;
	}


	/**
	 * @param allPages the allPages to set
	 */
	public void setAllPages(String allPages) {
		this.allPages = allPages;
	}

}
