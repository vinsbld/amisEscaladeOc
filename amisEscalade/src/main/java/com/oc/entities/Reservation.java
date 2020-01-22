package com.oc.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class Reservation.
 */
@Entity
public class Reservation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id resa. */
	@Id @GeneratedValue
	private long idResa;
	
	/** The date de la demande. */
	private Date dateDeLaDemande;
	
	/** The accepter demande. */
	private boolean accepterDemande;
	
	/** The demande en cours. */
	private boolean demandeEnCours;
	
	/** The close. */
	private boolean close;
	
	/** The user grimp. */
	@ManyToOne
	@JoinColumn(name = "USR_RES")
	private UserGrimp userGrimp;
	
	/** The topo. */
	@ManyToOne
	@JoinColumn(name = "TPO_RES")
	private Topo topo;

	/**
	 * Instantiates a new reservation.
	 */
	public Reservation() {

	}

	/**
	 * Instantiates a new reservation.
	 *
	 * @param idResa the id resa
	 * @param dateDeLaDemande the date de la demande
	 * @param accepterDemande the accepter demande
	 * @param demandeEnCours the demande en cours
	 * @param close the close
	 * @param userGrimp the user grimp
	 * @param topo the topo
	 */
	public Reservation(long idResa, Date dateDeLaDemande, boolean accepterDemande, boolean demandeEnCours, boolean close,
			UserGrimp userGrimp, Topo topo) {
		super();
		this.idResa = idResa;
		this.dateDeLaDemande = dateDeLaDemande;
		this.accepterDemande = accepterDemande;
		this.demandeEnCours = demandeEnCours;
		this.userGrimp = userGrimp;
		this.topo = topo;
		this.close = close;
	}

	/**
	 * Gets the id resa.
	 *
	 * @return the id resa
	 */
	public long getIdResa() {
		return idResa;
	}

	/**
	 * Sets the id resa.
	 *
	 * @param idResa the new id resa
	 */
	public void setIdResa(long idResa) {
		this.idResa = idResa;
	}

	/**
	 * Gets the date de la demande.
	 *
	 * @return the date de la demande
	 */
	public Date getDateDeLaDemande() {
		return dateDeLaDemande;
	}

	/**
	 * Sets the date de la demande.
	 *
	 * @param dateDeLaDemande the new date de la demande
	 */
	public void setDateDeLaDemande(Date dateDeLaDemande) {
		this.dateDeLaDemande = dateDeLaDemande;
	}

	/**
	 * Checks if is accepter demande.
	 *
	 * @return true, if is accepter demande
	 */
	public boolean isAccepterDemande() {
		return accepterDemande;
	}

	/**
	 * Sets the accepter demande.
	 *
	 * @param accepterDemande the new accepter demande
	 */
	public void setAccepterDemande(boolean accepterDemande) {
		this.accepterDemande = accepterDemande;
	}

	/**
	 * Checks if is demande en cours.
	 *
	 * @return true, if is demande en cours
	 */
	public boolean isDemandeEnCours() {
		return demandeEnCours;
	}

	/**
	 * Sets the demande en cours.
	 *
	 * @param demandeEnCours the new demande en cours
	 */
	public void setDemandeEnCours(boolean demandeEnCours) {
		this.demandeEnCours = demandeEnCours;
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
	 * Gets the topo.
	 *
	 * @return the topo
	 */
	public Topo getTopo() {
		return topo;
	}

	/**
	 * Sets the topo.
	 *
	 * @param topo the new topo
	 */
	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Checks if is close.
	 *
	 * @return true, if is close
	 */
	public boolean isClose() {
		return close;
	}

	/**
	 * Sets the close.
	 *
	 * @param close the new close
	 */
	public void setClose(boolean close) {
		this.close = close;
	}

	
}
