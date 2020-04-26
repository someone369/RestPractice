package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// GET method
	public void get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpget = new HttpGet(url); // This is the Http get request object
		CloseableHttpResponse responce = httpClient.execute(httpget);
		
		// Retrieving status code
		int ststuscode = responce.getStatusLine().getStatusCode();
		System.out.println("Status code : "+ststuscode);
		
		// Retrieving JSON responce
		String responceString = EntityUtils.toString(responce.getEntity(), "UTF-8");
		
		JSONObject responcejasono = new JSONObject(responceString);
		System.out.println("Responce JSON code from API "+responcejasono);
		
		// Retrieving Headderrs
		Header[] hedderResponce = responce.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : hedderResponce){
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers Responce : "+allHeaders);

		
	}
}
