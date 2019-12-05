package com.oc.forms;

import java.sql.Date;

public class TopoForm {
	
	private String name;
	private String description;
	private String lieu;
	private Date edate;
	private Boolean dispo;
	
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


}
