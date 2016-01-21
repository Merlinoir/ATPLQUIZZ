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

@RestController
@RequestMapping("/question")
public class QuestionRestController {
	
	private static Log log = LogFactory.getLog(Application.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	 @RequestMapping(value="/all", method = RequestMethod.GET)
	  public List<Question> findAll(){
	    // REQUETE
	    List<Question> questions= this.jdbcTemplate.query(
	        "select * from question",
	        new RowMapper<Question>() {
	            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Question question = new Question();
	                question.setId(rs.getInt("id_question"));
	                question.setLibelleQuestion(rs.getString("libelle"));
	                question.setId_theme(rs.getInt("id_theme"));
	                return question;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(Question question : questions) {
	          log.info("findAll rest request result : "+question.toString());
	        }
	        return questions;
	  }
	 
	 @RequestMapping(value="/{questionID}", method = RequestMethod.GET)
	  public List<Question> findById(@PathVariable String questionID){
	    // REQUETE
	    List<Question> questions = this.jdbcTemplate.query(
	        "select * from QUESTION where id_question="+questionID,
	        new RowMapper<Question>() {
	            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Question question = new Question();
	                question.setId(rs.getInt("id_question"));
	                question.setId_theme(rs.getInt("id_theme"));
	                question.setLibelleQuestion(rs.getString("libelle"));
	                return question;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(Question question : questions) {
	          log.info("findById rest request result : "+question.toString());
	        }
	        return questions;
	  }

}
