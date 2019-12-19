package com.oc.forms;

import java.sql.Date;

public class ReservationForm {

	private long idResa;
	private String emprunteur;
	private String proprietaireTopo;
	private String nomDuTopoResa;
	private Date dateDeLaDemande;
	private boolean accepterDemande;
	private boolean demandeEnCours;
	
	public ReservationForm() {

	}

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

	public long getIdResa() {
		return idResa;
	}

	public void setIdResa(long idResa) {
		this.idResa = idResa;
	}

	public String getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(String emprunteur) {
		this.emprunteur = emprunteur;
	}

	public String getProprietaireTopo() {
		return proprietaireTopo;
	}

	public void setProprietaireTopo(String proprietaireTopo) {
		this.proprietaireTopo = proprietaireTopo;
	}

	public String getNomDuTopoResa() {
		return nomDuTopoResa;
	}

	public void setNomDuTopoResa(String nomDuTopoResa) {
		this.nomDuTopoResa = nomDuTopoResa;
	}

	public Date getDateDeLaDemande() {
		return dateDeLaDemande;
	}

	public void setDateDeLaDemande(Date dateDeLaDemande) {
		this.dateDeLaDemande = dateDeLaDemande;
	}

	public boolean isAccepterDemande() {
		return accepterDemande;
	}

	public void setAccepterDemande(boolean accepterDemande) {
		this.accepterDemande = accepterDemande;
	}

	public boolean isDemandeEnCours() {
		return demandeEnCours;
	}

	public void setDemandeEnCours(boolean demandeEnCours) {
		this.demandeEnCours = demandeEnCours;
	}

	
	
}
