package com.services.fastmart.rest;

import java.text.SimpleDateFormat;

import com.services.fastmart.commons.UserRole;
import com.services.fastmart.exception.JWTException;
import com.services.fastmart.exception.UserActionException;
import com.services.fastmart.rest.request.SignupRequest;
import com.services.fastmart.rest.request.UpdatePasswordRequest;
import com.services.fastmart.rest.response.SuccessfulLoginResponse;
import com.services.fastmart.service.JwtTokenService;
import com.services.fastmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.services.fastmart.entity.User;
import com.services.fastmart.rest.request.LoginRequest;
import com.services.fastmart.rest.response.ResponseJson;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenService tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	SimpleDateFormat sdf = new SimpleDateFormat();

	@PostMapping("/signup")
	public SuccessfulLoginResponse newUserRegister(@RequestBody SignupRequest signupRequest) {

		String actualPassword = signupRequest.getPassword();
		signupRequest.setPassword(passwordEncoder.encode(actualPassword));

		User newUser = userService.saveNewUser(mapSignupRequestToUser(signupRequest));

		if(newUser==null) {
			throw new UserActionException("Error while registering. Please try again after sometime");
		}

		String token = authenticateAndGetToken(signupRequest.getUserEmail(), actualPassword);

		return new SuccessfulLoginResponse(newUser.getUserEmail(), token, newUser.getUserName(), newUser.getPrettyName());
	}

	private User mapSignupRequestToUser(SignupRequest signupRequest) {
		User user = User.builder().build();
		user.setUserEmail(signupRequest.getUserEmail());
		user.setPassword(signupRequest.getPassword());
		user.setUserName(signupRequest.getUserName());
		user.setPrettyName( StringUtils.hasText(signupRequest.getPrettyName())
				? signupRequest.getPrettyName() : signupRequest.getUserName() );
		user.setRole(UserRole.USER.name());
		return user;
	}

	@GetMapping("/check/{email}")
	public ResponseJson checkExistingUser(@PathVariable String email) {
		boolean check = userService.checkExistingUser(email);
		if (!check) {
			return new ResponseJson(HttpStatus.ACCEPTED.value(), "user does not exist. Allow to register",
					String.valueOf(sdf.format(System.currentTimeMillis())));
		}
		return new ResponseJson(HttpStatus.NOT_ACCEPTABLE.value(), "invalid action. user already exists",
				String.valueOf(sdf.format(System.currentTimeMillis())));
	}

	@GetMapping("/find/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}

	@PostMapping("/login")
	public SuccessfulLoginResponse checkUserIsAuth(@RequestBody LoginRequest loginRequest) {
		User existingUser = userService.getUserByEmail(loginRequest.getUserEmail());

		String token = authenticateAndGetToken(loginRequest.getUserEmail(), loginRequest.getPassword());

		return new SuccessfulLoginResponse(existingUser.getUserEmail(), token, existingUser.getUserName(), existingUser.getPrettyName());
	}

	@GetMapping("/validate/token")
	public SuccessfulLoginResponse validateToken(@RequestParam String accessToken) {
		boolean isValid = tokenProvider.validateToken(accessToken);
		if (!isValid) {
			throw new JWTException("Invalid token or token expired. Please login in");
		}
		String email = tokenProvider.getUserEmailFromJWT(accessToken);

		User user = userService.getUserByEmail(email);
		return new SuccessfulLoginResponse(user.getUserEmail(), accessToken, user.getUserName(), user.getPrettyName());
	}
	
	@PostMapping("/update/password")
	public ResponseEntity updateUserPasswordAfterForgot(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		User existingUser = userService.getUserByEmail(updatePasswordRequest.getUserEmail());
		userService.updateUserPassword(updatePasswordRequest.getUserEmail(), passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
		String token = authenticateAndGetToken(existingUser.getUserEmail(), updatePasswordRequest.getNewPassword());
		return ResponseEntity.ok().build();
	}

	private String authenticateAndGetToken(String email, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password));
		return tokenProvider.generateToken(authentication);
	}

}
