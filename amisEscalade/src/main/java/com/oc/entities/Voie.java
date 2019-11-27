package com.oc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Voie implements Serializable {
	
	@Id @GeneratedValue
	private long idVoie;
	@NonNull
	private String nomDeVoie;
	@NonNull
	private String cotation;
	
	@ManyToOne
	@JoinColumn(name = "VOI_SECT")
	private Secteur secteur;
	
	@OneToMany(mappedBy = "voie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Longueur> longueur;
	
	public Voie() {

	}

	public Voie(long idVoie, String nomDeVoie, String cotation, Secteur secteur, Collection<Longueur> longueur) {
		super();
		this.idVoie = idVoie;
		this.nomDeVoie = nomDeVoie;
		this.cotation = cotation;
		this.secteur = secteur;
		this.longueur = longueur;
	}

	public long getIdVoie() {
		return idVoie;
	}

	public void setIdVoie(long idVoie) {
		this.idVoie = idVoie;
	}

	public String getNomDeVoie() {
		return nomDeVoie;
	}

	public void setNomDeVoie(String nomDeVoie) {
		this.nomDeVoie = nomDeVoie;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Collection<Longueur> getLongueur() {
		return longueur;
	}

	public void setLongueur(Collection<Longueur> longueur) {
		this.longueur = longueur;
	}


	
}
