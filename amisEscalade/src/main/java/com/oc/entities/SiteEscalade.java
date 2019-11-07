package com.oc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class SiteEscalade implements Serializable{
	@Id @GeneratedValue
	private long id_site_escalade;
	@NonNull
	private String nomSiteEscalade;
	@NonNull
	private String departement;
	@NonNull
	private String ville;
		
	
	public SiteEscalade() {

	}

	public SiteEscalade(long id_site_scalade, String nomSiteEscalade, String departement, String ville) {

		this.id_site_escalade = id_site_scalade;
		this.nomSiteEscalade = nomSiteEscalade;
		this.departement = departement;
		this.ville = ville;
	}

	public long getId_site_scalade() {
		return id_site_escalade;
	}

	public void setId_site_scalade(long id_site_scalade) {
		this.id_site_escalade = id_site_scalade;
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
