package com.atplquiz.entity;

public class Reponse {
	private long id;
	private String libelleReponse;
	private boolean veracite;
	private long idQuestion;
	
	
	
	
	public Reponse() {
	}
	
	
	public Reponse(long id, String libelleReponse, boolean veracite,
			long idQuestion) {
		super();
		this.id = id;
		this.libelleReponse = libelleReponse;
		this.veracite = veracite;
		this.idQuestion = idQuestion;
	}
	public long getId() {
		return id;
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
	public long getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(long idQuestion) {
		this.idQuestion = idQuestion;
	}
	public void setId(long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Reponse [id=" + id + ", libelleReponse=" + libelleReponse
				+ ", veracite=" + veracite + ", idQuestion=" + idQuestion + "]";
	}
}
