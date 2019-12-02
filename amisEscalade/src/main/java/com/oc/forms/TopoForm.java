package com.oc.forms;

import java.sql.Date;

public class TopoForm {
	
	private String name;
	private String description;
	private String lieu;
	private Date edate;
	
	public TopoForm() {

	}

	public TopoForm(String name, String description, String lieu, Date edate) {
		super();
		this.name = name;
		this.description = description;
		this.lieu = lieu;
		this.edate = edate;
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

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	

}
