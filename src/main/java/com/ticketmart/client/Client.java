package com.ticketmart.client;

public class Client {
	
	private String name;
	private String age;
	private String phoneNumber;
	private String email;
	
	public Client(String name, String age, String phoneNumber, String email) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Client() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

}
