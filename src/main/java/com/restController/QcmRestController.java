package com.restController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.Application;
import com.entity.Qcm;
import com.entity.User;

@RestController
@RequestMapping("/qcm")
public class QcmRestController {
	
	private static Log log = LogFactory.getLog(Application.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	 @RequestMapping(value="/all", method = RequestMethod.GET)
	  public List<Qcm> findAll(){
	    // REQUETE
	    List<Qcm> qcms= this.jdbcTemplate.query(
	        "select * from qcm",
	        new RowMapper<Qcm>() {
	            public Qcm mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Qcm qcm = new Qcm();
	            	qcm.setDate(rs.getDate("ddate"));
	                qcm.setId_qcm(rs.getLong("id_qcm"));
	                qcm.setId_question(rs.getLong("id_question"));
	                qcm.setId_user(rs.getLong("id_utilisateur"));
	                qcm.setResultat(rs.getBoolean("resultat"));
	                return qcm;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(Qcm qcm : qcms) {
	          log.info("findAll rest request result : "+qcm.toString());
	        }
	        return qcms;
	  }
	 
	 @RequestMapping(value="/{qcmID}", method = RequestMethod.GET)
	  public List<Qcm> findById(@PathVariable String qcmID){
	    // REQUETE
	    List<Qcm> qcms = this.jdbcTemplate.query(
	        "select * from QCM where id_qcm="+qcmID,
	        new RowMapper<Qcm>() {
	            public Qcm mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Qcm qcm = new Qcm();
	                qcm.setId_qcm(rs.getLong("id_qcm"));
	                qcm.setId_question(rs.getLong("id_question"));
	                qcm.setId_user(rs.getLong("id_utilisateur"));
	                qcm.setDate(rs.getDate("ddate"));
	                qcm.setResultat(rs.getBoolean("resultat"));
	                return qcm;
	            }
	        });
	        // ECRITURE DANS LOG
	        for(Qcm qcm : qcms) {
	          log.info("findById rest request result : "+qcm.toString());
	        }
	        return qcms;
	  }

}
