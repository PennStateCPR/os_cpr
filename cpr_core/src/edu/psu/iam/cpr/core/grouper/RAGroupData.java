/* SVN FILE: $Id: RAGroupData.java 3536 2012-04-02 20:01:39Z jvuccolo $ */


package edu.psu.iam.cpr.core.grouper;

/**
 *  This class provides an implementation for interfacing with the Grouper.  There are methods
 *  here to send REST request to grouper and process the response.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 *  
 * @package edu.psu.iam.cpr.core.grouper
 * @author $Author: jvuccolo $
 * @version $Rev: 3536 $
 * @lastrevision $Date: 2012-04-02 16:01:39 -0400 (Mon, 02 Apr 2012) $
 */
public class RAGroupData {

	/**
	 *  Contains the Group name for the registration authority
	 */
	private String registrationAuthorityGroup;
	
	/**
	 * Contains the suspend status of the registration authority
	 */
	
	private boolean registrationAuthoritySuspendFlag;
	
	/**
	 * @return the registrationAuthorityGroup
	 */
	public String getRegistrationAuthorityGroup() {
		return registrationAuthorityGroup;
	}

	/**
	 * @param registrationAuthorityGroup the registrationAuthorityGroup to set
	 */
	public void setRegistrationAuthorityGroup(String registrationAuthorityGroup) {
		this.registrationAuthorityGroup = registrationAuthorityGroup;
	}

	/**
	 * @return the registrationAuthoritySuspendFlag
	 */
	public boolean isRegistrationAuthoritySuspendFlag() {
		return registrationAuthoritySuspendFlag;
	}

	/**
	 * @param registrationAuthoritySuspendFlag the registrationAuthoritySuspendFlag to set
	 */
	public void setRegistrationAuthoritySuspendFlag(
			boolean registrationAuthoritySuspendFlag) {
		this.registrationAuthoritySuspendFlag = registrationAuthoritySuspendFlag;
	}
	

	/**
	 * 
	 */
	public RAGroupData() {
		super();
		
	}

	/**
	 * @param registrationAuthorityGroup
	 * @param registrationAuthoritySuspendFlag
	 */
	public RAGroupData(String registrationAuthorityGroup,
			boolean registrationAuthoritySuspendFlag) {
		super();
		this.registrationAuthorityGroup = registrationAuthorityGroup;
		this.registrationAuthoritySuspendFlag = registrationAuthoritySuspendFlag;
	}

	}	
