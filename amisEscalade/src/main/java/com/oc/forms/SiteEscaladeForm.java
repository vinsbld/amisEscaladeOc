package com.oc.forms;

import java.io.Serializable;

public class SiteEscaladeForm implements Serializable{
	
	private long idSiteEscalade;
	// champs
	private String name;
	private String departement;
	private String ville;
	private boolean officiel;
	
	// constructeurs
	public SiteEscaladeForm() {

	}

	public SiteEscaladeForm(long idSiteEscalade, String name, String departement, String ville, boolean officiel) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.name = name;
		this.departement = departement;
		this.ville = ville;
		this.officiel = officiel;
	}

	// getters and setters
	public long getIdSiteEscalade() {
		return idSiteEscalade;
	}

	public void setIdSiteEscalade(long idSiteEscalade) {
		this.idSiteEscalade = idSiteEscalade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isOfficiel() {
		return officiel;
	}

	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
	}

	
	
}
