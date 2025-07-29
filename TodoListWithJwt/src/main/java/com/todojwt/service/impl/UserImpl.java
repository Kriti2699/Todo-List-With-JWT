package com.todojwt.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todojwt.entity.User;
import com.todojwt.repository.UserRepo;
import com.todojwt.service.UserService;



@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepo repo;
	@Override
	public User create(User u) {
		
		if (u.getUsername() == null || u.getEmailid() == null || u.getPassword() == null) {
	        throw new IllegalArgumentException("Username, Email, and Password are required!");
	    }
		
		Optional<User> existingUser=repo.findByUsername(u.getUsername());
		if (existingUser.isPresent()) {
			throw new IllegalArgumentException("Username already exists! Please choose another.");
		}
		
		return repo.save(u);
	}
	@Override
	public Optional<User> getById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	@Override
	public boolean existsByUsername(String username) {
	    return repo.findByUsername(username).isPresent();
	}


}
