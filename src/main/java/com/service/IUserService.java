package com.service;

import java.util.List;

import com.entity.User;

public interface IUserService {
	
	List<User> findAllUsers();
}