/* SVN FILE: $Id: CurrentAddressAction.java 6110 2013-02-01 04:44:33Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** No flag constant value */
	private static final String NO_FLAG = "No";

	/** Yes flag constant value */
	private static final String YES_FLAG = "Yes";

	/** contains the value for the starting default alternate address, will be set to Yes, if there is one */
	private String defaultAlternateAddress;
	
	/** Contains the list of alternate address values Yes/No. */
	private List<String> alternateAddressList;
	
	/** Contains the user selected alternate address value */
	private String alternateAddressFlag;
	
	/**
	 * Constructor.
	 */
	public CurrentAddressAction() {
		
		// Initalize the alternate address default value, based on whether there is an alternate address value.
        HashMap<String, String> argStringMap = MapHelper.genericObjToStringHashMap(getSessionMap());
        if (YES_FLAG.equals(argStringMap.get(UIConstants.CRA_ALTERNATE_ADDRESS_FLAG))) {
        	setDefaultAlternateAddress(YES_FLAG);
        }
        else {
        	setDefaultAlternateAddress(NO_FLAG);
        }
		alternateAddressList = new ArrayList<String>();
		alternateAddressList.add(YES_FLAG);
		alternateAddressList.add(NO_FLAG);
	}

	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="current_address",results={ 
			@Result(name=SUCCESS, location="/contact_info", type=REDIRECT),
            @Result(name="AlternateAddress", location="/alternate_address", type=REDIRECT),
            @Result(name="ContactInfo", location="/contact_info", type=REDIRECT),
            @Result(name="VerifyInfo", location="/verify_info", type=REDIRECT),
			@Result(name="stay on page", location="/jsp/currentAddress.jsp"),
			@Result(name="verify", location="/verify_info", type=REDIRECT),
            @Result(name="failure", location="/jsp/endPage.jsp")
            })
	public String execute() 
	{
		setPrefix("cra");
		
		String nextPage = super.execute();
		
		Map<String, Object> session = getSessionMap();
		session.put(UIConstants.CRA_ALTERNATE_ADDRESS_FLAG, getAlternateAddressFlag());
		
		if (YES_FLAG.equals(getAlternateAddressFlag())) {
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
	
	/**
	 * @return the alternateAddressFlag
	 */
	public String getAlternateAddressFlag() {
		return alternateAddressFlag;
	}

	/**
	 * @param alternateAddressFlag the alternateAddressFlag to set
	 */
	public void setAlternateAddressFlag(String alternateAddressFlag) {
		this.alternateAddressFlag = alternateAddressFlag;
	}

}
