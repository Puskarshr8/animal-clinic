package com.spring5app.animalclinic.services.sringdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Speciality;
import com.spring5app.animalclinic.repositories.SpecialityRepository;
import com.spring5app.animalclinic.services.SpecialtyService;

@Service
@Profile("springdatajpa")
public class SpecialtiesSDJpaService implements SpecialtyService {
	
	private final SpecialityRepository specialityRepository;

	public SpecialtiesSDJpaService(SpecialityRepository specialityRepository) {
		this.specialityRepository = specialityRepository;
	}

	@Override
	public Set<Speciality> findAll() {
		Set<Speciality> specialities = new HashSet<>();
		specialityRepository.findAll().forEach(specialities::add);
		return specialities;
	}

	@Override
	public Speciality findById(Long id) {
		return specialityRepository.findById(id).orElse(null);
	}

	@Override
	public Speciality save(Speciality t) {
		return specialityRepository.save(t);
	}

	@Override
	public void delete(Speciality t) {
		specialityRepository.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		specialityRepository.deleteById(id);
	}

}
