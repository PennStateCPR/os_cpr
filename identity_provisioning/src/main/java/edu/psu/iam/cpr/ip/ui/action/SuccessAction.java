/* SVN FILE: $Id: SuccessAction.java 5979 2013-01-07 03:13:12Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.helper.RegexHelper;
import edu.psu.iam.cpr.ip.ui.helper.SessionDeleteHelper;

/**
 * SuccessAction is the final screen in the provisioning process. 
 * The user is informed of their userid and password, and provided with
 * a link to return to the RA, from which they came.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5979 $
 * @lastrevision $Date: 2013-01-06 22:13:12 -0500 (Sun, 06 Jan 2013) $
 */
public class SuccessAction extends BaseAction 
{
    private static final long serialVersionUID = -6339833336450638348L;
    private String userId;
	private String psuId;
	
	// Registration Authority Name; such as, 'Undergratuate Admissions'
	private String ra;
	
	// RA's home URL -- RA_
	private String homeURL;    
	
	// The location that sent us to this IAM -- RA_RETURN_URL
	private String raReferrer; 
	
	@Override
	@Action(value="success",results={ @Result(name=SUCCESS,location="/jsp/success.jsp"),
                                      @Result(name="stay on page",location="/jsp/success.jsp"),
                                      @Result(name="failure",location="/jsp/endPage.jsp")
           })
	public String execute() 
	{
		if(!setup("suc"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		String returnLocation = STAY_ON_PAGE;
		
		ra         = getSessionString("rac.ra");
		homeURL    = getSessionString("rac.homeURL");
		raReferrer = getSessionString("rac.raReferrer");
		
		psuId = RegexHelper.formatUniversityId( getApplicationMap(), psuId);

		log.info(String.format("%s Request complete", getUniqueId()));
		getSessionMap().put("suc.request.complete","true");
		
		// Invalidate this session in x-seconds
		log.info(String.format("%s initiating session delete", getUniqueId()));
		SessionDeleteHelper.invalidate(this.getHttpServletRequest().getSession(), this.getApplicationMap());
		
		
		return returnLocation;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the psuId
	 */
	public String getPsuId() {
		return psuId;
	}

	/**
	 * @param psuId the psuId to set
	 */
	public void setPsuId(String psuId) {
		this.psuId = psuId;
	}

	/**
	 * @return the ra
	 */
	public String getRa() {
		return ra;
	}

	/**
	 * @param ra the ra to set
	 */
	public void setRa(String ra) {
		this.ra = ra;
	}

	/**
	 * @return the homeURL
	 */
	public String getHomeURL() {
		return homeURL;
	}

	/**
	 * @param homeURL the homeURL to set
	 */
	public void setHomeURL(String homeURL) {
		this.homeURL = homeURL;
	}

	/**
	 * @return the raReferrer
	 */
	public String getRaReferrer() {
		return raReferrer;
	}

	/**
	 * @param raReferrer the raReferrer to set
	 */
	public void setRaReferrer(String raReferrer) {
		this.raReferrer = raReferrer;
	}
}
