package com.oc.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Role {
	
	@Id @GeneratedValue
	private long id_role;
	@Size(max=9)
	private String grimpUser;
	@Size(max=10)
	private String grimpAdmin;
	
	public Role() {

	}

	public Role(long id_role, @Size(max = 9) String grimpUser, @Size(max = 10) String grimpAdmin) {
		super();
		this.id_role = id_role;
		this.grimpUser = grimpUser;
		this.grimpAdmin = grimpAdmin;
	}

	public long getId_role() {
		return id_role;
	}

	public void setId_role(long id_role) {
		this.id_role = id_role;
	}

	public String getGrimpUser() {
		return grimpUser;
	}

	public void setGrimpUser(String grimpUser) {
		this.grimpUser = grimpUser;
	}

	public String getGrimpAdmin() {
		return grimpAdmin;
	}

	public void setGrimpAdmin(String grimpAdmin) {
		this.grimpAdmin = grimpAdmin;
	}
	
	

	

}
