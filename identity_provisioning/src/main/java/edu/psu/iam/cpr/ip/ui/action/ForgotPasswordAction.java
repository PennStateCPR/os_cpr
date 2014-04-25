package edu.psu.iam.cpr.ip.ui.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.dao.IdentityProvisioningDAO;
import edu.psu.iam.cpr.ip.ui.soap.SoapClientIP;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * Forgot password action handler.
 * 
 * @author jvuccolo
 *
 */
public class ForgotPasswordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	/** Contains the user's first name */
	private String firstName;
	
	/** Contains the user's last name */
	private String lastName;
	
	/** Contains the user's city */
	private String city;
	
	/** Contains the user's date of birth */
	private String dob;
	
	/** Contains the user's commit id */
	private String commitId;


	/** Key in map for return code from findPerson */
	static final String STATUS_CODE      = "statusCode";
	
	/** Find Person service return codes */
	public static final int PERSON_FOUND            = 0;                 
	public static final int PERSON_NOT_FOUND        = 1;                  
	public static final int PERSON_DATA_ENTRY_ERROR = 2;                 
	public static final int PERSON_FATAL_ERROR      = 3;                 
	
	public static final String DATA_ENTRY_ERRORS1   = "There are data entry error(s).";
	public static final String DATA_ENTRY_ERRORS2	  = "Please review/verify the data entered";
	
	public static final String FATAL_ERROR1_KEY     = "ui.error.unable.to.proceed";  
	public static final String CALL_CUST_SERVICES   = "ui.error.call.cust.services";
	

	@Override
	@Action(value="forgot_password",results={ @Result(name=SUCCESS,location="/security_questions",type=REDIRECT),
			                                  @Result(name="success_security_questions",location="/security_questions",type=REDIRECT),
                                              @Result(name="stay on page",location="/jsp/forgotPassword.jsp"),
                                              @Result(name="failure",location="/jsp/endPage.jsp")
                                            })
	public String execute() 
	{
		String returnLocation = SUCCESS;

		if (! setup("fgot"))
		{
			return FAILURE;
		}
		
		if (FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation = STAY_ON_PAGE;
		}
		
		if (getDob() != null) {
			String[] parts = getDob().split("/");
			setDob(String.format("%02d/%02d/%04d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
		}

		saveFieldsToSession(getPrefix());

		if (returnLocation.equalsIgnoreCase(SUCCESS))
		{
			// Call a special version of find person that operates with limited data.
			Map<String, String> map = SoapClientIP.findPersonForgotUserOrPassword(getSessionMap(), getUniqueId());
			log.debug(String.format("%s check the returned data --> %s", getUniqueId(), map));
			switch(Integer.parseInt(map.get(STATUS_CODE)))
			{
			
			// Person was found!
			case PERSON_FOUND:               
				log.info(String.format("%s the person was FOUND by find person", getUniqueId()));
				
				// Verify that the userid from find person matches the one the user enter.
				if (getCommitId().equals(map.get("srv.userId"))) {
					getSessionMap().put("suc.personId", map.get("srv.personId"));  
					getSessionMap().put("suc.psuId"   , map.get("srv.psuId"));     
					getSessionMap().put("suc.userId"  , map.get("srv.userId"));    
					getSessionMap().putAll(map);                                   
					getSessionMap().put("sec.password.setting", "reset"); 
					getSessionMap().put("vfy.person.found", "yes");
					getSessionMap().put("lna.firstName", getFirstName());
					getSessionMap().put("lna.lastName", getLastName());
					final String emailAddress = IdentityProvisioningDAO.getEmailAddress(map.get("srv.personId"));
					getSessionMap().put("con.email", emailAddress);
					returnLocation = "success_security_questions"; 
				}
				else {
					setEndPageHeader("Please Contact Customer Services");
					addActionMessage("The CommIT Id you entered did not match the one found in the database.");
					addActionMessage(getApplicationString(CALL_CUST_SERVICES));
					addReferenceNumber();
					returnLocation = FAILURE; 
				}
				break;  

			// Person was not found!
			case PERSON_NOT_FOUND:          
				getSessionMap().put("vfy.person.found", "no");
				setEndPageHeader("Please Contact Customer Services");
				addActionMessage("Unable to find a matching record in the database.");
				addActionMessage(getApplicationString(CALL_CUST_SERVICES));
				addReferenceNumber();
				returnLocation = FAILURE; 
				break;                                                 

			// Fatal error.
			case PERSON_FATAL_ERROR:         
			default:                        
				setEndPageHeader("Please Contact Customer Services");
				addActionMessage("Unable to find a matching record in the database.");
				addActionMessage(getApplicationString(CALL_CUST_SERVICES));
				addReferenceNumber();
				returnLocation = FAILURE; 
				break;
			}
		}

		return returnLocation;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the commitId
	 */
	public String getCommitId() {
		return commitId;
	}

	/**
	 * @param commitId the commitId to set
	 */
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

}
