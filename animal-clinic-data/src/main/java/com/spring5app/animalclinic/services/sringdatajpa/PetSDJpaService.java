package com.spring5app.animalclinic.services.sringdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Pet;
import com.spring5app.animalclinic.repositories.PetRepository;
import com.spring5app.animalclinic.services.PetService;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
	
	private final PetRepository petRepository;
	
	

	public PetSDJpaService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> pets = new HashSet<>();
		petRepository.findAll().forEach(pets::add);
		return pets;
	}

	@Override
	public Pet findById(Long id) {
		return petRepository.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet t) {
		return petRepository.save(t);
	}

	@Override
	public void delete(Pet t) {
		petRepository.delete(t);
		
	}

	@Override
	public void deleteById(Long id) {
		petRepository.deleteById(id);
		
	}

}
