package com.todojwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todojwt.entity.User;
import com.todojwt.repository.UserRepo;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepo uRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
        User user = uRepo.findByEmailid(email);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        
        String role = "ROLE_" + user.getRole(); 
       
               return new org.springframework.security.core.userdetails.User(
        		user.getEmailid(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
               
        );

	}

}
