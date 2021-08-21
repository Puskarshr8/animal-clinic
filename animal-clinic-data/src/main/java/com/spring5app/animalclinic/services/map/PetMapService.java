package com.spring5app.animalclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Pet;
import com.spring5app.animalclinic.services.PetService;

@Service
@Profile({"default","map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}	
	
	@Override
	public Pet findById(Long id)
	{
		return super.findById(id);
	}
	
	@Override
	public Pet save(Pet pet)
	{
		return super.save(pet);
	}
	
	@Override
	public void delete(Pet pet)
	{
		super.delete(pet);
	}
	
	@Override
	public void deleteById(Long id)
	{
		super.deleteByID(id);
	}

}
