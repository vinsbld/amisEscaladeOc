package com.oc.forms;

import java.io.Serializable;
import java.sql.Date;

public class TopoForm implements Serializable{

	private static final long serialVersionUID = 1L;
	// champs
	private String name;
	private String description;
	private String lieu;
	private Date edate;
	private Boolean dispo;
	
	// constructeurs
	public TopoForm() {

	}

	public TopoForm(String name, String description, String lieu, Date edate, Boolean dispo) {
		super();
		this.name = name;
		this.description = description;
		this.lieu = lieu;
		this.edate = edate;
		this.dispo = dispo;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Boolean getDispo() {
		return dispo;
	}

	public void setDispo(Boolean dispo) {
		this.dispo = dispo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
