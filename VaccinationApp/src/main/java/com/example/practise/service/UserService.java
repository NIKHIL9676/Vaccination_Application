package com.example.practise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.practise.dao.UserRepo;
import com.example.practise.entity.User;

@Component
public class UserService {
	@Autowired
	UserRepo userRepo;

	public boolean saveUser(User user) {
		try {
			userRepo.save(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public User getUserEntityByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User getUserById(int id) {
		return userRepo.findById(id);
	}
	public boolean updateUserEntity(int id,String name , String email,String password) {
		User user=userRepo.findById(id);
		User save=null;
		if(user!=null) {
			user.setName(name);
			user.setEmail(email);
			user.setPassword("{noop}"+	password);
			save = userRepo.save(user);
		}
		if(save!=null) {
			return true;
		}
		return false;
	}
}
