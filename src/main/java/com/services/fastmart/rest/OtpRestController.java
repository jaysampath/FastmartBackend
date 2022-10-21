package com.services.fastmart.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.fastmart.helpers.EmailActionException;
import com.services.fastmart.helpers.ResponseJson;
import com.services.fastmart.service.EcommerceService;
import com.services.fastmart.service.EmailService;
import com.services.fastmart.service.OtpService;

@RestController
@CrossOrigin
@RequestMapping("/otp")
public class OtpRestController {

	@Autowired
	public OtpService otpService;

	@Autowired
	public EmailService emailService;
	
	

	SimpleDateFormat sdf = new SimpleDateFormat();

	@GetMapping("/generate/{userEmail}")
	public Integer generateOtp(@PathVariable String userEmail) {
		
		

		int otp = otpService.generateOtp(userEmail);
		try {
			emailService.sendMail(userEmail, otp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new EmailActionException("couldn't not send email. " + e.getMessage());
		}
		return 1;
		//return "otp sent";
	}

	@GetMapping("/validate/{email}/{otp}")
	public ResponseJson validateOtp(@PathVariable String email, @PathVariable int otp) {
		int serverOtp = otpService.getOtp(email);
		
		if (serverOtp == otp) {
			otpService.clearOtp(email);
			return new ResponseJson(HttpStatus.ACCEPTED.value(), "otp is valid",
					String.valueOf(sdf.format(System.currentTimeMillis())));
			
		}
		else
			return new ResponseJson(HttpStatus.NOT_ACCEPTABLE.value(), "invalid otp. Please try again",
					String.valueOf(sdf.format(System.currentTimeMillis())));
	}
	
	@GetMapping("/forgot/{email}")
	public int forgotPasswordOtpSend(@PathVariable String email) {
		int otp = otpService.generateOtp(email);
		try {
			emailService.sendForgotPasswordOtpMail(email, otp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new EmailActionException("couldn't not send email. " + e.getMessage());
		}
		return otp;
	}

}
