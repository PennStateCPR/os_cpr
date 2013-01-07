package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.RaApplications;

public class RaApplicationsLoader implements BeanLoader {

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
			
//			ra_application_key|ui_application_key|registration_authority_key|suspend_flag|allow_ssn_collection_flag

			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("[|]");
				
				RaApplications bean = new RaApplications();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("ra_application_key")) {
						bean.setRaApplicationKey(new Long(fields[i]));
					}
					else if (columns[i].equals("ui_application_key")) {
						bean.setUiApplicationKey(new Long(fields[i]));
					}
					else if (columns[i].equals("registration_authority_key")) {
						bean.setRegistrationAuthorityKey(new Long(fields[i]));
					}
					else if (columns[i].equals("suspend_flag")) {
						bean.setSuspendFlag(fields[i]);
					}
					else if (columns[i].equals("allow_ssn_collection_flag")) {
						bean.setAllowSsnCollectionFlag(fields[i]);
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
