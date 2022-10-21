package com.services.fastmart.dao;

import com.services.fastmart.entity.User;
import com.services.fastmart.helpers.LoginInput;
 
public interface UserDao {

	public User newUserRegister(User user);

	public boolean checkUserExists(String userEmail);

	public String isUserAuthenticated(LoginInput loginInput);

	public User getUserByEmail(String email);
	
	public User updateUserPassword(String email,String password);

}
