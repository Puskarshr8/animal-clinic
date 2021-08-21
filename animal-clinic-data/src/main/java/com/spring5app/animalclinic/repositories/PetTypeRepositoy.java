package com.spring5app.animalclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring5app.animalclinic.model.PetType;

public interface PetTypeRepositoy extends CrudRepository<PetType,Long>{

}
