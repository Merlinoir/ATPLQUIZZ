package com.atplquiz.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.atplquiz.entity.User;
import com.atplquiz.testUtils.UserUtils;

public class UserServiceHSQLDBTest extends ServiceHSQLDBTest {

	@Autowired
	UserService userService;

	@Test
	public void findAll(){
		//On test qu'il n'y a pas de user en db	
		//GIVEN
		List<User>listeRetrieved = new ArrayList<User>();
		//WHEN
		listeRetrieved = userService.findAll();
		//THEN
		Assert.assertEquals("Il ne doit pas y avoir de user en db",0, listeRetrieved.size());

		//Puis on crée un user et on vérifie sa persistance
		//GIVEN
		List<User>listeToInject = UserUtils.getUserListWithOneAdmin();
		for(User user : listeToInject){
			userService.createUser(user);
		}
		//WHEN
		listeRetrieved = userService.findAll();
		//THEN
		Assert.assertEquals("Il doit y avoir 6 user en db", 6, listeRetrieved.size());

	}

	@Test
	public void findById(){
		//GIVEN
		List<User>listeRetrieved = new ArrayList<User>();
		userService.createUser(UserUtils.getOneUser());
		listeRetrieved = userService.findAll();
		Assert.assertEquals("Il doit y avoir un seul user en db",1, listeRetrieved.size());
		listeRetrieved.clear();
		//WHEN
		listeRetrieved = userService.findById("1L");
		//THEN
		Assert.assertEquals("Le pseudo du user en BDD est pseudo", "pseudo", listeRetrieved.get(0).getPseudo());
	}

	@Test
	public void updateUserTest(){
		//GIVEN
		User userToUpdate = UserUtils.getOneUser();
		Assert.assertEquals("Le pseudo du user doit être pseudo", "pseudo", userToUpdate.getPseudo());
		userToUpdate.setPseudo("newPseudo");
		//WHEN
		User userUpdated = userService.updateUser(userToUpdate);

		//THEN
		Assert.assertEquals("Le pseudo du user doit être newPseudo", "newPseudo", userUpdated.getPseudo());
	}

	@Test
	public void deleteUserTest(){
		//GIVEN
		List<User>listeRetrieved = new ArrayList<User>();
		User userToDelete = UserUtils.getOneUser();
		//WHEN
		userService.createUser(userToDelete);
		listeRetrieved = userService.findAll();
		//THEN
		Assert.assertEquals("Il doit y avoir un seul user en db",1, listeRetrieved.size());

		//WHEN
		userService.deleteUser(userToDelete.getId());
		listeRetrieved = userService.findAll();
		//THEN
		Assert.assertEquals("Il doit plus y avoir de user en db",0, listeRetrieved.size());
	}

	@Test
	public void loginUserTestOK(){
		//GIVEN
		User userToLog = UserUtils.getOneUser();
		userService.createUser(userToLog);
		//WHEN
		User userRetrieved = userService.loginUser(userToLog.getPseudo(), userToLog.getPassword());

		//THEN
		Assert.assertEquals("userRetrieved doit avoir comme pseudo pseudo","pseudo", userRetrieved.getPseudo());
	}

	@Test
	public void loginUserTestKO(){
		//GIVEN
		User userToLog = UserUtils.getOneUser();
		userService.createUser(userToLog);
		userToLog.setPassword("passwordKO");
		//WHEN
		User userRetrieved = userService.loginUser(userToLog.getPseudo(), userToLog.getPassword());

		//THEN
		Assert.assertNull("userRetrieved doit etre null", userRetrieved);
	}
}
