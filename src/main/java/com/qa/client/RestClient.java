package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
		
	// GET methods return the HttpGet responce 
		public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
			
			CloseableHttpClient closableHttpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);	// THis is the GET request
			CloseableHttpResponse responceOfRetRequest = closableHttpClient.execute(httpget);
			
			return responceOfRetRequest;
		}
		
	// POST method is to update the exesisting data
		public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> hashmap) throws ClientProtocolException, IOException{
			
			CloseableHttpClient closableHttpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url); // THis is the POST request
			
			httpPost.setEntity(new StringEntity(entityString));
			
			// for Headers
			for(Map.Entry<String, String> entry : hashmap.entrySet()){
				httpPost.addHeader(entry.getKey(), entry.getValue()); 
			}
			CloseableHttpResponse postResponce = closableHttpClient.execute(httpPost);
			return postResponce;
		}
	
}
