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
import java.sql.Types;

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

	  @RequestMapping(value="add", method = RequestMethod.POST)
	  public void createPerson(@PathVariable String nom, @PathVariable String prenom, @PathVariable String password){
	    // REQUETE
	    this.jdbcTemplate.update("insert into UTILISATEUR (NOM, PRENOM, PASSWORD) values (?,?,?)", new Object[]{nom, prenom, password}, new Object[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
	  }

}
