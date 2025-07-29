package com.todojwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todojwt.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, String>{
	
	Optional<User> findByUsername(String username);
	
	User findByEmailid(String emailid);
	


}
