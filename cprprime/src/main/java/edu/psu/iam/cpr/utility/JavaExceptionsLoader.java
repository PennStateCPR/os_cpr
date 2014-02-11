package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.JavaExceptions;

public class JavaExceptionsLoader implements BeanLoader {

	@Override
	public void loadTable(Database db, String primeDirectory,
			String tableName) {
		BufferedReader bufferedReader = null;
		try {
			Date d = new Date();
			
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
				
				JavaExceptions bean = new JavaExceptions();
				bean.setCreatedOn(d);
				bean.setLastUpdateOn(d);
				
				// java_exception_key|java_exception_enum|java_exception_text

				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("java_exception_key")) {
						bean.setJavaExceptionKey(new Long(fields[i]));
					}
					else if (columns[i].equals("java_exception_enum")) {
						bean.setJavaExceptionEnum(fields[i]);
					}
					else if (columns[i].equals("java_exception_text")) {
						bean.setJavaExceptionText(fields[i]);
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
