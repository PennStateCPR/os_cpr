/* SVN FILE: $Id: SecurityQuestion3Action.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.MagicNumber;

/**
 * SecurityQuestion3Action handles the returning user who was found, in the system,
 * and now they must answer/match their previously recorded security questions. 
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

public class SecurityQuestion3Action extends SecurityQuestionBaseAction 
{
    private static final long serialVersionUID = -3449686981945926727L;

    @Action(value="sq3",results={
			@Result(name=SUCCESS,location="/password",type=REDIRECT),
            @Result(name="Welcome"       ,location="/welcome"         ,type=REDIRECT),
            @Result(name="DataAccuracy"  ,location="/data_accuracy"   ,type=REDIRECT),
            @Result(name="LegalName"     ,location="/legal_name"      ,type=REDIRECT),
            @Result(name="CurrentAddress",location="/current_address" ,type=REDIRECT),
            @Result(name="ContactInfo"   ,location="/contact_info"    ,type=REDIRECT),
            @Result(name="PersonalInfo"  ,location="/personal_info"   ,type=REDIRECT),
            @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),
			                                   @Result(name="stay on page",location="/jsp/sq3.jsp"),
					                           @Result(name="verify",location="/verify_info",type=REDIRECT),
                                               @Result(name="failure",location="/jsp/endPage.jsp")
                                            })
    /**
     *  Execute all of the common logic for Security Question Answers, when each answer is a separate page                                        
     */                                        
	public String execute()
	{
		setPrefix("sq3");
		return super.execute();
	}
	
	@Override
	public String getGroupId() 
	{
		return Integer.toString(MagicNumber.I3);
	}

}