package com.todojwt.entity;

import java.time.LocalDateTime;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.todojwt.helper.Generate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="tb_usersdump")
public class User {

	@Id
	private String id;
	private String username;

	private String emailid;
	private String password;
	
	private LocalDateTime createon;
	private LocalDateTime updateon;
	
	private String role;
	private long otpGenerationTime;
	private String otp;
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
    
	private List<Task> task;
    
    @JsonIgnore
	@Transient
	private Generate generate=new Generate();

	public User() {
		this.id = new Generate().generateId();
	}
//	
//    public User() {
//    	
//    }
	public User(String userid) {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreateon() {
		return createon;
	}
	public void setCreateon(LocalDateTime createon) {
		this.createon = createon;
	}
	public LocalDateTime getUpdateon() {
		return updateon;
	}
	public void setUpdateon(LocalDateTime updateon) {
		this.updateon = updateon;
	}
	public List<Task> getTask() {
		return task;
	}
	public void setTask(List<Task> task) {
		this.task = task;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getOtpGenerationTime() {
		return otpGenerationTime;
	}
	public void setOtpGenerationTime(long otpGenerationTime) {
		this.otpGenerationTime = otpGenerationTime;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Generate getGenerate() {
		return generate;
	}
	public void setGenerate(Generate generate) {
		this.generate = generate;
	}

	
	
}
