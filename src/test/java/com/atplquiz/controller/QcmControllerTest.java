package com.atplquiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atplquiz.entity.Qcm;
import com.atplquiz.entity.QuestionReponse;
import com.atplquiz.service.QcmService;
import com.atplquiz.testUtils.QcmUtils;
import com.atplquiz.testUtils.QuestionUtils;
import com.atplquiz.testUtils.ReponseUtils;

@RunWith(MockitoJUnitRunner.class)
public class QcmControllerTest {
	
	@Mock
	QcmService mockQcmService;
		
	
	@InjectMocks
	QcmController qcmController; 

	
	@Test
	public void testFindAll(){
		//GIVEN
		List<Qcm> qcmList = QcmUtils.getQcmListSameUser();
		Mockito.when(mockQcmService.findAll()).thenReturn(qcmList);
		
		//WHEN
		List<Qcm> retrievedList = qcmController.findAll();
		
		//THEN
		Assert.assertEquals("La liste doit contenir 6 Qcm", 6,retrievedList.size());		
	}
	
	@Test
	public void testFindById(){
		//GIVEN
		Qcm qcm =QcmUtils.getOneQcm();
		List<Qcm> qcmList = new ArrayList<Qcm>();
		qcmList.add(qcm);
		Mockito.when(mockQcmService.findById(Mockito.anyString())).thenReturn(qcmList);
		//WHEN
		List<Qcm> retrievedList = qcmController.findById("1");
		//THEN
		Assert.assertNotNull(retrievedList);
		Assert.assertEquals("La liste ne doit contenir qu'un seul Qcm", 1, retrievedList.size());
	}
	
	@Test
	public void testCreateQcm(){
		//GIVEN
		Qcm qcm = QcmUtils.getOneQcm();
		Mockito.when(mockQcmService.createQcm(qcm)).thenReturn(qcm);
		
		//WHEN
		Qcm qcmRetrieved = qcmController.createQcm(qcm);
		
		//THEN
		Assert.assertEquals("L'id de qcmRetrieved doit être 1", "1", qcmRetrieved.getId());
	}
	
	@Test
	public void testUpdateQcm(){
		//GIVEN
		Qcm qcmToUpdate = QcmUtils.getOneQcm();
		Assert.assertEquals("l'id de qcmToUpdate doit être 1L", 1L, qcmToUpdate.getIdUser());
		qcmToUpdate.setIdUser(2L);
		Mockito.when(mockQcmService.updateQcm(qcmToUpdate)).thenReturn(qcmToUpdate);
		
		//WHEN
		Qcm qcmUpdated = qcmController.updateQcm(qcmToUpdate);
		//THEN
		Assert.assertEquals("L'idUser de qcmUpdated doit être 2L", 2L, qcmUpdated.getIdUser());
	}
	
	@Test
	public void testGetQuestionnaireByTheme(){
		//GIVEN
		QuestionReponse questionReponse = new QuestionReponse(QuestionUtils.getOneQuestion(), ReponseUtils.getReponseListTrueFirst(1L));
		List<QuestionReponse>listeQuestionreponse = new ArrayList<QuestionReponse>();
		listeQuestionreponse.add(questionReponse);
		Mockito.when(mockQcmService.generateQuestionReponseByTheme(1L)).thenReturn(listeQuestionreponse);
		
		//WHEN
		List<QuestionReponse>listeQuestionReponseRetrieved = qcmController.questionnaireByTheme(1L);
		
		//THEN
		Assert.assertTrue("Les deux listes doivent avoir la même taille", listeQuestionreponse.size()==listeQuestionReponseRetrieved.size());
		
	}

}
