package com.atplquiz.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
@SequenceGenerator(
		name = "user_seq",
		sequenceName = "user_table_id_user_seq")
public class User {

	private long id;
	private String pseudo;
	private String password;
	private Boolean isAdmin;

	public User() {
	}

	public User(long id, String pseudo, String password, Boolean isAdmin) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pseudo=" + pseudo + ", isAdmin=" + isAdmin + ", password=" + password + "]";
	}

}
