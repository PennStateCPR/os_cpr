/* SVN FILE: $Id: CountryTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;
import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Country;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * This class provides the implementation for the country codeset database table.
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
 * @package edu.psu.iam.cpr.core.database.tables
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class CountryTable {

	private static final int BUFFER_SIZE = 128;
	
	/** Country code set bean. */
	private Country countryBean;

	/**
	 * Constructor
	 */
	public CountryTable() {
		super();
	}

	/**
	 * @param countryBean the countryBean to set
	 */
	public void setCountryBean(Country countryBean) {
		this.countryBean = countryBean;
	}


	/**
	 * @return the countryBean
	 */
	public Country getCountryBean() {
		return countryBean;
	}


	/**
	 * Obtain the Country information using a country code.
	 * @param db contains an open database connection.
	 * @param countryCode contains the country code to be retrieved from the database.
	 * @param retrieveBy contains the userid of the person doing the retrieve.
	 * @throws GeneralDatabaseException will be thrown if there are any database problems.
	 */
	public void getCountryInfo (Database db, String countryCode, String retrieveBy) throws GeneralDatabaseException {
		
		boolean found = false;
		try {
			final Session session = db.getSession();
			
			final Country bean = new Country();
			bean.setCountryKey(null);
			bean.setCountryCodeThree(countryCode);
			setCountryBean(bean);
			
			if (bean.getCountryCodeThree() == null || bean.getCountryCodeThree().length() == 0) {
				bean.setCountryKey(null);
				return;
			}
			
			bean.setCountryCodeThree(bean.getCountryCodeThree().toUpperCase().trim());
			
			final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			sb.append("SELECT country_key, country ");
			sb.append("FROM country ");
			sb.append("WHERE country_code_three = :country_code_in ");
			sb.append(" AND us_territory_flag='N' ");
			sb.append("AND end_date IS NULL ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("country_code_in", bean.getCountryCodeThree());
			query.addScalar("country_key", StandardBasicTypes.LONG);
			query.addScalar("country", StandardBasicTypes.STRING);
			final Iterator<?> it = query.list().iterator();
			
			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				bean.setCountryKey((Long) res[0]);
				bean.setCountry((String) res[1]);
				found = true;
			}
			
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (! found) {
			throw new GeneralDatabaseException("Unable to retrieve country name for country code = " + countryCode);		
		}
	}
}
