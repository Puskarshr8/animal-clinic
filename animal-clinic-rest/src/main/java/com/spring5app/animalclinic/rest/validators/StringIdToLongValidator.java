package com.spring5app.animalclinic.rest.validators;

public class StringIdToLongValidator implements ApiDataValidator<String>{

	@Override
	public boolean validate(String t, ValidationErrors error) {
		
		boolean result = false;
		
		if(t == null || t.isEmpty())
		{
			result = true;
			error.add("Id cannot be null or empty.");
		}
		
		try
		{
			Long.valueOf(t);
		}
		catch(NumberFormatException e)
		{
			result = true;
			error.add("Id cannot be a invalid number.");
		}
		
		return result;
	}
}
