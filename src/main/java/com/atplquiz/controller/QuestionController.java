package com.atplquiz.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atplquiz.entity.Question;
import com.atplquiz.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	

 
	private QuestionService qs;

	
	  @RequestMapping(value="/all", method = RequestMethod.GET)
	  public List<Question> findAll(){
		  return qs.findAll();
	  }

	  @RequestMapping(value="/{id}", method = RequestMethod.GET)
	  public List<Question> findById(@PathVariable String id){
		  return qs.findById(id);
	  }


	  @RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public Question createQuestion (@RequestBody Question question){
			return qs.createQuestion(question);
		}

	  
	  @RequestMapping(value="update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public Question updateQuestion(@RequestBody Question question){
		  return qs.updateQuestion(question);
	  }
	  
	  

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteQuestion (@PathVariable long id) {
		qs.deleteQuestion(id);
	}
	
	
	  //Fonction utilisée par le QcmService
	  public List<Question> findByThemeId(long id){
		  return qs.findByThemeId(id);
	  }
}
	