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
public class Secteur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long idSecteur;
	
	// attributs d'un secteur
	@NonNull
	private String nomDuSecteur;
	@NonNull
	private String localisation;
	@NonNull
	private String acces;
	
	// clé étrangère les secteurs sont liés à un site d'escalade
	@ManyToOne
	@JoinColumn(name = "SECT_SIT")
	private SiteEscalade siteEscalade;
	
	// un secteur a une collection de voies
	@OneToMany(mappedBy = "secteur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Voie> voie;
	
	// contructeur par défaut
	public Secteur() {

	}
	
	// constructeur avec paramètres
	public Secteur(long idSecteur, String nomDuSecteur, String localisation, String acces) {
		super();
		this.idSecteur = idSecteur;
		this.nomDuSecteur = nomDuSecteur;
		this.localisation = localisation;
		this.acces = acces;
	}

	// getters and setters
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

	public SiteEscalade getSiteEscalade() {
		return siteEscalade;
	}

	public void setSiteEscalade(SiteEscalade siteEscalade) {
		this.siteEscalade = siteEscalade;
	}

	public Collection<Voie> getVoie() {
		return voie;
	}

	public void setVoie(Collection<Voie> voie) {
		this.voie = voie;
	}
		
}
