/* SVN FILE: $Id: DataAccuracyAction.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * DataAccuracyAction handles the user's confirmation that information entered   
 * on subsequent screens/interations is factually correct.
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
public class DataAccuracyAction extends BaseAction 
{

	private String statementAgree;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="data_accuracy",results={ @Result(name=SUCCESS,location="/legal_name",type=REDIRECT),
            @Result(name="Welcome"       ,location="/welcome"         ,type=REDIRECT),
            @Result(name="DataAccuracy"  ,location="/data_accuracy"   ,type=REDIRECT),
            @Result(name="LegalName"     ,location="/legal_name"      ,type=REDIRECT),
            @Result(name="CurrentAddress",location="/current_address" ,type=REDIRECT),
            @Result(name="ContactInfo"   ,location="/contact_info"    ,type=REDIRECT),
            @Result(name="PersonalInfo"  ,location="/personal_info"   ,type=REDIRECT),
            @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),
            @Result(name="VerifyInfo"    ,location="/verify_info"     ,type=REDIRECT),
			                                @Result(name="failure",location="/jsp/dataAccuracy.jsp"),
			                                @Result(name="stay on page",location="/jsp/dataAccuracy.jsp"),
				                            @Result(name="verify",location="/verify_info",type=REDIRECT),
                                            @Result(name="failure",location="/jsp/endPage.jsp")
                                            })
	public String execute() 
	{
		String returnLocation = SUCCESS;
		if(!setup("dta"))                  
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;

		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		// TODO Business Logic
		if(FieldUtility.fieldIsNotPresent(statementAgree) || statementAgree.equalsIgnoreCase("false"))
		{
			returnLocation =  STAY_ON_PAGE;
			setUiMessage("You must agree before continuing.");
		}

		
		// Save form data to session
		saveFieldsToSession(getPrefix());
		
		return filterSuccess(returnLocation);
	}

	/**
	 * @return the statementAgree
	 */
	public String getStatementAgree() {
		return statementAgree;
	}

	/**
	 * @param statementAgree the statementAgree to set
	 */
	public void setStatementAgree(String statementAgree) {
		this.statementAgree = statementAgree;
	}



}
