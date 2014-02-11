/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.batch;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

import edu.psu.iam.cpr.core.database.beans.CampusCs;
import edu.psu.iam.cpr.core.database.beans.Student;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicCollege;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicCollegeHist;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicDepartment;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicDeptHist;
import edu.psu.iam.cpr.core.database.beans.StudentHist;
import edu.psu.iam.cpr.core.database.beans.StudentMajor;
import edu.psu.iam.cpr.core.database.beans.StudentMajorHist;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateHelper;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.util.DataQualityService;
import edu.psu.iam.cpr.core.util.Utility;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * This class provides the implementation of the code necessary to process student information from ISIS.
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
public class StudentInfo extends PersonBio {
	 
	/** Contains the rank */
	private static final String RANK_LEVEL = "rank";

	/** Contains the semester code constant */
	private static final String SEMESTER_CODE = "semester_code";

	/** Contains a reference to the old student bean */
	private Student oldStudent = null;
	
	/** Contains a reference to the new student bean */
	private Student newStudent = null;
	
	/** Contains a reference to the old student academic college bean */
	private StudentAcademicCollege oldStudentAcademicCollege = null;
	
	/** Contains a reference to the new student academic college bean */
	private StudentAcademicCollege newStudentAcademicCollege = null;
	
	/** Contains a reference to the old student major bean */
	private StudentMajor oldStudentMajor = null;
	
	/** Contains a reference to the new student major bean */
	private StudentMajor newStudentMajor = null;
	
	/** Contains a reference to the old student academic department bean */
	private StudentAcademicDepartment oldStudentAcademicDepartment = null;
	
	/** Contains a reference to the new student academic department bean */
	private StudentAcademicDepartment newStudentAcademicDepartment = null;
	
	/** Instance of logger */
	private static final Logger LOG = Logger.getLogger(StudentInfo.class);

	/**
	 * Default constructor.
	 */
	public StudentInfo() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param databaseSession contains the hibernate database session.
	 * @param batchDataSource contains the source of the data feed.
	 * @param dataQualityService contains the data quality service reference.
	 */
    public StudentInfo(final StatelessSession databaseSession,
			final BatchDataSource batchDataSource,
			final DataQualityService dataQualityService) {
		super(databaseSession, batchDataSource, dataQualityService);
	}
    
	/**
	 * This method is used to reset the history beans.
	 */
	@Override
	public void resetHistoryBeans() {
		super.resetHistoryBeans();

		oldStudentAcademicCollege = null;
		newStudentAcademicCollege = null;
		oldStudentMajor = null;
		newStudentMajor = null;
		oldStudentAcademicDepartment = null;
		newStudentAcademicDepartment = null;
	}
	
    /**
     * Update or create student information.
     * @param semesterCode contains the semester code.
     * @param campusCode contains the campus code.
     * @param registrationStatus contains the student's registration status.
     * @param academicLevel contains the student's academic level.
     * @param yearTerm contains the student's year termination status.
     * @param graduatingFlag contains the graduating flag.
     * @param semLoaStart contains the LOA start date.
     * @param semLoaEnd contains the LOA end date.
     * @param dormLocation contains the dorm location code.
     * @param classLoad contains the class load.
     * @param studentAidFlag contains the student aid flag
     * @throws CprException will be thrown if there are any CPR related problems.
     */
    public void updateStudent(final String semesterCode, final String campusCode, final String registrationStatus, final String academicLevel,
    		final String yearTerm, final String graduatingFlag, final String semLoaStart, final String semLoaEnd, final String dormLocation,
    		final String classLoad, final String studentAidFlag) throws CprException {
    	
		// do not attempt to update without a semester code.
		if (ValidateHelper.isFieldEmpty(semesterCode)) {
			return;
		}
		
		boolean matchFound = false;
		boolean changeFound = false;
		final Date d = getUpdateDate();
		
		// Perform a query to find the student record for the user based on semester code, there should only ever be one.
		final StatelessSession session = getDatabaseSession();
		final String updatedBy = getBatchDataSource().toString();
		final Query query = session.createQuery("from Student where personId = :person_id and semesterCode = :semester_code");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(SEMESTER_CODE, semesterCode);
		
		// Loop through all of the results.
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			matchFound = true;
		
			final Student bean = (Student) it.next();
			
			// Save the current student to the oldStudent for the history table using copy constructors.
			setOldStudent(new Student(bean));
			setNewStudent(new Student(bean));

			// If a campus code was specified, we need to obtain its key for storage in the database.
			if (! ValidateHelper.isFieldEmpty(campusCode)) {
				try {
					final CampusCs campusCsBean = getCampusBean(campusCode);
					if (! Utility.areLongFieldsEqual(bean.getCampusCodeKey(), campusCsBean.getCampusCodeKey())) {
						newStudent.setCampusCodeKey(campusCsBean.getCampusCodeKey());
						changeFound = true;
					}
				}
				catch (CprException e) {
					LOG.warn("Unknown campus code: " + campusCode + " for record " + e);
				}
			}
			
			if (! Utility.areStringFieldsEqual(bean.getAcademicLevel(), academicLevel)) {
				newStudent.setAcademicLevel(academicLevel);
				changeFound = true;
			}
			
			if (! Utility.areStringFieldsEqual(bean.getClassLoad(), classLoad)) {
				newStudent.setClassLoad(classLoad);
				changeFound = true;
			}
			
			if (! Utility.areStringFieldsEqual(bean.getDormLocation(), dormLocation)) {
				newStudent.setDormLocation(dormLocation);
				changeFound = true;
			}
			
			final String tempGraduatingFlag = Validate.isValidYesNo(graduatingFlag);
			if (! Utility.areStringFieldsEqual(bean.getGraduatingFlag(), tempGraduatingFlag)) {
				newStudent.setGraduatingFlag(tempGraduatingFlag);
				changeFound = true;
			}
			
			if (! Utility.areStringFieldsEqual(bean.getRegistrationStatus(), registrationStatus)) {
				newStudent.setRegistrationStatus(registrationStatus);
				changeFound = true;
			}
			
			if (! Utility.areStringFieldsEqual(bean.getSemLoaEnd(), semLoaEnd)) {
				newStudent.setSemLoaEnd(semLoaEnd);
				changeFound = true;
			}
			
			if (! Utility.areStringFieldsEqual(bean.getSemLoaStart(), semLoaStart)) {
				newStudent.setSemLoaStart(semLoaStart);
				changeFound = true;
			}
			
			if (! Utility.areStringFieldsEqual(bean.getYearTerm(), yearTerm)) {
				newStudent.setYearTerm(yearTerm);
				changeFound = true;
			}
			
			final String tempStudentAidFlag = Validate.isValidYesNo(studentAidFlag);
			if (! Utility.areStringFieldsEqual(bean.getStudentAidFlag(), tempStudentAidFlag)) {
				newStudent.setStudentAidFlag(tempStudentAidFlag);
				changeFound = true;
			}
			
			if (changeFound) {
				saveStudentHistory();
				
				newStudent.setStartDate(d);
				
				newStudent.setLastUpdateBy(updatedBy);
				newStudent.setLastUpdateOn(d);
				
				newStudent.setImportFrom(updatedBy);
				newStudent.setImportDate(d);
				
				getDatabaseSession().update(newStudent);
			}
			
			// Student found, but no changes, reset the beans to indicate that we do not need to do messaging.
			else {
				setOldStudent(null);
				setNewStudent(null);
			}
			
		}
		if (! matchFound) {
			addStudent(semesterCode, campusCode, registrationStatus, academicLevel, yearTerm, graduatingFlag,
					semLoaStart, semLoaEnd, dormLocation, classLoad, studentAidFlag);
		}

    }
 
    /**
     * This method is used to add a new student record.
     * @param semesterCode contains the semester code.
     * @param campusCode contains the campus code.
     * @param registrationStatus contains the student's registration status.
     * @param academicLevel contains the student's academic level.
     * @param yearTerm contains the student's year termination status.
     * @param graduatingFlag contains the graduating flag.
     * @param semLoaStart contains the LOA start date.
     * @param semLoaEnd contains the LOA end date.
     * @param dormLocation contains the dorm location code.
     * @param classLoad contains the class load.
     * @param studentAidFlag contains the student aid flag
     * @throws CprException will be thrown if there are any CPR related problems.
     */
    public void addStudent(final String semesterCode, final String campusCode, final String registrationStatus, final String academicLevel,
    		final String yearTerm, final String graduatingFlag, final String semLoaStart, final String semLoaEnd, final String dormLocation,
    		final String classLoad, final String studentAidFlag) throws CprException { 
    	
    	final String updatedBy = getBatchDataSource().toString();
    	final Date d = getUpdateDate();
    	
    	final Student student = new Student();
    	
    	student.setAcademicLevel(academicLevel);
    	
		// If a campus code was specified, we need to obtain its key for storage in the database.
		if (! ValidateHelper.isFieldEmpty(campusCode)) {
			try {
				final CampusCs campusCsBean = getCampusBean(campusCode);
	    		student.setCampusCodeKey(campusCsBean.getCampusCodeKey());
			}
			catch (Exception e) {
				LOG.warn("Unknown campus code: " + campusCode + " for record " + e);
			}
		}
   	
    	student.setClassLoad(classLoad);
    	student.setCreatedBy(updatedBy);
    	student.setCreatedOn(d);
    	student.setDormLocation(dormLocation);
    	student.setGraduatingFlag(Validate.isValidYesNo(graduatingFlag));
    	student.setImportDate(d);
    	student.setImportFrom(updatedBy);
    	student.setLastUpdateBy(updatedBy);
    	student.setLastUpdateOn(d);
    	student.setPersonId(getPersonId());
    	student.setRegistrationStatus(registrationStatus);
    	student.setSemesterCode(semesterCode);
    	student.setSemLoaEnd(semLoaEnd);
    	student.setSemLoaStart(semLoaStart);
    	student.setStartDate(d);
    	student.setStudentAidFlag(Validate.isValidYesNo(studentAidFlag));
    	student.setYearTerm(yearTerm);

    	getDatabaseSession().insert(student);
    	
    	// New student was added, but there was not a previous student record, so we only need to store the new record.
    	setOldStudent(null);
    	setNewStudent(student);
   }
    
    /**
     * This method converts a student record to a student history record
     * @throws CprException
     */
    public void saveStudentHistory() throws CprException {
    	final StudentHist studentHist = new StudentHist();
    	
    	studentHist.setAcademicLevel(oldStudent.getAcademicLevel());
    	studentHist.setCampusCodeKey(oldStudent.getCampusCodeKey());
    	studentHist.setClassLoad(oldStudent.getClassLoad());
    	studentHist.setCreatedBy(oldStudent.getCreatedBy());
    	studentHist.setCreatedOn(oldStudent.getCreatedOn());
    	studentHist.setDormLocation(oldStudent.getDormLocation());
    	studentHist.setEndDate(getUpdateDate());
    	studentHist.setGraduatingFlag(oldStudent.getGraduatingFlag());
    	studentHist.setImportDate(oldStudent.getImportDate());
    	studentHist.setImportFrom(oldStudent.getImportFrom());
    	studentHist.setLastUpdateBy(oldStudent.getLastUpdateBy());
    	studentHist.setLastUpdateOn(oldStudent.getLastUpdateOn());
    	studentHist.setPersonId(oldStudent.getPersonId());
    	studentHist.setRegistrationStatus(oldStudent.getRegistrationStatus());
    	studentHist.setSemesterCode(oldStudent.getSemesterCode());
    	studentHist.setSemLoaEnd(oldStudent.getSemLoaEnd());
    	studentHist.setSemLoaStart(oldStudent.getSemLoaStart());
    	studentHist.setStartDate(oldStudent.getStartDate());
    	studentHist.setStudentKey(oldStudent.getStudentKey());
    	studentHist.setStudentAidFlag(oldStudent.getStudentAidFlag());
    	
    	getDatabaseSession().insert(studentHist);
    }
    
    /**
     * Update the student's academic college.
     * @param semesterCode contains the semester code. 
     * @param academicCollege contains the academic college code.
     * @param rank contains the rank.
     * @throws CprException will be thrown if there are any cpr related problems.
     */
    public void updateStudentAcademicCollege(final String semesterCode, final String academicCollege, final Long rank) throws CprException {
		// do not attempt to update without mandatory fields.
		if (ValidateHelper.isFieldEmpty(semesterCode) || ValidateHelper.isFieldEmpty(academicCollege)) {
			return;
		}
		boolean matchFound = false;
		boolean changeFound = false;
		final Date d = getUpdateDate();
		
		// Perform a query to find the student record for the user based on semester code, there should only ever be one.
		final StatelessSession session = getDatabaseSession();
		final String updatedBy = getBatchDataSource().toString();
    
		// See if they have a record for this rank
		final Query query = session.createQuery(
				"from StudentAcademicCollege where personId = :person_id and semesterCode = :semester_code and rank = :rank");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(SEMESTER_CODE, semesterCode);
		query.setParameter(RANK_LEVEL, rank);
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			matchFound = true;
			final StudentAcademicCollege bean = (StudentAcademicCollege) it.next();
			
			setOldStudentAcademicCollege(new StudentAcademicCollege(bean));
			setNewStudentAcademicCollege(new StudentAcademicCollege(bean));

			if (! Utility.areStringFieldsEqual(bean.getCollegeCode(), academicCollege)) {
				newStudentAcademicCollege.setCollegeCode(academicCollege);
				changeFound = true;
			}	
			
			if (changeFound) {
				saveStudAcademicCollegeHistory();
								
				newStudentAcademicCollege.setStartDate(d);
				
				newStudentAcademicCollege.setLastUpdateBy(updatedBy);
				newStudentAcademicCollege.setLastUpdateOn(d);
				
				newStudentAcademicCollege.setImportFrom(updatedBy);
				newStudentAcademicCollege.setImportDate(d);
				
				getDatabaseSession().update(newStudentAcademicCollege);
			}
			
			// Reset the beans because no changes were found.
			else {
				setOldStudentAcademicCollege(null);
				setNewStudentAcademicCollege(null);
			}
		}
		
		if ((! matchFound) && (! ValidateHelper.isFieldEmpty(academicCollege))) {
			addStudentAcademicCollege(semesterCode, academicCollege, rank);
		}	
   }
 
    /**
     * Add the student's academic college.
     * @param semesterCode contains the semester code.
     * @param academicCollege contains the academic college code.
     * @param rank contains the rank.
     * @throws CprException will be thrown if there are any CPR related problems.
     */
     public void addStudentAcademicCollege(final String semesterCode, final String academicCollege, final Long rank) throws CprException {
		// do not attempt to update without mandatory fields.
		if (ValidateHelper.isFieldEmpty(semesterCode) || ValidateHelper.isFieldEmpty(academicCollege)) {
			return;
		}

		final StudentAcademicCollege studentAcademicCollege = new StudentAcademicCollege();
		final String updatedBy = getBatchDataSource().toString();
		final Date d = getUpdateDate();
		
		studentAcademicCollege.setCollegeCode(academicCollege);
		studentAcademicCollege.setRank(rank);
		studentAcademicCollege.setSemesterCode(semesterCode);
		studentAcademicCollege.setPersonId(getPersonId());
		studentAcademicCollege.setStartDate(d);
		studentAcademicCollege.setCreatedBy(updatedBy);
		studentAcademicCollege.setCreatedOn(d);
		studentAcademicCollege.setLastUpdateBy(updatedBy);
		studentAcademicCollege.setLastUpdateOn(d);
		studentAcademicCollege.setImportFrom(updatedBy);
		studentAcademicCollege.setImportDate(d);
		
		getDatabaseSession().insert(studentAcademicCollege);
		
		// New record was cut, save it off.
		setOldStudentAcademicCollege(null);
		setNewStudentAcademicCollege(studentAcademicCollege);
	 
    }  
 
    /**
     * This method converts a student academic college record to its history record
     * @throws CprException will be thrown if there are any CPR related problems.
     */
    public void saveStudAcademicCollegeHistory() throws CprException {
    	final StudentAcademicCollegeHist academicCollegeHist = new StudentAcademicCollegeHist();
    	
    	academicCollegeHist.setCollegeCode(oldStudentAcademicCollege.getCollegeCode());
    	academicCollegeHist.setRank(oldStudentAcademicCollege.getRank());
    	academicCollegeHist.setCreatedBy(oldStudentAcademicCollege.getCreatedBy());
    	academicCollegeHist.setCreatedOn(oldStudentAcademicCollege.getCreatedOn());
    	academicCollegeHist.setEndDate(getUpdateDate());
    	academicCollegeHist.setImportDate(oldStudentAcademicCollege.getImportDate());
    	academicCollegeHist.setImportFrom(oldStudentAcademicCollege.getImportFrom());
    	academicCollegeHist.setLastUpdateBy(oldStudentAcademicCollege.getLastUpdateBy());
    	academicCollegeHist.setLastUpdateOn(oldStudentAcademicCollege.getLastUpdateOn());
    	academicCollegeHist.setPersonId(oldStudentAcademicCollege.getPersonId());
    	academicCollegeHist.setSemesterCode(oldStudentAcademicCollege.getSemesterCode());
    	academicCollegeHist.setStartDate(oldStudentAcademicCollege.getStartDate());
    	academicCollegeHist.setStudentAcademicCollegeKey(oldStudentAcademicCollege.getStudentAcademicCollegeKey());

    	getDatabaseSession().insert(academicCollegeHist);
    }
    
    /**
     * Update student's major.
     * @param semesterCode contains the semester code.
     * @param major contains the major code.
     * @param rank contains the rank.
     * @throws CprException will be thrown if there are any CPR related problems.
     */
    public void updateStudentMajor(final String semesterCode, final String major, final Long rank) throws CprException {
 
		// do not attempt to update without mandatory fields.
		if (ValidateHelper.isFieldEmpty(semesterCode) || ValidateHelper.isFieldEmpty(major)) {
			return;
		}
		boolean matchFound = false;
		boolean changeFound = false;
		final Date d = getUpdateDate();
		
		// Perform a query to find the student record for the user based on semester code, there should only ever be one.
		final StatelessSession session = getDatabaseSession();
		final String updatedBy = getBatchDataSource().toString();
    
		// See if they have a record for this rank
		final Query query = session.createQuery("from StudentMajor where personId = :person_id and semesterCode = :semester_code and rank = :rank");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(SEMESTER_CODE, semesterCode);
		query.setParameter(RANK_LEVEL, rank);
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			matchFound = true;
			final StudentMajor bean = (StudentMajor) it.next();
			
			setOldStudentMajor(new StudentMajor(bean));
			setNewStudentMajor(new StudentMajor(bean));

			if (! Utility.areStringFieldsEqual(bean.getMajorCode(), major)) {
				newStudentMajor.setMajorCode(major);
				changeFound = true;
			}	
			
			if (changeFound) {
				saveStudentMajorHistory();
								
				newStudentMajor.setStartDate(d);
				
				newStudentMajor.setLastUpdateBy(updatedBy);
				newStudentMajor.setLastUpdateOn(d);
				
				newStudentMajor.setImportFrom(updatedBy);
				newStudentMajor.setImportDate(d);
				
				getDatabaseSession().update(newStudentMajor);
			}
			
			// Reset the major beans, because no changes were made.
			else {
				setOldStudentMajor(null);
				setNewStudentMajor(null);
			}
		}
		
		if ((! matchFound) && (! ValidateHelper.isFieldEmpty(major))) {
			addStudentMajor(semesterCode, major, rank);
		}	       
 
    }
    
    /**
     * Add the student's major.
     * @param semesterCode contains the semester code.
     * @param major contains the major code.
     * @param rank contains the rank.
     * @throws CprException will be thrown if there are any CPR related problems.
     */
     public void addStudentMajor(final String semesterCode, final String major, final Long rank) throws CprException {
    	 
		// do not attempt to update without mandatory fields.
		if (ValidateHelper.isFieldEmpty(semesterCode) || ValidateHelper.isFieldEmpty(major)) {
			return;
		}

		final StudentMajor studentMajor = new StudentMajor();
		final String updatedBy = getBatchDataSource().toString();
		final Date d = getUpdateDate();
		
		studentMajor.setMajorCode(major);
		studentMajor.setRank(rank);
		studentMajor.setSemesterCode(semesterCode);
		studentMajor.setPersonId(getPersonId());
		studentMajor.setStartDate(d);
		studentMajor.setCreatedBy(updatedBy);
		studentMajor.setCreatedOn(d);
		studentMajor.setLastUpdateBy(updatedBy);
		studentMajor.setLastUpdateOn(d);
		studentMajor.setImportFrom(updatedBy);
		studentMajor.setImportDate(d);
		
		getDatabaseSession().insert(studentMajor);
		
		// New major was added.
		setOldStudentMajor(null);
		setNewStudentMajor(studentMajor);
	 
    }
     /**
      * This method converts a student major record to its history record
      * @throws CprException will be thrown if there are any CPR related problems.
      */
     public void saveStudentMajorHistory() throws CprException {
     	final StudentMajorHist studentMajorHist = new StudentMajorHist();
     	
     	studentMajorHist.setMajorCode(oldStudentMajor.getMajorCode());
     	studentMajorHist.setRank(oldStudentMajor.getRank());
     	studentMajorHist.setCreatedBy(oldStudentMajor.getCreatedBy());
     	studentMajorHist.setCreatedOn(oldStudentMajor.getCreatedOn());
     	studentMajorHist.setEndDate(getUpdateDate());
     	studentMajorHist.setImportDate(oldStudentMajor.getImportDate());
     	studentMajorHist.setImportFrom(oldStudentMajor.getImportFrom());
     	studentMajorHist.setLastUpdateBy(oldStudentMajor.getLastUpdateBy());
     	studentMajorHist.setLastUpdateOn(oldStudentMajor.getLastUpdateOn());
     	studentMajorHist.setPersonId(oldStudentMajor.getPersonId());
     	studentMajorHist.setSemesterCode(oldStudentMajor.getSemesterCode());
     	studentMajorHist.setStartDate(oldStudentMajor.getStartDate());
     	studentMajorHist.setStudentMajorKey(oldStudentMajor.getStudentMajorKey());

     	
     	getDatabaseSession().insert(studentMajorHist);
     }    
 
    /**
     * Update student's academic department.
     * @param semesterCode contains the semester code that is being processed.
     * @param academicDepartment contains the student's academic department code.
     * @param rank contains the rank.
     * @throws CprException will be thrown if there are any CPR related problems.
     */
    public void updateStudentAcademicDepartment(final String semesterCode, final String academicDepartment, final Long rank) throws CprException {
 
		// do not attempt to update without mandatory fields.
		if (ValidateHelper.isFieldEmpty(semesterCode) || ValidateHelper.isFieldEmpty(academicDepartment)) {
			return;
		}
		
		boolean matchFound = false;
		boolean changeFound = false;
		final Date d = getUpdateDate();
		
		// Perform a query to find the student record for the user based on semester code, there should only ever be one.
		final StatelessSession session = getDatabaseSession();
		final String updatedBy = getBatchDataSource().toString();
    
		// See if they have a record for this rank
		final Query query = session.createQuery(
				"from StudentAcademicDepartment where personId = :person_id and semesterCode = :semester_code and rank = :rank");
		query.setParameter(PERSON_ID, getPersonId());
		query.setParameter(SEMESTER_CODE, semesterCode);
		query.setParameter(RANK_LEVEL, rank);
		
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			matchFound = true;
			final StudentAcademicDepartment bean = (StudentAcademicDepartment) it.next();
			
			// Save off two copies of the beans using copy constructors.
			setOldStudentAcademicDepartment(new StudentAcademicDepartment(bean));
			setNewStudentAcademicDepartment(new StudentAcademicDepartment(bean));

			if (! Utility.areStringFieldsEqual(bean.getDepartmentCode(), academicDepartment)) {
				newStudentAcademicDepartment.setDepartmentCode(academicDepartment);
				changeFound = true;
			}	
			
			if (changeFound) {
				saveStudentAcademicDepartmentHistory();
								
				newStudentAcademicDepartment.setStartDate(d);
				
				newStudentAcademicDepartment.setLastUpdateBy(updatedBy);
				newStudentAcademicDepartment.setLastUpdateOn(d);
				
				newStudentAcademicDepartment.setImportFrom(updatedBy);
				newStudentAcademicDepartment.setImportDate(d);
				
				getDatabaseSession().update(newStudentAcademicDepartment);
			}
			
			// No changes were major for student academic department.
			else {
				setOldStudentAcademicDepartment(null);
				setNewStudentAcademicDepartment(null);
			}
		}
		
		if ((! matchFound) && (! ValidateHelper.isFieldEmpty(academicDepartment))) {
			addStudentAcademicDepartment(semesterCode, academicDepartment, rank);
		}	       
    }
 
    /**
     * This method is used to add a student academic department record.
     * @param semesterCode contains the semester code associated with the record.
     * @param academicDepartment contains the academic department code.
     * @param rank contains the rank.
     */
	public void addStudentAcademicDepartment(final String semesterCode, final String academicDepartment, final Long rank) {
		
		if (ValidateHelper.isFieldEmpty(semesterCode) || ValidateHelper.isFieldEmpty(academicDepartment)) {
			return;
		}
		
		final StudentAcademicDepartment studentAcademicDepartment = new StudentAcademicDepartment();
		final String updatedBy = getBatchDataSource().toString();
		final Date d = getUpdateDate();
		
		studentAcademicDepartment.setPersonId(getPersonId());
		
		studentAcademicDepartment.setRank(rank);
		studentAcademicDepartment.setSemesterCode(semesterCode);
		studentAcademicDepartment.setDepartmentCode(academicDepartment);
		
		studentAcademicDepartment.setStartDate(d);

		studentAcademicDepartment.setCreatedBy(updatedBy);
		studentAcademicDepartment.setCreatedOn(d);
		
		studentAcademicDepartment.setImportDate(d);
		studentAcademicDepartment.setImportFrom(updatedBy);
		
		studentAcademicDepartment.setLastUpdateBy(updatedBy);
		studentAcademicDepartment.setLastUpdateOn(d);
		
		getDatabaseSession().insert(studentAcademicDepartment);
		
		// Save off the student academic department.
		setOldStudentAcademicDepartment(null);
		setNewStudentAcademicDepartment(studentAcademicDepartment);
	}

	/**
	 * This method is used to save off history.
	 */
	public void saveStudentAcademicDepartmentHistory() {
		
		final StudentAcademicDeptHist studentAcademicDeptHist = new StudentAcademicDeptHist(); 
		
		studentAcademicDeptHist.setCreatedBy(oldStudentAcademicDepartment.getCreatedBy());
		studentAcademicDeptHist.setCreatedOn(oldStudentAcademicDepartment.getCreatedOn());
		studentAcademicDeptHist.setDepartmentCode(oldStudentAcademicDepartment.getDepartmentCode());
		studentAcademicDeptHist.setEndDate(getUpdateDate());
		studentAcademicDeptHist.setImportDate(oldStudentAcademicDepartment.getImportDate());
		studentAcademicDeptHist.setImportFrom(oldStudentAcademicDepartment.getImportFrom());
		studentAcademicDeptHist.setLastUpdateBy(oldStudentAcademicDepartment.getLastUpdateBy());
		studentAcademicDeptHist.setLastUpdateOn(oldStudentAcademicDepartment.getLastUpdateOn());
		studentAcademicDeptHist.setPersonId(oldStudentAcademicDepartment.getPersonId());
		studentAcademicDeptHist.setRank(oldStudentAcademicDepartment.getRank());
		studentAcademicDeptHist.setSemesterCode(oldStudentAcademicDepartment.getSemesterCode());
		studentAcademicDeptHist.setStartDate(oldStudentAcademicDepartment.getStartDate());
		studentAcademicDeptHist.setStudentAcademicDeptKey(oldStudentAcademicDepartment.getStudentAcademicDeptKey());
		
		getDatabaseSession().insert(studentAcademicDeptHist);
	}

	/**
	 * @return the oldStudent
	 */
	public Student getOldStudent() {
		return oldStudent;
	}

	/**
	 * @param oldStudent the oldStudent to set
	 */
	public void setOldStudent(Student oldStudent) {
		this.oldStudent = oldStudent;
	}

	/**
	 * @return the newStudent
	 */
	public Student getNewStudent() {
		return newStudent;
	}

	/**
	 * @param newStudent the newStudent to set
	 */
	public void setNewStudent(Student newStudent) {
		this.newStudent = newStudent;
	}

	/**
	 * @return the oldStudentAcademicCollege
	 */
	public StudentAcademicCollege getOldStudentAcademicCollege() {
		return oldStudentAcademicCollege;
	}

	/**
	 * @param oldStudentAcademicCollege the oldStudentAcademicCollege to set
	 */
	public void setOldStudentAcademicCollege(StudentAcademicCollege oldStudentAcademicCollege) {
		this.oldStudentAcademicCollege = oldStudentAcademicCollege;
	}

	/**
	 * @return the newStudentAcademicCollege
	 */
	public StudentAcademicCollege getNewStudentAcademicCollege() {
		return newStudentAcademicCollege;
	}

	/**
	 * @param newStudentAcademicCollege the newStudentAcademicCollege to set
	 */
	public void setNewStudentAcademicCollege(StudentAcademicCollege newStudentAcademicCollege) {
		this.newStudentAcademicCollege = newStudentAcademicCollege;
	}

	/**
	 * @return the oldStudentmajor
	 */
	public StudentMajor getOldStudentMajor() {
		return oldStudentMajor;
	}

	/**
	 * @param oldStudentMajor the oldStudentMajor to set
	 */
	public void setOldStudentMajor(StudentMajor oldStudentMajor) {
		this.oldStudentMajor = oldStudentMajor;
	}

	/**
	 * @return the newStudentMajor
	 */
	public StudentMajor getNewStudentMajor() {
		return newStudentMajor;
	}

	/**
	 * @param newStudentMajor the newStudentMajor to set
	 */
	public void setNewStudentMajor(StudentMajor newStudentMajor) {
		this.newStudentMajor = newStudentMajor;
	}

	/**
	 * @param oldStudentAcademicDepartment the oldStudentAcademicDepartment to set
	 */
	public void setOldStudentAcademicDepartment(
			StudentAcademicDepartment oldStudentAcademicDepartment) {
		this.oldStudentAcademicDepartment = oldStudentAcademicDepartment;
	}

	/**
	 * @return the oldStudentAcademicDepartment
	 */
	public StudentAcademicDepartment getOldStudentAcademicDepartment() {
		return oldStudentAcademicDepartment;
	}

	/**
	 * @param newStudentAcademicDepartment the newStudentAcademicDepartment to set
	 */
	public void setNewStudentAcademicDepartment(
			StudentAcademicDepartment newStudentAcademicDepartment) {
		this.newStudentAcademicDepartment = newStudentAcademicDepartment;
	}

	/**
	 * @return the newStudentAcademicDepartment
	 */
	public StudentAcademicDepartment getNewStudentAcademicDepartment() {
		return newStudentAcademicDepartment;
	}

}
