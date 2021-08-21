package com.spring5app.animalclinic.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9087340702579047986L;
	
	private String username;
	
	private String password;
	
	private List<String> error = new ArrayList<>(2);
	
	public String getUserName() {
		return username;
	}
	
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getError() {
		return this.error;
	}

	public void addError(String error) {
		this.error.add(error);
	}
}
