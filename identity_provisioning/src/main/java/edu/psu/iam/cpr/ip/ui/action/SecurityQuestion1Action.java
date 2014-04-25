/* SVN FILE: $Id: SecurityQuestion1Action.java 5956 2013-01-02 16:47:16Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.MagicNumber;

/**
 * SecurityQuestion1Action handles the returning user who was found, in the system,
 * and now they must answer/match their previously recorded security questions. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5956 $
 * @lastrevision $Date: 2013-01-02 11:47:16 -0500 (Wed, 02 Jan 2013) $
 */

public class SecurityQuestion1Action extends SecurityQuestionBaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value="sq1",results={ 
			@Result(name=SUCCESS,location="/sq2",type=REDIRECT),
			@Result(name="stay on page",location="/jsp/sq1.jsp"),
			@Result(name="verify",location="/verify_info",type=REDIRECT),
            @Result(name="failure",location="/jsp/endPage.jsp")
            })
    /**
     *  Execute all of the common logic for Security Question Answers, when each answer is a separate page                                        
     */
	public String execute()
	{
		setPrefix("sq1");
		return super.execute();
	}
    
	@Override
	public String getGroupId() 
	{
		return Integer.toString(MagicNumber.I1);
	}

}
