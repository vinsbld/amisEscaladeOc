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

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long idSiteEscalade;
	
	// attributs d'un site d'escalade
	@NonNull
	private String nomSiteEscalade;
	@NonNull
	private String lieu;
	private boolean officiel;
	
	// clé étrangère, les sites d'escalades sont liés à un utilisateur
	@ManyToOne
	@JoinColumn(name = "SIT_USR")
	private UserGrimp userGrimp;
	
	// un site d'escalade a une collection de secteurs
	@OneToMany(mappedBy ="siteEscalade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Secteur> secteur;
	
	// clé étrangére, les sites d'escalades sont créer d'après certains éléments du codex
	@ManyToOne
	@JoinColumn(name = "CDX_STE")
	private Codex codex;
	
	// clé étrangére un site peut avoir un ou plusieurs commentaires
	@OneToMany(mappedBy = "siteEscalade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Commentaire>commentaires;
		
	// constructeur par défaut
	public SiteEscalade() {

	}

	// constructeur avec paramètres
	public SiteEscalade(long idSiteEscalade, String nomSiteEscalade, String lieu, boolean officiel, UserGrimp userGrimp,
			Collection<Secteur> secteur, Codex codex, Collection<Commentaire> commentaires) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.nomSiteEscalade = nomSiteEscalade;
		this.lieu = lieu;
		this.officiel = officiel;
		this.userGrimp = userGrimp;
		this.secteur = secteur;
		this.codex = codex;
		this.commentaires = commentaires;
	}

	// getters and setters
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

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public boolean isOfficiel() {
		return officiel;
	}

	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
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

	public Codex getCodex() {
		return codex;
	}

	public void setCodex(Codex codex) {
		this.codex = codex;
	}

	public Collection<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Collection<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
