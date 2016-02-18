package com.atplquiz.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.atplquiz.entity.Qcm;
import com.atplquiz.entity.Question;
import com.atplquiz.entity.QuestionReponse;
import com.atplquiz.entity.Reponse;
import com.atplquiz.entity.Theme;
import com.atplquiz.entity.User;
import com.atplquiz.testUtils.QcmUtils;
import com.atplquiz.testUtils.QuestionUtils;
import com.atplquiz.testUtils.ReponseUtils;
import com.atplquiz.testUtils.ThemeUtils;
import com.atplquiz.testUtils.UserUtils;


public class QcmServiceHSQLDBTest extends ServiceHSQLDBTest {

	@Autowired
	QcmService qcmService;

	@Autowired
	UserService userService;

	@Autowired
	ThemeService themeService;

	@Autowired
	QuestionService questionService;

	@Autowired
	ReponseService reponseService;

	@Test
	public void testFindAll() {
		// given
		//On vérifie qu'il n'y a pas de qcm en db
		List<Qcm> listRetrieved = qcmService.findAll();
		Assert.assertEquals("Il doit y avoir 0 Qcm en db",0 , listRetrieved.size());
		userService.createUser(UserUtils.getOneUser());
		//On crée 6 qcm
		List<Qcm> qcms = QcmUtils.getQcmListSameUser();
		for(Qcm qcm : qcms){
			qcmService.createQcm(qcm);
		}
		// when
		listRetrieved = qcmService.findAll();

		// then
		//On vérifie qu'il y a bien 6 qcm trouvés en db
		Assert.assertEquals("Il doit y avoir 6 Qcm en db",6 , listRetrieved.size());
	}

	@Test
	public void testFindById(){
		// given
		//On vérifie qu'il n'y a pas de qcm en db
		List<Qcm> listRetrieved = qcmService.findAll();
		Assert.assertEquals("Il doit y avoir 0 Qcm en db",0 , listRetrieved.size());
		userService.createUser(UserUtils.getOneUser());
		//On crée 1 qcm
		Qcm qcm = QcmUtils.getOneQcm();
		qcmService.createQcm(qcm);
		//On crée un deuxieme qcm avec un id different
		Qcm qcmIdDifférent = QcmUtils.getOneQcm();
		qcmIdDifférent.setId("2");
		qcmService.createQcm(qcmIdDifférent);
		//On vérifie qu'il ya 2 qcm en db
		listRetrieved = qcmService.findAll();
		Assert.assertEquals("Il doit y avoir 2 Qcm en db",2 , listRetrieved.size());
		listRetrieved.clear();
		//WHEN
		listRetrieved = qcmService.findById(qcm.getId());

		//THEN
		//On vérifie que l'on récupère que le qcm qcm
		Assert.assertEquals("Il doit y avoir 1 Qcm en db avec comme id 1", "1" , listRetrieved.get(0).getId());
	}

	@Test
	public void testDeleteQcm(){
		//GIVEN
		//On vérifie qu'il n'y a pas de qcm en db
		List<Qcm> listRetrieved = qcmService.findAll();
		Assert.assertEquals("Il doit y avoir 0 Qcm en db",0 , listRetrieved.size());
		userService.createUser(UserUtils.getOneUser());
		//On crée 1 qcm
		Qcm qcm = QcmUtils.getOneQcm();
		qcmService.createQcm(qcm);

		//WHEN
		qcmService.deleteQcm(Integer.parseInt(qcm.getId()));
		//THEN
		listRetrieved = qcmService.findAll();
		Assert.assertEquals("Il doit y avoir 0 Qcm en db",0 , listRetrieved.size());

	}


	//@Test
	public void testGenerateQuestionReponseByTheme(){
		//GIVEN
		//On crée 1 question avec ses 4 réponses avec le theme id 1L
		//User user = userService.createUser(UserUtils.getOneUser());
		Theme theme = themeService.createTheme(ThemeUtils.getOneTheme());
		Question question = questionService.createQuestion(QuestionUtils.getOneQuestion());
		List<Reponse>listeReponseIdThem1L = ReponseUtils.getReponseListTrueFirst(question.getId());
		for(Reponse reponse : listeReponseIdThem1L){
			reponseService.createReponse(reponse);
		}
		//On crée 1 question avec ses 4 réponses avec le theme id 2L
		theme.setIdTheme(2L);
		theme = themeService.createTheme(theme);
		List<Theme>listThemeRetrieved = themeService.findAll();
		Assert.assertEquals("Il y a bien 2 themes en db", 2 , listThemeRetrieved.size());
		question.setIdTheme(theme.getIdTheme());
		question.setId(2L);
		question = questionService.createQuestion(question);
		List<Reponse>listeReponseIdThem2L = ReponseUtils.getReponseListTrueFirst(question.getId());
		for(Reponse reponse : listeReponseIdThem2L){
			Long id = reponse.getId()+5;
			reponse.setId(id);
			reponseService.createReponse(reponse);
		}
		//On vérifie que l'on a correctement popullé la db
		List<Reponse>listReponseRetrieved = reponseService.findAll();
		Assert.assertEquals("Il y a bien 8réponses en db", 8 , listReponseRetrieved.size());
		List<Question>listQuestionRetrieved = questionService.findAll();
		Assert.assertEquals("Il y a bien 2 questions en db", 2 , listQuestionRetrieved.size());

		//WHEN
		List<QuestionReponse>listQuestionReponseThemeId1LRetrieved = qcmService.generateQuestionReponseByTheme(1L);

		//THEN
		//On vérifie que l'on récupère bien une liste de 4 qcm dont l'idTheme est 1L
		Assert.assertEquals("Il doit y avoir 4 QuestionReponse dans la liste", 4, listQuestionReponseThemeId1LRetrieved.size());

		//WHEN
		List<QuestionReponse>listQuestionReponseThemeId2LRetrieved = qcmService.generateQuestionReponseByTheme(2L);

		//THEN
		//On vérifie que l'on récupère bien une liste de 4 qcm dont l'idTheme est 1L
		Assert.assertEquals("Il doit y avoir 4 QuestionReponse dans la liste", 4, listQuestionReponseThemeId2LRetrieved.size());
	}
}
