package com.spring5app.animalclinic.security.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -366711486181846496L;
	private final String JWT;

	public AuthenticationResponse(String jWT) 
	{
		JWT = jWT;
	}

	public String getJWT() {
		return JWT;
	}
}
