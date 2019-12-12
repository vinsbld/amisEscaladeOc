package com.oc.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Entity
public class Topo implements Serializable{
	
	@Id @GeneratedValue
	private long idTopo;
	@NonNull
	private String name;
	@NonNull
	@Length(max = 255)
	private String description;
	@NonNull
	private String lieu;
	@NonNull
	private Date edate;
	@NonNull
	private Boolean dispo;
	
	
	
	  @ManyToOne 
	  @JoinColumn(name = "TOPO_USR") 
	  private UserGrimp userGrimp;
	 
	public Topo() {

	}

	public Topo(long idTopo, String name, @Length(max = 255) String description, String lieu, Date edate, Boolean dispo,
			UserGrimp userGrimp) {
		super();
		this.idTopo = idTopo;
		this.name = name;
		this.description = description;
		this.lieu = lieu;
		this.edate = edate;
		this.dispo = dispo;
		this.userGrimp = userGrimp;
	}

	public long getIdTopo() {
		return idTopo;
	}

	public void setIdTopo(long idTopo) {
		this.idTopo = idTopo;
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

	public UserGrimp getUserGrimp() {
		return userGrimp;
	}

	public void setUserGrimp(UserGrimp userGrimp) {
		this.userGrimp = userGrimp;
	}



}
