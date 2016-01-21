package com.entity;

import javax.persistence.Entity;

@Entity
import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "UTILISATEUR")
@SequenceGenerator(
		name = "UTILISATEUR_SEQ",
		sequenceName = "UTILISATEUR_SEQ")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * id utilisateur
	 */
	@Id
	@GeneratedValue(strategy=AUTO, generator = "UTILSATEUR_SEQ")
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
