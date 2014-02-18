package edu.psu.iam.cpr.ip.ui.action;

/* SVN FILE: $Id: SecurityQuestionBaseAction.java 5956 2013-01-02 16:47:16Z jal55 $ */
import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.util.AnswerHelper;

/**
 * SecurityQuestionBaseAction handles the returning user who was found, in the system,
 * and now they must answer/match their previously recorded security questions. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5956 $
 * @lastrevision $Date: 2013-01-02 11:47:16 -0500 (Wed, 02 Jan 2013) $
 */
public abstract class SecurityQuestionBaseAction extends BaseAction 
{
    private static final long serialVersionUID = 8217828502697548478L;
    private String  securityQuestion;
	private String  securityAnswer;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ip.ui.action.BaseAction#execute()
	 */
	public String execute() 
	{
		if(!setup(getPrefix()))
		{
			return FAILURE;
		}
		
		// Get the question group number: question 1, or question 2, or question 3
		String groupId = getGroupId();
				
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		String registeredAnswer = "";   
		
		String returnLocation = SUCCESS;
		
		securityQuestion = getSessionString(UIConstants.SEC_PREVIOUS_SECURITY_QUESTION +groupId); 
        registeredAnswer = getSessionString(UIConstants.SEC_PREVIOUS_RECORDED_ANSWER   +groupId);
        
        Integer attempts = (Integer)getSessionMap().get("sq" +groupId +".attempts");
		int nativeAttempts = (attempts == null) ? 0 : attempts.intValue();
		attempts = null;
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation = STAY_ON_PAGE;
		}
		if(FieldUtility.fieldIsPresent(getBtnsubmit()))
		{
			if(AnswerHelper.equals(securityAnswer, registeredAnswer))
			{
				returnLocation = SUCCESS;
			}
			else
			{
				returnLocation = STAY_ON_PAGE;
				addActionMessage(getApplicationString(UIConstants.SECURITY_QUESTION_INCORRECT_ANSWER));
				nativeAttempts++;
				getSessionMap().put("sq" +groupId +".attempts", Integer.valueOf(nativeAttempts));
			}
		}
		
		if(nativeAttempts >= UIConstants.MAX_SECURITY_QUESTION_RETRIES)
		{
			setEndPageHeader(getApplicationString( UIConstants.HELP_DESK_RESET_PSWD_HEADER));
			clearMessages();
			addActionMessage(getApplicationString(UIConstants.HELP_DESK_RESET_PSWD_TEXT  ));
			addActionMessage(getApplicationString(UIConstants.SECURITY_QUESTION_ANSWER_FAILURE));
			addReferenceNumber();
			returnLocation = FAILURE;
		}
		
		saveFieldsToSession(getPrefix());
		
		return filterSuccess(returnLocation);

	}

	/**
	 * Get the group id of the question: Is it question 1, question 2, or question 3
	 * @return
	 */
	public abstract String getGroupId() ;

	/**
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * @return the securityAnswer
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	/**
	 * @param securityAnswer the securityAnswer to set
	 */
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
}

