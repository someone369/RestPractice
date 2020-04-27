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
		
	// This methods return the HttpGet responce 
		public void get(String url) throws ClientProtocolException, IOException{
			
			CloseableHttpClient closableHttpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse responceOfRetRequest = closableHttpClient.execute(httpget);
			
			// retieveing the status code
			int statusCode = responceOfRetRequest.getStatusLine().getStatusCode();
			System.out.println("");
			System.out.println("Status code is : "+statusCode);
			
			// Retrieving Http json Responce
			String stringResponce = EntityUtils.toString(responceOfRetRequest.getEntity(), "UTF-8");
			
			JSONObject jsonobject = new JSONObject(stringResponce);
			System.out.println("");
			System.out.println("Json responce codw is : "+jsonobject);
			
			// Retrieving Headers
			Header[] Headersarray = responceOfRetRequest.getAllHeaders();
			
			HashMap<String, String> allheaders = new HashMap<String, String>();
			
			for(Header header:Headersarray){
				allheaders.put(header.getName(), header.getValue());
				
			}
			System.out.println("");
			System.out.println("Headers in the Responce is : "+allheaders);
			System.out.println("");
		}
}
