package com.spring5app.animalclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring5app.animalclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {

}
