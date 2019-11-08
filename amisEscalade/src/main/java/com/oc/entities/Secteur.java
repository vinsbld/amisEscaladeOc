package com.oc.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

public class Secteur implements Serializable {
	
	@Id @GeneratedValue
	private long id_secteur;
	@NonNull
	private String nomDuSecteur;
	@NonNull
	private String localisation;
	@NonNull
	private String acces;
	
	public Secteur() {

	}

	public Secteur(long id_secteur, String nomDuSecteur, String localisation, String acces) {
		super();
		this.id_secteur = id_secteur;
		this.nomDuSecteur = nomDuSecteur;
		this.localisation = localisation;
		this.acces = acces;
	}

	public long getId_secteur() {
		return id_secteur;
	}

	public void setId_secteur(long id_secteur) {
		this.id_secteur = id_secteur;
	}

	public String getNomDuSecteur() {
		return nomDuSecteur;
	}

	public void setNomDuSecteur(String nomDuSecteur) {
		this.nomDuSecteur = nomDuSecteur;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getAcces() {
		return acces;
	}

	public void setAcces(String acces) {
		this.acces = acces;
	}
	
	
}
