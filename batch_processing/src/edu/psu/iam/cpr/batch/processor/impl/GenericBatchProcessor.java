package edu.psu.iam.cpr.batch.processor.impl;
import java.text.ParseException;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.DataQualityService;
 
public abstract class GenericBatchProcessor {
	
	/** Instance of logger */
	private static final Logger LOG = Logger.getLogger(GenericBatchProcessor.class);
 
    /** Database connection to be used to iterate over the records contained in the temporary database table. */
    private StatelessSession databaseSession = null;
 
    /** Messaging core instance */
    private MessagingCore messagingCore = null;
    
    /** Instance of a data quality service */
    private DataQualityService dataQualityService = null;
    
	private static final int BUFFER_SIZE = 1024;
 
	/**
	 * Main driver for batch processing.
	 */
    public void processBatch() {
    	
        Transaction tx = null;
        
        try {
            initBatch();
            tx = databaseSession.beginTransaction();
            implementBatchProcessor(databaseSession, messagingCore, dataQualityService);
            tx.commit();
        }
        catch (JDBCException e) {
            handleJDBCException(LOG, tx, e);
        }
        catch (CprException e) {
        	handleCprException(LOG, tx, e);
        }
        catch (RuntimeException e) {
            handleOtherException(LOG, tx, e);
        } 
        catch (ParseException e) {
        	handleOtherException(LOG, tx, e);
		} 
        catch (JSONException e) {
        	handleOtherException(LOG, tx, e);
		} 
        catch (JMSException e) {
        	handleOtherException(LOG, tx, e);
		}
        finally {
            try {
                cleanupBatch();
            }
            catch (Exception e) { // $codepro.audit.disable emptyCatchClause
            }
        }
    }
 
    /**
     * Initialize batch processing, obtain database and messaging connections.
     * @throws CprException will be thrown for any CPR Related problems.
     * @throws JMSException will be thrown if messaging could not be initialized.
     */
    private void initBatch() throws CprException,JMSException {
 
        // Establish database and messaging session.
        databaseSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();

        // create messaging core
        messagingCore = new MessagingCore(databaseSession, "BatchProcessing");
        messagingCore.initializeMessaging();
        
        // Create an instance of the data quality service.
        dataQualityService = new DataQualityService();
 
    }
    
	/**
	 * This routine is used to handle generic exceptions.
	 * @param log4jLogger contains a log4j Logger instance.
	 * @param tx contains a database transaction.
	 * @param e Contains the generic exception.
	 */
	public void handleOtherException(Logger log4jLogger, 
			Transaction tx,  
			Exception e) {
		
		log4jLogger.error("Exception: " + e.getMessage(), e);
		tx.rollback();
	}
	
	/**
	 * This routine is used to handle exceptions of the JDBC type.
	 * @param log4jLogger contains a log4j Logger instance.
	 * @param tx contains a database transaction.
	 * @param e contains the JDBCEXception exception information.
	 * @return contains the formatted String.
	 */
	public String handleJDBCException(Logger log4jLogger,
			Transaction tx, 
			JDBCException e) {

		String sqlState = e.getSQLState();
		sqlState = (sqlState == null) ? "N/A" : sqlState;
		final int errorCode = e.getErrorCode();
		String exceptionMessage = e.getMessage();
		exceptionMessage = (exceptionMessage == null) ? "EMPTY" : exceptionMessage;

		final StringBuffer sb = new StringBuffer(BUFFER_SIZE);
		sb.append("Database Exception: ");
		sb.append("Message = ");
		sb.append(exceptionMessage);
		sb.append(", Error Code = ");
		sb.append(errorCode);
		sb.append(", SQL State = ");
		sb.append(sqlState);

		final String formattedException = sb.toString();
		
		log4jLogger.error(formattedException, e);
		tx.rollback();
		return formattedException;
	}

	/**
	 * This routine is used to handle a CPR specific exception.
	 * @param log4jLogger contains an instance of a log4j Logger.
	 * @param tx contains a database transaction.
	 * @param e contains the CPR specific exception information.
	 * @return will return a formatted string of the exception.
	 */
	public String handleCprException(Logger log4jLogger,
			Transaction tx, 
			CprException e) {
		String errorMessage = e.getReturnType().message();
		if (e.getParameterValue() != null) {
			errorMessage = String.format(errorMessage, e.getParameterValue());
		}
		
		log4jLogger.error("CPR Exception: " + e.getReturnType().toString() + ", Message: " + errorMessage, e);
		tx.rollback();
		return errorMessage;
	}
 
    /**
     * Free up any resources.
     */
    private void cleanupBatch() {
 
    	try {
    		// perform batch clean up.
    		databaseSession.close();
    	}
    	catch (HibernateException e) { // $codepro.audit.disable emptyCatchClause
    	}

    	try {
    		dataQualityService.closeSession();
    	} 
    	catch (Exception e) { // $codepro.audit.disable emptyCatchClause
    	}
    	
    	// Close messaging.
    	messagingCore.closeMessaging();
    }
 
    /**
     * Implement the specific parts of batch processing.
     * @param databaseSession contains the database session (stateless).
     * @param messagingCore contains a reference to the messaging Core.
     * @param dataQualityService contains a reference to the data flux service.
     * @throws JSONException will be thrown if there are any JSON problems.
     * @throws JMSException will be thrown if there are any JMS problems.
     */
    public abstract void implementBatchProcessor(StatelessSession databaseSession, MessagingCore messagingCore, 
    		DataQualityService dataQualityService) throws CprException, ParseException, JSONException, JMSException;
 
}
