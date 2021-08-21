package com.spring5app.animalclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.PetType;
import com.spring5app.animalclinic.services.PetTypeService;

@Service
@Profile({"default","map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService{

	@Override
	public Set<PetType> findAll() {
		return super.findAll();
	}
	
	@Override
	public PetType findById(Long id) {
		return super.findById(id);
	}

	@Override
	public PetType save(PetType t) {
		return super.save(t);
	}

	@Override
	public void delete(PetType t) {
		super.delete(t);		
	}

	@Override
	public void deleteById(Long id) {
		super.deleteByID(id);	
	}

}
