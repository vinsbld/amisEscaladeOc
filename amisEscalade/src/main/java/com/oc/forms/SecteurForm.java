package com.oc.forms;




public class SecteurForm {


	private String name;
	private String localisation;
	private String acces;
	
	public SecteurForm() {

	}

	public SecteurForm(String nomDuSecteur, String localisation, String acces) {

		this.name = nomDuSecteur;
		this.localisation = localisation;
		this.acces = acces;
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
