package com.spring5app.animalclinic.rest.validators;

import java.util.HashSet;
import java.util.Set;

public class ValidationErrors {
	
	final Set<String> errors = new HashSet<>();
	
	public void add(String error)
	{
		errors.add(error);
	}
	
	public Set<String> getErrors()
	{
		return errors;
	}
}
