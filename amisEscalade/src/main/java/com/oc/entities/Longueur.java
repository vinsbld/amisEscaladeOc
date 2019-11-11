package com.oc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Longueur implements Serializable {
	
	@Id @GeneratedValue
	private long id_longueur;
	@NotNull
	private int distance;
	@NotNull
	private int hauteur;
	
	public Longueur() {

	}

	public Longueur(long id_longueur, @NotNull int distance, @NotNull int hauteur) {
		super();
		this.id_longueur = id_longueur;
		this.distance = distance;
		this.hauteur = hauteur;
	}

	public long getId_longueur() {
		return id_longueur;
	}

	public void setId_longueur(long id_longueur) {
		this.id_longueur = id_longueur;
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
	
	
	
	

}
