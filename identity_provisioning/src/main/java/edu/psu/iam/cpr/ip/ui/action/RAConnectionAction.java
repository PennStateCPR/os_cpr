/* SVN FILE: $Id: RAConnectionAction.java 6113 2013-02-01 08:59:19Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.core.authentication.AuthenticateService;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.proxy.ProxyDb;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * RAConnectionAction the initial landing URL for Registration Authorities.  
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 6113 $
 * @lastrevision $Date: 2013-02-01 03:59:19 -0500 (Fri, 01 Feb 2013) $
 */
public class RAConnectionAction extends BaseAction  
{

	private static final String ACTION = "action";
    private static final long serialVersionUID = 8600053409774294118L;

    private String principalId ;   // RA must send this on ra_connect
	private String password    ;   // RA must send  "" "" ""
	private String requestedBy ;   // For Identity Provisioning is not necessary, will be used when users are logged in
	private String sitename    ;   
	private String ra          ;   // Registration Authority Name
	private String raReferrer  ;   // Return to URL
	private String assignUserId;   // If blank in post, then use from properties file
	private String assignPsuId ;   // if blank is post, then use from properties file
	private String homeURL     ;   // Home URL for RA
	private String showSSN     ;   // RA Switch to show SSN, or not
	private String ssnMessage  ;   // SSN is required to be collected, etc..
	private String raMessage   ;   // Please create an online identity for the applicant
	private String emailMessage;   // By clicking this box, you are granting permission...
	private String raScreens   ;   // The property name of the RA Screens order/name table
	private String screenCount ;   // Total Number of screens the user will view
	private String screenNumber;   // Present # of screen within screenCount
	
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="ra_connect",results={ @Result(name=SUCCESS         ,location="/welcome",type=REDIRECT),
							             @Result(name="Welcome"       ,location="/welcome",type=REDIRECT),
                                         @Result(name="failure",location="/jsp/endPage.jsp")
                                     })
	public String execute() throws CprException, NamingException  
	{
		// Authenticate the RA against LDAP using the posted attributes
		setup("rac");
		log.debug(String.format("%s ", getUniqueId()))  ;

		// If a preceding JSP set the parameters, which means the URL only included
		// the parameter 'ra' (for security reasons) let's get the other parameters from the request object
		// where the JSP set them.
		if(FieldUtility.fieldIsPresent(ra) && FieldUtility.fieldIsNotPresent(principalId))
		{
			
			principalId = (String) this.getHttpServletRequest().getAttribute("principalId");
			password    = (String) this.getHttpServletRequest().getAttribute("password");
			requestedBy = (String) this.getHttpServletRequest().getAttribute("requestedBy");
			sitename    = (String) this.getHttpServletRequest().getAttribute("sitename");
		}
		
		String returnLocation = "success";
		
		String referer = this.getHttpServletRequest().getHeader("Referer");
		
		getSessionMap().put(getPrefix() +".raURL", referer);
		getSessionMap().put(UIConstants.RAC_PRINCIPAL_ID, principalId);
		getSessionMap().put(UIConstants.RAC_PASSWORD    , password);
		
		
		// Authenticate the RA
		{
			AuthenticateService.authenticate(principalId, password);
			this.getSessionMap().put(UIConstants.RAC_AUTHENTICATED, "true");
			log.info(String.format("%s RaConnection - user (%s) has been authenticated", getUniqueId(),principalId));
			
			// Get RA Properties
			String appName = (String)getApplicationMap().get(UIConstants.UI_APPLICATION_NAME);
			Map<String, String> RAProperties = ProxyDb.loadRAPropertiestFromDb(getApplicationMap(), appName, principalId);
			
			if(RAProperties == null)
			{
				throw new CprException(ReturnType.GENERAL_DATABASE_EXCEPTION,"Unable to obtain a database connection from the connection pool");
			}
			assignUserId = RAProperties.get(UIConstants.RA_CREATE_USER_ID);  
			assignPsuId  = RAProperties.get(UIConstants.RA_CREATE_PSUID  );
			ra           = RAProperties.get(UIConstants.RA_NAME          );
			raReferrer   = RAProperties.get(UIConstants.RA_RETURN_URL    );
			homeURL      = RAProperties.get(UIConstants.RA_HOME_URL      );
			
			// If posted form field ['requestedBy'] is not present, then get from database
			if(FieldUtility.fieldIsNotPresent(requestedBy))
				requestedBy  = RAProperties.get(UIConstants.RA_REQUESTED_BY  );
			
			showSSN      = RAProperties.get(UIConstants.RA_SHOW_SSN      );
			ssnMessage   = RAProperties.get(UIConstants.RA_SSN_MSG       );
			raMessage    = RAProperties.get(UIConstants.RA_MSG           );
			emailMessage = RAProperties.get(UIConstants.RA_EMAIL_MSG     );
			raScreens    = String.format("%s.%s.%s", UIConstants.IN_CORE_RA_SCREEN_KEY_PREFIX, appName, principalId);
			
			
			// Put application/principal screen order into memory
			List<String> RAScreens = ProxyDb.loadRAScreens(getApplicationMap(), appName, principalId);
			log.info(String.format("%s Screen Map/Order-> %s", getUniqueId(), RAScreens));
			
			// Front-end needs a list of screens/actions in the order they will be executed, for this RA
			getSessionMap().put(UIConstants.RA_SCREEN_ORDER, RAScreens.toString());
			
			// Produce a List of screens for the front-end with 'true' or 'false' by them
			for(String screenName: RAScreens)
			{
				getSessionMap().put(String.format("%s.%s", ACTION, screenName), "true");
			}
			
			// Total Number of Screens to process for this RA 
			screenCount =  (String) getSessionMap().put(UIConstants.TOTAL_SCREENS_FOR_RA, getApplicationString(UIConstants.RA_TOTAL_SECREENS)); 
		} 

		
		// Save form data to session
		if(returnLocation.equals(SUCCESS))
		{
			saveFieldsToSession(getPrefix());
		}
		
		return filterSuccess(returnLocation);
	}

	/**
	 * @return the principalId
	 */
	public String getPrincipalId() {
		return principalId;
	}

	/**
	 * @param principalId the principalId to set
	 */
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the assignUserid
	 */
	public String getAssignUserId() {
		return assignUserId;
	}

	/**
	 * @param assignUserid the assignUserid to set
	 */
	public void setAssignUserId(String assignUserId) {
		this.assignUserId = assignUserId;
	}

	/**
	 * @return the assignPsuId
	 */
	public String getAssignPsuId() {
		return assignPsuId;
	}

	/**
	 * @param assignPsuId the assignPsuId to set
	 */
	public void setAssignPsuId(String assignPsuId) {
		this.assignPsuId = assignPsuId;
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
	 * @return the showSSN
	 */
	public String getShowSSN() {
		return showSSN;
	}

	/**
	 * @param showSSN the showSSN to set
	 */
	public void setShowSSN(String showSSN) {
		this.showSSN = showSSN;
	}

	/**
	 * @return the ssnMessage
	 */
	public String getSsnMessage() {
		return ssnMessage;
	}

	/**
	 * @param ssnMessage the ssnMessage to set
	 */
	public void setSsnMessage(String ssnMessage) {
		this.ssnMessage = ssnMessage;
	}

	/**
	 * @return the raMessage
	 */
	public String getRaMessage() {
		return raMessage;
	}

	/**
	 * @param raMessage the raMessage to set
	 */
	public void setRaMessage(String raMessage) {
		this.raMessage = raMessage;
	}

	/**
	 * @return the emailMessage
	 */
	public String getEmailMessage() {
		return emailMessage;
	}

	/**
	 * @param emailMessage the emailMessage to set
	 */
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	/**
	 * @return the raScreens
	 */
	public String getRaScreens() {
		return raScreens;
	}

	/**
	 * @param raScreens the raScreens to set
	 */
	public void setRaScreens(String raScreens) {
		this.raScreens = raScreens;
	}

	/**
	 * @return the screenCount
	 */
	public String getScreenCount() {
		return screenCount;
	}

	/**
	 * @param screenCount the screenCount to set
	 */
	public void setScreenCount(String screenCount) {
		this.screenCount = screenCount;
	}

	/**
	 * @return the screenNumber
	 */
	public String getScreenNumber() {
		return screenNumber;
	}

	/**
	 * @param screenNumber the screenNumber to set
	 */
	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	/**
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
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
