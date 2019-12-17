package com.oc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	private Boolean officiel;
	
	@ManyToOne
	@JoinColumn(name = "SIT_USR")
	private UserGrimp userGrimp;
	
	@OneToMany(mappedBy ="siteEscalade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Secteur> secteur;
	
	
	public SiteEscalade() {

	}


	public SiteEscalade(long idSiteEscalade, String nomSiteEscalade, String departement, String ville, Boolean officiel,
			UserGrimp userGrimp, Collection<Secteur> secteur) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.nomSiteEscalade = nomSiteEscalade;
		this.departement = departement;
		this.ville = ville;
		this.officiel = officiel;
		this.userGrimp = userGrimp;
		this.secteur = secteur;
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


	public UserGrimp getUserGrimp() {
		return userGrimp;
	}


	public void setUserGrimp(UserGrimp userGrimp) {
		this.userGrimp = userGrimp;
	}


	public Collection<Secteur> getSecteur() {
		return secteur;
	}


	public void setSecteur(Collection<Secteur> secteur) {
		this.secteur = secteur;
	}


	public Boolean getOfficiel() {
		return officiel;
	}


	public void setOfficiel(Boolean officiel) {
		this.officiel = officiel;
	}


	
}
