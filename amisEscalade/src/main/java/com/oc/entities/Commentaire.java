package com.oc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class Commentaire.
 */
@Entity
public class Commentaire implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id com. */
	@Id @GeneratedValue
	private Long idCom;
	
	/** The comments. */
	private String comments;
	
	private Date date;
	
	/** The user grimp. */
	// un ou plusieurs commentaires pour un site d'escalade
	@ManyToOne
	@JoinColumn(name = "USR_COM")
	private UserGrimp userGrimp;
	
	/** The site escalade. */
	// un ou plusieurs commentaires par un utilisateur
	@ManyToOne
	@JoinColumn(name = "SIT_COM")
	private SiteEscalade siteEscalade;

	/**
	 * Instantiates a new commentaire.
	 */
	public Commentaire() {

	}

	/**
	 * Instantiates a new commentaire.
	 *
	 * @param idCom the id com
	 * @param comments the comments
	 * @param date the date
	 * @param userGrimp the user grimp
	 * @param siteEscalade the site escalade
	 */
	public Commentaire(Long idCom, String comments, Date date, UserGrimp userGrimp, SiteEscalade siteEscalade) {
		super();
		this.idCom = idCom;
		this.comments = comments;
		this.date = date;
		this.userGrimp = userGrimp;
		this.siteEscalade = siteEscalade;
	}


	/**
	 * Gets the id com.
	 *
	 * @return the id com
	 */
	// getters and setters
	public Long getIdCom() {
		return idCom;
	}

	/**
	 * Sets the id com.
	 *
	 * @param idCom the new id com
	 */
	public void setIdCom(Long idCom) {
		this.idCom = idCom;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the user grimp.
	 *
	 * @return the user grimp
	 */
	public UserGrimp getUserGrimp() {
		return userGrimp;
	}

	/**
	 * Sets the user grimp.
	 *
	 * @param userGrimp the new user grimp
	 */
	public void setUserGrimp(UserGrimp userGrimp) {
		this.userGrimp = userGrimp;
	}

	/**
	 * Gets the site escalade.
	 *
	 * @return the site escalade
	 */
	public SiteEscalade getSiteEscalade() {
		return siteEscalade;
	}

	/**
	 * Sets the site escalade.
	 *
	 * @param siteEscalade the new site escalade
	 */
	public void setSiteEscalade(SiteEscalade siteEscalade) {
		this.siteEscalade = siteEscalade;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
