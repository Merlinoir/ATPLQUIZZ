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

import com.atplquiz.entity.Theme;
import com.atplquiz.service.ThemeService;
import com.atplquiz.testUtils.ReponseUtils;
import com.atplquiz.testUtils.ThemeUtils;

@RunWith(MockitoJUnitRunner.class)
public class ThemeControllerTest {
	
	@Mock
	ThemeService mockThemeService;
	
	@InjectMocks
	ThemeController themeController;
	
	@Test
	public void testFindAll(){
		//GIVEN
		List<Theme> themeList = ThemeUtils.getListTheme();
		Mockito.when(mockThemeService.findAll()).thenReturn(themeList);
		
		//WHEN
		List<Theme> retrievedList = themeController.findAll();
		
		//THEN
		Assert.assertEquals("La liste doit contenir 2 Theme", 2,retrievedList.size());		
	}
	
	@Test
	public void testFindById(){
		//GIVEN
		List<Theme> returnedThemeList = new ArrayList<Theme>();
		Theme theme = ThemeUtils.getOneTheme();
		returnedThemeList.add(theme);
		Mockito.when(mockThemeService.findById(Mockito.anyString())).thenReturn(returnedThemeList);
		//WHEN
		List<Theme> retrievedList = themeController.findById("1");
		//THEN
		Assert.assertNotNull(retrievedList);
		Assert.assertEquals("La liste ne doit contenir qu'un seul Theme", 1, retrievedList.size());
	}
	
	@Test
	public void testCreateTheme(){
		//GIVEN
		Theme theme = ThemeUtils.getOneTheme();
		Mockito.when(mockThemeService.createTheme(theme)).thenReturn(theme);
		
		//WHEN
		Theme themeRetrieved = themeController.createTheme(theme);
		
		//THEN
		Assert.assertEquals("L'id de themeRetrieved doit être 1", 1L, themeRetrieved.getIdTheme());
	}
	
	@Test
	public void testUpdateTheme(){
		//GIVEN
		Theme themeToUpdate = ThemeUtils.getOneTheme();
		Assert.assertEquals("l'id de themeToUpdate doit être 1L", 1L, themeToUpdate.getIdTheme());
		Mockito.when(mockThemeService.updateTheme(themeToUpdate)).thenReturn(themeToUpdate);
	
		themeToUpdate.setIdTheme(2L);
		
		//WHEN
		Theme themeUpdated = themeController.updateTheme(themeToUpdate);
		//THEN
		Assert.assertEquals("L'idQuestion de themeUpdated doit être 2L", 2L, themeUpdated.getIdTheme());
	}


}
