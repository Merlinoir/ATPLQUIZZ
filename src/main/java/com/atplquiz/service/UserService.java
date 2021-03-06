package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.atplquiz.controller.UserController;
import com.atplquiz.entity.User;

@Service
public class UserService {
	
	private static Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	
	public List<User> findAll(){
	    // REQUETE
	    List<User> users = this.jdbcTemplate.query(
	        "select * from user_table",
	        new RowMapper<User>() {
	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	User user = new User();
	                user.setId(rs.getInt("id_user"));
	                user.setPseudo(rs.getString("pseudo"));
					user.setPassword(rs.getString("password"));
					user.setIsAdmin(rs.getBoolean("isAdmin"));
	                return user;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(User user : users) {
	          log.info("findAll rest request result : "+user.toString());
	        }
	        return users;
	  }
	
	public List<User> findById(@PathVariable String userID){
	    // REQUETE
	    List<User> users = this.jdbcTemplate.query(
	        "select * from user_table where id_user="+userID,
	        new RowMapper<User>() {
	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	User user = new User();
	                user.setId(rs.getInt("id_user"));
	                user.setPseudo(rs.getString("pseudo"));
	                user.setPassword(rs.getString("password"));
					user.setIsAdmin(rs.getBoolean("isAdmin"));
	                return user;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(User user : users) {
	          log.info("findById rest request result : "+user.toString());
	        }
	        return users;
	  }
	
	public User createUser (@RequestBody User user){
		  log.info("Creating user :" + user.toString());
		  final String pseudo = user.getPseudo();
		  final String password = user.getPassword();
			final boolean isAdmin = user.getIsAdmin();

		  this.jdbcTemplate.update("insert into user_table (PSEUDO,PASSWORD,ISADMIN) values (?,?,?)",pseudo,password,isAdmin);
			return user;
		}
	
	public User updateUser(@RequestBody User user){
		  log.info("User before update : "+user.toString());
		  final long userID = user.getId();
		  final String pseudo = user.getPseudo();
		  final String password = user.getPassword();
		  final boolean isAdmin = user.getIsAdmin();

		  this.jdbcTemplate.update("update user_table set PSEUDO=?, PASSWORD=?, ISADMIN=? "+" where ID_USER=? ",pseudo,password,isAdmin,userID);
		  log.info("User after update : "+user.toString());
		  return user;
	  }
	
	public User loginUser(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password){
		
		String sql = "select * from user_table where pseudo=? and password=?";
		
		try{
		 
		 	 User user = jdbcTemplate.queryForObject(sql, new Object[]{login, password}, new RowMapper<User>() {
		            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	User user = new User();
		                user.setId(rs.getLong("id_user"));
		                user.setPseudo(rs.getString("pseudo"));
		                return user;
		            }
		            
		        });
		   System.out.println("userTEst :"+user);
		   return user;
		   }catch (EmptyResultDataAccessException e) {
			   return null;
		   }
		   
	  }
	
	public void deleteUser (@PathVariable long userId) {
		log.info("Deleting user_table record ID " + userId);

		jdbcTemplate.update (
			"DELETE FROM user_table WHERE id_user = ?;",
			new Object[] { userId }
		);
	}

}
