package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.UspsStates;

public class UspsStatesLoader implements BeanLoader {

	@Override
	public void loadTable(Database db, String primeDirectory, String tableName) {
		BufferedReader bufferedReader = null;
		try {
			
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
				
				// usps_state_type_key|state_name|state_code

				UspsStates bean = new UspsStates();
				
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("usps_state_type_key")) {
						bean.setUspsStateTypeKey(new Long(fields[i]));
					}
					else if (columns[i].equals("state_name")) {
						bean.setStateName(fields[i]);
					}
					else if (columns[i].equals("state_code")) {
						bean.setStateCode(fields[i]);
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
