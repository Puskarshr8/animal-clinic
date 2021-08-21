package com.spring5app.animalclinic.services;

import java.util.List;

import com.spring5app.animalclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
	
	Owner findByLastName(String lastName);
	
	List<Owner> findByLastNameLike(String lastName);
}
