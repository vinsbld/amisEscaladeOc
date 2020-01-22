package com.oc.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * The Class Longueur.
 */
@Entity
public class Longueur implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id longueur. */
	@Id @GeneratedValue
	private long idLongueur;
	
	// attributs d'une longueur
	/** The distance. */
	@NotNull
	private int distance;
	
	/** The hauteur. */
	@NotNull
	private int hauteur;
	
	/** The voie. */
	// clé étrangère, les longueurs sont liées a des voies
	@ManyToOne
	@JoinColumn(name = "LONGR_VOI")
	private Voie voie;
	
	/**
	 * Instantiates a new longueur.
	 */
	// constructeur par défaut
	public Longueur() {

	}
	
	/**
	 * Instantiates a new longueur.
	 *
	 * @param idLongueur the id longueur
	 * @param distance the distance
	 * @param hauteur the hauteur
	 * @param voie the voie
	 */
	// constructeur avec paramètres
	public Longueur(long idLongueur, @NotNull int distance, @NotNull int hauteur, Voie voie) {
		super();
		this.idLongueur = idLongueur;
		this.distance = distance;
		this.hauteur = hauteur;
		this.voie = voie;
	}

	/**
	 * Gets the id longueur.
	 *
	 * @return the id longueur
	 */
	// getters and setters
	public long getIdLongueur() {
		return idLongueur;
	}

	/**
	 * Sets the id longueur.
	 *
	 * @param idLongueur the new id longueur
	 */
	public void setIdLongueur(long idLongueur) {
		this.idLongueur = idLongueur;
	}

	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Sets the distance.
	 *
	 * @param distance the new distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * Gets the hauteur.
	 *
	 * @return the hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Sets the hauteur.
	 *
	 * @param hauteur the new hauteur
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	/**
	 * Gets the voie.
	 *
	 * @return the voie
	 */
	public Voie getVoie() {
		return voie;
	}

	/**
	 * Sets the voie.
	 *
	 * @param voie the new voie
	 */
	public void setVoie(Voie voie) {
		this.voie = voie;
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
