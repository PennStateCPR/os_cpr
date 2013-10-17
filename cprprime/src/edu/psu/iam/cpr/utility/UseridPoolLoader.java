package edu.psu.iam.cpr.utility;

import java.util.Date;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.UseridPool;

public class UseridPoolLoader implements BeanLoader {

	@Override
	public void loadTable(Database db, String primeDirectory, String tableName) {
		
		StatelessSession session = null;
		Transaction tx = null;
		try {
			
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			tx = session.beginTransaction();
			
			Date d = new Date();
			String requestor = "SYSTEM";
			int start = (int) 'a';
			int end = (int) 'z';
			for (int i = start; i <= end; ++i) {
				for (int j = start; j <= end; ++j) {
					for (int k = start; k <= end; ++k) {
						for (int l = 1; l < 2; ++l) {
							StringBuilder sb = new StringBuilder(7);
							sb.append((char) i);
							sb.append((char) j);
							sb.append((char) k);
							String charPart = sb.toString();
							sb.append(l);
							String userid = sb.toString();
							
							UseridPool bean = new UseridPool();
							bean.setCharPart(charPart);
							bean.setCreatedBy(requestor);
							bean.setCreatedOn(d);
							bean.setNumPart(new Long(l));
							bean.setUserid(userid);
							session.insert(bean);
						}
					}
				}
			}
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			try {
				session.close();
			}
			catch (Exception e) {
			}
		}
	}
}
