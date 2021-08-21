package com.spring5app.animalclinic.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring5app.animalclinic.model.User;

public interface UserRepository extends CrudRepository<User,Long>{
	
	Optional<User> findByUserName(String userName);

}
