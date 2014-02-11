/* SVN FILE: $Id: State.java 4507 2012-08-02 13:29:56Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.valueobject;

/**
 * State represents one state, it's 2-character code, and optionally a selected attribute. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 4507 $
 * @lastrevision $Date: 2012-08-02 09:29:56 -0400 (Thu, 02 Aug 2012) $
 */
public class State 
{
	private String state;
	private String stateCode;
	private String selected;
	/**
	 * @param state
	 * @param stateCode
	 */
	public State(String state, String stateCode) 
	{
		super();
		this.state    = state;
		this.stateCode = stateCode;
		this.selected    = "";
	}
    /**	
	 * @param state
	 * @param stateCode
	 * @param selected
	 */
	public State(String state, String stateCode, String selected) 
	{
		super();
		this.state    = state;
		this.stateCode = stateCode;
		this.selected    = selected;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the selected
	 */
	public String getSelected() {
		return selected;
	}
	/**
	 * @param selected the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}
}
