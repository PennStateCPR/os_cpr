/* SVN FILE: $Id: FederationTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 *  This class provides an implementation for interfacing with the Federation database
 * table.  There are methods within here to determine if a federation is valid in the CPR.
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
public class FederationTable {

	private static final int BUFFER_SIZE = 256;

	/**
	 * 
	 * @param db  contains Database object
	 * @param federationName  contains the federationName to validate		
	 * @return true if valid federation, false otherwse
	 * @throws CprException
	 * @throws GeneralDatabaseException
	 */
	public boolean isFederationValid (Database db, String federationName) throws CprException {
		
		boolean fedValid = false;
		final Session session = db.getSession();
		String localFederationName = (federationName != null) ? federationName.trim() : null;

		String activeFlag = "";
		final String upperFed = localFederationName.toUpperCase();
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT active_flag ");
		sb.append("FROM federation ");
		sb.append("WHERE UPPER(federation)  = :fed_name_in ");
		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("fed_name_in", upperFed);
		query.addScalar("active_flag", StandardBasicTypes.STRING);
		final Iterator<?> it = query.list().iterator();
		if (it.hasNext()) {
			activeFlag = (String) it.next();
			if (activeFlag.equals("Y")) {
				fedValid = true;
			}
		}
		else {
			fedValid = false;
		}	
		if (! fedValid) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Federation name");
		}
		return fedValid;
		
	}
}
