package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.ExternalIap;

public class ExternalIapLoader implements BeanLoader {

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
				
				ExternalIap bean = new ExternalIap();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				// external_iap|federation_key|active_flag|external_iap_desc|external_iap_url|external_iap_key

				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("external_iap")) {
						bean.setExternalIap(fields[i]);
					}
					else if (columns[i].equals("federation_key")) {
						bean.setFederationKey(new Long(fields[i]));
					}
					else if (columns[i].equals("active_flag")) {
						bean.setActiveFlag(fields[i]);
					}
					else if (columns[i].equals("external_iap_desc")) {
						bean.setExternalIapDesc(fields[i]);
					}
					else if (columns[i].equals("external_iap_url")) {
						bean.setExternalIapUrl(fields[i]);
					}
					else if (columns[i].equals("external_iap_key")) {
						bean.setExternalIapKey(new Long(fields[i]));
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
