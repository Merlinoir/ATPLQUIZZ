package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.atplquiz.entity.Reponse;

public class ReponseService {
	
	JdbcTemplate jdbcTemplate;

	private static Log log = LogFactory.getLog(ReponseService.class);
	

	
	public ReponseService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Reponse>findAll(){
				List<Reponse> reponses = this.jdbcTemplate.query(
						"select * from reponse",
						new RowMapper<Reponse>() {
							public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
								Reponse reponse = new Reponse();
								reponse.setId(rs.getInt("id_reponse"));
								reponse.setLibelleReponse(rs.getString("libelle_reponse"));
								reponse.setVeracite(rs.getBoolean("veracite"));
								reponse.setIdQuestion(rs.getLong("id_question"));
								return reponse;
							}
						});
				// ECRITURE DANS LOG
				for(Reponse reponse : reponses) {
					log.info("findAll rest request result : "+reponse.toString());
				}
				return reponses;
	}
	
	
	public List<Reponse> findById(String id){
				List<Reponse> reponses = this.jdbcTemplate.query(
						"select * from reponse where id_reponse="+id,
						new RowMapper<Reponse>() {
							public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
								Reponse reponse = new Reponse();
								reponse.setId(rs.getLong("id_question"));
								reponse.setLibelleReponse(rs.getString("libelle_reponse"));
								reponse.setVeracite(rs.getBoolean("veracite"));
								reponse.setIdQuestion(rs.getLong("id_question"));
								return reponse;
							}
						});
				// ECRITURE DANS LOG
				for(Reponse reponse : reponses) {
					log.info("findById rest request result : "+reponse.toString());
				}
				return reponses;
	}
	
	
	public Reponse createReponse(Reponse reponse){
		log.info("Creating reponse :" + reponse.toString());
		final long id = reponse.getId();
		final String libelleReponse = reponse.getLibelleReponse();
		final boolean veracite = reponse.isVeracite();
		final long idQuestion = reponse.getIdQuestion();
		
		this.jdbcTemplate.update("insert into reponse (ID_REPONSE, LIBELLE_REPONSE, VERACITE, ID_QUESTION) values (?,?,?,?)",id,libelleReponse,veracite,idQuestion);
		return reponse;
	}
	
	
	public Reponse updapteReponse(Reponse reponse){
		log.info("Réponse before update : "+reponse.toString());
		final long id = reponse.getId();
		final String libelleReponse = reponse.getLibelleReponse();
		final boolean veracite = reponse.isVeracite();
		final long idQuestion = reponse.getIdQuestion();

		this.jdbcTemplate.update("update reponse set LIBELLE_REPONSE=?, VERACITE=?, ID_QUESTION=? "+" where ID_REPONSE=? ",libelleReponse,veracite,idQuestion,id);
		log.info("Reponse after update : "+reponse.toString());
		return reponse;
	}
	
	
	public void deleteReponse(long id){
		log.info("Deleting reponse record ID " + id);

		jdbcTemplate.update (
				"DELETE FROM reponse WHERE ID_REPONSE = ?;",
				new Object[] { id }
				);
	}
	
	
	public List<Reponse> findReponseByIdQuestion(long id) {
		// REQUETE
		List<Reponse> reponses = this.jdbcTemplate.query("select * from reponse where id_question =" + id,
				new RowMapper<Reponse>() {
					public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
						Reponse reponse = new Reponse();
						reponse.setId(rs.getLong("id_reponse"));
						reponse.setLibelleReponse(rs.getString("libelle_reponse"));
						reponse.setVeracite(rs.getBoolean("veracite"));
						reponse.setIdQuestion(rs.getLong("id_question"));
						return reponse;
						
					}
				});
		return reponses;
	}
}
