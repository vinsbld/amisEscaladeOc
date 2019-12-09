package com.oc.forms;


public class VoieForm {

	private long idVoie;
	private String name;
	private String cotation;
	
	public VoieForm() {

	}

	public VoieForm(long idVoie, String name, String cotation) {
		super();
		this.idVoie = idVoie;
		this.name = name;
		this.cotation = cotation;
	}

	public long getIdVoie() {
		return idVoie;
	}

	public void setIdVoie(long idVoie) {
		this.idVoie = idVoie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}


	
}
