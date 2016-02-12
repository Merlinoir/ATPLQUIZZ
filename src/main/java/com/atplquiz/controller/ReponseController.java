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

import com.atplquiz.entity.Reponse;
import com.atplquiz.service.ReponseService;


@RestController
@RequestMapping("/reponse")
public class ReponseController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public ReponseService rs;

	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<Reponse> findAll(){
		rs = new ReponseService(jdbcTemplate);
		return rs.findAll();
	}

	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public List<Reponse> findById(@PathVariable String id){
		rs = new ReponseService(jdbcTemplate);
		return rs.findById(id);
	}

	
	@RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Reponse createReponse (@RequestBody Reponse reponse){
		rs = new ReponseService(jdbcTemplate);
		return rs.createReponse(reponse);
	}
	

	@RequestMapping(value="update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Reponse updateReponse(@RequestBody Reponse reponse){
		rs = new ReponseService(jdbcTemplate);
		return rs.updapteReponse(reponse);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteReponse (@PathVariable long id) {
		rs = new ReponseService(jdbcTemplate);
		rs.deleteReponse(id);
	}
	
	
	//Fonction utilisée uniquement par le QcmService
	  public List<Reponse> findReponseByIdQuestion( long id){
		  rs = new ReponseService(jdbcTemplate);
		  return rs.findReponseByIdQuestion(id);
	  }
	
	
	
}
