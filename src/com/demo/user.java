package com.demo;

import java.io.Serializable;

public class user implements Serializable {
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String name, password, confirm_pass, email, confirm_email;
	
	public int getUserId(){
		return user_id;
	}
	
	public void setUserId(int user_id){
		this.user_id = user_id;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setConfirmEmail(String confirm_email){
		this.confirm_email = confirm_email;
	}
	
	public String getConfirmEmail(){
		return confirm_email;
	}
	
	public void setConfirmPassword(String confirm_pass){
		this.confirm_pass = confirm_pass;
	}
	
	public String getConfirmPassword(){
		return confirm_pass;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
