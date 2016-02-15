package com.atplquiz.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atplquiz.entity.User;
import com.atplquiz.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService us;

	  @RequestMapping(value = "", method = RequestMethod.GET)
	  public List<User> findAll(){
	      return us.findAll();
	  }

	  @RequestMapping(value="/{userID}", method = RequestMethod.GET)
	  public List<User> findById(@PathVariable String userID){
	      return us.findById(userID);
	        
	  }

		@RequestMapping(value = "", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public User createUser (@RequestBody User user){
		  return us.createUser(user);
		}

		@RequestMapping(value = "", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public User updateUser(@RequestBody User user){
		  return us.updateUser(user);
	  }

		@RequestMapping(value="loginUser", method=RequestMethod.GET)
		  @ResponseBody
		  public User loginUser(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password){
			return us.loginUser(login, password);
			   
		  }
		
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	void deleteUser (@PathVariable long userId) {
		us.deleteUser(userId);
	}
}
