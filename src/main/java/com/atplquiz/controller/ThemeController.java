package com.atplquiz.controller;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atplquiz.entity.Theme;

@RestController
@RequestMapping("/themes")
public class ThemeController {

	private static Log log = LogFactory.getLog(ThemeController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Theme> findAll() {
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

	@RequestMapping(value = "/{themeID}", method = RequestMethod.GET)
	public List<Theme> findById(@PathVariable String themeID) {
		// REQUETE
		List<Theme> themes = this.jdbcTemplate.query("select * from user_table where id_user=" + themeID,
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

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Theme createTheme(@RequestBody Theme theme) {
		log.info("Creating theme :" + theme.toString());
		final Long id = theme.getIdTheme();
		final String nom = theme.getNomTheme();

		this.jdbcTemplate.update("insert into theme (ID_THEME,NOM_THEME) values (?,?)", id, nom);
		return theme;

	}

	@RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Theme updateTheme(@RequestBody Theme theme) {
		log.info("Theme before update : " + theme.toString());
		final long id = theme.getIdTheme();
		final String nom = theme.getNomTheme();

		this.jdbcTemplate.update("update theme set NOM_THEME=? where ID_THEME=? ", id, nom);
		log.info("Theme after update : " + theme.toString());
		return theme;
	}

	@RequestMapping(value = "/{themeId}", method = RequestMethod.DELETE)
	void deleteTheme(@PathVariable long themeId) {
		log.info("Deleting theme_table record ID " + themeId);

		jdbcTemplate.update("DELETE FROM theme WHERE id_theme = ?;", new Object[] { themeId });
	}
}
