package com.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	UserRepository userRepo;

	@RequestMapping(method=RequestMethod.GET)
	public List<User> list() {
		return userRepo.findAll();
	}


	//    @RequestMapping("/users")
	//    @Resource()
	//	 public List<User> listAllUsers() {
	//		List<User> users = userService.findAllUsers(); 
	//	  return users;
	//	 }
	//	private static Log log = LogFactory.getLog(Application.class);
	//
	//	@Autowired
	//	JdbcTemplate jdbcTemplate;
	//
	//	  @RequestMapping(value="/all", method = RequestMethod.GET)
	//	  public List<User> findAll(){
	//	    // REQUETE
	//	    List<User> users = this.jdbcTemplate.query(
	//	        "select * from USER_TABLE",
	//	        new RowMapper<User>() {
	//	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	//	            	User user = new User();
	//	                user.setId(rs.getInt("id"));
	//	                user.setNom(rs.getString("nom"));
	//	                user.setPrenom(rs.getString("prenom"));
	//									user.setPassword(rs.getString("password"));
	//	                return user;
	//	            }
	//	        });
	//	        // ECRITURE DANS LOG
	//	        for(User user : users) {
	//	          log.info("findAll rest request result : "+user.toString());
	//	        }
	//	        return users;
	//	  }
	//
	//	  @RequestMapping(value="/{userID}", method = RequestMethod.GET)
	//	  public List<User> findById(@PathVariable String userID){
	//	    // REQUETE
	//	    List<User> users = this.jdbcTemplate.query(
	//	        "select * from USER_TABLE where id="+userID,
	//	        new RowMapper<User>() {
	//	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	//	            	User user = new User();
	//	                user.setId(rs.getInt("id"));
	//	                user.setNom(rs.getString("nom"));
	//	                user.setPrenom(rs.getString("prenom"));
	//	                user.setPassword(rs.getString("password"));
	//	                return user;
	//	            }
	//	        });
	//	        // ECRITURE DANS LOG
	//	        for(User user : users) {
	//	          log.info("findById rest request result : "+user.toString());
	//	        }
	//	        return users;
	//	  }
	//
	//	  @RequestMapping(value="/{userID}/{nom}/{prenom}/{password}", method = RequestMethod.PUT)
	//	  public void createPerson(@PathVariable String userID, @PathVariable String nom, @PathVariable String prenom, @PathVariable String password){
	//	    // REQUETE
	//	    this.jdbcTemplate.update("insert into USER_TABLE (ID, NOM, PRENOM, PASSWORD) values (?,?,?)", new Object[]{userID, nom, prenom, password}, new Object[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
	//	  }

}
