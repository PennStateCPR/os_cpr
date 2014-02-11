/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.batch;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.StatelessSession;

import edu.psu.iam.cpr.core.database.beans.UserServiceStatus;
import edu.psu.iam.cpr.core.database.types.AccessAccountServiceType;
import edu.psu.iam.cpr.core.database.types.AccessAccountStatusType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.messaging.MessagingCore;

/**
 * This class contains the implementation for the processing of the Access Account status.  This status information 
 * comes from the CACTUS system in a file called OASIS.
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
public class AccessAccountStatus {
	
	/** Contains an instance of the message core object */
	private MessagingCore messagingCore;
	
	/** Contains an open database connection for a stateless session.  This is not final because if there a problem in the batch program
	 * it will need to be reassigned. */
	private StatelessSession databaseSession;
	
	/** Contains the batch data source */
	private final BatchDataSource batchDataSource = BatchDataSource.OASIS;
	
	/** Contains the date the service was added, effectively when this batch program was executed. */
	private final Date serviceDate = new Date();
	
	/** Contains the old Access Account Status, if there was a change. */
	private AccessAccountStatusType oldAccessAccountStatus = null;
	
	/** Contains the new Access Account status, if there was a change */
	private AccessAccountStatusType newAccessAccountStatus = null;
	
	/**
	 * Constructor.
	 */
	public AccessAccountStatus() {
		super();
		databaseSession = null;
		messagingCore = null;
	}
	
	/**
	 * Constructor.
	 * @param databaseSession contains the database session to be stored.
	 * @param messagingCore contains a reference to the messaging core class.
	 */
	public AccessAccountStatus(final StatelessSession databaseSession, final MessagingCore messagingCore) {
		super();
		this.databaseSession = databaseSession;
		this.messagingCore = messagingCore;
	}

	/**
	 * This method is used to process a status change for a person's access account.
	 * @param personId contains the person identifier for the person who was found using their PSU ID number.
	 * @param userid contains the user's userid.
	 * @param accessAccountStatusType contains the status value associated with the access account.
	 */
	public void processStatusChange(final long personId, final String userid, final AccessAccountStatusType accessAccountStatusType) {
		
		// Reset the account status.
		setOldAccessAccountStatus(null);
		setNewAccessAccountStatus(null);
		
		switch (accessAccountStatusType) {
		
		// Full Account.
		case FULL_ACCOUNT: 
			processFullAccountStatus(personId, userid);
			break;
		
		// Partial Account.
		case PARTIAL_ACCOUNT: 
			processPartialAccountStatus(personId, userid);
			break;
		
		// Locked Account.
		case LOCKED_ACCOUNT: 
			processLockedAccountStatus(personId, userid);
			break;
		
		// Deleted Account.
		case DELETED_ACCOUNT: 
			processLockedAccountStatus(personId, userid);
			break;
			
		default:
			break;
		}
		setNewAccessAccountStatus(accessAccountStatusType);
	}
	
	/**
	 * This method is used to process a request for removing services for a particular user.
	 * @param personId contains the person identifier.
	 * @param userid contains the userid.
	 */
	public void processLockedAccountStatus(final long personId, final String userid) {
		
		// Check the status of all of the services associated with an access account.
		final boolean hasKerberos 	= isServiceActive(personId, userid, AccessAccountServiceType.KERBEROS);
		final boolean hasDirectory 	= isServiceActive(personId, userid, AccessAccountServiceType.DIRECTORY);
		final boolean hasEmail 		= isServiceActive(personId, userid, AccessAccountServiceType.EMAIL);
		final boolean hasPASS 		= isServiceActive(personId, userid, AccessAccountServiceType.PASS);

		// Already locked.
		if ((! hasKerberos) && (! hasDirectory) && (! hasEmail) && (! hasPASS)) {
			setOldAccessAccountStatus(AccessAccountStatusType.LOCKED_ACCOUNT);
			return;
		}
		
		// Full Account.
		else if (hasKerberos && hasDirectory && hasEmail && hasPASS) {
			setOldAccessAccountStatus(AccessAccountStatusType.FULL_ACCOUNT);
			removeService(personId, userid, AccessAccountServiceType.KERBEROS);
			removeService(personId, userid, AccessAccountServiceType.DIRECTORY);
			removeService(personId, userid, AccessAccountServiceType.EMAIL);
			removeService(personId, userid, AccessAccountServiceType.PASS);
		}
		
		// Partial Account.
		else if (hasKerberos && hasDirectory && (! hasEmail) && (! hasPASS)) {
			setOldAccessAccountStatus(AccessAccountStatusType.PARTIAL_ACCOUNT);
			removeService(personId, userid, AccessAccountServiceType.KERBEROS);
			removeService(personId, userid, AccessAccountServiceType.DIRECTORY);
		}
	}

	/**
	 * This method is used to process a full access account status record.  It ensures that the person has all of the services
	 * related to a full access account, and they don't it will add them.
	 * @param personId contains the person identifier associated with the userid.
	 * @param userid contains the userid to be checked.
	 */
	public void processFullAccountStatus(final long personId, final String userid) {
		
		// Check the status of all of the services associated with an access account.
		final boolean hasKerberos 	= isServiceActive(personId, userid, AccessAccountServiceType.KERBEROS);
		final boolean hasDirectory 	= isServiceActive(personId, userid, AccessAccountServiceType.DIRECTORY);
		final boolean hasEmail 		= isServiceActive(personId, userid, AccessAccountServiceType.EMAIL);
		final boolean hasPASS 		= isServiceActive(personId, userid, AccessAccountServiceType.PASS);
		
		// Already a full account.
		if (hasKerberos && hasDirectory && hasEmail && hasPASS) {
			setOldAccessAccountStatus(AccessAccountStatusType.FULL_ACCOUNT);
			return;
		}
		
		// Partial Account.
		else if (hasKerberos && hasDirectory) {
			setOldAccessAccountStatus(AccessAccountStatusType.PARTIAL_ACCOUNT);
			addService(personId, userid, AccessAccountServiceType.EMAIL);
			addService(personId, userid, AccessAccountServiceType.PASS);
		}
		
		// Determine the missing services to be added.
		else {
			if (isAccountNew(personId, userid)) {
				setOldAccessAccountStatus(null);
			}
			else {
				setOldAccessAccountStatus(AccessAccountStatusType.LOCKED_ACCOUNT);
			}
			addService(personId, userid, AccessAccountServiceType.KERBEROS);
			addService(personId, userid, AccessAccountServiceType.DIRECTORY);
			addService(personId, userid, AccessAccountServiceType.EMAIL);
			addService(personId, userid, AccessAccountServiceType.PASS);
		}
	}
	
	/**
	 * This method to use to process a record with a partial access account status.  That means that the account has
	 * authentication and directory.
	 * @param personId contains the person identifier associated with the record.
	 * @param userid contains the userid associated with the record.
	 */
	public void processPartialAccountStatus(final long personId, final String userid) {
		
		// Check the status of all of the services associated with an access account.
		final boolean hasKerberos 	= isServiceActive(personId, userid, AccessAccountServiceType.KERBEROS);
		final boolean hasDirectory 	= isServiceActive(personId, userid, AccessAccountServiceType.DIRECTORY);
		final boolean hasEmail 		= isServiceActive(personId, userid, AccessAccountServiceType.EMAIL);
		final boolean hasPASS 		= isServiceActive(personId, userid, AccessAccountServiceType.PASS);
		
		// Already partial.
		if (hasKerberos && hasDirectory && (! hasEmail) && (! hasPASS)) {
			setOldAccessAccountStatus(AccessAccountStatusType.PARTIAL_ACCOUNT);
			return;
		}
		else {
			
			// New Account.
			if (isAccountNew(personId, userid)) {
				setOldAccessAccountStatus(null);
				addService(personId, userid, AccessAccountServiceType.KERBEROS);
				addService(personId, userid, AccessAccountServiceType.DIRECTORY);
			}
			
			// Full Account.
			else if (hasKerberos && hasDirectory && hasEmail && hasPASS) {
				setOldAccessAccountStatus(AccessAccountStatusType.FULL_ACCOUNT);
				removeService(personId, userid, AccessAccountServiceType.EMAIL);
				removeService(personId, userid, AccessAccountServiceType.PASS);
			}
			
			// Locked Account
			else {
				setOldAccessAccountStatus(AccessAccountStatusType.LOCKED_ACCOUNT);
				addService(personId, userid, AccessAccountServiceType.KERBEROS);
				addService(personId, userid, AccessAccountServiceType.DIRECTORY);
			}
		}
		
	}
	
	/**
	 * This method is used to remove a service from a user, if they have it assigned to them.
	 * @param personId contains the person identifier associated with the user.
	 * @param userid contains the userid.
	 * @param accessAccountServiceType contains the access account service type to be expired.
	 */
	public void removeService(final long personId, final String userid, final AccessAccountServiceType accessAccountServiceType) {
		
		final StatelessSession session = getDatabaseSession();
		final Date d = getServiceDate();
		final String updatedBy = getBatchDataSource().toString();
		
		final Query query = session.createQuery(	
	"from UserServiceStatus where personId = :person_id AND userid = :userid AND serviceKey = :service_key AND deprovisionDate is NULL");
		query.setParameter("person_id", personId);
		query.setParameter("userid", userid);
		query.setParameter("service_key", accessAccountServiceType.index());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final UserServiceStatus userServiceStatus = (UserServiceStatus) it.next();
			userServiceStatus.setDeprovisionDate(d);
			userServiceStatus.setLastUpdateBy(updatedBy);
			userServiceStatus.setLastUpdateOn(d);
			session.update(userServiceStatus);
		}
		
	}

	/**
	 * This method is used to determine whether a person has a particular service or not.  It will return true if the person
	 * has the service, otherwise it will return false.
	 * @param personId contains the person identifier to be searched for.
	 * @param userid contains the userid to be searched for.
	 * @param accessAccountServiceType contains the type of service to be checked.
	 * @return will return true if the person actively has the service, otherwise it will return false.
	 */
	public boolean isServiceActive(final long personId, final String userid, final AccessAccountServiceType accessAccountServiceType) {
		
		final StatelessSession session = getDatabaseSession();
		final Query query = session.createQuery(
	"from UserServiceStatus where personId = :person_id AND userid = :userid AND serviceKey = :service_key AND deprovisionDate is NULL");
		query.setParameter("person_id", personId);
		query.setParameter("userid", userid);
		query.setParameter("service_key", accessAccountServiceType.index());
		return query.list().size() != 0;
	}
	
	/**
	 * This method is used to determine whether an account is new or not.
	 * @param personId contains the person identifier to be searched for.
	 * @param userid contains the userid to be searched for.
	 * @return will return true if the account is new, otherwise it will return false.
	 */
	public boolean isAccountNew(final long personId, final String userid) {
		final StatelessSession session = getDatabaseSession();
		final Query query = session.createQuery("from UserServiceStatus where personId = :person_id AND userid = :userid");
		query.setParameter("person_id", personId);
		query.setParameter("userid", userid);
		return query.list().size() == 0;		
	}
	
	/**
	 * This method is used to add a new service to the user service status table.
	 * @param personId contains the person identifier whose information is to be added.
	 * @param userid contains the userid associated with the person identifier.
	 * @param accessAccountServiceType contains the services.
	 */
	public void addService(final long personId, final String userid, final AccessAccountServiceType accessAccountServiceType) {
		
		// Do a second check to ensure that the service really is not associated with the user.
		if (! isServiceActive(personId, userid, accessAccountServiceType)) {
			
			// Create the bean.
			final Date d = getServiceDate();
			final String updatedBy = getBatchDataSource().toString();
			final StatelessSession session = getDatabaseSession();
			final Long consumerKey = getMessagingCore().getBatchMessageConsumerKey();
			
			final UserServiceStatus userServiceStatus = new UserServiceStatus();
			
			userServiceStatus.setPersonId(personId);
			userServiceStatus.setUserid(userid);
			userServiceStatus.setMessageConsumerKey(consumerKey);
			userServiceStatus.setServiceKey(accessAccountServiceType.index());

			userServiceStatus.setProvisionDate(d);
			userServiceStatus.setDeprovisionDate(null);
			userServiceStatus.setExpirationDate(null);

			userServiceStatus.setCreatedBy(updatedBy);
			userServiceStatus.setCreatedOn(d);
			
			userServiceStatus.setLastUpdateBy(updatedBy);
			userServiceStatus.setLastUpdateOn(d);

			session.insert(userServiceStatus);
		}
	}
	
	/**
	 * Will return the batch data source enumerated value.
	 * @return batchDataSource.
	 */
	public BatchDataSource getBatchDataSource() {
		return batchDataSource;
	}

	/**
	 * Will return the service date.
	 * @return serviceDate.
	 */
	public Date getServiceDate() {
		return serviceDate;
	}

	/**
	 * @param oldAccessAccountStatus the oldAccessAccountStatus to set
	 */
	public void setOldAccessAccountStatus(AccessAccountStatusType oldAccessAccountStatus) {
		this.oldAccessAccountStatus = oldAccessAccountStatus;
	}

	/**
	 * @return the oldAccessAccountStatus
	 */
	public AccessAccountStatusType getOldAccessAccountStatus() {
		return oldAccessAccountStatus;
	}

	/**
	 * @param newAccessAccountStatus the newAccessAccountStatus to set
	 */
	public void setNewAccessAccountStatus(AccessAccountStatusType newAccessAccountStatus) {
		this.newAccessAccountStatus = newAccessAccountStatus;
	}

	/**
	 * @return the newAccessAccountStatus
	 */
	public AccessAccountStatusType getNewAccessAccountStatus() {
		return newAccessAccountStatus;
	}

	/**
	 * @param databaseSession the databaseSession to set
	 */
	public void setDatabaseSession(StatelessSession databaseSession) {
		this.databaseSession = databaseSession;
	}

	/**
	 * @return the databaseSession
	 */
	public StatelessSession getDatabaseSession() {
		return databaseSession;
	}

	/**
	 * @param messagingCore the messagingCore to set
	 */
	public void setMessagingCore(MessagingCore messagingCore) {
		this.messagingCore = messagingCore;
	}

	/**
	 * @return the messagingCore
	 */
	public MessagingCore getMessagingCore() {
		return messagingCore;
	}

}
