package com.todojwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Emailservice {
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmail(String toEmail,String otp) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("OTP");
		message.setText("Your OTP is: " + otp);
		
		try {
			emailSender.send(message);
		    System.out.println("OTP sent successfully to " + toEmail);

		}
		catch(Exception e) {
		    System.err.println("Failed to send email: " + e.getMessage());

			
		}
		
	}


}
