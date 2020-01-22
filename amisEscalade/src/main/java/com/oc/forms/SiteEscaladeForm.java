package com.oc.forms;

import java.io.Serializable;

/**
 * The Class SiteEscaladeForm.
 */
public class SiteEscaladeForm implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id site escalade. */
	private long idSiteEscalade;
	
	// champs
	/** The site name. */
	private String siteName;
	
	/** The lieu. */
	private String lieu;
	
	/** The officiel. */
	private boolean officiel;
	
	/**
	 * Instantiates a new site escalade form.
	 */
	// constructeurs
	public SiteEscaladeForm() {

	}

	/**
	 * Instantiates a new site escalade form.
	 *
	 * @param idSiteEscalade the id site escalade
	 * @param siteName the site name
	 * @param lieu the lieu
	 * @param officiel the officiel
	 */
	public SiteEscaladeForm(long idSiteEscalade, String siteName, String lieu, boolean officiel) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.siteName = siteName;
		this.lieu = lieu;
		this.officiel = officiel;
	}

	/**
	 * Gets the id site escalade.
	 *
	 * @return the id site escalade
	 */
	// getters and setters
	public long getIdSiteEscalade() {
		return idSiteEscalade;
	}

	/**
	 * Sets the id site escalade.
	 *
	 * @param idSiteEscalade the new id site escalade
	 */
	public void setIdSiteEscalade(long idSiteEscalade) {
		this.idSiteEscalade = idSiteEscalade;
	}

	/**
	 * Gets the site name.
	 *
	 * @return the site name
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * Sets the site name.
	 *
	 * @param siteName the new site name
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * Gets the lieu.
	 *
	 * @return the lieu
	 */
	public String getLieu() {
		return lieu;
	}

	/**
	 * Sets the lieu.
	 *
	 * @param lieu the new lieu
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/**
	 * Checks if is officiel.
	 *
	 * @return true, if is officiel
	 */
	public boolean isOfficiel() {
		return officiel;
	}

	/**
	 * Sets the officiel.
	 *
	 * @param officiel the new officiel
	 */
	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
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
