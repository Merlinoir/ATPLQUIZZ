package com.atplquiz.service;

import java.util.List;

import com.atplquiz.entity.Question;

public interface QuestionService {
	
	Question findById(int id);
	
	void saveQuestion(Question q);
	
	void updateQuestion(Question q);
	
	void deleteQuestion(int id);
	
	List<Question> findAll();
	
	public List<Question> findByThemeId(long id);
	
}
