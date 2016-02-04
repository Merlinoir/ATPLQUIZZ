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

@RestController
@RequestMapping("/resultats")
public class ResultatController {
	
	private static Log log = LogFactory.getLog(ResultatController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
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

	@RequestMapping(value = "/{resultatID}", method = RequestMethod.GET)
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

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resultat createResultat(@RequestBody Resultat resultat) {
		log.info("Creating resultat :" + resultat.toString());
		final long idR = resultat.getIdResultat();
		final long idQ = resultat.getIdQcm();
		final long idRep = resultat.getIdReponse();

		this.jdbcTemplate.update("insert into resultat (ID_RESULTAT,ID_QCM,ID_REPONSE) values (?,?,?)", idR, idQ, idRep);
		return resultat;

	}

	@RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resultat updateResultat(@RequestBody Resultat resultat) {
		log.info("Theme before update : " + resultat.toString());
		final long idR = resultat.getIdResultat();
		final long idQ = resultat.getIdQcm();
		final long idRep = resultat.getIdReponse();

		this.jdbcTemplate.update("update resultat set ID_QCM=? and ID_REPONSE=? where ID_RESULTAT=? ", idR, idQ, idRep);
		log.info("Resultat after update : " + resultat.toString());
		return resultat;
	}

	@RequestMapping(value = "/{themeId}", method = RequestMethod.DELETE)
	void deleteResultat(@PathVariable long resultatId) {
		log.info("Deleting resultat record ID " + resultatId);

		jdbcTemplate.update("DELETE FROM resultat WHERE id_resultat = ?;", new Object[] { resultatId });
	}

}
