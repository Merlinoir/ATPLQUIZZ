package com.restController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.Application;
import com.entity.Question;
import com.entity.Reponse;


	
	@RestController
	@RequestMapping("/reponse")
	public class ReponseRestController {
		
		private static Log log = LogFactory.getLog(Application.class);

		@Autowired
		JdbcTemplate jdbcTemplate;
		
		 @RequestMapping(value="/all", method = RequestMethod.GET)
		  public List<Reponse> findAll(){
		    // REQUETE
		    List<Reponse> reponses= this.jdbcTemplate.query(
		        "select * from reponse",
		        new RowMapper<Reponse>() {
		            public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Reponse reponse = new Reponse();
		                reponse.setId_reponse(rs.getInt("id_reponse"));
		                reponse.setId_question(rs.getInt("id_question"));
		                reponse.setLibelleReponse(rs.getString("libelle_rep"));
		                reponse.setVeracite(rs.getBoolean("veracite"));
		                return reponse;
		            }
		        });
		        // ECRITURE DANS LOG
		        for(Reponse reponse : reponses) {
		          log.info("findAll rest request result : "+reponse.toString());
		        }
		        return reponses;
		  }
		 
		 @RequestMapping(value="/{reponseID}", method = RequestMethod.GET)
		  public List<Reponse> findById(@PathVariable String reponseID){
		    // REQUETE
		    List<Reponse> reponses = this.jdbcTemplate.query(
		        "select * from REPONSE where id_reponse="+reponseID,
		        new RowMapper<Reponse>() {
		            public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Reponse reponse = new Reponse();
		                reponse.setId_reponse(rs.getInt("id_reponse"));
		                reponse.setId_question(rs.getInt("id_question"));
		                reponse.setLibelleReponse(rs.getString("libelle_rep"));
		                reponse.setVeracite(rs.getBoolean("veracite"));
		                return reponse;
		            }
		        });
		        // ECRITURE DANS LOG
		        for(Reponse reponse : reponses) {
		          log.info("findById rest request result : "+reponse.toString());
		        }
		        return reponses;
		  }
	}


