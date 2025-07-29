package com.todojwt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.todojwt.entity.Task;



public interface ToDoService {
	
	Task save(Task t);
	
	Optional<Task> getTaskById(String id);
	
	List<Task> getAllTask();
	
//	Task modify(Map<String, Object> updateTask);

	Task modify(Task t);
	
	boolean delete(String t);

	List<Task> getByUserId(String id);
	
	

}
