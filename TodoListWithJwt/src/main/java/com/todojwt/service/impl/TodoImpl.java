package com.todojwt.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todojwt.entity.Task;
import com.todojwt.repository.ToDoRepo;
import com.todojwt.repository.UserRepo;
import com.todojwt.service.ToDoService;



@Service
public class TodoImpl implements ToDoService {

	@Autowired
	private ToDoRepo taskRepo;
	@Autowired
	private UserRepo userrepo;
	
	@Override
	public Task save(Task t) {
//		
//		User user = userrepo.findById(userid)
//	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userid));
//		t.setUser(user);
		return taskRepo.save(t);
	}
	

	@Override
	public Optional<Task> getTaskById(String id) {
	
		return taskRepo.findById(id);
	}
	
	
	@Override
	public List<Task> getAllTask() {
		return taskRepo.findAll();
	}

@Override
	public Task modify(Task t) {
		return taskRepo.save(t);
	}


@Override
public boolean delete(String id) {
	// TODO Auto-generated method stub
	
	if(taskRepo.existsById(id)) {
		taskRepo.deleteById(id);
			return true;
	}
	return false;
}


@Override
public List<Task> getByUserId(String userid) {
//	User user=userrepo.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
//	return taskRepo.findByUser_Id(user);
	return taskRepo.findByUser_Id(userid);
}





//	@Override
//	public Task modify(Map<String, Object> updateTask) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	

}
