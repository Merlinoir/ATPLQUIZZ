package com.atplquiz.controller;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atplquiz.entity.Resultat;
import com.atplquiz.service.ResultatService;

@RestController
@RequestMapping("/resultats")
public class ResultatController {
	


	@Autowired
	JdbcTemplate jdbcTemplate;
	
	ResultatService rs;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Resultat> findAll() {
		rs = new ResultatService(jdbcTemplate);
		return rs.findAll();
	}

	@RequestMapping(value = "/{resultatID}", method = RequestMethod.GET)
	public List<Resultat> findById(@PathVariable String resultatID) {
		rs = new ResultatService(jdbcTemplate);
		return rs.findById(resultatID);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resultat createResultat(@RequestBody Resultat resultat) {
		rs = new ResultatService(jdbcTemplate);
		return rs.createResultat(resultat);
	}

	@RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resultat updateResultat(@RequestBody Resultat resultat) {
		rs = new ResultatService(jdbcTemplate);
		return rs.updateResultat(resultat);
	}

	@RequestMapping(value = "/{themeId}", method = RequestMethod.DELETE)
	void deleteResultat(@PathVariable long resultatId) {
		rs = new ResultatService(jdbcTemplate);
		rs.deleteResultat(resultatId);
		
	}

}
