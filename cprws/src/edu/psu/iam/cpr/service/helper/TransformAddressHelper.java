/* SVN FILE: $Id: TransformAddressHelper.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.helper;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.service.returns.TransformServiceReturn;

/**
 * This class provides helper methods for the transform address service.
 * 
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.service.helper
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public class TransformAddressHelper {

	/** Instance of logger */
	private static final Logger Log4jLogger = Logger.getLogger(TransformAddressHelper.class);

	/**
	 * Constructor.
	 */
	public TransformAddressHelper() {
		super();
	}
	
	/**
	 * This routine provides the implementation for the transform address functionality.  It is called by the main service (TransformAddress)
	 * and other services.
	 * @param db contains an instance of the database connection.
	 * @param serviceCoreReturn contains a service core return class.
	 * @param requestedBy contains the user that requested the transform.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2.
	 * @param address3 contains address line #3.
	 * @param city contains the city.
	 * @param stateOrProvince contains the state or province.
	 * @param postalCode contains the postal code.
	 * @param countryCode contains the country code.
	 * @return will return a TransformServiceReturn object upon success.
	 * @throws SessionError will be thrown if there are any transform problems.
	 */
	public TransformServiceReturn doTransformAddress(
			Database db, 
			ServiceCoreReturn serviceCoreReturn,			
			String requestedBy, 
			String address1, 
			String address2, 
			String address3, 
			String city, 
			String stateOrProvince, 
			String postalCode,
			String countryCode) {
		
//	     // dfIntelliServer configuration 
//		HashMap <String, String> config = new HashMap <String, String> ();
//
//		final Properties props = CprProperties.getInstance().getProperties();
//		config.put("server", props.getProperty(CprPropertyName.CPR_DQ_SERVER.toString()));
//		config.put("transport", props.getProperty(CprPropertyName.CPR_DQ_TRANSPORT.toString()));
//		config.put("log_file", props.getProperty(CprPropertyName.CPR_DQ_LOGFILE.toString()));
//
//		
//	    // connect to the server and initialize the dfClient object 
//	    Base base = new SessionObject(config);
//	    Verify verify = (Verify) base;
//	    
//	    //
//	    // build function call input parameters
//	    //
//	    
//	    // set flag to format return address in proper case
//	    // set flag to guess the country if none provided
//	    int addr_flags = Verify.ADDR_PROPERCASE + Verify.ADDR_GUESS_COUNTRY;
//	    
//	    // convert three character input country code to two character code
//    	String twoCharCountryCode = convertThreeCharCountryCode(countryCode);
//	    
//	    String firm = null;
//	    String lastLine = city + " " + stateOrProvince + " " + postalCode;
//
//	    // function call accepts only two lines for street address 
//	    // combine address2 and address3 input 
//	    String combinedAddress = combineAddressLines(address2, address3);
//	    
//	    HashMap verified = verify.transformAddress(addr_flags, firm, address1, combinedAddress, 
//	    		lastLine, twoCharCountryCode);
//	    
//	    // extract output values from function return
//    	TransformServiceReturn serviceReturn = new TransformServiceReturn (ReturnType.SUCCESS.index(), "SUCCESS");
//    	serviceReturn.setAddress1((String) verified.get("address_line1"));
//    	serviceReturn.setAddress2((String) verified.get("address_line2"));
//    	serviceReturn.setCity((String) verified.get("city"));
//    	serviceReturn.setStateOrProvince((String) verified.get("state"));
//    	serviceReturn.setPostalCode((String) verified.get("postal_code"));
//        twoCharCountryCode = ((String) verified.get("country_code"));
//    	serviceReturn.setCountryName((String) verified.get("country"));
//    	serviceReturn.setDeliverability((String) verified.get("deliverability"));
//    	
//    	// convert two character country code to three character code
//       	String threeCharCountryCode = convertTwoCharCountryCode(twoCharCountryCode);
//    	serviceReturn.setCountryCode(threeCharCountryCode);
//    	
//    	// close verify session
//        base.close();
//		
//	    // log a success!
//		serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
//		
//		Log4jLogger.info("Transform: Status = SUCCESS");
//        
//        return serviceReturn;
		return null;
	}
	
	/**
	 * Concatenate two lines of a street address
	 * @param line1 contains first address line
	 * @param line2 contains second address line
	 * @return combinedLine contains concatenated address lines
	 */	
	private String combineAddressLines (String line1, String line2) {
    	String combinedLine = new String();
    	if (line2 == null || line2.length() == 0){
    		combinedLine = line1;
    	}
    	else {
    		if (line1 == null || line1.length() == 0) {
    			combinedLine = line2;
    		}
    		else {
    			combinedLine = line1 + " " + line2;
    		}
    	}
    	return combinedLine;
	}
	
	/**
	 * Convert two character country code to three character code
	 * @param twoCharCode contains a two character country code.
	 * @return threeCharCode contains a three character country code
	 */	
	private String convertTwoCharCountryCode(String twoCharCode) {
		String threeCharCode = new String();
       	if ("US".equals(twoCharCode)) {
       		threeCharCode = "USA";
    	}
    	else {
    		if ("CA".equals(twoCharCode)) {
    			threeCharCode = "CAN";
    		}
    	}
       	return threeCharCode;
	}
	
	/**
	 * Convert three character country code to two character code
	 * @param threeCharCode contains a three character country code.
	 * @return twoCharCode contains a two character country code
	 */	
	private String convertThreeCharCountryCode(String threeCharCode) {
		String twoCharCode = new String();
    	if ("USA".equals(threeCharCode)) {
    		twoCharCode = "US";
    	}
    	else {
    		if ("CAN".equals(threeCharCode)) {
    			twoCharCode = "CAN";
    		}
    	}
    	return twoCharCode; 
	}
	
	/**
	 * This routine provides an interface for the CPR to execute the Transform Address service functionality.
	 * @param db contains an instance of the database connection.
	 * @param serviceCoreReturn contains a service core return.
	 * @param addressesTable contains the address database table information.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 * @throws GeneralDatabaseException will be thrown if there are any general database errors.
	 */
	public void transformRegistryAddress(Database db, 
			ServiceCoreReturn serviceCoreReturn, 
			AddressesTable addressesTable) throws CprException, GeneralDatabaseException {
		
//		try {
//			TransformServiceReturn transformServiceReturn = null;
//			Addresses bean = addressesTable.getAddressesBean();
//
//			if  ((addressesTable.getCountryThreeCharCode().equals("USA")) || (addressesTable.getCountryThreeCharCode().equals("CAN")) ){
//				if (addressesTable.getCountryThreeCharCode().equals("USA")) {
//					transformServiceReturn = doTransformAddress(db, serviceCoreReturn, bean.getLastUpdateBy(), bean.getAddress1(), 
//							bean.getAddress2(), bean.getAddress3(), bean.getCity(), bean.getState(), bean.getPostalCode(), 
//							addressesTable.getCountryThreeCharCode());
//				}
//				else {
//					transformServiceReturn = doTransformAddress(db, serviceCoreReturn, bean.getLastUpdateBy(), bean.getAddress1(), 
//							bean.getAddress2(), bean.getAddress3(), bean.getCity(), bean.getProvince(), bean.getPostalCode(), 
//							addressesTable.getCountryThreeCharCode());
//				}
//
//				if (transformServiceReturn.getStatusCode() == 0) {
//					if ( transformServiceReturn.getDeliverability().equals("VALID") ){
//						bean.setVerifiedFlag("Y") ;
//					}
//
//					if ( addressesTable.getCountryThreeCharCode().equals("USA") ){
//						if (ValidateAddress.isAddressValid(db, transformServiceReturn.getAddress1(),
//								transformServiceReturn.getAddress2(), null, transformServiceReturn.getCity(), 
//								transformServiceReturn.getStateOrProvince(),transformServiceReturn.getPostalCode(),  true) ) {
//							bean.setAddress1(transformServiceReturn.getAddress1());
//							bean.setAddress2(transformServiceReturn.getAddress2());
//							bean.setCity(transformServiceReturn.getCity());
//							bean.setState(transformServiceReturn.getStateOrProvince());
//							bean.setProvince(null);
//							bean.setPostalCode(transformServiceReturn.getPostalCode());
//							if (bean.getAddress3() !=null)
//								bean.setAddress3(null);
//						}
//						else {
//							throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Address");
//						}
//					}
//					else {
//						if (ValidateAddress.isAddressValid(db, transformServiceReturn.getAddress1(),transformServiceReturn.getAddress2(),
//								null, transformServiceReturn.getCity(), transformServiceReturn.getStateOrProvince(),
//								transformServiceReturn.getPostalCode(), false))  {
//							bean.setAddress1(transformServiceReturn.getAddress1());
//							bean.setAddress2(transformServiceReturn.getAddress2());
//							bean.setCity(transformServiceReturn.getCity());
//							bean.setProvince(transformServiceReturn.getStateOrProvince());
//							bean.setState(null);
//							bean.setPostalCode(transformServiceReturn.getPostalCode());
//							if (bean.getAddress3() !=null)
//								bean.setAddress3(null);
//						}
//						else {
//							throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Address");
//						}
//					}
//				}
//			}
//			else {
//				// don't transform.
//			}
//
//		}
//		catch (SessionError e) {
//		}
	}
}
