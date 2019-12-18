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
	private String emprunteur;
	private String proprietaireTopo;
	private String nomDuTopoResa;
	private Date dateDeLaDemande;
	private boolean accepterDemande;
	
	@ManyToOne
	@JoinColumn(name = "USR_PRT")
	private UserGrimp userGrimp;
	
	@OneToOne
	private Topo topo;

	public Reservation() {

	}

	public Reservation(long idResa, String emprunteur, String proprietaireTopo, String nomDuTopoResa,
			Date dateDeLaDemande, boolean accepterDemande, UserGrimp userGrimp, Topo topo) {
		super();
		this.idResa = idResa;
		this.emprunteur = emprunteur;
		this.proprietaireTopo = proprietaireTopo;
		this.nomDuTopoResa = nomDuTopoResa;
		this.dateDeLaDemande = dateDeLaDemande;
		this.accepterDemande = accepterDemande;
		this.userGrimp = userGrimp;
		this.topo = topo;
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
