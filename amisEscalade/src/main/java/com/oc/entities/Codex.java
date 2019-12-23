package com.oc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Codex implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private long idCdx;

	// attributs du codex
	private int codePostal;
	private String commune;
	private String departement;
	
	// le codex sert a la création de tous les sites d'escalades
	@OneToMany(mappedBy ="codex", fetch = FetchType.LAZY)
	private Collection<SiteEscalade>siteEscalades;
	
	// constructeur par défaut
	public Codex() {
		
	}

	// constructeur avec paramètres
	public Codex(long idCdx, int codePostal, String commune, String departement) {
		super();
		this.idCdx = idCdx;
		this.codePostal = codePostal;
		this.commune = commune;
		this.departement = departement;
	}
	

	// getters and setters
	public long getIdCdx() {
		return idCdx;
	}

	public void setIdCdx(long idCdx) {
		this.idCdx = idCdx;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
