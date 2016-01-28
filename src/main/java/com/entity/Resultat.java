package com.entity;

public class Resultat {
	
	private long idResultat;
	private long idQcm;
	private long idReponse;
	
	public Resultat(){
		
	}
	
	public Resultat(long idResultat, long idQcm, long idReponse) {
		super();
		this.idResultat = idResultat;
		this.idQcm = idQcm;
		this.idReponse = idReponse;
	}

	public long getIdResultat() {
		return idResultat;
	}

	public void setIdResultat(long idResultat) {
		this.idResultat = idResultat;
	}

	public long getIdQcm() {
		return idQcm;
	}

	public void setIdQcm(long idQcm) {
		this.idQcm = idQcm;
	}

	public long getIdReponse() {
		return idReponse;
	}

	public void setIdReponse(long idReponse) {
		this.idReponse = idReponse;
	}

	@Override
	public String toString() {
		return "Resultat [idResultat=" + idResultat + ", idQcm=" + idQcm + ", idReponse=" + idReponse + "]";
	}
	
}