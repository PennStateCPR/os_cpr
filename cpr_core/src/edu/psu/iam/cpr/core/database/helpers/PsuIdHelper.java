/* SVN FILE: $Id: PsuIdHelper.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.helpers;

import java.util.Date;
import java.util.Random;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.beans.PsuId;
import edu.psu.iam.cpr.core.database.tables.GeneratedIdentityTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.util.CprProperties;

/**
 * PsuIdHelper is a helper class that is used to aid the generation of PSU IDs.
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
public class PsuIdHelper {
	
	/** Minimum value for a PSU ID in production */
	private static final int PRODUCTION_MIN_VALUE = 900000000;
	
	/** Maximum value for a PSU ID in production */
	private static final int PRODUCTION_MAX_VALUE = 999999999;
	
	/** Minimum value for a PSU ID in acceptance */
	private static final int ACCEPTANCE_MIN_VALUE = 970000000;
	
	/** Maximum value for a PSU ID in acceptance */
	private static final int ACCEPTANCE_MAX_VALUE = 974999999;
	
	/** Minimum value for a PSU ID in test */
	private static final int TEST_MIN_VALUE = 970000000;
	
	/** Maximum value for a PSU ID in test */
	private static final int TEST_MAX_VALUE = 974999999;
	
	/** Array containing the minimum values for test, acceptance and production */
	private static final int[] MIN_RANGE_VALUES = { TEST_MIN_VALUE, ACCEPTANCE_MIN_VALUE, PRODUCTION_MIN_VALUE };
	
	/** Array containing the maximum values for test, acceptance and production. */
	private static final int[] MAX_RANGE_VALUES = { TEST_MAX_VALUE, ACCEPTANCE_MAX_VALUE, PRODUCTION_MAX_VALUE };

	private static final int BUFFER_SIZE = 2000;
	
	/** Contains an instance of the generated identity table implementation */
	private GeneratedIdentityTable generatedIdentityTable;
	
	/**
	 * This routine is used to generate a random number which represents a PSU ID.  The number generated will depend on 
	 * the mode the CPR is running in.
	 * @return will return the generated random number.
	 */
	private int getRandomPSUIdNumber() {
		
		final int index = (int) CprProperties.INSTANCE.getCprMode().index();
		final int minVal = MIN_RANGE_VALUES[index];
		final int maxVal = MAX_RANGE_VALUES[index];
		
		final Random random = new Random(new Date().getTime());
		
		return random.nextInt(maxVal-minVal+1) + minVal;		
	}
	
	/**
	 * This routine will generate a new PSU ID for a person and assign it.
	 * @param session contains a database session.
	 * @param bean contains the PSU ID database bean where the resultant PSU ID will be placed.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public void generatePSUIdNumber(Session session, final PsuId bean) throws CprException {
		
		boolean done = false;
		
		while (! done) {
			
			// Obtain a new PSU ID.
			String psuId = Integer.toString(getRandomPSUIdNumber());			
			
			// Determine if the new PSU ID is not already used, in the exception list or tagged for assignment. 
			StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			sb.append("SELECT psu_id FROM cpr.psu_id WHERE psu_id = :psu_id_1");
			sb.append(" UNION ");
			sb.append("SELECT psu_id FROM cpr.psu_id_exceptions WHERE psu_id = :psu_id_2");
			sb.append(" UNION ");
			sb.append("SELECT generated_identity AS psu_id from cpr.generated_identity WHERE generated_identity = :psu_id_3");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.addScalar("psu_id", StandardBasicTypes.STRING);
			query.setParameter("psu_id_1", psuId);
			query.setParameter("psu_id_2", psuId);
			query.setParameter("psu_id_3", psuId);
			
			// Not found, so we can assign it to the user.
			if (query.list().size() == 0) {
				
				setGeneratedIdentityTable(new GeneratedIdentityTable(bean.getPersonId(), psuId, bean.getLastUpdateBy()));
				getGeneratedIdentityTable().addGeneratedIdentity(session);
				
				bean.setPsuId(psuId);
				
				done = true;
			}
		}
	}

	/**
	 * @return the generatedIdentityTable
	 */
	public GeneratedIdentityTable getGeneratedIdentityTable() {
		return generatedIdentityTable;
	}

	/**
	 * @param generatedIdentityTable the generatedIdentityTable to set
	 */
	public void setGeneratedIdentityTable(
			GeneratedIdentityTable generatedIdentityTable) {
		this.generatedIdentityTable = generatedIdentityTable;
	}

}
