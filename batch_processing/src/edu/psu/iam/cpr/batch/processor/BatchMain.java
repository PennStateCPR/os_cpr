/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.batch.processor;

import edu.psu.iam.cpr.batch.processor.impl.AddressBatchProcessor;
import edu.psu.iam.cpr.batch.processor.impl.EmployeeBatchProcessor;
import edu.psu.iam.cpr.batch.processor.impl.HMCBatchProcessor;
import edu.psu.iam.cpr.batch.processor.impl.NamesBatchProcessor;
import edu.psu.iam.cpr.batch.processor.impl.OasisBatchProcessor;
import edu.psu.iam.cpr.batch.processor.impl.StudentBatchProcessor;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;

/**
 * This class provides the main driver for the batch processors.  It is passed in a arg that determine which batch process to fire
 * off.
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.batch
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public final class BatchMain {

	/** Good exit status value */
	private static final int GOOD_RETURN = 0;

	/** Error exit status value */
	private static final int BAD_RETURN = 1;

	/** Index of the name of the batch job in the command args array */
	private static final int BATCH_JOB_INDEX = 0;

	/** Correct number of command line args */
	private static final int CORRECT_NUM_ARGS = 1;

	/**
	 * Prevent instantiation
	 */
	private BatchMain() {
	}

	/**
	 * Main routine!
	 * @param args command line args.
	 */
	public static void main(final String[] args) {


		// Verify the correct number of command line args.
		if (args.length != CORRECT_NUM_ARGS) {
			System.err.println("FATAL ERROR: Batch program not specified!");
			System.exit(BAD_RETURN);
		}
		else {

			// Process batch based on the job name.
			if (args[BATCH_JOB_INDEX].equalsIgnoreCase(BatchDataSource.OASIS.toString())) {
				new OasisBatchProcessor().processBatch();
			}
			else if (args[BATCH_JOB_INDEX].equalsIgnoreCase(BatchDataSource.HMC.toString())) {
				new HMCBatchProcessor().processBatch();
			}
			else if (args[BATCH_JOB_INDEX].equalsIgnoreCase(BatchDataSource.IBIS.toString())) {
				new EmployeeBatchProcessor().processBatch();
			}
			else if (args[BATCH_JOB_INDEX].equalsIgnoreCase(BatchDataSource.ISIS.toString())) {
				new StudentBatchProcessor().processBatch();
			}
			else if (args[BATCH_JOB_INDEX].equalsIgnoreCase(BatchDataSource.NAME_POSTPROCESS.toString())) {
				new NamesBatchProcessor().processBatch();
			}
			else if (args[BATCH_JOB_INDEX].equalsIgnoreCase(BatchDataSource.ADDRESS_POSTPROCESS.toString())) {
				new AddressBatchProcessor().processBatch();
			}

			// If we get here, a bad value was passed in.
			else {
				System.err.println("FATAL ERROR: Invalid data source [" + args[0] + "] specified!");
				System.exit(BAD_RETURN);
			}
		}
		System.exit(GOOD_RETURN);
	}

}
