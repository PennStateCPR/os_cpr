/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.utility;
 
/**
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.utility
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.ChangeNotificationTypes;

public class ChangeNotificationTypesLoader implements BeanLoader {

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
			
			// Read and process the file.
			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("[|]");
				
				ChangeNotificationTypes bean = new ChangeNotificationTypes();
				
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				// change_notification_types_key|notification_type|notification_desc|active_flag

				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("change_notification_types_key")) {
						bean.setChangeNotificationTypesKey(new Long(fields[i]));
					}
					else if (columns[i].equals("notification_type")) {
						bean.setNotificationType(fields[i]);
					}
					else if (columns[i].equals("notification_desc")) {
						bean.setNotificationDesc(fields[i]);
					}
					else if (columns[i].equals("active_flag")) {
						bean.setActiveFlag(fields[i].substring(0,1));
					}
					
				}
				
				session.save(bean);
			}
			
			db.closeSession();
		}
		catch (Exception e) {
			e.printStackTrace();
			db.rollbackSession();
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
