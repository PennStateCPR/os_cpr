package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.Userid;

public class UseridLoader implements BeanLoader {

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
				
				Userid bean = new Userid();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				bean.setStartDate(d);
				bean.setEndDate(null);
				
				// person_id|userid|primary_flag|char_part|num_part|psu_id_letter|display_name_flag
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("person_id")) {
						bean.setPersonId(new Long(fields[i]));
					}
					else if (columns[i].equals("userid")) {
						bean.setUserid(fields[i]);
					}
					else if (columns[i].equals("primary_flag")) {
						bean.setPrimaryFlag(fields[i]);
					}
					else if (columns[i].equals("char_part")) {
						bean.setCharPart(fields[i]);
					}
					else if (columns[i].equals("num_part")) {
						bean.setNumPart((fields[i].equals("NULL")) ? null : new Long(fields[i]));
					}
					else if (columns[i].equals("psu_id_letter")) {
						bean.setPsuIdLetter(null);
					}
					else if (columns[i].equals("display_name_flag")) {
						bean.setDisplayNameFlag(fields[i]);
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
