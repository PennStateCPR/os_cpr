package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.AddressStaging;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.postprocess.AddressPostProcessor;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;

public class AddressPostProcessorTest {

	private StatelessSession statelessSession = null;
	private Transaction tx = null;
	private AddressPostProcessor addressPostProcessor;
	private static final long personId = 100000L;
	private static AddressStaging isisStaging;
	private static AddressStaging ibisStaging;
	
	private void openDbConnection() {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		addressPostProcessor = new AddressPostProcessor(statelessSession, null);
		tx = statelessSession.beginTransaction();
	}
	
	private void closeDbConnection() {
		tx.commit();
		statelessSession.close();
	}
	
	@Test
	public void _01testPostProcessor() {
		new AddressPostProcessor();
	}
	
	@Test
	public void _02testPostProcessorStateless() {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		new AddressPostProcessor(statelessSession, null);
		statelessSession.close();
	}
	
	@Test
	public void _03testPostProcessorStateful() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		new AddressPostProcessor(session, null);
	}
	
	@Test
	public void _04testGetAndEvaluateAffiliations() {
		openDbConnection();
		addressPostProcessor.getAndEvaluateAffiliations(personId);
		closeDbConnection();
	}
	
	public final void initProcessStudentAddressChange() {

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
			
				// Add student person affiliation
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
			
			// Fetch employee affiliations from database for test personId
			query = session.createQuery("from PersonAffiliation a where a.personId = :personId AND a.affiliationKey IN (:affiliationKeys) AND endDate IS NULL");
			query.setParameterList("affiliationKeys", employeeAffiliationKeyList());
			query.setParameter("personId", personId);
			
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				final PersonAffiliation bean = (PersonAffiliation) it.next();
				bean.setEndDate(new Date());
				
				session.update(bean);
			}
			
			// Add addresses to staging table
			
			isisStaging = new AddressStaging();
			isisStaging.setDataTypeKey(AddressType.LOCAL_ADDRESS.index());
			isisStaging.setAddress1("123 ISIS Lane");
			isisStaging.setCity("State College");
			isisStaging.setState("PA");
			isisStaging.setCountryCodeThree("USA");
			isisStaging.setImportDate(new Date());
			isisStaging.setImportFrom(BatchDataSource.ISIS.toString());
			isisStaging.setPersonId(personId);
			
			session.insert(isisStaging);
			
			ibisStaging = new AddressStaging();
			ibisStaging.setDataTypeKey(AddressType.WORK_ADDRESS.index());
			ibisStaging.setAddress1("456 IBIS Lane");
			ibisStaging.setCity("State College");
			ibisStaging.setState("PA");
			ibisStaging.setCountryCodeThree("USA");
			ibisStaging.setImportDate(new Date());
			ibisStaging.setImportFrom(BatchDataSource.IBIS.toString());
			ibisStaging.setPersonId(personId);
			
			session.insert(ibisStaging);
			
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
			catch (Exception e) {
			}
		}
	}
	
	@Test
	public void _05testProcessAddressChangeStudent() {
		initProcessStudentAddressChange();
		openDbConnection();
		addressPostProcessor.processAddressChange(personId, AddressType.PERMANENT_ADDRESS);
		closeDbConnection();
		if (addressPostProcessor.getNewAddressBean() != null) {
			assertTrue(addressPostProcessor.getNewAddressBean().getAddress1().equals(isisStaging.getAddress1()));
			assertTrue(addressPostProcessor.getNewAddressBean().getCity().equals(isisStaging.getCity()));
			assertTrue(addressPostProcessor.getNewAddressBean().getState().equals(isisStaging.getState()));
		}
	}
	
	@Test
	public void _06testApplyAddressRulesStudent() {
		openDbConnection();
		List<AddressStaging> beanList = new ArrayList<AddressStaging>();
		AddressStaging bean = new AddressStaging();
		bean.setAddress1("222 Student Lane");
		bean.setCity("State College");
		bean.setState("PA");
		bean.setCountryCodeThree("USA");
		bean.setImportDate(new Date());
		bean.setImportFrom(BatchDataSource.ISIS.toString());
		bean.setPersonId(personId);
		beanList.add(bean);
		addressPostProcessor.applyAddressRules(personId, AddressType.PERMANENT_ADDRESS, bean);
		closeDbConnection();
		if (addressPostProcessor.getNewAddressBean() != null) {
			assertTrue(addressPostProcessor.getNewAddressBean().getAddress1().equals(bean.getAddress1()));
			assertTrue(addressPostProcessor.getNewAddressBean().getCity().equals(bean.getCity()));
			assertTrue(addressPostProcessor.getNewAddressBean().getState().equals(bean.getState()));
		}
	}
	
	public final void initProcessEmployeeAddressChange() {

		StatelessSession session = null;
		Transaction trans = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			trans = session.beginTransaction();

			// Add employee person affiliation
			Date currentDate = new Date();
			PersonAffiliation newAffiliation = new PersonAffiliation();
			newAffiliation.setAffiliationKey(AffiliationsType.STAFF.index());
			newAffiliation.setCreatedBy(BatchDataSource.UNIT_TEST.toString());
			newAffiliation.setCreatedOn(currentDate);
			newAffiliation.setExceptionFlag("N");
			newAffiliation.setLastUpdateBy(BatchDataSource.UNIT_TEST.toString());
			newAffiliation.setLastUpdateOn(currentDate);
			newAffiliation.setPersonId(personId);
			newAffiliation.setPrimaryFlag("N");
			newAffiliation.setStartDate(currentDate);
				
			session.insert(newAffiliation);
			
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
			catch (Exception e) {
			}
		}
	}
	
	@Test
	public void _07testProcessAddressChangeEmployee() {
		initProcessEmployeeAddressChange();
		openDbConnection();
		addressPostProcessor.processAddressChange(personId, AddressType.PERMANENT_ADDRESS);
		closeDbConnection();
		if (addressPostProcessor.getNewAddressBean() != null) {
			assertTrue(addressPostProcessor.getNewAddressBean().getAddress1().equals(ibisStaging.getAddress1()));
			assertTrue(addressPostProcessor.getNewAddressBean().getCity().equals(ibisStaging.getCity()));
			assertTrue(addressPostProcessor.getNewAddressBean().getState().equals(ibisStaging.getState()));
		}
	}
	
	@Test
	public void _08testApplyAddressRulesEmployee() {
		openDbConnection();
		List<AddressStaging> beanList = new ArrayList<AddressStaging>();
		AddressStaging bean = new AddressStaging();
		bean.setAddress1("222 Employee Lane");
		bean.setCity("State College");
		bean.setState("PA");
		bean.setCountryCodeThree("USA");
		bean.setImportDate(new Date());
		bean.setImportFrom(BatchDataSource.IBIS.toString());
		bean.setPersonId(personId);
		beanList.add(bean);
		addressPostProcessor.applyAddressRules(personId,AddressType.PERMANENT_ADDRESS, bean);
		closeDbConnection();
		if (addressPostProcessor.getNewAddressBean() != null) {
			assertTrue(addressPostProcessor.getNewAddressBean().getAddress1().equals(bean.getAddress1()));
			assertTrue(addressPostProcessor.getNewAddressBean().getCity().equals(bean.getCity()));
			assertTrue(addressPostProcessor.getNewAddressBean().getState().equals(bean.getState()));
		}
	}
	
	@Test
	public void _09testSetPermanentAddress() {
		openDbConnection();
		AddressStaging bean = new AddressStaging();
		bean.setAddress1("789 Permanent Lane");
		bean.setCity("State College");
		bean.setState("PA");
		bean.setCountryCodeThree("USA");
		bean.setImportDate(new Date());
		bean.setImportFrom(BatchDataSource.ISIS.toString());
		bean.setPersonId(personId);	
		addressPostProcessor.setChangedAddress(personId, AddressType.PERMANENT_ADDRESS, bean);
		closeDbConnection();
		if (addressPostProcessor.getNewAddressBean() != null) {
			assertTrue(addressPostProcessor.getNewAddressBean().getAddress1().equals(bean.getAddress1()));
			assertTrue(addressPostProcessor.getNewAddressBean().getCity().equals(bean.getCity()));
			assertTrue(addressPostProcessor.getNewAddressBean().getState().equals(bean.getState()));
		}
	}
	
	@Test
	public void _10testIsEmployee() {
		openDbConnection();
		addressPostProcessor.getAndEvaluateAffiliations(personId);
		closeDbConnection();
		assertNotNull(addressPostProcessor.isEmployee());
	}
	
	@Test
	public void _11testIsStudent() {
		openDbConnection();
		addressPostProcessor.getAndEvaluateAffiliations(personId);
		closeDbConnection();
		assertNotNull(addressPostProcessor.isStudent());
	}
	
	private static List<Long> employeeAffiliationKeyList() {
		
		List<Long> affiliationsList = new ArrayList<Long>();
		
		affiliationsList.add(AffiliationsType.EMERITUS.index());
		affiliationsList.add(AffiliationsType.EMPLOYEE.index());
		affiliationsList.add(AffiliationsType.FACULTY.index());
		affiliationsList.add(AffiliationsType.RETIREE.index());
		affiliationsList.add(AffiliationsType.STAFF.index());
		
		return affiliationsList;
	}

}
