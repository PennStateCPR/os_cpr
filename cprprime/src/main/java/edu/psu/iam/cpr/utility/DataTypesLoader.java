package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.DataTypes;

public class DataTypesLoader implements BeanLoader {

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
				
				DataTypes bean = new DataTypes();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				// data_type_desc|active_flag|data_type_key|parent_data_type_key|can_assign_flag|enum_string|data_type

				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("data_type_desc")) {
						bean.setDataTypeDesc(fields[i]);
					}
					else if (columns[i].equals("active_flag")) {
						bean.setActiveFlag(fields[i]);
					}
					else if (columns[i].equals("data_type_key")) {
						bean.setDataTypeKey(new Long(fields[i]));
					}
					else if (columns[i].equals("parent_data_type_key")) {
						bean.setParentDataTypeKey(new Long(fields[i]));
					}
					else if (columns[i].equals("can_assign_flag")) {
						bean.setCanAssignFlag(fields[i]);
					}
					else if (columns[i].equals("enum_string")) {
						bean.setEnumString(fields[i]);
					}
					else if (columns[i].equals("data_type")) {
						bean.setDataType(fields[i]);
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
