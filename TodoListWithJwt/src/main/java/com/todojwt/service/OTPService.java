package com.todojwt.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OTPService {
	public static String generateOtp(int length) {
		
		String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        
        
        for(int i=0;i<length;i++) {
        	otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
		return otp.toString();
		
	}
	
	public boolean isOtpExpired(long otpGenerationTime) {
		
		long currentTime=System.currentTimeMillis();
		boolean diff=(currentTime-otpGenerationTime)>2*60*1000;
		return diff;
		
	}

}
