package com.restController;

import com.entity.User;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/users")
public class UserRestController {

	private static Log log = LogFactory.getLog(UserRestController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	  @RequestMapping(value="/all", method = RequestMethod.GET)
	  public List<User> findAll(){
	    // REQUETE
	    List<User> users = this.jdbcTemplate.query(
	        "select * from utilisateur",
	        new RowMapper<User>() {
	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	User user = new User();
	                user.setId(rs.getInt("id"));
	                user.setNom(rs.getString("nom"));
	                user.setPrenom(rs.getString("prenom"));
					user.setPassword(rs.getString("password"));
	                return user;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(User user : users) {
	          log.info("findAll rest request result : "+user.toString());
	        }
	        return users;
	  }

	  @RequestMapping(value="/{userID}", method = RequestMethod.GET)
	  public List<User> findById(@PathVariable String userID){
	    // REQUETE
	    List<User> users = this.jdbcTemplate.query(
	        "select * from UTILISATEUR where id="+userID,
	        new RowMapper<User>() {
	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	User user = new User();
	                user.setId(rs.getInt("id"));
	                user.setNom(rs.getString("nom"));
	                user.setPrenom(rs.getString("prenom"));
	                user.setPassword(rs.getString("password"));
	                return user;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(User user : users) {
	          log.info("findById rest request result : "+user.toString());
	        }
	        return users;
	  }

	  @RequestMapping(value="/{userID}/{nom}/{prenom}/{password}", method = RequestMethod.PUT)
	  public void createPerson(@PathVariable String userID, @PathVariable String nom, @PathVariable String prenom, @PathVariable String password){
	    // REQUETE
	    this.jdbcTemplate.update("insert into UTILISATEUR (ID, NOM, PRENOM, PASSWORD, ISADMIN) values (?,?,?,?, FALSE)", userID, nom, prenom, password);
	  }

}
