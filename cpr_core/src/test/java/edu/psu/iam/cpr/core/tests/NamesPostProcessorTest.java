package edu.psu.iam.cpr.core.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.NamesStaging;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.postprocess.NamesPostProcessor;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;

public class NamesPostProcessorTest {

	private StatelessSession statelessSession = null;
	private Transaction tx = null;
	private NamesPostProcessor namesPostProcessor = new NamesPostProcessor(statelessSession, null);
	private static final long personId = 100000L;
	private static NamesStaging isisStaging;
	
	private void openDbConnection() {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		namesPostProcessor = new NamesPostProcessor(statelessSession, null);
		tx = statelessSession.beginTransaction();
	}
	
	private void closeDbConnection() {
		tx.commit();
		statelessSession.close();
	}
	
	@Test
	public void _01testPostProcessor() {
		new NamesPostProcessor();
	}
	
	@Test
	public void _02testPostProcessorStateless() {
		new NamesPostProcessor(statelessSession, null);
	}
	
	@Test
	public void _03testPostProcessorStateful() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		new NamesPostProcessor(session, null);
	}

	@BeforeClass
	public static final void beforeProcessNameChange() {

		StatelessSession session = null;
		Transaction trans = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			trans = session.beginTransaction();
			
			// Fetch student affiliation from database for test personId
			Query query = session.createQuery("from PersonAffiliation where personId = :personId AND affiliationKey = :affiliationKey");
			query.setParameter("affiliationKey", AffiliationsType.STUDENT.index());
			query.setParameter("personId", personId);
			if (query.list().isEmpty()) {
				// Add student person affiliation, if it doesn't exist
				
				// Create a new affiliation
				Date currentDate = new Date();
				PersonAffiliation newAffiliation = new PersonAffiliation();
				newAffiliation.setAffiliationKey(AffiliationsType.STUDENT.index());
				newAffiliation.setCreatedBy(BatchDataSource.UNIT_TEST.toString());
				newAffiliation.setCreatedOn(currentDate);
				newAffiliation.setExceptionFlag("N");
				newAffiliation.setLastUpdateBy(BatchDataSource.UNIT_TEST.toString());
				newAffiliation.setLastUpdateOn(currentDate);
				newAffiliation.setPersonId(personId);
				newAffiliation.setPrimaryFlag("N");
				newAffiliation.setStartDate(currentDate);
				
				session.insert(newAffiliation);
			}
			
			// Add names to staging table
			isisStaging = new NamesStaging();
			isisStaging.setFirstName("ISIS");
			isisStaging.setImportDate(new Date());
			isisStaging.setImportFrom(BatchDataSource.ISIS.toString());
			isisStaging.setLastName("Tester");
			isisStaging.setMiddleNames("A");
			isisStaging.setPersonId(personId);
			
			session.insert(isisStaging);
			
			NamesStaging ibisStaging = new NamesStaging();
			ibisStaging.setFirstName("IBIS");
			ibisStaging.setImportDate(new Date());
			ibisStaging.setImportFrom(BatchDataSource.IBIS.toString());
			ibisStaging.setLastName("Tester");
			ibisStaging.setMiddleNames("A");
			ibisStaging.setNameMatchCode("~4~Y$$$$$$$&M7$$$$$$");
			ibisStaging.setPersonId(personId);
			
			session.insert(ibisStaging);
			
			NamesStaging hmcStaging = new NamesStaging();
			hmcStaging.setFirstName("HMC");
			hmcStaging.setImportDate(new Date());
			hmcStaging.setImportFrom(BatchDataSource.HMC.toString());
			hmcStaging.setLastName("Tester");
			hmcStaging.setMiddleNames("A");
			hmcStaging.setNameMatchCode("~4~Y$$$$$$$2$$$$$$$$");
			hmcStaging.setPersonId(personId);
			
			session.insert(hmcStaging);
			
			trans.commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (Exception e) { // $codepro.audit.disable emptyCatchClause
			}
		}
	}
	
	@Test
	public void _04testProcessNameChange() {
		openDbConnection();
		namesPostProcessor.processNameChange(personId);
		closeDbConnection();
		
	}

	@Test
	public void _05testGetCurrentSemesterCode() {
		openDbConnection();
		String semesterCode = namesPostProcessor.getCurrentSemesterCode();
		closeDbConnection();
		AssertJUnit.assertNotNull(semesterCode);
	}
	
	@Test
	public void _06testGetAndEvaluateAffiliations() {
		openDbConnection();
		namesPostProcessor.getAndEvaluateAffiliations(personId);
		closeDbConnection();
	}
	
	@Test
	public void _07testSetLegalNameEmployee() {
		openDbConnection();
		NamesStaging bean = new NamesStaging();
		bean.setFirstName("Unit");
		bean.setImportDate(new Date());
		bean.setImportFrom(BatchDataSource.IBIS.toString());
		bean.setLastName("Tester");
		bean.setMiddleNames("A");
		bean.setNameMatchCode("~4~Y$$$$$$$$$$$$$$$$");
		bean.setPersonId(personId);	
		namesPostProcessor.setLegalName(personId, bean);
		closeDbConnection();
		if (namesPostProcessor.getNewNamesBean() != null) {
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getFirstName().equals(bean.getFirstName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getLastName().equals(bean.getLastName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getMiddleNames().equals(bean.getMiddleNames()));
		}
	}
	
	@Test
	public void _08testApplyNameRulesISIS() {
		openDbConnection();
		namesPostProcessor.setStudent(true);
		namesPostProcessor.setStudentAid(true);
		namesPostProcessor.getAllStagedNamesForPerson(personId);
		namesPostProcessor.applyNameRules(personId);
		closeDbConnection();
		AssertJUnit.assertNotNull(namesPostProcessor.getNewNamesBean());
		if (namesPostProcessor.getNewNamesBean() != null && namesPostProcessor.getIsisName() != null) {
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getFirstName().equals(namesPostProcessor.getIsisName().getFirstName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getLastName().equals(namesPostProcessor.getIsisName().getLastName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getMiddleNames().equals(namesPostProcessor.getIsisName().getMiddleNames()));
		}
	}
	
	@Test
	public void _09testSetLegalNameStudent() {
		openDbConnection();
		NamesStaging bean = new NamesStaging();
		bean.setFirstName("Student");
		bean.setImportDate(new Date());
		bean.setImportFrom(BatchDataSource.ISIS.toString());
		bean.setLastName("Tester");
		bean.setMiddleNames("B");
		bean.setPersonId(personId);	
		namesPostProcessor.setLegalName(personId, bean);
		closeDbConnection();
		if (namesPostProcessor.getNewNamesBean() != null) {
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getFirstName().equals(bean.getFirstName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getLastName().equals(bean.getLastName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getMiddleNames().equals(bean.getMiddleNames()));
		}
	}
	
	@Test
	public void _10testApplyNameRulesRecentUpdate() {
		openDbConnection();
		namesPostProcessor.setEmployee(true);
		namesPostProcessor.setEmployeeHMC(false);
		namesPostProcessor.setStudentAid(false);
		namesPostProcessor.getAllStagedNamesForPerson(personId);
		namesPostProcessor.applyNameRules(personId);
		closeDbConnection();
		if (namesPostProcessor.getNewNamesBean() != null && namesPostProcessor.getIsisName() != null) {
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getFirstName().equals(namesPostProcessor.getUpdatedName().getFirstName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getLastName().equals(namesPostProcessor.getUpdatedName().getLastName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getMiddleNames().equals(namesPostProcessor.getUpdatedName().getMiddleNames()));
		}
	}
	
	
	@Test
	public void _11testApplyNameRulesHMC() {
		openDbConnection();
		namesPostProcessor.getAllStagedNamesForPerson(personId);
		namesPostProcessor.setStudentAid(false);
		namesPostProcessor.setEmployeeHMC(true);
		namesPostProcessor.applyNameRules(personId);
		closeDbConnection();
		if (namesPostProcessor.getNewNamesBean() != null && namesPostProcessor.getIbisName() != null) {
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getFirstName().equals(namesPostProcessor.getHmcName().getFirstName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getLastName().equals(namesPostProcessor.getHmcName().getLastName()));
			AssertJUnit.assertTrue(namesPostProcessor.getNewNamesBean().getMiddleNames().equals(namesPostProcessor.getHmcName().getMiddleNames()));
		}
	}
	
	@Test
	public void _12testGetStudentAidFlag() {
		openDbConnection();
		namesPostProcessor.getStudentAidFlag(personId);
		closeDbConnection();
		AssertJUnit.assertNotNull(namesPostProcessor.hasStudentAid());
	}
	
	@Test
	public void _13testisEmployee() {
		openDbConnection();
		namesPostProcessor.getAndEvaluateAffiliations(personId);
		closeDbConnection();
		AssertJUnit.assertNotNull(namesPostProcessor.isEmployee());
	}
	
	@Test
	public void _14testisStudent() {
		openDbConnection();
		namesPostProcessor.getAndEvaluateAffiliations(personId);
		closeDbConnection();
		AssertJUnit.assertNotNull(namesPostProcessor.isStudent());
	}

}
