/* SVN FILE: $Id: PolicyAction.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.MagicNumber;
import edu.psu.iam.cpr.ip.ui.helper.RegexHelper;
import edu.psu.iam.cpr.ip.ui.navigation.Navigation;
import edu.psu.iam.cpr.ip.ui.soap.SoapClientIP;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.util.MapHelper;

/**
 * PolicyAction Accept the students decision to accept the university policy statement.  
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
public class PolicyAction extends BaseAction 
{
	private String agree;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="policy_info",results={ @Result(name=SUCCESS,location="/security_questions",type=REDIRECT),
			                               @Result(name="stay on page",location="/jsp/policyStatement.jsp"),
                                           @Result(name="failure",location="/jsp/endPage.jsp")
                                          })
	public String execute() 
	{
		if(!setup("pol"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		String returnLocation = SUCCESS;
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		// TODO Business Logic
		if(FieldUtility.fieldIsNotPresent(agree) || agree.equalsIgnoreCase("false"))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		/* If the person was not found in verify, and they have agreed to the policy -- then 'addPerson' */
		if(returnLocation.equalsIgnoreCase(SUCCESS) && ((String)getSessionMap().get("vfy.person.found")).equalsIgnoreCase("no"))
		{
			HashMap<String, String> argStringMap = MapHelper.genericObjToStringHashMap(getSessionMap());
			Map<String, String> status = SoapClientIP.addPerson(argStringMap, getUniqueId()); 
			log.info(String.format("%s returnStatus from addPerson--> %s ", getUniqueId(), status));
				
			log.info(String.format("%s statuscode from addPerson[%s] ", getUniqueId(), Integer.parseInt(status.get("statusCode"))));
			switch(Integer.parseInt(status.get("statusCode")))
			{
			   /* 0: Send these id(s) to the success page */
			   case 0:  
				        getSessionMap().put("suc.personId", status.get("srv.personId"));  
			            getSessionMap().put("suc.psuId"   , status.get("srv.psuId"));     
			            getSessionMap().put("suc.userId"  , status.get("srv.userId"));    
			            getSessionMap().put("formatted.university.id"
			        		              , RegexHelper.formatUniversityId(getApplicationMap(), status.get("srv.psuId")));
			            Navigation.lock(getSessionMap());          
				  	    break;

			/* We think we found you */	  	   
			   case MagicNumber.I3: 
				        setEndPageHeader(getApplicationString("ui.near.match.header")); 
				   	    addActionMessage(getApplicationString("ui.near.match.message1"));
					    addActionMessage(getApplicationString("ui.near.match.message2"));
					    addReferenceNumber();
					    returnLocation = FAILURE;
					    break;
			}
		}

		
		saveFieldsToSession(getPrefix());
		
		return returnLocation;
	}
	

	/**
	 * @return the agree
	 */
	public String getAgree() {
		return agree;
	}

	/**
	 * @param agree the agree to set
	 */
	public void setAgree(String agree) {
		this.agree = agree;
	}



}
