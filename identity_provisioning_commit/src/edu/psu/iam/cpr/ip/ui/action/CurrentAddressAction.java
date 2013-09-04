/* SVN FILE: $Id: CurrentAddressAction.java 6110 2013-02-01 04:44:33Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.soap.SoapClientIP;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.util.MapHelper;
/**
 * CurrentAddressAction is responsible for acquiring the current address of  
 * the student.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 6110 $
 * @lastrevision $Date: 2013-01-31 23:44:33 -0500 (Thu, 31 Jan 2013) $
 */
public class CurrentAddressAction extends AddressBaseAction 
{
	
	private String defaultAlternateAddress;
	
	private List<String> alternateAddressList;
	
	private String alternateAddressValue;
	
	private String alternateRadio;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="current_address",results={ @Result(name=SUCCESS,location="/contact_info",type=REDIRECT),
            @Result(name="Welcome"       ,location="/welcome"         ,type=REDIRECT),
            @Result(name="DataAccuracy"  ,location="/data_accuracy"   ,type=REDIRECT),
            @Result(name="LegalName"     ,location="/legal_name"      ,type=REDIRECT),
            @Result(name="CurrentAddress",location="/current_address" ,type=REDIRECT),
            @Result(name="AlternateAddress",location="/alternate_address" ,type=REDIRECT),
            @Result(name="ContactInfo"   ,location="/contact_info"    ,type=REDIRECT),
            @Result(name="PersonalInfo"  ,location="/personal_info"   ,type=REDIRECT),
            @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),
            @Result(name="VerifyInfo"    ,location="/verify_info"     ,type=REDIRECT),
			                                  @Result(name="stay on page",location="/jsp/currentAddress.jsp"),
					                          @Result(name="verify",location="/verify_info",type=REDIRECT),
                                              @Result(name="failure",location="/jsp/endPage.jsp")
                                            })
	public String execute() 
	{
		setPrefix("cra");
		
		String nextPage = super.execute();
		
		if ("Yes".equals(getAlternateAddressValue())) {
			System.out.println("Yes was selected!!!");
		}
		else if ("No".equals(getAlternateAddressValue())) {
			System.out.println("No was selected!!");
		}
		
		if ("true".equals(getAlternateRadio())) {
			nextPage = "AlternateAddress";
		}
		
		return nextPage;
	}

	@Override
	/**
	 * This routine will determine whether or not dependent fields [on this screen] have been entered
	 * @return STAY_ON_PAGE if dependent fields are missing, else return null
	 */
	public String dependencyCheck() 
	{
		String returnLocation = null; 
		
		// Current address requires the following fields.  
		if ("USA".equals(getCountry())) {
			if(FieldUtility.fieldIsNotPresent(getCountry(), getAddressLine1(), getCity(), getState(), getPostalCode())) {
				returnLocation =  STAY_ON_PAGE;
			}
		}
		else {
			if(FieldUtility.fieldIsNotPresent(getCountry(), getAddressLine1(), getCity())) {
				returnLocation =  STAY_ON_PAGE;
			}
		}
		return returnLocation;
	}

	@Override
	/**
	 * Current Address does not need to do anything for special country check, handling.
	 * This method should only be overridden for address other than Current Address
	 */
	public void countryCheck(String returnLocation) 	{ }

	/**
	 * @param alternateRadio the alternateRadio to set
	 */
	public void setAlternateRadio(String alternateRadio) {
		this.alternateRadio = alternateRadio;
	}

	/**
	 * @return the alternateRadio
	 */
	public String getAlternateRadio() {
		return alternateRadio;
	}

	public String getDefaultAlternateAddress() {
		return defaultAlternateAddress;
	}

	public void setDefaultAlternateAddress(String defaultAlternateAddress) {
		this.defaultAlternateAddress = defaultAlternateAddress;
	}

	public List<String> getAlternateAddressList() {
		return alternateAddressList;
	}

	public void setAlternateAddressList(List<String> alternateAddressList) {
		this.alternateAddressList = alternateAddressList;
	}
	
	public CurrentAddressAction() {
        HashMap<String, String> argStringMap = MapHelper.genericObjToStringHashMap(getSessionMap());
        if (SoapClientIP.mapValueIsEmpty(argStringMap.get(UIConstants.ALT_ADDRESS_LINE1)) &&
        		SoapClientIP.mapValueIsEmpty(argStringMap.get(UIConstants.ALT_CITY))) {
        	setDefaultAlternateAddress("No");
        }
        else {
        	setDefaultAlternateAddress("Yes");
        }
		alternateAddressList = new ArrayList<String>();
		alternateAddressList.add("Yes");
		alternateAddressList.add("No");
	}

	/**
	 * @return the alternateAddressValue
	 */
	public String getAlternateAddressValue() {
		return alternateAddressValue;
	}

	/**
	 * @param alternateAddressValue the alternateAddressValue to set
	 */
	public void setAlternateAddressValue(String alternateAddressValue) {
		this.alternateAddressValue = alternateAddressValue;
	}

}
