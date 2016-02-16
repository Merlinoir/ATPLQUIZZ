package com.atplquiz.entity;

import java.util.Date;

public class Qcm {
	private String id;
	private long idUser;
	private Date date;



	public Qcm() {
	}
	public Qcm(String id, long idUser, java.util.Date date2) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.date = date2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "Qcm [id=" + id + ", idUser=" + idUser + ", date=" + date + "]";
	}
}
