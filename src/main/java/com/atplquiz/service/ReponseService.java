package com.atplquiz.service;

import org.springframework.jdbc.core.JdbcTemplate;

public class ReponseService {
	
	JdbcTemplate jdbcTemplate;

	public ReponseService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	


}
