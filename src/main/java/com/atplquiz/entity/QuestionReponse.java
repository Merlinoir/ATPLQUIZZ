package com.atplquiz.entity;

import java.util.List;

public class QuestionReponse {
	
	private Question question;
	private List<Reponse> listReponse;
	
	
	
	public QuestionReponse(Question question, List<Reponse> listReponse) {
		super();
		this.question = question;
		this.listReponse = listReponse;
	}
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<Reponse> getListReponse() {
		return listReponse;
	}
	public void setListReponse(List<Reponse> listReponse) {
		this.listReponse = listReponse;
	}

}
