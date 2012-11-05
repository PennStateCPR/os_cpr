/* SVN FILE: $Id: IdCardPrintLogTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.IdCardPrintLog;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 *  This class provides an implementation for interfacing with the ID_CARD_PRINT_LOG database
 * table.  There are methods within here to add, and get id_card_print_log for a 
 * person in the CPR.
 *  * 
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
 * 
 * @package edu.psu.iam.cpr.core.database.tables;
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

public class IdCardPrintLogTable {

	private static final int PERSON_ID = 0;
	private static final int ID_CARD_NUMBER = 1;
	private static final int IP_ADDRESS = 2;
	private static final int WORKSTATION_NAME = 3;
	private static final int PRINTED_BY = 4;
	private static final int PRINT_DATE = 5;

	private static final int BUFFER_SIZE = 1024;
	
	/**
	 * contain reference to idcardprintlog
	 */
	private IdCardPrintLog idCardPrintLogBean;

	
	/**
	 * contains idCardNumber
	 * 
	 */
	private String eventIdCardNumber;
	
	/**
	 * @return the idCardPrintLogBean
	 */
	public IdCardPrintLog getIdCardPrintLogBean() {
		return idCardPrintLogBean;
	}

	/**
	 * @param idCardPrintLogBean the idCardPrintLogBean to set
	 */
	public final void setIdCardPrintLogBean(IdCardPrintLog idCardPrintLogBean) {
		this.idCardPrintLogBean = idCardPrintLogBean;
	}

	
	
	/**
	 * @return the eventIdCardNumber
	 */
	public String getEventIdCardNumber() {
		return eventIdCardNumber;
	}

	/**
	 * @param eventIdCardNumber the eventIdCardNumber to set
	 */
	public void setEventIdCardNumber(String eventIdCardNumber) {
		this.eventIdCardNumber = eventIdCardNumber;
	}

	/**
	 * Default constructor for GetIdCardPrintLog
	 */
	public IdCardPrintLogTable() {
		super();
		
	}
	/**
	 * 
	 * @param eventIdCardNumber
	 */
	public IdCardPrintLogTable( String eventIdCardNumber) {
		
		this.eventIdCardNumber = eventIdCardNumber;
	}
	/**
	 * Constructor to AddIdCardPrintLogTable
	 * @param eventIdCardNumber contains the id card number associated with the event
	 * @param eventUserId contains the id of the user printing the card
	 * @param eventIpAddr contains the ip address of the workstation where the id card was printed
	 * @param eventWorkStation contains the workstation name where the id card was printed
	 * 
	 */
	public IdCardPrintLogTable( String eventIdCardNumber, String eventUserId, String eventIpAddr , String eventWorkStation ) {
		super();
		
		
		final Date d = new Date();
	
		final IdCardPrintLog bean = new IdCardPrintLog();
		this.eventIdCardNumber = eventIdCardNumber;
		setIdCardPrintLogBean(bean);
		
		bean.setPrintedBy(eventUserId);
		bean.setWorkStationIpAddress(eventIpAddr);
		bean.setWorkStationName(eventWorkStation);
		bean.setPrintedOn(d);
		
		
		
	}
	/**
	 * Add an Id Card Print Log event
	 * 
	 * @param db
	 * @throws CprException
	 */
	public void addIdCardPrintLog(Database db) throws CprException {
	
		boolean noPersonIdCard = false;
		final Session session = db.getSession();
		final IdCardPrintLog bean = getIdCardPrintLogBean();
		final String sqlQuery = "SELECT person_id_card_key FROM person_id_card WHERE id_card_number = :idCard AND end_date IS NULL";
		final SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setParameter("idCard", eventIdCardNumber);
		query.addScalar("person_id_card_key",  StandardBasicTypes.LONG);
		final Iterator<?> it = query.list().iterator();
		if (it.hasNext()) {
			bean.setPersonIdCardKey((Long)it.next());
			session.save(bean);
			session.flush();
		}
		else
		{
			noPersonIdCard = true;
		}
		if (noPersonIdCard) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "Id Card Print Log");
		}
	}
	
	/**
	 * Get an IdCard Print Log event
	 * @param db contains a database connection.
	 * @return IdCardPrintLogReturn array of results.
	 */
	public IdCardPrintLogReturn[] getIdCardPrintLog( Database db) {
		
		final List<IdCardPrintLogReturn> results = new ArrayList<IdCardPrintLogReturn>();
		final Session session = db.getSession();

		final StringBuffer sb = new StringBuffer(BUFFER_SIZE);
		sb.append("SELECT person_id, id_card_number, work_station_ip_address, ");
		sb.append("work_station_name, printed_by , printed_on ");
		sb.append("FROM v_person_id_card_print_log WHERE id_card_number = :id_card_number_in ");
		sb.append("order by printed_on ASC");

		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("id_card_number_in", getEventIdCardNumber());
		query.addScalar("person_id", StandardBasicTypes.LONG);
		query.addScalar("id_card_number", StandardBasicTypes.STRING);
		query.addScalar("work_station_ip_address", StandardBasicTypes.STRING);
		query.addScalar("work_station_name", StandardBasicTypes.STRING);
		query.addScalar("printed_by", StandardBasicTypes.STRING);
		query.addScalar("printed_on", StandardBasicTypes.TIMESTAMP);
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Object res[] = (Object []) it.next();
			IdCardPrintLogReturn anIdLog = new IdCardPrintLogReturn();
			anIdLog.setPersonId((Long) res[PERSON_ID]);
			anIdLog.setIdCardNumber((String) res[ID_CARD_NUMBER]);
			anIdLog.setIpAddress((String) res[IP_ADDRESS]);
			anIdLog.setWorkStationName((String) res[WORKSTATION_NAME]);
			anIdLog.setPrintedBy((String)res[PRINTED_BY]);
			anIdLog.setPrintDate(Utility.convertTimestampToString((Date) res[PRINT_DATE]));
			results.add(anIdLog);
		}
		return results.toArray(new IdCardPrintLogReturn[results.size()]);
		
	}
	
}
