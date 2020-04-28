package com.qa.data;


// This is POJO class this is equivalent to JSON object
// POJO  -   plain old java object
public class Users {
	String name;
	String job;
	String id;
	String createdAt;

	

	public Users() {

	}

	public Users(String name, String job) {
		this.name = name;
		this.job = job;
	}
	
	//Getters and Setters Methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
