package com.atplquiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.atplquiz.entity.Question;
import com.atplquiz.service.QuestionService;
import com.atplquiz.testUtils.QuestionUtils;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
	@Mock
	QuestionService mockQuestionService;
	
	@InjectMocks
	QuestionController questionController;
	
	@Test
	public void testFindAll(){
		//GIVEN
		List<Question> questionList = QuestionUtils.getQuestionListOneTheme();
		Mockito.when(mockQuestionService.findAll()).thenReturn(questionList);
		
		//WHEN
		List<Question> retrievedList = questionController.findAll();
		
		//THEN
		Assert.assertEquals("La liste doit contenir 2 Question", 2,retrievedList.size());		
	}
	
	@Test
	public void testFindById(){
		//GIVEN
		List<Question> returnedQuestionList = new ArrayList<Question>();
		Question question = QuestionUtils.getOneQuestion();
		returnedQuestionList.add(question);
		Mockito.when(mockQuestionService.findById(Mockito.anyString())).thenReturn(returnedQuestionList);
		//WHEN
		List<Question> retrievedList = questionController.findById("1");
		//THEN
		Assert.assertNotNull(retrievedList);
		Assert.assertEquals("La liste ne doit contenir qu'un seul Question", 1, retrievedList.size());
	}
	
	@Test
	public void testCreateQuestion(){
		//GIVEN
		Question question = QuestionUtils.getOneQuestion();
		Mockito.when(mockQuestionService.createQuestion(question)).thenReturn(question);
		
		//WHEN
		Question questionRetrieved = questionController.createQuestion(question);
		
		//THEN
		Assert.assertEquals("L'id de questionRetrieved doit être 1", 1L, questionRetrieved.getId());
	}
	
	@Test
	public void testUpdateQuestion(){
		//GIVEN
		Question questionToUpdate = QuestionUtils.getOneQuestion();
		Assert.assertEquals("l'id de userToUpdate doit être 1L", 1L, questionToUpdate.getId());
		Mockito.when(mockQuestionService.updateQuestion(questionToUpdate)).thenReturn(questionToUpdate);
	
		questionToUpdate.setId(2L);
		
		//WHEN
		Question questionUpdated = questionController.updateQuestion(questionToUpdate);
		//THEN
		Assert.assertEquals("L'idQuestion de questionUpdated doit être 2L", 2L, questionUpdated.getId());
	}

}
