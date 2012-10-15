/* SVN FILE: $Id: CampusCsTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.CampusCs;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * This class provides the implementation for the campus codeset database table.
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
public class CampusCsTable {

	/**	 Contains a reference to the campus code set bean. */
	private CampusCs campusCsBean;
	
	/**
	 * Empty Constuctor
	 */
	public CampusCsTable() {
		super();
	}

	/**
	 * @param campusCsBean the campusCsBean to set
	 */
	public void setCampusCsBean(CampusCs campusCsBean) {
		this.campusCsBean = campusCsBean;
	}

	/**
	 * @return the campusCsBean
	 */
	public CampusCs getCampusCsBean() {
		return campusCsBean;
	}

	/**
	 * Obtain the campus information using a campus code.
	 * @param db contains an open database connection.
	 * @param campusCode contains the campus code to be used for the search.
	 * @param  retrievedBy contains the userid requesting the compus code
	 * @throws GeneralDatabaseException will be thrown if there are any database problems.
	 * @throws CprException 
	 */
	public  void getCampusInfo(Database db, String campusCode, String retrievedBy) throws GeneralDatabaseException, CprException {
		
		boolean found = false;
		try {
			final Session session = db.getSession();
			
			final CampusCs bean = new CampusCs();
			bean.setCampusCode(campusCode);
			bean.setCampusCodeKey(null);
			setCampusCsBean(bean);
			
			if (bean.getCampusCode() == null || bean.getCampusCode().length() == 0) {
				bean.setCampusCodeKey(null);
				return;
			}
			
			bean.setCampusCode(bean.getCampusCode().toUpperCase().trim());
			
			final StringBuilder sb = new StringBuilder(128);
			sb.append("SELECT campus_code_key, campus ");
			sb.append("FROM campus_cs ");
			sb.append("WHERE campus_code = :campus_code_in ");
			sb.append("AND active_flag = 'Y' ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("campus_code_in", bean.getCampusCode());
			query.addScalar("campus_code_key", StandardBasicTypes.LONG);
			query.addScalar("campus", StandardBasicTypes.STRING);
			final Iterator<?> it = query.list().iterator();
			
			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				bean.setCampusCodeKey((Long) res[0]);
				bean.setCampus((String) res[1]);
				found = true;
			}
			
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (! found) {
			throw new GeneralDatabaseException("Unable to retrieve campus name for campus code = " + campusCode);		
		}
	}
}

