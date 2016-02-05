package com.atplquiz.application;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@ComponentScan(basePackages ={"com.atplquiz.controller"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.atplquiz.repository", entityManagerFactoryRef = "entityManagerFactory")
public class Application implements CommandLineRunner {

  private static Log log = LogFactory.getLog(Application.class);

//  @Autowired
//  JdbcTemplate jdbcTemplate;

  @Override
  public void run(String...strings) throws Exception{
	  
  }

  public static void main(String[] args) {
    log.info("*** Debut Application Run ***");
    SpringApplication.run(Application.class, args);
		
	}

}
