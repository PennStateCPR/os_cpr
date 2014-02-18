/* SVN FILE: $Id: WelcomeAction.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * WelcomeAction is the initial visible screen for end-users after they have been   
 * connected via a registration authority.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5953 $
 * @lastrevision $Date: 2012-12-20 17:21:58 -0500 (Thu, 20 Dec 2012) $
 */
public class WelcomeAction extends BaseAction  
{
    private static final long serialVersionUID = 6521263535647612868L;

    /* (non-Javadoc)
         * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
         */
	@Override
	@Action(value="welcome",results={ @Result(name=SUCCESS         ,location="/data_accuracy"   ,type=REDIRECT), 
						              @Result(name="Welcome"       ,location="/welcome"         ,type=REDIRECT),
						              @Result(name="DataAccuracy"  ,location="/data_accuracy"   ,type=REDIRECT),
						              @Result(name="LegalName"     ,location="/legal_name"      ,type=REDIRECT),
						              @Result(name="CurrentAddress",location="/current_address" ,type=REDIRECT),
						              @Result(name="ContactInfo"   ,location="/contact_info"    ,type=REDIRECT),
						              @Result(name="PersonalInfo"  ,location="/personal_info"   ,type=REDIRECT),
						              @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),
			                          @Result(name="stay on page"  ,location="/jsp/welcome.jsp"),			
                                      @Result(name="failure",location="/jsp/endPage.jsp")
                                     })
	public String execute() 
	{
		if(!setup("wel"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		String returnLocation = SUCCESS;  
		
		if(getBtnsubmit() == null)
		{
			returnLocation =  STAY_ON_PAGE;
		}
		else
		{
			returnLocation =  SUCCESS;
		}

		/** Save the RA's credentials, and other information to session */
		saveFieldsToSession(getPrefix());
		
		return filterSuccess(returnLocation);
 
	}
}
