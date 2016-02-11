package com.atplquiz.controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

import com.atplquiz.entity.Question;
import com.atplquiz.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
 
	private QuestionService qs;

	  @RequestMapping(value="/all", method = RequestMethod.GET)
	  public List<Question> findAll(){
		  qs = new QuestionService(jdbcTemplate);
		  return qs.findAll();
	  }

	  @RequestMapping(value="/{id}", method = RequestMethod.GET)
	  public List<Question> findById(@PathVariable String id){
		  qs = new QuestionService(jdbcTemplate);
		  return qs.findById(id);
	  }

	  @RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public Question createQuestion (@RequestBody Question question){
			qs = new QuestionService(jdbcTemplate);
			return qs.createQuestion(question);
		}

	  @RequestMapping(value="update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public Question updateQuestion(@RequestBody Question question){
		  qs = new QuestionService(jdbcTemplate);
		  return qs.updateQuestion(question);
	  }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteUser (@PathVariable long id) {
		qs = new QuestionService(jdbcTemplate);
		qs.deleteUser(id);
	}
	
	
	@RequestMapping(value="pickQuestionByThemeForQCM", method=RequestMethod.GET)
	  @ResponseBody
	  public List<Question> questionForQcm(@RequestParam(value = "idTheme") long idTheme){
		
		final int NB_QUESTION = 40;
		
		String sql = "select id_question, libelle_question from question where id_theme=?";
		
		try{
		 
			List<Question> allQuestionForQcm = jdbcTemplate.query(sql, new Object[]{idTheme}, new RowMapper<Question>() {
		            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Question question = new Question();
		                question.setId(rs.getLong("id_question"));
		                question.setLibelleQuestion(rs.getString("libelle_question"));
		                return question;
		            }
		        });
		            
		   System.out.println("All question : "+allQuestionForQcm);
		   
		   List<Long> idAllQuestion = new ArrayList<Long>();
		   
		   for (Question nb : allQuestionForQcm) {
			   idAllQuestion.add(nb.getId());
			}
		
		   System.out.println("All id question : "+idAllQuestion);
		   
		  int allQuestionSize = allQuestionForQcm.size();
		  
		  allQuestionSize = allQuestionSize - 1;
		  
		  System.out.println("Size of list of question : "+allQuestionSize);
		  
		  
			  
			  Random rng = new Random(); // Ideally just create one instance globally
			// Note: use LinkedHashSet to maintain insertion order
			Set<Integer> randomIndexQuestion = new LinkedHashSet<Integer>();
			while (randomIndexQuestion.size() < NB_QUESTION)
			{
			    Integer next = rng.nextInt(allQuestionSize) + 1;
			    // As we're adding to a set, this will automatically do a containment check
			    randomIndexQuestion.add(next);
			}
			
		  System.out.println("40 id question : "+randomIndexQuestion);
		  
		  List<Question> questionForQcm = new ArrayList<Question>();
		  List<Long> idQuestionForAnswer = new ArrayList<Long>();
		  
		  for (int nb : randomIndexQuestion) {
			  questionForQcm.add(allQuestionForQcm.get(nb));
			  idQuestionForAnswer.add(allQuestionForQcm.get(nb).getId());
			}
		   
		  System.out.println("40 question : "+questionForQcm);
		  System.out.println("40 id question : "+idQuestionForAnswer);
		  
		   return questionForQcm;
		   
		   }catch (EmptyResultDataAccessException e) {
			   return null;
		   }
		   
	  }

}
