package com.services.fastmart.service;

import com.services.fastmart.dao.UserDao;
import com.services.fastmart.entity.User;
import com.services.fastmart.exception.EmailActionException;
import com.services.fastmart.exception.UserActionException;
import com.services.fastmart.rest.request.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao usersDao;

    @Autowired
    private EmailService emailService;


    @Override
    public User saveNewUser(User user) {

        boolean checkIfExisting = usersDao.checkUserExists(user.getUserEmail());
        if (checkIfExisting) {
            throw new UserActionException("user already exists");
        }
        User newUser = usersDao.registerUser(user);
        try {
            emailService.sendSuccessfulSignupEmail(user.getUserEmail());
        } catch (MessagingException e) {
            throw new EmailActionException(e.getMessage());
        }
        return newUser;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        User user = usersDao.getUserByEmail(userEmail);
        if (user == null) {
            throw new UserActionException("user not found");
        }
        return user;
    }

    @Override
    public boolean checkExistingUser(String email) {
        boolean check = usersDao.checkUserExists(email);
        if (check) {
            throw new UserActionException("user already exists. please login with the same.");
        }
        return false;
    }

    @Override
    public User updateUserPassword(String email, String password) {
        User user = usersDao.updateUserPassword(email, password);
        if (user == null) {
            throw new UserActionException("invalid user email");
        }
        return user;
    }
}
