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
 * The Class Voie.
 */
@Entity
public class Voie implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id voie. */
	@Id @GeneratedValue
	private long idVoie;
	
	// attributs de voie
	/** The nom de voie. */
	@NonNull
	private String nomDeVoie;
	
	/** The cotation. */
	@NonNull
	private String cotation;
	
	/** The secteur. */
	// clé étrangère, les voies sont liés a un secteur 
	@ManyToOne
	@JoinColumn(name = "VOI_SECT")
	private Secteur secteur;
	
	/** The longueur. */
	// une voie a une collection de longueurs
	@OneToMany(mappedBy = "voie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Longueur> longueur;
	
	/**
	 * Instantiates a new voie.
	 */
	// constructeur par défaut
	public Voie() {

	}
	
	/**
	 * Instantiates a new voie.
	 *
	 * @param idVoie the id voie
	 * @param nomDeVoie the nom de voie
	 * @param cotation the cotation
	 * @param secteur the secteur
	 * @param longueur the longueur
	 */
	// constructeur avec paramètres
	public Voie(long idVoie, String nomDeVoie, String cotation, Secteur secteur, Collection<Longueur> longueur) {
		super();
		this.idVoie = idVoie;
		this.nomDeVoie = nomDeVoie;
		this.cotation = cotation;
		this.secteur = secteur;
		this.longueur = longueur;
	}

	/**
	 * Gets the id voie.
	 *
	 * @return the id voie
	 */
	// getters and setters
	public long getIdVoie() {
		return idVoie;
	}

	/**
	 * Sets the id voie.
	 *
	 * @param idVoie the new id voie
	 */
	public void setIdVoie(long idVoie) {
		this.idVoie = idVoie;
	}

	/**
	 * Gets the nom de voie.
	 *
	 * @return the nom de voie
	 */
	public String getNomDeVoie() {
		return nomDeVoie;
	}

	/**
	 * Sets the nom de voie.
	 *
	 * @param nomDeVoie the new nom de voie
	 */
	public void setNomDeVoie(String nomDeVoie) {
		this.nomDeVoie = nomDeVoie;
	}

	/**
	 * Gets the cotation.
	 *
	 * @return the cotation
	 */
	public String getCotation() {
		return cotation;
	}

	/**
	 * Sets the cotation.
	 *
	 * @param cotation the new cotation
	 */
	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	/**
	 * Gets the secteur.
	 *
	 * @return the secteur
	 */
	public Secteur getSecteur() {
		return secteur;
	}

	/**
	 * Sets the secteur.
	 *
	 * @param secteur the new secteur
	 */
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	/**
	 * Gets the longueur.
	 *
	 * @return the longueur
	 */
	public Collection<Longueur> getLongueur() {
		return longueur;
	}

	/**
	 * Sets the longueur.
	 *
	 * @param longueur the new longueur
	 */
	public void setLongueur(Collection<Longueur> longueur) {
		this.longueur = longueur;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
