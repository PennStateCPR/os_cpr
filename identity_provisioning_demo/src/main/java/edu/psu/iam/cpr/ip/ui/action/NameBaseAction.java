/* SVN FILE: $Id: NameBaseAction.java 6102 2013-01-30 15:09:55Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * NameBaseAction is a base class for LegalName, and FormerName Action.
 * It reduces the duplication of code 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 6102 $
 * @lastrevision $Date: 2013-01-30 10:09:55 -0500 (Wed, 30 Jan 2013) $
 */
public abstract class NameBaseAction extends BaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String middleNames;
	private String lastName;
	private String nameType;
	private String suffix;
	private String nickName;

	@Override
	public String execute() 
	{
		if(!setup(getPrefix()))
		{
			return FAILURE;
		}
		
		log.info(String.format("%s ", getUniqueId()))  ;
		
		nameType = AccessType.LEGAL_NAME.toString();
		
		String returnLocation = SUCCESS;

		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		String tempReturnLocation = dependencyCheck();
		if(tempReturnLocation != null)
		{
			returnLocation = tempReturnLocation;
		}
	
		saveFieldsToSession(getPrefix());

		return filterSuccess(returnLocation);
	}
	
	/**
	 * Perform any field dependency [do required fields contain data] checks
	 * @return STAY_ON_PAGE if dependent fields are missing, else return null
	 */
	abstract public String dependencyCheck();

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleNames
	 */
	public String getMiddleNames() {
		return middleNames;
	}

	/**
	 * @param middleNames the middleNames to set
	 */
	public void setMiddleNames(String middleNames) {
		this.middleNames = middleNames;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the type
	 */
	public String getNameType() {
		return nameType;
	}


	/**
	 * @param type the type to set
	 */
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}


	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}


	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

}
