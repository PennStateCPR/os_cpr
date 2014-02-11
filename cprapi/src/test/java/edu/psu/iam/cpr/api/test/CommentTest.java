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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

public class CommentTest {
	
	private static final String REST_HOST = System.getProperty("REST_HOST");
	private static final String URI = "/cprapi/v1/people/person_id:100000/comments";
	
	@Test
	public void _01testGet() throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpReq = new HttpGet(REST_HOST + URI + "/dummy1?requestedBy=cpruser");
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
		
		deleteComment("dummy2", "user_comment_misuse");
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpReq = new HttpPost(REST_HOST + URI);
		String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
		httpReq.setHeader("Authorization", "Basic " + encoding);
		httpReq.setHeader("Accept", "application/json");
		
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userId", "dummy2"));
		nvps.add(new BasicNameValuePair("userCommentType", "USER_COMMENT_MISUSE"));
		nvps.add(new BasicNameValuePair("comment", "Here is a comment."));
		nvps.add(new BasicNameValuePair("requestedBy", "cpruser"));

		httpReq.setEntity(new UrlEncodedFormEntity(nvps));

		HttpResponse response = httpclient.execute(httpReq);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new Exception("Failed test case!");
		}

	}
	
	@Test
	public void _20testUpdate() throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPut httpReq = new HttpPut(REST_HOST + URI);
		String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
		httpReq.setHeader("Authorization", "Basic " + encoding);
		httpReq.setHeader("Accept", "application/json");
		
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userId", "dummy2"));
		nvps.add(new BasicNameValuePair("userCommentType", "USER_COMMENT_MISUSE"));
		nvps.add(new BasicNameValuePair("comment", "Here is a second comment."));
		nvps.add(new BasicNameValuePair("requestedBy", "cpruser"));

		httpReq.setEntity(new UrlEncodedFormEntity(nvps));

		HttpResponse response = httpclient.execute(httpReq);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new Exception("Failed test case!");
		}

	}
	
	@Test
	public void _30testArchive() throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpReq = new HttpDelete(REST_HOST + URI + "?requestedBy=cpruser&userId=dummy2&userCommentType=USER_COMMENT_MISUSE");
		String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
		httpReq.setHeader("Authorization", "Basic " + encoding);
		httpReq.setHeader("Accept", "application/json");
		
		HttpResponse response = httpclient.execute(httpReq);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new Exception("Failed test case!");
		}

	}

	private void deleteComment(String userid, String commentType) {
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpDelete httpReq = new HttpDelete(REST_HOST + URI + "?requestedBy=cpruser&userId=" + userid + "&userCommentType=" + commentType);
			String encoding = new String(Base64.encodeBase64("cpruser:abcd1234".getBytes()));
			httpReq.setHeader("Authorization", "Basic " + encoding);
			httpReq.setHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(httpReq);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Failed test case!");
			}
		}
		catch (Exception e) {

		}
	
	}


}
