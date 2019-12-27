package com.oc.forms;

import java.io.Serializable;
import java.util.Date;

public class CommentaireForm implements Serializable{

	private static final long serialVersionUID = 1L;
	// champs
	private Long idCom;
	private String comments;
	private Date date;
	
	// constructeurs
	public CommentaireForm() {
		
	}

	// getters and setters
	public CommentaireForm(Long idCom, String comments, Date date) {
		super();
		this.idCom = idCom;
		this.comments = comments;
		this.date = date;
	}

	public Long getIdCom() {
		return idCom;
	}

	public void setIdCom(Long idCom) {
		this.idCom = idCom;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
