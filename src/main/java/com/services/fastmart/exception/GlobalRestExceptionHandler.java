package com.services.fastmart.exception;

import java.text.SimpleDateFormat;

import com.services.fastmart.rest.response.ResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalRestExceptionHandler {
	
	SimpleDateFormat sdf = new SimpleDateFormat();
	
	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(UserActionException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.NOT_FOUND.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
				);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(OrderActionException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.NOT_ACCEPTABLE.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
				);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(BadCredentialsException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.NOT_ACCEPTABLE.value(),
				"username and password combination is incorrect",
				String.valueOf(sdf.format(System.currentTimeMillis()))
		);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.NOT_ACCEPTABLE);
	}

	
	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(Exception exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
				);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(CartActionException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.NOT_ACCEPTABLE.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
				);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(ItemActionException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.NOT_ACCEPTABLE.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
				);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(OtpActionException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.NOT_ACCEPTABLE.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
				);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(EmailActionException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.NOT_ACCEPTABLE.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
				);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseJson> handleException(JWTException exc){
		ResponseJson response = new ResponseJson(
				HttpStatus.UNAUTHORIZED.value(),
				exc.getMessage(),
				String.valueOf(sdf.format(System.currentTimeMillis()))
		);
		return new ResponseEntity<ResponseJson>(response,HttpStatus.UNAUTHORIZED);
	}

}
