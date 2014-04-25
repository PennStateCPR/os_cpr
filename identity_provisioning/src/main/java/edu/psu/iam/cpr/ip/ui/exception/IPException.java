/* SVN FILE: $Id: IPException.java 5957 2013-01-02 16:47:30Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.exception;

/**
 * IPException is a generic IP Exception object 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.exception 
 * @author $Author: jal55 $
 * @version $Rev: 5957 $
 * @lastrevision $Date: 2013-01-02 11:47:30 -0500 (Wed, 02 Jan 2013) $
 */
public class IPException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	private String message = "";
	
	public static enum RETURN_TYPE 
	{
		SESSION_EXPIRATION, 
		REQUEST_COMPLETE  ,
		ALL_TYPES;
	};
	
	/**
	 * Note:  This table must be maintained/synchronized with RETURN_TYPE
	 */
	private static String[] literalReturntype = 
			{ "Your session has expired.",
		      "Credential request has completed",
			  "All types -- leave this as the last string -- it is not used for reporting"
			};
	
	public IPException()
	{
		super();
	}

	public IPException(RETURN_TYPE type) 
	{
		switch(type)
		{
			case SESSION_EXPIRATION: 
			case REQUEST_COMPLETE  :
			case ALL_TYPES         : message = literalReturntype[type.ordinal()];   break;
			default                : message = "Unknown IPException type"       ;   break; 
		}
	}
	
	public IPException(String message) 
	{
		super(message);
	}

	public IPException(Throwable cause) 
	{
		super(cause);
	}

	public IPException(String message, Throwable cause) 
	{
		super(message, cause);
	}
	

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
