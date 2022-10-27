package com.services.fastmart.dao;

import com.services.fastmart.entity.User;
import com.services.fastmart.rest.request.LoginRequest;
 
public interface UserDao {

	User registerUser(User user);

	boolean checkUserExists(String userEmail);

	User getUserByEmail(String email);
	
	User updateUserPassword(String email,String password);

}
