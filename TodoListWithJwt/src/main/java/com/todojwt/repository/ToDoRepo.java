package com.todojwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todojwt.entity.Task;


@Repository
public interface ToDoRepo extends JpaRepository<Task, String> {
	
//	@Query("SELECT t FROM Task t WHERE t.user.id = :userId")
	List<Task>findByUser_Id(@Param("userId") String userId);



}
