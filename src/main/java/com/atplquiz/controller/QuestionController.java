package com.atplquiz.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atplquiz.entity.Question;
import com.atplquiz.service.QuestionService;


@Controller
@RequestMapping("/questions")
public class QuestionController {
	

	@Autowired
	QuestionService qs;

	// ------------ All question -------------
	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Question> findAll(){
		return qs.findAll();
	}

	// --------------  One Question  ----------
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Question findById(@PathVariable("id")int id){
		return qs.findById(id);
	}

	// --------- Create -------------
	@RequestMapping(method=RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public void createQuestion (@RequestBody Question question){
		qs.saveQuestion(question);
	}

	// ---------- Update ------------
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = "application/json; charset=utf-8")
	public Question updateQuestion(@PathVariable("id") int id, @Valid @RequestBody Question question) {
		Question q = qs.findById(id);
		if(q == null) {
			return null;
		}
		
		q.setId(question.getId());
		q.setIdTheme(question.getIdTheme());
		q.setLibelleQuestion(question.getLibelleQuestion());
		
		qs.updateQuestion(q);
		return q;
	}
	  
	// --------- Delete ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteQuestion (@PathVariable("id") int id) {
		qs.deleteQuestion(id);
	}
	
	
	  //Fonction utilisée par le QcmService
	  public List<Question> findByThemeId(long id){
		  return qs.findByThemeId(id);
	  }
}
	