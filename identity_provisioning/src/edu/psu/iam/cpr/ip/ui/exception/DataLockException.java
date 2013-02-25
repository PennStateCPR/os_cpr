package edu.psu.iam.cpr.ip.ui.exception;

public class DataLockException extends Exception 
{
	String message;
	
	public DataLockException()
	{ 
		message = "You have agreed to policy and cannot change previously entered data";
	}
	
	public DataLockException(String message)
	{
		this.message = message;
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
