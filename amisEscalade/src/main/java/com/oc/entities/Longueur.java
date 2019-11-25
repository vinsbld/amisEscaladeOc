package com.oc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Longueur implements Serializable {
	
	@Id @GeneratedValue
	private long idLongueur;
	@NotNull
	private int distance;
	@NotNull
	private int hauteur;
	
	@ManyToOne
	@JoinColumn(name = "LONGR_VOI")
	private Voie voie;
	
	public Longueur() {

	}

	public Longueur(long idLongueur, @NotNull int distance, @NotNull int hauteur, Voie voie) {
		super();
		this.idLongueur = idLongueur;
		this.distance = distance;
		this.hauteur = hauteur;
		this.voie = voie;
	}

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

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}



}
