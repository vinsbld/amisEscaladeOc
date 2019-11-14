package com.oc.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Entity
public class Topo implements Serializable{
	
	@Id @GeneratedValue
	private long id_topo;
	@NonNull
	private String name;
	@NonNull
	@Length(max = 255)
	private String description;
	@NonNull
	private Date edate;
	
	public Topo() {

	}

	public Topo(long id_topo, String name, String description, Date edate) {
		super();
		this.id_topo = id_topo;
		this.name = name;
		this.description = description;
		this.edate = edate;
	}

	public long getId_topo() {
		return id_topo;
	}

	public void setId_topo(long id_topo) {
		this.id_topo = id_topo;
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
	
	
	

}
