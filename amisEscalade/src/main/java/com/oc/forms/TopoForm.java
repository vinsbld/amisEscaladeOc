package com.oc.forms;

import java.io.Serializable;
import java.sql.Date;

/**
 * The Class TopoForm.
 */
public class TopoForm implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	// champs
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The lieu. */
	private String lieu;
	
	/** The edate. */
	private Date edate;
	
	/** The dispo. */
	private Boolean dispo;
	
	/**
	 * Instantiates a new topo form.
	 */
	// constructeurs
	public TopoForm() {

	}

	/**
	 * Instantiates a new topo form.
	 *
	 * @param name the name
	 * @param description the description
	 * @param lieu the lieu
	 * @param edate the edate
	 * @param dispo the dispo
	 */
	public TopoForm(String name, String description, String lieu, Date edate, Boolean dispo) {
		super();
		this.name = name;
		this.description = description;
		this.lieu = lieu;
		this.edate = edate;
		this.dispo = dispo;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	// getters and setters
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
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
