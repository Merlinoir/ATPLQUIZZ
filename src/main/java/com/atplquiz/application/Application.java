package com.atplquiz.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan(basePackages ={"com.atplquiz.controller"})
public class Application implements CommandLineRunner {

  private static Log log = LogFactory.getLog(Application.class);

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public void run(String...strings) throws Exception{
	  
  }

  public static void main(String[] args) {
    log.info("*** Debut Application Run ***");
    SpringApplication.run(Application.class, args);
		
	}

}
