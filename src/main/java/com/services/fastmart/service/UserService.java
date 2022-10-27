package com.services.fastmart.service;

import com.services.fastmart.entity.User;
import com.services.fastmart.rest.request.LoginRequest;

public interface UserService {
    User saveNewUser(User user);

    User getUserByEmail(String userEmail);

    boolean checkExistingUser(String email);

    User updateUserPassword(String email, String password);
}
