package com.services.fastmart.rest;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.fastmart.entity.User;
import com.services.fastmart.rest.request.LoginRequest;
import com.services.fastmart.rest.response.ResponseJson;
import com.services.fastmart.service.FastmartService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private FastmartService fastmartService;

	SimpleDateFormat sdf = new SimpleDateFormat();

	@PostMapping("/signup")
	public ResponseJson newUserRegister(@RequestBody User user) {
		User newUser = fastmartService.saveNewUser(user);
		if(newUser==null) {
			return new ResponseJson(HttpStatus.NOT_ACCEPTABLE.value(),"Error while registering",String.valueOf(sdf.format(System.currentTimeMillis())));
		}
		return new ResponseJson(HttpStatus.ACCEPTED.value(),"User account created successfully",String.valueOf(sdf.format(System.currentTimeMillis())));
	}

	@GetMapping("/check/{email}")
	public ResponseJson checkExistingUser(@PathVariable String email) {
		String check = fastmartService.checkExistingUser(email);
		if (check.equals("no")) {
			return new ResponseJson(HttpStatus.ACCEPTED.value(), "user does not exist. Allow to register",
					String.valueOf(sdf.format(System.currentTimeMillis())));
		}
		return new ResponseJson(HttpStatus.NOT_ACCEPTABLE.value(), "invalid action. user already exists",
				String.valueOf(sdf.format(System.currentTimeMillis())));
	}

	@GetMapping("/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return fastmartService.getUserByEmail(email);
	}

	@PostMapping("/login")
	public ResponseJson checkUserIsAuth(@RequestBody LoginRequest loginRequest) {
		boolean response = fastmartService.checkUserIsAuth(loginRequest);
		ResponseJson loginResponse;
		if (response) {
			loginResponse = new ResponseJson(HttpStatus.ACCEPTED.value(), "login successful",
					String.valueOf(sdf.format(System.currentTimeMillis())));
			return loginResponse;
		}
		return new ResponseJson(HttpStatus.NOT_ACCEPTABLE.value(), "email and password combination is incorrect",
				String.valueOf(sdf.format(System.currentTimeMillis())));
	}
	
	@GetMapping("/update/{email}/{password}")
	public User updateUserPasswordAfterForgot(@PathVariable String email, @PathVariable String password) {
		User user = fastmartService.updateUserPassword(email, password);
		return user;
	}

}
