package com.spring5app.animalclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Speciality;
import com.spring5app.animalclinic.services.SpecialtyService;

@Service
@Profile({"default","map"})
public class SpecialtiesMapService extends AbstractMapService<Speciality, Long> implements SpecialtyService{

	@Override
	public Set<Speciality> findAll()
	{
		return super.findAll();
	}
	
	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Speciality save(Speciality t) {
		return super.save(t);
	}

	@Override
	public void delete(Speciality t) {
		super.delete(t);	
	}

	@Override
	public void deleteById(Long id) {
		super.deleteByID(id);		
	}

}
