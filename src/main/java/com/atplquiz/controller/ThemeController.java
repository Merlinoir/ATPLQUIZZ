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

import com.atplquiz.entity.Theme;
import com.atplquiz.service.ThemeService;

@RestController
@RequestMapping("/themes")
public class ThemeController {
	ThemeService ts;

	

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Theme> findAll() {
		return ts.findAll();
	}

	@RequestMapping(value = "themeByID", method = RequestMethod.GET)
	 @ResponseBody
	  public List<Theme> findById(@RequestParam(value = "themeId") String themeId){
		return ts.findById(themeId);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Theme createTheme(@RequestBody Theme theme) {
		return ts.createTheme(theme);
	}

	@RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Theme updateTheme(@RequestBody Theme theme) {
		return ts.updateTheme(theme);
	}

	@RequestMapping(value = "/{themeId}", method = RequestMethod.DELETE)
	void deleteTheme(@PathVariable long themeId) {
		ts.deleteTheme(themeId);
	}
}
