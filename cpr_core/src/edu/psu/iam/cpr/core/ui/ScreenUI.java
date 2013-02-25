/* SVN FILE: $Id: ScreenUI.java 5659 2012-11-19 17:24:59Z slk24 $ */
package edu.psu.iam.cpr.core.ui;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ScreenUI is an object class for transporting the necessary screen data from the database to the UI application
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
public class ScreenUI {
	
	public ScreenUI(String screenName, ArrayList<FieldUI> fieldList) {
		super();
		this.screenName = screenName;
		this.fieldList = fieldList;
	}

	public ScreenUI() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String screenName;
	private ArrayList<FieldUI> fieldList;

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setFieldList(ArrayList<FieldUI> fieldList) {
		this.fieldList = fieldList;
	}

	public ArrayList<FieldUI> getFieldList() {
		return fieldList;
	}
	
	public String toString() {
		StringBuilder resultString = new StringBuilder(screenName + "[");
		// loop through field list and add individual
		Iterator<FieldUI> it = fieldList.iterator();
		while (it.hasNext()) {
			FieldUI currentField = (FieldUI)it.next();
			resultString.append(currentField.toString() + ",");
		}
		int i = resultString.lastIndexOf(",");
		if (i > 0) {
			resultString.setCharAt(i, ']');
		} else {
			resultString.append("]");
		}
		return resultString.toString();
	}
	

}
