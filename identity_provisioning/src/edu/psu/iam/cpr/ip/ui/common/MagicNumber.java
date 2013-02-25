/* SVN FILE: $Id: MagicNumber.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.common;

/**
 * MagicNumber holds the so-called magic number variables. 
 * We can either do it this way, or disable the sonar rule for magic numbers
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.common 
 * @author $Author: jal55 $
 * @version $Rev: 5953 $
 * @lastrevision $Date: 2012-12-20 17:21:58 -0500 (Thu, 20 Dec 2012) $
 */
public final class MagicNumber 
{

	// Magic One 
	public static final int  I1 = 1;
	public static final long L1 = 1;

	// Magic Two
	public static final long L2 = 2;
	public static final int  I2 = 2;
	
	// Magic Three
	public static final int  I3 = 3;
	public static final long L3 = 3;
	
	// Magic 4
	public static final int  I4 = 4;
	
	// Magic 7
	public static final int INT_7 = 7;
	
	// Magic 16
	public static final int INT_16 = 16;
	
	// Magic Number 201
	public static final int INT_201 = 0;
	
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private MagicNumber()  { }

}
