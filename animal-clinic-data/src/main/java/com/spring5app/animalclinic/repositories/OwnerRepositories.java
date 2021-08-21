package com.spring5app.animalclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring5app.animalclinic.model.Owner;

public interface OwnerRepositories extends CrudRepository<Owner, Long> {
	
	Owner findByLastName(String lastName);
	
	List<Owner> findByLastNameLike(String lastName);
}
