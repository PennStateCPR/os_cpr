/* SVN FILE: $Id: UseridHelper.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.helpers;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.types.NameType;

/**
 * UseridHelper is a helper class that is used to aid the generation of userids.
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
 * @package edu.psu.iam.cpr.core.database.helpers
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class UseridHelper {
	
	/** Array of strings containing letters to be used if a first name was not specified */
	private static final String[] FIRST_LETTERS = {"u","q","x","z","o","i","y"};
	
	/** Array of strings containing letters to be used if the middle name was not specified */
	private static final String[] MIDDLE_LETTERS = {"x","u","z","q","y","o","i"};
	
	/** Array of strings containing letters to be used if the last night was not specified */
	private static final String[] LAST_LETTERS = {"x","q","u"};
	
	/** Contains the total number of letters in a userid */
	private static final int TOTAL_LETTERS = 3;
	
	private static final double FIRST_LETTER_RANGE1 = 0.2418;
	private static final double FIRST_LETTER_RANGE2 = 0.4217;
	private static final double FIRST_LETTER_RANGE3 = 0.5456;
	private static final double FIRST_LETTER_RANGE4 = 0.6669;
	private static final double FIRST_LETTER_RANGE5 = 0.7842;
	private static final double FIRST_LETTER_RANGE6 = 0.8922;

	private static final double MIDDLE_LETTER_RANGE1 = 0.1907;
	private static final double MIDDLE_LETTER_RANGE2 = 0.3787;
	private static final double MIDDLE_LETTER_RANGE3 = 0.5329;
	private static final double MIDDLE_LETTER_RANGE4 = 0.6827;
	private static final double MIDDLE_LETTER_RANGE5 = 0.8291;
	private static final double MIDDLE_LETTER_RANGE6 = 0.9139;

	private static final double LAST_LETTER_RANGE1 = 0.3766;
	private static final double LAST_LETTER_RANGE2 = 0.7082;

	private static final int BUFFER_SIZE = 20;

	private static final int INDEX_0 = 0;
	private static final int INDEX_1 = 1;
	private static final int INDEX_2 = 2;
	private static final int INDEX_3 = 3;
	private static final int INDEX_4 = 4;
	private static final int INDEX_5 = 5;
	private static final int INDEX_6 = 6;

	private static final int FIRST_LETTER = 0;
	private static final int MIDDLE_LETTER = 1;
	private static final int LAST_LETTER = 2;
	
	/**
	 * This routine is used to obtain the first letter contained with a name.
	 * @param namePart contains the name to extract the first letter from.
	 * @return will return the first letter if found, otherwise it will return null.
	 */
	private String getFirstLetter(final String namePart) {

		if (namePart == null) {
			return namePart;
		}
		
		final char[] letters = namePart.toLowerCase().trim().toCharArray();
		for (int i = 0; i < letters.length; ++i) {
			if (letters[i] >= 'a' && letters[i] <= 'z') {
				return new Character(letters[i]).toString();
			}
		}
		return null;
	}
	
	/**
	 * This routine is used to generate a userid an assign it.
	 * @param session contains a database session.
	 * @param bean contains a userid database bean that will contain the assigned userid.
	 */
	public void generateUserid(final Session session, final Userid bean)  {
		

		// Obtain the list of active names for the person.
		String sqlQuery = "from Names where personId = :person_id AND endDate IS NULL";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("person_id", bean.getPersonId());
		Names names = null;
		for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			names = (Names) it.next();

			// If we find a legal name, let's use it.
			if (names.getDataTypeKey() == NameType.LEGAL_NAME.index()) {
				break;
			}
		}

		// Extract the first letter from the selected name.
		String firstNameLetter 		= null;
		String middleNamesLetter 	= null;
		String lastNameLetter 		= null;
		if (names != null) {
			firstNameLetter 		= getFirstLetter(names.getFirstName());
			middleNamesLetter 		= getFirstLetter(names.getMiddleNames());
			lastNameLetter 			= getFirstLetter(names.getLastName());
		}

		boolean done = false;
		int missCount = 0;
		final Random random = new Random(new Date().getTime());

		while (! done) {
			double randomValue = random.nextDouble();

			// first name was not specified.
			if (firstNameLetter == null) {
				if (randomValue < FIRST_LETTER_RANGE1) {
					firstNameLetter = FIRST_LETTERS[INDEX_0];
				}
				else if (randomValue < FIRST_LETTER_RANGE2) {
					firstNameLetter = FIRST_LETTERS[INDEX_1];
				}
				else if (randomValue < FIRST_LETTER_RANGE3) {
					firstNameLetter = FIRST_LETTERS[INDEX_2];
				}
				else if (randomValue < FIRST_LETTER_RANGE4) {
					firstNameLetter = FIRST_LETTERS[INDEX_3];
				}
				else if (randomValue < FIRST_LETTER_RANGE5) {
					firstNameLetter = FIRST_LETTERS[INDEX_4];
				}
				else if (randomValue < FIRST_LETTER_RANGE6) {
					firstNameLetter = FIRST_LETTERS[INDEX_5];
				}
				else {
					firstNameLetter = FIRST_LETTERS[INDEX_6];
				}
			}

			// Middle names was not specified.
			if (middleNamesLetter == null) {
				if (randomValue < MIDDLE_LETTER_RANGE1) {
					middleNamesLetter = MIDDLE_LETTERS[INDEX_0];
				}
				else if (randomValue < MIDDLE_LETTER_RANGE2) {
					middleNamesLetter = MIDDLE_LETTERS[INDEX_1];
				}
				else if (randomValue < MIDDLE_LETTER_RANGE3) {
					middleNamesLetter = MIDDLE_LETTERS[INDEX_2];
				}
				else if (randomValue < MIDDLE_LETTER_RANGE4) {
					middleNamesLetter = MIDDLE_LETTERS[INDEX_3];
				}
				else if (randomValue < MIDDLE_LETTER_RANGE5) {
					middleNamesLetter = MIDDLE_LETTERS[INDEX_4];
				}
				else if (randomValue < MIDDLE_LETTER_RANGE6) {
					middleNamesLetter = MIDDLE_LETTERS[INDEX_5];
				}
				else {
					middleNamesLetter = MIDDLE_LETTERS[INDEX_6];
				}
			}

			// Last name was not specified.
			if (lastNameLetter == null) {
				if (randomValue < LAST_LETTER_RANGE1) {
					lastNameLetter = LAST_LETTERS[INDEX_0];
				}
				else if (randomValue < LAST_LETTER_RANGE2) {
					lastNameLetter = LAST_LETTERS[INDEX_1];
				}
				else {
					lastNameLetter = LAST_LETTERS[INDEX_2];
				}
			}

			// Construct the character portion of the userid.
			StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			sb.append(firstNameLetter);
			sb.append(middleNamesLetter);
			sb.append(lastNameLetter);

			String charPart = sb.toString();

			// Verify that the character porition of the userid does not exist in the bad prefixes database table.
			sqlQuery = "from BadPrefixes where charPart = :char_part";
			query = session.createQuery(sqlQuery);
			query.setParameter("char_part", charPart);

			if (query.list().size() == 0) {

				// Find a userid in the pool to be used.
				sqlQuery = "SELECT MIN(num_part) AS min_num_part FROM {h-schema}userid_pool WHERE char_part = :char_part";
				SQLQuery query1 = session.createSQLQuery(sqlQuery);
				query1.setParameter("char_part", charPart);
				query1.addScalar("min_num_part", StandardBasicTypes.LONG);
				Long numPart = null;
				for (Iterator<?> it = query1.list().iterator(); it.hasNext(); ) {
					numPart = (Long) it.next();
				}

				String userid = charPart + numPart.toString();

				// Delete the userid from the pool.
				query = session.createQuery("delete UseridPool where charPart = :char_part AND numPart = :num_part");
				query.setParameter("char_part", charPart);
				query.setParameter("num_part", numPart);
				query.executeUpdate();
				session.flush();

				bean.setUserid(userid);
				done = true;					
			}
			else {

				// OK, let's pick a different letter.
				missCount = (missCount + 1) % TOTAL_LETTERS;
				switch (missCount) {
				case FIRST_LETTER:
					firstNameLetter = null;
					break;
				case MIDDLE_LETTER:
					middleNamesLetter = null;
					break;
				case LAST_LETTER: 
					lastNameLetter = null;
					break;
				}
			}
		}
	}
}
