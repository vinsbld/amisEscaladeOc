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

/**
 * The Class SiteEscalade.
 */
@Entity
public class SiteEscalade implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id site escalade. */
	@Id @GeneratedValue
	private long idSiteEscalade;
	
	// attributs d'un site d'escalade
	/** The nom site escalade. */
	@NonNull
	private String nomSiteEscalade;
	
	/** The lieu. */
	@NonNull
	private String lieu;
	
	/** The officiel. */
	private boolean officiel;
	
	/** The user grimp. */
	// clé étrangère, les sites d'escalades sont liés à un utilisateur
	@ManyToOne
	@JoinColumn(name = "SIT_USR")
	private UserGrimp userGrimp;
	
	/** The secteur. */
	// un site d'escalade a une collection de secteurs
	@OneToMany(mappedBy ="siteEscalade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Secteur> secteur;
	
	/** The commentaires. */
	// clé étrangére un site peut avoir un ou plusieurs commentaires
	@OneToMany(mappedBy = "siteEscalade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Commentaire>commentaires;
		
	/**
	 * Instantiates a new site escalade.
	 */
	public SiteEscalade() {

	}

	/**
	 * Instantiates a new site escalade.
	 *
	 * @param idSiteEscalade the id site escalade
	 * @param nomSiteEscalade the nom site escalade
	 * @param lieu the lieu
	 * @param officiel the officiel
	 * @param userGrimp the user grimp
	 * @param secteur the secteur
	 * @param commentaires the commentaires
	 */
	public SiteEscalade(long idSiteEscalade, String nomSiteEscalade, String lieu, boolean officiel, UserGrimp userGrimp,
			Collection<Secteur> secteur, Collection<Commentaire> commentaires) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.nomSiteEscalade = nomSiteEscalade;
		this.lieu = lieu;
		this.officiel = officiel;
		this.userGrimp = userGrimp;
		this.secteur = secteur;
		this.commentaires = commentaires;
	}

	/**
	 * Gets the id site escalade.
	 *
	 * @return the id site escalade
	 */
	// getters and setters
	public long getIdSiteEscalade() {
		return idSiteEscalade;
	}

	/**
	 * Sets the id site escalade.
	 *
	 * @param idSiteEscalade the new id site escalade
	 */
	public void setIdSiteEscalade(long idSiteEscalade) {
		this.idSiteEscalade = idSiteEscalade;
	}

	/**
	 * Gets the nom site escalade.
	 *
	 * @return the nom site escalade
	 */
	public String getNomSiteEscalade() {
		return nomSiteEscalade;
	}

	/**
	 * Sets the nom site escalade.
	 *
	 * @param nomSiteEscalade the new nom site escalade
	 */
	public void setNomSiteEscalade(String nomSiteEscalade) {
		this.nomSiteEscalade = nomSiteEscalade;
	}

	/**
	 * Gets the lieu.
	 *
	 * @return the lieu
	 */
	public String getLieu() {
		return lieu;
	}

	/**
	 * Sets the lieu.
	 *
	 * @param lieu the new lieu
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/**
	 * Checks if is officiel.
	 *
	 * @return true, if is officiel
	 */
	public boolean isOfficiel() {
		return officiel;
	}

	/**
	 * Sets the officiel.
	 *
	 * @param officiel the new officiel
	 */
	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
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
	 * Gets the secteur.
	 *
	 * @return the secteur
	 */
	public Collection<Secteur> getSecteur() {
		return secteur;
	}

	/**
	 * Sets the secteur.
	 *
	 * @param secteur the new secteur
	 */
	public void setSecteur(Collection<Secteur> secteur) {
		this.secteur = secteur;
	}

	/**
	 * Gets the commentaires.
	 *
	 * @return the commentaires
	 */
	public Collection<Commentaire> getCommentaires() {
		return commentaires;
	}

	/**
	 * Sets the commentaires.
	 *
	 * @param commentaires the new commentaires
	 */
	public void setCommentaires(Collection<Commentaire> commentaires) {
		this.commentaires = commentaires;
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
