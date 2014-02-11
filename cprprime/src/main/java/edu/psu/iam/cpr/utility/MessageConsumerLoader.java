package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.MessageConsumer;

public class MessageConsumerLoader implements BeanLoader {

	@Override
	public void loadTable(Database db, String primeDirectory, String tableName) {
		BufferedReader bufferedReader = null;
		try {
			Date d = new Date();
			String requestor = "SYSTEM";
			
			db.openSession(SessionFactoryUtil.getSessionFactory());
			Session session = db.getSession();
			
			// Remove all of the records from the database table.
			String sqlQuery = "delete from " + tableName;
			Query query = session.createQuery(sqlQuery);
			query.executeUpdate();

			// Read in the first record containing the column headers.
			bufferedReader = new BufferedReader(new FileReader(primeDirectory + System.getProperty("file.separator") + tableName));
			String[] columns = bufferedReader.readLine().split("[|]");
			String line;
			
			// Read and process the file.
			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("[|]");
				
				MessageConsumer bean = new MessageConsumer();
				bean.setConsumerEmail("xyz123@example.edu");
				bean.setConsumerRegDate(d);
				bean.setStartDate(d);
				bean.setEndDate(null);
				bean.setSuspendFlag("N");
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				// message_consumer_key|consumer|consumer_destination|destination_type|maximum_retries|retry_interval|maximum_failure|maximum_destination_size

				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("message_consumer_key")) {
						bean.setMessageConsumerKey(new Long(fields[i]));
					}
					else if (columns[i].equals("consumer")) {
						bean.setConsumer(fields[i]);
					}
					else if (columns[i].equals("consumer_destination")) {
						bean.setConsumerDestination(fields[i]);
					}
					else if (columns[i].equals("destination_type")) {
						bean.setDestinationType(fields[i]);
					}
					else if (columns[i].equals("maximum_retries")) {
						bean.setMaximumRetries(new Long(fields[i]));
					}
					else if (columns[i].equals("retry_interval")) {
						bean.setRetryInterval(new Long(fields[i]));
					}
					else if (columns[i].equals("maximum_failure")) {
						bean.setMaximumFailure(new Long(fields[i]));
					}
					else if (columns[i].equals("maximum_destination_size")) {
						bean.setMaximumDestinationSize(new Long(fields[i]));
					}
				}
				
				session.save(bean);
			}
			
			db.closeSession();
		}
		catch (Exception e) {
			db.rollbackSession();
			e.printStackTrace();
		}
		finally {
			try {
				bufferedReader.close();
			}
			catch (Exception e) {
			}
		}
	}

}
