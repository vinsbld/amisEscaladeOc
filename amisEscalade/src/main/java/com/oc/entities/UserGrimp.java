package com.oc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class UserGrimp implements Serializable {
	@Id @GeneratedValue
	private long id_user;
	@NonNull
	@Size(min = 2, max = 30)
	private String pseudo;
	@NonNull
	private String email;
	@Size(min = 4)
	@NonNull
	private String password;
	
	public UserGrimp () {
		super();
	}
	
	public UserGrimp (String pseudo, String email, String password) {
		super();
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}