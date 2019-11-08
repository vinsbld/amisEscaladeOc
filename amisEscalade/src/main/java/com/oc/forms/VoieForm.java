package com.oc.forms;


public class VoieForm {

	private String name;
	private String cotation;
	
	public VoieForm() {

	}

	public VoieForm(String name, String cotation) {
		
		this.name = name;
		this.cotation = cotation;
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
