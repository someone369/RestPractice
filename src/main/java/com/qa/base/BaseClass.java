package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
	public Properties prop;
	public FileInputStream fin;
	
	public int STATUS_RESPONCE_CODE_200 = 200;
	public int STATUS_RESPONCE_CODE_201 = 201;
	public int STATUS_RESPONCE_CODE_404 = 404;
	public int STATUS_RESPONCE_CODE_401 = 401;
	public int STATUS_RESPONCE_CODE_500 = 500;
	
	public BaseClass(){
		
		try {
			prop = new Properties();
			fin = new FileInputStream(System.getProperty("user.dir")+"//src/main/java/com/qa/config/RestProperties.config");
			System.out.println("Path of the Properties file is : "+System.getProperty("user.dir")+"//src/main/java/com/qa/config/RestProperties.config");
			prop.load(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
