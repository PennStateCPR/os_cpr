/* SVN FILE: $Id: FieldUI.java 5659 2012-11-19 17:24:59Z slk24 $ */
package edu.psu.iam.cpr.core.ui;


/**
 * FieldUI is an object class for transporting the necessary field data from the database to the UI application
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database
 * @author $Author: slk24 $
 * @version $Rev: 5659 $
 * @lastrevision $Date: 2012-11-19 12:24:59 -0500 (Mon, 19 Nov 2012) $
 */

public class FieldUI {
	

	private String fieldName;
	private boolean requiredFlag;
	
	public FieldUI() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FieldUI(String fieldName, boolean requiredFlag) {
		super();
		this.fieldName = fieldName;
		this.requiredFlag = requiredFlag;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setRequiredFlag(boolean requiredFlag) {
		this.requiredFlag = requiredFlag;
	}
	public boolean getRequiredFlag() {
		return requiredFlag;
	}
	
	public String toString() {
		return fieldName + " " + requiredFlag;
	}

}
