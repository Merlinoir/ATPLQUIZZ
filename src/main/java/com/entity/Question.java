package com.entity;

public class Question {
	private long id;
	private String libelleQuestion;
	private long idTheme;
	
	
	public long getId() {
		return id;
	}
	public void setId(long idQuestion) {
		this.id = idQuestion;
	}
	public String getLibelleQuestion() {
		return libelleQuestion;
	}
	public void setLibelleQuestion(String libelleQuestion) {
		this.libelleQuestion = libelleQuestion;
	}
	public long getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(long idTheme) {
		this.idTheme = idTheme;
	}
	@Override
	public String toString() {
		return "Question [idQuestion=" + id + ", libelleQuestion="
				+ libelleQuestion + ", idTheme=" + idTheme + "]";
	}
	public Question(long idQuestion, String libelleQuestion, long idTheme) {
		super();
		this.id = idQuestion;
		this.libelleQuestion = libelleQuestion;
		this.idTheme = idTheme;
	}
	public Question() {
		
	}
	
	

}
