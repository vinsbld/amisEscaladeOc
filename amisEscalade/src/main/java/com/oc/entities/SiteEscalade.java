package com.oc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class SiteEscalade implements Serializable{
	@Id @GeneratedValue
	private long idSiteEscalade;
	@NonNull
	private String nomSiteEscalade;
	@NonNull
	private String departement;
	@NonNull
	private String ville;
		
	
	public SiteEscalade() {

	}


	public SiteEscalade(long idSiteEscalade, String nomSiteEscalade, String departement, String ville) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.nomSiteEscalade = nomSiteEscalade;
		this.departement = departement;
		this.ville = ville;
	}


	public long getIdSiteEscalade() {
		return idSiteEscalade;
	}


	public void setIdSiteEscalade(long idSiteEscalade) {
		this.idSiteEscalade = idSiteEscalade;
	}


	public String getNomSiteEscalade() {
		return nomSiteEscalade;
	}


	public void setNomSiteEscalade(String nomSiteEscalade) {
		this.nomSiteEscalade = nomSiteEscalade;
	}


	public String getDepartement() {
		return departement;
	}


	public void setDepartement(String departement) {
		this.departement = departement;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}




	
}
