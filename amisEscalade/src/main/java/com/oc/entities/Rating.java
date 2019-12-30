package com.oc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Rating implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long idCot;
	// attributs de cotation
	@NonNull
	private String cote;
	
	// les cotations servent à la création de voies
	@OneToMany(mappedBy ="rating", fetch = FetchType.LAZY)
	private Collection<Voie>voies;
	
	// constructeur par défaut
	public Rating() {

	}

	// constructeur avec paramètres
	public Rating(Long idCot, String cote, Collection<Voie> voies) {
		super();
		this.idCot = idCot;
		this.cote = cote;
		this.voies = voies;
	}

	// getters and setters
	public Long getIdCot() {
		return idCot;
	}

	public void setIdCot(Long idCot) {
		this.idCot = idCot;
	}

	public String getCote() {
		return cote;
	}

	public void setCote(String cote) {
		this.cote = cote;
	}

	public Collection<Voie> getVoies() {
		return voies;
	}

	public void setVoies(Collection<Voie> voies) {
		this.voies = voies;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
