package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.client.RestClient;
import com.qa.util.TestUtill;

public class GET_API_Test extends BaseClass {

	public BaseClass baseClass;
	String sreviceUrl;
	String ApiUrl;
	public String actualUrl;
	public RestClient restClient;
	CloseableHttpResponse responceOfRetRequest;

	@BeforeTest
	public void set() {
		baseClass = new BaseClass();
		sreviceUrl = prop.getProperty("URL");
		ApiUrl = prop.getProperty("apiUrl");
		actualUrl = sreviceUrl + ApiUrl;
	}

	@Test
	public void getResponcestatusCode() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		responceOfRetRequest = restClient.get(actualUrl);

		// retieveing the status code
		int statusCode = responceOfRetRequest.getStatusLine().getStatusCode();
		System.out.println("");
		System.out.println("Status code is : " + statusCode);
		
		Assert.assertEquals(statusCode, STATUS_RESPONCE_CODE_200);

	}
	
	@Test(dependsOnMethods = "getResponcestatusCode")
	public void getJsonResponce() throws ParseException, IOException {
		// Retrieving Http json Responce
		String stringResponce = EntityUtils.toString(responceOfRetRequest.getEntity(), "UTF-8");

		JSONObject jsonobject = new JSONObject(stringResponce);
		System.out.println("");
		System.out.println("Json responce codw is : " + jsonobject);
		
		String perPageValue = TestUtill.getValueByJPath(jsonobject, "/per_page");
		
		String totalValue = TestUtill.getValueByJPath(jsonobject, "/total");
		System.out.println("Total value present in the jason responce is : "+totalValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
	}

	@Test(dependsOnMethods = "getJsonResponce")
	public void getHeaderResponce() {
		// Retrieving Headers
		Header[] Headersarray = responceOfRetRequest.getAllHeaders();

		HashMap<String, String> allheaders = new HashMap<String, String>();

		for (Header header : Headersarray) {
			allheaders.put(header.getName(), header.getValue());

		}
		System.out.println("");
		System.out.println("Headers in the Responce is : " + allheaders);
		System.out.println("");
		
	}

}
