/**
 * 
 */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.util.MapHelper;

/**
 * @author jal55
 *
 */
public class LegalNameAction extends NameBaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Contains the default Suffix based on user input.
	 */
	private String defaultSuffix;
	
	/**
	 * Contains the list of the possible suffixes.
	 */
	private List<String> suffixList;

	public LegalNameAction() {
		@SuppressWarnings("unchecked")
		HashMap<String, String> argStringMap = MapHelper.genericObjToStringHashMap(getSessionMap());
		setDefaultSuffix(argStringMap.get(UIConstants.LNA_SUFFIX));
		
		suffixList = new ArrayList<String>();
		suffixList.add("");
		suffixList.add("I");
		suffixList.add("II");
		suffixList.add("III");
		suffixList.add("IV");
		suffixList.add("V");
		suffixList.add("VI");
		suffixList.add("VII");
		suffixList.add("VIII");
		suffixList.add("IX");
		suffixList.add("X");
		suffixList.add("Sr.");
		suffixList.add("Jr.");
		suffixList.add("Esq.");
		suffixList.add("MM");
	}
	
	/**
	 * Check for any required fields -- they must have data in them, else we stay on the current page
	 * Check the fields passed to see if they have data in them
	 * @return A string field indicating whether or not we should stay on this page, or null if we can progress
	 */
	@Override
	@Action(value="legal_name",results={ 
			@Result(name=SUCCESS, location="/contact_info", type=REDIRECT),
            @Result(name="ContactInfo",location="/contact_info", type=REDIRECT),
            @Result(name="VerifyInfo",location="/verify_info", type=REDIRECT),
			@Result(name="stay on page", location="/jsp/legalName.jsp"),
			@Result(name="verify", location="/verify_info", type=REDIRECT),
            @Result(name="failure", location="/jsp/endPage.jsp")
    })
    
    /**
     *  Execute all of the common logic for LegalName, FormerName                                        
     */
	public String execute()
	{
		setPrefix("lna");
		String returnLocation = super.execute();
		if ("CurrentAddress".equals(returnLocation)) {
			returnLocation = "ContactInfo";
		}
		return returnLocation;
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

	/**
	 * @return the defaultSuffix
	 */
	public String getDefaultSuffix() {
		return defaultSuffix;
	}

	/**
	 * @param defaultSuffix the defaultSuffix to set
	 */
	public void setDefaultSuffix(String defaultSuffix) {
		this.defaultSuffix = defaultSuffix;
	}

	/**
	 * @return the suffixList
	 */
	public List<String> getSuffixList() {
		return suffixList;
	}

	/**
	 * @param suffixList the suffixList to set
	 */
	public void setSuffixList(List<String> suffixList) {
		this.suffixList = suffixList;
	}
}
