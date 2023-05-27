package com.services.fastmart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection=DatabaseFields.USER_COLLECTION)
@Getter
@Setter
@ToString
@Builder
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
}
