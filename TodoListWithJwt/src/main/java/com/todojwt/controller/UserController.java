package com.todojwt.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todojwt.entity.User;
import com.todojwt.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/createuser")
	Map<String,Object>createUser(@RequestBody User u){
		Map<String, Object> response=new HashMap<>();
		
		
	
		u.setCreateon(LocalDateTime.now());
		u.setUpdateon(LocalDateTime.now());
		u.setRole("USER");
		try {
			if(userservice.existsByUsername(u.getUsername())) {
				response.put("status", "Failure");
	            response.put("error", "Username already exists! Please choose another.");
	            return response;
			}
			response.put("status", "Success");
			response.put("data",userservice.create(u));
		}
		catch(Exception e) {
			response.put("status", "Failure");
			response.put("data",e.fillInStackTrace());
		}
		return response;
	}
	
	@GetMapping("/getDetails/{id}")
	Map<String, Object>get(@PathVariable String id){
		Map<String, Object> response=new HashMap<>();
		
		try {
			response.put("status", "Success");
			response.put("data",userservice.getById(id));
		}
		catch(Exception e) {
			response.put("status", "Failure");
			response.put("data",e.fillInStackTrace());
		}
		return response;
		
	}


}
