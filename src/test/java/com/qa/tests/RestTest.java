package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.client.RestClient;

public class RestTest extends BaseClass{
	
	public BaseClass baseClass;
	String sreviceUrl;
	String ApiUrl;
	public String actualUrl;
	public RestClient restClient;
	
	
	@BeforeTest
	public void set(){
		baseClass = new BaseClass();
		 sreviceUrl = prop.getProperty("URL");
		 ApiUrl = prop.getProperty("apiUrl");
		 actualUrl = sreviceUrl + ApiUrl;
	}
	
	@Test
	public void getResponce() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		restClient.get(actualUrl);
	}

}
