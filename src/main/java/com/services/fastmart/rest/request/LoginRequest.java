package com.services.fastmart.rest.request;

public class LoginRequest {
	
	private String userEmail;
	
	private String password;
	
	public LoginRequest() {
		
	}

	public LoginRequest(String userEmail, String password) {
		this.userEmail = userEmail;
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
