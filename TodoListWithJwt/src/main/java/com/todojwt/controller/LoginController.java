package com.todojwt.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todojwt.entity.User;
import com.todojwt.jwt.JwtUtil;
import com.todojwt.repository.UserRepo;
import com.todojwt.service.Emailservice;
import com.todojwt.service.OTPService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private Emailservice emailservice;
	@Autowired
	private OTPService otpService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepo userRepo;
	
	
	@PostMapping("/send")
	Map<String, Object> sendOtp(@RequestParam String email)
	{
		
		Map<String, Object> response=new HashMap<>();
		try {
			String otp=otpService.generateOtp(6);
			Optional<User> user=Optional.ofNullable(userRepo.findByEmailid(email));
			System.out.println(">>>>>>>>>>>>>>"+userRepo.findByEmailid(email));
			String data="";
			
			if(user.isPresent()) {
				
				User u=user.get();
				u.setEmailid(email);
				u.setOtp(otp);
				u.setOtpGenerationTime(System.currentTimeMillis());
				userRepo.save(u);
				emailservice.sendEmail(email, otp);
				data=otp;
			}
			else {
				data="User not found";
			}
									
			response.put("status", "Success");
			response.put("data", data);
		}
		catch(Exception e){
			response.put("status", "Failure");
			response.put("data", e.fillInStackTrace());
		}
		return response;
	}
	
	
	@PostMapping("/verify-otp")
	Map<String, Object> verifyOtp(@RequestParam String email,@RequestParam String otp){
		
		String data = null;
		Map<String,Object> response=new HashMap<>();
		try {
			Optional<User> user=Optional.ofNullable(userRepo.findByEmailid(email));
			
			User u=user.get();
			if(otpService.isOtpExpired(u.getOtpGenerationTime())) {
				data="OTP expired";
				response.put("status","Failure");
				response.put("data", data);
			}
			if(u.getOtp().equals(otp)) {
				
				String token=jwtUtil.generateToken(email);
				data="OTP verified Successfully";
				response.put("status","Success");
				response.put("data", data);
				response.put("token", token);
				
			}
			else {
				data="Invalid OTP";
				response.put("status","Failure");
				response.put("data", data);
			}

		}
		catch(Exception e) {
			response.put("status","Failure");
			response.put("data", e.fillInStackTrace());
		}
		return response;
		
	}
}
