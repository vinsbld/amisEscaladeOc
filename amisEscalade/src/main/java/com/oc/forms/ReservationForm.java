package com.oc.forms;

import java.sql.Date;

public class ReservationForm {

	private long idResa;
	private String demandeur;
	private String proprietaire;
	private String nomDuTopoResa;
	private Date dateDeLaDemande;
	private boolean accepter;
	private long idProprietaire;
	private long idTopo;
	
	public ReservationForm() {

	}
	
	public ReservationForm(long idResa, String demandeur, String proprietaire, String nomDuTopoResa,
			Date dateDeLaDemande, boolean accepter, long idProprietaire, long idTopo) {
		super();
		this.idResa = idResa;
		this.demandeur = demandeur;
		this.proprietaire = proprietaire;
		this.nomDuTopoResa = nomDuTopoResa;
		this.dateDeLaDemande = dateDeLaDemande;
		this.accepter = accepter;
		this.idProprietaire = idProprietaire;
		this.idTopo = idTopo;
	}
	
	public long getIdResa() {
		return idResa;
	}
	public void setIdResa(long idResa) {
		this.idResa = idResa;
	}
	public String getDemandeur() {
		return demandeur;
	}
	public void setDemandeur(String demandeur) {
		this.demandeur = demandeur;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
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
	public boolean isAccepter() {
		return accepter;
	}
	public void setAccepter(boolean accepter) {
		this.accepter = accepter;
	}
	public long getIdProprietaire() {
		return idProprietaire;
	}
	public void setIdProprietaire(long idProprietaire) {
		this.idProprietaire = idProprietaire;
	}
	public long getIdTopo() {
		return idTopo;
	}
	public void setIdTopo(long idTopo) {
		this.idTopo = idTopo;
	}
	
	
	
}
