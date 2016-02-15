package com.atplquiz.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.atplquiz.entity.Question;
import com.atplquiz.entity.Reponse;
import com.atplquiz.entity.Theme;
import com.atplquiz.testUtils.QuestionUtils;
import com.atplquiz.testUtils.ReponseUtils;
import com.atplquiz.testUtils.ThemeUtils;

public class ReponseServiceHSQLDBTest extends ServiceHSQLDBTest {
	@Autowired
	QuestionService questionService;

	@Autowired
	ThemeService themeService;

	@Autowired
	ReponseService reponseService;

	@Test
	public void testFindAll(){
		//GIVEN
		Theme theme = ThemeUtils.getOneTheme();
		theme = themeService.createTheme(theme);
		Question question = QuestionUtils.getOneQuestion();
		questionService.createQuestion(question);
		List<Reponse> reponseList = ReponseUtils.getReponseListTrueFirst(question.getId());
		for(Reponse reponse : reponseList){
			reponseService.createReponse(reponse);
		}	
		//WHEN
		List<Reponse>listRetrieved = reponseService.findAll();
		//THEN
		Assert.assertEquals("Il doit y avoir 4 réponse en db",4, listRetrieved.size());
	}


	@Test
	public void testFindById(){
		//GIVEN
		Theme theme = ThemeUtils.getOneTheme();
		theme = themeService.createTheme(theme);
		Question question = QuestionUtils.getOneQuestion();
		questionService.createQuestion(question);
		List<Reponse> reponseList = ReponseUtils.getReponseListTrueFirst(question.getId());
		for(Reponse reponse : reponseList){
			reponseService.createReponse(reponse);
		}	
		//WHEN
		List<Reponse>listRetrieved = reponseService.findById(""+2L);

		//THEN
		Assert.assertEquals("Il ne doit y avoir qu'une réponse avec l'iD 2L", 1, listRetrieved.size());
		Assert.assertEquals("Le libellé de la réponse retrouvée doit être libelleReponse2", "libelleReponse2", listRetrieved.get(0).getLibelleReponse());
	}

	@Test
	public void testUpdate(){
		//GIVEN
		Theme theme = ThemeUtils.getOneTheme();
		theme = themeService.createTheme(theme);
		Question question = QuestionUtils.getOneQuestion();
		questionService.createQuestion(question);
		Reponse reponse = ReponseUtils.getOneReponse(question.getId());
		reponseService.createReponse(reponse);
		List<Reponse> listRetrieved = reponseService.findAll();
		Assert.assertEquals("il doit y avoir une seul reponse en db", 1, listRetrieved.size());
		Assert.assertEquals("Le libelle de la réponse doit être libelleReponse1", "libelleReponse1", listRetrieved.get(0).getLibelleReponse());
		Assert.assertEquals("L'ID de la réponse doit être 1L", 1L, listRetrieved.get(0).getId());
		listRetrieved.clear();
		listRetrieved = reponseService.findById(""+1L);
		listRetrieved.get(0).setLibelleReponse("nouveauLibelleReponse1");
		
		//WHEN
		reponseService.updateReponse(listRetrieved.get(0));
		
		listRetrieved= reponseService.findAll();
		//THEN
		Assert.assertEquals("Il ne doit y avoir qu'un réponse en db", 1, listRetrieved.size());
		Assert.assertEquals("Le libellé de la réponse en db doit être nouveauLibelleReponse1", "nouveauLibelleReponse1", listRetrieved.get(0).getLibelleReponse());
	}
	
	@Test
	public void testDeleteReponse(){
		//GIVEN
		Theme theme = ThemeUtils.getOneTheme();
		theme = themeService.createTheme(theme);
		Question question = QuestionUtils.getOneQuestion();
		questionService.createQuestion(question);
		Reponse reponse = ReponseUtils.getOneReponse(question.getId());
		reponseService.createReponse(reponse);	
		List<Reponse> listRetrieved = reponseService.findAll();
		Assert.assertEquals("il doit y avoir une seul reponse en db", 1, listRetrieved.size());
		Assert.assertEquals("Le libelle de la réponse doit être libelleReponse1", "libelleReponse1", listRetrieved.get(0).getLibelleReponse());
		
		//WHEN
		reponseService.deleteReponse(reponse.getId());
		
		//THEN
		listRetrieved = reponseService.findAll();
		Assert.assertEquals("il doit y avoir une seul reponse en db", 0, listRetrieved.size());
		
	}
	
	@Test
	public void testFindReponseByIdQuestion(){
		//GIVEN
		//On crée 4 réponses qui ont le même id Question
		Theme theme = ThemeUtils.getOneTheme();
		theme = themeService.createTheme(theme);
		Question question = QuestionUtils.getOneQuestion();
		questionService.createQuestion(question);
		List<Reponse> reponses = ReponseUtils.getReponseListTrueFirst(question.getId());
		for(Reponse reponse : reponses){
			reponseService.createReponse(reponse);	
		}
		//On crée une cinquième réponse avec un idQuestion différent
		Question questionIdDifférent = new Question(2L, "libelleQuestionIdDifférent", theme.getIdTheme());
		questionService.createQuestion(questionIdDifférent);
		Reponse reponseIdQuestionDifferent = new Reponse(5L, "libelleReponseIdQuestionDifférent", false, questionIdDifférent.getId());
		reponseService.createReponse(reponseIdQuestionDifferent);
		//On vérifie que l'on a bien 5 réponses en base
		List<Reponse>listRetrieved = reponseService.findAll();
		Assert.assertEquals("Il doit y avoir 5 réponses en db", 5, listRetrieved.size());
		//WHEN
		listRetrieved = reponseService.findReponseByIdQuestion(question.getId());
		//THEN
		//On vérife que l'on a bien 4 réponses en db avec l'id de question
		Assert.assertEquals("Il doit y avoir 4 réponses avec l'idQuestion de question", 4, listRetrieved.size());
		//WHEN
		listRetrieved = reponseService.findReponseByIdQuestion(questionIdDifférent.getId());
		//THEN
		//On vérifie que l'on a bien 1 réponse en db avec l'idQuestion de questionIdDifferent
		Assert.assertEquals("Il doit y avoir une seul réponse en db avec l'idQuestion de questionIdDifferent", 1, listRetrieved.size());
	}

}
