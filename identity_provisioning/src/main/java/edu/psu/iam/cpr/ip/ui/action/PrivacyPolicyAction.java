package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * Forgot password action handler.
 * 
 * @author jvuccolo
 *
 */
public class PrivacyPolicyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private String agree;
	
	
	@Override
	@Action(value="privacy_policy",results={ @Result(name=SUCCESS,location="/security_questions",type=REDIRECT),
			                                 @Result(name="success_security_questions",location="/security_questions",type=REDIRECT),
                                             @Result(name="stay on page",location="/jsp/privacyPolicy.jsp"),
                                             @Result(name="failure",location="/jsp/endPage.jsp")
                                           })
	public String execute() 
	{
		String returnLocation = SUCCESS;

		if (! setup("ppol"))
		{
			return FAILURE;
		}
				
		if (FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation = STAY_ON_PAGE;
		}
		
		if (returnLocation.equalsIgnoreCase(SUCCESS))
		{
			returnLocation = "success_security_questions";
		}
		
		saveFieldsToSession(getPrefix());
		
		return returnLocation;
	}
}
