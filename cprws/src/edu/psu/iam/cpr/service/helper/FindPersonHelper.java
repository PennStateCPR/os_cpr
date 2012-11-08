/* SVN FILE: $Id: FindPersonHelper.java 5343 2012-09-27 14:56:40Z jvuccolo $ */

package edu.psu.iam.cpr.service.helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.helpers.DBTypesHelper;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.database.tables.PsuIdTable;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.MatchType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.DateOfBirthReturn;
import edu.psu.iam.cpr.core.service.returns.GenderReturn;
import edu.psu.iam.cpr.core.service.returns.MatchReturn;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.UseridReturn;
import edu.psu.iam.cpr.core.util.Validate;
import edu.psu.iam.cpr.core.util.ValidateAddress;
import edu.psu.iam.cpr.core.util.ValidateDateOfBirth;
import edu.psu.iam.cpr.core.util.ValidateSSN;
import edu.psu.iam.cpr.service.CprwsService;
import edu.psu.iam.cpr.service.returns.FindPersonServiceReturn;

/**
 * This is a helper class for the FindPerson() service.
 * It contains several methods used by the FindPerson service.
 * 
 * After instantiating this class, the user should call
 * getMatchCodes() to populate the match codes using the GI
 * service. Then the matchNameMatchCodeFromCPR(),
 * matchAddressMatchCodeFromCPR(), and matchCityMatchCodeFromCPR()
 * methods may be used.
 * 
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.service.helper
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 * 
 */

public class FindPersonHelper {

	/** minimum value for a match */
	public static final Long MINIMUM_RANKING_SCORE = 330L;
	
	/** the maximum number of near match results to return */
	public static final int MAX_NEAR_MATCH_RESULTS = 10;

	/** The USA country code. */
	public static final String USA_COUNTRY_CODE = "USA";
	
	/** SQL to retrieve name match codes from the CPR. */
	protected static final String CPR_GET_NAME_MATCH_CODES = "SELECT name_match_code FROM names WHERE person_id=:person_id_in AND name_match_code=:name_match_code_in AND end_date IS NULL";
	
	/** SQL to retrieve address match codes from the CPR. */
	protected static final String CPR_GET_ADDRESS_MATCH_CODES = "SELECT address_match_code FROM addresses WHERE person_id=:person_id_in AND end_date IS NULL";
	
	/** SQL to retrieve city match codes from the CPR. */
	protected static final String CPR_GET_CITY_MATCH_CODES = "SELECT city_match_code FROM addresses WHERE person_id=:person_id_in AND end_date IS NULL";
	
	/** SQL to call the CPR's match code stored procedure. */
	protected static final String CPR_CALL_MATCH_PACKAGE_US = "{ call cpr.match_qp.find_matches_us(?,?,?,?,?,?,?,?) }";
	
	/** SQL to call the CPR's match code stored procedure. */
	protected static final String CPR_CALL_MATCH_PACKAGE_NON_US = "{ call cpr.match_qp.find_matches_non_us(?,?,?,?,?,?,?,?) }";
	
	/** GI-calculated match code for the person's name */
	protected String giNameMatchCode = "";
	
	/** GI-calculated match code for the person's address */
	protected String giAddressMatchCode = "";
	
	/** GI-calculated match code for the person's city */
	protected String giCityMatchCode = "";	

	/** the person id */
	protected long personId = -1L;
	
	/** Handle to the CPR database. */
	protected Database db;
	
	private Long matchSet;
	/** Contains the match set */
	
	/** Instance of logger */
	private static final Logger Log4jLogger = Logger.getLogger(CprwsService.class);
		
	/**
	 * Constructor.
	 * 
	 * @param db An open handle to the CPR database.
	 * 
	 * @throws GeneralDatabaseException if db is null or closed.
	 */
	public FindPersonHelper(final Database db) {
		
		super();
		
		if (db == null || !db.isSessionOpen()) {
			//TODO
		}
		
		this.db = db;

	}
	
	/**
	 * Gets the personID.
	 *
	 * @return the person id, or -1 if one was not cached.
	 */
	public long getPersonId() {
		return personId;
	}

	/**
	 * Sets the personID. This method should only be used for testing!
	 *
	 * param the person id
	 */
	public void setPersonId(long id) {
		personId = id;
	}
	
	/**
	 * Do a match using an open source algorithm.
	 * @param serviceCoreReturn contains the service core return object.
	 * @param requestedBy contains the user who requested the search.
	 * @param psuId penn state id.
	 * @param userId userid.
	 * @param ssn contains the social security number.
	 * @param firstName contains the first name.
	 * @param lastName contains the last name.
 	 * @param middleName contains the middle name(s).
	 * @param address1 contains the address line #1.
	 * @param address2 contains the address line #2.
	 * @param address3 contains the address line #3.
	 * @param city contains the city.
	 * @param state contains the state.
	 * @param postalCode contains the postal code.
	 * @param plus4 contains the plus4 postal code.
	 * @param country contains the country.
	 * @param dateOfBirth contains the dob.
	 * @param gender contains the gender.
	 * @param rankCutOff contains the rank cutoff - penn state only.
	 * @return will a return a find person service return object if successful.
	 * @throws GeneralDatabaseException will be thrown if there is a general database problem.
	 * @throws CprException will be thrown if there is a cpr specific problem.
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public FindPersonServiceReturn doSearchForPersonOS(ServiceCoreReturn serviceCoreReturn,
			String requestedBy,
			String psuId, 
			String userId, 
			String ssn, 
			String firstName,
			String lastName, 
			String middleName,
			String address1,
			String address2, 
			String address3,
			String city, 
			String state, 
			String postalCode,
			String plus4,
			String country, 
			String dateOfBirth,
			String gender, 
			String rankCutOff) throws CprException, ParseException {
		
		if (ssn != null && ! ssn.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: found ssn parm = " + ssn);
			if (ValidateSSN.validateSSN(ssn)) {
				ssn = ValidateSSN.extractNumericSSN(ssn);
				Log4jLogger.debug("SearchForPerson: found valid ssn = " + ssn);
			} 
			else {
				Log4jLogger.debug("SearchForPerson: found invalid ssn = " + ssn);
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid value was specified for the SSN.");
			}
		}
	
		if (userId != null && !userId.trim().equals("")) {
			userId = userId.trim();
			Log4jLogger.debug("SearchForPerson: found valid user id = " + userId);
		}
		
		if (firstName == null || firstName.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: found invalid first name = " + firstName);
			return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "First name must contain a value.");
		} 
		else {
			Log4jLogger.debug("SearchForPerson: found valid first name = " + firstName);
			firstName = firstName.trim();
		}
		
		if (lastName == null || lastName.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: found invalid last name = " + lastName);
			return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Last name must contain a value.");
		} 
		else {
			Log4jLogger.debug("SearchForPerson: found valid last name = " + lastName);
			lastName = lastName.trim();
		}
		
		if (dateOfBirth == null || dateOfBirth.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: no date of birth entered");
			return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "A date of birth must be entered.");
		}			
		else {
			String numericDateOfBirth = dateOfBirth;			
			// Take care of MM/DD and MM/DD/YYYY cases.
			if (dateOfBirth.length() == 5 || dateOfBirth.length() == 10) {				
				// If the DOB has a dash in it, replace it with a slash.
				dateOfBirth = dateOfBirth.replace('-', '/');
				// Attempt to validate the DOB.
				if (! ValidateDateOfBirth.isDateOfBirthValid(dateOfBirth)) {
					Log4jLogger.debug("SearchForPerson: found invalid date of birth = " + dateOfBirth);
					return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "An invalid date of birth was entered.");						
				}
				
				// Extract a character representation of the date of birth.
				numericDateOfBirth = DateOfBirthTable.toDobChar(dateOfBirth);
				Log4jLogger.debug("SearchForPerson: converting date of birth to a numeric string " + numericDateOfBirth);
			}
			// Verify that the resultant DOB is exactly 8 characters.
			Log4jLogger.debug("SearchForPerson: Verify that DOB is exactly 8 characters.");	
			if (numericDateOfBirth.length() != 8) {
				Log4jLogger.debug("SearchForPerson: found invalid date of birth = " + dateOfBirth);
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "An invalid date of birth was entered.");											
			}
			else {
				Log4jLogger.debug("SearchForPerson: found valid date of birth = " + dateOfBirth);
			}
		}
		
		// Get the list of matching person ids for the name.  We are doing a simple exact match.
		ArrayList<Long> nameMatch = new ArrayList<Long>();
		if (lastName != null) {
			
			// We are doing a query for names with history.
			Session session = db.getSession();
			String sqlQuery = "select distinct personId from Names where upper(lastName)=upper(:last_name)";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("last_name", lastName);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Long personId = (Long) it.next();
				nameMatch.add(personId);
			}
		}
		
		// Get the list of matches for the SSN.
		ArrayList<Long> ssnMatch = new ArrayList<Long>();
		if (ssn != null) {
			
			// Need to find the key that is associated with the SSN person identifier.
			HashMap<String,Object> map = DBTypesHelper.INSTANCE.getTypeMaps(DBTypesHelper.IDENTIFIER_TYPE);
			IdentifierType identifierType = null;
			for (Map.Entry<String,Object> entry : map.entrySet()) {
				identifierType = (IdentifierType) entry.getValue();
				if (identifierType.getTypeName().equals(Database.SSN_IDENTIFIER)) {
					break;
				}
			}
			
			// Do a query for the active person identifier.
			Session session = db.getSession();
			String sqlQuery = "select distinct personId from PersonIdentifier where typeKey = :type_key AND identifierValue = :identifier_value AND endDate is null";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("type_key", identifierType.getTypeKey());
			query.setParameter("identifier_value", ssn);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Long personId = (Long) it.next();
				ssnMatch.add(personId);
			}
		}
		
		// Get the list of matches for the userid.
		ArrayList<Long> useridMatch = new ArrayList<Long>();
		if (userId != null) {
			Session session = db.getSession();
			String sqlQuery = "select distinct personId from Userid where userid = :userid";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("userid", userId);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Long personId = (Long) it.next();
				useridMatch.add(personId);
			}		
		}
			
		// Check name, SSN, and DOB.
		if (ssn != null) {
			ArrayList<Long> finalMatch = new ArrayList<Long>();
			finalMatch = (ArrayList<Long>) CollectionUtils.intersection(nameMatch, ssnMatch);
			
			if (finalMatch.size() == 1) {
				
				setPersonId(finalMatch.get(0));

				// Verify that the DOB matches.
				if (matchDOBInCPR(dateOfBirth)) {
					Log4jLogger.info("SearchForPerson: match by ssn successful = " + ssn);								
					FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.SUCCESS.index(), "SUCCESS");
					findPersonReturn.setMatchingMethod(MatchType.SSN.toString());
					findPersonReturn.setPersonID(getPersonId());
					findPersonReturn.setPsuID(null);
					findPersonReturn.setUserId(getPrimaryUserIdFromCPR());
					return findPersonReturn;
				}
			}
		}
		
		// Check name, userid and DOB.
		if (userId != null) {
			ArrayList<Long> finalMatch = new ArrayList<Long>();
			finalMatch = (ArrayList<Long>) CollectionUtils.intersection(nameMatch, useridMatch);
			if (finalMatch.size() == 1) {

				setPersonId(finalMatch.get(0));

				// Verify that the DOB matches.
				if (matchDOBInCPR(dateOfBirth)) {
					// Log a success!
					serviceCoreReturn.getServiceLogTable().endLog(db, "SUCCESS");
					Log4jLogger.info("SearchForPerson: match by user id successful = " + userId);					        
					FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.SUCCESS.index(), "SUCCESS");
					findPersonReturn.setMatchingMethod(MatchType.USERID.toString());
					findPersonReturn.setPersonID(getPersonId());				
					findPersonReturn.setPsuID(null);
					findPersonReturn.setUserId(getPrimaryUserIdFromCPR());

					return findPersonReturn;
				}
			}
		}
		
		// No match was found.
		FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.PERSON_NOT_FOUND_EXCEPTION.index(), "FAILURE");
        findPersonReturn.setMatchingMethod(MatchType.NO_MATCH.toString());        
        return findPersonReturn;
	}

	/**
	 * Do a match using the Penn State Algorithm.
	 * @param serviceCoreReturn contains the service core return object.
	 * @param requestedBy contains the user who requested the search.
	 * @param psuId penn state id.
	 * @param userId userid.
	 * @param ssn contains the social security number.
	 * @param firstName contains the first name.
	 * @param lastName contains the last name.
 	 * @param middleName contains the middle name(s).
	 * @param address1 contains the address line #1.
	 * @param address2 contains the address line #2.
	 * @param address3 contains the address line #3.
	 * @param city contains the city.
	 * @param state contains the state.
	 * @param postalCode contains the postal code.
	 * @param plus4 contains the plus4 postal code.
	 * @param country contains the country.
	 * @param dateOfBirth contains the dob.
	 * @param gender contains the gender.
	 * @param rankCutOff contains the rank cutoff - penn state only.
	 * @return will a return a find person service return object if successful.
	 * @throws GeneralDatabaseException will be thrown if there is a general database problem.
	 * @throws CprException will be thrown if there is a cpr specific problem.
	 * @throws ParseException 
	 */
	public FindPersonServiceReturn doSearchForPersonPSU(ServiceCoreReturn serviceCoreReturn,
			String requestedBy,
			String psuId, 
			String userId, 
			String ssn, 
			String firstName,
			String lastName, 
			String middleName,
			String address1,
			String address2, 
			String address3,
			String city, 
			String state, 
			String postalCode,
			String plus4,
			String country, 
			String dateOfBirth,
			String gender, 
			String rankCutOff) throws CprException, ParseException {

		Long rankCutOffScore = FindPersonHelper.MINIMUM_RANKING_SCORE;
		GenderType inputGenderType = null;
		boolean hasPsuId = false;
		boolean hasUserId = false;
		boolean hasSSN = false;
		
		// if no country is set, default to USA 
		if (country == null || country.trim().equals("")) {
			country = FindPersonHelper.USA_COUNTRY_CODE;
			Log4jLogger.debug("SearchForPerson: setting null or blank country to USA");
		} 
		else {
			country = country.trim().toUpperCase();
			if (ValidateAddress.validCountryCode(db, country, null, requestedBy) < 1) {
				Log4jLogger.debug("SearchForPerson: found invalid country = " + country);
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid value was specified for the country parameter.");
			}
			else {
				Log4jLogger.debug("SearchForPerson: found valid country = " + country);
			}
		}

		// street address validation only works for US addresses for now
		if (country.equals(FindPersonHelper.USA_COUNTRY_CODE)) {			
			if (state != null) {
				state = state.trim().toUpperCase();		
				if (!ValidateAddress.isValidState(state)) {
					Log4jLogger.debug("SearchForPerson: found invalid state = " + state);
					return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid value was specified for the state parameter.");
				}
				else {
					Log4jLogger.debug("SearchForPerson: found valid state = " + state);
				}
			}

			if (postalCode != null) {
				if (!ValidateAddress.isValidZipCode(postalCode)) {
					Log4jLogger.debug("SearchForPerson: found invalid postalCode = " + postalCode);
					return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid value was specified for the postalCode parameter.");
				}
				else {
					Log4jLogger.debug("SearchForPerson: found valid postalCode = " + postalCode);
				}
			}

		}			
		
		if (ssn != null && !ssn.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: found ssn parm = " + ssn);
			if (ValidateSSN.validateSSN(ssn)) {
				ssn = ValidateSSN.extractNumericSSN(ssn);
				hasSSN = true;
				Log4jLogger.debug("SearchForPerson: found valid ssn = " + ssn);
			} else {
				Log4jLogger.debug("SearchForPerson: found invalid ssn = " + ssn);
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid value was specified for the SSN.");
			}
		}
	
		if (userId != null && !userId.trim().equals("")) {
			hasUserId = true;
			userId = userId.trim();
			Log4jLogger.debug("SearchForPerson: found valid user id = " + userId);
		}
		
		if (firstName == null || firstName.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: found invalid first name = " + firstName);
			return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "First name must contain a value.");
		} else {
			Log4jLogger.debug("SearchForPerson: found valid first name = " + firstName);
			firstName = firstName.trim();
		}
		
		if (lastName == null || lastName.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: found invalid last name = " + lastName);
			return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Last name must contain a value.");
		} else {
			Log4jLogger.debug("SearchForPerson: found valid last name = " + lastName);
			lastName = lastName.trim();
		}
		
		if (psuId != null && !psuId.trim().equals("")) {
			if (Validate.isValidPsuId(psuId)) {
				hasPsuId = true;
				Log4jLogger.debug("SearchForPerson: found valid psu id = " + psuId);
			} else {
				Log4jLogger.debug("SearchForPerson: found invalid psu id = " + psuId);
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "An invalid value was entered for the PSU Id.");
			}
		}
			
		if (dateOfBirth == null || dateOfBirth.trim().equals("")) {
			Log4jLogger.debug("SearchForPerson: no date of birth entered");
			return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "A date of birth must be entered.");
		}			
		else {
			String numericDateOfBirth = dateOfBirth;			
			// Take care of MM/DD and MM/DD/YYYY cases.
			if (dateOfBirth.length() == 5 || dateOfBirth.length() == 10) {				
				// If the DOB has a dash in it, replace it with a slash.
				dateOfBirth = dateOfBirth.replace('-', '/');
				// Attempt to validate the DOB.
				if (! ValidateDateOfBirth.isDateOfBirthValid(dateOfBirth)) {
					Log4jLogger.debug("SearchForPerson: found invalid date of birth = " + dateOfBirth);
					return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "An invalid date of birth was entered.");						
				}
				
				// Extract a character representation of the date of birth.
				numericDateOfBirth = DateOfBirthTable.toDobChar(dateOfBirth);
				Log4jLogger.debug("SearchForPerson: converting date of birth to a numeric string " + numericDateOfBirth);
			}
			// Verify that the resultant DOB is exactly 8 characters.
			Log4jLogger.debug("SearchForPerson: Verify that DOB is exactly 8 characters.");	
			if (numericDateOfBirth.length() != 8) {
				Log4jLogger.debug("SearchForPerson: found invalid date of birth = " + dateOfBirth);
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "An invalid date of birth was entered.");											
			}
			else {
				Log4jLogger.debug("SearchForPerson: found valid date of birth = " + dateOfBirth);
			}
		}
				
		if (rankCutOff != null && !rankCutOff.trim().equals("")) {
			try {
				rankCutOffScore = Long.parseLong(rankCutOff);
				if (rankCutOffScore < FindPersonHelper.MINIMUM_RANKING_SCORE) {
					return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid value was specified for the rankCutOff parameter.");
				}
			} catch (NumberFormatException e) {
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid value was specified for the rankCutOff parameter.");
			}
		}
		
		if (gender != null && !gender.trim().equals("")) {
			try {
				inputGenderType = GenderType.valueOf(gender.trim().toUpperCase());
			} catch (Exception ex) {
				return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "An invalid value was entered for the gender.");
			}
		} 
		
		// ensure that all IDs, if any, point to the same personID
		if (! validateIDs(psuId, userId, ssn)) {
			// XXX: reword the message.
			Log4jLogger.debug("SearchForPerson: one or more ids are invalid");
			return new FindPersonServiceReturn(ReturnType.INVALID_PARAMETERS_EXCEPTION.index(), "Invalid values specified for one or more of the psuId, userId, and SSN parameters.");
		}
		
		// start matching
		getMatchCodesFromGI(firstName, middleName, lastName, address1, address2, address3, city);
					
		// If psuid, userid, or SSN were passed, retrieve the personid
		// then match against the partial date of birth, (last) name, and gender.
		// If a match is found, return appropriately. If not, resort to using match codes.
		if (hasPsuId) {
			Log4jLogger.debug("SearchForPerson: psu id passed = " + psuId);
			// check name, DOB and gender	
			if ((matchNameMatchCodeFromCPR()) &&
				(matchGenderInCPR(inputGenderType)) && 
				(matchDOBInCPR(dateOfBirth))) {
				
			    // Log a success!
		        serviceCoreReturn.getServiceLogTable().endLog(db, "SUCCESS");
				Log4jLogger.info("SearchForPerson: match by psu id successful = " + psuId);			        
		        FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.SUCCESS.index(), "SUCCESS");
				findPersonReturn.setMatchingMethod(MatchType.PSU_ID.toString());			
				findPersonReturn.setPsuID(psuId);
				findPersonReturn.setPersonID(getPersonId());
				findPersonReturn.setUserId(getPrimaryUserIdFromCPR());
				
				return findPersonReturn;
			}
		}
		
		if (hasUserId) {
			Log4jLogger.info("SearchForPerson: user id passed = " + userId);
			// check name, DOB and gender
			Log4jLogger.debug("SearchForPerson: ready to check name, dob and gender in FPH");
			Log4jLogger.debug("SearchForPerson: gender type = " + inputGenderType);
			Log4jLogger.debug("SearchForPerson: dateOfBirth = " + dateOfBirth);
			if ((matchNameMatchCodeFromCPR()) &&
				(matchGenderInCPR(inputGenderType)) && 
				(matchDOBInCPR(dateOfBirth))) {
					
			    // Log a success!
		        serviceCoreReturn.getServiceLogTable().endLog(db, "SUCCESS");
				Log4jLogger.info("SearchForPerson: match by user id successful = " + userId);					        
				FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.SUCCESS.index(), "SUCCESS");
				findPersonReturn.setMatchingMethod(MatchType.USERID.toString());
				findPersonReturn.setPersonID(getPersonId());
				
				final PsuIdTable psuIdTable = new PsuIdTable();
				psuIdTable.setReturnHistoryFlag(false);
				final PsuIdReturn psuIdReturn[] = psuIdTable.getPsuIdForPersonId(db, getPersonId());
				if (psuIdReturn.length == 1) {
					findPersonReturn.setPsuID(psuIdReturn[0].getPsuId());
				}
				else {
					findPersonReturn.setPsuID(null);
				}
				findPersonReturn.setUserId(getPrimaryUserIdFromCPR());
					
				return findPersonReturn;
			}
		}
		
		if (hasSSN) {
			Log4jLogger.info("SearchForPerson: ssn passed = " + ssn);					
			// XXX additional authorization code: Is the requester authorized to query by SSN?
				
			// check name, DOB and gender	
			if ((matchNameMatchCodeFromCPR()) &&
				(matchGenderInCPR(inputGenderType)) && 
				(matchDOBInCPR(dateOfBirth))) {
						
				// Log a success!
			    serviceCoreReturn.getServiceLogTable().endLog(db, "SUCCESS");
				Log4jLogger.info("SearchForPerson: match by ssn successful = " + ssn);								
				FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.SUCCESS.index(), "SUCCESS");
				findPersonReturn.setMatchingMethod(MatchType.SSN.toString());
				findPersonReturn.setPersonID(getPersonId());
				
				final PsuIdTable psuIdTable = new PsuIdTable();
				psuIdTable.setReturnHistoryFlag(false);
				final PsuIdReturn psuIdReturn[] = psuIdTable.getPsuIdForPersonId(db, getPersonId());
				if (psuIdReturn.length == 1) {
					findPersonReturn.setPsuID(psuIdReturn[0].getPsuId());
				}
				else {
					findPersonReturn.setPsuID(null);
				}
				findPersonReturn.setUserId(getPrimaryUserIdFromCPR());
						
				return findPersonReturn;
			}
		}
		

		// get minimum successful match code by calling the database's stored procedure
		// compare the match code we got from the CPR and to the rankCutOffScore
		MatchReturn[] nearMatches = getNearMatchesFromCPR(state, postalCode, country, dateOfBirth, requestedBy, rankCutOffScore);
		Log4jLogger.info("SearchForPerson: Near Match Results -- nearMatches.length = " + nearMatches.length);			
		if (nearMatches.length == 0) {
			serviceCoreReturn.getServiceLogTable().endLog(db, "FAILURE!");
	        
			// XXX: do this, or throw a Not Found Exception ?
			FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.PERSON_NOT_FOUND_EXCEPTION.index(), "FAILURE");
	        findPersonReturn.setMatchingMethod(MatchType.NO_MATCH.toString());
	        
	        return findPersonReturn;
		}
		
		// Log a success!
		
        serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
		Log4jLogger.info("SearchForPerson: match by near match successful");		
		FindPersonServiceReturn findPersonReturn = new FindPersonServiceReturn(ReturnType.SUCCESS.index(), "SUCCESS");
		findPersonReturn.setMatchingMethod(MatchType.NEAR_MATCH.toString());
		findPersonReturn.setNearMatchArray(nearMatches);
		
		if (nearMatches.length > 0){
			Log4jLogger.debug("SearchForPerson: Near Match Array ");
			// loop through matchResults and retrieve the psuId and userId for each entry
			for (int i = 0; i < nearMatches.length; i++) {
				Log4jLogger.debug("SearchForPerson: Iteration = " + (i + 1) +
						": person id = " + nearMatches[i].getPersonId() + 
						", score = " + nearMatches[i].getScore() +
						", PSU id = " + nearMatches[i].getPsuId() +
						", primary uid = " + nearMatches[i].getUserId());
				nearMatches[i].getPsuId();
				nearMatches[i].getPersonId();
				nearMatches[i].getScore();
			}
		}
        
        return findPersonReturn;
		
	}
	
	/**
	 * Get match codes from the GI.
	 * This method updates the instance variables nameMatchCode, addressMatchCode, and cityMatchCode.
	 * 
	 * @param firstName The user's first name.
	 * @param middleName The user's middle name.
	 * @param lastName The user's last name.
	 * @param address1 The first line of the street address
	 * @param address2 The second line of the street address
	 * @param address3 The third line of the street address
	 * @param city The city to match
	 */
	public void getMatchCodesFromGI(final String firstName, final String middleName, final String lastName,
						      final String address1, final String address2, final String address3, final String city) {		
	}
	
	/**
	 * Match a user's gender against the value stored in the CPR.
	 * @param gender The gender value to be matched.
	 * 
	 * @return true If the record in the CPR matches the input parameter, or if null is passed, or if there is no gender in the CPR for the personid;
	 *  otherwise, false.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public boolean matchGenderInCPR(final GenderType gender) throws CprException {
		
		// if no gender was specified, count that as a match
		if (gender == null) {
			return true;
		}
		
		final PersonGenderTable genderTable = new PersonGenderTable();
		genderTable.setReturnHistoryFlag(false);
		final GenderReturn genderReturn[] = genderTable.getGenderForPersonId(db, personId);
		
		// if there's no gender in the CPR, count that as a match
		if (genderReturn.length == 0) {
			return true;
		}
		
		GenderType cprGender = GenderType.valueOf(genderReturn[0].getGender());
		if (gender == cprGender) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Match a user's (partial) date of birth against the value stored in the CPR.
	 * @param dob The (partial) DOB to be matched.
	 * 
	 * @return true If the record in the CPR matches the input parameters; otherwise, false.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public boolean matchDOBInCPR(final String dob) throws CprException {
		Log4jLogger.debug("FPH: checking date of birth; personId = " + personId + ", dob = " + dob);
		
		if (personId < 0) {
			return false;
		}
		
		if (db == null || !db.isSessionOpen()) {
			// TODO
		}
		
		if (dob == null || "".equals(dob.trim())) {
			return false;
		}
		
		Date d;
		try {
			Log4jLogger.debug("FPH: ready for conversion of dob = " + dob + " to Date object");			
			d = parseDateString(dob, true);
		} catch (ParseException ex) {
			Log4jLogger.debug("FPH: parse exception = " + ex.getMessage());		
			return false;
		}
		
		Calendar inputCal = Calendar.getInstance(Locale.US);
		inputCal.setTime(d);
	
		DateOfBirthTable dobTable = new DateOfBirthTable();
		
		Log4jLogger.debug("FPH: ready for conversion to retrieve date from DOB table");	
		dobTable.setReturnHistoryFlag(false);
		DateOfBirthReturn dobReturn[] = dobTable.getDateOfBirthForPersonId(db, personId);
		if (dobReturn.length == 0 || dobReturn[0].getDob() == null) {
			Log4jLogger.debug("FPH: no dob found");	
			return false;
		}

		String cprDOB = dobReturn[0].getDob();
		
		Log4jLogger.debug("FPH: date of birth found " + cprDOB );
		
    	try {
    		final Date cprDate = parseDateString(cprDOB, true);
    		final Calendar cprCal = convertDateToCalendar(cprDate, true);
    		
    		// if the exact dates match, return true
    		if (dob.equals(cprDate)) {
    			return true;
    		}
    		
    		// check for a partial match
    		int inputMonth = inputCal.get(Calendar.MONTH);
    		int cprMonth = cprCal.get(Calendar.MONTH);
    		int inputDay = inputCal.get(Calendar.DAY_OF_MONTH);
    		int cprDay = cprCal.get(Calendar.DAY_OF_MONTH);
    		int inputYear = inputCal.get(Calendar.YEAR);
    		int cprYear = inputCal.get(Calendar.YEAR);
    		    		
    		if ((inputMonth == cprMonth && (inputDay == cprDay || inputYear == cprYear)) || (inputDay == cprDay && inputYear == cprYear)) { 
    			return true;
    		}
    		
    		return false;
    	}
    	catch (Exception e) {
    		return false;
    	}
	}
	
	/**
	 * Validate the PSU ID, User ID, and SSN all point to the same person ID in the CPR.
	 * Nulls are allowed for any or all of the inputs.
	 *
	 * @param psuId The PSU ID
	 * @param userId The User ID
	 * @param ssn The SSN
	 * 
	 * @return true if they match; otherwise, false
	 * @throws GeneralDatabaseException
	 */
	public boolean validateIDs(String psuId, String userId, String ssn) {
		
		// these are set to true if a parameter is not null and not an empty string
		boolean hasPsuId = false;
		boolean hasUserId = false;
		boolean hasSSN = false;
		
		long psuIdPersonId = -1L;		
		long userIdPersonId = -1L;	
		long ssnPersonId = -1L;		
		
		if (psuId != null) {
			psuId = psuId.trim();
			if (!"".equals(psuId)) {
				hasPsuId = true;
			}
		}
		
		if (userId != null) {
			userId = userId.trim();
			if (!"".equals(userId)) {
				hasUserId = true;
			}
		}
		
		if (ssn != null) {
			ssn = ssn.trim();
			if (!"".equals(ssn)) {
				hasSSN = true;
			}
		}
		
		// if all are null or empty, the IDs are valid
		if (!hasPsuId && !hasUserId && !hasSSN) {
			return true;
		}
		
		// Multiple IDs were specified.
		// Validate that they point to the same personID in the CPR.
		
		if (hasPsuId) {
			try {
				psuIdPersonId = db.getPersonIdUsingPsuId(psuId);
			} catch (CprException e) {
				psuIdPersonId = -1;
				//hasPsuId = false;
			}
		}
		
		if (hasUserId) {
			try {
				userIdPersonId = db.getPersonIdUsingUserid(userId);
			} catch (CprException e) {
				userIdPersonId = -1;
				//hasUserId = false;
			}
		}
		
//		if (hasSSN) {
//			try {
//				SSNServiceReturn ssnReturn = SSNService.getPsuIdBySSN(ssn);
//				if (ssnReturn != null && ssnReturn.getStatusCode() == ReturnType.SUCCESS) {
//					ssnPersonId = db.getPersonIdUsingPsuId(ssnReturn.getPsuId());
//				}
//			} catch (CprException e) {
//				ssnPersonId = -1;
//				//hasSSN = false;
//			}
//		}
		
		// ensure the the IDs match
		if ((hasPsuId && hasUserId && psuIdPersonId != userIdPersonId) ||
			(hasUserId && hasSSN && userIdPersonId != ssnPersonId) ||
			(hasPsuId && hasSSN && psuIdPersonId != ssnPersonId)) {
			return false;
		}
		
		// if all IDs were invalid, return false
		if (psuIdPersonId == -1 && userIdPersonId == -1 && ssnPersonId == -1) {
			return false;
		}
		
		// update personId
		if (hasPsuId) {
			personId = psuIdPersonId;
		} else if (hasUserId) {
			personId = userIdPersonId;
		} else if (hasSSN) {
			personId = ssnPersonId;
		}
		
		return true;
	}
	
	/**
	 * Get the primary user id (access id) from the CPR.
	 * 
	 * @return the primary user ID or an empty string on error
	 * @throws CprException 
	 */
	public String getPrimaryUserIdFromCPR() throws CprException {
	
		Log4jLogger.debug("FPH: now getting primary userid for person id = " + personId);
		
		if (personId < 0) {
			return "";
		}
		
		final UseridTable u = new UseridTable();
		u.setReturnHistoryFlag(false);
		final UseridReturn[] queryResults = u.getUseridsForPersonId(db, personId);
		
		if (queryResults == null || queryResults.length == 0) {
			return "";
		}
		
		for (UseridReturn uid : queryResults) {
			if (uid.getPrimary().equals("Y")) {
				return uid.getUserid();
			}
		}
		
		return "";
		
	}
	
	public String getPrimaryUserIdFromCPRFor(Long persId) throws CprException {
		
		Log4jLogger.debug("FPH: now getting primary userid for person id = " + persId);
		
		if (persId < 0) {
			return "";
		}
		
		final UseridTable u = new UseridTable();
		u.setReturnHistoryFlag(false);
		final UseridReturn[] queryResults = u.getUseridsForPersonId(db, persId);
		
		Log4jLogger.debug("FPH: now looking up user id = " + persId);
		
		if (queryResults == null || queryResults.length == 0) {
			return "";
		}
		
		Log4jLogger.debug("FPH: number of userids found = " + queryResults.length);
		
		for (UseridReturn uid : queryResults) {
			if (uid.getPrimary().equals("Y")) {
				return uid.getUserid();
			}
		}
		
		return "";
		
	}
		
	/**
	 * Check if a GI match code for a personid matches the value stored in the CPR
	 *
	 * @throws Exception
	 * @return true if the matchCode matches the matchCode in the CPR; otherwise false.
	 */
	public boolean matchNameMatchCodeFromCPR()  {

		if (personId < 0 || giNameMatchCode == null || giNameMatchCode.equals("")) {
			return false;
		}
			Session session = db.getSession();
			SQLQuery query = session.createSQLQuery(CPR_GET_NAME_MATCH_CODES);
			query.setParameter("person_id_in", personId);
			query.setParameter("name_match_code_in", giNameMatchCode);
			query.addScalar("name_match_code", StandardBasicTypes.STRING);
			if (query.list().size() > 0) {
				return true;
			}
			else {
				return false;
			}
	}

	/**
	 * Return an address match code for a person id
	 * 
	 * @throws GeneralDatabaseException if unable to connect to the database.
	 * 
	 * @return The address match code or an empty string on error
	 */
	public String getAddressMatchCodeFromCPR()  {
		
		if (personId < 0) {
			return "";
		}
		
			Session session = db.getSession();
			SQLQuery query = session.createSQLQuery(CPR_GET_ADDRESS_MATCH_CODES);
			query.setParameter("person_id_in", personId);
			query.addScalar("address_match_code", StandardBasicTypes.STRING);
			
			Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				return (String) it.next();
			}
			else {
				return "";
			}
	}
	
	/**
	 * Return a city match code for a person id
	 * 
	 * @throws GeneralDataBaseException If unable to connect to the database.
	 * 
	 * @return The city match code or an empty string on error
	 */
	public String getCityMatchCodeFromCPR() {
		
		if (personId < 0) {
			return "";
		}
		
			Session session = db.getSession();
			SQLQuery query = session.createSQLQuery(CPR_GET_CITY_MATCH_CODES);
			query.setParameter("person_id_in", personId);
			query.addScalar("city_match_code", StandardBasicTypes.STRING);
			Iterator<?> it = query.list().iterator();
			
			if (it.hasNext()) {
				return (String) it.next();
			}
			else {
				return "";
			}
	}
	
	/**
	 * Call the database with JDBC to get any near matches.
	 *
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 */
	public MatchReturn[] getNearMatchesFromCPR(final String state, final String postalCode, final String country,
			final String dob, final String requestedBy, final Long rankCutOff) throws CprException {
		
//		Log4jLogger.debug("FPH: Starting near match process.");
//		Log4jLogger.debug("FPH: person id = " + personId);
//		Log4jLogger.debug("FPH: dob = " + dob);
//
///**		
//		if (personId < 0) {
//			return new MatchReturn[0];
//		}
//*/
//		
//		final StringBuilder parameters = new StringBuilder(1000);
//		parameters.append("giNameMatchCode=[").append(giNameMatchCode).append("] ");
//		parameters.append("giAddressMatchCode=[").append(giAddressMatchCode).append("] ");
//		parameters.append("giCityMatchCode=[").append(giCityMatchCode).append("] ");
//		if (FindPersonHelper.USA_COUNTRY_CODE.equals(country)) {
//			parameters.append("state=[").append(state).append("] ");
//		}
//		else {
//			parameters.append("country=[").append(country).append("] ");
//		}
//		parameters.append("postalCode=[").append(postalCode).append("] ");
//		String testDob = dob.replaceAll("/", "");
//		if (testDob.length() == 4) {
//			testDob = testDob + "0000";
//		}
//		parameters.append("testDob=[").append(testDob).append("] ");
//		parameters.append("requestedBy=[").append(requestedBy).append("] ");
//		parameters.append("Types.NUMERIC=[").append(Types.NUMERIC).append("] ");
//		Log4jLogger.info("FPH: Stored Procedure parms = " + parameters.toString());
//
//		Session session = db.getSession();
//		MatchingTable matchTable = null;
//		MatchReturn nearMatches[] = null;
//		session.doWork(new Work() {
//
//			public void execute(Connection conn) {
//				CallableStatement stmt = null;
//				String query = new String();
//				try {
//					
//					String modifiedDob = dob.replaceAll("/", "");
//					if (modifiedDob.length() == 4) {
//						modifiedDob = modifiedDob + "0000";
//					}
//					
//					
//					if (FindPersonHelper.USA_COUNTRY_CODE.equals(country)) {
//						query = CPR_CALL_MATCH_PACKAGE_US;
//					}
//					else {
//						query = CPR_CALL_MATCH_PACKAGE_NON_US;
//					}
//					
//					stmt = conn.prepareCall(query);
//					
//					// Set up output and input parameters.
//					stmt.setString(1, giNameMatchCode);
//					stmt.setString(2, giAddressMatchCode);
//					stmt.setString(3, giCityMatchCode);
//					
//					if (FindPersonHelper.USA_COUNTRY_CODE.equals(country)) {
//						stmt.setString(4, state);
//					}
//					else {
//						stmt.setString(4, country);
//					}
//					
//					stmt.setString(5, postalCode);
//					stmt.setString(6, modifiedDob);
//					stmt.setString(7, requestedBy);
//					stmt.registerOutParameter(8, Types.NUMERIC);
//									
//					// Execute the stored function.
//					stmt.execute();
//								
//					// Get the status from the database.
//					setMatchSet(stmt.getLong(8));
//				}
//				catch (Exception e) {
//					setMatchSet(-1L);
//				}
//				finally {
//					try {
//						stmt.close();
//					}
//					catch (Exception e) {
//					}
//				}
//			}		
//		});
//		
//		Log4jLogger.debug("FPH: return code from stored procedure " + getMatchSet());	
//		if (getMatchSet() == 0) {
//			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "person");
//		}
//		else if (getMatchSet() == -1) {
//			throw new GeneralDatabaseException("Exception from stored procedure");
//		}
//
//		Log4jLogger.debug("FPH: Ready to select records >= rank cutoff from Match_Results table(match_set_id = " + getMatchSet() + ")");	
//		matchTable = new MatchingTable(getMatchSet());
//		List<MatchResultsTable> matchResults = matchTable.getMatchSetWithLimitAndCutoff(db, MAX_NEAR_MATCH_RESULTS, rankCutOff);
//		Log4jLogger.debug("FPH: Number records selected from Match_Results table = " + matchResults.size());		
//		Log4jLogger.debug("FPH: Ready to build near match return rows [person id, score, psu id, primary user id");
//		if (matchResults.size() != 0) {
//			nearMatches = new MatchReturn[matchResults.size()];
//			// loop through matchResults and retrieve the psuId and userId for each entry
//			int i = 0;
//			PsuIdTable psuIdTable = new PsuIdTable(); 
//			psuIdTable.setReturnHistoryFlag(false);
//			for (MatchResultsTable match : matchResults) {
//				MatchReturn nextEntry = new MatchReturn();
//				nextEntry.setPersonId(match.getPersonId());
//				nextEntry.setScore(match.getScore());
//				PsuIdReturn psuIdReturn[] = psuIdTable.getPsuIdForPersonId(db, match.getPersonId());
//				if (psuIdReturn.length == 0) {
//					nextEntry.setPsuId(null);
//				}
//				else {
//					nextEntry.setPsuId(psuIdReturn[0].getPsuId());
//				}
//				nextEntry.setUserId(getPrimaryUserIdFromCPRFor(match.getPersonId()));
//				Log4jLogger.debug("FPH: return entry " + (i + 1) + 
//						": person id = " + nextEntry.getPersonId() + 
//						", score = " + nextEntry.getScore() +
//						", PSU id = " + nextEntry.getPsuId() +
//						", primary uid = " + nextEntry.getUserId());
//				nearMatches[i] = nextEntry;
//				i++;
//			}
//		}
//		Log4jLogger.debug("FPH: near matches array complete. size = " + nearMatches.length);
//		return nearMatches;
		
		return null;
	}
	
	/**
	 * Parse a date string and return a Calendar object.
	 * 
	 * @param dateString The date to parse (in MM-DD-YYYY or MM/DD/YYYY format)
	 * @param lenient Should parsing be lenient
	 * 
	 * @return a Calendar object
	 * @throws ParseException if dateString is malformed
	 */
	public static final Date parseDateString(final String dateString, boolean lenient) throws ParseException {
		Log4jLogger.debug("FPH: now = parseDateString for string = " + dateString);
		
//		if (dateString == null) {
//			throw new ParseException("Date string is null", 0);
//		}
//		
//		SimpleDateFormat sdf = new SimpleDateFormat(CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_FORMAT_PARTIAL_DATE.toString()));
//		sdf.setLenient(lenient);
//		Date d = sdf.parse(dateString.replace('-', '/').trim());
//		
//		return d;
		return null;
	}
	
	/**
	 * Convert a Date object into a Calendar object.
	 * 
	 * @param d The date to convert
	 * @param lenient Should parsing be lenient
	 * 
	 * @return a Calendar object
	 */
	public static final Calendar convertDateToCalendar(final Date d, boolean lenient) {

		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setLenient(lenient);
		cal.setTime(d);	
	
		return cal;
	}

	/**
	 * @param matchSet the matchSet to set
	 */
	public void setMatchSet(Long matchSet) {
		this.matchSet = matchSet;
	}

	/**
	 * @return the matchSet
	 */
	public Long getMatchSet() {
		return matchSet;
	}
}
