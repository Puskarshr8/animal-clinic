package com.spring5app.animalclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.model.Pet;
import com.spring5app.animalclinic.model.PetType;
import com.spring5app.animalclinic.services.OwnerService;
import com.spring5app.animalclinic.services.PetService;
import com.spring5app.animalclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
	
	private final PetService petService;
	private final OwnerService ownerService;
	private final PetTypeService petTypeService;
	
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	
	public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService)
	{
		this.petService = petService;
		this.ownerService = ownerService;
		this.petTypeService = petTypeService;
	}
	
	@InitBinder("owner")
	public void initBinder(WebDataBinder dataBinder)
	{
		dataBinder.setDisallowedFields("id");		
	}
	
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long id )
	{
		return ownerService.findById(id);
	}
	
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes()
	{
		return petTypeService.findAll();
	}
	
    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/pets/new")    
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result,Model model)
    {
    	if (StringUtils.isNoneBlank(pet.getName() ) && pet.isNew() && owner.getPet(pet) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
   	     pet.setOwner(owner);
    	 owner.getPets().add(pet);
         if (result.hasErrors()) {
             model.addAttribute(pet);
             return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
         } else {
             petService.save(pet);

             return "redirect:/owners/" + owner.getId();
         }
    }
    
    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model)
    {
    	 model.addAttribute("pet", petService.findById(petId));
         return VIEWS_PETS_CREATE_OR_UPDATE_FORM;    	
    }
    
    @PostMapping("/pets/{petId}/edit")
    public String processUpateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model)
    {
    	 if (result.hasErrors()) {
             pet.setOwner(owner);
             model.addAttribute("pet", pet);
             return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
         } else {
        	 pet.setOwner(owner);
             petService.save(pet);
             owner.getPets().add(pet);
             return "redirect:/owners/" + owner.getId();
         }
    }
}
