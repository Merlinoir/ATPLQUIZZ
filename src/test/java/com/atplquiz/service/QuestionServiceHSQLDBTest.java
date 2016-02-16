package com.atplquiz.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.atplquiz.entity.Question;
import com.atplquiz.testUtils.QuestionUtils;
import com.atplquiz.testUtils.ThemeUtils;

public class QuestionServiceHSQLDBTest extends ServiceHSQLDBTest {

	@Autowired
	QuestionService questionService;

	@Autowired
	ThemeService themeService;

	@Test
	public void testFindAll() {
		// given
		themeService.createTheme(ThemeUtils.getOneTheme());
		questionService.createQuestion(new Question(1L, "libelleQuestion", 1L));

		// when
		List<Question> questions = questionService.findAll();

		// then
		Assert.assertNotNull(questions);
		Assert.assertEquals(1,questions.size());
	}

	@Test
	public void testFindById() {
		// given
		themeService.createTheme(ThemeUtils.getOneTheme());
		questionService.createQuestion(new Question(1L, "libelleQuestion", 1L));

		// when
		List<Question> questions = questionService.findById(""+1L);

		// then
		Assert.assertNotNull(questions);
		Assert.assertEquals(1,questions.size());
	}
	@Test
	public void testFindByThemeId(){
		// given
		themeService.createTheme(ThemeUtils.getOneTheme());
		questionService.createQuestion(new Question(1L, "libelleQuestion", 1L));
		// when
		List<Question> questions = questionService.findByThemeId(1L);
		// then
		Assert.assertEquals(1,questions.size());
		Assert.assertEquals("Les id des questions doivent être identiques", 1L, questions.get(0).getId());

		// when
		questions = questionService.findByThemeId(2L);
		// then	
		Assert.assertEquals(0,questions.size());
	}


	@Test
	public void testUpdateQuestion(){
		//GIVEN
		themeService.createTheme(ThemeUtils.getOneTheme());
		Question questionTopdate = QuestionUtils.getOneQuestion();
		questionService.createQuestion(questionTopdate);
		Assert.assertEquals("Le libellé de la question doit être libelleQuestion1", "libelleQuestion1", questionTopdate.getLibelleQuestion());
		questionTopdate.setLibelleQuestion("nouveauLibelleQuestion1");
		//WHEN
		Question questionUpdated = questionService.updateQuestion(questionTopdate);

		//THEN
		Assert.assertEquals("Le libellé de la question doit être nouveauLibelleQuestion1", "nouveauLibelleQuestion1", questionUpdated.getLibelleQuestion());

	}


	@Test
	public void testDeleteQuestion(){
		//GIVEN
		themeService.createTheme(ThemeUtils.getOneTheme());
		Question questionTopdate = QuestionUtils.getOneQuestion();
		questionService.createQuestion(questionTopdate);
		List<Question> listRetrieved = questionService.findAll();
		Assert.assertEquals("Il doit y avoir une question en db", 1, listRetrieved.size());
		//WHEN
		questionService.deleteQuestion(questionTopdate.getId());
		//THEN
		listRetrieved = questionService.findAll();
		Assert.assertEquals("Il ne doit pas y avoir une question en db", 0, listRetrieved.size());
		
		

	}

}
