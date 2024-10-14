package com.example.demo.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class User {
	
	public User () {
		
	}
	public User(int id,String b, String c) {
		this.id=id;
		this.mail = b;
		this.password = c;
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id;
	private String mail;
	private String password;
	
	public String getMail() {
		return mail;
	}
	public void setName(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
