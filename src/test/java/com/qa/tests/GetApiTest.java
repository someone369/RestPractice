package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.client.RestClient;

public class GetApiTest extends BaseClass {

	BaseClass baseclass;
	String Url;
	String apiUrl;
	String actualUrl;
	RestClient restclient;

	@BeforeTest
	public void setup() {
		baseclass = new BaseClass();
		Url = prop.getProperty("URL");
		apiUrl = prop.getProperty("apiUrlafterUrl");
		actualUrl = Url + apiUrl;
		System.out.println("Actual Url is : "+actualUrl);
	}

	@Test
	public void gettest() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		restclient.get(actualUrl);
	}

}
