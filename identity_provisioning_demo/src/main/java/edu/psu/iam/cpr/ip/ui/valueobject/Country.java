/* SVN FILE: $Id: Country.java 4503 2012-08-02 12:54:25Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.valueobject;

/**
 * Country represents one country, and it's 2-character code. 
 * The country codes are taken from ISO 3166.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 4503 $
 * @lastrevision $Date: 2012-08-02 08:54:25 -0400 (Thu, 02 Aug 2012) $
 */
public class Country 
{
	private String country;
	private String countryCode;
	private String selected;
	/**
	 * @param country
	 * @param countryCode
	 */
	public Country(String countryCode, String country) 
	{
		super();
		this.country    = country;
		this.countryCode = countryCode;
		this.selected    = "";
	}
    /**	
	 * @param country
	 * @param countryCode
	 * @param selected
	 */
	public Country(String countryCode, String country, String selected) 
	{
		super();
		this.country    = country;
		this.countryCode = countryCode;
		this.selected    = selected;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
