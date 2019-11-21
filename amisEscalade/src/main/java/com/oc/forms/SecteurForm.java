package com.oc.forms;




public class SecteurForm {

	private long idSecteur;
	private String name;
	private String localisation;
	private String acces;
	
	public SecteurForm() {

	}


	public SecteurForm(long idSecteur, String name, String localisation, String acces) {
		super();
		this.idSecteur = idSecteur;
		this.name = name;
		this.localisation = localisation;
		this.acces = acces;
	}




	public long getIdSecteur() {
		return idSecteur;
	}


	public void setIdSecteur(long idSecteur) {
		this.idSecteur = idSecteur;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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
