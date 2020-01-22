package com.oc.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

/**
 * The Class Rating.
 */
@Entity
public class Rating implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id cot. */
	@Id
	private Long idCot;
	
	// attributs de cotation
	/** The cote. */
	@NonNull
	private String cote;
	
	/**
	 * Instantiates a new rating.
	 */
	// constructeur par défaut
	public Rating() {

	}

	/**
	 * Instantiates a new rating.
	 *
	 * @param idCot the id cot
	 * @param cote the cote
	 */
	// constructeur avec paramètres
	public Rating(Long idCot, String cote) {
		super();
		this.idCot = idCot;
		this.cote = cote;
	}

	/**
	 * Gets the id cot.
	 *
	 * @return the id cot
	 */
	// getters and setters
	public Long getIdCot() {
		return idCot;
	}

	/**
	 * Sets the id cot.
	 *
	 * @param idCot the new id cot
	 */
	public void setIdCot(Long idCot) {
		this.idCot = idCot;
	}

	/**
	 * Gets the cote.
	 *
	 * @return the cote
	 */
	public String getCote() {
		return cote;
	}

	/**
	 * Sets the cote.
	 *
	 * @param cote the new cote
	 */
	public void setCote(String cote) {
		this.cote = cote;
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
