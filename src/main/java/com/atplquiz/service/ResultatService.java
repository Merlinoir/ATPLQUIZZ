package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.atplquiz.controller.ResultatController;
import com.atplquiz.entity.Resultat;
@Service
public class ResultatService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;	
	
	private static Log log = LogFactory.getLog(ResultatService.class);
	
	public List<Resultat> findAll() {
		// REQUETE
		List<Resultat> resultats = this.jdbcTemplate.query("select * from resultat", new RowMapper<Resultat>() {
			public Resultat mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resultat resultat = new Resultat();
				resultat.setIdResultat(rs.getInt("id_resultat"));
				resultat.setIdQcm(rs.getInt("id_qcm"));
				resultat.setIdReponse(rs.getInt("id_reponse"));
				return resultat;
			}
		});
		return resultats;
	}
	
	public List<Resultat> findById(@PathVariable String resultatID) {
		// REQUETE
		List<Resultat> resultats = this.jdbcTemplate.query("select * from resultat where id_resultat=" + resultatID,
				new RowMapper<Resultat>() {
					public Resultat mapRow(ResultSet rs, int rowNum) throws SQLException {
						Resultat resultat = new Resultat();
						resultat.setIdResultat(rs.getInt("id_resultat"));
						resultat.setIdQcm(rs.getInt("id_qcm"));
						resultat.setIdReponse(rs.getInt("id_reponse"));
						return resultat;
					}
				});
		return resultats;
	}
	
	
	public Resultat createResultat(@RequestBody Resultat resultat) {
		log.info("Creating resultat :" + resultat.toString());
		final long idR = resultat.getIdResultat();
		final long idQ = resultat.getIdQcm();
		final long idRep = resultat.getIdReponse();
		this.jdbcTemplate.update("insert into resultat (ID_RESULTAT,ID_QCM,ID_REPONSE) values (?,?,?)", idR, idQ, idRep);
		return resultat;

	}
	
	public Resultat updateResultat(@RequestBody Resultat resultat) {
		log.info("Theme before update : " + resultat.toString());
		final long idR = resultat.getIdResultat();
		final long idQ = resultat.getIdQcm();
		final long idRep = resultat.getIdReponse();
		this.jdbcTemplate.update("update resultat set ID_QCM=? and ID_REPONSE=? where ID_RESULTAT=? ", idR, idQ, idRep);
		log.info("Resultat after update : " + resultat.toString());
		return resultat;
	}
	
	public void deleteResultat(@PathVariable long resultatId) {
		log.info("Deleting resultat record ID " + resultatId);

		jdbcTemplate.update("DELETE FROM resultat WHERE id_resultat = ?;", new Object[] { resultatId });
	}
	
	
}
