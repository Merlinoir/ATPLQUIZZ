package com.atplquiz.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.atplquiz.entity.Question;
import com.atplquiz.repository.QuestionRepository;
import com.atplquiz.service.QuestionService;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepo;
	
	
	public Question findById(int id) {
		Question bo = null;
		try {
			bo = questionRepo.findOne(id);
		} catch(DataAccessException e) {
			e.printStackTrace();
		}
		return bo;
	}
	
	public void saveQuestion(Question q) {
		questionRepo.save(q);
	}
	
	public void updateQuestion(Question q) {
		questionRepo.saveAndFlush(q);		
	}
	
	public List<Question> findAll() {
		return questionRepo.findAll();
	}

	public void deleteQuestion(int id) {
		questionRepo.delete(id);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;
	
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
}