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

import org.springframework.lang.NonNull;

@Entity
public class Voie implements Serializable {
	
	@Id @GeneratedValue
	private long id_voie;
	@NonNull
	private String nomDeVoie;
	@NonNull
	private String cotation;
	
	@ManyToOne
	@JoinColumn(name = "VOI_SECT")
	private Secteur secteur;
	
	@OneToMany(mappedBy = "voie", fetch = FetchType.LAZY)
	private Collection<Longueur> longueur;
	
	public Voie() {

	}

	public Voie(long id_voie, String nomDeVoie, String cotation) {

		this.id_voie = id_voie;
		this.nomDeVoie = nomDeVoie;
		this.cotation = cotation;
	}

	public long getId_voie() {
		return id_voie;
	}

	public void setId_voie(long id_voie) {
		this.id_voie = id_voie;
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
	
	
	
}
