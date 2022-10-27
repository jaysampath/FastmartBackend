package com.services.fastmart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection=DatabaseFields.USER_COLLECTION)
public class User {
	
	@Id
	private String userId;

	@Field(DatabaseFields.USERNAME)
	private String userName;

	@Field(DatabaseFields.USER_PRETTY_NAME)
	private String prettyName;

	@Field(DatabaseFields.USER_EMAIL)
	private String userEmail;

	@Field(DatabaseFields.USER_PASSWORD)
	private String password;

	@Field(DatabaseFields.USER_ROLE)
	private String role;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPrettyName() {
		return prettyName != null ? prettyName : userName;
	}

	public void setPrettyName(String prettyName) {
		this.prettyName = prettyName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", prettyName='" + prettyName + '\'' +
				", userEmail='" + userEmail + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
