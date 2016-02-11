package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.atplquiz.entity.Question;
import com.atplquiz.entity.QuestionReponse;
import com.atplquiz.entity.Reponse;

public class QcmService {

	private final static int NB_QUESTIONS = 9;

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	List<QuestionReponse> questionReponseList = new ArrayList<QuestionReponse>();
	List<Question> randomQuestions = new ArrayList<Question>();
	List<Reponse> answersToRandomQuestion= new ArrayList<Reponse>();
	
	
	
	public List<QuestionReponse> generateQuestionReponseByTheme(long idTheme) {
		
		// Recuperer les question par rapport au theme
		List<Question> questions = findQuestionByThemeId(idTheme);

		// Extraire 10 Questions e math.random
		
		while (randomQuestions.size() != NB_QUESTIONS) {
			int index = randomWithRange(0, questions.size());
			if (!randomQuestions.contains(questions.get(index))) {
				randomQuestions.add(questions.get(index));
			}
		}
		
		if (randomQuestions != null && randomQuestions.size()==NB_QUESTIONS){
			for (Question question :randomQuestions){
				
				answersToRandomQuestion = retreiveAnswersByQuestion(question);
				questionReponseList.add(new QuestionReponse(question, answersToRandomQuestion));
			}
			
		}	
		
		return questionReponseList;
	}

	
	
	public int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
	
	
	
	public List<Reponse> retreiveAnswersByQuestion(Question question){
		
		if (question != null){
				long idQuestion= question.getId();
				answersToRandomQuestion.addAll(findAnswerByIdQuestion(idQuestion));
		}
		
		return answersToRandomQuestion;
	}
	

	public List<Question> findQuestionByThemeId(long id) {
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
	
	public List<Reponse> findAnswerByIdQuestion(long id) {
		// REQUETE
		List<Reponse> reponses = this.jdbcTemplate.query("select * from reponse where id_question =" + id,
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
		return reponses;
	}

}