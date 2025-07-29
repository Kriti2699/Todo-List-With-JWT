package com.todojwt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.todojwt.jwt.JwtUtil;


@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jFilter;
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
						.requestMatchers("/user/**","/login/**").permitAll()
						.requestMatchers("/todolist/add").hasRole("USER")
						.anyRequest().authenticated()
						)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		        .addFilterBefore(jFilter, UsernamePasswordAuthenticationFilter.class)
		        .build();
		
		
	}


}
