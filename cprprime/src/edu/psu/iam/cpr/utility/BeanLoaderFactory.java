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
		else if (tableName.equals("GroupAccess")) { 
			return new GroupAccessLoader();
		}
		else if (tableName.equals("GroupDataTypeAccess")) { 
			return new GroupDataTypeAccessLoader();
		}
		else if (tableName.equals("GroupMembers")) { 
			return new GroupMembersLoader();
		}
		else if (tableName.equals("IamGroups")) { 
			return new IamGroupsLoader();
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
		else if (tableName.equals("RaGroups")) { 
			return new RaGroupsLoader();
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
		else if (tableName.equals("ServiceProvisioner")) { 
			return new ServiceProvisionerLoader();
		}
		else if (tableName.equals("Services")) { 
			return new ServicesLoader();
		}
		else if (tableName.equals("SpNotification")) { 
			return new SpNotificationLoader();
		}
		else if (tableName.equals("Userid")) { 
			return new UseridLoader();
		}
		else if (tableName.equals("WebService")) { 
			return new WebServiceLoader();
		}
		return null;
	}

}
