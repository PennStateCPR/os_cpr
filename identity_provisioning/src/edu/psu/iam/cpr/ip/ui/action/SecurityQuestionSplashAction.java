/* SVN FILE: $Id: SecurityQuestionSplashAction.java 5829 2012-12-10 12:01:10Z jal55 $ */  
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * SecurityQuestionSplashAction is an information page for security questions whent the applicant 
 * has been found, and is prompted to correctly answer their previously answered security questions.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5829 $
 * @lastrevision $Date: 2012-12-10 07:01:10 -0500 (Mon, 10 Dec 2012) $
 */
public class SecurityQuestionSplashAction extends BaseAction 
{
	@Override
	@Action(value="sq0",results={ 
			                      @Result(name=SUCCESS,location="/sq1",type=REDIRECT),
                                  @Result(name="stay on page",location="/jsp/sq0.jsp"),
                                  @Result(name="failure",location="/jsp/endPage.jsp")
          })
	public String execute() 
	{
		// Present a splash/info page for the answering of security questions, when a person is found
		// from a previous registration.  We are in reset mode, not 'set' mode
		if(!setup("sq0"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		String returnLocation = SUCCESS;
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation = STAY_ON_PAGE;
		}
		
		return filterSuccess(returnLocation);
	}

}
