package edu.psu.iam.cpr.utility;


import edu.psu.iam.cpr.core.database.Database;

/**
 * Interface for the generic bean loaders.
 * @author jvuccolo
 *
 */
public interface BeanLoader {
	
	public void loadTable(Database db, String primeDirectory, String tableName);

}
