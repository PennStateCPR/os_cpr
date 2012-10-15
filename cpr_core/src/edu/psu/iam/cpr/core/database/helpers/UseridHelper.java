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
import edu.psu.iam.cpr.core.database.tables.GeneratedIdentityTable;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

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
	private static final String[] firstLetters = {"u","q","x","z","o","i","y"};
	
	/** Array of strings containing letters to be used if the middle name was not specified */
	private static final String[] middleLetters = {"x","u","z","q","y","o","i"};
	
	/** Array of strings containing letters to be used if the last night was not specified */
	private static final String[] lastLetters = {"x","q","u"};
	
	/** Contains the total number of letters in a userid */
	private static final int TOTAL_LETTERS = 3;
	
	/** Contains an instance of the generated identity implementation */
	private GeneratedIdentityTable generatedIdentityTable;
	
	/**
	 * This routine is used to obtain the first letter contained with a name.
	 * @param namePart contains the name to extract the first letter from.
	 * @return will return the first letter if found, otherwise it will return null.
	 */
	private String getFirstLetter(String namePart) {

		if (namePart == null) {
			return namePart;
		}
		
		final char letters[] = namePart.toLowerCase().trim().toCharArray();
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
	 * @throws CprException will be thrown if there are any CPR exceptions.
	 */
	public void generateUserid(Session session, Userid bean) throws CprException {
		
		try {
			
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
					if (randomValue < 0.2418) {
						firstNameLetter = firstLetters[0];
					}
					else if (randomValue < 0.4217) {
						firstNameLetter = firstLetters[1];
					}
					else if (randomValue < 0.5456) {
						firstNameLetter = firstLetters[2];
					}
					else if (randomValue < 0.6669) {
						firstNameLetter = firstLetters[3];
					}
					else if (randomValue < 0.7842) {
						firstNameLetter = firstLetters[4];
					}
					else if (randomValue < 0.8922) {
						firstNameLetter = firstLetters[5];
					}
					else {
						firstNameLetter = firstLetters[6];
					}
				}
				
				// Middle names was not specified.
				if (middleNamesLetter == null) {
					if (randomValue < 0.1907) {
						middleNamesLetter = middleLetters[0];
					}
					else if (randomValue < 0.3787) {
						middleNamesLetter = middleLetters[1];
					}
					else if (randomValue < 0.5329) {
						middleNamesLetter = middleLetters[2];
					}
					else if (randomValue < 0.6827) {
						middleNamesLetter = middleLetters[3];
					}
					else if (randomValue < 0.8291) {
						middleNamesLetter = middleLetters[4];
					}
					else if (randomValue < 0.9139) {
						middleNamesLetter = middleLetters[5];
					}
					else {
						middleNamesLetter = middleLetters[6];
					}
				}
				
				// Last name was not specified.
				if (lastNameLetter == null) {
					if (randomValue < 0.3766) {
						lastNameLetter = lastLetters[0];
					}
					else if (randomValue < 0.7082) {
						lastNameLetter = lastLetters[1];
					}
					else {
						lastNameLetter = lastLetters[2];
					}
				}
				
				// Construct the character portion of the userid.
				StringBuilder sb = new StringBuilder(20);
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
					sqlQuery = "SELECT MIN(num_part) AS min_num_part FROM cpr.userid_pool WHERE char_part = :char_part";
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

					// Save it off.
					setGeneratedIdentityTable(new GeneratedIdentityTable(bean.getPersonId(), userid, charPart, numPart, bean.getLastUpdateBy()));
					getGeneratedIdentityTable().addGeneratedIdentity(session);
					
					bean.setUserid(userid);
					done = true;					
				}
				else {
					
					// OK, let's pick a different letter.
					missCount = (missCount + 1) % TOTAL_LETTERS;
					switch (missCount) {
					case 0:
						firstNameLetter = null;
						break;
					case 1:
						middleNamesLetter = null;
						break;
					case 2: 
						lastNameLetter = null;
						break;
					}
				}
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "Userid");
		}
	}

	/**
	 * @param generatedIdentityTable the generatedIdentityTable to set
	 */
	public void setGeneratedIdentityTable(GeneratedIdentityTable generatedIdentityTable) {
		this.generatedIdentityTable = generatedIdentityTable;
	}

	/**
	 * @return the generatedIdentityTable
	 */
	public GeneratedIdentityTable getGeneratedIdentityTable() {
		return generatedIdentityTable;
	}

}
