/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.batch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

import edu.psu.iam.cpr.core.api.returns.MatchCodeServiceReturn;
import edu.psu.iam.cpr.core.api.returns.TransformServiceReturn;
import edu.psu.iam.cpr.core.database.beans.*;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateHelper;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.MatchingAlgorithmType;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.DataQualityService;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class contains the database implementation for the person biographical class.  Information from a batch
 * file (ISIS, IBIS, HMC, and others) will be sent to methods in this class to be updated in the database and for
 * notification messages to be sent.
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.batch
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class PersonBio {

	/** Constant United States */
	private static final String US = "US";

	/** Constant United States of America */
	private static final String USA = "USA";

	/** Constant import from */
	private static final String IMPORT_FROM = "import_from";

	/** Two character country code length */
	private static final int TWO_CHAR_COUNTRY_LENGTH = 2;

	/** Three charcter country code length */
	private static final int THREE_CHAR_COUNTRY_LENGTH = 3;

	/** data type key constant */
	private static final String DATA_TYPE_KEY = "data_type_key";

	/** person id constant */
	protected static final String PERSON_ID = "person_id";

	/** DateFormat for Date of Birth */
	private final DateFormat dateOfBirthFormat = new SimpleDateFormat("MMddyyyy");

	/** Contains an open database connection for a stateless session.  databaseSession cannot be final because if there is an error
	 * during batch processing, a new database session will have to be created.
	 */
	private StatelessSession databaseSession;

	/** Contains the batch data source */
	private final BatchDataSource batchDataSource;

	/** Contains a reference to the data quality service. */
	private final DataQualityService dataQualityService;

	/** Contains the person identifier that was found or created by looking up the PSU ID number */
	private Long personId = null;

	/** Contains the PSU ID number associated with the person */
	private String psuIdNumber = null;

	/** Contains the user's primary userid */
	private String primaryUserid = null;

	/** Constant that defines the psu email domain */
	private static final String UNIVERSITY_EMAIL_DOMAIN = CprProperties.INSTANCE.getProperties().getProperty(
																	CprPropertyName.CPR_UNIVERSITY_EMAIL_DOMAIN.toString());

	/** Contains a reference to the old address bean */
	private Addresses oldAddress = null;

	/** Contains a reference to the old date of birth bean */
	private DateOfBirth oldDateOfBirth = null;

	/** Contains a reference to the old email address bean */
	private EmailAddress oldEmailAddress = null;

	/** Contains a reference to the old gender bean */
	private PersonGender oldPersonGender = null;

	/** Contains a reference to the old names bean */
	private Names oldName = null;

	/** Contains a reference to the old phone bean */
	private Phones oldPhone = null;

	/** Contains a reference to the old psu id bean */
	private PsuId oldPsuId = null;

	/** Contains the old userid */
	private Userid oldUserid = null;

	/** Contains a reference to the new address bean */
	private Addresses newAddress = null;

	/** Contains a reference to the new date of birth bean */
	private DateOfBirth newDateOfBirth = null;

	/** Contains a reference to the new email address bean */
	private EmailAddress newEmailAddress = null;

	/** Contains a reference to the new gender bean */
	private PersonGender newPersonGender = null;

	/** Contains a reference to the new names bean */
	private Names newName = null;

	/** Contains a reference to the new phones bean */
	private Phones newPhone = null;

	/** Contains a reference to the new psu id bean */
	private PsuId newPsuId = null;

	/** Contains a reference to the new userid */
	private Userid newUserid = null;

	/** Contains the date that all of the records use for their updates */
	private final Date updateDate = new Date();

	/** Instance of logger */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(PersonBio.class);
	
	/**
	 * Constructor
	 */
	public PersonBio() {
		super();
		databaseSession = null;
		batchDataSource = null;
		dataQualityService = null;
	}

	/**
	 * Constructor.
	 * @param databaseSession contains the hibernate database session.
	 * @param batchDataSource contains the source of the data feed.
	 * @param dataQualityService contains the data quality service.
	 */
	public PersonBio(final StatelessSession databaseSession, final BatchDataSource batchDataSource, final DataQualityService dataQualityService) {
		super();
		this.databaseSession = databaseSession;
		this.batchDataSource = batchDataSource;
		this.dataQualityService = dataQualityService;
	}

	/**
	 * This method is used to reset all of the history beans to null.  It will be called after the message notifications are sent.
	 * This is mainly done because we are only going to keep one instance of the PersonBio class for a particular batch program.
	 */
	public void resetHistoryBeans() {

		oldAddress 		= null;
		oldDateOfBirth 	= null;
		oldEmailAddress = null;
		oldPersonGender = null;
		oldName 		= null;
		oldPhone 		= null;
		oldPsuId 		= null;
		oldUserid 		= null;

		newAddress 		= null;
		newDateOfBirth 	= null;
		newEmailAddress = null;
		newPersonGender = null;
		newName 		= null;
		newPhone 		= null;
		newPsuId 		= null;
		newUserid 		= null;
	}

	/**
	 * This routine is used to search for a person in the database using the PSU ID number.  If the person
	 * was found, the personId variable will be set.
	 * @param psuId contains the PSU ID to be search for.
	 * @return will return the found person id.
	 */
	public Long findPerson(final String psuId) {

		// Reset some variables.
		setPersonId(null);
		setPsuIdNumber(null);
		setPrimaryUserid(null);

		// Find the person using the PSU ID.
		final Query query = getDatabaseSession().createQuery("from PsuId where psuId = :psu_id and endDate is null");
		query.setParameter("psu_id", psuId);
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final PsuId psuIdBean = (PsuId) it.next();
			setPersonId(psuIdBean.getPersonId());
			setPsuIdNumber(psuIdBean.getPsuId());
		}

		// If the person exists, attempt to find their userid.
		if (personId != null) {
			findPrimaryUserid();
		}

		return getPersonId();

	}

	/**
	 * This routine is used to search for a person in the database using the person identifier.
	 * @param personId contains the person identifier to search for.
	 * @return will return the found person id.
	 */
	public Long findPersonUsingPersonId(final Long personId) {

		// Reset some variables.
		setPersonId(null);
		setPsuIdNumber(null);
		setPrimaryUserid(null);

		// Find the person using the PSU ID.
		final Query query = getDatabaseSession().createQuery("from PsuId where personId = :person_id and endDate is null");
		query.setParameter(PERSON_ID, personId);
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final PsuId psuIdBean = (PsuId) it.next();
			setPersonId(psuIdBean.getPersonId());
			setPsuIdNumber(psuIdBean.getPsuId());
		}

		if (personId != null) {
			findPrimaryUserid();
		}

		return getPersonId();

	}

	/**
	 * This method is used to obtain a person's primary userid.
	 */
	public void findPrimaryUserid() {

		final Query useridQuery = getDatabaseSession().createQuery("from Userid where personId = :person_id AND primaryFlag = 'Y'");
		useridQuery.setParameter(PERSON_ID, getPersonId());
		for (final Iterator<?> it = useridQuery.list().iterator(); it.hasNext(); ) {
			final Userid bean = (Userid) it.next();
			setPrimaryUserid(bean.getUserid());
		}
	}

	/**
	 * This method is used to create a new person record.  This will be executed whenever a person is not found
	 * in the CPR using the PSU ID number.
	 */
	public void addPerson() {

		final String updatedBy = getBatchDataSource().toString();
		final Date d = getUpdateDate();

		final Person bean = new Person();

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

		bean.setImportFrom(updatedBy);
		bean.setImportDate(d);

		getDatabaseSession().insert(bean);

		setPersonId(bean.getPersonId()); 
		
	}
	
	/**
	 * This method is used to update a PSU ID record or add a new one.
	 * @param psuId contains the PSU ID to be updated.
	 */
	public void updatePsuId(final String psuId) {

		// Do not attempt to update null PSU IDs.
		if (ValidateHelper.isFieldEmpty(psuId)) {
			return;
		}

		boolean matchFound = false;

		// Perform a query to find the active PSU IDs for the user, there should only ever be one.
		final StatelessSession session = getDatabaseSession();
		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();

		final Query query = session.createQuery("from PsuId where personId = :person_id and endDate is null");
		query.setParameter(PERSON_ID, getPersonId());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final PsuId bean = (PsuId) it.next();

			// Does the bean's PSU ID, equal the incoming psu id, if so we can ignore the update.
			if (bean.getPsuId().equals(psuId)) {
				matchFound = true;
			}

			// PSU ID change, so expire the existing one.
			else {
				bean.setEndDate(d);
				bean.setLastUpdateBy(updatedBy);
				bean.setLastUpdateOn(d);
				session.update(bean);
				setOldPsuId(bean);
			}
		}

		// If we did not find a match, we need to add the new PSU ID.
		if (! matchFound) {
			addPsuId(psuId);
		}
	}

	/**
	 * This method is used to create a new PSU ID record.
	 * @param psuId contains the PSU ID to be added.
	 */
	public void addPsuId(final String psuId) {

		// Do not store null PSU ID #s.
		if (ValidateHelper.isFieldEmpty(psuId)) {
			return;
		}

		final PsuId bean = new PsuId();
		final String updatedBy = getBatchDataSource().toString();
		final Date d = getUpdateDate();

		bean.setPersonId(getPersonId());
		bean.setPsuId(psuId);

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

		bean.setImportFrom(updatedBy);
		bean.setImportDate(d);

		getDatabaseSession().insert(bean);
		setNewPsuId(bean);
		setPsuIdNumber(psuId);

	}

	/**
	 * This method is used to update gender record with a new gender if one differs from what's stored in the database.
	 * @param genderType contains the incoming gender type.
	 */
	public void updateGender(final GenderType genderType) {

		// do not attempt to store a null gender type.
		if (genderType == null) {
			return;
		}

		boolean matchFound = false;

		// Perform a query to find the active genders for the user, there should only ever be one.
		final StatelessSession session = getDatabaseSession();
		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();

		final Query query = session.createQuery("from PersonGender where personId = :person_id and endDate is null");
		query.setParameter(PERSON_ID, getPersonId());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final PersonGender bean = (PersonGender) it.next();

			// Does the bean's Gender, equal the incoming gender, if so we can ignore the update.
			if (bean.getDataTypeKey().equals(genderType.index())) {
				matchFound = true;
			}

			// Gender change, so expire the existing one.
			else {
				bean.setEndDate(d);
				bean.setLastUpdateBy(updatedBy);
				bean.setLastUpdateOn(d);
				session.update(bean);
				setOldPersonGender(bean);
			}
		}

		// If we did not find a match, we need to add the new gender.
		if (! matchFound) {
			addGender(genderType);
		}

	}

	/**
	 * This method is used to add a new gender record.
	 * @param genderType contains the gender to be added.
	 */
	public void addGender(final GenderType genderType) {

		// Do not attempt to store a null gender.
		if (genderType == null) {
			return;
		}

		final PersonGender bean = new PersonGender();
		final String updatedBy = getBatchDataSource().toString();
		final Date d = getUpdateDate();

		bean.setPersonId(getPersonId());
		bean.setDataTypeKey(genderType.index());

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setImportFrom(updatedBy);
		bean.setImportDate(d);

		getDatabaseSession().insert(bean);
		setNewPersonGender(bean);
	}

	/**
	 * This method is used to update/add a date of birth for a person to the CPR. If the date of birth is NULL it will not be stored.
	 *
	 * @param dob The Date of birth.
	 */
	public void updateDateOfBirth(final Date dob) throws ParseException {
		if (dob == null) {
			return;
		}

		final String dobString = dateOfBirthFormat.format(dob);
		updateDateOfBirth(dobString);
	}

	/**
	 * This method is used to update/add a date of birth record.  The input is a dob string formatted as MMDDYYYY string.
	 * A check will be made to ensure the DOB does not already exist.  Otherwise, it will expire the existing on and add
	 * a new one.
	 * @param dobString contains the date of birth string formatted as MMDDYYYY.
	 * @throws ParseException will be thrown if the Date of Birth cannot be parsed.
	 */
	public void updateDateOfBirth(final String dobString) throws ParseException {

		// do not attempt to store a null date of birth.
		if (ValidateHelper.isFieldEmpty(dobString)) {
			return;
		}

		boolean matchFound = false;

		// Perform a query to find the active date of births for the user, there should only ever be one.
		final StatelessSession session = getDatabaseSession();
		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();

		final Query query = session.createQuery("from DateOfBirth where personId = :person_id and endDate is null");
		query.setParameter(PERSON_ID, getPersonId());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final DateOfBirth bean = (DateOfBirth) it.next();

			// Does the bean's dobString, equal the incoming dobString, if so we can ignore the update.
			if (bean.getDobChar().equals(dobString)) {
				matchFound = true;
			}

			// Date of birth change, so expire the existing one.
			else {
				bean.setEndDate(d);
				bean.setLastUpdateBy(updatedBy);
				bean.setLastUpdateOn(d);
				session.update(bean);
				setOldDateOfBirth(bean);
			}
		}

		// If we did not find a match, we need to add the new date of birth.
		if (! matchFound) {
			addDateOfBirth(dobString);
		}
	}

	/**
	 * This method is used to add a date of birth for a person to the CPR. If the date of birth is NULL it will not be stored.
	 *
	 * @param dob The Date of birth.
	 */
	public void addDateOfBirth(final Date dob) throws ParseException {
		if (dob == null) {
			return;
		}

		final String dobString = dateOfBirthFormat.format(dob);
		addDateOfBirth(dobString);
	}

	/**
	 * This method is used to add a date of birth for a person to the CPR.  If the date of birth is NULL it will not be stored.
	 * @param dobString contains the date of birth string, formatted to be MMDDYYYY, where MM = 2 digit month,
	 * DD = 2 digit year, and YYYY = 4 digit year.
	 * @throws ParseException will be thrown if it cannot parse the date.
	 */
	public void addDateOfBirth(final String dobString) throws ParseException {

		// Do not store NULL date of birth records.
		if (ValidateHelper.isFieldEmpty(dobString)) {
			return;
		}

		final DateOfBirth bean = new DateOfBirth();
		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();

		bean.setPersonId(getPersonId());

		bean.setDobChar(dobString);

		// Attempt to convert the DOB string representation to an actual date.
		if (dobString.endsWith("0000")) {
			bean.setDob(null);
		} else {
			bean.setDob(dateOfBirthFormat.parse(dobString));
		}

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

		bean.setImportFrom(updatedBy);
		bean.setImportDate(d);

		getDatabaseSession().insert(bean);
		setNewDateOfBirth(bean);
	}

	/**
	 * This method is used to determine the email address by examining the base domain of the email address.  Based on the results
	 * of the query, it will set the EmailAddressType enum.
	 * @param emailAddress contains the email address to be examined.
	 * @return will return the email address type.
	 */
	public EmailAddressType getEmailAddressType(final String emailAddress) {

		final int start = emailAddress.length() - UNIVERSITY_EMAIL_DOMAIN.length();
		if (emailAddress.toLowerCase().lastIndexOf(UNIVERSITY_EMAIL_DOMAIN, start) >= 0) {
			return EmailAddressType.UNIVERSITY_EMAIL;
		}
		else {
			return EmailAddressType.OTHER_EMAIL;
		}
	}

	/**
	 * This method is used to determine how to process an email address update.  It will search to see if there is an email address
	 * that matches the incoming email address.  If so the update is ignored.  Otherwise, the existing one is expired and the new one
	 * is added.
	 * @param emailAddress contains the email address to be added.
	 */
	public void updateEmailAddress(final String emailAddress) {

		// If the email address is null, ignore the update.
		if (ValidateHelper.isFieldEmpty(emailAddress)) {
			return;
		}

		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();
		final StatelessSession session = getDatabaseSession();
		final EmailAddressType emailAddressType = getEmailAddressType(emailAddress);
		boolean matchFound = false;

		// Perform a query to find the active email addresses for the user, there should only ever be one.
		final Query query = session.createQuery(
				"from EmailAddress where personId = :person_id and endDate is null and dataTypeKey = :data_type_key");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(DATA_TYPE_KEY, emailAddressType.index());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final EmailAddress bean = (EmailAddress) it.next();

			// Does the bean's email address equal the incoming email address, if so we can ignore the update.
			if (bean.getEmailAddress().equalsIgnoreCase(emailAddress)) {
				matchFound = true;
			}

			// Date of birth change, so expire the existing one.
			else {
				bean.setEndDate(d);
				bean.setLastUpdateBy(updatedBy);
				bean.setLastUpdateOn(d);
				session.update(bean);
				setOldEmailAddress(bean);
			}
		}

		// If we did not find a match, we need to add the new email address.
		if (! matchFound) {
			addEmailAddress(emailAddress);
		}
	}

	/**
	 * This method is used to store an email address in the database.  It determines the type of email address by looking at the domain
	 * of the address.
	 * @param emailAddress contains the email address to be stored.
	 */
	public void addEmailAddress(final String emailAddress) {

		if (ValidateHelper.isFieldEmpty(emailAddress)) {
			return;
		}

		final EmailAddress bean = new EmailAddress();
		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();

		bean.setPersonId(getPersonId());
		bean.setDataTypeKey(getEmailAddressType(emailAddress).index());
		bean.setEmailAddress(emailAddress);

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

		bean.setImportFrom(updatedBy);
		bean.setImportDate(d);

		getDatabaseSession().insert(bean);
		setNewEmailAddress(bean);
	}

	/**
	 * This method is used to update a name within the CPR.  A check is made to ensure that the name does not already exist, if it
	 * does not, the current name is expired and a new name is added.
	 * @param firstName contains the first name to be added.
	 * @param middleNames contains the middle name to be added.
	 * @param lastName contains the last name to be added.
	 * @param suffix contains the suffix to be added.
	 */
	public void updateName(final String firstName, final String middleNames, final String lastName, final String suffix) {

		// Make sure we have a last name.
		if (ValidateHelper.isFieldEmpty(lastName)) {
			return;
		}

		final String updatedBy = getBatchDataSource().toString();
		final StatelessSession session = getDatabaseSession();
		final DataQualityService dq = getDataQualityService();

		boolean skipAdd = false;

		final Query query = session.createQuery("from NamesStaging where personId = :person_id AND importFrom = :import_from");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(IMPORT_FROM, updatedBy);

		// Get the match code for the input name.
		String nameMatchCode = null;
		if (CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_MATCHING_ALGORITHM.toString()).equals(
				MatchingAlgorithmType.PENN_STATE.toString())) {
			final MatchCodeServiceReturn nameMatchCodeReturn = dq.getNameMatchCode(firstName, middleNames, lastName);
			nameMatchCode = nameMatchCodeReturn.getMatchCode();
		}

		// This iterator will return at most zero or one records.  If it returns most, we have a problem, Houston!
		for (final Iterator<?> it = query.list().iterator(); it.hasNext();) {
			final NamesStaging bean = (NamesStaging) it.next();
			skipAdd = true;

			// If the data source is ISIS, we cannot do a match code compare, we just do an exact one.
			if (getBatchDataSource() == BatchDataSource.ISIS) {
				if (! (Utility.areStringFieldsEqualIgnoreCase(bean.getFirstName(), firstName) &&
						Utility.areStringFieldsEqualIgnoreCase(bean.getMiddleNames(), middleNames) &&
						Utility.areStringFieldsEqualIgnoreCase(bean.getLastName(), lastName) &&
						Utility.areStringFieldsEqualIgnoreCase(bean.getSuffix(), suffix))) {
					updateStagingName(bean, firstName, middleNames, lastName, suffix, nameMatchCode);
				}
			}

			// For other data sources we can do a match code compare.
			else {
				// If the name match codes differ, we need to do an update because the names do not match.
				if (CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_MATCHING_ALGORITHM.toString()).equals(
						MatchingAlgorithmType.PENN_STATE.toString())) {
					if (! nameMatchCode.equals(bean.getNameMatchCode())) {
						updateStagingName(bean, firstName, middleNames, lastName, suffix, nameMatchCode);
					}
					else {
						updateStagingName(bean, firstName, middleNames, lastName, suffix, nameMatchCode);
					}
				}
			}
		}

		// No name was found, so we need to add one.
		if (! skipAdd) {
			addStagingName(firstName, middleNames, lastName, suffix, nameMatchCode);
		}
	}

	/**
	 * This method is used to update an existing name within the name staging to a new name that is the result of an import.
	 * @param bean contains the names staging bean to be updated.
	 * @param firstName contains the new first name.
	 * @param middleNames contains the new middle names.
	 * @param lastName contains the new last name.
	 * @param suffix contains the new suffix.
	 * @param matchCode contains the match code.
	 */
	public void updateStagingName(final NamesStaging bean, final String firstName,
			final String middleNames, final String lastName, final String suffix, final String matchCode) {

		fillStagingNameBean(firstName, middleNames, lastName, suffix, matchCode, bean);

		getDatabaseSession().update(bean);
	}

	/**
	 * This method is used to add a record to the staging database table for names which will be used later to determine
	 * how to process the name change and pick the correct name.
	 * @param firstName contains the first name.
	 * @param middleNames contains the middle name.
	 * @param lastName contains the last name.
	 * @param suffix contains the suffix.
	 * @param matchCode contains the match code.
	 */
	public void addStagingName(final String firstName, final String middleNames, final String lastName, final String suffix, final String matchCode) {

		final NamesStaging bean = new NamesStaging();

		bean.setPersonId(getPersonId());

		fillStagingNameBean(firstName, middleNames, lastName, suffix, matchCode, bean);
		
		bean.setImportFrom(getBatchDataSource().toString());

		getDatabaseSession().insert(bean);
	}

	/**
	 * This method is used to populate the names staging bean with information from a data record.
	 * @param firstName contains the person's first name.
	 * @param middleNames contains the person's middle name.
	 * @param lastName contains the person's last name.
	 * @param suffix contains the suffix for the person's name.
	 * @param matchCode contains the name match code.
	 * @param bean contains the names staging bean that will be populated.
	 */
	private void fillStagingNameBean(final String firstName, final String middleNames, final String lastName,
			final String suffix, final String matchCode, final NamesStaging bean) {
		
		bean.setFirstName(firstName);
		bean.setMiddleNames(middleNames);
		bean.setLastName(lastName);
		bean.setSuffix(suffix);

		bean.setNameMatchCode(matchCode);

		bean.setImportDate(getUpdateDate());
	}

	/**
	 * This method is used to create a new names record in the CPR database.
	 * @param firstName contains the first name to be added.
	 * @param middleNames contains the middle name to be added.
	 * @param lastName contains the last name to be added.
	 * @param suffix contains the suffix to be added.
	 * @throws CprException will be thrown if there are any Cpr related errors.
	 */
	public void addName(final String firstName, final String middleNames, final String lastName, final String suffix) throws CprException {

		// Reuse NamesTable, so we can get at the matching code without have to rewrite it.
		final NamesTable namesTable = new NamesTable(getPersonId(), NameType.LEGAL_NAME.toString(), null,
											firstName, middleNames, lastName, suffix, null, getBatchDataSource().toString());
		final Names bean = namesTable.getNamesBean();

		// Update the fields that bean does not.
		bean.setCreatedOn(getUpdateDate());
		bean.setLastUpdateOn(getUpdateDate());
		bean.setImportDate(bean.getLastUpdateOn());
		bean.setImportFrom(bean.getLastUpdateBy());

		// Do matching.
		namesTable.getMatchCodeUsingOpenSession(getDataQualityService(), bean);

		// Save off the record.
		getDatabaseSession().insert(bean);
		setNewName(bean);

		// Make sure you save off the new name to the staging area.
		addStagingName(firstName, middleNames, lastName, suffix, bean.getNameMatchCode());
	}

	/**
	 * This method is used to update a telephone number.
	 * @param phoneType contains the type of telephone number to be updated.
	 * @param phoneNumber contains the actual telephone number.
	 * @param extension contains the extension.
	 */
	public void updatePhone(final PhoneType phoneType, final String phoneNumber, final String extension) {

		// Only do an update if we have a phone #.
		if (ValidateHelper.isFieldEmpty(phoneNumber)) {
			return;
		}
		
		final String localPhoneNumber = phoneNumber.replaceAll("\\s","");
		final StatelessSession session = getDatabaseSession();
		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();
		boolean matchFound = false;

		final Query query = session.createQuery("from Phones where personId = :person_id AND dataTypeKey = :data_type_key AND endDate IS NULL");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(DATA_TYPE_KEY, phoneType.index());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final Phones bean = (Phones) it.next();
			if (Utility.areStringFieldsEqual(localPhoneNumber, bean.getPhoneNumber())) {
				matchFound = true;
			}

			// Based on the data we are receiving from the feeds this is the best we can do at this time.  Not sure what to do about
			// the home phone.
			else if (phoneType == PhoneType.LOCAL_PHONE || phoneType == PhoneType.WORK_PHONE){
				bean.setEndDate(d);
				bean.setLastUpdateBy(updatedBy);
				bean.setLastUpdateOn(d);
				session.update(bean);
				setOldPhone(bean);
			}
		}

		// If we did not find a match, we need to determine what the new group id will be.
		if (! matchFound) {

			Long groupId = null;
			final Query maxQuery = session.createQuery(
					"select max(groupId) from Phones where personId = :person_id and dataTypeKey = :data_type_key");
			maxQuery.setParameter(PERSON_ID, getPersonId());
			maxQuery.setParameter(DATA_TYPE_KEY, phoneType.index());
			groupId = (Long) maxQuery.list().get(0);
			if (groupId == null) {
				groupId = 1L;
			}
			else {
				groupId++;
			}

			addPhone(phoneType, localPhoneNumber, extension, groupId);
		}
	}

	/**
	 * This method is used to add a telephone number to the database.  It will be passed in the phone number and the extension.
	 * @param phoneType contains the type of phone number to be added.
	 * @param phoneNumber contains the phone number.
	 * @param extension contains the extension.
	 * @param groupId contains the group id #.  For new records this will be one.
	 */
	public void addPhone(final PhoneType phoneType, final String phoneNumber, final String extension, final Long groupId) {

		// Only do an add if we actually have a phone #.
		if (ValidateHelper.isFieldEmpty(phoneNumber)) {
			return;
		}

		final Phones bean = new Phones();
		final Date d = getUpdateDate();
		final String updatedBy = getBatchDataSource().toString();

		bean.setPersonId(getPersonId());

		bean.setDataTypeKey(phoneType.index());
		bean.setPhoneNumber(phoneNumber.replaceAll("\\s",""));
		bean.setExtension(extension);
		bean.setInternationalNumberFlag("N");

		bean.setPrimaryFlag("N" );
		bean.setGroupId(groupId);

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setImportFrom(updatedBy);
		bean.setImportDate(d);

		getDatabaseSession().insert(bean);
		setNewPhone(bean);
	}

	/**
	 * This method is used to update a userid record in the database.
	 * @param userid contains the userid to be updated.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public void updateUserid(final String userid) throws CprException {

		if (ValidateHelper.isFieldEmpty(userid)) {
			return;
		}

		final StatelessSession session = getDatabaseSession();
		boolean matchFound = false;

		// do a query to see if the userid to be added is not already active.
		Query query = session.createQuery("from Userid where userid = :userid AND endDate is NULL");
		query.setParameter("userid", userid);
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final Userid bean = (Userid) it.next();

			// If the userid matches the current person - great!
			if (bean.getPersonId().equals(getPersonId())) {
				matchFound = true;
			}

			// Otherwise we have a problem and cannot add this new userid.
			else {
				throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "Userid");
			}
		}

		// Determine if the user already has a userid, if so we need to add this one as a secondary userid, not a primary one.
		String primaryFlag = "Y";
		query = session.createQuery("from Userid where personId = :person_id AND endDate IS NULL");
		query.setParameter(PERSON_ID, getPersonId());
		if (query.list().size() > 0) {
			primaryFlag = "N";
		}

		if (! matchFound) {
			addUseridPrimaryFlag(userid, primaryFlag);
		}
	}

	/**
	 * This method is used to add a new userid to the database along with the specific primary flag.  NOTE: to enable some
	 * reuse, we are using the code from the Userid Table.
	 * @param userid contains the userid to be added.
	 * @param primaryFlag contains the primary flag.
	 */
	public void addUseridPrimaryFlag(final String userid, final String primaryFlag) {

		if (ValidateHelper.isFieldEmpty(userid)) {
			return;
		}

		final UseridTable useridTable = new UseridTable(getPersonId(), getBatchDataSource().toString());
		final Userid bean = useridTable.getUseridBean();

		// Set the bean values that are left as null from the creation of the UseridTable.
		bean.setUserid(userid);
		bean.setPrimaryFlag(primaryFlag);
		bean.setImportDate(bean.getLastUpdateOn());
		bean.setImportFrom(bean.getLastUpdateBy());
		bean.setCharPart(useridTable.getCharacterPart(userid));
		bean.setNumPart(useridTable.getNumberPart(userid, bean.getCharPart()));

		// Create the new record.
		getDatabaseSession().insert(bean);

		// Save off a reference for messaging.
		setNewUserid(bean);

	}

	/**
	 * This method is invoked as the result of the creation of a new person.  A new person's userid will always have their
	 * primary flag set to Y.
	 * @param userid contains the userid to be added.
	 */
	public void addUserid(final String userid) {

		addUseridPrimaryFlag(userid, "Y");

	}

	/**
	 * This method is used to update or add a new address record.  It does a check to ensure that address does not already exist
	 * for the address1 and city.
	 * @param addressType contains the type of address.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2.
	 * @param address3 contains address line #3.
	 * @param city contains the city.
	 * @param state contains the state.
	 * @param postalCode contains the postal code.
	 * @param countryCode contains the country code.
	 * @param campusCode contains the campus code.
	 * @throws CprException  will be thrown if there is a CPR releated problem.
	 */
	public void updateAddress(final AddressType addressType, final String address1, final String address2,  final String address3,  
			final String city, final String state, final String postalCode, final String countryCode, final String campusCode) 
				throws CprException {

		// Only do an update if we have an address1, and city.
		if (ValidateHelper.isFieldEmpty(address1) || ValidateHelper.isFieldEmpty(city)) {
			return;
		}

		final StatelessSession session = getDatabaseSession();
		final String updatedBy = getBatchDataSource().toString();
		final DataQualityService dq = getDataQualityService();
		boolean skipAdd = false;

		// Save off the fields, they will be updated with the transformed values if possible
		String transAddress1 = address1;
		String transAddress2 = address2;
		String transAddress3 = address3;
		String transCity = city;
		String transState = state;
		String transPostalCode = postalCode;
		String transCountryCode = countryCode;

		String addressMatchCode = null;
		String cityMatchCode = null;
		
		if (CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_MATCHING_ALGORITHM.toString()).equals(
				MatchingAlgorithmType.PENN_STATE.toString())) {
			// Transform the address.
			final TransformServiceReturn transformAddressReturn = dq.transformAddress(address1, address2, address3, city, state, postalCode, countryCode);

			// If possible use the transformed values
			if (transformAddressReturn.getStatusCode() == ReturnType.SUCCESS.index()) {
				transAddress1 = transformAddressReturn.getAddress1();
				transAddress2 = transformAddressReturn.getAddress2();
				transAddress3 = null;
				transCity = transformAddressReturn.getCity();
				transState = transformAddressReturn.getStateOrProvince();
				transPostalCode = transformAddressReturn.getPostalCode();
				transCountryCode = transformAddressReturn.getCountryCode();
			}

			// Obtain match codes for city and address.
			final MatchCodeServiceReturn addressMatch = dq.getAddressMatchCode(transAddress1, transAddress2, transAddress3);
			final MatchCodeServiceReturn cityMatch = dq.getCityMatchCode(transCity);

			if (addressMatch.getStatusCode() != ReturnType.SUCCESS.index() || cityMatch.getStatusCode() != ReturnType.SUCCESS.index()) {
				throw new CprException(ReturnType.GENERAL_DATABASE_EXCEPTION, "Unable to obtain match codes");
			}
			addressMatchCode = addressMatch.getMatchCode();
			cityMatchCode = cityMatch.getMatchCode();
		}

		final Query query = session.createQuery("from AddressStaging where personId = :person_id AND importFrom = :import_from AND dataTypeKey = :data_type_key");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(IMPORT_FROM, updatedBy);
		query.setParameter(DATA_TYPE_KEY, addressType.index());

		for (final Iterator<?> it = query.list().iterator(); it.hasNext();) {
			final AddressStaging bean = (AddressStaging) it.next();
			skipAdd = true;

			// Check to see if the match codes or any fields that are not part of a match code have changed.
			if (CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_MATCHING_ALGORITHM.toString()).equals(
					MatchingAlgorithmType.PENN_STATE.toString())) {
				if (! (Utility.areStringFieldsEqual(addressMatchCode, bean.getAddressMatchCode()) &&
						Utility.areStringFieldsEqual(cityMatchCode, bean.getCityMatchCode()) &&
						Utility.areStringFieldsEqualIgnoreCase(campusCode, bean.getCampusCode()) && 
						Utility.areStringFieldsEqualIgnoreCase(transPostalCode, bean.getPostalCode()) &&
						isStateOrProvinceEqual(transState, bean.getState(), bean.getProvince()))) {
					updateStagingAddress(bean, addressType, transAddress1, transAddress2, transAddress3, transCity, transState, transPostalCode, 
							transCountryCode, campusCode, addressMatchCode, cityMatchCode);
				}
			}
			else {
				if (! (Utility.areStringFieldsEqual(address1, bean.getAddress1()) &&
						Utility.areStringFieldsEqual(city, bean.getCity()) &&
						Utility.areStringFieldsEqualIgnoreCase(campusCode, bean.getCampusCode()) && 
						Utility.areStringFieldsEqualIgnoreCase(transPostalCode, bean.getPostalCode()) &&
						isStateOrProvinceEqual(transState, bean.getState(), bean.getProvince()))) {
					updateStagingAddress(bean, addressType, transAddress1, transAddress2, transAddress3, transCity, transState, transPostalCode, 
							transCountryCode, campusCode, addressMatchCode, cityMatchCode);	
				}
			}
		}
		// No address was found, so we need to add one.
		if (! skipAdd) {
			addStagingAddress(addressType, transAddress1, transAddress2, transAddress3, transCity, transState, transPostalCode, 
					transCountryCode, campusCode, addressMatchCode, cityMatchCode);
		}
	}

	/**
	 * This method is used to check for a difference in either the state or province field.
	 * @param checkValue contains the value to be checked against the address staging database table.
	 * @param state contains the state from the address staging bean.
	 * @param province contains the province from the address staging bean.
	 * @return will return true if the values are equal, otherwise, it will return false.
	 */
	public boolean isStateOrProvinceEqual(final String checkValue, final String state, final String province) {
		
		if (state != null) {
			return Utility.areStringFieldsEqualIgnoreCase(checkValue, state);
		}
		
		else if (province != null) {
			return Utility.areStringFieldsEqualIgnoreCase(checkValue, province);
		}
		
		else if (state == null && province == null && checkValue == null) {
			return true;
		}
		
		else {
			return false;
		}
		
	}
	/**
	 * This method is used to update an existing address within the address staging table to a new
	 * address that is the result of an import.
	 * @param bean contains the address staging bean to be updated.
	 * @param addressType contains the type of address to be added.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2
	 * @param address3 contains address line #3
	 * @param city contains the city
	 * @param state contains the state.
	 * @param postalCode contains the postal code
	 * @param countryCode contains the country code
	 * @param campusCode contains the campus code
	 * @param addressMatchCode contains the match code for address
	 * @param cityMatchCode contains the match code for city
	 * @throws CprException will be thrown if there is a CPR related problem.
	 */
	public void updateStagingAddress(final AddressStaging bean, final AddressType addressType, final String address1,
			final String address2, final String address3, final String city, final String state,
			final String postalCode, final String countryCode, final String campusCode,
			final String addressMatchCode, final String cityMatchCode) throws CprException {

		fillStagingAddressBean(bean, addressType, address1, address2, address3, city, state, postalCode, countryCode, campusCode,
				addressMatchCode, cityMatchCode);

		getDatabaseSession().update(bean);

	}

	/**
	 * This method is used to add a new address record to the address staging table.
	 * @param addressType contains the type of address to be added.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2
	 * @param address3 contains address line #3
	 * @param city contains the city
	 * @param state contains the state.
	 * @param postalCode contains the postal code
	 * @param countryCode contains the country code
	 * @param campusCode contains the campus code
	 * @param addressMatchCode contains the match code for address
	 * @param cityMatchCode contains the match code for city
	 * @throws CprException will be thrown if there is a CPR related problem.
	 */
	public void addStagingAddress(final AddressType addressType, final String address1,
			final String address2, final String address3, final String city, final String state,
			final String postalCode, final String countryCode, final String campusCode, final String addressMatchCode, 
			final String cityMatchCode) throws CprException {

		final AddressStaging bean = new AddressStaging();

		bean.setPersonId(getPersonId());

		fillStagingAddressBean(bean, addressType, address1, address2, address3, city,
				state, postalCode, countryCode, campusCode,
				addressMatchCode, cityMatchCode);

		bean.setImportFrom(getBatchDataSource().toString());

		getDatabaseSession().insert(bean);
	}
	
	/**
	 * This method is used to fill the address staging bean with a user's information.
	 * @param bean contains the address staging bean.
	 * @param addressType contains the address type (enumerated value).
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2.
	 * @param address3 contains address line #3.
	 * @param city contains the city.
	 * @param state contains the state.
	 * @param postalCode contains the postal code.
	 * @param countryCode contains the country code (string).
	 * @param campusCode contains the campus code (string).
	 * @param addressMatchCode contains the address match code.
	 * @param cityMatchCode contains the city match code.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	private void fillStagingAddressBean(final AddressStaging bean,
			final AddressType addressType, final String address1,
			final String address2, final String address3, final String city,
			final String state, final String postalCode,
			final String countryCode, final String campusCode,
			final String addressMatchCode, final String cityMatchCode) throws CprException {
		
		bean.setDataTypeKey(addressType.index());		
		bean.setAddress1(address1);
		bean.setAddress2(address2);
		bean.setAddress3(address3);
		bean.setAddressMatchCode(addressMatchCode);
		bean.setCity(city);
		bean.setCityMatchCode(cityMatchCode);

		// Country code storage.
		if (! ValidateHelper.isFieldEmpty(countryCode)) {
			try {
				final Country countryBean = getCountryBean(countryCode);
				bean.setCountryKey(countryBean.getCountryKey());
				bean.setCountryCodeThree(countryBean.getCountryCodeThree());
				bean.setCountryCodeTwo(countryBean.getCountryCodeTwo());
			}
			catch (CprException e) {
				LOG4J_LOGGER.warn("Unknown country code: " + countryCode + " for record " + e);
			}
		}
		else {
			bean.setCountryKey(null);
			bean.setCountryCodeThree(null);
			bean.setCountryCodeTwo(null);
		}
		
		// If a campus code was specified, we need to obtain its key for storage in the database.
		if (! ValidateHelper.isFieldEmpty(campusCode)) {
			try {
				final CampusCs campusCsBean = getCampusBean(campusCode);
				bean.setCampusCodeKey(campusCsBean.getCampusCodeKey());
				bean.setCampusCode(campusCode);
			} 
			catch (CprException e) {
				LOG4J_LOGGER.warn("Unknown campus code: " + campusCode + " for record " + e);
			}
		}
		else {
			bean.setCampusCodeKey(null);
			bean.setCampusCode(null);
		}
		
		bean.setImportDate(getUpdateDate());
		bean.setPostalCode(postalCode);
		
		// Only set the state for the United States.
		if (Utility.areStringFieldsEqualIgnoreCase(countryCode, USA) ||
				Utility.areStringFieldsEqualIgnoreCase(countryCode, US)) {
			bean.setState(state);
		}
		else {
			bean.setProvince(state);
		}
	}

	/**
	 * This method is used to add a new address record to the CPR database.
	 * @param addressType contains the type of address to be added.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2
	 * @param address3 contains address line #3
	 * @param city contains the city
	 * @param state contains the state.
	 * @param postalCode contains the postal code
	 * @param countryCode contains the country code
	 * @param campusCode contains the campus code
	 * @param groupId contains the group id
	 * @throws CprException will be thrown if there is a CPR related problem.
	 */
	public void addAddress(final AddressType addressType, final String address1,
			final String address2, final String address3, final String city, final String state,
			final String postalCode, final String countryCode, final String campusCode, final Long groupId) throws CprException {

		final String updatedBy = getBatchDataSource().toString();

		// If a country code was specified, we need to obtains its key for storage in the database.
		Long countryCodeKey = null;
		String countryName = null;
		String countryThreeCharCode = null;
		if (! ValidateHelper.isFieldEmpty(countryCode)) {
			try {
				final Country countryBean = getCountryBean(countryCode);
				countryCodeKey = countryBean.getCountryKey();
				countryName = countryBean.getCountry();
				countryThreeCharCode = countryBean.getCountryCodeThree();
			}
			catch (CprException e) {
				LOG4J_LOGGER.warn("Unknown country code: " + countryCode + " for record " + e);
			}
		}

		// If a campus code was specified, we need to obtain its key for storage in the database.
		Long campusCodeKey = null;
		String campusName = null;
		try {
			if (! ValidateHelper.isFieldEmpty(campusCode)) {
				final CampusCs campusCsBean = getCampusBean(campusCode);
				campusCodeKey = campusCsBean.getCampusCodeKey();
				campusName = campusCsBean.getCampus();
			}
		}
		catch (CprException e) {
			LOG4J_LOGGER.warn("Unknown campus code: " + campusCode + " for record " + e);
		}

		// Create an instance of the addresses table.
		final AddressesTable addressesTable = new AddressesTable(getPersonId(), addressType.toString(), null, groupId, updatedBy,
				address1, address2, address3, city, state, postalCode, null, countryCodeKey,
				campusCodeKey, countryName, campusName, countryThreeCharCode);

		final Addresses bean = addressesTable.getAddressesBean();

		// Because we are doing batch we need to set the import information.
		bean.setCreatedOn(getUpdateDate());
		bean.setLastUpdateOn(getUpdateDate());
		bean.setImportDate(getUpdateDate());
		bean.setImportFrom(bean.getLastUpdateBy());

		// Obtain match codes.
		addressesTable.getMatchCodesUsingOpenSession(getDataQualityService(), bean);

		// Store the record off.
		getDatabaseSession().insert(bean);

		// Save the bean for messaging.
		setNewAddress(bean);

		// Add to the staging table
		addStagingAddress(addressType, address1, address2, address3, city, state, postalCode, countryCode, campusCode, 
				bean.getAddressMatchCode(), bean.getCityMatchCode() );
	}
	
	/**
	 * This method is use to obtain a campus code record based on the campus code.
	 * @param campusCode contains the campus code to search for.
	 * @return will return reference to a campus code bean if found.
	 * @throws CprException will be thrown if the record was not found.
	 */
	public CampusCs getCampusBean(final String campusCode)
			throws CprException {

		if (ValidateHelper.isFieldEmpty(campusCode)) {
			return null;
		}

		final StatelessSession session = getDatabaseSession();
		CampusCs campusCsBean = null;
		final Query query = session.createQuery("from CampusCs where campusCode = :campus_code and activeFlag = 'Y'");
		query.setParameter("campus_code", campusCode.toUpperCase());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			campusCsBean = (CampusCs) it.next();
		}

		if (campusCsBean == null) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "Campus Code");
		}
		return campusCsBean;
	}

	/**
	 * This helper method is used to obtain a country bean based on the input country code.
	 * @param countryCode contains the country code to search for.
	 * @return will return a reference to the country bean.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public Country getCountryBean(final String countryCode) throws CprException {

		Country countryBean = null;
		Query query = null;
		final StatelessSession session = getDatabaseSession();

		// Two character codes are from IBIS.
		if (countryCode.length() == TWO_CHAR_COUNTRY_LENGTH) {
			final StringBuilder sb = new StringBuilder(300);
			sb.append("from Country where countryKey IN ");
			sb.append("(SELECT countryKey FROM IrsCountry WHERE irsCountryCode = :country_code AND endDate IS NULL) ");
			sb.append("AND endDate IS NULL");
			query = session.createQuery(sb.toString());
		}

		// Three character codes are from ISIS.
		else if (countryCode.length() == THREE_CHAR_COUNTRY_LENGTH) {
			query = session.createQuery("from Country where countryCodeThree = :country_code and endDate is NULL");
		}

		// Something is wrong.
		else {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "Country Code");
		}

		query.setParameter("country_code", countryCode.toUpperCase());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			countryBean = (Country) it.next();
		}

		if (countryBean == null) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "Country Code");
		}
		return countryBean;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(final Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the batchDataSource
	 */
	public BatchDataSource getBatchDataSource() {
		return batchDataSource;
	}

	/**
	 * @return the oldDateOfBirth
	 */
	public DateOfBirth getOldDateOfBirth() {
		return oldDateOfBirth;
	}

	/**
	 * @param oldDateOfBirth the oldDateOfBirth to set
	 */
	public void setOldDateOfBirth(final DateOfBirth oldDateOfBirth) {
		this.oldDateOfBirth = oldDateOfBirth;
	}

	/**
	 * @return the oldEmailAddress
	 */
	public EmailAddress getOldEmailAddress() {
		return oldEmailAddress;
	}

	/**
	 * @param oldEmailAddress the oldEmailAddress to set
	 */
	public void setOldEmailAddress(final EmailAddress oldEmailAddress) {
		this.oldEmailAddress = oldEmailAddress;
	}

	/**
	 * @return the oldPersonGender
	 */
	public PersonGender getOldPersonGender() {
		return oldPersonGender;
	}

	/**
	 * @param oldPersonGender the oldPersonGender to set
	 */
	public void setOldPersonGender(final PersonGender oldPersonGender) {
		this.oldPersonGender = oldPersonGender;
	}

	/**
	 * @return the oldName
	 */
	public Names getOldName() {
		return oldName;
	}

	/**
	 * @param oldName the oldName to set
	 */
	public void setOldName(final Names oldName) {
		this.oldName = oldName;
	}

	/**
	 * @return the oldPhone
	 */
	public Phones getOldPhone() {
		return oldPhone;
	}

	/**
	 * @param oldPhone the oldPhone to set
	 */
	public void setOldPhone(final Phones oldPhone) {
		this.oldPhone = oldPhone;
	}

	/**
	 * @return the oldPsuId
	 */
	public PsuId getOldPsuId() {
		return oldPsuId;
	}

	/**
	 * @param oldPsuId the oldPsuId to set
	 */
	public void setOldPsuId(final PsuId oldPsuId) {
		this.oldPsuId = oldPsuId;
	}

	/**
	 * @return the newDateOfBirth
	 */
	public DateOfBirth getNewDateOfBirth() {
		return newDateOfBirth;
	}

	/**
	 * @param newDateOfBirth the newDateOfBirth to set
	 */
	public void setNewDateOfBirth(final DateOfBirth newDateOfBirth) {
		this.newDateOfBirth = newDateOfBirth;
	}

	/**
	 * @return the newEmailAddress
	 */
	public EmailAddress getNewEmailAddress() {
		return newEmailAddress;
	}

	/**
	 * @param newEmailAddress the newEmailAddress to set
	 */
	public void setNewEmailAddress(final EmailAddress newEmailAddress) {
		this.newEmailAddress = newEmailAddress;
	}

	/**
	 * @return the newPersonGender
	 */
	public PersonGender getNewPersonGender() {
		return newPersonGender;
	}

	/**
	 * @param newPersonGender the newPersonGender to set
	 */
	public void setNewPersonGender(final PersonGender newPersonGender) {
		this.newPersonGender = newPersonGender;
	}

	/**
	 * @return the newName
	 */
	public Names getNewName() {
		return newName;
	}

	/**
	 * @param newName the newName to set
	 */
	public void setNewName(final Names newName) {
		this.newName = newName;
	}

	/**
	 * @return the newPhone
	 */
	public Phones getNewPhone() {
		return newPhone;
	}

	/**
	 * @param newPhone the newPhone to set
	 */
	public void setNewPhone(final Phones newPhone) {
		this.newPhone = newPhone;
	}

	/**
	 * @return the newPsuId
	 */
	public PsuId getNewPsuId() {
		return newPsuId;
	}

	/**
	 * @param newPsuId the newPsuId to set
	 */
	public void setNewPsuId(final PsuId newPsuId) {
		this.newPsuId = newPsuId;
	}

	/**
	 * @param oldUserid the oldUserid to set
	 */
	public void setOldUserid(final Userid oldUserid) {
		this.oldUserid = oldUserid;
	}

	/**
	 * @return the oldUserid
	 */
	public Userid getOldUserid() {
		return oldUserid;
	}

	/**
	 * @param newUserid the newUserid to set
	 */
	public void setNewUserid(final Userid newUserid) {
		this.newUserid = newUserid;
	}

	/**
	 * @return the newUserid
	 */
	public Userid getNewUserid() {
		return newUserid;
	}

	/**
	 * @param oldAddress the oldAddress to set
	 */
	public void setOldAddress(final Addresses oldAddress) {
		this.oldAddress = oldAddress;
	}

	/**
	 * @return the oldAddress
	 */
	public Addresses getOldAddress() {
		return oldAddress;
	}

	/**
	 * @param newAddress the newAddress to set
	 */
	public void setNewAddress(final Addresses newAddress) {
		this.newAddress = newAddress;
	}

	/**
	 * @return the newAddress
	 */
	public Addresses getNewAddress() {
		return newAddress;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param psuIdNumber the psuIdNumber to set
	 */
	public void setPsuIdNumber(final String psuIdNumber) {
		this.psuIdNumber = psuIdNumber;
	}

	/**
	 * @return the psuIdNumber
	 */
	public String getPsuIdNumber() {
		return psuIdNumber;
	}

	/**
	 * @return the dataQualityService
	 */
	public DataQualityService getDataQualityService() {
		return dataQualityService;
	}

	/**
	 * @param databaseSession the databaseSession to set
	 */
	public void setDatabaseSession(final StatelessSession databaseSession) {
		this.databaseSession = databaseSession;
	}

	/**
	 * @return the databaseSession
	 */
	public StatelessSession getDatabaseSession() {
		return databaseSession;
	}

	/**
	 * @param primaryUserid the primaryUserid to set
	 */
	public void setPrimaryUserid(final String primaryUserid) {
		this.primaryUserid = primaryUserid;
	}

	/**
	 * @return the primaryUserid
	 */
	public String getPrimaryUserid() {
		return primaryUserid;
	}
}
