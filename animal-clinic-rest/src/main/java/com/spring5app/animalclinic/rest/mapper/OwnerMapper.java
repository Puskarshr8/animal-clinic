package com.spring5app.animalclinic.rest.mapper;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.rest.api.dto.model.OwnerDTO;

public class OwnerMapper implements Mapper<Owner, OwnerDTO>{

	@Override
	public Owner mapTo(OwnerDTO f) {
		Owner owner = new Owner();
		com.spring5app.animalclinic.rest.api.dto.model.Name name = f.getName();
		if(name != null)
		{
			owner.setFirstName(name.getFirstName());
			owner.setLastName(name.getLastName());
		}
		
		com.spring5app.animalclinic.rest.api.dto.model.Address address = f.getAddress();
		
		if(address != null)
		{
			owner.setAddress(address.getAddress1() +  " " + address.getCity() + " " + address.getCountry());
			owner.setCity(address.getCity());
		}
		
		com.spring5app.animalclinic.rest.api.dto.model.Contact contact = f.getContact();
		
		if(contact != null && contact.getPhone() != null)
		{
			owner.setTelephone(contact.getPhone());
		}
		
		return owner;
	}
	
	@Override
	public OwnerDTO mapFrom(Owner t) {
		OwnerDTO dto = new OwnerDTO();
		
		dto.setId(t.getId());
		
		com.spring5app.animalclinic.rest.api.dto.model.Name name = new com.spring5app.animalclinic.rest.api.dto.model.Name();
		name.setFirstName(t.getFirstName());
		name.setLastName(t.getLastName());
		dto.setName(name);
		
		com.spring5app.animalclinic.rest.api.dto.model.Address address = new com.spring5app.animalclinic.rest.api.dto.model.Address();
		address.setAddress1(t.getAddress());
		address.setCity(t.getCity());
		address.setCountry("USA");
		address.setZipCode(32837L);
		dto.setAddress(address);
		
		com.spring5app.animalclinic.rest.api.dto.model.Contact contact = new com.spring5app.animalclinic.rest.api.dto.model.Contact();
		contact.setPhone(t.getTelephone());
		dto.setContact(contact);
		
		return dto;
	}

}
