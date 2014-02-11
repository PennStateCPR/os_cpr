/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.messaging;

import java.util.Iterator;

import javax.jms.JMSException;

import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.beans.CampusCs;
import edu.psu.iam.cpr.core.database.beans.Confidentiality;
import edu.psu.iam.cpr.core.database.beans.Country;
import edu.psu.iam.cpr.core.database.beans.DateOfBirth;
import edu.psu.iam.cpr.core.database.beans.EmailAddress;
import edu.psu.iam.cpr.core.database.beans.Employee;
import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.beans.PersonGender;
import edu.psu.iam.cpr.core.database.beans.Phones;
import edu.psu.iam.cpr.core.database.beans.PsuId;
import edu.psu.iam.cpr.core.database.beans.Student;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicCollege;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicDepartment;
import edu.psu.iam.cpr.core.database.beans.StudentMajor;
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.types.AccessAccountStatusType;
import edu.psu.iam.cpr.core.database.types.ChangeNotificationType;
import edu.psu.iam.cpr.core.database.types.MessageKeyName;
import edu.psu.iam.cpr.core.error.CprException;


/**
 * This class is used to handle message change change notifications.  It makes a determination based on the
 * input data to determine if a message needs to be sent out.  Then using Messaging Core it will send out the
 * the message.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * 
 * @package edu.psu.iam.cpr.core.messaging
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class ChangeNotification {
	
	/** Contains an instance of the messaging core class that is used to send notifications */
	private final MessagingCore messagingCore;
	
	/** Contains an instance of the database session */
	private StatelessSession statelessSession = null;

	/** Contains the user's person identifier. */
	private Long personId = null;
	
	/** Contains the user's userid */
	private String userid = null;
	
	/** Contains the user's PSU ID number */
	private String psuId = null;
	
	/** Contains the requestor */
	private String requestedBy = null;
	
	/**
	 * Empty constructor.
	 */
	public ChangeNotification() {
		super();
		messagingCore = null;
		statelessSession = null;
	}
	
	/**
	 * Constructor.
	 * @param statelessSession contains the database session.
	 * @param messagingCore contains the instance of messaging core.
	 */
	public ChangeNotification(StatelessSession statelessSession, MessagingCore messagingCore) {
		super();
		this.statelessSession = statelessSession;
		this.messagingCore = messagingCore;
	}

	/**
	 * This method is used to set the required information needed for a message.
	 * @param personId contains the person identifier.
	 * @param psuId contains the PSU ID number associated with the person if available.
	 * @param userid contains the userid associated with the person if available.
	 * @param requestedBy contains the entity that is sending the change.
	 */
	public void setRequiredInfo(Long personId, String psuId, String userid, String requestedBy) {
		setPersonId(personId);
		setPsuId(psuId);
		setUserid(userid);
		setRequestedBy(requestedBy);
	}
	
	/**
	 * This method is used to create the initial part of the JSON message that contains all of the common information.
	 * @param change contains the type of change notification that will be sent.
	 * @return will return a json message if successful.
	 * @throws JSONException will be thrown if there are any json related problems.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public JsonMessage createBaseJsonMessage(ChangeNotificationType change) throws JSONException, CprException {
		return new JsonMessage(getPersonId(), getPsuId(), getUserid(), change.toString(), getRequestedBy());
	}
	
	/**
	 * This method is used to determine if there was an address change and to request it to be processed. 
	 * @param oldAddress contains the old address bean.
	 * @param newAddress contains the new address bean.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void addressChange(Addresses oldAddress, Addresses newAddress) throws JSONException, CprException, JMSException {
		
		// Check for a change.
		if (oldAddress != null || newAddress != null) {
					
			final ChangeNotificationType change = ChangeNotificationType.ADDRESS_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			String oldCampusCode = null;
			String oldCountryCode = null;
			String newCampusCode = null;
			String newCountryCode = null;
			
			if (oldAddress != null) {
				oldCampusCode = getCampusCodeUsingKey(oldAddress.getCampusCodeKey());
				oldCountryCode = getCountryCodeUsingKey(oldAddress.getCountryKey());
			}
			
			if (newAddress != null) {
				newCampusCode = getCampusCodeUsingKey(newAddress.getCampusCodeKey());
				newCountryCode = getCountryCodeUsingKey(newAddress.getCountryKey());
			}
			jsonMessage.setAddressChange(oldAddress, 
					oldCampusCode, 
					oldCountryCode, 
					newAddress, 
					newCampusCode, 
					newCountryCode);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			
		}
	}
	
	/**
	 * This method is used to determine if there was an email address change and to send a message if there was.
	 * @param oldEmailAddress contains the old email address bean.
	 * @param newEmailAddress contains the new email address bean.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void emailAddressChange(EmailAddress oldEmailAddress, EmailAddress newEmailAddress) throws JSONException, CprException, JMSException {
		
		if (oldEmailAddress != null || newEmailAddress != null) {
			
			final ChangeNotificationType change = ChangeNotificationType.EMAIL_ADDRESS_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setEmailAddressChange(oldEmailAddress, newEmailAddress);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			
		}
	}
	
	/**
	 * This method is used to process an employee data change.
	 * @param oldEmployee contains the old employee bean.
	 * @param newEmployee contains the new employee bean.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void employeeChange(Employee oldEmployee, Employee newEmployee) 
		throws JSONException, CprException, JMSException {
		
		if (oldEmployee != null || newEmployee != null) {
			final ChangeNotificationType change = ChangeNotificationType.EMPLOYEE_DATA_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			String oldCampusCode = null;
			String newCampusCode = null;
			
			if (oldEmployee != null) {
				oldCampusCode = getCampusCodeUsingKey(oldEmployee.getCampusCodeKey());
			}
			
			if (newEmployee != null) {
				newCampusCode = getCampusCodeUsingKey(newEmployee.getCampusCodeKey());
			}
			jsonMessage.setEmployeeChange(oldEmployee, 
					oldCampusCode, 
					newEmployee, 
					newCampusCode);
			
			if (jsonMessage.isValueSet(MessageKeyName.OLD_EMPLOYEE_DATA) || 
					jsonMessage.isValueSet(MessageKeyName.NEW_EMPLOYEE_DATA)) {
				getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			}
		}
	}
	
	/**
	 * This method is used to determine and process a gender change.
	 * @param oldGender contains the old gender value.
	 * @param newGender contains the new gender value.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void genderChange(PersonGender oldGender, PersonGender newGender) throws JSONException, CprException, JMSException {
		
		if (oldGender != null || newGender != null) {
			
			final ChangeNotificationType change = ChangeNotificationType.GENDER_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setGenderChange(oldGender, newGender);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
						
		}
	}

	/**
	 * This method is used to determine if there was a name change and process it accordingly.
	 * @param oldName contains the old name value.
	 * @param newName contains the new name value.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void nameChange(Names oldName, Names newName) throws JSONException, CprException, JMSException {
		
		if (oldName != null || newName != null) {
			
			final ChangeNotificationType change = ChangeNotificationType.NAME_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setNameChange(oldName, newName);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			
		}
	}
	
	/**
	 * This method is used to determine if there was a phone change and process it accordingly.
	 * @param oldPhone contains the old phone value.
	 * @param newPhone contains the new phone value.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void phoneChange(Phones oldPhone, Phones newPhone) throws JSONException, CprException, JMSException {
		
		if (oldPhone != null || newPhone != null) {
			
			final ChangeNotificationType change = ChangeNotificationType.PHONE_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setPhoneChange(oldPhone, newPhone);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			
		}
	}
	
	/**
	 * This method is used to determine if there was a PSU ID change and process it accordingly.
	 * @param oldPsuId contains the old psu id bean.
	 * @param newPsuId contains the new psu id bean.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void psuIdChange(PsuId oldPsuId, PsuId newPsuId) throws JSONException, CprException, JMSException {
		
		if (oldPsuId != null || newPsuId != null) {
			
			final ChangeNotificationType change = ChangeNotificationType.PSU_ID_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setPsuIdChange(oldPsuId, newPsuId);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			
		}
	}
	
	/**
	 * This method is used to determine if there was a student information change and process it accordingly.
	 * @param oldStudent contains the old student information bean.
	 * @param oldStudentAcademicCollege contains the student's old academic college.
	 * @param oldStudentAcademicDepartment contains the student's old academic department.
	 * @param oldStudentMajor contains the student old major.
	 * @param newStudent contains the new student information bean.
	 * @param newStudentAcademicCollege contains the student's new academic college.
	 * @param newStudentAcademicDepartment contains the student's new academic department.
	 * @param newStudentMajor contains the student new major.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void studentChange(Student oldStudent, 
			StudentAcademicCollege oldStudentAcademicCollege, 
			StudentAcademicDepartment oldStudentAcademicDepartment, 
			StudentMajor oldStudentMajor,
			Student newStudent, 
			StudentAcademicCollege newStudentAcademicCollege, 
			StudentAcademicDepartment newStudentAcademicDepartment,
			StudentMajor newStudentMajor) 
		throws JSONException, CprException, JMSException  {
		
		if (oldStudent != null || newStudent != null) {
			
			final ChangeNotificationType change = ChangeNotificationType.STUDENT_DATA_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			String oldCampusCode = null;
			String newCampusCode = null;
			
			if (oldStudent != null) {
				oldCampusCode = getCampusCodeUsingKey(oldStudent.getCampusCodeKey());
			}
			if (newStudent != null) {
				newCampusCode = getCampusCodeUsingKey(newStudent.getCampusCodeKey());
			}
			jsonMessage.setStudentChange(oldStudent, 
					oldCampusCode, 
					oldStudentAcademicCollege, 
					oldStudentAcademicDepartment,
					oldStudentMajor,
					newStudent, 
					newCampusCode, 
					newStudentAcademicCollege, 
					newStudentAcademicDepartment,
					newStudentMajor);
			
			if (jsonMessage.isValueSet(MessageKeyName.OLD_STUDENT_DATA) ||
					jsonMessage.isValueSet(MessageKeyName.NEW_STUDENT_DATA)) {
				getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			}
		}
	}
	
	/**
	 * This method is used to handle the processor of an access account status change.
	 * @param oldAccountStatus contains the old access account status.
	 * @param newAccountStatus contains the new access account status.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void accountStatusChange(AccessAccountStatusType oldAccountStatus, AccessAccountStatusType newAccountStatus) 
			throws JSONException, CprException, JMSException {
		
		if (oldAccountStatus != null || newAccountStatus != null) {	
			final ChangeNotificationType change = ChangeNotificationType.ACCOUNT_STATUS_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setAccountStatusChange(oldAccountStatus, newAccountStatus);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
		}
		
	}
	
	/**
	 * This method is used to determine if there was a userid change and process it accordingly.
	 * @param oldUserid contains the old userid bean.
	 * @param newUserid contains the new userid bean.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JSONException  will be thrown if there are any JSON message problems.
	 * @throws JMSException will be thrown if there is a JMS error.
	 */
	public void useridChange(Userid oldUserid, Userid newUserid) throws JSONException, CprException, JMSException {
		
		if (oldUserid != null || newUserid != null) {
			
			final ChangeNotificationType change = ChangeNotificationType.USERID_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setUseridChange(oldUserid, newUserid);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
			
		}
	}
	
	/**
	 * This method is used to add a new employee's notification message.
	 * @param newName contains their new name.
	 * @param newAddress contains their address.
	 * @param newPhone contains their phone number.
	 * @param newEmailAddress contains their email address.
	 * @param newAffiliation contains their affiliation
	 * @param newGender contains their gender.
	 * @param newConfidentiality contains their confidentiality.
	 * @param newEmployee contains their employee information.
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JMSException will be thrown if there are any JMS problems.
	 */
	public void newEmployee(Names newName, 
			Addresses newAddress, 
			Phones newPhone, 
			EmailAddress newEmailAddress, 
			PersonAffiliation newAffiliation,
			PersonGender newGender, 
			Confidentiality newConfidentiality, 
			Employee newEmployee) throws JSONException, CprException, JMSException {
		
		String newCampusCodeEmployee = null;
		String newCampusCode = null;
		String newCountryCode = null;
	
		if (newEmployee != null) {
			newCampusCodeEmployee = getCampusCodeUsingKey(newEmployee.getCampusCodeKey());
		}
		
		if (newAddress != null) {
			newCampusCode = getCampusCodeUsingKey(newAddress.getCampusCodeKey());
			newCountryCode = getCountryCodeUsingKey(newAddress.getCountryKey());
		}
		
		final ChangeNotificationType change = ChangeNotificationType.ADD_EMPLOYEE_CHANGE;
		final JsonMessage jsonMessage = createBaseJsonMessage(change); 
		jsonMessage.setEmployeeAdd(newName, newAddress, newCampusCode, newCountryCode, newPhone, newEmailAddress, 
				newAffiliation, newGender, newConfidentiality, newEmployee, newCampusCodeEmployee);
		getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
	}
	
	/**
	 * This method is used to handlle the addition of a new student in the CPR.
	 * @param newName contains the student's name.
	 * @param newAddress contains the student's address.
	 * @param newPhone contains the student's phone number.
	 * @param newEmailAddress contains the student's email address.
	 * @param newAffiliation contains the student's affiliation.
	 * @param newGender contains the student's gender.
	 * @param newConfidentiality contains the student's confidentiality status.
	 * @param newStudent contains the student's information.
	 * @param newStudentAcademicCollege contains the student's academic college.
	 * @param newStudentAcademicDepartment contains the student's academic department.
	 * @param newStudentMajor contains the student's major.
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JMSException will be thrown if there are any JMS problems.
	 */
	public void newStudent(Names newName,
			Addresses newAddress,
			Phones newPhone,
			EmailAddress newEmailAddress,
			PersonAffiliation newAffiliation,
			PersonGender newGender,
			Confidentiality newConfidentiality,
			Student newStudent,
			StudentAcademicCollege newStudentAcademicCollege,
			StudentAcademicDepartment newStudentAcademicDepartment,
			StudentMajor newStudentMajor)  throws JSONException, CprException, JMSException  {
		
		String newCampusCodeStudent = null;
		String newCampusCode = null;
		String newCountryCode = null;
	
		if (newStudent != null) {
			newCampusCodeStudent = getCampusCodeUsingKey(newStudent.getCampusCodeKey());
		}
		
		if (newAddress != null) {
			newCampusCode = getCampusCodeUsingKey(newAddress.getCampusCodeKey());
			newCountryCode = getCountryCodeUsingKey(newAddress.getCountryKey());
		}
		
		final ChangeNotificationType change = ChangeNotificationType.ADD_STUDENT_CHANGE;
		final JsonMessage jsonMessage = createBaseJsonMessage(change); 
		jsonMessage.setStudentAdd(newName, newAddress, newCampusCode, newCountryCode, newPhone, newEmailAddress, 
				newAffiliation,  newGender, newConfidentiality, newStudent, newCampusCodeStudent, 
				newStudentAcademicCollege, newStudentAcademicDepartment, newStudentMajor);
		getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
	}
	


	
	/**
	 * This method is responsible for affiliation changes for any affiliation.
	 * @param oldAffiliation contains the old affiliation.
	 * @param newAffiliation contains the new affiliation.
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JMSException will be thrown if there are any JMS problems.
	 */
	public void affiliationChange(PersonAffiliation oldAffiliation, PersonAffiliation newAffiliation) throws JSONException, CprException, JMSException {
		
		if (oldAffiliation != null || newAffiliation != null) {
			final ChangeNotificationType change = ChangeNotificationType.EDU_PERSON_AFFILIATION_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setPrimaryAffiliationChange(oldAffiliation, newAffiliation);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
		
		}
		
	}
	/**
	 * This method is responsible for affiliation changes for the primary affiliation.
	 * @param oldAffiliation contains the old primary affiliation.
	 * @param newAffiliation contains the new primary affiliation.
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws JMSException will be thrown if there are any JMS problems.
	 */
	public void primaryAffiliationChange(PersonAffiliation oldAffiliation, PersonAffiliation newAffiliation) throws JSONException, CprException, JMSException {
		
		if (oldAffiliation != null || newAffiliation != null) {
			final ChangeNotificationType change = ChangeNotificationType.EDU_PERSON_PRIMARY_AFFILIATION_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setPrimaryAffiliationChange(oldAffiliation, newAffiliation);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
		
		}
		
	}
	
	public void dateOfBirthChange(DateOfBirth oldDateOfBirth, DateOfBirth newDateOfBirth)throws JSONException, CprException, JMSException {
		if (oldDateOfBirth != null || newDateOfBirth != null) {
			final ChangeNotificationType change = ChangeNotificationType.DATE_OF_BIRTH_CHANGE;
			final JsonMessage jsonMessage = createBaseJsonMessage(change); 
			jsonMessage.setDateOfBirthChange(oldDateOfBirth, newDateOfBirth);
			getMessagingCore().sendNotification(getStatelessSession(), change, jsonMessage);
		
		}
		
	}
	/**
	 * This method is used to obtain a campus code using the campus key value.
	 * @param campusCodeKey contains the campus key value to be used as part of the query.
	 * @return will return the campus code if found.
	 */
	private String getCampusCodeUsingKey(Long campusCodeKey) {
		
		String campusCode = null;
		
		if (campusCodeKey == null) {
			return campusCode;
		}
		
		final StatelessSession session = getStatelessSession();
		final Query query = session.createQuery("from CampusCs where campusCodeKey = :campus_code_key");
		query.setParameter("campus_code_key", campusCodeKey);
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			CampusCs bean = (CampusCs) it.next();
			campusCode = bean.getCampusCode();
		}
		
		return campusCode;
	}
	
	/**
	 * This method is used to obtain a country code using the campus key value.
	 * @param countryCodeKey contains the country code to be obtained.
	 * @return will return a country code if successful.
	 */
	private String getCountryCodeUsingKey(Long countryCodeKey) {
		
		String countryCode = null;
		
		if (countryCodeKey == null) {
			return countryCode;
		}
		
		final StatelessSession session = getStatelessSession();
		final Query query = session.createQuery("from Country where countryKey = :country_key");
		query.setParameter("country_key", countryCodeKey);
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Country bean = (Country) it.next();
			countryCode = bean.getCountryCodeThree();
		}
		
		return countryCode;
	}
	
	/**
	 * @return the messagingCore
	 */
	public MessagingCore getMessagingCore() {
		return messagingCore;
	}

	/**
	 * @param statelessSession the statelessSession to set
	 */
	public void setStatelessSession(StatelessSession statelessSession) {
		this.statelessSession = statelessSession;
	}

	/**
	 * @return the statelessSession
	 */
	public StatelessSession getStatelessSession() {
		return statelessSession;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param psuId the psuId to set
	 */
	public void setPsuId(String psuId) {
		this.psuId = psuId;
	}

	/**
	 * @return the psuId
	 */
	public String getPsuId() {
		return psuId;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}


}
