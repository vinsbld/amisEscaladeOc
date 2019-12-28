package com.oc.forms;

import java.io.Serializable;

public class SiteEscaladeForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long idSiteEscalade;
	// champs
	private String siteName;
	private String lieu;
	private boolean officiel;
	
	// constructeurs
	public SiteEscaladeForm() {

	}

	public SiteEscaladeForm(long idSiteEscalade, String siteName, String lieu, boolean officiel) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.siteName = siteName;
		this.lieu = lieu;
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

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
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
