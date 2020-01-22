package com.oc.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

/**
 * The Class Topo.
 */
@Entity
public class Topo implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id topo. */
	@Id @GeneratedValue
	private long idTopo;
	
	// attributs d'un topo
	/** The name. */
	@NonNull
	private String name;
	
	/** The description. */
	@NonNull
	@Length(max = 255)
	private String description;
	
	/** The lieu. */
	@NonNull
	private String lieu;
	
	/** The edate. */
	@NonNull
	private Date edate;
	
	/** The dispo. */
	@NonNull
	private Boolean dispo;
	
	/** The user grimp. */
	// clé étrangère topo lié à un utilisateur
	@ManyToOne 
	@JoinColumn(name = "TOPO_USR")
	private UserGrimp userGrimp;
	
	/** The reservations. */
	// clé étrangère topo lié à une réservation
	@OneToMany(mappedBy = "topo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Reservation>reservations;

	/**
	 * Instantiates a new topo.
	 */
	// constructeur par défaut
	public Topo() {

	}
	
	/**
	 * Instantiates a new topo.
	 *
	 * @param idTopo the id topo
	 * @param name the name
	 * @param description the description
	 * @param lieu the lieu
	 * @param edate the edate
	 * @param dispo the dispo
	 * @param userGrimp the user grimp
	 * @param reservations the reservations
	 */
	// constructeur avec paramètres
	public Topo(long idTopo, String name, @Length(max = 255) String description, String lieu, Date edate, Boolean dispo,
			UserGrimp userGrimp, Collection<Reservation> reservations) {
		super();
		this.idTopo = idTopo;
		this.name = name;
		this.description = description;
		this.lieu = lieu;
		this.edate = edate;
		this.dispo = dispo;
		this.userGrimp = userGrimp;
		this.reservations = reservations;
	}
	
	/**
	 * Gets the id topo.
	 *
	 * @return the id topo
	 */
	// getters and setters
	public long getIdTopo() {
		return idTopo;
	}

	/**
	 * Sets the id topo.
	 *
	 * @param idTopo the new id topo
	 */
	public void setIdTopo(long idTopo) {
		this.idTopo = idTopo;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * Gets the edate.
	 *
	 * @return the edate
	 */
	public Date getEdate() {
		return edate;
	}

	/**
	 * Sets the edate.
	 *
	 * @param edate the new edate
	 */
	public void setEdate(Date edate) {
		this.edate = edate;
	}

	/**
	 * Gets the dispo.
	 *
	 * @return the dispo
	 */
	public Boolean getDispo() {
		return dispo;
	}

	/**
	 * Sets the dispo.
	 *
	 * @param dispo the new dispo
	 */
	public void setDispo(Boolean dispo) {
		this.dispo = dispo;
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
	 * Gets the reservations.
	 *
	 * @return the reservations
	 */
	public Collection<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * Sets the reservations.
	 *
	 * @param reservations the new reservations
	 */
	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
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