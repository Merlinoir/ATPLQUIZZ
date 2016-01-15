package com.entity;

public class Question {
	
	private long id_question;
	private String libelleQuestion;
	private String id_theme;
	
	
	
	public Question(){
		
	}
	public Question(long id_question, String libelleQuestion, String id_theme) {
		super();
		this.id_question = id_question;
		this.libelleQuestion = libelleQuestion;
		this.id_theme = id_theme;
	}
	
	
	public long getId() {
		return id_question;
	}
	public void setId(long id_question) {
		this.id_question = id_question;
	}
	public String getLibelleQuestion() {
		return libelleQuestion;
	}
	public void setLibelleQuestion(String libelleQuestion) {
		this.libelleQuestion = libelleQuestion;
	}
	public String getId_theme() {
		return id_theme;
	}
	public void setId_theme(String id_theme) {
		this.id_theme = id_theme;
	}
	
	
}

