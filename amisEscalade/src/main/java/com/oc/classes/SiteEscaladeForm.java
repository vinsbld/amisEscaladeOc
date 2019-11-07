package com.oc.classes;

public class SiteEscaladeForm {

	private String name;
	private String departement;
	private String ville;
	
	public SiteEscaladeForm() {

	}

	public SiteEscaladeForm(String name, String departement, String ville) {
		super();
		this.name = name;
		this.departement = departement;
		this.ville = ville;
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
	
	
	
}
