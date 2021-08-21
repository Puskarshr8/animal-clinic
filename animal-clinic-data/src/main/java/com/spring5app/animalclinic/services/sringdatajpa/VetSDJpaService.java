package com.spring5app.animalclinic.services.sringdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Vet;
import com.spring5app.animalclinic.repositories.VetRepository;
import com.spring5app.animalclinic.services.VetService;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {
	
	private final VetRepository vetRepository;
	
	public VetSDJpaService(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public Set<Vet> findAll() {
		
		Set<Vet> vets = new HashSet<>();
		
		vetRepository.findAll().forEach(vets::add);
		
		return vets;
	}

	@Override
	public Vet findById(Long id) {
		return vetRepository.findById(id).orElse(null);
	}

	@Override
	public Vet save(Vet t) {
		return vetRepository.save(t);
	}

	@Override
	public void delete(Vet t) {
		vetRepository.delete(t);

	}

	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById(id);

	}

}
