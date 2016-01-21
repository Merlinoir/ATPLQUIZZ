package com.entity;

public class Question {
	
	private long id_question;
	private String libelleQuestion;
	private int id_theme;
	
	
	
	public Question(){
		
	}
	public Question(long id_question, String libelleQuestion, int id_theme) {
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
	public int getId_theme() {
		return id_theme;
	}
	public void setId_theme(int i) {
		this.id_theme = i;
	}
	
	
}

