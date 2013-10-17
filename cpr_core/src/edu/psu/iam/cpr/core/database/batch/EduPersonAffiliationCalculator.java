/* SVN FILE: $Id: EduPersonAffiliationCalculator.java 8245 2013-10-03 19:15:48Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.batch;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.StatelessSession;

import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.types.AffiliationCalculatorKey;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.DataQualityService;

/**
 * Determines the eduPerson affiliation description for employee, student and Hershey
 * records based on data elements from batch data feeds.
 * 
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 United States License. To view a copy
 * of this license, visit [http://creativecommons.org/licenses/by-nc-nd/3.0/us/]
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain
 * View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.batch.
 * @author $Author: jvuccolo $
 * @version $Rev: 8245 $
 * @lastrevision $Date: 2013-10-03 15:15:48 -0400 (Thu, 03 Oct 2013) $
 *
 */
public class EduPersonAffiliationCalculator extends PersonBio {
	
	/** Yes character flag */
	private static final String YES = "Y";
	
	/** No character flag */
	private static final String NO = "N";

	/** Contains a reference to the current primary affiliation bean */
	private PersonAffiliation currentPrimary = null;
	
	/** Contains a reference to the old primary affiliation bean */
	private PersonAffiliation oldPersonAffiliation = null;
	
	/** Contains a reference to the new primary affiliation bean */
	private PersonAffiliation newPersonAffiliation = null;
	
	/** Contains a reference to the primary override bean */
	private Long primaryOverride = null;
		
	/** Map contains all of the working affiliations, separated by categories. */
	private final Map<AffiliationCalculatorKey, ArrayList<PersonAffiliation>> currentAffiliations = new HashMap<AffiliationCalculatorKey, ArrayList<PersonAffiliation>>();
	
	/** Contains the map of the final affiliations */
	private final Map<Long, PersonAffiliation> finalAffiliations = new HashMap<Long, PersonAffiliation>();
	
	/** Contains the affiliation (eduPerson) ranking order */
	private final long[] affiliationRanking = {
			AffiliationsType.EMERITUS.index(),
			AffiliationsType.FACULTY.index(),
			AffiliationsType.RETIREE.index(),
			AffiliationsType.STAFF.index(),
			AffiliationsType.EMPLOYEE.index(),			
			AffiliationsType.STUDENT.index(),
			AffiliationsType.MEMBER.index(),
		};

	
	/**						      
	 * Default Constructor		      
	 */	
	public EduPersonAffiliationCalculator() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param databaseSession contains the hibernate database session.
	 * @param batchDataSource contains the source of the data feed.
	 * @param dataQualityService contains the data quality service reference.
	 */
    public EduPersonAffiliationCalculator(StatelessSession databaseSession,
			BatchDataSource batchDataSource,
			DataQualityService dataQualityService) {
		super(databaseSession, batchDataSource, dataQualityService);
	}
    
	/**
	 * This method is used to reset the history beans.
	 */
    @Override
	public void resetHistoryBeans() {
    	super.resetHistoryBeans();
		oldPersonAffiliation = null;
		newPersonAffiliation = null;
	}
	
	/**
	 * Get the employee affiliation based on input and make any necessary affiliation updates
	 * @param classCode holds a code representing employee class
	 * @param statusCode holds a code representing employee status
	 * @param specialStatus holds a code indicating a special appointment status
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public void setEmployeeAffiliation(String classCode, String statusCode, String specialStatus) throws CprException {
		primaryOverride = null;
		AffiliationsType newAffiliation = calculateEmployeeAffiliation(classCode, statusCode, specialStatus);
		updateAffiliations(AffiliationCalculatorKey.EMPLOYEE, newAffiliation.index());	
	}
	
	/**
	 * Get the student affiliation based on input and make any necessary affiliation updates
	 * @param statusCode holds a code representing student status
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public void setStudentAffiliation(String statusCode) throws CprException {
		primaryOverride = null;
		AffiliationsType newAffiliation = calculateStudentAffiliation(statusCode);
		updateAffiliations(AffiliationCalculatorKey.STUDENT, newAffiliation.index());	
	}
	
	/**
	 * Set the HMC affiliation. 
	 * @param newAffiliation contains the new affiliation to be added.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public void setHMCAffiliation(long newAffiliation) throws CprException {
		primaryOverride = newAffiliation;
		updateAffiliations(AffiliationCalculatorKey.EMPLOYEE, newAffiliation);
	}
	
	/**
	 * Determine the eduPerson employee affiliation.
	 * @param classCode holds a code representing employee class
	 * @param statusCode holds a code representing employee status
	 * @param specialStatus holds a code indicating a special appointment status
	 * @return AffiliationsType representing a eduPerson affiliation type
	 */
	public AffiliationsType calculateEmployeeAffiliation (String classCode, String statusCode, String specialStatus) {

		AffiliationsType affiliation = AffiliationsType.MEMBER;

		if (statusCode != null) {
			if (statusCode.equals("RET")) {
				if (specialStatus != null && specialStatus.equals("E")) {
					affiliation = AffiliationsType.EMERITUS;
				}
				else {
					affiliation = AffiliationsType.RETIREE;
				}
			}

			else if (statusCode.equals("ACT") && classCode != null) {
				if (isFacultyClass(classCode)) {
					affiliation = AffiliationsType.FACULTY;
				}
				else if (isStaffClass(classCode)) {
					affiliation = AffiliationsType.STAFF;
				}
				else if (isEmployeeClass(classCode)) {
					affiliation = AffiliationsType.EMPLOYEE;
				}
			}
		}
		
		return affiliation;
	}
	
	/**
	 * Determine the eduPerson student affiliation.
	 * @param statusCode holds a code representing student status
	 * @return AffiliationsType representing a eduPerson affiliation type
	 */
	public AffiliationsType calculateStudentAffiliation (String statusCode) {
		
		AffiliationsType affiliation = AffiliationsType.MEMBER;
		
		if (statusCode != null) {
			
			// Advanced Registration
			if (statusCode.equals("REGADV")) {
				affiliation = AffiliationsType.STUDENT;
			}
			// Correspondence registration only
			else if (statusCode.equals("REGIND")) {
				affiliation = AffiliationsType.STUDENT;
			}
			// Regular Registration
			else if (statusCode.equals("REGIST")) {
				affiliation = AffiliationsType.STUDENT;
			}
			// Late Registration - after 10 days
			else if (statusCode.equals("REGLAT")) {
				affiliation = AffiliationsType.STUDENT;
			}
			// Schedule Students courses
			else if (statusCode.equals("SCHED")) {
				affiliation = AffiliationsType.STUDENT;
			}
		}
		
		return affiliation;
	}
	
	/**
	 * Pulls all the eduPerson affiliation keys into a list
	 * @return List<Long> holds a list of the affiliation keys
	 */
	private static List<Long> affiliationKeyList() {
		
		List<Long> affiliationsList = new ArrayList<Long>();
		
		affiliationsList.add(AffiliationsType.EMERITUS.index());
		affiliationsList.add(AffiliationsType.EMPLOYEE.index());
		affiliationsList.add(AffiliationsType.FACULTY.index());
		affiliationsList.add(AffiliationsType.MEMBER.index());
		affiliationsList.add(AffiliationsType.RETIREE.index());
		affiliationsList.add(AffiliationsType.STAFF.index());
		affiliationsList.add(AffiliationsType.STUDENT.index());
		
		return affiliationsList;
	}
	
	/**
	 * Determine if any database PersonAffilation changes need to be made based on the new affiliation type key
	 * @param key contains the category of affiliation, will either be employee or student.
	 * @param newAffiliationKey holds the key for the new eduPerson affiliation type
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public void updateAffiliations(AffiliationCalculatorKey key, long newAffiliationKey) throws CprException {

		// Reset the History Beans.
		resetHistoryBeans();
		
		// Get the current list of eduPerson affiliations for the given personId
		determineCurrentPersonAffiliations();
		
		// Process the affiliation change.
		processAffiliationChange(key, newAffiliationKey);

		// Check to see if primary affiliation has changed
		calculatePrimaryAffiliation();
	}
	
	
	/**
	 * This method is used to process an affiliation change.
	 * @param key contains the type of affiliation, employee, student or member.
	 * @param affiliationKey contains the affiliation key which is the more detailed affiliation.
	 */
	public void processAffiliationChange(AffiliationCalculatorKey key, long affiliationKey) {
		
		ArrayList<PersonAffiliation> affiliationBeans = getCurrentAffiliations().get(key);
		
		// Add it.
		if (affiliationBeans == null) {
			
			// Don't add member here, because we do that at the bottom.
			if (affiliationKey != AffiliationsType.MEMBER.index()) {
				addNewAffiliation(key, affiliationKey, NO);
			}
		}
		
		else {

			// See if the affiliation already exists.
			PersonAffiliation foundBean = null;
			for (PersonAffiliation bean : affiliationBeans) {
				if (bean.getAffiliationKey().equals(affiliationKey)) {
					foundBean = bean;
					break;
				}
			}
			
			if (foundBean == null) {
				
				// If the new bean is MEMBER, we need to expire all of the affiliations for the category.
				if (affiliationKey == AffiliationsType.MEMBER.index()) {
					for (PersonAffiliation bean: affiliationBeans) {
						expirePersonAffiliation(bean);
					}
					getCurrentAffiliations().remove(key);
				}
				
				// Otherwise we just add the new affiliation.
				else {
					if (affiliationKey != AffiliationsType.MEMBER.index()) {
						PersonAffiliation bean = createPersonAffiliation(affiliationKey, NO);
						affiliationBeans.add(bean);
					}
				}
				
			}
			
			// Found!  Make sure the Last Update By is correct.
			else {
				
				if (! foundBean.getLastUpdateBy().equals(getBatchDataSource().toString())) {
					foundBean.setLastUpdateBy(getBatchDataSource().toString());
					foundBean.setLastUpdateOn(new Date());
					getDatabaseSession().update(foundBean);
				}
			}
		}
		
		// If there is an employee change to something else other than member, we need to expire the other employee affiliations.
		if (key == AffiliationCalculatorKey.EMPLOYEE &&
				affiliationKey != AffiliationsType.MEMBER.index()) {
			handleEmployeeChange(key, affiliationKey, NO);
		}
		
		// Make sure the user has a member affiliation.
		affiliationBeans = getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		if (affiliationBeans == null) {
			addNewAffiliation(AffiliationCalculatorKey.MEMBER, AffiliationsType.MEMBER.index(), NO);
		}
	}

	/**
	 * This method is used to handle the employee change where the old affiliation needs to be expired.
	 * @param key contains the key, which will always be the AffiliationCalculatorKey.EMPLOYEE.
	 * @param affiliationKey contains the affiliation key.
	 * @param primary contains the primary flag Y/N.
	 */
	private void handleEmployeeChange(AffiliationCalculatorKey key, long affiliationKey, String primary) {
		ArrayList<PersonAffiliation> affiliationBeans = getCurrentAffiliations().get(key);
		ArrayList<PersonAffiliation> copy = new ArrayList<PersonAffiliation>(affiliationBeans);
		
		for (PersonAffiliation bean: affiliationBeans) {
			if (! bean.getAffiliationKey().equals(affiliationKey)) {
				expirePersonAffiliation(bean);
				copy.remove(bean);
			}
		}
		
		getCurrentAffiliations().put(key, copy);
	}
	
	/**
	 * This method is a wrapper used to add a new affiliation.
	 * @param key contains the affiliation calculator key.
	 * @param affiliationKey contains the new affiliation's key.
	 * @param primary contains the primary flag.
	 */
	private void addNewAffiliation(AffiliationCalculatorKey key, long affiliationKey, String primary) {
		
		ArrayList<PersonAffiliation> affiliationBeans;
		PersonAffiliation bean = createPersonAffiliation(affiliationKey, primary);
		affiliationBeans = new ArrayList<PersonAffiliation>();
		affiliationBeans.add(bean);
		getCurrentAffiliations().put(key, affiliationBeans);
	}
	
	
	/**
	 * Given an affiliation key, determine if this is an employee affiliation
	 * @param key holds the affiliation key
	 * @return true if employee affiliation, false otherwise
	 */
	private boolean isEmployeeAffiliationKey(long key) {
		
		if (key == AffiliationsType.FACULTY.index()) {
			return true;
		}
		if (key == AffiliationsType.STAFF.index()) {
			return true;
		}
		if (key == AffiliationsType.RETIREE.index()) {
			return true;
		}
		if (key == AffiliationsType.EMPLOYEE.index()) {
			return true;
		}
		if (key == AffiliationsType.EMERITUS.index()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Given an affiliation key, determine if this is a student affiliation
	 * @param key holds the affiliation key
	 * @return true if student affiliation, false otherwise
	 */
	private boolean isStudentAffiliationKey(long key) {
		
		if (key == AffiliationsType.STUDENT.index()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Given an affiliation key, determine if this is a member affiliation
	 * @param key holds the affiliation key
	 * @return true if member affiliation, false otherwise
	 */
	private boolean isMemberAffiliationKey(long key) {
		
		if (key == AffiliationsType.MEMBER.index()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines the current set of person affiliations stored in the database for a person
	 * @throws CprException will be thrown if there is a Cpr related problem.
	 */
	public void determineCurrentPersonAffiliations() throws CprException {
		
		// Fetch the current person affiliations from the database
		final String affiliationSqlQuery = "from PersonAffiliation a where a.personId = :personId AND a.affiliationKey IN (:affiliationKeys) AND endDate IS NULL";
		final Query affiliationQuery = getDatabaseSession().createQuery(affiliationSqlQuery);
		affiliationQuery.setParameterList("affiliationKeys", affiliationKeyList());
		affiliationQuery.setParameter("personId", getPersonId());
		
		// Clear the person affiliation values
		resetPersonAffiliations();
		
		// Loop through the affiliation list
		for (final Iterator<?> it = affiliationQuery.list().iterator(); it.hasNext(); ) {
			PersonAffiliation affiliationDbBean = (PersonAffiliation) it.next();
			
			if (isEmployeeAffiliationKey(affiliationDbBean.getAffiliationKey())) {
				storeCurrentAffiliation(AffiliationCalculatorKey.EMPLOYEE, affiliationDbBean);
			}
			else if (isStudentAffiliationKey(affiliationDbBean.getAffiliationKey())) {
				storeCurrentAffiliation(AffiliationCalculatorKey.STUDENT, affiliationDbBean);
			}
			else if (isMemberAffiliationKey(affiliationDbBean.getAffiliationKey())) {
				storeCurrentAffiliation(AffiliationCalculatorKey.MEMBER, affiliationDbBean);
			}
		}		
	}
	
	/**
	 * This method is used to store an affiliation bean for a specific type in the current affiliations hash map.
	 * @param affiliationType contains the type of affiliation to be stored.
	 * @param affiliationDbBean contains a reference to the affiliation database bean.
	 * @throws CprException will be thrown if there is a problem with storing the affiliation.
	 */
	private void storeCurrentAffiliation(AffiliationCalculatorKey affiliationType, PersonAffiliation affiliationDbBean) throws CprException {
		
		// Obtain the list of affiliations for the specified type.  If no list was found, create one and add it.
		ArrayList<PersonAffiliation> affiliationsList = currentAffiliations.get(affiliationType);
		if (affiliationsList == null) {
			affiliationsList = new ArrayList<PersonAffiliation>();
			currentAffiliations.put(affiliationType, affiliationsList);
		}
		
		// Add new bean to the associated list.
		affiliationsList.add(affiliationDbBean);
		
		// Check for primary.
		if (affiliationDbBean.getPrimaryFlag().equals(YES)) {
			if (currentPrimary == null) {
				
				// Because the bean could be expired, we need to use a copy constructor to ensure we have a copy of the data elements.
				currentPrimary = new PersonAffiliation(affiliationDbBean);
			}
			else {
				throw new CprException(ReturnType.AFFILIATION_USE_EXCEPTION, "Unable to process affiliation change for personId = " + getPersonId() + 
						" person has more than one primary affiliation.");
			}
		}
		
	}

	/**
	 * Create a new PersonAffiliation record in the database, if it's a primary affiliation,
	 * set the new affiliation for notifications
	 * @param newAffiliationKey holds the key of the affiliation type to be created
	 * @param primary contains the value of the primary flag.
	 * @return PersonAffiliation holds the newly created affiliation record
	 */
	public PersonAffiliation createPersonAffiliation(long newAffiliationKey, String primary) {
		
		final Date currentDate = new Date();
		final String s = getBatchDataSource().toString();
		
		PersonAffiliation newAffiliation = new PersonAffiliation();
		newAffiliation.setAffiliationKey(newAffiliationKey);
		newAffiliation.setCreatedBy(s);
		newAffiliation.setCreatedOn(currentDate);
		newAffiliation.setExceptionFlag(NO);
		newAffiliation.setLastUpdateBy(s);
		newAffiliation.setLastUpdateOn(currentDate);
		newAffiliation.setPersonId(getPersonId());
		newAffiliation.setPrimaryFlag(primary);
		newAffiliation.setStartDate(currentDate);
		newAffiliation.setEndDate(null);
		
		getDatabaseSession().insert(newAffiliation);
		
		return newAffiliation;
	}
	
	/**
	 * Expire the given PersonAffiliation record in the database
	 * @param affiliation to be expired
	 */
	void expirePersonAffiliation(PersonAffiliation affiliation) {
		
		// Expire the affiliation record
		final Date currentDate = new Date();
		affiliation.setEndDate(currentDate);
		affiliation.setLastUpdateBy(getBatchDataSource().toString());
		affiliation.setLastUpdateOn(currentDate);
		
		getDatabaseSession().update(affiliation);
		
	}
	
	/**
	 * Determines if the given employee class code represents a faculty position
	 * @param classCode holds the employee class code
	 * @return true if this is a faculty position, false otherwise
	 */
	private boolean isFacultyClass(String classCode) {
		
		// Academic
		if (classCode.equals("ACAD")) {
			return true;
		}
		// Academic Administrator
		else if (classCode.equals("ACAM")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if the given employee class code represents a staff position
	 * @param classCode holds the employee class code
	 * @return true if this is a staff position, false otherwise
	 */
	private boolean isStaffClass(String classCode) {
		
		// Staff Administrator
		if (classCode.equals("ADMR")) {
			return true;
		}
		// Clerical
		if (classCode.equals("CLER")) {
			return true;
		}
		// Executive
		if (classCode.equals("EXEC")) {
			return true;
		}
		// Standing Exempt
		if (classCode.equals("STEX")) {
			return true;
		}
		// Standing Non-Exempt
		if (classCode.equals("STNE")) {
			return true;
		}
		// Staff
		if (classCode.equals("STFF")) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used to test for the Employee (Tech Services) class.
	 * @param classCode contains the class code to be tested.
	 * @return will return true if the class is of a tech services person, otherwise it will return false.
	 */
	private boolean isEmployeeClass(String classCode) {
		
		// Technical Services
		if (classCode.equals("TECH")) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Determine the primary affiliation for the set eduPerson affiliations
	 * @throws CprException will be thrown if there is a problem with determine the primary affiliation.
	 */
	public void calculatePrimaryAffiliation() throws CprException {

		// Find the new primary affiliation, start with employees first.
		PersonAffiliation newPrimary = null;
		
		storeFinalAffiliations(AffiliationCalculatorKey.EMPLOYEE);
		storeFinalAffiliations(AffiliationCalculatorKey.STUDENT);
		storeFinalAffiliations(AffiliationCalculatorKey.MEMBER);
		
		if (primaryOverride != null) {
			newPrimary = setOverridePrimary();
		}
		else {
			newPrimary = setRankingPrimary();
		}
		
		if (newPrimary != null) {		
			if ((currentPrimary == null) || (! currentPrimary.getAffiliationKey().equals(newPrimary.getAffiliationKey()))) {				
				setOldPersonAffiliation(currentPrimary);
				setNewPersonAffiliation(newPrimary);
			}
		}
		else {
			throw new CprException(ReturnType.GENERAL_DATABASE_EXCEPTION, "Unable to determine primary affiliation for personId = " + getPersonId());
		}
	}
	
	/**
	 * This method is used to process the setting of an override primary affiliation, which is typically the result of a HMC affiliation.
	 * @return will return the new primary.
	 */
	private PersonAffiliation setOverridePrimary() {

		PersonAffiliation newPrimary = null;
		for (int i = 0; i < affiliationRanking.length; ++i) {
			PersonAffiliation bean = finalAffiliations.get(affiliationRanking[i]);
			if (bean != null) {
				if (bean.getAffiliationKey().equals(primaryOverride)) {
					newPrimary = bean;
					updatePrimary(bean, YES);
				}
				else {
					if (bean.getPrimaryFlag().equals(YES)) {
						updatePrimary(bean, NO);
					}
				}
			}
		}
		return newPrimary;
	}
	
	/**
	 * This method is used to handle setting of the primary through a normal means.
	 * @return will return the resultant ranking primary affiliation.
	 */
	private PersonAffiliation setRankingPrimary() {

		PersonAffiliation newPrimary = null;
		for (int i = 0; i < affiliationRanking.length; ++i) {
			PersonAffiliation bean = finalAffiliations.get(affiliationRanking[i]);
			if (bean != null) {
				if (newPrimary == null) {
					newPrimary = bean;
					updatePrimary(bean, YES);
				}
				else {
					if (bean.getPrimaryFlag().equals(YES)) {
						updatePrimary(bean, NO);
					}
				}
			}
		}
		return newPrimary;
	}

	
	/**
	 * This method is used to update the primary affiliation to a specific value for a specific bean.
	 * @param bean contains the bean to be updated.
	 * @param primaryFlag contains the value of the primary flag.
	 */
	private void updatePrimary(PersonAffiliation bean, String primaryFlag) {
		
		final Date d = new Date();
		bean.setPrimaryFlag(primaryFlag);
		bean.setLastUpdateBy(getBatchDataSource().toString());
		bean.setLastUpdateOn(d);
		getDatabaseSession().update(bean);
	}

	/**
	 * This method is used to store off the final calculated affiliations.  Effectively its gathering all of the individual affiliations and
	 * placing them into a single list.
	 * @param key contains the affiliation key (EMPLOYEE, STUDENT or MEMBER).
	 */
	private void storeFinalAffiliations(AffiliationCalculatorKey key) {
		ArrayList<PersonAffiliation> list = currentAffiliations.get(key);
		if (list != null) {
			for (PersonAffiliation bean : list) {
				finalAffiliations.put(bean.getAffiliationKey(), bean);
			}
		}
	}
	
	/**
	 * This method resets the person affiliations
	 */
	private void resetPersonAffiliations() {

		// NOTE: resetting the primaryOverride is done elsewhere in the individual methods.
		currentPrimary = null;
		currentAffiliations.clear();
		finalAffiliations.clear();
	}
	
	/**
	 * @return the oldPersonAffiliation
	 */
	public PersonAffiliation getOldPersonAffiliation() {
		return oldPersonAffiliation;
	}

	/**
	 * @param oldPersonAffiliation the oldPersonAffiliation to set
	 */
	public void setOldPersonAffiliation(PersonAffiliation oldPersonAffiliation) {
		this.oldPersonAffiliation = oldPersonAffiliation;
	}

	/**
	 * @return the newPersonAffiliation
	 */
	public PersonAffiliation getNewPersonAffiliation() {
		return newPersonAffiliation;
	}

	/**
	 * @param newPersonAffiliation the newPersonAffiliation to set
	 */
	public void setNewPersonAffiliation(PersonAffiliation newPersonAffiliation) {
		this.newPersonAffiliation = newPersonAffiliation;
	}

	/**
	 * @return the currentAffiliations
	 */
	public Map<AffiliationCalculatorKey, ArrayList<PersonAffiliation>> getCurrentAffiliations() {
		return currentAffiliations;
	}

	public PersonAffiliation getPrimaryAffiliation() {
		return currentPrimary;
	}

	public void setPrimaryAffiliation(PersonAffiliation primaryAffiliation) {
		this.currentPrimary = primaryAffiliation;
	}

	/**
	 * @return the finalAffiliations
	 */
	public Map<Long, PersonAffiliation> getFinalAffiliations() {
		return finalAffiliations;
	}

	/**
	 * @return the affiliationRanking
	 */
	public long[] getAffiliationRanking() {
		return affiliationRanking;
	}

	/**
	 * @return the primaryOverride
	 */
	public Long getPrimaryOverride() {
		return primaryOverride;
	}

	/**
	 * @param primaryOverride the primaryOverride to set
	 */
	public void setPrimaryOverride(Long primaryOverride) {
		this.primaryOverride = primaryOverride;
	}
	
}
