package com.oc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Secteur implements Serializable {
	
	@Id @GeneratedValue
	private long idSecteur;
	@NonNull
	private String nomDuSecteur;
	@NonNull
	private String localisation;
	@NonNull
	private String acces;
	
	public Secteur() {

	}

	public Secteur(long idSecteur, String nomDuSecteur, String localisation, String acces) {
		super();
		this.idSecteur = idSecteur;
		this.nomDuSecteur = nomDuSecteur;
		this.localisation = localisation;
		this.acces = acces;
	}


	public long getIdSecteur() {
		return idSecteur;
	}

	public void setIdSecteur(long idSecteur) {
		this.idSecteur = idSecteur;
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
