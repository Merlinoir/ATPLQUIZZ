package com.atplquiz.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
public class Question {
	
	@Id
	private long id;
	private String libelle_question;
	private long id_theme;
	
	
	public long getId() {
		return id;
	}
	public void setId(long idQuestion) {
		this.id = idQuestion;
	}
	public String getLibelleQuestion() {
		return libelle_question;
	}
	public void setLibelleQuestion(String libelleQuestion) {
		this.libelle_question = libelleQuestion;
	}
	public long getIdTheme() {
		return id_theme;
	}
	public void setIdTheme(long idTheme) {
		this.id_theme = idTheme;
	}
	@Override
	public String toString() {
		return "Question [idQuestion=" + id + ", libelleQuestion="
				+ libelle_question + ", idTheme=" + id_theme + "]";
	}
	public Question(long idQuestion, String libelleQuestion, long idTheme) {
		super();
		this.id = idQuestion;
		this.libelle_question = libelleQuestion;
		this.id_theme = idTheme;
	}
	public Question() {
		
	}
}
