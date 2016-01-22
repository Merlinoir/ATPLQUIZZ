package com.entity;

public class Reponse {
	
	private long id_reponse;
	private String libelleReponse;
	private boolean veracite;
	private long id_question;
	
	
	
	public Reponse(){
		
	}
	
	public Reponse(long id_reponse, String libelleReponse, boolean veracite, long id_question) {
		super();
		this.id_reponse = id_reponse;
		this.libelleReponse = libelleReponse;
		this.veracite = veracite;
		this.id_question = id_question;
	}

	
	
	public long getId_reponse() {
		return id_reponse;
	}

	public void setId_reponse(long id_reponse) {
		this.id_reponse = id_reponse;
	}

	public String getLibelleReponse() {
		return libelleReponse;
	}

	public void setLibelleReponse(String libelleReponse) {
		this.libelleReponse = libelleReponse;
	}

	public boolean isVeracite() {
		return veracite;
	}

	public void setVeracite(boolean veracite) {
		this.veracite = veracite;
	}

	public long getId_question() {
		return id_question;
	}

	public void setId_question(long id_question) {
		this.id_question = id_question;
	}
	
	

}
