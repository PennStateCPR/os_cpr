package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.ApplicationProperties;

public class ApplicationPropertiesLoader implements BeanLoader {

	@Override
	public void loadTable(Database db, String primeDirectory, String tableName) {
		// TODO Auto-generated method stub


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
			
			// ui_application_key|key_name|key_value|active_flag
			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("[|]");
				
				ApplicationProperties bean = new ApplicationProperties();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("ui_application_key")) {
						bean.setUiApplicationKey(new Long(fields[i]));
					}
					else if (columns[i].equals("key_name")) {
						bean.setKeyName(fields[i]);
					}
					else if (columns[i].equals("key_value")) {
						bean.setKeyValue(fields[i]);
					}
					else if (columns[i].equals("active_flag")) {
						bean.setActiveFlag(fields[i]);
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
