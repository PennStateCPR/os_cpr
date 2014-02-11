package edu.psu.iam.cpr.api.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

public class AddressTest {
	private static final String REST_HOST = System.getProperty("REST_HOST");
	private static final String URI = "/cprapi/v1/people/person_id:100000/addresses";
	
	@Test
	public void _01testGet() throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpReq = new HttpGet(REST_HOST + URI + "?requestedBy=cpruser");
		String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
		httpReq.setHeader("Authorization", "Basic " + encoding);
		httpReq.setHeader("Accept", "application/json");
		HttpResponse response = httpclient.execute(httpReq);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new Exception("Failed test case!");
		}

	}
	
	@Test
	public void _10testAdd() throws Exception {
		deleteAddress("LOCAL_ADDRESS");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpReq = new HttpPost(REST_HOST + URI);
		String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
		httpReq.setHeader("Authorization", "Basic " + encoding);
		httpReq.setHeader("Accept", "application/json");
		
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("addressType", "LOCAL_ADDRESS"));
		nvps.add(new BasicNameValuePair("address1", "112 Hill Street"));
		nvps.add(new BasicNameValuePair("city", "Some City"));
		nvps.add(new BasicNameValuePair("stateOrProvince", "PA"));
		nvps.add(new BasicNameValuePair("postalCode", "11111"));
		nvps.add(new BasicNameValuePair("countryCode", "usa"));
		nvps.add(new BasicNameValuePair("requestedBy", "cpruser"));

		httpReq.setEntity(new UrlEncodedFormEntity(nvps));

		HttpResponse response = httpclient.execute(httpReq);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new Exception("Failed test case!");
		}

	}
	
	@Test
	public void _20testUpdate() throws Exception {
	}
	
	@Test
	public void _30testArchive() throws Exception {
	}

	private void deleteAddress(String addressType) {
		try {
			for (int i = 0; i < 50; ++i) {
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpDelete httpReq = new HttpDelete(REST_HOST + URI + "?requestedBy=cpruser&addressType=" + addressType + "&groupId=" + i);
				String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
				httpReq.setHeader("Authorization", "Basic " + encoding);
				httpReq.setHeader("Accept", "application/json");

				@SuppressWarnings("unused")
				HttpResponse response = httpclient.execute(httpReq);
			}
		}
		catch (Exception e) {

		}
	
	}


}
