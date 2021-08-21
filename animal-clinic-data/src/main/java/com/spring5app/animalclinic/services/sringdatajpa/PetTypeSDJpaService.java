package com.spring5app.animalclinic.services.sringdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.PetType;
import com.spring5app.animalclinic.repositories.PetTypeRepositoy;
import com.spring5app.animalclinic.services.PetTypeService;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService{
	
	private final PetTypeRepositoy petTypeRepository;	

	public PetTypeSDJpaService(PetTypeRepositoy petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Set<PetType> findAll() {
		Set<PetType> petTypes = new HashSet<>();
		petTypeRepository.findAll().forEach(petTypes::add);
		return petTypes;
	}

	@Override
	public PetType findById(Long id) {
		return petTypeRepository.findById(id).orElse(null);
	}

	@Override
	public PetType save(PetType t) {
		return petTypeRepository.save(t);
	}

	@Override
	public void delete(PetType t) {
		petTypeRepository.delete(t);
		
	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);
	}

}
