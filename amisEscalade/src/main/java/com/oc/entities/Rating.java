package com.oc.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public class Rating implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long idCot;
	// attributs de cotation
	@NonNull
	private String cote;
	
	// constructeur par défaut
	public Rating() {

	}

	// constructeur avec paramètres
	public Rating(Long idCot, String cote) {
		super();
		this.idCot = idCot;
		this.cote = cote;
	}

	// getters and setters
	public Long getIdCot() {
		return idCot;
	}

	public void setIdCot(Long idCot) {
		this.idCot = idCot;
	}

	public String getCote() {
		return cote;
	}

	public void setCote(String cote) {
		this.cote = cote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
