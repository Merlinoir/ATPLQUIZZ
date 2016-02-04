package com.atplquiz.entity;

public class Theme {
	
	private long idTheme;
	private String nomTheme;
	
	public Theme(){
		
	}
	
	public Theme(long idTheme, String nomTheme) {
		super();
		this.idTheme = idTheme;
		this.nomTheme = nomTheme;
	}
	
	public long getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(long idTheme) {
		this.idTheme = idTheme;
	}
	public String getNomTheme() {
		return nomTheme;
	}
	public void setNomTheme(String nomTheme) {
		this.nomTheme = nomTheme;
	}

	@Override
	public String toString() {
		return "Theme [idTheme=" + idTheme + ", nomTheme=" + nomTheme + "]";
	}
}