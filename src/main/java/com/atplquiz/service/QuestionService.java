package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.atplquiz.entity.Question;


public class QuestionService {

	JdbcTemplate jdbcTemplate;

	public QuestionService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Question> findAll() {
		// REQUETE
		List<Question> questions = this.jdbcTemplate.query("select * from question", new RowMapper<Question>() {
			public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
				Question question = new Question();
				question.setId(rs.getLong("id_question"));
				question.setLibelleQuestion(rs.getString("libelle_question"));
				question.setIdTheme(rs.getLong("id_theme"));

				return question;
			}
		});
		return questions;
	}

	public List<Question> findById(String id) {
		// REQUETE
		List<Question> questions = this.jdbcTemplate.query("select * from question where id_question =" + id,
				new RowMapper<Question>() {
					public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
						Question question = new Question();
						question.setId(rs.getLong("id_question"));
						question.setLibelleQuestion(rs.getString("libelle_question"));
						question.setIdTheme(rs.getLong("id_theme"));
						return question;
					}
				});
		return questions;
	}

	public Question createQuestion(Question question) {
		final long id = question.getId();
		final String libelle = question.getLibelleQuestion();
		final long idTheme = question.getIdTheme();

		this.jdbcTemplate.update("insert into question (ID_QUESTION,LIBELLE_QUESTION,ID_THEME) values (?,?,?)", id,
				libelle, idTheme);
		return question;
	}

	public Question updateQuestion(Question question) {
		final long id = question.getId();
		final String libelle = question.getLibelleQuestion();
		final long idTheme = question.getIdTheme();

		this.jdbcTemplate.update("update question set LIBELLE_QUESTION=?, ID_THEME=? " + " where ID_QUESTION=? ",
				libelle, idTheme, id);
		return question;
	}

	public void deleteUser(long id) {
		jdbcTemplate.update("DELETE FROM question WHERE id_question = ?;", new Object[] { id });
	}

}
