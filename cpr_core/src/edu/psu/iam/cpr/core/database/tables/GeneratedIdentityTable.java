/* SVN FILE: $Id: GeneratedIdentityTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.beans.GeneratedIdentity;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * GeneratedIdentityTable is an implementation of methods that manipulate data in the generated identity database table.
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
 * @package edu.psu.iam.cpr.core.database.table
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class GeneratedIdentityTable {
	
	/** Contains a reference to the generated identity bean */
	private GeneratedIdentity generatedIdentityBean;
	
	/**
	 * Constructor
	 * @param personId contains the person id.
	 * @param generatedIdentity contains the generated identity.
	 * @param requestedBy contains the entity that created the identity.
	 */
	public GeneratedIdentityTable(long personId, String generatedIdentity, String requestedBy) {
		this(personId, generatedIdentity, null, 0L, requestedBy);
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifier.
	 * @param generatedIdentity contains the generated identity.
	 * @param charPart contains the character portion of the identity (userids only).
	 * @param numPart contains the numerical portion of the identity (userids only).
	 * @param requestedBy contains the entity that requested the identity.
	 */
	public GeneratedIdentityTable(long personId, String generatedIdentity, String charPart, Long numPart, String requestedBy) {
		super();
		
		GeneratedIdentity bean = new GeneratedIdentity();
		setGeneratedIdentityBean(bean);
		
		bean.setPersonId(personId);
		
		bean.setGeneratedIdentity(generatedIdentity);
		bean.setCharPart(charPart);
		bean.setNumPart(numPart);
		
		bean.setSetId(1L);

		bean.setCreatedBy(requestedBy);
		bean.setCreatedOn(new Date());
		
	}

	/**
	 * @param generatedIdentityBean the generatedIdentityBean to set
	 */
	public final void setGeneratedIdentityBean(GeneratedIdentity generatedIdentityBean) {
		this.generatedIdentityBean = generatedIdentityBean;
	}

	/**
	 * @return the generatedIdentityBean
	 */
	public GeneratedIdentity getGeneratedIdentityBean() {
		return generatedIdentityBean;
	}
	
	/**
	 * This method is used to add data to the generated identity database table.
	 * @param session contains an open database session.
	 * @throws CprException will be thrown if there are any CPR related exceptions.
	 */
	public void addGeneratedIdentity(Session session) throws CprException {
		try {
			session.save(getGeneratedIdentityBean());
			session.flush();
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "Generated identity");
		}
	}
	
	/**
	 * This method is used to remove an entity from the generated identity database table.
	 * @param session contains an open database session.
	 * @throws CprException will be thrown if there are CPR related problems.
	 */
	public void removeGeneratedIdentity(Session session) throws CprException {
		try {
			Query query = session.createQuery("delete GeneratedIdentity where generatedIdentityKey = :generated_identity_key");
			query.setParameter("generated_identity_key", getGeneratedIdentityBean().getGeneratedIdentityKey());
			query.executeUpdate();
			session.flush();
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "Generated identity");
		}
	}
}
