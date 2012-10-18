package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.GroupDataTypeAccess;

public class GroupDataTypeAccessLoader implements BeanLoader {

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
				
				GroupDataTypeAccess bean = new GroupDataTypeAccess();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);

				// read_flag|data_type_key|archive_flag|iam_group_key|group_data_type_access_key|write_flag

				
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("read_flag")) {
						bean.setReadFlag(fields[i]);
					}
					else if (columns[i].equals("data_type_key")) {
						bean.setDataTypeKey(new Long(fields[i]));
					}
					else if (columns[i].equals("archive_flag")) {
						bean.setArchiveFlag(fields[i]);
					}
					else if (columns[i].equals("iam_group_key")) {
						bean.setIamGroupKey(new Long(fields[i]));
					}
					else if (columns[i].equals("group_data_type_access_key")) {
						bean.setGroupDataTypeAccessKey(new Long(fields[i]));
					}
					else if (columns[i].equals("write_flag")) {
						bean.setWriteFlag(fields[i]);
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
