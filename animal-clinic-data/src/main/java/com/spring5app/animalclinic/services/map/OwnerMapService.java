package com.spring5app.animalclinic.services.map;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.model.Pet;
import com.spring5app.animalclinic.services.OwnerService;
import com.spring5app.animalclinic.services.PetService;
import com.spring5app.animalclinic.services.PetTypeService;

@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService{
	
	private final PetTypeService petTypeService;
	private final PetService petService;
	
	public OwnerMapService(PetTypeService petTypeService, PetService petService)
	{
		this.petService = petService;
		this.petTypeService = petTypeService;
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}	
	
	@Override
	public Owner findById(Long id)
	{
		return super.findById(id);
	}
	
	@Override
	public Owner save(Owner owner)
	{
		if(owner != null)
		{
			if(owner.getPets() != null)
			{
				owner.getPets().forEach(pet -> {
					if(pet.getPetType() != null) {
						if(pet.getPetType().getId() == null)
						{
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					}
					else
					{
						throw new RuntimeException("Pet type is required");
					}
					
					if(pet.getId() == null) {
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			return super.save(owner);
		}
		else
		{
			return null;
		}	
	}
	
	@Override
	public void delete(Owner owner)
	{
		super.delete(owner);
	}
	
	@Override
	public void deleteById(Long id)
	{
		super.deleteByID(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
	 /*for(Owner owner : super.findAll())
	 {
		 if(owner.getLastName().equals(lastName))
		 {
			 return owner;
		 }
	 }
		return null;*/
		
		return super.findAll().stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName)).findFirst().orElse(null);
	}

	@Override
	public List<Owner> findByLastNameLike(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
