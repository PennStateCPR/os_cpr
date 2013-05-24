/* SVN FILE: $Id: FormerNameAction.java 7362 2013-05-23 12:32:05Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;
/**
 * FormerNameAction processes the former legal name of a user.
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

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
public class FormerNameAction extends NameBaseAction 
{
	/**
	 * Check for any required fields -- they must have data in them, else we stay on the current page
	 * Check the fields passed to see if they have data in them
	 * @return A string field indicating whether or not we should stay on this page, or null if we can progress
	 */
	@Override
	@Action(value="former_name",results={ @Result(name=SUCCESS,location="/current_address",type=REDIRECT),
            @Result(name="Welcome"       ,location="/welcome"         ,type=REDIRECT),
            @Result(name="DataAccuracy"  ,location="/data_accuracy"   ,type=REDIRECT),
            @Result(name="LegalName"     ,location="/legal_name"      ,type=REDIRECT),
            @Result(name="CurrentAddress",location="/current_address" ,type=REDIRECT),
            @Result(name="ContactInfo"   ,location="/contact_info"    ,type=REDIRECT),
            @Result(name="PersonalInfo"  ,location="/personal_info"   ,type=REDIRECT),
            @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),
            @Result(name="VerifyInfo"    ,location="/verify_info"     ,type=REDIRECT),
			                              @Result(name="stay on page",location="/jsp/formerName.jsp"),
			                              @Result(name="verify",location="/verify_info",type=REDIRECT),
                                          @Result(name="failure",location="/jsp/endPage.jsp")
                                        })
                                     
    /**
     *  Execute all of the common logic for LegalName, FormerName                                        
     */
	public String execute()
	{
		setPrefix("fmr");
		LOG.info("inside newer former name without the 2");
		return super.execute();
	}
	
	/**
	 * This routine will determine whether or not dependent fields [on this screen] have been entered
	 * @return STAY_ON_PAGE if dependent fields are missing, else return null
	 */
	public String dependencyCheck() 
	{
		// Note:  FormerName has no form field requirements/dependencies, so 'null' is returned always
		String returnLocation = null; 

		return returnLocation;
	}
}
