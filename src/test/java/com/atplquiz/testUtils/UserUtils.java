package com.atplquiz.testUtils;

import java.util.ArrayList;
import java.util.List;

import com.atplquiz.entity.User;

public class UserUtils {
	
	
	public static User getOneUser(){
		User user = new User();
		user.setId(1L);
		user.setPseudo("pseudo");
		user.setPassword("password");
		user.setIsAdmin(false);
		return user;
	}
	
	public static List<User>getUserListWithoutAdmin(){
		List<User> userList = new ArrayList<User>();
		userList.add(getOneUser());
		userList.add(new User(2L, "user2Pseudo", "user2Password", false));
		userList.add(new User(3L, "user3Pseudo", "user3Password", false));
		userList.add(new User(4L, "user4Pseudo", "user4Password", false));
		userList.add(new User(5L, "user5Pseudo", "user5Password", false));
		userList.add(new User(6L, "user6Pseudo", "user6Password", false));
		return userList;
	}
	
	public static List<User>getUserListWithOneAdmin(){
		List<User> userList = new ArrayList<User>();
		userList.add(getOneUser());
		userList.add(new User(2L, "user2Pseudo", "user2Password", false));
		userList.add(new User(3L, "user3Pseudo", "user3Password", false));
		userList.add(new User(4L, "user4Pseudo", "user4Password", false));
		userList.add(new User(5L, "user5Pseudo", "user5Password", false));
		userList.add(new User(6L, "user6PseudoAdmin", "user6PasswordAdmin", true));
		return userList;
	}

}
