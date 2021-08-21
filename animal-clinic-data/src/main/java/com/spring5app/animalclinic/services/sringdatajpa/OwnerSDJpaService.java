package com.spring5app.animalclinic.services.sringdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.repositories.OwnerRepositories;
import com.spring5app.animalclinic.repositories.PetRepository;
import com.spring5app.animalclinic.repositories.PetTypeRepositoy;
import com.spring5app.animalclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
	
	Logger logger = LoggerFactory.getLogger(OwnerSDJpaService.class);
	
	private final OwnerRepositories ownerRepository;
	private final PetRepository petResepository;
	private final PetTypeRepositoy petTypeRepository;
	
	

	public OwnerSDJpaService(OwnerRepositories ownerRepository, PetRepository petResepository,
			PetTypeRepositoy petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petResepository = petResepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Set<Owner> findAll() {
		
		logger.info("finding all owners");
		
		Set<Owner> owners = new HashSet<>();
		
		ownerRepository.findAll().forEach(owners::add);
		
		return owners;
		
	}

	@Override
	public Owner findById(Long id) {
		return ownerRepository.findById(id).orElse(null);
	}

	@Override
	public Owner save(Owner t) {
		return ownerRepository.save(t);
	}

	@Override
	public void delete(Owner t) {
		ownerRepository.delete(t);

	}

	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);

	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public List<Owner> findByLastNameLike(String lastName) {
		logger.info("finding owner "+lastName);
		return ownerRepository.findByLastNameLike(lastName);
	}

}
