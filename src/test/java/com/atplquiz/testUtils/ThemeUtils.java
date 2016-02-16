package com.atplquiz.testUtils;

import java.util.ArrayList;
import java.util.List;

import com.atplquiz.entity.Theme;

public class ThemeUtils {
	
	
	public static Theme getOneTheme(){
		Theme theme = new Theme();
		theme.setIdTheme(1L);
		theme.setNomTheme("nomTheme");
		return theme;
	}
	
	public static List<Theme> getListTheme(){
		List<Theme>themeList = new ArrayList<Theme>();
		themeList.add(getOneTheme());
		themeList.add(new Theme(2L, "nomTheme2"));
		return themeList;
		
	}

}
