/* SVN FILE: $Id: CprComponentStatusTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.CprComponentStatus;
import edu.psu.iam.cpr.core.database.types.ComponentStatus;
import edu.psu.iam.cpr.core.database.types.CprComponent;

/**
 * This class provides the implementation for the CPR Component Status database table.
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
public class CprComponentStatusTable {
	
	private CprComponentStatus cprComponentStatusBean;
	
	/**
	 * Constructor.
	 */
	public CprComponentStatusTable() {
		super();
		
		Date d = new Date();
		CprComponentStatus bean = new CprComponentStatus();
		
		bean.setActiveFlag(null);				// Will be set elsewhere.
		bean.setCprComponent(null);				// Will be set elsewhere.
		bean.setCprComponentDesc(null);			// Will be set elsewhere.
		
		bean.setLastUpdateBy("SYSTEM");
		bean.setLastUpdateOn(d);
		
		setCprComponentStatusBean(bean);
	}

	/**
	 * This routine is used to obtain the status as to whether a component of the CPR is active or not.
	 * @param db contains the database connection.
	 * @param cprComponent contains the component to be retrieved.
	 * @return will return true if the component is active, otherwise it will return false.
	 */
	public boolean isCprComponentActive(Database db, CprComponent cprComponent) {
		
		try {
			final Session session = db.getSession();
			
			String sqlQuery = "from CprComponentStatus where cprComponent = :cpr_component";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("cpr_component", cprComponent.toString());
			boolean activeFlag = false;
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				CprComponentStatus cprComponentStatus = (CprComponentStatus) it.next();
				if (cprComponentStatus.getActiveFlag().equals("Y")) {
					activeFlag = true;
				}
			}
			return activeFlag;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This routine is used to update the CPR Component status.
	 * @param db contains the database connection.
	 * @param cprComponent contains the component to be updated.
	 * @param componentStatus contains the status to be set.
	 */
	public void changeComponentStatus(Database db, CprComponent cprComponent, ComponentStatus componentStatus) {
		
		try {
			
			final Session session = db.getSession();
			String sqlQuery = "from CprComponentStatus where cprComponent = :cpr_component";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("cpr_component", cprComponent.toString());
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				CprComponentStatus cprComponentStatusBean = (CprComponentStatus) it.next();

				switch (componentStatus) {
				case ACTIVE:
					cprComponentStatusBean.setActiveFlag("Y");
					break;
					
				case DISABLED:
					cprComponentStatusBean.setActiveFlag("N");
					break;
					
				default:
					break;
				}
				
				cprComponentStatusBean.setLastUpdateOn(new Date());
				
				session.update(cprComponentStatusBean);
				session.flush();
			}
			
		}
		catch (Exception e) {
		}
		
	}
	
	/**
	 * @param cprComponentStatusBean the cprComponentStatusBean to set
	 */
	public void setCprComponentStatusBean(CprComponentStatus cprComponentStatusBean) {
		this.cprComponentStatusBean = cprComponentStatusBean;
	}

	/**
	 * @return the cprComponentStatusBean
	 */
	public CprComponentStatus getCprComponentStatusBean() {
		return cprComponentStatusBean;
	}

}
