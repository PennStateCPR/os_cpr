/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.postprocess;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.beans.NamesStaging;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.beans.Semesters;
import edu.psu.iam.cpr.core.database.beans.Student;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.Utility;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * This class contains the implementation of the name post processor.  Data records are evaluated to determine what the
 * new name for a person will be.
 *  
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.postprocess
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class NamesPostProcessor {
	
	/** Data type key constant */
	private static final String DATA_TYPE_KEY = "data_type_key";

	/** Semester code constant */
	private static final String SEMESTER_CODE = "semester_code";

	/** now constant */
	private static final String NOW = "now";

	/** person id constant */
	private static final String PERSON_ID = "person_id";

	/** Contains an instance of the messaging core object which represents a connection to the messaging system */
	private final MessagingCore messagingCore;
	
	/** Contains an open database connection for a stateless session.  statelessSession cannot be final because if there is an error
	 * during batch processing, a new database session will have to be created.
	 */
	private StatelessSession statelessSession;
	
	/** Contains standard hibernate database session, would only be used by the services */
	private Session session;
	
	/** Boolean that indicates whether a person is a student. */
	private boolean student = false;
	
	/** Boolean that indicates whether a person is an employee */
	private boolean employee = false;
	
	/** Boolean that indicates whether a person is a Hershey employee */
	private boolean employeeHMC = false;
	
	/** Boolean that indicates whether a person has student aid or not */
	private boolean studentAid = false;
	
	/** This variable is used to store the ISIS name for a student if one exists */
	private NamesStaging isisName = null;
	
	/** This variable is used to store the IBIS name for an employee if one exists */
	private NamesStaging ibisName = null;
	
	/** This variable is used to store the Hershey Medical Center name for an employee if one exists */
	private NamesStaging hmcName = null;
	
	/** This variable is used to store the most recently updated name from the names staging table */
	private NamesStaging updatedName = null;
	
	/** Contains the old names bean */
	private Names oldNamesBean = null;
	
	/** Contains the new names bean */
	private Names newNamesBean = null;
	
	/**
	 * Default constructor.
	 */
	public NamesPostProcessor() {
		super();
		messagingCore = null;
		statelessSession = null;
		session = null;
	}
	
	/**
	 * Constructor.
	 * @param statelessSession contains the stateless session to be stored.
	 * @param messagingCore contains the messaging core reference.
	 */
	public NamesPostProcessor(final StatelessSession statelessSession, final MessagingCore messagingCore) {
		super();
		this.statelessSession = statelessSession;
		this.messagingCore = messagingCore;
	}
	
	/**
	 * Constructor.
	 * @param session contains a reference to the standard hibernate session.
	 * @param messagingCore contains a reference to a messaging core instance.
	 */
	public NamesPostProcessor(final Session session, final MessagingCore messagingCore) {
		super();
		this.session = session;
		this.messagingCore = messagingCore;
	}
	
	/**
	 * This method is used to reset history beans if this class is used in batch mode.
	 */
	public void resetHistoryBeans() {
		oldNamesBean = null;
		newNamesBean = null;
	}
	
	/**
	 * This method is used to process a name change for a person and determine what records need to be reconciled based on the 
	 * type of change.
	 * @param personId contains the person identifier whose name was changed.
	 */
	public void processNameChange(final long personId) {
		
		// Get all of the affiliations.
		getAndEvaluateAffiliations(personId);
		
		// Get their student aid flag
		getStudentAidFlag(personId);
		
		// Get all of the names that they have in staging.
		getAllStagedNamesForPerson(personId);
		
		// Apply the rules.
		applyNameRules(personId);
	}
	
	/**
	 * This method is used to apply the name rules to determine what name to store for the user.
	 * @param personId contains the person identifier.
	 */
	public void applyNameRules(final long personId) {
		
		// If this is a student with a student aid flag, use the ISIS name
		if (isStudent() && getIsisName() != null && hasStudentAid()) {
			setLegalName(personId, getIsisName());
		}
		// Else if this is an active Hershey employee, use the HMC name
		else if (isEmployeeHMC() && getHmcName() != null) {
			setLegalName(personId, getHmcName());
		}
		else if (getUpdatedName() != null) {
			// Use the most recently modified name
			setLegalName(personId, getUpdatedName());
		}
	}
	
	/**
	 * This is the method that set's the final legal name.
	 * @param personId contains the person identifier whose name is to be set.
	 * @param namesStagingBean contains the name to be set.
	 */
	public void setLegalName(final long personId, final NamesStaging namesStagingBean) {
		
		final StatelessSession db = getStatelessSession();
		
		final Query query = db.createQuery("from Names where personId = :person_id AND dataTypeKey = :data_type_key AND endDate is NULL");
		query.setParameter(PERSON_ID, personId);
		query.setParameter(DATA_TYPE_KEY, NameType.LEGAL_NAME.index());
		
		boolean matchFound = false;
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final Names bean = (Names) it.next();
			
			// If the incoming name is from ISIS, we need to do an exact match on the components of name.
			if (namesStagingBean.getImportFrom().equals(BatchDataSource.ISIS.toString())) {
				
				// Did we find a match?
				if (Utility.areStringFieldsEqualIgnoreCase(namesStagingBean.getFirstName(), bean.getFirstName()) &&
						Utility.areStringFieldsEqualIgnoreCase(namesStagingBean.getMiddleNames(), bean.getMiddleNames()) &&
						Utility.areStringFieldsEqualIgnoreCase(namesStagingBean.getLastName(), bean.getLastName()) &&
						Utility.areStringFieldsEqualIgnoreCase(namesStagingBean.getSuffix(), bean.getSuffix())) {
					matchFound =  true;
				}
				
				// No match, so expire existing name, because we are going to add a new one.
				else {
					expireName(db, bean, namesStagingBean.getImportFrom());
				}
			}
			
			// Non-ISIS names can use match codes.
			else {
				
				// Did we find a match using a match code?
				if (namesStagingBean.getNameMatchCode().equals(bean.getNameMatchCode())) {
					matchFound = true;
				}
				
				// No match, so expire existing name, because we are going to add a new one.
				else {
					expireName(db, bean, namesStagingBean.getImportFrom());
				}
			}	
		}
		
		// We did not find a match, so its time to add a new name.
		if (! matchFound) {
			addName(db, namesStagingBean);
		}
		
	}
	
	/**
	 * This method is used to add a new legal name to the database.
	 * @param db contains the database session.
	 * @param namesStagingBean contains the names bean.
	 */
	public void addName(final StatelessSession db, final NamesStaging namesStagingBean) {
		
		final Names bean = new Names();
		final Date d = new Date();
		
		bean.setPersonId(namesStagingBean.getPersonId());
		
		bean.setDataTypeKey(NameType.LEGAL_NAME.index());
		bean.setDocumentTypeKey(null);

		bean.setNameMatchCode(namesStagingBean.getNameMatchCode());

		bean.setFirstName(namesStagingBean.getFirstName());
		bean.setMiddleNames(namesStagingBean.getMiddleNames());
		bean.setLastName(namesStagingBean.getLastName());
		bean.setSuffix(namesStagingBean.getSuffix());

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(namesStagingBean.getImportFrom());
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(namesStagingBean.getImportFrom());
		bean.setLastUpdateOn(d);

		bean.setImportFrom(namesStagingBean.getImportFrom());
		bean.setImportDate(d);
		
		db.insert(bean);
		
		setNewNamesBean(bean);
	}
	
	/**
	 * This method is used to expire an old names bean, because a new one will be created.
	 * @param db contains the database session.
	 * @param bean contains the bean that will be expired.
	 * @param updatedBy contains the updated by user.
	 */
	public void expireName(final StatelessSession db, final Names bean, final String updatedBy) {
		final Date d = new Date();
		
		bean.setEndDate(d);
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		db.update(bean);
		
		setOldNamesBean(bean);

	}
	
	/**
	 * This method is used to obtain all of the names for a person from the names staging table.
	 * @param personId contains the person to do the search for.
	 */
	public void getAllStagedNamesForPerson(final long personId) {

		setIsisName(null);
		setIbisName(null);
		setHmcName(null);
		setUpdatedName(null);
		
		final StatelessSession db = getStatelessSession(); 
		final Query query = db.createQuery("from NamesStaging where personId = :person_id order by importDate");
		query.setParameter(PERSON_ID, personId);
		
		// Most recently updated name
		setUpdatedName((NamesStaging)query.list().get(0));
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			
			final NamesStaging bean = (NamesStaging) it.next();
			final String importFrom = bean.getImportFrom();
			
			// IBIS Name.
			if (importFrom.equalsIgnoreCase(BatchDataSource.IBIS.toString())) {
				setIbisName(bean);
			}
			
			// ISIS Name.
			else if (importFrom.equalsIgnoreCase(BatchDataSource.ISIS.toString())) {
				setIsisName(bean);
			}
			
			// HMC Name.
			else if (importFrom.equalsIgnoreCase(BatchDataSource.HMC.toString())) {
				setHmcName(bean);
			}
		}
		
	}
	
	/**
	 * This method is used to obtain the current semester code.
	 * @return will return the semester code.
	 */
	public String getCurrentSemesterCode() {
		
		final StatelessSession db = getStatelessSession();
		final Date d = new Date();
		final Query query = db.createQuery("from Semesters where :now >= semStartDate AND :now < semEndDate");
		query.setParameter(NOW, d);
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Semesters bean = (Semesters) it.next();
			return bean.getSemesterCode();
		}
		return null;
		
	}
	
	/**
	 * This method is used to obtain the value of the Student Aid flag.
	 * @param personId contains the person to be search for.
	 */
	public void getStudentAidFlag(final long personId) {
		
		setStudentAid(false);
		
		final String semesterCode = getCurrentSemesterCode();
		final StatelessSession db = getStatelessSession();
		
		// Obtain the student record for the current semester.
		final Query query = db.createQuery("from Student where personId = :person_id AND semesterCode = :semester_code");
		query.setParameter(PERSON_ID, personId);
		query.setParameter(SEMESTER_CODE, semesterCode);
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final Student bean = (Student) it.next();
			
			// Call Validate to ensure the student aid flag is a Y/N value.
			final String flag = Validate.isValidYesNo(bean.getStudentAidFlag());
			if (flag.equalsIgnoreCase("Y")) {
				setStudentAid(true);
			}
			else {
				setStudentAid(false);
			}
		}
		
	}

	/**
	 * This helper method is used to obtains all of the affiliations for a person and determine whether they are 
	 * either an employee or a student or both.
	 * @param personId contains the person identifier parameter.
	 */
	public void getAndEvaluateAffiliations(final long personId) {
		
		setStudent(false);
		setEmployee(false);
		
		final StatelessSession db = getStatelessSession();
		final Query query = db.createQuery("from PersonAffiliation where personId = :person_id and endDate is null");
		query.setParameter(PERSON_ID, personId);
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final PersonAffiliation bean = (PersonAffiliation) it.next();
			final AffiliationsType affiliationsType = AffiliationsType.get(bean.getAffiliationKey());

			if (affiliationsType == AffiliationsType.STUDENT) {
				setStudent(true);
			}
			else if (affiliationsType == AffiliationsType.STAFF ||
					affiliationsType == AffiliationsType.FACULTY ||
					affiliationsType == AffiliationsType.EMPLOYEE ||
					affiliationsType == AffiliationsType.EMERITUS ||
					affiliationsType == AffiliationsType.RETIREE) {
				setEmployee(true);
				// Check is a Hershey employee affiliation is active
				if (bean.getLastUpdateBy().equals(BatchDataSource.HMC.toString())) {
					setEmployeeHMC(true);
				}
			}
		}
		
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
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(boolean student) {
		this.student = student;
	}

	/**
	 * @return the student
	 */
	public boolean isStudent() {
		return student;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(boolean employee) {
		this.employee = employee;
	}

	/**
	 * @return the employee
	 */
	public boolean isEmployee() {
		return employee;
	}

	/**
	 * @param studentAid the studentAid to set
	 */
	public void setStudentAid(boolean studentAid) {
		this.studentAid = studentAid;
	}

	/**
	 * @return the studentAid
	 */
	public boolean hasStudentAid() {
		return studentAid;
	}

	/**
	 * @param isisName the isisName to set
	 */
	public void setIsisName(NamesStaging isisName) {
		this.isisName = isisName;
	}

	/**
	 * @return the isisName
	 */
	public NamesStaging getIsisName() {
		return isisName;
	}

	/**
	 * @param oldNamesBean the oldNamesBean to set
	 */
	public void setOldNamesBean(Names oldNamesBean) {
		this.oldNamesBean = oldNamesBean;
	}

	/**
	 * @return the oldNamesBean
	 */
	public Names getOldNamesBean() {
		return oldNamesBean;
	}

	/**
	 * @param newNamesBean the newNamesBean to set
	 */
	public void setNewNamesBean(Names newNamesBean) {
		this.newNamesBean = newNamesBean;
	}

	/**
	 * @return the newNamesBean
	 */
	public Names getNewNamesBean() {
		return newNamesBean;
	}

	/**
	 * @param ibisName the ibisName to set
	 */
	public void setIbisName(NamesStaging ibisName) {
		this.ibisName = ibisName;
	}

	/**
	 * @return the ibisName
	 */
	public NamesStaging getIbisName() {
		return ibisName;
	}

	/**
	 * @param hmcName the hmcName to set
	 */
	public void setHmcName(NamesStaging hmcName) {
		this.hmcName = hmcName;
	}

	/**
	 * @return the hmcName
	 */
	public NamesStaging getHmcName() {
		return hmcName;
	}

	/**
	 * @return the updatedName
	 */
	public NamesStaging getUpdatedName() {
		return updatedName;
	}

	/**
	 * @param updatedName the updatedName to set
	 */
	public void setUpdatedName(NamesStaging updatedName) {
		this.updatedName = updatedName;
	}

	/**
	 * @return the employeeHMC
	 */
	public boolean isEmployeeHMC() {
		return employeeHMC;
	}

	/**
	 * @param employeeHMC the employeeHMC to set
	 */
	public void setEmployeeHMC(boolean employeeHMC) {
		this.employeeHMC = employeeHMC;
	}

}
