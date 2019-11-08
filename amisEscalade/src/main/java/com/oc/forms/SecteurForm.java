package com.oc.forms;




public class SecteurForm {

	private long id_secteur;
	private String name;
	private String localisation;
	private String acces;
	
	public SecteurForm() {

	}

	public SecteurForm(long id_secteur, String nomDuSecteur, String localisation, String acces) {
		super();
		this.id_secteur = id_secteur;
		this.name = nomDuSecteur;
		this.localisation = localisation;
		this.acces = acces;
	}

	public long getId_secteur() {
		return id_secteur;
	}

	public void setId_secteur(long id_secteur) {
		this.id_secteur = id_secteur;
	}

	public String getNomDuSecteur() {
		return name;
	}

	public void setNomDuSecteur(String nomDuSecteur) {
		this.name = nomDuSecteur;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getAcces() {
		return acces;
	}

	public void setAcces(String acces) {
		this.acces = acces;
	}
	
	

}
