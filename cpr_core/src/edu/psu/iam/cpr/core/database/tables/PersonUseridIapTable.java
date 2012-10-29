/* SVN FILE: $Id: PersonUseridIapTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PersonUseridIap;
import edu.psu.iam.cpr.core.database.types.IapType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.IAPReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 *  This class provides an implementation for interfacing with the PersonUseridIap database
 * table.  There are methods within here to get IAP (external, PSU) for a 
 * person in the CPR.
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
public class PersonUseridIapTable {

	
	
	/** contains the personUseridIap Bean */
	private PersonUseridIap personUseridIapBean;
	
	/** contains the iapType */
	private IapType iapType;
	
	/** Boolean flag that indicates whether to return all historical records or not */
	private boolean returnHistoryFlag;
	
	/** 
	 * 
	 * @return IapType
	 */
	public IapType getIapType() {
		return iapType;
		
	}
	/**
	 * @param iapType the iapType to set
	 * 
	 */
	
	public final void setIapType (IapType iapType) {
		this.iapType = iapType;
	}
	
	/**
	 * @param iapTypeString the iapTypeString to set
	 * 
	 */
	
	public final void setIapType(String iapTypeString) {
		setIapType(IapType.valueOf(iapTypeString.toUpperCase().trim()));
	};

	
	
	/**
	 * @param personUseridIapBean the personUserIapBean to set
	 */
	public final void setPersonUseridIapBean(PersonUseridIap personUseridIapBean) {
		this.personUseridIapBean = personUseridIapBean;
	}

	/**
	 * @return the addressesBean
	 */
	public PersonUseridIap  getPersonUseridIapBean() {
		return personUseridIapBean;
	}
	
	/**
	 * Constructor
	 */
	public PersonUseridIapTable() {
		super();
		
	}

	/**
	 * Constructor
	 * @param personId contains the personId
	 * @param userId contains the userid
	 * @param iapTypeString contains the IAP type string
	 * @param updatedBy contains the userid of updater
	 */
	public PersonUseridIapTable(long personId, String userId, String iapTypeString, String updatedBy) {
		
		super();
		final PersonUseridIap bean = new PersonUseridIap();
		setPersonUseridIapBean(bean);
		final Date d = new Date();
		bean.setPersonId(personId);
		bean.setUserid(userId);
		setIapType(iapTypeString);
		bean.setCreatedOn(d);
		bean.setCreatedBy(updatedBy);
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

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
		 * Retrieve all active PSU IAPS for a userid.
		 * 
		 * @param db contains the Database Object
		 * @param personId contains the personId
		 * @param userid The userid to query
		 * @param federation contains the name of the federation
		 * @return A list of IAPs
		 * @throws CprException
		 * @throws GeneralDatabaseException on database errors
		 */
		public  IAPReturn[] getExternalIAP( Database db,  long personId, String userid, String federation ) throws GeneralDatabaseException, CprException {
		
			boolean useridValid = false;
			try {
				
				final Session session = db.getSession();
				useridValid = db.isValidUserid(personId, userid);
				if (useridValid) {
					final String upperFed = federation.toUpperCase();
					final StringBuilder sb = new StringBuilder(2000);
				
					final List<IAPReturn> results = new ArrayList<IAPReturn>();
					sb.append("SELECT external_iap ");
					sb.append("FROM v_external_iap_federation ");
					sb.append("WHERE userid = :userid_in ");
					sb.append("AND person_id = :person_id ");
					sb.append("AND UPPER(federation)=:federation_in");
					final SQLQuery query  = session.createSQLQuery(sb.toString());
					query.setParameter("userid_in", userid);
					query.setParameter("person_id", personId);
					query.setParameter("federation_in",upperFed);
					final Iterator<?> it = query.list().iterator();

					while (it.hasNext()) {
						String iapFed= (String) it.next();
						IAPReturn anIAP = new IAPReturn();
						anIAP.setIap( iapFed);
						results.add(anIAP);

					}
					return results.toArray(new IAPReturn[results.size()]);
					

				}
			}
			catch (Exception e) {
				throw new GeneralDatabaseException("Unable to retrieve Federation " + federation + " IAPs for user id " + userid);			
			}
			if (!useridValid) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "userid");
			}
			else {
				return null;
			}        
		}
	/**
	 * Retrieve all active PSU IAPS for a userid.
	 * @param db An active database handle
	 * @param personId contains the person_id
	 * @param userid The userid to query
	 * 
	 * @return A list of IAPs
	 * @throws CprException
	 * @throws GeneralDatabaseException on database errors
	 */
	public  IAPReturn[] getPSUIAP( Database db, long personId, String userid) throws GeneralDatabaseException, CprException {
		
		boolean useridValid = false;
		try {
			final Session session = db.getSession();
			useridValid = db.isValidUserid(personId, userid);
			if (useridValid) {
		
				final List<IAPReturn> results = new ArrayList<IAPReturn>();
				final StringBuilder sb = new StringBuilder(200);
				sb.append("SELECT iap.iap, ");
				sb.append("person_userid_iap.start_date, ");
				sb.append("person_userid_iap.end_date, ");
				sb.append("person_userid_iap.last_update_by, " );
				sb.append("person_userid_iap.last_update_on, ");
				sb.append("person_userid_iap.created_by, " );
				sb.append("person_userid_iap.created_on ");
				sb.append("FROM iap ");
				sb.append("LEFT JOIN person_userid_iap ON iap.iap_key = person_userid_iap.iap_key ");
				sb.append("WHERE person_userid_iap.person_id = :person_id_in ");
				sb.append("AND person_userid_iap.userid=:userid_in ");
				
				
				// If we are not returning all records, we need to just return the active ones.
				if (! isReturnHistoryFlag()) {
					sb.append("AND person_userid_iap.end_date IS NULL ");
				}		
				sb.append("ORDER BY iap.iap_key ASC, person_userid_iap.start_date ASC ");
				final SQLQuery query = session.createSQLQuery(sb.toString());
				query.setParameter("person_id_in", personId);

				query.setParameter("userid_in",userid);
				query.addScalar("iap",StandardBasicTypes.STRING );
				query.addScalar("start_date",StandardBasicTypes.TIMESTAMP );
				query.addScalar("end_date",StandardBasicTypes.TIMESTAMP );
				query.addScalar("last_update_by",StandardBasicTypes.STRING );
				query.addScalar("last_update_on",StandardBasicTypes.TIMESTAMP );
				query.addScalar("created_by",StandardBasicTypes.STRING );
				query.addScalar("created_on",StandardBasicTypes.TIMESTAMP );
				
				for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
					Object res[] = (Object []) it.next();
					IAPReturn anIAP = new IAPReturn();
					anIAP.setIap((String) res[0]);
					anIAP.setStartDate(Utility.convertTimestampToString((Date) res[1]));
					anIAP.setEndDate(Utility.convertTimestampToString((Date)res[2]));
					anIAP.setLastUpdateBy((String) res[3]);
					anIAP.setLastUpdateOn(Utility.convertTimestampToString((Date) res[4]));
					anIAP.setCreatedBy((String) res[5]);
					anIAP.setCreatedOn(Utility.convertTimestampToString((Date) res[6]));
					results.add(anIAP);

				}
				
				return results.toArray(new IAPReturn[results.size()]);
			}
		
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve IAP for person identifier = " + personId);			
		}
		if (!useridValid) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "userid");
			

		}
		else
		{
			return null;
		}
	}
}
