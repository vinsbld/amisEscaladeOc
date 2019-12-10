package com.oc.forms;

public class SiteEscaladeForm {
	
	private long idSiteEscalade;
	private String name;
	private String departement;
	private String ville;
	private Boolean officiel;
	
	public SiteEscaladeForm() {

	}

	
	public SiteEscaladeForm(long idSiteEscalade, String name, String departement, String ville, Boolean officiel) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.name = name;
		this.departement = departement;
		this.ville = ville;
		this.officiel = officiel;
	}



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


	public Boolean getOfficiel() {
		return officiel;
	}


	public void setOfficiel(Boolean officiel) {
		this.officiel = officiel;
	}
	
	
	
}
