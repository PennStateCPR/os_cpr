/* SVN FILE: $Id: IrsCountryLoader.java 8016 2013-09-11 15:52:46Z emh1 $ */
package edu.psu.iam.cpr.utility;

/**
 * This class implements ...
 * 
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 United States License. To view a copy
 * of this license, visit [http://creativecommons.org/licenses/by-nc-nd/3.0/us/]
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain
 * View, California, 94041, USA.
 *
 * @package put the correct package name here.
 * @author $Author: emh1 $
 * @version $Rev: 8016 $
 * @lastrevision $Date: 2013-09-11 11:52:46 -0400 (Wed, 11 Sep 2013) $
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.IrsCountry;

public class IrsCountryLoader implements BeanLoader {
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
				
				IrsCountry bean = new IrsCountry();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				// country_key|irs_country_code|start_date|end_date

				for (int i = 0; i < columns.length; ++i) {	
					if (columns[i].equals("country_key")) {
						bean.setCountryKey(new Long(fields[i]));
					}
					else if (columns[i].equals("irs_country_code")) {
						bean.setIrsCountryCode(fields[i]);
					}
					else if (columns[i].equals("start_date")) {
						if (fields[i].equals("NULL")) {
							bean.setStartDate(null);
						}
						else {
							DateFormat formatter = new SimpleDateFormat("MMddyyyy");
							bean.setStartDate((Date)formatter.parse(fields[i]));
						}	
					}
					else if (columns[i].equals("end_date")) {
						if (fields[i].equals("NULL")) {
							bean.setEndDate(null);
						}
						else {
							DateFormat formatter = new SimpleDateFormat("MMddyyyy");
							bean.setEndDate((Date) formatter.parse(fields[i]));
						}	
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
