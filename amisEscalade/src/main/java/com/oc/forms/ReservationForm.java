package com.oc.forms;

import java.sql.Date;

/**
 * The Class ReservationForm.
 */
public class ReservationForm {

	/** The id resa. */
	private long idResa;
	
	/** The emprunteur. */
	private String emprunteur;
	
	/** The proprietaire topo. */
	private String proprietaireTopo;
	
	/** The nom du topo resa. */
	private String nomDuTopoResa;
	
	/** The date de la demande. */
	private Date dateDeLaDemande;
	
	/** The accepter demande. */
	private boolean accepterDemande;
	
	/** The demande en cours. */
	private boolean demandeEnCours;
	
	/**
	 * Instantiates a new reservation form.
	 */
	public ReservationForm() {

	}

	/**
	 * Instantiates a new reservation form.
	 *
	 * @param idResa the id resa
	 * @param emprunteur the emprunteur
	 * @param proprietaireTopo the proprietaire topo
	 * @param nomDuTopoResa the nom du topo resa
	 * @param dateDeLaDemande the date de la demande
	 * @param accepterDemande the accepter demande
	 * @param demandeEnCours the demande en cours
	 */
	public ReservationForm(long idResa, String emprunteur, String proprietaireTopo, String nomDuTopoResa,
			Date dateDeLaDemande, boolean accepterDemande, boolean demandeEnCours) {
		super();
		this.idResa = idResa;
		this.emprunteur = emprunteur;
		this.proprietaireTopo = proprietaireTopo;
		this.nomDuTopoResa = nomDuTopoResa;
		this.dateDeLaDemande = dateDeLaDemande;
		this.accepterDemande = accepterDemande;
		this.demandeEnCours = demandeEnCours;
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
	 * Gets the emprunteur.
	 *
	 * @return the emprunteur
	 */
	public String getEmprunteur() {
		return emprunteur;
	}

	/**
	 * Sets the emprunteur.
	 *
	 * @param emprunteur the new emprunteur
	 */
	public void setEmprunteur(String emprunteur) {
		this.emprunteur = emprunteur;
	}

	/**
	 * Gets the proprietaire topo.
	 *
	 * @return the proprietaire topo
	 */
	public String getProprietaireTopo() {
		return proprietaireTopo;
	}

	/**
	 * Sets the proprietaire topo.
	 *
	 * @param proprietaireTopo the new proprietaire topo
	 */
	public void setProprietaireTopo(String proprietaireTopo) {
		this.proprietaireTopo = proprietaireTopo;
	}

	/**
	 * Gets the nom du topo resa.
	 *
	 * @return the nom du topo resa
	 */
	public String getNomDuTopoResa() {
		return nomDuTopoResa;
	}

	/**
	 * Sets the nom du topo resa.
	 *
	 * @param nomDuTopoResa the new nom du topo resa
	 */
	public void setNomDuTopoResa(String nomDuTopoResa) {
		this.nomDuTopoResa = nomDuTopoResa;
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

	
	
}
