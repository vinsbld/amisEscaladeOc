package com.oc.forms;

import java.io.Serializable;

/**
 * The Class VoieForm.
 */
public class VoieForm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id voie. */
	private long idVoie;
	
	// champs
	/** The name. */
	private String name;
	
	/** The cotation. */
	private String cotation;
	
	/**
	 * Instantiates a new voie form.
	 */
	//constructeurs
	public VoieForm() {

	}

	/**
	 * Instantiates a new voie form.
	 *
	 * @param idVoie the id voie
	 * @param name the name
	 * @param cotation the cotation
	 */
	public VoieForm(long idVoie, String name, String cotation) {
		super();
		this.idVoie = idVoie;
		this.name = name;
		this.cotation = cotation;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
