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

/**
 * The Class Secteur.
 */
@Entity
public class Secteur implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id secteur. */
	@Id @GeneratedValue
	private long idSecteur;
	
	// attributs d'un secteur
	/** The nom du secteur. */
	@NonNull
	private String nomDuSecteur;
	
	/** The localisation. */
	@NonNull
	private String localisation;
	
	/** The acces. */
	@NonNull
	private String acces;
	
	/** The site escalade. */
	// clé étrangère, les secteurs sont liés à un site d'escalade
	@ManyToOne
	@JoinColumn(name = "SECT_SIT")
	private SiteEscalade siteEscalade;
	
	/** The voie. */
	// un secteur a une collection de voies
	@OneToMany(mappedBy = "secteur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Voie> voie;
	
	/**
	 * Instantiates a new secteur.
	 */
	// contructeur par défaut
	public Secteur() {

	}
	
	/**
	 * Instantiates a new secteur.
	 *
	 * @param idSecteur the id secteur
	 * @param nomDuSecteur the nom du secteur
	 * @param localisation the localisation
	 * @param acces the acces
	 */
	// constructeur avec paramètres
	public Secteur(long idSecteur, String nomDuSecteur, String localisation, String acces) {
		super();
		this.idSecteur = idSecteur;
		this.nomDuSecteur = nomDuSecteur;
		this.localisation = localisation;
		this.acces = acces;
	}

	/**
	 * Gets the id secteur.
	 *
	 * @return the id secteur
	 */
	// getters and setters
	public long getIdSecteur() {
		return idSecteur;
	}

	/**
	 * Sets the id secteur.
	 *
	 * @param idSecteur the new id secteur
	 */
	public void setIdSecteur(long idSecteur) {
		this.idSecteur = idSecteur;
	}

	/**
	 * Gets the nom du secteur.
	 *
	 * @return the nom du secteur
	 */
	public String getNomDuSecteur() {
		return nomDuSecteur;
	}

	/**
	 * Sets the nom du secteur.
	 *
	 * @param nomDuSecteur the new nom du secteur
	 */
	public void setNomDuSecteur(String nomDuSecteur) {
		this.nomDuSecteur = nomDuSecteur;
	}

	/**
	 * Gets the localisation.
	 *
	 * @return the localisation
	 */
	public String getLocalisation() {
		return localisation;
	}

	/**
	 * Sets the localisation.
	 *
	 * @param localisation the new localisation
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**
	 * Gets the acces.
	 *
	 * @return the acces
	 */
	public String getAcces() {
		return acces;
	}

	/**
	 * Sets the acces.
	 *
	 * @param acces the new acces
	 */
	public void setAcces(String acces) {
		this.acces = acces;
	}

	/**
	 * Gets the site escalade.
	 *
	 * @return the site escalade
	 */
	public SiteEscalade getSiteEscalade() {
		return siteEscalade;
	}

	/**
	 * Sets the site escalade.
	 *
	 * @param siteEscalade the new site escalade
	 */
	public void setSiteEscalade(SiteEscalade siteEscalade) {
		this.siteEscalade = siteEscalade;
	}

	/**
	 * Gets the voie.
	 *
	 * @return the voie
	 */
	public Collection<Voie> getVoie() {
		return voie;
	}

	/**
	 * Sets the voie.
	 *
	 * @param voie the new voie
	 */
	public void setVoie(Collection<Voie> voie) {
		this.voie = voie;
	}
		
}
