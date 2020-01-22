package com.oc.forms;

import java.io.Serializable;

/**
 * The Class SecteurForm.
 */
public class SecteurForm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id secteur. */
	private long idSecteur;
	
	// champs
	/** The name. */
	private String name;
	
	/** The localisation. */
	private String localisation;
	
	/** The acces. */
	private String acces;
	
	/**
	 * Instantiates a new secteur form.
	 */
	// constructeurs
	public SecteurForm() {

	}

	/**
	 * Instantiates a new secteur form.
	 *
	 * @param idSecteur the id secteur
	 * @param name the name
	 * @param localisation the localisation
	 * @param acces the acces
	 */
	public SecteurForm(long idSecteur, String name, String localisation, String acces) {
		super();
		this.idSecteur = idSecteur;
		this.name = name;
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
	 * Gets the nom du secteur.
	 *
	 * @return the nom du secteur
	 */
	public String getNomDuSecteur() {
		return name;
	}

	/**
	 * Sets the nom du secteur.
	 *
	 * @param nomDuSecteur the new nom du secteur
	 */
	public void setNomDuSecteur(String nomDuSecteur) {
		this.name = nomDuSecteur;
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
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
