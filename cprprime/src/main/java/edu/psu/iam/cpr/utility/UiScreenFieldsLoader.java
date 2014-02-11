package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.UiScreenFields;

public class UiScreenFieldsLoader implements BeanLoader {

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
			
//			ui_screen_name|ui_field_name|ui_field_type|max_length|field_width|field_height|required_flag|display_flag|active_flag

			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("[|]");
				
				UiScreenFields bean = new UiScreenFields();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("ui_screen_name")) {
						bean.setUiScreenName(fields[i]);
					}
					else if (columns[i].equals("ui_field_name")) {
						bean.setUiFieldName(fields[i]);
					}
					else if (columns[i].equals("ui_field_type")) {
						bean.setUiFieldType(fields[i]);
					}
					else if (columns[i].equals("max_length")) {
						bean.setMaxLength(null);
					}
					else if (columns[i].equals("field_width")) {
						bean.setFieldWidth(null);
					}
					else if (columns[i].equals("field_height")) {
						bean.setFieldHeight(null);
					}
					else if (columns[i].equals("required_flag")) {
						bean.setRequiredFlag(fields[i]);
					}
					else if (columns[i].equals("display_flag")) {
						bean.setDisplayFlag(fields[i]);
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
