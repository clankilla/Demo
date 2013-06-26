package com.demo;

import java.io.Serializable;

import javax.faces.bean.*;

public class user implements Serializable {
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String name, password;
	
	public int getUserId(){
		return user_id;
	}
	
	public void setUserId(int user_id){
		this.user_id = user_id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		System.out.println(name);
		this.name = name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
