package edu.psu.iam.cpr.ip.ui.valueobject;

public class JMSRequestObject 
{
	private String queueName;
	private String replyQueueName;
	private String message;

	
	public  JMSRequestObject(String queueName, String replyQueueName, String message)
	{
		this.queueName      = queueName;
		this.replyQueueName = replyQueueName;
		this.message        = message;
	}
	
	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * @return the replyQueueName
	 */
	public String getReplyQueueName() {
		return replyQueueName;
	}

	/**
	 * @param replyQueueName the replyQueueName to set
	 */
	public void setReplyQueueName(String replyQueueName) {
		this.replyQueueName = replyQueueName;
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

	public String toString()
	{
		return String.format("queueName[%s], jsonMessage[%s]", queueName, message);
	}
	
}
