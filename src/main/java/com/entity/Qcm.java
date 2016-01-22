package com.entity;

import java.sql.Date;

public class Qcm {
	
	
	private long id_qcm;
	private long id_user;
	private long id_question;
	private Date date;
	private boolean resultat;
	
	
	
	public Qcm(){
		
	}
	
	public Qcm(long id_qcm, long id_user, long id_question, Date date, boolean resultat) {
		super();
		this.id_qcm = id_qcm;
		this.id_user = id_user;
		this.id_question = id_question;
		this.date = date;
		this.resultat = resultat;
	}

	
	public long getId_qcm() {
		return id_qcm;
	}

	public void setId_qcm(long id_qcm) {
		this.id_qcm = id_qcm;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public long getId_question() {
		return id_question;
	}

	public void setId_question(long id_question) {
		this.id_question = id_question;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date2) {
		this.date = date2;
	}

	public boolean isResultat() {
		return resultat;
	}

	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}
	
	

	
}
