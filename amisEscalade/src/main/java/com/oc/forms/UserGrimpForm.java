package com.oc.forms;

import java.io.Serializable;

/**
 * The Class UserGrimpForm.
 */
public class UserGrimpForm implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id user. */
	private long id_user;
	
	// champs
	/** The username. */
	private String username;
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
	
	/**
	 * Instantiates a new user grimp form.
	 */
	// constructeurs
	public UserGrimpForm() {

	}

	/**
	 * Instantiates a new user grimp form.
	 *
	 * @param id_user the id user
	 * @param username the username
	 * @param email the email
	 * @param password the password
	 */
	public UserGrimpForm(long id_user, String username, String email, String password) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	// getters and setters
	public long getId_user() {
		return id_user;
	}

	/**
	 * Sets the id user.
	 *
	 * @param id_user the new id user
	 */
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
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
