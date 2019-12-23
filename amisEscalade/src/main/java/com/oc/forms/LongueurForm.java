package com.oc.forms;

import java.io.Serializable;

public class LongueurForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private long idLongueur;
	// champs
	private int distance;
	private int hauteur;
	
	// constructeurs
	public LongueurForm() {

	}

	public LongueurForm(long idLongueur, int distance, int hauteur) {
		super();
		this.idLongueur = idLongueur;
		this.distance = distance;
		this.hauteur = hauteur;
	}

	// getters and setters
	public long getIdLongueur() {
		return idLongueur;
	}

	public void setIdLongueur(long idLongueur) {
		this.idLongueur = idLongueur;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
