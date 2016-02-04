package com.atplquiz.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atplquiz.entity.Question;


@RestController
@RequestMapping("/question")
public class QuestionController {

	private static Log log = LogFactory.getLog(QuestionController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	  @RequestMapping(value="/all", method = RequestMethod.GET)
	  public List<Question> findAll(){
	    // REQUETE
	    List<Question> questions = this.jdbcTemplate.query(
	        "select * from question",
	        new RowMapper<Question>() {
	            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Question question = new Question();
	                question.setId(rs.getLong("id_question"));
	                question.setLibelleQuestion(rs.getString("libelle_question"));
	                question.setIdTheme(rs.getLong("id_theme"));
	    
	                return question;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(Question question : questions) {
	          log.info("findAll rest request result : "+question.toString());
	        }
	        return questions;
	  }

	  @RequestMapping(value="/{id}", method = RequestMethod.GET)
	  public List<Question> findById(@PathVariable String id){
	    // REQUETE
	    List<Question> questions = this.jdbcTemplate.query(
	        "select * from question where id_question ="+id,
	        new RowMapper<Question>() {
	            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Question question = new Question();
	                question.setId(rs.getLong("id_question"));
	                question.setLibelleQuestion(rs.getString("libelle_question"));
	                question.setIdTheme(rs.getLong("id_theme"));
	                return question;
	            }
	        });
	        // ECRITURE DANS LOG
	    for(Question question : questions) {
	          log.info("findById rest request result : "+question.toString());
	        }
	        return questions;
	  }

		@RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public Question createQuestion (@RequestBody Question question){
		  log.info("Creating question :" + question.toString());
		  final long id = question.getId();
		  final String libelle = question.getLibelleQuestion();
		  final long idTheme = question.getIdTheme();

		  this.jdbcTemplate.update("insert into question (ID_QUESTION,LIBELLE_QUESTION,ID_THEME) values (?,?,?)",id,libelle,idTheme);
			return question;

		}

	  @RequestMapping(value="update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public Question updateQuestion(@RequestBody Question question){
		  log.info("Question before update : "+question.toString());
		  final long id = question.getId();
		  final String libelle = question.getLibelleQuestion();
		  final long idTheme = question.getIdTheme();

		  this.jdbcTemplate.update("update question set LIBELLE_QUESTION=?, ID_THEME=? "+" where ID_QUESTION=? ",libelle,idTheme,id);
		  log.info("Question after update : "+question.toString());
		  return question;
	  }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteUser (@PathVariable long id) {
		log.info("Deleting user_table record ID " + id);

		jdbcTemplate.update (
			"DELETE FROM question WHERE id_question = ?;",
			new Object[] { id }
		);
	}

}
