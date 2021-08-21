package com.spring5app.animalclinic.rest.validators;

public interface ApiDataValidator<T> {	
	
	public boolean validate(T t, ValidationErrors error);
	
}
