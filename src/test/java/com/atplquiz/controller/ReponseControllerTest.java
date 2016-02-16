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
import com.atplquiz.entity.Reponse;
import com.atplquiz.service.ReponseService;
import com.atplquiz.testUtils.QuestionUtils;
import com.atplquiz.testUtils.ReponseUtils;

@RunWith(MockitoJUnitRunner.class)
public class ReponseControllerTest {

	@Mock
	ReponseService mockReponseService;
	
	@InjectMocks
	ReponseController reponseController;
	
	@Test
	public void testFindAll(){
		//GIVEN
		List<Reponse> questionList = ReponseUtils.getReponseListTrueFirst(1L);
		Mockito.when(mockReponseService.findAll()).thenReturn(questionList);
		
		//WHEN
		List<Reponse> retrievedList = reponseController.findAll();
		
		//THEN
		Assert.assertEquals("La liste doit contenir 4 Reponse", 4,retrievedList.size());		
	}
	
	@Test
	public void testFindById(){
		//GIVEN
		List<Reponse> returnedReponseList = new ArrayList<Reponse>();
		Reponse reponse = ReponseUtils.getOneReponse(1L);
		returnedReponseList.add(reponse);
		Mockito.when(mockReponseService.findById(Mockito.anyString())).thenReturn(returnedReponseList);
		//WHEN
		List<Reponse> retrievedList = reponseController.findById("1");
		//THEN
		Assert.assertNotNull(retrievedList);
		Assert.assertEquals("La liste ne doit contenir qu'un 1 Reponse", 1, retrievedList.size());
	}
	
	@Test
	public void testCreateReponse(){
		//GIVEN
		Reponse reponse = ReponseUtils.getOneReponse(1L);
		Mockito.when(mockReponseService.createReponse(reponse)).thenReturn(reponse);
		
		//WHEN
		Reponse reponseRetrieved = reponseController.createReponse(reponse);
		
		//THEN
		Assert.assertEquals("L'id de reponseRetrieved doit être 1", 1L, reponseRetrieved.getId());
	}
	
	@Test
	public void testUpdateReponse(){
		//GIVEN
		Reponse reponseToUpdate = ReponseUtils.getOneReponse(1L);
		Assert.assertEquals("l'id de userToUpdate doit être 1L", 1L, reponseToUpdate.getId());
		Mockito.when(mockReponseService.updateReponse(reponseToUpdate)).thenReturn(reponseToUpdate);
		reponseToUpdate.setId(2L);
		
		//WHEN
		Reponse reponseUpdated = reponseController.updateReponse(reponseToUpdate);
		//THEN
		Assert.assertEquals("L'idQuestion de reponseUpdated doit être 2L", 2L, reponseUpdated.getId());
	}
	
	
	@Test
	public void testFindReponseByIdQuestion(){
		//GIVEN
		List<Reponse>listeReponse = ReponseUtils.getReponseListTrueFirst(1L);
		Mockito.when(mockReponseService.findReponseByIdQuestion(1L)).thenReturn(listeReponse);
		
		//WHEN
		List<Reponse>listRetrieved = reponseController.findReponseByIdQuestion(1L);
		
		//THEN
		Assert.assertEquals("La liste doit contenir 4 réponses", 4, listRetrieved.size());
	}
}
