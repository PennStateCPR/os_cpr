/* SVN FILE: $Id: ScreenUI.java 6489 2013-03-13 16:16:03Z dvm105 $ */
package edu.psu.iam.cpr.core.ui;

import java.util.List;
import java.util.Iterator;

/**
 * ScreenUI is an object class for transporting the necessary screen data from the database to the UI application
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database
 * @author $Author: dvm105 $
 * @version $Rev: 6489 $
 * @lastrevision $Date: 2013-03-13 12:16:03 -0400 (Wed, 13 Mar 2013) $
 */
public class ScreenUI {
	
	public ScreenUI(final String screenName, final List<FieldUI> fieldList) {
		super();
		this.screenName = screenName;
		this.fieldList = fieldList;
	}

	public ScreenUI() {
		super();
	}

	private String screenName;
	private List<FieldUI> fieldList;

	public void setScreenName(final String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setFieldList(final List<FieldUI> fieldList) {
		this.fieldList = fieldList;
	}

	public List<FieldUI> getFieldList() {
		return fieldList;
	}
	
	public String toString() {
		final StringBuilder resultString = new StringBuilder(screenName);
		resultString.append('[');
		// loop through field list and add individual
		final Iterator<FieldUI> it = fieldList.iterator();
		while (it.hasNext()) {
			FieldUI currentField = it.next();
			resultString.append(currentField.toString() + ",");
		}
		final int i = resultString.lastIndexOf(",");
		if (i > 0) {
			resultString.setCharAt(i, ']');
		} else {
			resultString.append(']');
		}
		return resultString.toString();
	}
	

}
