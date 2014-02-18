/**
 * 
 */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * @author jal55
 *
 */
public class LegalNameAction extends NameBaseAction 
{
    private static final long serialVersionUID = -2669284407045403525L;

    /**
	 * Check for any required fields -- they must have data in them, else we stay on the current page
	 * Check the fields passed to see if they have data in them
	 * @return A string field indicating whether or not we should stay on this page, or null if we can progress
	 */
	@Override
	@Action(value="legal_name",results={ @Result(name=SUCCESS,location="/former_name",type=REDIRECT),
            @Result(name="Welcome"       ,location="/welcome"         ,type=REDIRECT),
            @Result(name="DataAccuracy"  ,location="/data_accuracy"   ,type=REDIRECT),
            @Result(name="LegalName"     ,location="/legal_name"      ,type=REDIRECT),
            @Result(name="CurrentAddress",location="/current_address" ,type=REDIRECT),
            @Result(name="ContactInfo"   ,location="/contact_info"    ,type=REDIRECT),
            @Result(name="PersonalInfo"  ,location="/personal_info"   ,type=REDIRECT),
            @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),
			                             @Result(name="stay on page",location="/jsp/legalName.jsp"),
			                             @Result(name="verify",location="/verify_info",type=REDIRECT),
                                         @Result(name="failure",location="/jsp/endPage.jsp")
                                     })
                                     
    /**
     *  Execute all of the common logic for LegalName, FormerName                                        
     */
	public String execute()
	{
		LOG.info("inside legal name");
		setPrefix("lna");
		return super.execute();
	}
	
	/**
	 * This routine will determine whether or not dependent fields [on this screen] have been entered
	 * @return STAY_ON_PAGE if dependent fields are missing, else return null
	 */
	public String dependencyCheck() 
	{
		String returnLocation = null; 

		if(FieldUtility.fieldIsNotPresent(getFirstName(), getLastName()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		return returnLocation;
	}
}
