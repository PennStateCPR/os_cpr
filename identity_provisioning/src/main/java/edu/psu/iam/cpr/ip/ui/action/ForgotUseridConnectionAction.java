package edu.psu.iam.cpr.ip.ui.action;

import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.core.error.CprException;

/**
 * Entry point for the forgot userid flow.
 * 
 * @author jvuccolo
 *
 */
public class ForgotUseridConnectionAction extends RAConnectionAction {
	private static final long serialVersionUID = 1L;

	@Override
	@Action(value="forgot_userid_connect",results={ @Result(name=SUCCESS, location="/forgot_userid", type=REDIRECT),
                                         			@Result(name="failure", location="/jsp/endPage.jsp")
                                     			  })
	public String execute() throws CprException, NamingException  
	{
		// Authenticate the RA against LDAP using the posted attributes
		super.execute();
		return SUCCESS;
	}


}
