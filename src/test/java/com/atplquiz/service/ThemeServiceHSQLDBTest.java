package com.atplquiz.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.atplquiz.entity.Theme;
import com.atplquiz.testUtils.ThemeUtils;

public class ThemeServiceHSQLDBTest extends ServiceHSQLDBTest {
	
	@Autowired
	ThemeService themeService;

	@Test
	public void findAll(){
		//On test qu'il n'y a pas de Theme en db	
		//GIVEN
		List<Theme>listeRetrieved = new ArrayList<Theme>();
		//WHEN
		listeRetrieved = themeService.findAll();
		//THEN
		Assert.assertEquals("Il ne doit pas y avoir de theme en db",0, listeRetrieved.size());

		//Puis on crée un theme et on vérifie sa persistance
		//GIVEN
		List<Theme>listeToInject = ThemeUtils.getListTheme();
		for(Theme theme : listeToInject){
			themeService.createTheme(theme);
		}
		//WHEN
		listeRetrieved = themeService.findAll();
		//THEN
		Assert.assertEquals("Il doit y avoir 2 theme en db", 2, listeRetrieved.size());

	}

	@Test
	public void findById(){
		//GIVEN
		Theme theme = ThemeUtils.getOneTheme();
		List<Theme>listeRetrieved = new ArrayList<Theme>();
		//WHEN
		listeRetrieved = themeService.findById(""+theme.getIdTheme());
		//THEN
		Assert.assertEquals("Il ne doit pas y avoir de Theme en db", 0, listeRetrieved.size());
		
		//GIVEN	
		theme = themeService.createTheme(theme);
		//WHEN
		listeRetrieved = themeService.findById(""+theme.getIdTheme());
		//THEN
		Assert.assertEquals("Le nom du theme en BDD est nomTheme", "nomTheme", listeRetrieved.get(0).getNomTheme());
		Assert.assertEquals("Il doit y avoir un seul theme en db",1, listeRetrieved.size());
		
	}

	@Test
	public void updateThemeTest(){
		//GIVEN
		List<Theme>listeRetrieved = new ArrayList<Theme>();
		Theme themeToUpdate = ThemeUtils.getOneTheme();	
		
		Theme themeUpdated = themeService.createTheme(themeToUpdate);
		//WHEN
		listeRetrieved = themeService.findAll();
		//THEN
		Assert.assertEquals("Le noms de theme doivent etre egaux", themeUpdated.getNomTheme(), themeToUpdate.getNomTheme());
		
		//GIVEN
		themeToUpdate.setNomTheme("nouveaNomTheme");
		themeToUpdate = themeService.updateTheme(themeToUpdate);
		
		//WHEN
		listeRetrieved = themeService.findAll();
		
		//THEN
		Assert.assertEquals("Le noms de theme doivent etre egaux", themeToUpdate.getNomTheme(), listeRetrieved.get(0).getNomTheme());
	}

	@Test
	public void deleteThemeTest(){
		//GIVEN
		List<Theme>listeRetrieved = new ArrayList<Theme>();
		Theme themeToDelete = ThemeUtils.getOneTheme();
		//WHEN
		themeService.createTheme(themeToDelete);
		listeRetrieved = themeService.findAll();
		//THEN
		Assert.assertEquals("Il doit y avoir un seul theme en db",1, listeRetrieved.size());

		//WHEN
		themeService.deleteTheme(themeToDelete.getIdTheme());
		listeRetrieved = themeService.findAll();
		//THEN
		Assert.assertEquals("Il doit plus y avoir de theme en db",0, listeRetrieved.size());
	}

}
