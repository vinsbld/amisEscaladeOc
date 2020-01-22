package com.oc.forms;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class CommentaireForm.
 */
public class CommentaireForm implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	// champs
	/** The id com. */
	private Long idCom;
	
	/** The comments. */
	private String comments;
	
	/** The date. */
	private Date date;
	
	/**
	 * Instantiates a new commentaire form.
	 */
	// constructeurs
	public CommentaireForm() {
		
	}

	/**
	 * Instantiates a new commentaire form.
	 *
	 * @param idCom the id com
	 * @param comments the comments
	 * @param date the date
	 */
	public CommentaireForm(Long idCom, String comments, Date date) {
		super();
		this.idCom = idCom;
		this.comments = comments;
		this.date = date;
	}

	/**
	 * Gets the id com.
	 *
	 * @return the id com
	 */
	// getters and setters
	public Long getIdCom() {
		return idCom;
	}

	/**
	 * Sets the id com.
	 *
	 * @param idCom the new id com
	 */
	public void setIdCom(Long idCom) {
		this.idCom = idCom;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
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
