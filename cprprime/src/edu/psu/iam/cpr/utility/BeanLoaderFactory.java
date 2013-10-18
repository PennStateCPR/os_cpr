package edu.psu.iam.cpr.utility;


public class BeanLoaderFactory {
	
	public BeanLoader getLoaderInterface(String tableName) {
		if (tableName.equals("Affiliations")) { 
			return new AffiliationsLoader();
		}
		else if (tableName.equals("BadPrefixes")) { 
			return new BadPrefixesLoader();
		}
		else if (tableName.equals("CampusCs")) { 
			return new CampusCsLoader();
		}
		else if (tableName.equals("Country")) { 
			return new CountryLoader();
		}
		else if (tableName.equals("CprComponentStatus")) { 
			return new CprComponentStatusLoader();
		}
		else if (tableName.equals("DataTypes")) { 
			return new DataTypesLoader();
		}
		else if (tableName.equals("DateOfBirth")) { 
			return new DateOfBirthLoader();
		}
		else if (tableName.equals("ExtAffiliation")) { 
			return new ExtAffiliationLoader();
		}
		else if (tableName.equals("ExtAffiliationMapping")) { 
			return new ExtAffiliationMappingLoader();
		}
		else if (tableName.equals("ExtAffiliationTypes")) { 
			return new ExtAffiliationTypesLoader();
		}
		else if (tableName.equals("ExternalIap")) { 
			return new ExternalIapLoader();
		}
		else if (tableName.equals("Federation")) { 
			return new FederationLoader();
		}
		else if (tableName.equals("GroupDataTypeAccess")) { 
			return new GroupDataTypeAccessLoader();
		}
		else if (tableName.equals("GroupMembers")) { 
			return new GroupMembersLoader();
		}
		else if (tableName.equals("Iap")) { 
			return new IapLoader();
		}
		else if (tableName.equals("IapExtMapping")) { 
			return new IapExtMappingLoader();
		}
		else if (tableName.equals("IdentifierType")) { 
			return new IdentifierTypeLoader();
		}
		else if (tableName.equals("JavaExceptions")) { 
			return new JavaExceptionsLoader();
		}
		else if (tableName.equals("Names")) { 
			return new NamesLoader();
		}
		else if (tableName.equals("Person")) { 
			return new PersonLoader();
		}
		else if (tableName.equals("PersonIdCard")) { 
			return new PersonIdCardLoader();
		}
		else if (tableName.equals("PersonIdentifier")) { 
			return new PersonIdentifierLoader();
		}
		else if (tableName.equals("PsuDirectory")) { 
			return new PsuDirectoryLoader();
		}
		else if (tableName.equals("PsuId")) { 
			return new PsuIdLoader();
		}
		else if (tableName.equals("PsuIdExceptions")) { 
			return new PsuIdExceptionsLoader();
		}
		else if (tableName.equals("RaAffiliation")) { 
			return new RaAffiliationLoader();
		}
		else if (tableName.equals("RaIapAssign")) { 
			return new RaIapAssignLoader();
		}
		else if (tableName.equals("RaServerPrincipals")) { 
			return new RaServerPrincipalsLoader();
		}
		else if (tableName.equals("RegistrationAuthority")) { 
			return new RegistrationAuthorityLoader();
		}
		else if (tableName.equals("Services")) { 
			return new ServicesLoader();
		}
		else if (tableName.equals("Userid")) { 
			return new UseridLoader();
		}
		else if (tableName.equals("WebService")) { 
			return new WebServiceLoader();
		}
		else if (tableName.equals("ApplicationProperties")) {
			return new ApplicationPropertiesLoader();
		}
		else if (tableName.equals("EmailNotification")) {
			return new EmailNotificationLoader();
		}
		else if (tableName.equals("SecurityQuestions")) {
			return new SecurityQuestionsLoader();
		}
		else if (tableName.equals("UiApplications")) {
			return new UiApplicationsLoader();
		}
		else if (tableName.equals("UiFieldTypes")) {
			return new UiFieldTypesLoader();
		}
		else if (tableName.equals("UiScreenFields")) {
			return new UiScreenFieldsLoader();
		}
		else if (tableName.equals("UiScreens")) {
			return new UiScreensLoader();
		}
		else if (tableName.equals("RaApplicationProperties")) {
			return new RaApplicationPropertiesLoader();
		}
		else if (tableName.equals("RaApplications")) {
			return new RaApplicationsLoader();
		}
		else if (tableName.equals("RaScreenFields")) {
			return new RaScreenFieldsLoader();
		}
		else if (tableName.equals("RaScreens")) {
			return new RaScreensLoader();
		}
		else if (tableName.equals("UspsStates")) {
			return new UspsStatesLoader();
		}
		else if (tableName.equals("UspsStateTypes")) {
			return new UspsStateTypesLoader();
		}
		else if (tableName.equals("WebServiceAccess")) {
			return new WebServiceAccessLoader();
		}
		else if (tableName.equals("ServerPrincipalIp")) {
			return new ServerPrincipalIpLoader();
		}
		else if (tableName.equals("CprAccessGroups")) {
			return new CprAccessGroupsLoader();
		}
		else if (tableName.equals("MessageConsumer")) {
			return new MessageConsumerLoader();
		}
		else if (tableName.equals("MessageConsumerMapping")) {
			return new MessageConsumerMappingLoader();
		}
		else if (tableName.equals("Addresses")) {
			return new AddressesLoader();
		}
		else if (tableName.equals("IrsCountry")) {
			return new IrsCountryLoader();
		}
		else if (tableName.equals("AcademicCollegeCs")) {
			return new AcademicCollegeCsLoader();
		}
		else if (tableName.equals("AcademicDepartmentCs")) {
			return new AcademicDepartmentCsLoader();
		}
		else if (tableName.equals("AppointmentCs")) {
			return new AppointmentCsLoader();
		}
		else if (tableName.equals("CitizenshipCs")) {
			return new CitizenshipCsLoader();
		}
		else if (tableName.equals("EmployeeClassCs")) {
			return new EmployeeClassCsLoader();
		}
		else if (tableName.equals("MajorCs")) {
			return new MajorCsLoader();
		}
		else if (tableName.equals("PayFrequencyCs")) {
			return new PayFrequencyCsLoader();
		}
		else if (tableName.equals("StudentStatusCs")) {
			return new StudentStatusCsLoader();
		}
		else if (tableName.equals("VisaCs")) {
			return new VisaCsLoader();
		}
		else if (tableName.equals("EmployeeStatusCs")) {
			return new EmployeeStatusCsLoader();
		}
		else if (tableName.equals("ChangeNotificationTypes")) {
			return new ChangeNotificationTypesLoader();
		}
		else if (tableName.equals("ChangeNotifications")) {
			return new ChangeNotificationsLoader();
		}
		else if (tableName.equals("Semesters")) {
			return new SemestersLoader();
		}
		return null;
	}

}
