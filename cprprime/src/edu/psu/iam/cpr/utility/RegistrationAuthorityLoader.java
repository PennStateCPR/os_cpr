package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.RegistrationAuthority;

public class RegistrationAuthorityLoader implements BeanLoader {

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
				
				RegistrationAuthority bean = new RegistrationAuthority();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				bean.setStartDate(d);
				bean.setEndDate(null);
				
				// registration_authority|registration_authority_key|ra_contact_key|registration_authority_desc|suspend_flag
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("registration_authority")) {
						bean.setRegistrationAuthority(fields[i]);
					}
					else if (columns[i].equals("registration_authority_key")) {
						bean.setRegistrationAuthorityKey(new Long(fields[i]));
					}
					else if (columns[i].equals("ra_contact_key")) {
						bean.setRaContactKey(new Long(fields[i]));
					}
					else if (columns[i].equals("registration_authority_desc")) {
						bean.setRegistrationAuthorityDesc(fields[i]);
					}
					else if (columns[i].equals("suspend_flag")) {
						bean.setSuspendFlag(fields[i]);
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
