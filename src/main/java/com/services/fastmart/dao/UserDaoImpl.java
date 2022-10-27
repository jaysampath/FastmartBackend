package com.services.fastmart.dao;

import com.services.fastmart.entity.DatabaseFields;
import com.services.fastmart.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.services.fastmart.entity.User;
import com.services.fastmart.rest.request.LoginRequest;
import com.services.fastmart.exception.UserActionException;

@Component
public class UserDaoImpl implements UserDao {

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private final UserRepository repository;
	private final MongoTemplate mongoTemplate;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserDaoImpl(UserRepository repository, MongoTemplate mongoTemplate, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.mongoTemplate = mongoTemplate;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User persistedUser = repository.save(user);
		logger.info("New User Registered - {}" , persistedUser);
		return persistedUser;
	}

	@Override
	public boolean checkUserExists(String userEmail) {
		return repository.existsByUserEmail(userEmail);
	}

	@Override
	public boolean isUserAuthenticated(LoginRequest loginRequest) {
		String givenPassword = loginRequest.getPassword();
		User user = repository.findByUserEmail(loginRequest.getUserEmail());
		if(user == null) {
			throw new UserActionException("you are not a registered user, please signup");
		}
		String storedPasswordHashed = user.getPassword();
		return passwordEncoder.matches(givenPassword, storedPasswordHashed);
	}

	@Override
	public User getUserByEmail(String email) {
		return repository.findByUserEmail(email);
	}

	@Override
	public User updateUserPassword(String email,String password) {
		String hashedPassword = passwordEncoder.encode(password);
		Query query = new Query().addCriteria(Criteria.where(DatabaseFields.USER_EMAIL).is(email));
		FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
		findAndModifyOptions.returnNew(true);
		logger.info("Password updated for user - {}",email);
		return mongoTemplate.findAndModify(query, Update.update(DatabaseFields.USER_PASSWORD, hashedPassword), findAndModifyOptions, User.class);
	}

}
