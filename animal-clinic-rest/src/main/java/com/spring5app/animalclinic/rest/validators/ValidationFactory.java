package com.spring5app.animalclinic.rest.validators;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.spring5app.animalclinic.rest.api.dto.model.OwnerDTO;

public class ValidationFactory {
	
	private static Map<Type,ApiDataValidator<?>> validators = new HashMap<>();
	
	static
	{
		validators.put(String.class, new StringIdToLongValidator());
		validators.put(OwnerDTO.class, new OwnerValidator());
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> ApiDataValidator<T> getValidator(Type type)
	{
		return (ApiDataValidator<T>) ValidationFactory.validators.get(type);
	}	
}
