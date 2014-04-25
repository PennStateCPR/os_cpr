/* SVN FILE: $Id: SecurityQuestion2Action.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.MagicNumber;

/**
 * SecurityQuestion2Action handles the returning user who was found, in the system,
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

public class SecurityQuestion2Action extends SecurityQuestionBaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value="sq2",results={ 
			@Result(name=SUCCESS,location="/sq3",type=REDIRECT),
            @Result(name="stay on page",location="/jsp/sq2.jsp"),
			@Result(name="verify",location="/verify_info",type=REDIRECT),
            @Result(name="failure",location="/jsp/endPage.jsp")
            })
         
    /**
     *  Execute all of the common logic for Security Question Answers, when each answer is a separate page                                        
     */
	public String execute()
	{
		setPrefix("sq2");
		return super.execute();
	}
	
	@Override
	public String getGroupId() 
	{
		return Integer.toString(MagicNumber.I2);
	}

}
