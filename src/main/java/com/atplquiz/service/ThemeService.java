package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.atplquiz.controller.ThemeController;
import com.atplquiz.entity.Theme;

@Service
public class ThemeService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Log log = LogFactory.getLog(ThemeController.class);
		
	public List<Theme>findAll(){
		// REQUETE
		List<Theme> themes = this.jdbcTemplate.query("select * from theme", new RowMapper<Theme>() {
			public Theme mapRow(ResultSet rs, int rowNum) throws SQLException {
				Theme theme = new Theme();
				theme.setIdTheme(rs.getInt("id_theme"));
				theme.setNomTheme(rs.getString("nom_theme"));
				return theme;
			}
		});
		return themes;
	}
	
	public List<Theme> findById(@RequestParam(value = "themeId") String themeId) {
		List<Theme> themes = this.jdbcTemplate.query("select * from theme where id_theme=" + themeId,
				new RowMapper<Theme>() {
					public Theme mapRow(ResultSet rs, int rowNum) throws SQLException {
						Theme theme = new Theme();
						theme.setIdTheme(rs.getInt("id_theme"));
						theme.setNomTheme(rs.getString("nom_theme"));
						return theme;
					}
				});
		return themes;
	}
	
	
	public Theme createTheme(@RequestBody Theme theme) {
		log.info("Creating theme :" + theme.toString());
		final Long id = theme.getIdTheme();
		final String nom = theme.getNomTheme();
		this.jdbcTemplate.update("insert into theme (ID_THEME,NOM_THEME) values (?,?)", id, nom);
		return theme;
	}
	
	public Theme updateTheme(@RequestBody Theme theme) {
		log.info("Theme before update : " + theme.toString());
		final long id = theme.getIdTheme();
		final String nom = theme.getNomTheme();
		this.jdbcTemplate.update("update theme set NOM_THEME=? where ID_THEME=? ", nom, id);
		log.info("Theme after update : " + theme.toString());
		return theme;
	}
	
	public void deleteTheme(@PathVariable long themeId) {
		log.info("Deleting theme_table record ID " + themeId);

		jdbcTemplate.update("DELETE FROM theme WHERE id_theme = ?;", new Object[] { themeId });
	}
	
	
	
}
