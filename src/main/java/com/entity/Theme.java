package com.entity;

public class Theme {
	
	
	private long id_theme;
	private String nom;
	
	
	
	public Theme(){
		
	}
	
	public Theme(long id_theme, String nom) {
		super();
		this.id_theme = id_theme;
		this.nom = nom;
	}

	
	
	public long getId_theme() {
		return id_theme;
	}

	public void setId_theme(long id_theme) {
		this.id_theme = id_theme;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
