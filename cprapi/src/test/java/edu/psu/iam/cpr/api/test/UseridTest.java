package edu.psu.iam.cpr.api.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

public class UseridTest {

	private static final String REST_HOST = System.getProperty("REST_HOST");
	private static final String URI = "/cprapi/v1/people/person_id:100000/userids";
	
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
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpReq = new HttpPost(REST_HOST + URI);
		String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
		httpReq.setHeader("Authorization", "Basic " + encoding);
		httpReq.setHeader("Accept", "application/json");
		
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();
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


}
