package com.spring5app.animalclinic.rest.validators;

import com.spring5app.animalclinic.rest.api.dto.model.OwnerDTO;

public class OwnerValidator implements ApiDataValidator<OwnerDTO>
{

	@Override
	public boolean validate(OwnerDTO owner, ValidationErrors error) {
		
		boolean result = false;
		if(owner.getName() == null || owner.getName().getFirstName() == null || owner.getName().getFirstName().isEmpty())
		{
			error.add("First name cannot be null or empty. ");
			result = true;
		}
		
		if(owner.getName() == null || owner.getName().getLastName() == null || owner.getName().getLastName().isEmpty())
		{
			error.add("Last name cannot be null or empty. ");
			result = true;
		}	
		return result;
	}
}
