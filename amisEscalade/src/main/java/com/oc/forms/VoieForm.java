package com.oc.forms;

import java.io.Serializable;

public class VoieForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private long idVoie;
	// champs
	private String name;
	private String cotation;
	
	//constructeurs
	public VoieForm() {

	}

	public VoieForm(long idVoie, String name, String cotation) {
		super();
		this.idVoie = idVoie;
		this.name = name;
		this.cotation = cotation;
	}

	// getters and setters
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
