package com.oc.forms;

import java.io.Serializable;

public class SiteEscaladeForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idSiteEscalade;
	// champs
	private String siteName;
	private String departement;
	private String ville;
	private int codePostal;
	private boolean officiel;
	
	// constructeurs
	public SiteEscaladeForm() {

	}

	public SiteEscaladeForm(long idSiteEscalade, String siteName, String departement, String ville, int codePostal,
			boolean officiel) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.siteName = siteName;
		this.departement = departement;
		this.ville = ville;
		this.codePostal = codePostal;
		this.officiel = officiel;
	}

	// getters and setters
	public long getIdSiteEscalade() {
		return idSiteEscalade;
	}

	public void setIdSiteEscalade(long idSiteEscalade) {
		this.idSiteEscalade = idSiteEscalade;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public boolean isOfficiel() {
		return officiel;
	}

	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
