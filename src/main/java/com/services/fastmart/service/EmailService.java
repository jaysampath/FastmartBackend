package com.services.fastmart.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.services.fastmart.entity.Order;

@Service
public class EmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	  private final TemplateEngine templateEngine;

	  @Autowired
	  @Qualifier("gmail")
	    private final JavaMailSender javaMailSender;

	    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
	        this.templateEngine = templateEngine;
	        this.javaMailSender = javaMailSender;
	    }

	    public void sendMail(String email,int otp) throws MessagingException {
	        Context context = new Context();
	        context.setVariable("email", email);
	        context.setVariable("otp",otp);

	        String process = templateEngine.process("SendOtp", context);
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	        helper.setSubject("Welcome " + email);
	        helper.setText(process, true);
	        helper.setTo(email);
	        javaMailSender.send(mimeMessage);
	        
	    }
	    
	    public void sendForgotPasswordOtpMail(String email,int otp) throws MessagingException {
	        Context context = new Context();
	        context.setVariable("email", email);
	        context.setVariable("otp",otp);

	        String process = templateEngine.process("forgotPasswordOtp", context);
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	        helper.setSubject("Otp for password reset ");
	        helper.setText(process, true);
	        helper.setTo(email);
	        javaMailSender.send(mimeMessage);
	        
	    }
	    
	    public void sendSuccessfulSignupEmail(String email) throws MessagingException{
	    	Context context = new Context();
	    	context.setVariable("email", email);
	    	
	        String process = templateEngine.process("SuccessSignup", context);
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	        helper.setSubject("Your fastmart account successfully created. ");
	        helper.setText(process, true);
	        helper.setTo(email);
	        javaMailSender.send(mimeMessage);
	    }
	    
	    public void sendOrderPlacedMail(Order order) throws MessagingException {
	    	Context context = new Context();
	    	context.setVariable("email",order.getUserEmail());
	    	context.setVariable("orderAddress", order.getOrderAddress());
	    	context.setVariable("amount", order.getOrderAmount());
	    	context.setVariable("items", order.getOrderItems());
	    	context.setVariable("srcUrl", "https://drive.google.com/uc?export=view&id=");
	    	
	    	String process = templateEngine.process("orderPlaced", context);
	    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	    	helper.setSubject("Hurray! your order successfully placed");
	    	helper.setText(process,true);
	    	helper.setTo(order.getUserEmail());
	    	javaMailSender.send(mimeMessage);
	    	
	    }

}
