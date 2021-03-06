package com.atplquiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atplquiz.entity.Qcm;
import com.atplquiz.entity.QuestionReponse;
import com.atplquiz.service.QcmService;


@RestController
@RequestMapping("/qcm")
public class QcmController {
	@Autowired
	private QcmService qs;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<Qcm> findAll(){
		  return qs.findAll();
	}

	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public List<Qcm> findById(@PathVariable String id){
		  return qs.findById(id);
	}

	
	@RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Qcm createQcm (@RequestBody Qcm qcm){
		qs.createQcm(qcm);
		return qcm;
	}
	

	@RequestMapping(value="update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Qcm updateQcm(@RequestBody Qcm qcm){
		qs.updateQcm(qcm);
		return qcm;
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteQcm (@PathVariable long id) {
		qs.deleteQcm(id);		
	}
	
	
	@RequestMapping(value="getQuestionnaire", method=RequestMethod.GET)
	  @ResponseBody
	  public List<QuestionReponse> questionnaireByTheme(@RequestParam(value = "idTheme") long idTheme){
		  return qs.generateQuestionReponseByTheme(idTheme);
	}
	
}
