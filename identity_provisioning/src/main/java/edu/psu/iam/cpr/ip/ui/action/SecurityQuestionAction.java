/* SVN FILE: $Id: SecurityQuestionAction.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import edu.psu.iam.cpr.core.database.beans.SecurityQuestionAnswers;
import edu.psu.iam.cpr.ip.ui.common.MagicNumber;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.dao.IdentityProvisioningDAO;
import edu.psu.iam.cpr.ip.ui.soap.SoapClientIP;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.util.AnswerHelper;
import edu.psu.iam.cpr.ip.util.MapHelper;

/**
 * SecurityQuestionAction processes the rendering of the security question screen 
 * and the processing of end-user responses.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5953 $
 * @lastrevision $Date: 2012-12-20 17:21:58 -0500 (Thu, 20 Dec 2012) $
 */
public class SecurityQuestionAction extends BaseAction 
{
	private static final String SEC_RETRY_COUNT = "sec.retry.count";
	private static final String ZERO = "0";
    private static final long serialVersionUID = 2007566517768725745L;

    // Users answer to question 1, 2 and 3
	private String securityQuestion1Answer;
	private String securityQuestion2Answer;
	private String securityQuestion3Answer;
	
	// Used to send the list of questions/dropdowns to the form
	private Map<String, String> securityQuestions1;
	private Map<String, String> securityQuestions2;
	private Map<String, String> securityQuestions3;
	
	// The indicator of which question was selected via the drop down -- its a number indicating the question id
	private String securityQuestion1;
	private String securityQuestion2;
	private String securityQuestion3;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="security_questions",results={ @Result(name=SUCCESS,location="/password", type=REDIRECT),
                                                 @Result(name="stay on page",location="/jsp/securityQuestions.jsp"),
                                                 @Result(name="go to sq splash", location="/sq0", type=REDIRECT),
                                                 @Result(name="failure",location="/jsp/endPage.jsp")
                                               })
	public String execute() 
	{
		if(!setup("sec"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		String returnLocation = SUCCESS;
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation = STAY_ON_PAGE;
		}
		
		if(personWasFound() && securityQuestionsPreviouslyNotSet())
		{
			setEndPageHeader(getApplicationString( UIConstants.HELP_DESK_RESET_PSWD_HEADER));
			addActionMessage(getApplicationString(UIConstants.HELP_DESK_RESET_PSWD_TEXT  ));
			addReferenceNumber();
			returnLocation = FAILURE;
		}
		
		/* Populate the security question drop-downs */
		if(!returnLocation.equalsIgnoreCase(FAILURE))
		{
			/* Populate all '3' security questions */
			ArrayList<Map<String,String>> securityQuestions = new ArrayList<Map<String, String>>();

			securityQuestions = (ArrayList<Map<String, String>>) ((getApplicationMap().containsKey(UIConstants.IN_CORE_SECURITY_QUESTIONS)) 
			                            ? getApplicationMap().get(UIConstants.IN_CORE_SECURITY_QUESTIONS)
					                    : IdentityProvisioningDAO.loadSecurityQuestionsFromDb()); 

			securityQuestions1 = securityQuestions.get(0);
			securityQuestions2 = securityQuestions.get(1);
			securityQuestions3 = securityQuestions.get(2);
			
			/* Need to check if the person was found again.
			 * If they were, then we already know their question were
			 * previously set, so let's scroll the drop-downs to those questions,
			 * and put the previous answers into memory for later comparisons.
			 */
			if(personWasFound())
			{
				List<SecurityQuestionAnswers> list = getListSecurityQuestions();
				
				securityQuestion1 = Long.toString(list.get(0).getSecQuestKey());
				securityQuestion2 = Long.toString(list.get(1).getSecQuestKey());
				securityQuestion3 = Long.toString(list.get(2).getSecQuestKey());
				
				getSessionMap().put(UIConstants.SEC_PREVIOUS_RECORDED_ANSWER +"1", list.get(0).getAnswer());
				getSessionMap().put(UIConstants.SEC_PREVIOUS_RECORDED_ANSWER +"2", list.get(1).getAnswer());
				getSessionMap().put(UIConstants.SEC_PREVIOUS_RECORDED_ANSWER +"3", list.get(2).getAnswer());
				
				// Change to we-found-you logic.  Now, we will instantly go to the splash page
				// but we need the info from this section to be saved first
                getSessionMap().put(UIConstants.SEC_PREVIOUS_SECURITY_QUESTION +"1", securityQuestions1.get(securityQuestion1));
                getSessionMap().put(UIConstants.SEC_PREVIOUS_SECURITY_QUESTION +"2", securityQuestions2.get(securityQuestion2));
                getSessionMap().put(UIConstants.SEC_PREVIOUS_SECURITY_QUESTION +"3", securityQuestions3.get(securityQuestion3));
                
                if(FieldUtility.fieldIsPresent(getApplicationString(UIConstants.LOGIC_SECURITY_QUESTIONS_PERSON_FOUND)))
                {
                	//"go to sq splash"
                	String newLogicFlow = getApplicationString(UIConstants.LOGIC_SECURITY_QUESTIONS_PERSON_FOUND);      
                	if(!newLogicFlow.equalsIgnoreCase("default"))
                	{
                		returnLocation = newLogicFlow;
                	}
                }
			}
		
	
			saveFieldsToSession(getPrefix());
		
			/* If they answer one question, they must answer all three */
			if(returnLocation.equalsIgnoreCase(ActionSupport.SUCCESS))
			{
				int numberAnswers = 0;
			
				if(FieldUtility.fieldIsPresent(securityQuestion1Answer))
				{
					numberAnswers++;
				}
			
				if(FieldUtility.fieldIsPresent(securityQuestion2Answer))
				{
					numberAnswers++;
				}
 
				if(FieldUtility.fieldIsPresent(securityQuestion3Answer))
				{
					numberAnswers++;
				}
			
				int numberQuestionsSelected = 0;
				try
				{
					if(Integer.parseInt(securityQuestion1) > 0)
					{
						numberQuestionsSelected++;
					}
				
					if(Integer.parseInt(securityQuestion2) > 0)
					{
						numberQuestionsSelected++;
					}
				
					if(Integer.parseInt(securityQuestion3) > 0)
					{
						numberQuestionsSelected++;
					}
				}
				catch(Exception e) {}
			
				// If you answer one question, you must answer and select all three
				if((numberAnswers > 0 || numberQuestionsSelected > 0) && (numberAnswers !=MagicNumber.I3 || numberQuestionsSelected !=MagicNumber.L3))
				{
					addActionMessage(getApplicationString(UIConstants.ERROR_YOU_MUST_ANSWER_ALL_QUESTIONS));
					returnLocation = STAY_ON_PAGE;
				}
			}
			
			/* Write security questions and answers to database*/
			String personId = (String)getSessionMap().get("suc.personId");
			String userId   = (String)getSessionMap().get("suc.userId");
		
			/* Default to password should be initial setting */
			if(returnLocation.equalsIgnoreCase(SUCCESS) && personWasNotFound()   &&
				FieldUtility.fieldIsPresent(personId, userId, securityQuestion1Answer, securityQuestion2Answer, securityQuestion3Answer))  
			{
				IdentityProvisioningDAO.saveUserSecurityQuestionAnswers(createAnswerQuestionObjects());
			}
		
			/* Note: The person was found, and the questions were preset, and btnSubmit was hit */
			if(returnLocation.equalsIgnoreCase(SUCCESS) && personWasFound() && FieldUtility.fieldIsPresent(getBtnsubmit())) 
			{
				// Proceed to password RESET function	
				returnLocation = SUCCESS;                  

				// update person
				/* If the person was verify, and they have answered questions correctly -- then 'updatePerson' */
				HashMap<String, String> argStringMap = MapHelper.genericObjToStringHashMap(getSessionMap());
				HashMap<String, String> status = SoapClientIP.updatePerson(argStringMap, getUniqueId()); 
				log.info(String.format("%s returnStatus from addPerson--> %s ", getUniqueId(), status));
					
				log.info(String.format("%s statuscode from addPerson[%s]", getUniqueId(), Integer.parseInt(status.get("statusCode"))));
				switch(Integer.parseInt(status.get("statusCode")))
				{
				    // Updates were made by updatePerson
					case 0: 
					// Maybe address was updated, but phone was a duplicate -- minor, and ignore
					case 1: 
							break;
							  	   
					// Error from updatePerson
					default:setEndPageHeader(getApplicationString("ui.update.person.error1.header"));    
			           		addActionMessage(getMessageElements("ui.update.person.error1.message1.count"));
		           			addReferenceNumber();
			           		returnLocation = FAILURE;
			           		break;
				}
			}
			else
			{
				if(personWasFound() && FieldUtility.fieldIsPresent(getBtnsubmit()) )
				{
					if(FieldUtility.fieldIsNotPresent((String)getSessionMap().get(SEC_RETRY_COUNT)))
					{
						getSessionMap().put(SEC_RETRY_COUNT, ZERO);
					}

					int retryCount = Integer.parseInt((String)getSessionMap().get(SEC_RETRY_COUNT)) ;
				
					if(++retryCount >= UIConstants.MAX_SECURITY_QUESTION_RETRIES )				
					{
						setEndPageHeader(getApplicationString("ui.security.answers.incorrect.header"));

						List<String> messageList = getMessageElements("ui.security.answers.incorrect.count");
					
						for(String message: messageList)
						{
							addActionMessage(message);
						}
						addReferenceNumber();
					
						log.info(String.format("%s Security Answers did not match", getUniqueId()));
					
						returnLocation = FAILURE;
					}
					if(!hasActionMessages() && FieldUtility.fieldIsPresent(getBtnsubmit()))   
					{
						getSessionMap().put(SEC_RETRY_COUNT, String.format("%s", retryCount));
						addActionMessage(getApplicationString(UIConstants.SECURITY_QUESTION_ANSWER_INCORRECT));
						returnLocation = STAY_ON_PAGE;
					}
				}
			}
		}   
			   
		return returnLocation;
	}
	
	/**
	 * Determine, for found persons, if their current answers match their previously entered answers
	 * @return
	 */
	public boolean isSecurityAnswersCorrect()
	{
		return(AnswerHelper.equals(securityQuestion1Answer, getSessionString("sec.previous.recorded.answer1")) &&
		       AnswerHelper.equals(securityQuestion2Answer, getSessionString("sec.previous.recorded.answer2")) &&
		       AnswerHelper.equals(securityQuestion3Answer, getSessionString("sec.previous.recorded.answer3"))) ;

	}
	
	/**
	 * Return 'true' if security questions previously set for this credential
	 * @return
	 */
	public boolean securityQuestionsPreviouslySet()
	{
		boolean previousQuestionsExist = true;

		if(getListSecurityQuestions().isEmpty())
		{
			previousQuestionsExist = false;
		}

		return previousQuestionsExist;
	}
	
	/**
	 * Return 'true' if security questions previously not set for this credential
	 * @return
	 */
	public boolean securityQuestionsPreviouslyNotSet()
	{
		// Default to true for this test
		boolean securityQuestionsPreviouslyNotSet = true;
		
		// Our default was incorrect, but we'll be right 50% of the time-- probably!
		if(securityQuestionsPreviouslySet())
		{
			securityQuestionsPreviouslyNotSet = false;
		}

		return securityQuestionsPreviouslyNotSet;
			
	}
	
	/**
	 * Get the SecurityQuestionAnswer objects from the database
	 * @return The security questions, and answers for this credential
	 */
	public List<SecurityQuestionAnswers> getListSecurityQuestions()
	{
		return IdentityProvisioningDAO.loadSecurityAnswersFromDb(Long.parseLong(getSessionString("suc.personId")), 
                                                                 getSessionString("suc.userId"));
		
	}

    /**
     * 	The routine creates SecurityQuestionAnswers objects from the security
     *  questions and answers on the end-users's screen.
     *  
     * @return - an List of SecurityQuestionAnswers objects 
     */
	public List<SecurityQuestionAnswers> createAnswerQuestionObjects()
	{
		ArrayList<SecurityQuestionAnswers> list = new ArrayList<SecurityQuestionAnswers>();
		Date dateCreatedOn = new Date();
		String userId      = (String)getSessionMap().get("suc.userId");
		Long   personId    = Long.parseLong((String)getSessionMap().get("suc.personId"));
		

		SecurityQuestionAnswers sqa = new SecurityQuestionAnswers();
        
		// Question 1
		sqa.setPersonId(personId);
		sqa.setUserid(userId);
		sqa.setSecQuestKey(Long.parseLong(securityQuestion1));
		sqa.setSecQuestGroupKey(MagicNumber.L1);    
		sqa.setAnswer(AnswerHelper.compress(securityQuestion1Answer));
		sqa.setCreatedOn(dateCreatedOn);
		list.add(sqa);
		
		// Question 2
		sqa = new SecurityQuestionAnswers();
		sqa.setPersonId(personId);
		sqa.setUserid(userId);
		sqa.setSecQuestKey(Long.parseLong(securityQuestion2));
		sqa.setSecQuestGroupKey(MagicNumber.L2);
		sqa.setAnswer(AnswerHelper.compress(securityQuestion2Answer));
		sqa.setCreatedOn(dateCreatedOn);
		list.add(sqa);
		
		// Question 3
		sqa = new SecurityQuestionAnswers();
		sqa.setPersonId(personId);
		sqa.setUserid(userId);
		sqa.setSecQuestKey(Long.parseLong(securityQuestion3));
		sqa.setSecQuestGroupKey(MagicNumber.L3);
		sqa.setAnswer(AnswerHelper.compress(securityQuestion3Answer));
		sqa.setCreatedOn(dateCreatedOn);
		list.add(sqa);
		
		return list;
	}

	/**
	 * @return the securityQuestion1Answer
	 */
	public String getSecurityQuestion1Answer() {
		return securityQuestion1Answer;
	}

	/**
	 * @param securityQuestion1Answer the securityQuestion1Answer to set
	 */
	public void setSecurityQuestion1Answer(String securityQuestion1Answer) {
		this.securityQuestion1Answer = securityQuestion1Answer;
	}


	/**
	 * @return the securityQuestion2Answer
	 */
	public String getSecurityQuestion2Answer() {
		return securityQuestion2Answer;
	}


	/**
	 * @param securityQuestion2Answer the securityQuestion2Answer to set
	 */
	public void setSecurityQuestion2Answer(String securityQuestion2Answer) {
		this.securityQuestion2Answer = securityQuestion2Answer;
	}


	/**
	 * @return the securityQuestion3Answer
	 */
	public String getSecurityQuestion3Answer() {
		return securityQuestion3Answer;
	}


	/**
	 * @param securityQuestion3Answer the securityQuestion3Answer to set
	 */
	public void setSecurityQuestion3Answer(String securityQuestion3Answer) {
		this.securityQuestion3Answer = securityQuestion3Answer;
	}


	/**
	 * @return the securityQuestions1
	 */
	public Map<String, String> getSecurityQuestions1() {
		return securityQuestions1;
	}



	/**
	 * @param securityQuestions1 the securityQuestions1 to set
	 */
	public void setSecurityQuestions1(Map<String, String> securityQuestions1) {
		this.securityQuestions1 = securityQuestions1;
	}



	/**
	 * @return the securityQuestions2
	 */
	public Map<String, String> getSecurityQuestions2() {
		return securityQuestions2;
	}



	/**
	 * @param securityQuestions2 the securityQuestions2 to set
	 */
	public void setSecurityQuestions2(Map<String, String> securityQuestions2) {
		this.securityQuestions2 = securityQuestions2;
	}



	/**
	 * @return the securityQuestions3
	 */
	public Map<String, String> getSecurityQuestions3() {
		return securityQuestions3;
	}



	/**
	 * @param securityQuestions3 the securityQuestions3 to set
	 */
	public void setSecurityQuestions3(Map<String, String> securityQuestions3) {
		this.securityQuestions3 = securityQuestions3;
	}



	/**
	 * @return the securityQuestion1
	 */
	public String getSecurityQuestion1() {
		return securityQuestion1;
	}



	/**
	 * @param securityQuestion1 the securityQuestion1 to set
	 */
	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}



	/**
	 * @return the securityQuestion2
	 */
	public String getSecurityQuestion2() {
		return securityQuestion2;
	}



	/**
	 * @param securityQuestion2 the securityQuestion2 to set
	 */
	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}



	/**
	 * @return the securityQuestion3
	 */
	public String getSecurityQuestion3() {
		return securityQuestion3;
	}


	/**
	 * @param securityQuestion3 the securityQuestion3 to set
	 */
	public void setSecurityQuestion3(String securityQuestion3) {
		this.securityQuestion3 = securityQuestion3;
	}
}
