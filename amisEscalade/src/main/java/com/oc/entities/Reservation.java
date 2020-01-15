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

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long idResa;
	private Date dateDeLaDemande;
	private boolean accepterDemande;
	private boolean demandeEnCours;
	private boolean close;
	
	@ManyToOne
	@JoinColumn(name = "USR_RES")
	private UserGrimp userGrimp;
	
	@ManyToOne
	@JoinColumn(name = "TPO_RES")
	private Topo topo;

	public Reservation() {

	}

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

	public long getIdResa() {
		return idResa;
	}

	public void setIdResa(long idResa) {
		this.idResa = idResa;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	
}
