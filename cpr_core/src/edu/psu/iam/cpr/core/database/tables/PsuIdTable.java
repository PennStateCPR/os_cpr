/* SVN FILE: $Id: PsuIdTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PsuId;
import edu.psu.iam.cpr.core.database.helpers.PsuIdHelper;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for interfacing with the PSU ID database table.
 * This table contains a mapping of PSU IDs to people within the CPR.  This class
 * provides methods to add and get PSU ID information.
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
public class PsuIdTable {
	
	private static final int PSU_ID = 0;

	private static final int START_DATE = 1;

	private static final int END_DATE = 2;

	private static final int LAST_UPDATE_BY = 3;

	private static final int LAST_UPDATE_ON = 4;

	private static final int CREATED_BY = 5;

	private static final int CREATED_ON = 6;

	/** contains a reference to the PSU ID database bean */
	private PsuId psuIdBean;
	
	/** Contains the set id returned from the stored procedure */
	private int setId;
	
	/** Boolean flag that indicates whether to return history on the GET or not */
	private boolean returnHistoryFlag;
	
	/** Contains an instance of the PsuIdHelper class. */
	private PsuIdHelper psuIdHelper;
	
	/**
	 * Constructor.
	 */
	public PsuIdTable() {
		super();		
	}
	
	/**
	 * Constructor
	 * @param personId
	 * @param psuId
	 * @param updatedBy
	 */
	public PsuIdTable(long personId, String psuId, String updatedBy) {
		
		super();
		final PsuId bean = new PsuId();
		final Date d = new Date();
		setPsuIdBean(bean);
		
		// Init the constructor.
		bean.setPersonId(personId);
		bean.setPsuId(null); 
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		setPsuIdHelper(new PsuIdHelper());
	}
	
	/**
	 * @param returnHistoryFlag the returnHistoryFlag to set
	 */
	public void setReturnHistoryFlag(boolean returnHistoryFlag) {
		this.returnHistoryFlag = returnHistoryFlag;
	}

	/**
	 * @return the returnHistoryFlag
	 */
	public boolean isReturnHistoryFlag() {
		return returnHistoryFlag;
	}

	/**
	 * @param setId the setId to set
	 */
	public void setSetId(int setId) {
		this.setId = setId;
	}

	/**
	 * @return the setId
	 */
	public int getSetId() {
		return setId;
	}

	/**
	 * @param psuIdBean the psuIdBean to set
	 */
	public final void setPsuIdBean(PsuId psuIdBean) {
		this.psuIdBean = psuIdBean;
	}

	/**
	 * @return the psuIdBean
	 */
	public PsuId getPsuIdBean() {
		return psuIdBean;
	}

	/**
	 * @param psuIdHelper the psuIdHelper to set
	 */
	public final void setPsuIdHelper(PsuIdHelper psuIdHelper) {
		this.psuIdHelper = psuIdHelper;
	}

	/**
	 * @return the psuIdHelper
	 */
	public PsuIdHelper getPsuIdHelper() {
		return psuIdHelper;
	}

	/**
	 * This routine is used to obtain the PSU ID for a specific person.
	 * @param db contains the open database connection.
	 * @param personId contains the person identifier used to retrieve the PSU ID for.
	 * @return will return a PsuIdReturn object if success.
	 * @throws GeneralDatabaseException
	 */
	public PsuIdReturn[] getPsuIdForPersonId(Database db, long personId) throws GeneralDatabaseException {

		try {
			final Session session = db.getSession();
			final ArrayList<PsuIdReturn> results = new ArrayList<PsuIdReturn>();
			final StringBuilder sb = new StringBuilder(2048);
			
			sb.append("SELECT psu_id, ");
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("FROM psu_id WHERE person_id=:person_id ");
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			sb.append("ORDER BY start_date ASC ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id", personId);
			query.addScalar("psu_id", StandardBasicTypes.STRING);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);
			
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Object res[] = (Object []) it.next();
				results.add(new PsuIdReturn((String) res[PSU_ID],									
										Utility.convertTimestampToString((Date) res[START_DATE]),		
										Utility.convertTimestampToString((Date) res[END_DATE]),		
										(String) res[LAST_UPDATE_BY],										
										Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]),		
										(String) res[CREATED_BY],										
										Utility.convertTimestampToString((Date) res[CREATED_ON])));		
			}
			return results.toArray(new PsuIdReturn[results.size()]);
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve PSU ID for person identifier = " + personId);
		}
	}

	
	/**
	 * This routine is used to add a PSU ID for a specific person.
	 * @param db contains a open database connection
	 * @throws CprException 
	 */
	public void addPsuIdForPersonId(Database db) throws CprException {
		
		final Session session = db.getSession();
		try {
			final PsuId bean = getPsuIdBean();

			// Obtain a PSU ID using a stored procedure.
			getPsuIdHelper().generatePSUIdNumber(session, bean);

			// Expire the existing PSU ID.
			final String sqlQuery = "from PsuId where personId = :person_id AND endDate IS NULL";
			final Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				PsuId dbBean = (PsuId) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				session.update(dbBean);
				session.flush();
			}

			// Add the new one.
			session.save(bean);
			session.flush();
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "PSU ID");
		}
		finally {
			try {
				getPsuIdHelper().getGeneratedIdentityTable().removeGeneratedIdentity(session);
			}
			catch (Exception e) {
			}
		}
	}
}
