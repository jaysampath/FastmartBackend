package com.services.fastmart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="user")
@ToString
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	@Indexed
	private long userId;
	
	private String userName;
	
	private String userEmail;
	
	private String password;
	
	public User() {
		
	}

	public User(String userName, String userEmail, String password) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
	
	
	

}
