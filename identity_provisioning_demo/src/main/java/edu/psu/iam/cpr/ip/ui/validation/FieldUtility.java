/* SVN FILE: $Id: FieldUtility.java 6100 2013-01-29 21:23:16Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * FieldUtility is a static member field utility class <b/> 
 * with methods for manipulating/checking field contents
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.validation 
 * @author $Author: jal55 $
 * @version $Rev: 6100 $
 * @lastrevision $Date: 2013-01-29 16:23:16 -0500 (Tue, 29 Jan 2013) $
 */
public final class FieldUtility 
{
	
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private FieldUtility()  { }
	
   /**
    * check all fields supplied to see if they have data
	* @param names a variable argument list of fields
	* @return 'true' if all fields contain data, else return 'false'
	*/
   public static boolean fieldIsPresent( String... names )
   {
	 /* false means at least one field is null or empty
      * true  means all fields have real data 
      */ 
      boolean isPresent = true;    
		
      // All fields must be present
	  for ( String name : names )
	  {
	     if(name == null || name.trim().length() == 0)
	     {
		    isPresent = false;
	     }
	  }

      return isPresent;
   }
   
   /**
    * Check multiple fields to see if they are empty or null
    * @param names variable parameter list of names
    * @return true if all any field is null or empty, else false
    */
   public static boolean fieldIsNotPresent( String... names)
   {
	   return  (fieldIsPresent(names) == true ? false: true) ;
   }
   
   /**
    * Return a List of name tokens
    * @param names
    * @return
    */
   public static List<String> getNameTokens(String names)
   {
	   ArrayList<String> listNames = new ArrayList<String>();
	   if(FieldUtility.fieldIsPresent(names) )
	   {
		   StringTokenizer stkn = new StringTokenizer(names, " ,");
		   while(stkn.hasMoreTokens())
		   {
			   String aName = stkn.nextToken();
			   if(aName.trim().length() > 0)
			   {
				   listNames.add(aName);
			   }
		   }
		}

	   return listNames;
   }
   
   /**
    * Check significant characters for 'yes' and return if yes is indicated or not
    * @param field
    * @return
    */
   public static boolean isOptionYes(String field)
   {
	   return "yes".substring(0, field.length()).equalsIgnoreCase(field);
   }
   
   /**
    * Check significant characters for 'no' and return if no is indicated or not
    * @param field
    * @return
    */
   public static boolean isOptionNo(String field)
   {
	   return "no".substring(0, field.length()).equalsIgnoreCase(field);
   }
   
	/**
	 * Convert a String to an 'int'
	 * @param strNumber - the String to convert
	 * @return return an 'int' number representing the string, or 0 if the number is not valid
	 */
	public static int convertStringToInt(String strNumber)
	{
		int numericNumber = 0;
		try
		{
			numericNumber =  Integer.parseInt(strNumber);
		}
		catch(NumberFormatException nfe)   {  }
		
		return numericNumber;
		
	}

}