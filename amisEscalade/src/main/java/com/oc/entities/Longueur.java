package com.oc.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Longueur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long idLongueur;
	
	// attributs d'une longueur
	@NotNull
	private int distance;
	@NotNull
	private int hauteur;
	
	// clé étrangère, les longueurs sont liées a des voies
	@ManyToOne
	@JoinColumn(name = "LONGR_VOI")
	private Voie voie;
	
	// constructeur par défaut
	public Longueur() {

	}
	
	// constructeur avec paramètres
	public Longueur(long idLongueur, @NotNull int distance, @NotNull int hauteur, Voie voie) {
		super();
		this.idLongueur = idLongueur;
		this.distance = distance;
		this.hauteur = hauteur;
		this.voie = voie;
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

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
