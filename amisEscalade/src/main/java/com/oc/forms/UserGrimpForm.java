package com.oc.forms;

import java.io.Serializable;

public class UserGrimpForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id_user;
	// champs
	private String username;
	private String email;
	private String password;
	
	// constructeurs
	public UserGrimpForm() {

	}

	public UserGrimpForm(long id_user, String username, String email, String password) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	// getters and setters
	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
