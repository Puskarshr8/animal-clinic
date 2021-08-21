package com.spring5app.animalclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring5app.animalclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
