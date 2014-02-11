/* SVN FILE: $Id: AddressPostProcessor.java 7759 2013-07-24 14:12:35Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.postprocess;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import edu.psu.iam.cpr.core.database.beans.AddressStaging;
import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.beans.CampusCs;
import edu.psu.iam.cpr.core.database.beans.Country;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.MatchingAlgorithmType;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class contains the implementation of the address post processor.  Data records are evaluated to determine what the
 * new address for a person will be.
 *  
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.postprocess
 * @author $Author: jvuccolo $
 * @version $Rev: 7759 $
 * @lastrevision $Date: 2013-07-24 10:12:35 -0400 (Wed, 24 Jul 2013) $
 */
public class AddressPostProcessor {
	
	/** Data type key constant */
	private static final String DATA_TYPE_KEY = "data_type_key";

	/** person id constant */
	private static final String PERSON_ID = "person_id";
	
	/** active flag constant */
	private static final String ACTIVE_FLAG = "active_flag";
	
	/** yes constant */
	private static final String YES = "Y";
	
	/** no constant */
	private static final String NO = "N";
	
	/** constant for the integer number 1 */
	private static final int ONE = 1;
	
	/** Contains an instance of the messaging core object which represents a connection to the messaging system */
	private final MessagingCore messagingCore;
	
	/** Contains an open database connection for a stateless session.  statelessSession cannot be final because if there is an error
	 * during batch processing, a new database session will have to be created.
	 */
	private StatelessSession statelessSession;
	
	/** Contains standard hibernate database session, would only be used by the services */
	private Session session;
	
	/** Mapping of the campus code to the campus key */
	private Map<String, Long> campusMap = null;
	
	/** Mapping of the three digit country code to the country key */
	private Map<String, Long> countryThreeMap = null;
	
	/** Mapping of the two digit country code to the country key */
	private Map<String, Long> countryTwoMap = null;
	
	/** Boolean that indicates whether a person is a student. */
	private boolean student = false;
	
	/** Boolean that indicates whether a person is an employee */
	private boolean employee = false;
	
	/** Contains the old names bean */
	private Addresses oldAddressBean = null;
	
	/** Contains the new names bean */
	private Addresses newAddressBean = null;
	
	/**
	 * Default constructor.
	 */
	public AddressPostProcessor() {
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
	public AddressPostProcessor(final StatelessSession statelessSession, final MessagingCore messagingCore) {
		super();
		this.statelessSession = statelessSession;
		this.messagingCore = messagingCore;
		generateMaps();
	}
	
	/**
	 * Constructor.
	 * @param session contains a reference to the standard hibernate session.
	 * @param messagingCore contains a reference to a messaging core instance.
	 */
	public AddressPostProcessor(final Session session, final MessagingCore messagingCore) {
		super();
		this.session = session;
		this.messagingCore = messagingCore;
	}
	
	/**
	 * This method is used to reset history beans if this class is used in batch mode.
	 */
	public void resetHistoryBeans() {
		oldAddressBean = null;
		newAddressBean = null;
	}
	
	
	/**
	 * This method is used to process an address change for a person and determine what records need to be reconciled based on the 
	 * type of change.
	 * @param personId contains the person identifier whose address was changed.
	 * @param addressType contains the type of address to be processed.
	 */
	public void processAddressChange(final long personId, final AddressType addressType) {
		
		resetHistoryBeans();
		
		// For the person, find all of the address changes, order by descending order, so the most recent one will be at the top.
		final StatelessSession db = getStatelessSession();
		final Query query = db.createQuery("from AddressStaging where personId = :person_id AND dataTypeKey = :data_type_key ORDER BY importDate DESC");
		query.setParameter(PERSON_ID, personId);
		query.setParameter(DATA_TYPE_KEY, addressType.index());

		final int numberOfResults = query.list().size();

		AddressStaging bean = null;
		
		// Did we get any results back?
		if (numberOfResults >= ONE) {
			
			// The first result is the most recent.
			bean = (AddressStaging) query.list().get(0);
			
			// If there is only one address change, that address should be set as the new address
			if (numberOfResults == ONE) {
				setChangedAddress(personId, addressType, bean);
			}
			
			// If there is more than one address, we need to apply the business rules.
			else if (numberOfResults > ONE) {
				applyAddressRules(personId, addressType, bean);
			}	
		}
	}
	
	/**
	 * This method is used to apply the address rules to determine what address to store for the user.
	 * @param personId contains the person identifier.
	 * @param addressType contains the type of address that is being processed.
	 * @param bean contains the bean from the address staging table.
	 */
	public void applyAddressRules(final long personId, final AddressType addressType, final AddressStaging bean) {
		
		// Get all of the affiliations.
		getAndEvaluateAffiliations(personId);
		
		AddressStaging newStagedAddress = null;

		// If this in an employee, use the address from Hershey or IBIS
		if (isEmployee()) {
			if (BatchDataSource.HMC.toString().equals(bean.getImportFrom())) {
				newStagedAddress = bean;
			}
			else if (BatchDataSource.IBIS.toString().equals(bean.getImportFrom())) {
				newStagedAddress = bean;
			}
		}
		else if (isStudent()) {
			if (BatchDataSource.ISIS.toString().equals(bean.getImportFrom())) {
				newStagedAddress = bean;
			}
		}
		else if (BatchDataSource.CIDR.toString().equals(bean.getImportFrom())) {
				newStagedAddress = bean;
			}
	
		
		if (newStagedAddress != null) {
			setChangedAddress(personId, addressType, newStagedAddress);
		}
	}

	/**
	 * This is the method that sets the final permanent address.
	 * @param personId contains the person identifier whose address is to be set.
	 * @param addressType contains the type of address to be set.
	 * @param addressStagingBean contains the address to be set.
	 */
	public void setChangedAddress(final long personId, final AddressType addressType, final AddressStaging addressStagingBean) {
		
		final StatelessSession db = getStatelessSession();
		
		final Query query = db.createQuery("from Addresses where personId = :person_id AND dataTypeKey = :data_type_key AND endDate is NULL");
		query.setParameter(PERSON_ID, personId);
		query.setParameter(DATA_TYPE_KEY, addressType.index());
		
		boolean matchFound = false;
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final Addresses bean = (Addresses) it.next();

			// Did we find a match using a match code?
			if (CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_MATCHING_ALGORITHM.toString()).equals(
					MatchingAlgorithmType.PENN_STATE.toString())) {
				if (Utility.areStringFieldsEqual(addressStagingBean.getAddressMatchCode(), bean.getAddressMatchCode()) &&
						Utility.areStringFieldsEqual(addressStagingBean.getCityMatchCode(), bean.getCityMatchCode())) {
					matchFound = true;
				}
				// No match, so expire existing address, because we are going to add a new one.
				else {
					expireAddress(db, bean, addressStagingBean.getImportFrom());
				}
			}
			else {
				if (Utility.areStringFieldsEqual(addressStagingBean.getAddress1(), bean.getAddress1()) &&
						Utility.areStringFieldsEqual(addressStagingBean.getCity(), bean.getCity())) {
					matchFound = true;
				}
				// No match, so expire existing address, because we are going to add a new one.
				else {
					expireAddress(db, bean, addressStagingBean.getImportFrom());
				}
			}	
		}
		// We did not find a match, so its time to add a new name.
		if (!matchFound) {
			addAddress(db, addressType, addressStagingBean);
		}
	}
	
	/**
	 * This method is used to add a new permanent address to the database.
	 * @param db contains the database session.
	 * @param addressType contains the type of address to be added.
	 * @param addressStagingBean contains the address bean.
	 */
	public void addAddress(final StatelessSession db, final AddressType addressType, final AddressStaging addressStagingBean) {
		
		final Addresses bean = new Addresses();
		final Date d = new Date();
		
		bean.setPersonId(addressStagingBean.getPersonId());
		
		bean.setDataTypeKey(addressType.index());
		bean.setDocumentTypeKey(null);

		// get the last group id for this address type for this person so can set the correct group id
		Long groupId = null;
		final Query maxQuery = db.createQuery(
				"select max(groupId) from Addresses where personId = :person_id and dataTypeKey = :data_type_key");
		maxQuery.setParameter(PERSON_ID, bean.getPersonId());
		maxQuery.setParameter(DATA_TYPE_KEY, bean.getDataTypeKey());
		groupId = (Long) maxQuery.list().get(0);
		if (groupId == null) {
			groupId = 1L;
		}
		else {
			groupId++;
		}
		bean.setGroupId(groupId);
		bean.setAddressMatchCode(addressStagingBean.getAddressMatchCode());

		if (addressStagingBean.getCampusCode() != null) {
			bean.setCampusCodeKey(campusMap.get(addressStagingBean.getCampusCode()));
		}
		
		if (addressStagingBean.getCountryCodeTwo() != null) {
			bean.setCountryKey(countryTwoMap.get(addressStagingBean.getCountryCodeTwo()));
		}
		else if (addressStagingBean.getCountryCodeThree() != null) {
			bean.setCountryKey(countryThreeMap.get(addressStagingBean.getCountryCodeThree()));
		}
		
		bean.setProvince(addressStagingBean.getProvince());
		
		bean.setAddress1(addressStagingBean.getAddress1());
		bean.setAddress2(addressStagingBean.getAddress2());
		bean.setAddress3(addressStagingBean.getAddress3());
		
		bean.setCity(addressStagingBean.getCity());
		bean.setCityMatchCode(addressStagingBean.getCityMatchCode());
		
		bean.setState(addressStagingBean.getState());
		bean.setPostalCode(addressStagingBean.getPostalCode());

		bean.setPrimaryFlag(NO);
		bean.setVerifiedFlag(NO);
		bean.setDoNotVerifyFlag(NO);
		
		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(addressStagingBean.getImportFrom());
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(addressStagingBean.getImportFrom());
		bean.setLastUpdateOn(d);

		bean.setImportFrom(addressStagingBean.getImportFrom());
		bean.setImportDate(d);
		
		db.insert(bean);
		
		setNewAddressBean(bean);
	}
	
	/**
	 * This method is used to expire an old names bean, because a new one will be created.
	 * @param db contains the database session.
	 * @param bean contains the bean that will be expired.
	 * @param updatedBy contains the updated by user.
	 */
	public void expireAddress(final StatelessSession db, final Addresses bean, final String updatedBy) {
		final Date d = new Date();
		
		bean.setEndDate(d);
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		db.update(bean);
		
		setOldAddressBean(bean);

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
			}
		}
		
	}
	
	/**
	 * This method will generate all the mappings of codes to keys
	 */
	private void generateMaps() {
		generateCampusMap();
		generateCountryMaps();
	}
	
	/**
	 * This method will generate the campus mappings of codes to keys
	 */
	private void generateCampusMap() {
		
		campusMap = new HashMap<String, Long>();
		
		final StatelessSession db = getStatelessSession();
		
		if (db != null) {
			final Query query = db.createQuery("from CampusCs where activeFlag = :active_flag");
			query.setParameter(ACTIVE_FLAG, YES);
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				final CampusCs bean = (CampusCs) it.next();
				campusMap.put(bean.getCampusCode(), bean.getCampusCodeKey());
			}
		}
	}

	/**
	 * The method will generate the country mappings of codes to keys
	 */
	private void generateCountryMaps() {
		
		countryThreeMap = new HashMap<String, Long>();
		countryTwoMap = new HashMap<String, Long>();
		
		final StatelessSession db = getStatelessSession();
		
		if (db != null) {
		
			final Query query = db.createQuery("from Country where endDate is NULL");
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				final Country bean = (Country) it.next();
				countryTwoMap.put(bean.getCountryCodeTwo(), bean.getCountryKey());
				countryThreeMap.put(bean.getCountryCodeThree(), bean.getCountryKey());
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
	 * @return the oldAddressBean
	 */
	public Addresses getOldAddressBean() {
		return oldAddressBean;
	}

	/**
	 * @param oldAddressBean the oldAddressBean to set
	 */
	public void setOldAddressBean(Addresses oldAddressBean) {
		this.oldAddressBean = oldAddressBean;
	}

	/**
	 * @return the newAddressBean
	 */
	public Addresses getNewAddressBean() {
		return newAddressBean;
	}

	/**
	 * @param newAddressBean the newAddressBean to set
	 */
	public void setNewAddressBean(Addresses newAddressBean) {
		this.newAddressBean = newAddressBean;
	}
}
