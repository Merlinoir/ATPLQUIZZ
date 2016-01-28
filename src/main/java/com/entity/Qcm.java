package com.entity;

import java.sql.Date;

public class Qcm {
	private long id;
	private long idUser;
	private Date date;



	public Qcm() {
	}
	public Qcm(long id, long idUser, Date date) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
