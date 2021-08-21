package com.spring5app.animalclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring5app.animalclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
