/* SVN FILE: $Id: IdentityProvisioningDAO.java 6103 2013-01-30 15:10:38Z jal55 $ */

package edu.psu.iam.cpr.ip.ui.dao;
 /**
  * AddressDAO is responsible for database I/O related to addresses; such as, 
  * retrieving a country list, and a state list.
  * 
  * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
  * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
  * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
  *
  * @package edu.psu.iam.cpr.ip.ui.action 
  * @author $Author: jal55 $
  * @version $Rev: 6103 $
  * @lastrevision $Date: 2013-01-30 10:10:38 -0500 (Wed, 30 Jan 2013) $
  */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.EmailAddress;
import edu.psu.iam.cpr.core.database.beans.SecurityQuestionAnswers;
import edu.psu.iam.cpr.core.ui.DatabaseUI;
import edu.psu.iam.cpr.core.ui.EmailMsgUI;
import edu.psu.iam.cpr.core.ui.Pair;
import edu.psu.iam.cpr.ip.ui.common.MagicNumber;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;

public final class IdentityProvisioningDAO 
{
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(IdentityProvisioningDAO.class);
	
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private IdentityProvisioningDAO()  { }
	
	/**
	 * Create citizenship drop-down data
	 * @return a map which is used to populate the drop-down
	 */
	public static Map loadCitizenshipt()
	{
		Map<String, String> map = null;
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		
		list.add(new Pair<String, String>("US"    , "U.S. Citizen"    ));
		list.add(new Pair<String, String>("NON-US", "Non U.S. Citizen"));
		map = convertPairToMap(list);
		
		return map;
	}

	/**
	 * create a map for gender
	 * @return a map to be used in the gender drop-down
	 */
	public static List loadGenderList()
	{
		 // Source of key and value need to come from database 
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		
		list.add(new Pair<String, String>("", "Please select..."   ));
		list.add(new Pair<String, String>("GENDER_FEMALE", "Female"));
		list.add(new Pair<String, String>("GENDER_MALE"  , "Male"  ));
		list.add(new Pair<String, String>("GENDER_OTHER" , "Other" ));
		
		return list;
	}
	

	/**
	 * Return a 'Map' of RA properties
	 * @param applicationName
	 * @param principalId
	 * @return
	 */
	public static Map<String, String> getRAPropertiesFromDb(String applicationName, String principalId)
	{
		Map<String, String> map = null;
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			map = db.getRaApplicationProperties(applicationName, principalId);
		} 
		catch (Exception e)   
		{
			LOG.info(String.format("  Exception [%s] encountered in [%s] ", e.getMessage(), "IP DAO getRAPropertiesFromDB"));
			LOG.info(String.format("  Cause [%s]", e.getCause()));
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return map;
	}
	
	/**
	 * Retrieve all RA properties from the database.  
	 * @param applicationName
	 * @return
	 */
	public static Map<String, Map<String, String>> getAllRAPropertiesFromDb(String applicationName)
	{
		Map<String, Map<String, String>> map = null;
		
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			map = db.getAllApplicationProperties(applicationName);
		} 
		catch (Exception e)   
		{
			LOG.info(String.format("  Exception [%s] encountered in [%s]", e.getMessage(), "IP DAO getAllRAPropertiesFromDb"));
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return map;
	}
	
	/**
	 * Return a 'Map' of RA Screens
	 * @param applicationName
	 * @param principalId
	 * @return
	 */
	public static List<String> loadRAScreensFromDb(String applicationName, String principalId)
	{
		List<String> map = null;
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			map = db.getRaScreenList(applicationName, principalId);
		} 
		catch (Exception e)   
		{
			LOG.info(String.format("Error loading RA Screens list from database - %s", e.getMessage()));
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return map;
	}
	
	/** 
	 * Save the users questions selected for answering, and the matching answer entered
	 * @param answersAndQuestions - A List representing the questions and answers to the questions
	 */
	public static void saveUserSecurityQuestionAnswers(List<SecurityQuestionAnswers> answersAndQuestions)
	{
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			db.setUserSecurityQuestionAnswers((ArrayList<SecurityQuestionAnswers>) answersAndQuestions);
		} 
		catch (Exception e)   
		{
			// Could not save the security questions, so roll back, but continue
			LOG.info(String.format("Error user-entered Security answers to database - %s", e.getMessage()));
			logStackTrace(e);
			db.rollbackSession();
		}

		finally{
			db.closeSession();
		}
		
		return ;

	}
	
	
	/**
	 * 
	 * @return A Map where the key represents the gender value <br> 
	 *         and the value represents the viewable gender name
	 */
	public static Map<String, String> loadGenderListFromDb()
	{
		Map<String, String> map = null;
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			map = convertPairToMap(db.getGenderList());
		} 
		catch (Exception e)   
		{
			LOG.info("Error loading Gender List/map from database");
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return map;
	}
	
	/**
	 * 
	 * @return A Map where the key represents the country value <br> 
	 *         and the value represents the viewable country name
	 */
	public static Map<String, String> loadCountryListFromDb()
	{
		Map<String, String> map = null;
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			map = convertPairToMap(db.getCountryList());
		} 
		catch (Exception e)   
		{
			LOG.info(String.format("Error loading country list from database - %s", e.getMessage()));
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return map;
	}
	
	/**
	 * 
	 * @return A Map where the key represents the state value <br> 
	 *         and the value represents the viewable state name
	 */
	public static Map<String, String> loadStateListFromDb()
	{
		Map<String, String> map = null;
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			map = convertPairToMap(db.getStateList());
		} 
		catch (Exception e)   
		{
			LOG.info(String.format("Error loading State list/map from database - %s", e.getMessage()));
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return map;
	}
	
	/**
	 * 
	 * @return A Map where the key represents the state value <br> 
	 *         and the value represents the viewable state name
	 */
	public static List<Map<String, String>> loadSecurityQuestionsFromDb()
	{
		List<Map<String, String>> listQuestions = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		DatabaseUI db = new DatabaseUI();
		
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			for(int i = 1; i < MagicNumber.I4; i++)
			{
				map = convertPairToMap(db.getSecurityQuestionList((i)));
				listQuestions.add(map);
			}
		} 
		catch (Exception e)  
		{
			LOG.info(String.format("Error loading Security Questions from database - %s", e.getMessage()));
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return listQuestions;
	}
	
	/**
	 * Retrieve from the database the answers an end-user supplied when they previously registered
	 * @param personId - The assigned person id from the original credential assignment
	 * @param userId - The assigned userid from the original credential assignment
	 * @return - A List of the answers, three of them, from the database
	 */
	public static List<SecurityQuestionAnswers> loadSecurityAnswersFromDb(Long personId, String userId)
	{
		List<SecurityQuestionAnswers> listAnswers = new ArrayList<SecurityQuestionAnswers>();
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
			listAnswers = db.getUserSecurityQuestionAnswerList(personId, userId);
		} 
		catch (Exception e)   
		{
			LOG.info(String.format("Error loading Security Answers from database - %s", e.getMessage()));
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return listAnswers;
	}
	
	/**
	 * Convert list of Pair[s] into a Map
	 * @param pairs List of 'Pair' objects
	 * @return Map<String, String> as a LinkedHashMap to guarantee order of iteration
	 */
	public static Map<String, String> convertPairToMap(List<Pair<String,String>>pairs)
	{
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		for(Pair<String, String> pair: pairs)
		{
			map.put(pair.getKey(), pair.getValue());
		}
		
		return map;
	}
	
	/**
	 * Get the two email objects for [new] account creation <b/> 
	 * and reset password functions.
	 * @return Map<String, EmailMsgUI> consisting of the two objects
	 */
	public static Map<String, EmailMsgUI> getEmailObjectsFromDbByKey()
	{
		Map<String, EmailMsgUI> map = new HashMap<String, EmailMsgUI>();
//		DatabaseUI db = new DatabaseUI();
//		
//		try 
//		{
//			db.openSession(SessionFactoryUtil.getSessionFactory());
//			map.put(UIConstants.EMAIL_ACCOUNT_CREATION_KEY, db.getEmailContents(UIConstants.EMAIL_ACCOUNT_CREATION_KEY));
//			map.put(UIConstants.EMAIL_PASSWORD_RESET_KEY  , db.getEmailContents(UIConstants.EMAIL_PASSWORD_RESET_KEY  ));
//		} 
//		catch(Exception e)   
//		{
//			LOG.info(String.format("  Exception [%s] encountered", e.getMessage()));
//			logStackTrace(e);
//		}
//
//		finally{
//			db.closeSession();
//		}
//		
		LOG.info("EmailMsgUI map from database " +(map == null || map.isEmpty()
				? "empty -- property file version in effect"
				: "overriding property file version"));
		return map;
	}
	
	/**
	 * This method is used to obtain a person's email address.  This method is only used by the forgot userid and/or forgot password flows.
	 * @param personId contains the person identifier to be searched for.
	 * @return will return the person's email address.
	 */
	public static String getEmailAddress(String personId) {
		
		DatabaseUI db = new DatabaseUI();
		String emailAddress = null;
		
		try {
			final long personIdLong = Long.valueOf(personId);
			db.openSession(SessionFactoryUtil.getSessionFactory());
			final Session session = db.getSession();
			final Query query = session.createQuery("from EmailAddress where personId = :person_id and endDate is null");
			query.setParameter("person_id", personIdLong);
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				EmailAddress bean = (EmailAddress) it.next();
				emailAddress = bean.getEmailAddress();
			}
		}
		catch (Exception e) {
			LOG.info(String.format("  Exception [%s] encountered", e.getMessage()));
			logStackTrace(e);			
		}
		finally {
			db.closeSession();
		}
		return emailAddress;
	}
	
	/**
	 * Print a listing of the stack trace, in the log, when deemed necessary
	 * @param e
	 */
	public static void logStackTrace(Exception e)
	{
		StackTraceElement[] trace = e.getStackTrace();
		for(int i = 0; i < trace.length; i++)
		{
			LOG.info(trace[i]);
		}
	}
	
	/**
	 * This method is used to get Hibernate to initiate the pool.  
	 * 
	 * Hibernate doesn't actually initiate the pool until the first session request; so, 
	 * we'll use this to make the first request during start-up.
	 */
	public static void initializeHibernatePool()
	{
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
		} 
		catch (Exception e)   
		{
			logStackTrace(e);
		}

		finally{
			db.closeSession();
		}
		
		return ;
	}
	
	/**
	 * Get a connection to the database
	 * @return - A Database [DatabaseUI]  connection
	 */
	public static DatabaseUI getDatabaseConnection()
	{
		DatabaseUI db = new DatabaseUI();
		
		try 
		{
			db.openSession(SessionFactoryUtil.getSessionFactory());
		} 
		catch (Exception e)   
		{
			LOG.info(String.format("Error -- could not acquire a database connection - %s", e.getMessage()));
			logStackTrace(e);
		}
		return db;
	}
}
