package com.oc.forms;

import java.io.Serializable;

/**
 * The Class LongueurForm.
 */
public class LongueurForm implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id longueur. */
	private long idLongueur;
	
	// champs
	/** The distance. */
	private int distance;
	
	/** The hauteur. */
	private int hauteur;
	
	/**
	 * Instantiates a new longueur form.
	 */
	// constructeurs
	public LongueurForm() {

	}

	/**
	 * Instantiates a new longueur form.
	 *
	 * @param idLongueur the id longueur
	 * @param distance the distance
	 * @param hauteur the hauteur
	 */
	public LongueurForm(long idLongueur, int distance, int hauteur) {
		super();
		this.idLongueur = idLongueur;
		this.distance = distance;
		this.hauteur = hauteur;
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
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
