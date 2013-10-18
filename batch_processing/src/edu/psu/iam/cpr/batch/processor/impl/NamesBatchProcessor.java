/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.batch.processor.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.PersonBio;
import edu.psu.iam.cpr.core.database.postprocess.NamesPostProcessor;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.ChangeNotification;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.DataQualityService;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides the concrete implementation for the names batch processing.  This class is executed to
 * process the results of names changes for the current day.
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
public class NamesBatchProcessor extends GenericBatchProcessor {
	
	/** Instance of logger */
	private static final Logger LOG = Logger.getLogger(NamesBatchProcessor.class);

	/** Contains the number of records to fetch for initial processing */
	private static final int RECORD_FETCH_SIZE = 50;
	
	/** Contains the starting date/time for processing of names changes for the current date. */
	private Date startDateTime = null;
	
	/** Contains the ending date/time for processing of names changes for the current date */
	private Date endDateTime = null;

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
	public void implementBatchProcessor(StatelessSession databaseSession,
			MessagingCore messagingCore, DataQualityService dataQualityService)
			throws CprException, ParseException, JSONException, JMSException {
		
		final Date d = new Date();
		final long startTime, stopTime;
		final long totalRecords;
		long recordsProcessed = 0;

		startTime = System.currentTimeMillis();

		// Calculate the start and end date/times based on the current date.
		setStartDateTime(Utility.makeStartDate(d));
		setEndDateTime(Utility.makeEndDate(d));
		
		// Do a select to find all of the people who have had a name changed for the current date.
		final Query query = databaseSession.createQuery("select distinct personId from NamesStaging where importDate BETWEEN :start_date AND :end_date");
		query.setFetchSize(RECORD_FETCH_SIZE);
		query.setParameter("start_date", startDateTime);
		query.setParameter("end_date", endDateTime);
		totalRecords = query.list().size();
		
		// Init some objects.
		StatelessSession recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		final NamesPostProcessor namesPostProcessor = new NamesPostProcessor(recordSession, messagingCore);
		final ChangeNotification changeNotification = new ChangeNotification(recordSession, messagingCore);
		final PersonBio personBio = new PersonBio(recordSession, BatchDataSource.NAME_POSTPROCESS, dataQualityService);
		Transaction tx = null;
		
		// Loop through the results.
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Long personId = (Long) it.next();

			try {
				
				tx = recordSession.beginTransaction();
								
				// Process a name change for a person.
				namesPostProcessor.resetHistoryBeans();
				namesPostProcessor.processNameChange(personId);

				// Only check for messaging if there was a change.
				if (namesPostProcessor.getOldNamesBean() != null ||
						namesPostProcessor.getNewNamesBean() != null) {
				
					// Find the person using their person identifier.
					Long pid = personBio.findPersonUsingPersonId(personId);
					if (pid != null && pid.equals(personId)) {

						// Set the required information for a json message.
						changeNotification.setRequiredInfo(personId, 
								personBio.getPsuIdNumber(), 
								personBio.getPrimaryUserid(), 
								namesPostProcessor.getNewNamesBean().getImportFrom());

						// Name change.
						changeNotification.nameChange(namesPostProcessor.getOldNamesBean(), namesPostProcessor.getNewNamesBean());
					}
				}
				
				tx.commit();
				recordsProcessed++;

			}
			catch (HibernateException e) {
				LOG.info("Names Post Processor Batch: error encountered person identifier : " + personId);
				tx.rollback();
				recordSession.close();
				recordSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
				namesPostProcessor.setStatelessSession(recordSession);
				changeNotification.setStatelessSession(recordSession);
				personBio.setDatabaseSession(recordSession);
			}
		}
		
		try {
			recordSession.close();
		}
		catch (Exception e) { // $codepro.audit.disable emptyCatchClause
		}
		
		stopTime = System.currentTimeMillis();
		final double elapsedTime = ((double) stopTime - startTime) / 1000;

		LOG.info(BatchDataSource.NAME_POSTPROCESS.toString() + " Batch: processed " + recordsProcessed + " records out of " + 
				totalRecords + " in " + elapsedTime + " seconds");

	}

	/**
	 * @param startDateTime the startDateTime to set
	 */
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * @return the startDateTime
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * @param endDateTime the endDateTime to set
	 */
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	/**
	 * @return the endDateTime
	 */
	public Date getEndDateTime() {
		return endDateTime;
	}

}
