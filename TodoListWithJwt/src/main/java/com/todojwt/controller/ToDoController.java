package com.todojwt.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todojwt.entity.Task;
import com.todojwt.entity.User;
import com.todojwt.repository.UserRepo;
import com.todojwt.service.ToDoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/todolist")
public class ToDoController {
	
	@Autowired
	private ToDoService todoservice;
	
	@Autowired
	private UserRepo urepo;
	
	@PostMapping("/add")	
	Map<String, Object> add(@RequestBody Map<String, Object> payload)
	{
		String userId =(String) payload.get("user_id");
		System.out.println(userId);
        User user = urepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        


        Task t = new Task();
		
        t.setTitle(payload.get("title").toString());
        t.setTask(payload.get("task").toString());
        t.setCompleted(false);
        t.setCreateon(LocalDateTime.now());
        t.setUpdateon(LocalDateTime.now());
        t.setUser(user);
        
		Map<String, Object> response=new HashMap<>();
		try {
			response.put("status", "Success");
			response.put("data",todoservice.save(t) );
		}
		catch(Exception e) {
			response.put("status", "Failure");
			response.put("data",e.fillInStackTrace());
		}
		return response;
	}
	
	
	@GetMapping("/{id}")
	Map<String, Object> getById(@PathVariable String id){
		
		Map<String, Object> response=new HashMap<>();
		try {
			response.put("status", "Success");
			response.put("data", todoservice.getTaskById(id));
		} catch (Exception e) {
			response.put("status", "Failure");
			response.put("data", e.fillInStackTrace());
		}
		return response;
	}
	
	@PutMapping("/modifyTask/{id}")
	Map<String, Object> modifyTask(@PathVariable String id,@RequestBody Map<String, Object> updateTask){
		Map<String, Object> response=new HashMap<>();
		try {
			Task existingTask=todoservice.getTaskById(id)
					.orElseThrow(()->new RuntimeException("Task not found for id "+id));
			
		
			if(updateTask.containsKey("title")) {
				existingTask.setTitle(updateTask.get("title").toString());
			}
			if (updateTask.containsKey("task")) {
				existingTask.setTask(updateTask.get("task").toString());
			}
			if (updateTask.containsKey("completed")) {
				existingTask.setCompleted(Boolean.parseBoolean((String) updateTask.get("completed")));
			}
			
			existingTask.setUpdateon(LocalDateTime.now());
			
			response.put("status", "Success");
			response.put("data", todoservice.modify(existingTask));
		} catch (Exception e) {
			response.put("status", "Failure");
			response.put("data", e.fillInStackTrace());
		}
		
		return response;
	}
	
	@GetMapping("/alltask")
	Map<String, Object> getAllTask(){
		
		Map<String, Object> response=new HashMap<>();
		try {
			response.put("status", "Success");
			response.put("data", todoservice.getAllTask());
		} catch (Exception e) {
			response.put("status", "Failure");
			response.put("data", e.fillInStackTrace());
		}
		
		return response;
		
	}
	@DeleteMapping("/delete/{id}")
	Map<String,Object> deleteRecord(@PathVariable String id){
		
		String result;
		boolean isDelete=todoservice.delete(id);
		if(isDelete) {
			 result="deleted successfully";
		}
		else {
			 result="error in deletion";
		}
		Map<String,Object> response=new HashMap<>();
		try {
			response.put("status", "Success");
			response.put("data", result);
		} catch (Exception e) {
			response.put("status", "Failure");
			response.put("data", e.fillInStackTrace());
		}		
		return response;
		
	}
	
	@GetMapping("/getAllTask/{userId}")
	Map<String,Object> getAllTaskById(@PathVariable String userId){
		Map<String, Object> response=new HashMap<>();
		try {
			
			response.put("status", "Success");
			response.put("data", todoservice.getByUserId(userId));
		} catch (Exception e) {
			response.put("status", "Failure");
			response.put("data", e.fillInStackTrace());
		}
		return response;
	}
}
