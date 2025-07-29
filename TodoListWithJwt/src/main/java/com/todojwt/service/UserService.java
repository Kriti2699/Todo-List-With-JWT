package com.todojwt.service;

import java.util.Optional;

import com.todojwt.entity.User;



public interface UserService {
	
	User create(User u);
	
	Optional<User> getById(String id);
	
	boolean existsByUsername(String username);


}
