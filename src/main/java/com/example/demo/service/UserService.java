package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.demo.dto.User;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	@Qualifier("userRepo")
	UserRepo ur;
	
	public User getUserByMail(String email) {
		List<User> userList = ur.findAll();
		if (userList.size()>0) {
			for(User u : userList) {
				if (u.getMail().equals(email)) {
					return u;
				}
			}
		}
		return null;
	}

}
