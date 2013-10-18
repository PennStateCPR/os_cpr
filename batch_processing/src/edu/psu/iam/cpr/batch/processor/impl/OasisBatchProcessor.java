/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.batch.processor.impl;

import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.AccessAccountStatus;
import edu.psu.iam.cpr.core.database.batch.PersonBio;
import edu.psu.iam.cpr.core.database.beans.TransOasis;
import edu.psu.iam.cpr.core.database.types.AccessAccountStatusType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.ChangeNotification;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.DataQualityService;

/**
 * This class provides the concrete implementation for the OASIS (CACTUS Access Account status file) batch processor.
 * This class will process all of the records in the file, determine if new userids need to be create and update the individual
 * status records.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.batch
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class OasisBatchProcessor extends GenericBatchProcessor {

	/** Instance of logger */
	private static final Logger LOG = Logger.getLogger(OasisBatchProcessor.class);

	/** Contains the number of records to fetch for initial processing */
	private static final int RECORD_FETCH_SIZE = 50;

	/** Contains the name of the database table for the source of records */
	private static final String TRANS_DATABASE_TABLE = "TransOasis";

	/** Contains the name of the batch data source associated with this processor */
	private static final BatchDataSource BATCH_DATA_SOURCE = BatchDataSource.OASIS;

	/**
	 * This method implements the core logic of the batch processor.
	 * @param databaseSession contains the database session from the abstract class that will be used to iterator over the individual records.
	 * @param messagingCore contains a reference to the messaging infrastructure.
	 * @param dataQualityService contains a reference to the data quality service.
	 * @throws CprException will be thrown for any Cpr Related problems.
	 * @throws JSONException will be thrown for any JSON problems.
	 * @throws JMSException will be thrown for any messaging problems.
	 */
	@Override
	public void implementBatchProcessor(final StatelessSession databaseSession, final MessagingCore messagingCore, final DataQualityService dataQualityService)
			throws CprException, JSONException, JMSException {

		final long startTime, stopTime;
		final long totalRecords;
		long recordsProcessed = 0;

		startTime = System.currentTimeMillis();

		// Perform a query for all of the trans oasis records.
		final Query query = databaseSession.createQuery("from " + TRANS_DATABASE_TABLE);
		query.setFetchSize(RECORD_FETCH_SIZE);

		StatelessSession recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		final PersonBio personBio = new PersonBio(recordSession, BATCH_DATA_SOURCE, dataQualityService);
		final AccessAccountStatus accessAccountStatus = new AccessAccountStatus(recordSession, messagingCore);
		final ChangeNotification changeNotification = new ChangeNotification(recordSession, messagingCore);
		Transaction tx = null;

		final List<?> queryList = query.list();
		totalRecords = queryList.size();

		// Loop for all of the records that were found.
		for (final Iterator<?> it = queryList.iterator(); it.hasNext(); ) {
			final TransOasis transOasis = (TransOasis) it.next();
			recordsProcessed++;
			try {
				// Begin transaction.
				tx = recordSession.beginTransaction();

				// Using person bio, find the person and their associated userid.  NOTE: if the userid is not in the userid table, person bio will add it.
				personBio.resetHistoryBeans();
				personBio.findPerson(transOasis.getPsuId());
				final Long personId = personBio.getPersonId();
				if (personId == null) {
					LOG.info(BATCH_DATA_SOURCE.toString() + " Batch: psu id number not found error encountered on record #: " +
							transOasis.getTransOasisKey());
				}
				else {
					final String userid = transOasis.getUserid().toLowerCase();
					personBio.updateUserid(userid);
					
					final String status = transOasis.getStatus();

					// Update the status.
					accessAccountStatus.processStatusChange(personId, userid, AccessAccountStatusType.get(status));

					if (personBio.getPrimaryUserid() == null && personBio.getNewUserid() != null) {
						personBio.setPrimaryUserid(personBio.getNewUserid().getUserid());
					}

					changeNotification.setRequiredInfo(personId, personBio.getPsuIdNumber(), personBio.getPrimaryUserid(),
							BATCH_DATA_SOURCE.toString());

					changeNotification.useridChange(personBio.getOldUserid(), personBio.getNewUserid());

					if (accessAccountStatus.getOldAccessAccountStatus() != accessAccountStatus.getNewAccessAccountStatus()) {
						changeNotification.accountStatusChange(accessAccountStatus.getOldAccessAccountStatus(), accessAccountStatus.getNewAccessAccountStatus());
					}
				}

				// Commit!
				tx.commit();
			}
			catch (final HibernateException e) { // $codepro.audit.disable logExceptions

				// Log the error.
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transOasis.getTransOasisKey());

				// Rollback the transaction, close the session.
				tx.rollback();
				recordSession.close();

				// We need to create a new session and update the person bio and access account status classes with the new session.
				recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
				personBio.setDatabaseSession(recordSession);
				accessAccountStatus.setDatabaseSession(recordSession);
				changeNotification.setStatelessSession(recordSession);
			}
			catch (final CprException ex) {

				// Log the error.
				LOG.error(BATCH_DATA_SOURCE.toString() + " Batch: error encountered on record #: " + transOasis.getTransOasisKey(), ex);
				
				// Rollback the transaction, close the session.
				tx.rollback();
				recordSession.close();

				// We need to create a new session and update the person bio and access account status classes with the new session.
				recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
				personBio.setDatabaseSession(recordSession);
				accessAccountStatus.setDatabaseSession(recordSession);
				changeNotification.setStatelessSession(recordSession);
			}
		}

		try {
			recordSession.close();
		}
		catch (final HibernateException e) { // $codepro.audit.disable emptyCatchClause, logExceptions
		}

		stopTime = System.currentTimeMillis();
		final double elapsedTime = ((double) stopTime - startTime) / 1000;

		LOG.info(BATCH_DATA_SOURCE.toString() + " Batch: processed " + recordsProcessed + " records out of " + totalRecords + " in " + elapsedTime + " seconds");
	}


}
