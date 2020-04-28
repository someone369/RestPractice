package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.BaseClass;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class POST_API_Test extends BaseClass {

	public BaseClass baseClass;
	String sreviceUrl;
	String ApiUrl;
	public String actualUrl;
	public RestClient restClient;
	CloseableHttpResponse responceOfPostRequest;
	ObjectMapper mapper;
	Users users;
	

	@BeforeTest
	public void set() {
		baseClass = new BaseClass();
		sreviceUrl = prop.getProperty("URL");
		ApiUrl = prop.getProperty("apiUrl");
		actualUrl = sreviceUrl + ApiUrl;
	}
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> headeramap = new HashMap<String, String>();
		headeramap.put("content-type", "application/json");
		
		//JSON api
		mapper = new ObjectMapper();
		users = new Users("morpheos", "leader"); //Experted users object
		
		// Object to json file
		mapper.writeValue(new File("src/main/java/com/qa/data/userData.json"), users);
		
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(" Users Json String is : "+usersJsonString);
		
		responceOfPostRequest = restClient.post(actualUrl, usersJsonString, headeramap);
		
		// Retrieving status code
		int statusCode = responceOfPostRequest.getStatusLine().getStatusCode();
		System.out.println("Status code is : "+statusCode);
		Assert.assertEquals(STATUS_RESPONCE_CODE_201, statusCode);
		
		//retrieving Json responce
		String responceString = EntityUtils.toString(responceOfPostRequest.getEntity(), "UTF-8");
		JSONObject jsonobject = new JSONObject(responceString);
		
		System.out.println("Json responce from API in json format is : "+jsonobject);
		System.out.println();
		
		//validate  JSON to java demarshelling
		Users usereResponceObj = mapper.readValue(responceString, Users.class);// actual users object
		System.out.println("Responce actual : "+usereResponceObj);
		
		Assert.assertTrue(users.getName().equals(usereResponceObj.getName()));
		Assert.assertTrue(users.getJob().equals(usereResponceObj.getJob()));
		
		System.out.println(usereResponceObj.getId());
		System.out.println(usereResponceObj.getName());
		System.out.println(usereResponceObj.getJob());
		System.out.println(usereResponceObj.getCreatedAt());
		
	}
}
