package com.atplquiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.atplquiz.entity.User;
import com.atplquiz.service.UserService;
import com.atplquiz.testUtils.UserUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@Mock
	UserService mockUserService;
	
	@InjectMocks
	UserController userController;
	
	@Test
	public void testFindAll(){
		//GIVEN
		List<User> userList = UserUtils.getUserListWithOneAdmin();
		Mockito.when(mockUserService.findAll()).thenReturn(userList);
		
		//WHEN
		List<User> retrievedList = userController.findAll();
		
		//THEN
		Assert.assertEquals("La liste doit contenir 6 User", 6,retrievedList.size());		
	}
	
	@Test
	public void testFindById(){
		//GIVEN
		List<User> returnedUserList = new ArrayList<User>();
		User user = UserUtils.getOneUser();
		returnedUserList.add(user);
		Mockito.when(mockUserService.findById(Mockito.anyString())).thenReturn(returnedUserList);
		//WHEN
		List<User> retrievedList = userController.findById("1");
		//THEN
		Assert.assertNotNull(retrievedList);
		Assert.assertEquals("La liste ne doit contenir qu'un seul User", 1, retrievedList.size());
	}
	
	@Test
	public void testCreateUser(){
		//GIVEN
		User user = UserUtils.getOneUser();
		Mockito.when(mockUserService.createUser(user)).thenReturn(user);
		
		//WHEN
		User userRetrieved = userController.createUser(user);
		
		//THEN
		Assert.assertEquals("L'id de userRetrieved doit être 1", 1L, userRetrieved.getId());
	}
	
	@Test
	public void testUpdateUser(){
		//GIVEN
		User userToUpdate = UserUtils.getOneUser();
		Assert.assertEquals("l'id de userToUpdate doit être 1L", 1L, userToUpdate.getId());
		Mockito.when(mockUserService.createUser(userToUpdate)).thenReturn(userToUpdate);
	
		userToUpdate.setId(2L);
		Mockito.when(mockUserService.updateUser(userToUpdate)).thenReturn(userToUpdate);
		
		//WHEN
		User userUpdated = userController.updateUser(userToUpdate);
		//THEN
		Assert.assertEquals("L'idUser de userUpdated doit être 2L", 2L, userUpdated.getId());
	}


}
