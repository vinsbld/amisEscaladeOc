package com.oc.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The Class Codex.
 */
@Entity
public class Codex implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id cdx. */
	@Id
	private long idCdx;

	// attributs du codex
	/** The code postal. */
	private int codePostal;
	
	/** The commune. */
	private String commune;
	
	/** The departement. */
	private String departement;
	
	/**
	 * Instantiates a new codex.
	 */
	// constructeur par défaut
	public Codex() {
		
	}

	/**
	 * Instantiates a new codex.
	 *
	 * @param idCdx the id cdx
	 * @param codePostal the code postal
	 * @param commune the commune
	 * @param departement the departement
	 */
	// constructeur avec paramètres
	public Codex(long idCdx, int codePostal, String commune, String departement) {
		super();
		this.idCdx = idCdx;
		this.codePostal = codePostal;
		this.commune = commune;
		this.departement = departement;
	}
	

	/**
	 * Gets the id cdx.
	 *
	 * @return the id cdx
	 */
	// getters and setters
	public long getIdCdx() {
		return idCdx;
	}

	/**
	 * Sets the id cdx.
	 *
	 * @param idCdx the new id cdx
	 */
	public void setIdCdx(long idCdx) {
		this.idCdx = idCdx;
	}

	/**
	 * Gets the code postal.
	 *
	 * @return the code postal
	 */
	public int getCodePostal() {
		return codePostal;
	}

	/**
	 * Sets the code postal.
	 *
	 * @param codePostal the new code postal
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Gets the commune.
	 *
	 * @return the commune
	 */
	public String getCommune() {
		return commune;
	}

	/**
	 * Sets the commune.
	 *
	 * @param commune the new commune
	 */
	public void setCommune(String commune) {
		this.commune = commune;
	}

	/**
	 * Gets the departement.
	 *
	 * @return the departement
	 */
	public String getDepartement() {
		return departement;
	}

	/**
	 * Sets the departement.
	 *
	 * @param departement the new departement
	 */
	public void setDepartement(String departement) {
		this.departement = departement;
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
