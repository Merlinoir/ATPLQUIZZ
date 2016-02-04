package com.atplquiz.entity;

import static javax.persistence.GenerationType.AUTO;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
@SequenceGenerator(
		name = "user_seq",
		sequenceName = "user_table_id_user_seq")
public class User {

	@Id
    @GeneratedValue(strategy = AUTO, generator = "user_seq")
	private int id;
	private String nom;
	private String prenom;
	private String password;

	public User() {
	}

	public User(int id, String nom, String prenom, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + "]";
	}

}
