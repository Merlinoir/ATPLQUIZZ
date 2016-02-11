package com.atplquiz.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atplquiz.entity.Reponse;


@RestController
@RequestMapping("/reponse")
public class ReponseController {
	private static Log log = LogFactory.getLog(ReponseController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<Reponse> findAll(){
		// REQUETE
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

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public List<Reponse> findById(@PathVariable String id){
		// REQUETE
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

	@RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Reponse createReponse (@RequestBody Reponse reponse){
		log.info("Creating reponse :" + reponse.toString());
		final long id = reponse.getId();
		final String libelleReponse = reponse.getLibelleReponse();
		final boolean veracite = reponse.isVeracite();
		final long idQuestion = reponse.getIdQuestion();
		

		this.jdbcTemplate.update("insert into reponse (ID_REPONSE, LIBELLE_REPONSE, VERACITE, ID_QUESTION) values (?,?,?,?)",id,libelleReponse,veracite,idQuestion);
		return reponse;

	}

	@RequestMapping(value="update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Reponse updateReponse(@RequestBody Reponse reponse){
		log.info("Réponse before update : "+reponse.toString());
		final long id = reponse.getId();
		final String libelleReponse = reponse.getLibelleReponse();
		final boolean veracite = reponse.isVeracite();
		final long idQuestion = reponse.getIdQuestion();

		this.jdbcTemplate.update("update reponse set LIBELLE_REPONSE=?, VERACITE=?, ID_QUESTION=? "+" where ID_REPONSE=? ",libelleReponse,veracite,idQuestion,id);
		log.info("Reponse after update : "+reponse.toString());
		return reponse;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteReponse (@PathVariable long id) {
		log.info("Deleting reponse record ID " + id);

		jdbcTemplate.update (
				"DELETE FROM reponse WHERE ID_REPONSE = ?;",
				new Object[] { id }
				);
	}
	
	@RequestMapping(value="pickReponseForQCM", method=RequestMethod.GET)
	  @ResponseBody
	  public List<Reponse> reponseForQcm(@RequestParam(value = "idQuestionForAnswer") long idQuestionForAnswer){
		
//		List<Long> idQuestionForAnswer = new ArrayList<Long>();
//		for (int i : question) {
//			  questionForQcm.add(allQuestionForQcm.get(nb));
//			  idQuestionForAnswer.add(allQuestionForQcm.get(nb).getId());
//			}
		
		System.out.println("ID QUESTION : "+idQuestionForAnswer);
	
	 String sqlReponse = "select id_reponse, libelle_reponse, veracite, id_question from reponse where id_question=?";
		
	 
		List<Reponse> reponseForSelectedQuestion = jdbcTemplate.query(sqlReponse, new Object[]{idQuestionForAnswer}, new RowMapper<Reponse>() {
	            public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Reponse reponse = new Reponse();
	            	reponse.setId(rs.getLong("id_reponse"));
	            	reponse.setLibelleReponse(rs.getString("libelle_reponse"));
					reponse.setVeracite(rs.getBoolean("veracite"));
					reponse.setIdQuestion(rs.getLong("id_question"));
	                return reponse;
	            }
	        });
		System.out.println("All reponse : "+reponseForSelectedQuestion);
		return reponseForSelectedQuestion;
		}
}
