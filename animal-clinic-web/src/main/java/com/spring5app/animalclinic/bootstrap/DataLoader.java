package com.spring5app.animalclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.model.Pet;
import com.spring5app.animalclinic.model.PetType;
import com.spring5app.animalclinic.model.Speciality;
import com.spring5app.animalclinic.model.Status;
import com.spring5app.animalclinic.model.User;
import com.spring5app.animalclinic.model.Vet;
import com.spring5app.animalclinic.model.Visit;
import com.spring5app.animalclinic.model.common.Address;
import com.spring5app.animalclinic.model.common.Contact;
import com.spring5app.animalclinic.model.common.Phone;
import com.spring5app.animalclinic.model.common.PhoneType;
import com.spring5app.animalclinic.model.common.Sex;
import com.spring5app.animalclinic.services.OwnerService;
import com.spring5app.animalclinic.services.PetTypeService;
import com.spring5app.animalclinic.services.SpecialtyService;
import com.spring5app.animalclinic.services.UserService;
import com.spring5app.animalclinic.services.VetService;
import com.spring5app.animalclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialityService;
	private final VisitService visitService;
	private final UserService userService;
	
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialityService, VisitService visitService, UserService userService)
	{
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
		this.userService = userService;
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		int count = petTypeService.findAll().size();
		
		if(count == 0)
		{
			loadData();
		}	
	}
	
	private void loadData()
	{
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		
		Speciality surgery = new Speciality();
		surgery.setDescription("Surgery");
		
		Speciality dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		
		specialityService.save(radiology);
		specialityService.save(surgery);
		specialityService.save(dentistry);
		
		Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Brookshield");
        owner1.setCity("Houston");
        owner1.setTelephone("123-456-7890");
        
        Pet mikePet = new Pet();
        mikePet.setName("Rosco");
        mikePet.setPetType(savedDogPetType);
        mikePet.setBirthday(LocalDate.now());
        mikePet.setOwner(owner1);        
        owner1.getPets().add(mikePet);        
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("Manchester");
        owner2.setCity("Tampa");
        owner2.setTelephone("345-456-7890");
        
        Pet FionaPet = new Pet();
        FionaPet.setName("Poos");
        FionaPet.setPetType(savedCatPetType);
        FionaPet.setBirthday(LocalDate.now());
        FionaPet.setOwner(owner2);        
        owner2.getPets().add(FionaPet);        
       
        ownerService.save(owner2);
        
        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Sneazy Date");
        visit.setPet(FionaPet);
        
        visitService.save(visit);
       
        
        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(surgery);
        vet2.getSpecialities().add(dentistry);
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
		
        User user = new User(1L);        
        user.getUserInfo().getName().setFirstName("Om");
        user.getUserInfo().getName().setLastName("Hari");
        user.getUserInfo().setDateOfBirth(LocalDate.of(1992, 11, 01));
        user.getUserInfo().setSex(Sex.MALE);
        
        Contact contact = new Contact();
        contact.addPhone(new Phone("4022348876", PhoneType.HOME));
        contact.addPhone(new Phone("4022349987", PhoneType.WORK));
        user.getUserInfo().setContact(contact);
        
        Address address = new Address();
        address.setAddressLine1("12536 WestHope Dr");
        address.setAddressLine2("#12");
        address.setCity("Orlando");
        address.setZipCode("32837");
        address.setState("Florida");
        address.setCountry("USA");
        address.setMailing(true);
        address.setPrimary(true);
        user.getUserInfo().addAddress(address);
        
        Address address2 = new Address();
        address2.setAddressLine1("11525 Blackmoor Dr");
        address2.setAddressLine2(" Apt 14");
        address2.setCity("Orlando");
        address2.setZipCode("32837");
        address2.setState("Florida");
        address2.setCountry("USA");
        address2.setMailing(false);
        address2.setPrimary(false);
        user.getUserInfo().addAddress(address2);
        
        user.setEmail("test@gmail.com");
        user.setStatus(Status.INACTIVE);
        user.setUserName("OHari");
        user.setPassword("adacadabra");
        
        userService.save(user);
        
        System.out.println("Loaded Users....");
        
	}

}
