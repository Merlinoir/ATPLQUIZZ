package com.atplquiz.service;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-config.xml" })
public abstract class ServiceHSQLDBTest {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	private EmbeddedDatabase dataSource;

	@Before
	public void setUp() {
		dataSource = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2).addScript("create-db.sql")
				.build();

		jdbcTemplate.setDataSource(dataSource);
	}
	
	@After
	public void destroy(){
		dataSource.shutdown();
	}
}
