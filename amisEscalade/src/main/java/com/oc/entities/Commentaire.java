package com.oc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commentaire implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long idCom;
	// attibut d'un commentaire
	private String comments;
	private Date date;
	
	// un ou plusieurs commentaires pour un site d'escalade
	@ManyToOne
	@JoinColumn(name = "USR_COM")
	private UserGrimp userGrimp;
	
	// un ou plusieurs commentaires par un utilisateur
	@ManyToOne
	@JoinColumn(name = "SIT_COM")
	private SiteEscalade siteEscalade;

	// constructeur par défaut
	public Commentaire() {

	}

	// constructeur avec paramètres
	public Commentaire(Long idCom, String comments, Date date, UserGrimp userGrimp, SiteEscalade siteEscalade) {
		super();
		this.idCom = idCom;
		this.comments = comments;
		this.date = date;
		this.userGrimp = userGrimp;
		this.siteEscalade = siteEscalade;
	}


	// getters and setters
	public Long getIdCom() {
		return idCom;
	}

	public void setIdCom(Long idCom) {
		this.idCom = idCom;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public UserGrimp getUserGrimp() {
		return userGrimp;
	}

	public void setUserGrimp(UserGrimp userGrimp) {
		this.userGrimp = userGrimp;
	}

	public SiteEscalade getSiteEscalade() {
		return siteEscalade;
	}

	public void setSiteEscalade(SiteEscalade siteEscalade) {
		this.siteEscalade = siteEscalade;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
