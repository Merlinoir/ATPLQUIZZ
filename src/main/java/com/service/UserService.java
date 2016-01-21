package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;



@Service("userService")
public class UserService implements IUserService{

	private UserRepository userRepository;

	@Override
	public List<User> findAllUsers() {
		
	return userRepository.findAll();
	}
	
}