package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.atplquiz.entity.Question;

@Service
public class QuestionService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Question> findAll() {
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
	
	
	public List<Question> findByThemeId(long id) {
		List<Question> questions = this.jdbcTemplate.query("select * from question where id_theme =" + id,
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

	
	public void deleteQuestion(long id) {
		jdbcTemplate.update("DELETE FROM question WHERE id_question = ?;", new Object[] { id });
	}
}
