package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.atplquiz.entity.Question;

public class QcmService {

	private final static int NB_QUESTIONS = 9;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Question> generateQcmByTheme(long idTheme) {
		// Recuperer les question par rappport au theme
		List<Question> questions = findByThemeId(idTheme);

		// Extraire 10 Questions e math.random
		List<Question> randomQuestions = new ArrayList<Question>();
		while (randomQuestions.size() != NB_QUESTIONS) {
			int index = randomWithRange(0, questions.size());
			if (!randomQuestions.contains(questions.get(index))) {
				randomQuestions.add(questions.get(index));
			}
		}
		return randomQuestions;
	}

	public int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	public List<Question> findByThemeId(long id) {
		// REQUETE
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

}