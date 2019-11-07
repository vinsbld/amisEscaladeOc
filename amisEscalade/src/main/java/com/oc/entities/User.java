package com.oc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class User implements Serializable {
	@Id @GeneratedValue
	private long id_user;
	@NonNull
	@Size(min = 4, max = 8)
	private String pseudo;
	private String email;
	@Size(min = 8)
	private String password;
	
	public User () {
		super();
	}
	
	public User (String pseudo, String email, String password) {
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