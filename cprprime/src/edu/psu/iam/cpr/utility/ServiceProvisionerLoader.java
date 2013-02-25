package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.ServiceProvisioner;

public class ServiceProvisionerLoader implements BeanLoader {

	@Override
	public void loadTable(Database db, String primeDirectory,
			String tableName) {
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
				
				ServiceProvisioner bean = new ServiceProvisioner();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				bean.setStartDate(d);
				bean.setEndDate(null);
				bean.setServiceProvisionerRegDate(new Date());

				// service_provisioner_email|service_provisioner_key|service_provisioner_queue|retry_interval|maximum_failure|service_provisioner|suspend_flag|maximum_retries|maximum_queue_size
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("service_provisioner_email")) {
						bean.setServiceProvisionerEmail((fields[i].equals("NULL")) ? null : fields[i]);
					}
					else if (columns[i].equals("service_provisioner_key")) {
						bean.setServiceProvisionerKey(new Long(fields[i]));
					}
					else if (columns[i].equals("service_provisioner_queue")) {
						bean.setServiceProvisionerQueue(fields[i]);
					}
					else if (columns[i].equals("retry_interval")) {
						bean.setRetryInterval(new Long(fields[i]));
					}
					else if (columns[i].equals("maximum_failure")) {
						bean.setMaximumFailure(new Long(fields[i]));
					}
					else if (columns[i].equals("service_provisioner")) {
						bean.setServiceProvisioner(fields[i]);
					}
					else if (columns[i].equals("suspend_flag")) {
						bean.setSuspendFlag(fields[i]);
					}
					else if (columns[i].equals("maximum_retries")) {
						bean.setMaximumRetries(new Long(fields[i]));
					}
					else if (columns[i].equals("maximum_queue_size")) {
						bean.setMaximumQueueSize(new Long(fields[i]));
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
