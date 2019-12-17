package com.oc.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reservation implements Serializable {

	@Id @GeneratedValue
	private long idResa;
	private String demandeur;
	private String proprietaire;
	private String nomDuTopoResa;
	private Date dateDeLaDemande;
	private boolean accepter;
	
	@ManyToOne
	@JoinColumn(name = "USR_PRT")
	private UserGrimp userGrimp;
	
	@OneToOne
	private Topo topo;

	public Reservation() {

	}

	public Reservation(long idResa, String demandeur, String proprietaire, String nomDuTopoResa, Date dateDeLaDemande,
			boolean accepter, UserGrimp userGrimp, Topo topo) {
		super();
		this.idResa = idResa;
		this.demandeur = demandeur;
		this.proprietaire = proprietaire;
		this.nomDuTopoResa = nomDuTopoResa;
		this.dateDeLaDemande = dateDeLaDemande;
		this.accepter = accepter;
		this.userGrimp = userGrimp;
		this.topo = topo;
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

	public UserGrimp getUserGrimp() {
		return userGrimp;
	}

	public void setUserGrimp(UserGrimp userGrimp) {
		this.userGrimp = userGrimp;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}


	
	
}
