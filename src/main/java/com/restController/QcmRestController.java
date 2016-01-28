package com.restController;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.Application;
import com.entity.Qcm;


@RestController
@RequestMapping("/qcm")
public class QcmRestController {
	
	private static Log log = LogFactory.getLog(Application.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<Qcm> findAll(){
		// REQUETE
		List<Qcm> qcms = this.jdbcTemplate.query(
				"select * from QCM",
				new RowMapper<Qcm>() {
					public Qcm mapRow(ResultSet rs, int rowNum) throws SQLException {
						Qcm qcm = new Qcm();
						qcm.setId(rs.getLong("id_qcm"));
						qcm.setIdUser(rs.getLong("id_user"));
						qcm.setDate(rs.getDate("date"));
						return qcm;
					}
				});
		// ECRITURE DANS LOG
		for(Qcm qcm : qcms) {
			log.info("findAll rest request result : "+qcm.toString());
		}
		return qcms;
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public List<Qcm> findById(@PathVariable String id){
		// REQUETE
		List<Qcm> qcms = this.jdbcTemplate.query(
				"select * from QCM where ID_QCM ="+id,
				new RowMapper<Qcm>() {
					public Qcm mapRow(ResultSet rs, int rowNum) throws SQLException {
						Qcm qcm = new Qcm();
						qcm.setId(rs.getLong("id_qcm"));
						qcm.setIdUser(rs.getLong("id_user"));
						qcm.setDate(rs.getDate("date"));
						return qcm;
					}
				});
		// ECRITURE DANS LOG
		for(Qcm qcm : qcms)  {
			log.info("findById rest request result : "+qcm.toString());
		}
		return qcms;
	}

	@RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Qcm createQcm (@RequestBody Qcm qcm){
		log.info("Creating qcm :" + qcm.toString());
		final long id = qcm.getId();
		final long idUser = qcm.getIdUser();
		final Date date = qcm.getDate();
		

		this.jdbcTemplate.update("insert into QCM (ID_QCM, ID_USER, DATE) values (?,?,?)",id,idUser,date);
		return qcm;

	}

	@RequestMapping(value="update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Qcm updateQcm(@RequestBody Qcm qcm){
		log.info("Qcm before update : "+qcm.toString());
		final long id = qcm.getId();
		final long idUser = qcm.getIdUser();
		final Date date = qcm.getDate();

		this.jdbcTemplate.update("update QCM set ID_USER=?, DATE=? "+" where ID_QCM=? ",idUser, date,id);
		log.info("Qcm after update : "+qcm.toString());
		return qcm;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteQcm (@PathVariable long id) {
		log.info("Deleting Qcm record ID " + id);

		jdbcTemplate.update (
				"DELETE FROM QCM WHERE ID_QCM = ?;",
				new Object[] { id }
				);
	}

}
